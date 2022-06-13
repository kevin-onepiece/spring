package com.foo.oauth2auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * @author foo
 * @since 2022-06-11
 */
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager);
    }

    /*// 重写以便设置 ClientDetailsService 实例
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 使用 ClientDetailsService 实现创建实例
        InMemoryClientDetailsService service = new InMemoryClientDetailsService();

        // 创建 ClientDetails 的实例并且设置所需的关于客户端的详细信息
        BaseClientDetails clientDetails = new BaseClientDetails();
        clientDetails.setClientId("client");
        clientDetails.setClientSecret("secret");
        clientDetails.setScope(List.of("read"));
        clientDetails.setAuthorizedGrantTypes(List.of("password"));

        // 将 ClientDetails 添加到 InMemoryClientDetailsService 实例中
        HashMap<String, BaseClientDetails> map = new HashMap<>();
        map.put("client", clientDetails);
        service.setClientDetailsStore(map);

        // 配置 clientDetailService 以供授权服务器使用
        clients.withClientDetails(service);
    }*/

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("client")
                .secret("secret")
                .authorizedGrantTypes("password")
                .scopes("read");
    }
}
