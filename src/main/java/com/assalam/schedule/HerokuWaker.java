package com.assalam.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.assalam.config.ApplicationProperties;

public class HerokuWaker {

  private static final String HEROKU_APP_URL = "https://assalamapp.herokuapp.com";

  private ApplicationProperties appProperties;

  @Autowired
  public void setAppProperties(ApplicationProperties appProperties) {
    this.appProperties = appProperties;
  }

  private final Logger log = LoggerFactory.getLogger(HerokuWaker.class);



  }
