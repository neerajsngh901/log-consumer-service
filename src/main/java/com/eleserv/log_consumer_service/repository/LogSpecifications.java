package com.eleserv.log_consumer_service.repository;
import com.eleserv.log_consumer_service.entity.ApplicationLog;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class LogSpecifications {

    public static Specification<ApplicationLog> filterByCriteria(
            String applicationName, String traceId, String flowName, 
            String caseId, String messageType, String status, 
            LocalDateTime startDate, LocalDateTime endDate) {
        
        return (root, query, criteriaBuilder) -> {
            var predicate = criteriaBuilder.conjunction();

            if (applicationName != null && !applicationName.isBlank()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("applicationName"), applicationName));
            }
            if (traceId != null && !traceId.isBlank()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("traceId"), traceId));
            }
            if (flowName != null && !flowName.isBlank()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("flowName"), flowName));
            }
            if (caseId != null && !caseId.isBlank()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("caseId"), caseId));
            }
            if (messageType != null && !messageType.isBlank()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("messageType"), messageType.toUpperCase()));
            }
            if (status != null && !status.isBlank()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("status"), status.toUpperCase()));
            }
            if (startDate != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("dateTime"), startDate));
            }
            if (endDate != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("dateTime"), endDate));
            }

            return predicate;
        };
    }
}