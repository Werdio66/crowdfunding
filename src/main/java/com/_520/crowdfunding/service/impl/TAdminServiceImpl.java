package com._520.crowdfunding.service.impl;

import com._520.crowdfunding.common.exception.LoginException;
import com._520.crowdfunding.common.util.AppDateUtils;
import com._520.crowdfunding.common.util.Const;
import com._520.crowdfunding.common.util.MD5Util;
import com._520.crowdfunding.domain.TAdmin;
import com._520.crowdfunding.domain.TAdminExample;
import com._520.crowdfunding.domain.TAdminKey;
import com._520.crowdfunding.mapper.TAdminMapper;
import com._520.crowdfunding.service.TAdminService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class TAdminServiceImpl implements TAdminService {
    @Autowired
    private TAdminMapper adminMapper;

    // 登录业务
    @Override
    public TAdmin checkLogin(Map<String, Object> map) {
        // 拿到map中的数据
        String username = (String) map.get("username");
        String password = (String) map.get("password");

        // 操作mapper
        // 根据条件查找
        TAdminExample example = new TAdminExample();
        // 设置查询条件
        example.createCriteria().andLoginacctEqualTo(username);
        // 得到符合条件的用户
        List<TAdmin> admins = adminMapper.selectByExample(example);
        // 没有找到指定的用户
        if (admins == null || admins.size() == 0){
            throw new LoginException(Const.LOGIN_LOGINACCT_ERROR);
        }

        // 找到用户
        TAdmin admin = admins.get(0);

        // 判断密码是否正确，将用户输入的密码加密后和数据库中的对比
        if (!admin.getUserpswd().equals(MD5Util.digest(password))){
            throw new LoginException(Const.LOGIN_USERPSWD_ERROR);
        }
        return admin;
    }

    @Override
    public PageInfo<TAdmin> listAdminPage(Map<String, Object> map) {
        String condition = (String) map.get("condition");
        TAdminExample example = new TAdminExample();
        // 如果有条件就进行模糊查询
        if (!"".equals(condition)){
            example.createCriteria().andLoginacctLike("%" + condition + "%");
            TAdminExample example1 = new TAdminExample();
            TAdminExample example2 = new TAdminExample();
            example.or(example1.createCriteria().andEmailLike("%" + condition + "%"));
            example.or(example2.createCriteria().andUsernameLike("%" + condition + "%"));
        }
        List<TAdmin> tAdmins = adminMapper.selectByExample(example);
        // 将查询的所有用户信息存到PageInfo中
        return new PageInfo<>(tAdmins, 5);
    }

    @Override
    public void saveTAdmin(TAdmin admin) {
        // 设置默认密码
        admin.setUserpswd(MD5Util.digest(Const.DEFAULT_USERPSWD));
        // 设置时间为当前时间
        admin.setCreatetime(AppDateUtils.getFormatTime());
        // 不完全添加
        adminMapper.insertSelective(admin);
    }

    @Override
    public TAdmin getTAdminById(Integer id) {
        TAdminKey key = new TAdminKey();
        key.setId(id);
        return adminMapper.selectByPrimaryKey(key);
    }

    @Override
    public void updateTAdmin(TAdmin admin) {
        adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public void deleteById(Integer id) {
        TAdminKey key = new TAdminKey();
        key.setId(id);
        adminMapper.deleteByPrimaryKey(key);
    }

    @Override
    public void deleteBatch(List<Integer> idList) {
        TAdminExample example = new TAdminExample();
        example.createCriteria().andIdIn(idList);
        adminMapper.deleteByExample(example);
    }
}
