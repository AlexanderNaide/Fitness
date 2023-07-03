package org.satal.backservice.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.satal.backservice.api.MainFilter;
import org.satal.backservice.services.AuthService;
import org.satal.backservice.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    private final AuthService authService;

    @Bean
    public SecurityFilterChain filterChain(MainFilter filter, HttpSecurity http) throws Exception{
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
/*        return http.
                securityMatcher("/api/v1/fit/**").
//                 authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated()).
        authorizeHttpRequests(authorize -> authorize.anyRequest().hasRole("super")).
//                requestMatchers("/api/v1/cart/**").authenticated().
        httpBasic(Customizer.withDefaults()).
//                anyRequest().permitAll().
//                and().formLogin().
//                and().addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class).
        build();*/

         return http.
                 securityMatcher("/super/**").
//                 authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated()).
                 authorizeHttpRequests(authorize -> authorize.anyRequest().hasRole("super")).
//                httpBasic(Customizer.withDefaults()).

                 addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class).
                build();

    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return webSecurity -> webSecurity.ignoring().requestMatchers("/auth/**", "/api/v1/auth/**");
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user = User.builder()
//                .username("user")
//                .password("pass")
//                .authorities("ADMIN", "MANAGER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user = User.builder()
//                .username("user")
//                .password("pass")
//                .authorities("ADMIN", "MANAGER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider... providers){
        return new ProviderManager(providers);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        return new StandardAuthenticationProvider();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        DaoAuthenticationProvider authenticationProvider = new MainDaoProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(authService);
        return authenticationProvider;
    }
}
