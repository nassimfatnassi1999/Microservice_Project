package com.saccess.newsservice.repositories;

import com.saccess.newsservice.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Date;


@Repository
public interface INewsRepository extends JpaRepository<News, Long> {

    public Iterable<News> findByDateBefore(Date twoWeeksAgo);
}
