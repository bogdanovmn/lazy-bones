package ${pkgProjectPrefix}.web.infrastructure.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private final ProjectUserDetailsService userDetailsService;

	@Autowired
	public WebSecurityConfig(ProjectUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(new Md5PasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/registration").anonymous()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/webjars/**").permitAll()
				.antMatchers("/admin/**").hasAuthority("Admin")
				.anyRequest().authenticated()

		.and().formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/index-page", true)
			.permitAll()

		.and().logout()
			.logoutRequestMatcher(
				new AntPathRequestMatcher("/logout")
			)
			.logoutSuccessUrl("/login")
			.permitAll()

		.and().csrf()
			.disable();
	}
}
