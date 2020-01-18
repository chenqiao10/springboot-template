package com.example.demo.config.Sercurty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.service.UserDetailService;
@Configuration
@EnableWebSecurity
@Component
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
   @Autowired
   private UserDetailService userDetailService;
 
   @Override
   protected void configure(HttpSecurity http) throws Exception{
       http.authorizeRequests()
               // .antMatchers("/index/show").hasAnyRole("ROLE_ADMIN","ROLE_USER")//个人首页只允许拥有ADMIN,BUYER,SELLER角色的用户访问
                
                //在此后面可以根据自己的项目需要进行页面拦截的添加                             
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/index/test").permitAll()//这里程序默认路径就是登陆页面，允许所有人进行登陆
               .loginProcessingUrl("/j_spring_security_check")//登陆提交的处理url
                .usernameParameter("j_username")//登陆用户名参数
                .passwordParameter("j_password")//登陆密码参数
                .failureUrl("/index/test?error=true")//登陆失败进行转发，这里回到登陆页面，参数error可以告知登陆状态
                .defaultSuccessUrl("/index/show")//登陆成功的url，这里去到个人首页
                .and().logout().logoutUrl("/j_spring_security_logout").permitAll().logoutSuccessUrl("/index/test?logout=true")//按顺序，第一个是登出的url，security会拦截这个url进行处理，所以登出不需要我们实现，第二个是登出url，logout告知登陆状态
                .and()
                .rememberMe()
                .tokenValiditySeconds(604800)//记住我功能，cookies有限期是一周
                .and()
                .csrf().disable();       
    }
 
    @Override
    public void configure(WebSecurity web) throws Exception{
        super.configure(web);
    }
 
    @Override
    public  void configure(AuthenticationManagerBuilder auth) throws Exception{
      auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }
 
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
} 