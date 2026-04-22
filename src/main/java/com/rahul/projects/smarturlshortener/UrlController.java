package com.rahul.projects.smarturlshortener;

import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class UrlController{

    private final UrlService urlService;

    public UrlController(UrlService urlService){
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<Url> shortenUrl(@RequestBody String originalUrl) {
        Url url = urlService.shortenUrl(originalUrl);
        return ResponseEntity.ok(url);
    }

    @GetMapping("/{shortCode}")

    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
      Url url = urlService.getByShortCode(shortCode);
      if(url==null){
        return ResponseEntity.notFound().build();
      }
      return ResponseEntity.status(302)
         .header("Location",url.getOriginalUrl())
         .build();
    }

}

