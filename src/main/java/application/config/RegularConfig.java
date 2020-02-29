package application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegularConfig {

    @Value(value = "${regularSymbols}")
    private String regularSymbols;

    //Изначально tomcat запрещает использовать в URL спецсимволы ^ | { } [ ]
    // Поэтому с помощью данного бина, мы добавляем необходимые нам символы для использования
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> connector.setProperty("relaxedQueryChars", regularSymbols));
        return factory;
    }
}
