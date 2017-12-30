package com.assalam.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

public class HerokuWaker {

  private static final String HEROKU_APP_URL = "https://assalamapp2.herokuapp.com";

  private final Logger log = LoggerFactory.getLogger(HerokuWaker.class);

  @Scheduled(fixedDelay = 300000)
  public void wakeUp() {

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.exchange
        (HEROKU_APP_URL, HttpMethod.GET, null, String.class);



    }

  }
