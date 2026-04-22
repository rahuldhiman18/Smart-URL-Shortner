package com.rahul.projects.smarturlshortener;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.time.LocalDateTime;

@Service 
public class UrlService {
   
    private final UrlRepository  urlRepository;

    public UrlService(UrlRepository urlRepository){
        this.urlRepository = urlRepository;
    }

    public Url shortenUrl(String originalUrl){
        Url existing = urlRepository.findByOriginalUrl(originalUrl);

        if(existing != null){
            return existing;
        }

        String shortcode = UUID.randomUUID().toString().substring(0,8);
        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortCode(shortcode);
        url.setCreatedAt(LocalDateTime.now());

        return urlRepository.save(url);
    }
    public Url getByShortCode(String shortCode){
        return urlRepository.findByShortCode(shortCode);

    }
     
}
