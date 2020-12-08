package com.rcl.rclbackend.Configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.rcl.rclbackend.Model.RoleNames;
import com.rcl.rclbackend.ServiceImpl.MyUserDetailsService;
import com.rcl.rclbackend.filters.JwtRequestFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests()
		.antMatchers("/admin/**").hasAuthority(RoleNames.ADMIN.name())
		.antMatchers("/register/**").permitAll()
		.antMatchers("/download/**").permitAll()
		.antMatchers("/authenticate").permitAll()
		.antMatchers("/api/rcl/download/component/public/all").permitAll()
		.antMatchers("/api/rcl/static/component/**").permitAll()
//		.antMatchers("/user/**").permitAll()
		.anyRequest().authenticated()
//		.and()
//		.httpBasic()
//		.and()
//		.formLogin()
		.and()
		.exceptionHandling()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.csrf().disable();
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//		.antMatchers("/admin/**").hasRole(RoleNames.ADMIN.name())
//		.antMatchers("/").hasAnyRole(RoleNames.ADMIN.name(), RoleNames.USER.name())
//		.antMatchers("/").permitAll()
//		.and()
//		.formLogin();
//		http
//			.authorizeRequests()
//			.antMatchers("/console/**").permitAll()
//			.antMatchers("/", "/addNewUser").authenticated()
//			.antMatchers("/getAllUser/**", "/removeAll/**").hasAuthority(RoleNames.ADMIN.name())
//			.antMatchers("/removeAll/**", "/addNewUser/**", "/save/**", "/register/**", "/delete/**", "/page/**", "/next/**", "/search/**").hasAuthority(RoleNames.ADMIN.name())
//			.anyRequest().permitAll()
//			.and()
//				.formLogin().loginPage("/login")
//				.defaultSuccessUrl("/")
//				.usernameParameter("username")
//				.passwordParameter("password")
//			.and()
//				.logout().logoutSuccessUrl("/login")
//			.and()
//				.exceptionHandling().accessDeniedPage("/403")
//			.and()
//				.csrf().disable();
		
//		http.sessionManagement()
//				.maximumSessions(1)
//				.maxSessionsPreventsLogin(true)
//				.expiredUrl("/login?error=You are already logged in from somewhere");
	}

	@Bean
	public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
		return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

}
