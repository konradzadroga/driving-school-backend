package com.konradzadroga.drivingschool.config.security;

import com.konradzadroga.drivingschool.config.jwt.JwtAuthEntryPoint;
import com.konradzadroga.drivingschool.config.jwt.JwtAuthTokenFilter;
import com.konradzadroga.drivingschool.config.jwt.JwtProvider;
import com.konradzadroga.drivingschool.config.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private MyUserDetailsService userDetailsService;
    private JwtAuthEntryPoint jwtAuthEntryPoint;
    private JwtProvider jwtProvider;
    private final String ANGULAR_API = "http://localhost:4200";

    public WebSecurityConfig(MyUserDetailsService userDetailsService, JwtAuthEntryPoint jwtAuthEntryPoint, JwtProvider jwtProvider) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoderBean());
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthTokenFilter authTokenFilterBean() {
        return new JwtAuthTokenFilter(jwtProvider, userDetailsService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/signup").permitAll()
                .antMatchers("/signin").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(authTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowedOrigins(Arrays.asList(ANGULAR_API));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "UPDATE","DELETE", "PATCH"));
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
