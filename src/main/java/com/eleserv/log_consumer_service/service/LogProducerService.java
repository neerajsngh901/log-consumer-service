package com.eleserv.log_consumer_service.service;
import com.eleserv.log_consumer_service.dto.LogMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogProducerService {

    private final KafkaTemplate<String, LogMessageDto> kafkaTemplate;

    @Value("${kafka.topic.name:application-logs-topic}")
    private String topicName;

    public void sendLog(String applicationName, String traceId, String flowName,
                        String caseId, String messageType, String message) {

        LogMessageDto logDto = LogMessageDto.builder()
                .applicationName(applicationName)
                .traceId(traceId)
                .flowName(flowName)
                .caseId(caseId)
                .messageType(messageType.toUpperCase())
                .message(message)
                .dateTime(LocalDateTime.now())
                .build();

        String key = (traceId != null && !traceId.isBlank()) ? traceId : applicationName;

        kafkaTemplate.send(topicName, key, logDto)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.trace("Log published to Kafka topic [{}]", topicName);
                    } else {
                        log.error("Failed to publish log to Kafka: {}", ex.getMessage(), ex);
                    }
                });
    }
}