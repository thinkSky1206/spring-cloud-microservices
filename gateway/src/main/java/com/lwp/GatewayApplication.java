package com.lwp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//http://blog.monkey.codes/how-to-use-jwt-and-oauth-with-spring-boot/
//http://www.baeldung.com/spring-security-oauth-jwt
//https://piotrminkowski.wordpress.com/2017/03/30/advanced-microservices-security-with-oauth2/
//http://callistaenterprise.se/blogg/teknik/2015/04/27/building-microservices-part-3-secure-APIs-with-OAuth/
//http://stytex.de/blog/2016/02/01/spring-cloud-security-with-oauth2/
//http://www.baeldung.com/spring-security-oauth-jwt
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableOAuth2Sso
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }



}
