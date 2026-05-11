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
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class UrlController {

    private final UrlService urlService;
    private final QRService qrService;

    public UrlController(UrlService urlService, QRService qrService) {
        this.urlService = urlService;
        this.qrService = qrService;
    }


    // Shorten URL
    @PostMapping("/shorten")
    public ResponseEntity<?> shortenUrl(@RequestBody ShortenRequest request) {
        try{
        Url url = urlService.shortenUrl(request);
        return ResponseEntity.ok(url);
    } catch(IllegalArgumentException e){
       return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }
}

    // Redirect to original URL
    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
     try {
        Url url = urlService.getAndIncrementClicks(shortCode);
        return ResponseEntity.status(HttpStatus.FOUND)
           .location(URI.create(url.getOriginalUrl().trim()))
           .build();
     }    catch(IllegalArgumentException e){
        return ResponseEntity.status(410).build();
     } 
     catch(RuntimeException e){
           return ResponseEntity.notFound().build();
     }  
    }

    @GetMapping("/qr/{shortcode}")
    public ResponseEntity<byte[]> genrateQR(@PathVariable String shortcode){
        try {
            Url url = urlService.getByShortCode(shortcode);
            byte[] qrcode = qrService.generateQRCode(url.getOriginalUrl());
            return ResponseEntity.ok()
                   .header("Content-Type","image/png")
                   .body(qrcode);
        } catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    
}