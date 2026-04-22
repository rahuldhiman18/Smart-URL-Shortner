package com.rahul.projects.smarturlshortener;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UrlRepository extends JpaRepository<Url,Long>{

    
   Optional<Url> findByShortCode(String shortCode); 

    Optional<Url> findByOriginalUrl(String originalUrl);

    boolean existsByShortCode(String shortCode);

}