package com.web_dong_ho.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()

                .antMatchers( "/gio-hang/**","/thanh-toan","/hoa-don/**", "/api/**").hasRole("USER")
                 .antMatchers("/admin/**").hasRole("ADMIN")

                .and()
                .formLogin()
                .loginPage("/login")
                 .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=error")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403")

                .and()
                .logout() // Cho phép logout
                .logoutUrl("/logout")
                .permitAll();;


    }

}
