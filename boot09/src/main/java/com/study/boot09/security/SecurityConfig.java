package com.study.boot09.security;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Log
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;
    @Autowired
    com.study.boot09.security.Boot09UserService boot09UserService;

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//        log.info("build Auth global......");
////        auth.userDetailsService(boot08UserService).passwordEncoder(passwordEncoder());
////        String query1 = "SELECT uid username, upw password, true enabled FROM tbl_members WHERE uid= ?";
//////
////        String query2 = "SELECT member uid, role_name role FROM tbl_member_roles  WHERE member = ?";
////        auth.jdbcAuthentication()
////                .dataSource(dataSource)
////                //sersByUsernameQuery(query1)
////                .rolePrefix("ROLE_")
////              //.authoritiesByUsernameQuery(query2);
//        auth.userDetailsService(boot08UserService).passwordEncoder(passwordEncoder());
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("security config.................");
        http.authorizeRequests()
                .antMatchers("/boards/list").permitAll()
                .antMatchers("/boards/register")
                .hasAnyRole("BASIC","MANAGER","ADMIN");
        http.formLogin().loginPage("/login");
        http.exceptionHandling().accessDeniedPage("/accessDenied");
        http.logout().logoutUrl("/logout").invalidateHttpSession(true);

        http.rememberMe()
                .key("boot08")
                .userDetailsService(boot09UserService)
                .tokenRepository(getJDBCRepository())
                .tokenValiditySeconds(60*60*24);



    }
    private PersistentTokenRepository getJDBCRepository() {

        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        }
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        log.info("build Auth global ......");
        auth.userDetailsService(boot09UserService).passwordEncoder(passwordEncoder());

    }

}
