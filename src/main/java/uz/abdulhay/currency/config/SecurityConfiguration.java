package uz.abdulhay.currency.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import uz.abdulhay.currency.security.JwtAuthEntryPoint;
import uz.abdulhay.currency.security.JwtConfigurer;
import uz.abdulhay.currency.security.JwtTokenProvider;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthEntryPoint jwtAuthEntryPoint;

    public SecurityConfiguration(@Qualifier("customUserDetails") @Lazy UserDetailsService userDetailsService, JwtTokenProvider jwtTokenProvider, JwtAuthEntryPoint jwtAuthEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthEntryPoint)
                .and()
                .headers().frameOptions().and().and()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/service/**").hasAnyRole("USER")
//                .antMatchers("/api/service/**").permitAll()
                .antMatchers("/swagger-ui.html", "/webjars/**", "/swagger-resources", "/swagger-resources/**", "/v2/**", "/csrf").permitAll()
//                .anyRequest().authenticated()
                .and()
                .formLogin()
                .disable()
                .apply(new JwtConfigurer(jwtTokenProvider));

    }


}
