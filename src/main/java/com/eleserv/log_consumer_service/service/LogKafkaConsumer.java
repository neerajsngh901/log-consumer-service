package com.eleserv.log_consumer_service.service;

import com.eleserv.log_consumer_service.dto.LogMessageDto;
import com.eleserv.log_consumer_service.entity.ApplicationLog;
import com.eleserv.log_consumer_service.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Objects; // Make sure to import this
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogKafkaConsumer {
    private final LogRepository logRepository;
    @KafkaListener(topics = "${kafka.topic.name:application-logs-topic}", groupId = "log-consumer-group")
    @Transactional
    public void consume(LogMessageDto dto) {
        try {
            if (dto == null) {
                log.warn("Received a null log message payload. Skipping processing.");
                return;
            }

            ApplicationLog logEntity = ApplicationLog.builder()
                    .applicationName(dto.applicationName())
                    .traceId(dto.traceId())
                    .flowName(dto.flowName())
                    .caseId(dto.caseId())
                    .messageType(dto.messageType())
                    .message(dto.message())
                    .status("STORED")
                    .dateTime(dto.dateTime())
                    .build();
            logRepository.save(logEntity);
            log.info("Log message processed and saved successfully for traceId: {}", dto.traceId());

        } catch (Exception e) {
            log.error("Failed to save log message: {}", e.getMessage(), e);
        }
    }
}