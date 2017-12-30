package com.assalam.service.sms;

public class SmsRequest {

  private String source;

  private String to;

  private String from;

  private String body;

  public SmsRequest(String source, String to, String from, String body) {
    this.source = source;
    this.to = to;
    this.from = from;
    this.body = body;

  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getRecipient() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }



}
