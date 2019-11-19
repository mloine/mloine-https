package com.mloine.https;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

/**
 *  @Author: XueYongKang
 *  @Description:
 *              接口测试路径：http://127.0.0.1:7070/swagger-ui.html
 *  @Data: 2019/11/19 12:32
 */
@SpringBootApplication
public class PaydemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaydemoApplication.class, args);
    }

    @Value("${http.port}")
    private Integer httpPort;

    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(createHTTPConnector());
        return tomcat;
    }

    private Connector createHTTPConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setSecure(false);
        connector.setPort(httpPort);
        return connector;
    }


}
