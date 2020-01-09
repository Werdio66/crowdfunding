package com._520.crowdfunding.service.impl;

import com._520.crowdfunding.common.exception.LoginException;
import com._520.crowdfunding.common.util.Const;
import com._520.crowdfunding.common.util.MD5Util;
import com._520.crowdfunding.domain.TAdmin;
import com._520.crowdfunding.domain.TAdminExample;
import com._520.crowdfunding.mapper.TAdminMapper;
import com._520.crowdfunding.service.TAdminService;
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
}
