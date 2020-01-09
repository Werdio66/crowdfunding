package com._520.crowdfunding;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

public class TestMBG {
    public static void main(String[] args)
            throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        //MBG执行过程中的警告信息
        List<String> warnings = new ArrayList<String>();
        //生成代码重复时，是否覆盖源代码
        boolean override = false;
        InputStream in = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(in);

        DefaultShellCallback callback = new DefaultShellCallback(override);
        //创建MBG
        MyBatisGenerator mbg = new MyBatisGenerator(config, callback, warnings);
        mbg.generate(null);
        //输出警告信息
        for (String warn : warnings) {
            System.out.println(warn);
        }
    }
}

