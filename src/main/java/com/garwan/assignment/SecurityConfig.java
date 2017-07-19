package com.garwan.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
	@Autowired private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		int iterations = 500000;
		int hashLength = 160;

		// In real app pepper should be saved outside git and in more secure way ..
		String pepper = "df798\\(ojd8'><}^@$98e*hKa?51";
		
	    return new Pbkdf2PasswordEncoder(pepper, iterations, hashLength);
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
        	.passwordEncoder(passwordEncoder());
    }
	
	protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
	            .antMatchers("/css/**", "/login", "/h2-console/**").permitAll()
	            .anyRequest().authenticated()
	            .and()
	        .formLogin()
	            .loginPage("/login").permitAll()
	            .defaultSuccessUrl("/")
	            .and()
	        .logout()
	            .permitAll()
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	            .logoutSuccessUrl("/login?logout")
	            .and()
	        .csrf()
	        	.disable()
	        .headers()
	        	.frameOptions()
	        	.disable();
    }
}
