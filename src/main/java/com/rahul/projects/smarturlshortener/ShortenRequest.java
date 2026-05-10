package com.rahul.projects.smarturlshortener;
import java.time.LocalDateTime;

public class ShortenRequest {

    private String originalUrl;
    private String customCode;
    private LocalDateTime expiryAt;

    public String getOriginalUrl(){
         return originalUrl;
    }
   public void setOriginalUrl(String originalUrl){
         this.originalUrl = originalUrl;
   }

   public String getCustomCode() {
      return customCode;
   }

   public void setCustomCode(String customCode){
      this.customCode = customCode;
   }

   public LocalDateTime getExpiryAt(){
      return expiryAt;
   }

   public void setExpiryAt(LocalDateTime expiryAt){
      this.expiryAt = expiryAt;
   }
}