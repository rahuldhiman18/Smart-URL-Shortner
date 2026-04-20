package com.rahul.projects.smarturlshortener;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url,Long>{

    Url findByShortCode(String shortCode);

    Url findByOriginalUrl(String originalUrl);

}