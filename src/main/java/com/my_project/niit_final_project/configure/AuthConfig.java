package com.my_project.niit_final_project.configure;


import com.my_project.niit_final_project.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;




@Configuration
@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter {
  /*  @Autowired
    CustomUserDetailsService customerDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
                .and().authorizeRequests().antMatchers("/","/client/**","/client/cart/**","/css/*", "/js/*").permitAll()
                .and().formLogin().loginPage("/login").and().logout().logoutUrl("/logout")
                .and().exceptionHandling().accessDeniedPage("/access_deny");
    }

    *//*@Bean
    protected UserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("12345678").roles("ADMIN").build();
        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("123456789").roles("USER").build();
        return new InMemoryUserDetailsManager(admin, user);
    }*//*
  @Bean
  @Override
  public UserDetailsService userDetailsService( ) {
      UserDetails user =
              User.builder()
                      .username("user")
                      .password(passwordEncoder.encode("12345678"))
                      .roles("ADMIN")
                      .build();

      return new InMemoryUserDetailsManager(user);
  }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().withUser("user").password(passwordEncoder.encode("12345678")).roles("ADMIN");
        auth.userDetailsService(customerDetailsService) // Cung cáp userservice cho spring security
                .passwordEncoder(passwordEncoder()); // cung cấp password encoder
    }*/

    @Autowired
    private CustomUserDetailsService customerDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
                .and().authorizeRequests().antMatchers("/","/client/**","/client/cart/**","/css/*", "/js/*").permitAll()
                .and().formLogin().loginPage("/login").and().logout().logoutUrl("/logout")
                .and().exceptionHandling().accessDeniedPage("/access_deny");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password(passwordEncoder.encode("12345678")).roles("ADMIN");
        auth.userDetailsService(this.customerDetailsService).passwordEncoder(passwordEncoder());
    }
}
