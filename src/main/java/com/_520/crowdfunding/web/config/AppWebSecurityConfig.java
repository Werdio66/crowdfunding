package com._520.crowdfunding.web.config;

import com._520.crowdfunding.web.component.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class AppWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

         // 授权首页和静态资源
        http.authorizeRequests()
                .antMatchers("/static/**", "/welcome.jsp", "/toLogin").permitAll() // 对这些资源放行
                .anyRequest().authenticated();// 其他的请求必须认证

        // 启用记住我功能
        http.rememberMe();


        // 设置表单用户名和密码名称，登录成功后的跳转页面
        http.formLogin().loginPage("/toLogin")  // 自定义登录页面
                .loginProcessingUrl("/login")   // 登录页面表单提交路径
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/main");    // 登录成功页面

        // 没有访问权限跳转
        http.exceptionHandling().accessDeniedPage("/unauth.html");
        // 禁用csrf
        http.csrf().disable();
        // 默认注销
//        http.logout();
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/index");


        // 异常处理
        http.exceptionHandling().accessDeniedHandler((request, response, e) -> {
            response.getWriter().print(Integer.valueOf(403));
        });

    }
}
