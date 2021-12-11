package qlpk.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import qlpk.entity.enums.Role;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public AuthenticationSuccessHandler handler(){
        return new CustomAuthenticationSuccessHandler();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImp();
    }
    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                // cho phép dùng các resource
                .antMatchers("/resources/**", "/img/**", "/css/**", "/js/**").permitAll()
                .antMatchers("/qlns/**").hasAuthority(Role.ADMIN.getType())
                .antMatchers("/bacsi/**").hasAuthority(Role.BACSY.getType())
                .antMatchers("/yta/**").hasAuthority(Role.YTA.getType())
                .antMatchers(HttpMethod.POST, "/**").permitAll()
                .antMatchers(HttpMethod.GET, "/*").permitAll()
                .anyRequest().authenticated()

                //cho phép login
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticateTheUser")
                .successHandler(handler())
                //.defaultSuccessUrl("/loginSuccess")
                .permitAll()
                //còn lại phẳi authen mới được vào
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/403");

    }

}
