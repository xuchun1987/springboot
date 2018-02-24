package boot.security;

import boot.filter.JWTAuthenticationFilter;
import boot.filter.JWTLoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by xuchun on 2017/9/25.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 配置登录验证userDetailService处理类
     * @return
     */
    @Bean
    protected UserDetailsService userDetailsService(){
        return new CwbbUserDetailsService();
    }


    /**
     * 配置登录成功后相关操作处理类
     * @return
     */
    @Bean
    public CwbbLoginSuccessHandler loginSuccessHandler(){
        return new CwbbLoginSuccessHandler();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //springSecurity4.0后，默认开启了CSRD拦截
        http.
                csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll() //无需登录认证权限
                .anyRequest().authenticated() //其他所有资源都需要认证，登陆后访问
                .and()
                .formLogin()
                .loginPage("/login") //配置登录页面路径,如果访问未授权页面会重定向到此路径
                .failureUrl("/login?error")//登录失败后页面路径
                .permitAll()
                .successHandler(loginSuccessHandler())////登录成功后可使用loginSuccessHandler()存储用户信息，可选。
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // And filter other requests to check the presence of JWT in header
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);


        //.logout().logoutRequestMatcher(new AntPathRequestMatcher(“/logout”))
         //.rememberMe()//登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
         //.tokenValiditySeconds(1209600);
               //.rememberMe()
                //设置cookie有效期
                //.tokenValiditySeconds(60 * 60 * 24 * 7)
                //设置cookie的私钥
                //.key("")
    }

    /**
     * 配置不拦截的资源文件
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/static/**")
                .antMatchers("/frame/**");
    }

    /**
     * 设置密码策略为BCrypt
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }
/*    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }*/
}
