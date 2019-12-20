//package com.lukasz.api.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.*;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//@ComponentScan(basePackages = {"com.lukasz.api"})
//@EnableWebSecurity
//public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
//    //final UserDetailsService userDetailsService;
//    final MyUserDetailsService myUserDetailsService;
//
//    private static final Logger log = LogManager.getLogger();
//
//    @Autowired
//    public AppSecurityConfig( MyUserDetailsService myUserDetailsService) {
//       // this.userDetailsService = userDetailsService;
//        this.myUserDetailsService = myUserDetailsService;
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authProvider() {
//
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(myUserDetailsService);
//        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
//        //authProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//        return authProvider;
//    }
//
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.csrf().disable()
////                .authorizeRequests().antMatchers("/login").permitAll()
////                .anyRequest().authenticated()
////                .and()
////                .formLogin()
////                .loginPage("/login").permitAll()
////                .and()
////                .logout().invalidateHttpSession(true)
////                .clearAuthentication(true)
////                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
////                .logoutSuccessUrl("/clients").permitAll();
////
////
////
//////        http.csrf().disable().authorizeRequests()
//////                //.antMatchers("/").permitAll()
//////                .antMatchers(HttpMethod.POST, "/newclient").permitAll()
//////                .antMatchers(HttpMethod.POST, "/login").permitAll()
//////                .antMatchers(HttpMethod.POST,"/newclient/*").permitAll()
//////                .antMatchers(HttpMethod.POST,"/admin/**").permitAll()
//////                .antMatchers(HttpMethod.GET,"/master/*").permitAll()
//////                .anyRequest().authenticated()
//////                .and()
//////                .formLogin().loginPage("/login").permitAll();
////
////    }
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.authenticationProvider(authProvider());
////    }
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//}
