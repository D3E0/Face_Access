//package config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import java.util.logging.Logger;
//
///**
// * @author ACM-PC
// */
////@Configuration
////@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    //    private DataSource dataSource;
////
////    @Autowired
////    public void setDataSource(DataSource dataSource) {
////        this.dataSource = dataSource;
////    }
//    private Logger logger = Logger.getLogger("logger");
//
//    private UserDetailsService userDetailsService;
//
////    @Autowired
////    @Qualifier(value = "signInServiceImp")
//    public void setUserDetailsService(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//        logger.info("-----UserDetailsService Autowired Success-----");
//    }
//
////    @Autowired
//    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
//        logger.info("-----AuthenticationManagerBuilder Builder Success-----");
////        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
////                .withUser("jjkk").password(new BCryptPasswordEncoder().encode("jjkk")).roles("USER")
////                .and().withUser("admin").password(new BCryptPasswordEncoder().encode("root")).roles("ADMIN");
////        auth.jdbcAuthentication().dataSource(dataSource).
////                usersByUsernameQuery("select username,password,true from tb_user where username = ?").
////                authoritiesByUsernameQuery("select username,'ROLE_USER' from tb_user where username = ?")
////                .passwordEncoder(new BCryptPasswordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//                .antMatchers("/admin").hasRole("ADMIN")
//                .antMatchers("/welcome").hasRole("USER")
//                .anyRequest().permitAll()
//                .and().formLogin().loginPage("/signIn")
//                .and().logout()
//                .and().rememberMe().tokenValiditySeconds(300).key("SecuredKey")
//                .and().exceptionHandling().accessDeniedPage("/error")
//                .and().csrf().disable();
//    }
//}
