package com.broadcom.tanzu.demos.bonjour;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
class IndexController {
    private final PageViewService pageViewService;
    private final AppProperties appProperties;

    IndexController(PageViewService pageViewService, AppProperties appProperties) {
        this.pageViewService = pageViewService;
        this.appProperties = appProperties;
    }

    @GetMapping(value = "/")
    Greetings index() {
        final int pageViews = pageViewService.incrementPageViewCount("/");
        return new Greetings(appProperties.greetings(), pageViews, Instant.now());
    }
}

record Greetings(String message, int visitors, Instant timestamp) {
}
