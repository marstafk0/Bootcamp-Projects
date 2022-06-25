package com.marstafk.IHMtrackerTool.security;

import com.marstafk.IHMtrackerTool.serviceImpl.UserDetailsServiceImpl;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/register**",
                                        "/process_register",
                                        "/js/**",
                                        "/css/**",
                                        "/img/**",
                                        "/webjars/**",
                                        "/chartData",
                                        "/chartGradeData",
                                        "/index").permitAll()
                .antMatchers("/new").hasAnyAuthority("ADMIN")
                .antMatchers("/edit/**").hasAnyAuthority("ADMIN")
                .antMatchers("/delete/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll().defaultSuccessUrl("/", true)
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403")
        ;
    }
}
