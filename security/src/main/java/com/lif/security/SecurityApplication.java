package com.lif.security;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *  学习security安全配置
 * @author lifan 2020年1月30日
 */
@EnableWebSecurity
@SpringBootApplication
public class SecurityApplication extends WebSecurityConfigurerAdapter {

    /**
     * 覆盖security配置，使用内存用户签名
     * @param auth
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 密码编辑器
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pwd = passwordEncoder.encode("abc");
        // 使用内存存储
        auth.inMemoryAuthentication()
                // 设置密码编辑器
                .passwordEncoder(passwordEncoder)
                // 创建用户admin，密码abc，并赋予USER和ADMIN角色权限
                .withUser("admin")
                .password(passwordEncoder.encode("abc"))
                .roles("USER", "ADMIN")
                // 连接方式and
                .and()
                .withUser("user1")
                .password(passwordEncoder.encode("123456"))
                .roles("USER");
    }

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

}
