package com.eleserv.log_consumer_service.dto;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record LogMessageDto(
        String applicationName,
        String traceId,
        String flowName,
        String caseId,
        String messageType,
        String message,
        LocalDateTime dateTime
) {}