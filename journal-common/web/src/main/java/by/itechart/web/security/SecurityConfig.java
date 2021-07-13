package by.itechart.web.security;

import by.itechart.web.security.token.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

        private final UserDetailsService userDetails;

        private final BasicAuthenticationEntryPoint authEntryPoint;

        private final PasswordEncoder encoder;

        private final JwtFilter jwtFilter;


        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) {
            auth.authenticationProvider(daoAuthenticationProvider());
        }

        @Bean
        public DaoAuthenticationProvider daoAuthenticationProvider() {

            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setPasswordEncoder(encoder);
            provider.setUserDetailsService(userDetails);

            return provider;
        }


        @Override
        public void configure(WebSecurity web) {
            web.ignoring().antMatchers("/");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
                http
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/admin/**").hasAuthority("SUPER_ADMIN")
                    .antMatchers("/users/**").hasAuthority("ADMIN")
                    .antMatchers("/classes/**").authenticated()
                    .antMatchers("/details/**").authenticated()
                    .and()
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                    .httpBasic()
                    .authenticationEntryPoint(authEntryPoint);
        }
}
