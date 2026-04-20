package com.rahul.projects.smarturlshortener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "urls")
public class url{
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String originalUrl;

    @Column(unique=true ,nullable = false)
    private String shortcode;

    private LocalDateTime createdAt;

    private Long Click = 0L;


}

