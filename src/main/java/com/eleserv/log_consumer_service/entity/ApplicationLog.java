package com.eleserv.log_consumer_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "application_logs", indexes = {
    @Index(name = "idx_trace_id", columnList = "traceId"),
    @Index(name = "idx_app_name", columnList = "applicationName"),
    @Index(name = "idx_message_type", columnList = "messageType"),
    @Index(name = "idx_status", columnList = "status"),
    @Index(name = "idx_date_time", columnList = "dateTime")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String applicationName;

    @Column(length = 100)
    private String traceId;

    @Column(length = 100)
    private String flowName;

    @Column(length = 100)
    private String caseId;

    @Column(nullable = false, length = 50)
    private String messageType;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;

    @Column(nullable = false, length = 50)
    private String status;

    @Column(nullable = false)
    private LocalDateTime dateTime;
}