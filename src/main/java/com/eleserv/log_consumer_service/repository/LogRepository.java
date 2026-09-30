package com.eleserv.log_consumer_service.repository;


import com.eleserv.log_consumer_service.entity.ApplicationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface LogRepository extends JpaRepository<ApplicationLog, Long>, JpaSpecificationExecutor<ApplicationLog> {
    void deleteByDateTimeBefore(LocalDateTime dateTime);
}