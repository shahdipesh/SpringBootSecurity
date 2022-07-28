package com.example.demo.Security;

import com.example.demo.Filters.JwtFilter;
import com.example.demo.Service.UserPrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserPrincipalDetailsService userPrincipalDetailsService;

    @Autowired
    DataSource dataSource;

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    OauthSuccessHandler oauthSuccessHandler;






    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /********* Uses jdbc authentication provider to authenticate users********/
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select username,password,'true' as enabled from users where username = ?")
//                .authoritiesByUsernameQuery("select username,roles from users where username = ?");

        /********* Uses userDetailsService to authenticate users********/

            auth.userDetailsService(userPrincipalDetailsService);



        /********* Uses CustomAuthentication to authenticate users********/
     //       auth.authenticationProvider(daoAuthenticationProvider());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/oauth/login","/auth/login/**").permitAll()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/admin/index").hasRole("ADMIN")
                .antMatchers("/management/index").hasRole("MANAGER")
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .failureHandler((request, response, exception) -> {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                })
                .successHandler(oauthSuccessHandler);


    }


    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userPrincipalDetailsService);

        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

