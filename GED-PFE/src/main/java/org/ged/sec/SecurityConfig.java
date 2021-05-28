package org.ged.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	// TODO Auto-generated method stub
	//super.configure(auth);
	
	auth.userDetailsService(userDetailsService)
	.passwordEncoder(bCryptPasswordEncoder);
}

//@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		
		http.formLogin().loginPage("/login");
	http.authorizeRequests().antMatchers("/login/**","/registerForm/**","/register/**").permitAll();
	http.authorizeRequests().anyRequest().authenticated();
	
	http.exceptionHandling().accessDeniedPage("/403");
	http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
	}
}
