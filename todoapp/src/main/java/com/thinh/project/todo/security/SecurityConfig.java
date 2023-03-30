package com.thinh.project.todo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;



@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager (DataSource dataSource){

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        //define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select username, password, enabled from user where username=?");
        //query to retrieve role
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select username, role from user where username=?");
        return jdbcUserDetailsManager;
    }


    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        //basic authentication using custom log-in form
        http.anonymous().and().csrf().disable()
                .authorizeHttpRequests()
                /*.requestMatchers(HttpMethod.GET,"/api/**").hasAuthority("USER")
                .requestMatchers(HttpMethod.POST,"/api/**").hasAuthority("USER")
                .requestMatchers(HttpMethod.PUT,"/api/**").hasAuthority("USER")
                .requestMatchers(HttpMethod.DELETE,"/api/**").hasAuthority("USER")*/
                .requestMatchers("/api/login","/api/register","/api/register/save").permitAll().anyRequest().authenticated()
                .and()
                .formLogin(form->form
                        .loginPage("/api/login")
                        .defaultSuccessUrl("/api/tasks")
                        .failureUrl("/api/login?error=true")
                        .permitAll()
                ).logout(
                        logout->logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                );
        return http.build();
    }
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
