package com.rahul.projects.smarturlshortener;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service 
public class UrlService {
   
    private final UrlRepository  urlRepository;

    public UrlService(UrlRepository urlRepository){
        this.urlRepository = urlRepository;
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

    url.setClicks(url.getClicks() + 1);

    return urlRepository.save(url);
}
}
