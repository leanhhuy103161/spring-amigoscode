package com.amigoscode.basicspring.security.config;

//import com.amigoscode.basicspring.appuser.AppUserService;
import com.amigoscode.basicspring.security.ApplicationUserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.amigoscode.basicspring.security.ApplicationUserRole.ADMIN;
import static com.amigoscode.basicspring.security.ApplicationUserRole.STUDENT;

@Configuration
@AllArgsConstructor // notice
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    private final AppUserService appUserService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v*/registration/**", "/", "/css/*", "index", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .anyRequest()
                .authenticated().and()
                .httpBasic();
//                .formLogin();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails jossUser = User.builder()
                .username("joss")
                .password(passwordEncoder.encode("josstice"))
                .roles(STUDENT.name()) // ROLE_STUDENT
                .build();

        UserDetails alexUser = User.builder()
                .username("alex")
                .password(passwordEncoder.encode("alexa"))
                .roles(ADMIN.name()) // ROLE_ADMIN
                .build();

        return new InMemoryUserDetailsManager(
                jossUser,
                alexUser
        );
    }

    //    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider =
//                new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(bCryptPasswordEncoder);
//        provider.setUserDetailsService(appUserService);
//
//        return  provider;
//    }
}