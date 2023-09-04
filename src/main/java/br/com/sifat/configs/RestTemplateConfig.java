package br.com.sifat.configs;

import br.com.sifat.core.utils.RestTemplateUtils;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

    //pode ser usando se a api estiver no contexto do serviceRegister
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {

        int TIMEOUT = RestTemplateUtils.getTIME_OUT_DEFAULT();

        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofMillis(TIMEOUT))
                .setReadTimeout(Duration.ofMillis(TIMEOUT))
                .build();
    }
}
