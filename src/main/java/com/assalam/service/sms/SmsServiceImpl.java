package com.assalam.service.sms;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.assalam.web.rest.PaiementResource;

public class SmsServiceImpl {

  private final Logger log = LoggerFactory.getLogger(PaiementResource.class);

  private static final String API_KEY = "4952EA10-0949-F465-F828-CA1B6D67F1C1";

  private static final String API_LOGIN = "assalamapp";

  private static final String SMS_API_URL = "https://rest.clicksend.com/v3/sms/send";


  public ResponseEntity<String> testSms() {
  RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = createHeaders(API_LOGIN, API_KEY);

    JSONObject sms = new JSONObject();
    JSONObject message = new JSONObject();
    JSONArray messages = new JSONArray();

    try {

      message.put("source", "java");
      message.put("from", "assalamasso");
      message.put("to", "+447405547847");
      message.put("body", "test java");

      messages.put(message);
      sms.put("messages", messages);

    }
    catch (JSONException e) {

      e.printStackTrace();
    }


    HttpEntity<String> request = new HttpEntity<>(sms.toString(), headers);

    log.debug("SMS Request $$$$$$$$$^^^^^" + request.toString());

    ResponseEntity<String> response = restTemplate.exchange
        (SMS_API_URL, HttpMethod.POST, request, String.class);

    log.debug("SMS $$$$$$$$$^^^^^" + response.toString() + response);

    return response;

  }

  private HttpHeaders createHeaders(final String username, final String password) {
    return new HttpHeaders() {
      {
        // String auth = username + ":" + password;
        // byte[] encodedAuth = Base64.encodeBase64(
        // auth.getBytes(Charset.forName("US-ASCII")));
        // String authHeader = "Basic " + new String(encodedAuth);
        // set("Content-Type", "application/json");
        // set("Authorization", authHeader);

      }
    };
  }

}
