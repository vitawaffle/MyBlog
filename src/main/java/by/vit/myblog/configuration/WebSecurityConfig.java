package by.vit.myblog.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Web security configuration class.
 *
 * @author Vitaly Lobatsevich (vitaly.lobatsevich@gmail.com)
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * This bean sets BCryptPasswordEncoder as default password encoder.
     *
     * @return BCryptPasswordEncoder object.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests(registry -> {
                    registry
                            .antMatchers("/", "/css/**", "/js/**", "/img/**", "/signIn").permitAll()
                            .antMatchers(HttpMethod.GET, "/posts").permitAll()
                            .anyRequest().authenticated();
                })
                .formLogin(registry -> {
                    registry
                            .loginPage("/login")
                            .defaultSuccessUrl("/")
                            .permitAll();
                })
                .logout(registry -> {
                    registry
                            .logoutSuccessUrl("/")
                            .permitAll();
                })
                .csrf().disable();
    }

}
