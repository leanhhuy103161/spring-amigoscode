package com.amigoscode.basicspring.security.config;

//import com.amigoscode.basicspring.appuser.AppUserService;
import com.amigoscode.basicspring.security.ApplicationUserPermission;
import com.amigoscode.basicspring.security.ApplicationUserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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

import static com.amigoscode.basicspring.security.ApplicationUserPermission.*;
import static com.amigoscode.basicspring.security.ApplicationUserRole.*;

@Configuration
@AllArgsConstructor // notice
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    private final AppUserService appUserService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v*/registration/**", "/", "/css/*", "index", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
//                .antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())
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
//                .roles(STUDENT.name()) // ROLE_STUDENT
                .authorities(STUDENT.getGrantedAuthorities())
                .build();

        UserDetails alexUser = User.builder()
                .username("alex")
                .password(passwordEncoder.encode("alexa"))
//                .roles(ADMIN.name()) // ROLE_ADMIN
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails marianUser = User.builder()
                .username("marian")
                .password(passwordEncoder.encode("marian"))
//                .roles(ADMINTRAINEE.name()) // ROLE_ADMINTRAINEE
                .authorities(ADMINTRAINEE.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                jossUser,
                alexUser,
                marianUser
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