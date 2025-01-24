package com.broadcom.tanzu.demos.bonjour;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
record AppProperties(String greetings) {
}
