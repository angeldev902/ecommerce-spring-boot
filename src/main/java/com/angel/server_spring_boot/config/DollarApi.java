package com.angel.server_spring_boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "dollar-api")
public class DollarApi {
    private String dollarUrl;
    private String dollarKey;

    public String getDollarUrl() { return dollarUrl; }
    public void setDollarUrl(String dollarUrl) { this.dollarUrl = dollarUrl; }

    public String getDollarKey() { return dollarKey; }
    public void setDollarKey(String dollarKey) { this.dollarKey = dollarKey; }
}
