package com.marstafk.IHMtrackerTool.security;

import com.marstafk.IHMtrackerTool.service.UserService;
import com.marstafk.IHMtrackerTool.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

//    @Bean
//    public UserService userDetailsService() {
//        return new UserServiceImpl();
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
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
                .antMatchers("/signup_form**",
                                        "/process_register",
                                        "/js/**",
                                        "/css/**",
                                        "/img/**",
                                        "/webjars/**",
                                        "/chartData",
                                        "/chartGradeData",
                                        "/index").permitAll()
                .antMatchers("/addClassroom",
                        "/addFamily",
                        "/addJogathon",
                        "/addStudentTest",
                        "/addStudent",
                        "/addTeacher",
                        "/addPerson",
                        "/addPersonType",
                        "/addPledgeType",
                        "/addPledge",
                        "/addSponsor",
                        "/editClassroom",
                        "/editFamily",
                        "/editJogathon",
                        "/saveStudent",
                        "/saveTeacher",
                        "/editPerson",
                        "/editPersonType",
                        "/editPledgeType",
                        "/editSponsor",
                        "/confirmDeactivateClassroom",
                        "/confirmDeactivateFamily",
                        "/confirmDeactivateJogathon",
                        "/confirmDeactivateStudent",
                        "/confirmDeactivateTeacher",
                        "/confirmDeactivatePersonType",
                        "/confirmDeactivatePledgeType",
                        "/deactivatePledge").hasAnyAuthority("ADMIN")
                //.antMatchers("/edit/**").hasAnyAuthority("ADMIN")
                //.antMatchers("/delete/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll().defaultSuccessUrl("/", true).loginPage("/login").usernameParameter("email").passwordParameter("password")
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403")
        ;
    }
}
