package com.rahul.projects.smarturlshortener;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    // Shorten URL
    @PostMapping("/shorten")
    public ResponseEntity<Url> shortenUrl(@RequestBody String originalUrl) {
        Url url = urlService.shortenUrl(originalUrl);
        return ResponseEntity.ok(url);
    }

    // Redirect to original URL
    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {

        Url url = urlService.getAndIncrementClicks(shortCode);
        if(url==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url.getOriginalUrl().trim()))
                .build();
    }
}