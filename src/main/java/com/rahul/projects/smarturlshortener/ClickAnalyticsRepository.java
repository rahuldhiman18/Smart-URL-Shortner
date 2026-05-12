package com.rahul.projects.smarturlshortener;

import java.util.List;
import  org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClickAnalyticsRepository extends JpaRepository<ClickAnalytics, Long>{
   List<ClickAnalytics> findByUrl(Url url);
}

