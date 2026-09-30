# Log Consumer Service

## Description

The **Log Consumer Service** is a Spring Boot application designed to process and store application logs. It integrates with Kafka for log message consumption and production, and provides a GraphQL API for querying and managing logs.

---

## Features

- **Kafka Integration**: 
  - Consumes log messages from a Kafka topic.
  - Produces log messages to a Kafka topic.
- **GraphQL API**:
  - Query logs based on various criteria.
  - Process and publish logs via mutations.
- **Database Integration**:
  - Stores logs in a relational database.
  - Supports dynamic filtering using JPA Specifications.

---

## Technologies Used

- **Java** (Spring Boot)
- **Kafka** (Spring Kafka)
- **GraphQL** (Spring GraphQL)
- **JPA** (Spring Data JPA)
- **Maven** (Build Tool)

---

## Prerequisites

- **Java 17+**
- **Apache Kafka** (Running instance)
- **Relational Database** (MySQL)
- **Maven** (Installed)

---

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/log-consumer-service.git
   cd log-consumer-service
