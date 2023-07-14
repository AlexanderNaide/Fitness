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
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
@Slf4j
@EnableWebSecurity
public class SecurityConfig {

    private final AuthService authService;

    @Bean
    public SecurityFilterChain filterChain(MainFilter filter, HttpSecurity http) throws Exception{

        // Старая версия security
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

        // Для спринг 3.0.2
//        return http.csrf().disable().
//                cors().disable().
//                authorizeHttpRequests().
//                requestMatchers("/api/v1/super/**").hasAuthority("super").
//                requestMatchers("/auth/**").permitAll().
//                and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
//                and().exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)).
//                and().addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class).
//                build();

        // Для спринг 3.1.0
         return http
                 .csrf(AbstractHttpConfigurer::disable)
//                 .cors(AbstractHttpConfigurer::disable)
                 .cors(withDefaults())
                 .authorizeHttpRequests(request ->
                         request
                                 .requestMatchers("/api/v1/super/**").hasAuthority("super")
                                 .requestMatchers("/api/v1/admin/**").hasAuthority("admin")
                                 .requestMatchers("/api/v1/trainer/**").hasAuthority("trainer")
                                 .requestMatchers("/api/v1/user/**").hasAuthority("user")
                                 .requestMatchers("/api/v1/workout/**").authenticated()
                                 .requestMatchers("/auth/**").permitAll()
                 )
                 .sessionManagement(sm ->
                         sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                 .exceptionHandling(eh ->
                         eh.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                 .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                 .build();
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
////        configuration.setAllowedOrigins(List.of("https://example.com"));
//        configuration.setAllowedOrigins(List.of("http://www.localhost:3880"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://www.localhost:3880")
//                        .allowedOrigins("http://localhost:3880")
                        .allowedMethods("*");
            }
        };
    }



//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer(){
//        return webSecurity -> webSecurity.ignoring().requestMatchers("/auth/**", "/api/v1/auth/**");
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
