package com.example.springsecurity.config;

import com.example.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");
        http.formLogin().loginPage("/toLogin")
                //默认是 username password 可以改变前端参数名称
                .usernameParameter("user").passwordParameter("pwd")
                .loginProcessingUrl("/login");

        //防止网站攻击 get,post 不允许用get方式注销，可以是post；
        // 可以手动改成post方式注销，也可以关闭功能
        http.csrf().disable();
        http.logout().logoutSuccessUrl("/");
        //可以改变前端参数名称
        http.rememberMe().rememberMeParameter("remember");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(encoder)
                .withUser("admin").password(encoder.encode("123456")).roles("vip1", "vip2", "vip3").and()
                .withUser("root").password(encoder.encode("123456")).roles("vip2", "vip3");
//        User user = userRepository.findUserById(1);
//        System.out.println(user);
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .withDefaultSchema().withUser(user.getName())
//                .password(user.getPassword()).roles("vip1", "vip2");
    }
}
