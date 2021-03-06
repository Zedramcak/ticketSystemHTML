package eu.adamzrc.ticketSystemHTML.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    public WebSecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, DataSource dataSource) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/login", "/js/**", "/css/**", "/images/**").permitAll()
                    .antMatchers("/user/edit/*", "/user/add", "/user/delete/*").hasAnyAuthority("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                .csrf().disable()
                .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error=true")
                    .defaultSuccessUrl("/myProfile")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .and()
                .logout().logoutSuccessUrl("/")
                .permitAll()
                .and()
                .httpBasic();
    }
}
