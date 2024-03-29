package com.pivotal.slack.talkers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableCaching
public class SlackTalkersApplication {

  public static void main(String[] args) {
    SpringApplication.run(SlackTalkersApplication.class, args);
  }

  @Configuration
  public static class PcfsTalkersConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
      return restTemplateBuilder.build();
    }

    @Bean
    public SlackService slackService(@Value("${slack.api-token}") String slackApiToken,
        RestTemplate restTemplate) {
      return new SlackService(slackApiToken, restTemplate);
    }

  }

}
