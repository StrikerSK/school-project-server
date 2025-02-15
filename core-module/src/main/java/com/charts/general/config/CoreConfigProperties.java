package com.charts.general.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.charts")
public class CoreConfigProperties {

    public Files file = new Files();

    @Data
    public static class Files {
        Boolean enabled = false;
    }

}
