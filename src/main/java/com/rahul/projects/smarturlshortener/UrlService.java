package com.rahul.projects.smarturlshortener;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service 
public class UrlService {
   
    private final UrlRepository  urlRepository;
    private final ClickAnalyticsRepository clickAnalyticsRepository;

    public UrlService(UrlRepository urlRepository , ClickAnalyticsRepository  clickAnalyticsRepository){
        this.urlRepository = urlRepository;
        this.clickAnalyticsRepository = clickAnalyticsRepository;
    }

    public Url shortenUrl(ShortenRequest request){
         String originalUrl = request.getOriginalUrl().trim();
         if(originalUrl==null || originalUrl.isBlank()){
            throw new IllegalArgumentException("Url cannot be empty");
         }

         if(!originalUrl.startsWith("http://") && !originalUrl.startsWith("https://")){
            throw new IllegalArgumentException("Invalid Url format");
         }
          Optional<Url> existing = urlRepository.findByOriginalUrl(originalUrl);

         if(existing.isPresent()){
          return existing.get();
    }

         String shortcode;

         if(request.getCustomCode() != null &&  !request.getCustomCode().isBlank()){
            if(urlRepository.existsByShortCode(request.getCustomCode())){
                throw new IllegalArgumentException("Custome code already taken");
            }
            shortcode = request.getCustomCode();
          } else{
                do{
                    shortcode = UUID.randomUUID().toString().substring(0 , 8);
                }   while(urlRepository.existsByShortCode(shortcode));
         }

        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortCode(shortcode);
        url.setExpiryAt(request.getExpiryAt());
        url.setCreatedAt(LocalDateTime.now());

        return urlRepository.save(url);
    } 

      public Url getByShortCode(String shortCode){
    return urlRepository.findByShortCode(shortCode)
        .orElseThrow(() -> new RuntimeException("Short URL not found"));
}
     public Url getAndIncrementClicks(String shortCode){

    Url url = urlRepository.findByShortCode(shortCode)
        .orElseThrow(() -> new RuntimeException("Short URL not found"));

        if(url.getExpiryAt() != null && LocalDateTime.now().isAfter(url.getExpiryAt())){
            throw new IllegalArgumentException("Url expired");
        }

    url.setClicks(url.getClicks() + 1);

    ClickAnalytics click = new ClickAnalytics();
    click.setUrl(url);
    click.setClickedAt(LocalDateTime.now());
    click.setDevice("unknown");
    click.setBrowser("unknown");
    clickAnalyticsRepository.save(click);

    return urlRepository.save(url);
}
}