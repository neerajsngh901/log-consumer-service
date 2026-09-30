package com.eleserv.log_consumer_service.controller;


import com.eleserv.log_consumer_service.entity.ApplicationLog;
import com.eleserv.log_consumer_service.repository.LogRepository;
import com.eleserv.log_consumer_service.repository.LogSpecifications;
import com.eleserv.log_consumer_service.service.LogProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LogGraphqlController {

    private final LogRepository logRepository;
    private final LogProducerService logProducerService;
    @QueryMapping
    public List<ApplicationLog> searchLogs(
            @Argument String applicationName,
            @Argument String traceId,
            @Argument String flowName,
            @Argument String caseId,
            @Argument String messageType,
            @Argument String status,
            @Argument String startDate,
            @Argument String endDate) {

        LocalDateTime start;
        if (startDate != null && !startDate.isBlank()) {
            start = java.time.OffsetDateTime.parse(startDate).toLocalDateTime();
        } else {
            // Fall back automatically to 60 days ago if no start date is requested
            start = LocalDateTime.now().minusDays(60);
        }

        LocalDateTime end = (endDate != null && !endDate.isBlank())
                ? java.time.OffsetDateTime.parse(endDate).toLocalDateTime()
                : LocalDateTime.now();

        var spec = LogSpecifications.filterByCriteria(
                applicationName, traceId, flowName, caseId, messageType, status, start, end
        );

        return logRepository.findAll(spec);
    }

    @MutationMapping
    public LogResponse processLog(
            @Argument String applicationName,
            @Argument String traceId,
            @Argument String flowName,
            @Argument String caseId,
            @Argument String messageType,
            @Argument String message
    ) {
        logProducerService.sendLog(applicationName, traceId, flowName, caseId, messageType, message);
        return new LogResponse(applicationName, traceId, flowName, caseId, messageType, message);
    }

    public record LogResponse(String applicationName, String traceId, String flowName, String caseId, String messageType, String message) {}

}