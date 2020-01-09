package com._520.crowdfunding.test;

import com._520.crowdfunding.domain.TAdmin;
import com._520.crowdfunding.domain.TAdminKey;
import com._520.crowdfunding.mapper.TAdminMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = "classpath:applicationContext.xml")
public class LogTest {
    @Autowired
    private TAdminMapper mapper;

    @Test
    void test(){
        TAdminKey key = new TAdminKey();
        key.setId(1);
        TAdmin admin = mapper.selectByPrimaryKey(key);
        System.out.println(admin);
    }

}
