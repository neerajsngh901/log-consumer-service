package com.eleserv.log_consumer_service.service;


import com.eleserv.log_consumer_service.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogCleanupService {

    private final LogRepository logRepository;

    @Value("${logging.retention.days:60}")
    private int retentionDays;

    @Scheduled(cron = "0 0 2 * * ?")
    @Transactional
    public void purgeOldLogs() {
        LocalDateTime thresholdDate = LocalDateTime.now().minusDays(retentionDays);
        log.info("Starting log cleanup. Purging logs older than {} days (before: {})", retentionDays, thresholdDate);
        
        try {
            logRepository.deleteByDateTimeBefore(thresholdDate);
            log.info("Successfully purged expired logs.");
        } catch (Exception e) {
            log.error("Error occurred while purging old logs: {}", e.getMessage(), e);
        }
    }
}