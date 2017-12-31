package com.assalam.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * Properties specific to JHipster.
 *
 * <p>
 *     Properties are configured in the application.yml file.
 * </p>
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
  private final RemoteApp remoteApp = new RemoteApp();



  public RemoteApp getRemoteApp() {
      return remoteApp;
  }



  public static class RemoteApp {

      private String url;

      public String getUrl() {
          return url;
      }

      public void setUrl(String url) {
          this.url = url;
      }


}
}
