package com.rahul.projects.smarturlshortener;

import jakarta.persistence.Table;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name="click_analytics")

public class ClickAnalytics {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String device;
    private String browser;
    private LocalDateTime clickedAt;

    @ManyToOne
    @JoinColumn(name = "url-id")
    private Url url;

    public Long getId(){
        return id;
    }

    public String getDevice(){
        return device;
    }

    public void setDevice(String device){
        this.device = device;
    }

    public String getBrowser(){
        return browser;
    }

    public void setBrowser(String browser){
        this.browser = browser;
}
  public LocalDateTime getClickedAt(){
    return clickedAt;
  }

  public void setClickedAt(LocalDateTime clickedAt){
    this.clickedAt = clickedAt;
  }

  public Url getUrl(){
    return url;
  }
  public void setUrl(Url url){
    this.url = url;
  }
}
