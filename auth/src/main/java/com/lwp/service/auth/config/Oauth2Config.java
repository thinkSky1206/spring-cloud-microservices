package com.lwp.service.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Author:liuwp
 * Date: 2017/8/23
 * Description:
 * http://www.cnblogs.com/xingxueliao/p/5911292.html
 * http://zhouqing86.github.io/2017/10/12/spring-cloud-oauth2/
 * http://www.javashuo.com/content/p-6571172.html
 * http://www.javashuo.com/content/p-6571171.html
 * 步骤1 获取code
 * 请求：http://localhost:9999/oauth/authorize?client_id=web&response_type=code&redirect_uri=http://www.baidu.com
 * 返回：https://www.baidu.com/?code=YosMBY
 * 步骤2 获取acesss token
 * post http://web:webs@localhost:9999/oauth/token
 *      grant_type=authorization_code
 *      code=Li4NZo
 *      redirect_uri=http://www.baidu.com
 *
 */
@Configuration
@EnableAuthorizationServer
public class Oauth2Config extends AuthorizationServerConfigurerAdapter {

    /**
     * endpoint security配置
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
    }

    /**
     * 客户端详情配置
     * ClientDetailsService接口：jdbc和memory2个实现
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("web")
                .secret("webs")
                .authorizedGrantTypes("authorization_code", "refresh_token", "implicit", "password", "client_credentials")//允许授权类型
                .scopes("web_app");//允许授权范围
    }

    /**
     * 授权（authorization），令牌（token）的访问端点，令牌服务(token services)配置
     * AuthorizationServerTokenServices接口：DefaultTokenServices实现-》TokenStore 接口：InMemoryTokenStore,JdbcTokenStore,JwtTokenStore
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter());
//        authenticationManager 授权类型为password时 需要配置
//        endpoints.tokenServices(tokenServices());
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }


}
