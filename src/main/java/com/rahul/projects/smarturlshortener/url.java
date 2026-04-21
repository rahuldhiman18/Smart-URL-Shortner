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

    @Column(nullable=false ,unique = true)
    private String originalUrl;

    @Column(unique=true ,nullable = false)
    private String shortCode;


    @Column(nullable = false)
    private LocalDateTime createdAt;

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

    public Long getClicks(){
        return clicks;
    }
    public void setClicks(Long clicks){
        this.clicks = clicks;
    }


}

