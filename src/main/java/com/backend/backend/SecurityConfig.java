package com.backend.backend;


import com.backend.backend.jwt.JwtAuthenticationFilter;
import com.backend.backend.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private  final JwtTokenProvider jwtTokenProvider;
    public SecurityConfig(JwtTokenProvider jwtTokenProvider)
    {
        this.jwtTokenProvider=jwtTokenProvider;
    }
    @Bean
    public BCryptPasswordEncoder encoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                //crsf(cross site request forgery):사이트간 위조 요청 , 인증된 사용자의 토큰을 탈취해 위조된 요청을 보냈을 경우 파악해 방지하는 것
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //JWT를 사용하기 때문에 세션도 사용하지 않는다.
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                //HTTP Basic Authentication과 Form Based Authentication을 사용 x
                .authorizeRequests()
                .antMatchers("/api/user").permitAll()
                // 해당 요청에 관해 모두 접근 가능하게 한다.
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
                //UsernamePasswordAuthenticationFilter에 가기 전에 직접 만든 JwtAuthentication을 실행하겠다.

        return http.build();
        //서버가 실행되고 모든 HTTP 요청이 이 SecurityConfig로 들어온다.
        //여기서 ("/api/user")라는 요청이 있으면 모두 접근 가능하게 해준다.
        // 즉 , controller의 login 요청으로 가게 되는 것이다.
        //그리고 서비스 단으로 넘어가면 본격적인 인증 과정을 거친다.
        /*여기서 Authentication 객체를 만들어주는데 이 객체가 UsernamePasswordAuthenticationFilter와 관계 있기때문에
        먼저 JwtAuthenticationFilter(jwtTokenProvider)가 실행될 것이다.*/
    }
}
