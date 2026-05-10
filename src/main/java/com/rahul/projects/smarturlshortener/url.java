package com.rahul.projects.smarturlshortener;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "urls")
public class Url{
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_url", nullable = false, unique = true)
      private String originalUrl;
     
      @Column(name = "short_code", unique = true, nullable = false)
       private String shortCode;

       @Column(name = "created_at", nullable = false)
       private LocalDateTime createdAt;

       @Column(name = "expiryAt", nullable=true)
       private LocalDateTime expiryAt;

    @Column(nullable = false)
    private Long clicks;

   public Url(){
        this.clicks = 0L;
   }

    public Long getId() {
        return id;
    }

    public String getOriginalUrl(){
      return originalUrl;
    }
    public void setOriginalUrl(String originalUrl){
        this.originalUrl = originalUrl;
    }
   
    public String getShortCode(){
        return shortCode;
    }
    public void setShortCode(String shortCode){
        this.shortCode = shortCode;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiryAt(){
        return expiryAt;
    }

    public void setExpiryAt(LocalDateTime expiryAt){
        this.expiryAt = expiryAt;
    }

    public Long getClicks(){
        return clicks;
    }
    public void setClicks(Long clicks){
        this.clicks = clicks;
    }


}

