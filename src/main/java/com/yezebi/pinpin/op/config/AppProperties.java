package com.yezebi.pinpin.op.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app")
public record AppProperties(
        String projectId
) {
}
