# StreamLocal

A production-grade local media server built with Java and Spring Boot 4, demonstrating distributed systems concepts including CQRS, event-driven architecture, observability, and resilience patterns.

Built as a portfolio project targeting backend engineering roles at product companies. Designed to run entirely on a local machine via a single `docker-compose up`.

---

## Architecture

StreamLocal uses a CQRS (Command Query Responsibility Segregation) architecture with an event-driven sync mechanism:

- **Write path** — video metadata is written to Postgres (source of truth)
- **Event bus** — Kafka carries change events between write and read sides
- **Read path** — Redis serves all read queries, kept in sync by a Kafka consumer
- **Streaming** — FFmpeg transcodes videos to HLS format, served via Spring Boot endpoints
- **Resilience** — Resilience4j provides rate limiting (token bucket) and circuit breaking on all Postgres calls
- **Observability** — Micrometer + Prometheus + Grafana for metrics, Zipkin for distributed tracing, structured JSON logging with correlation IDs throughout

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 4 |
| Database | PostgreSQL 15 |
| Cache / Read model | Redis 7 |
| Message broker | Apache Kafka (KRaft — no Zookeeper) |
| Metrics | Micrometer + Prometheus + Grafana |
| Tracing | Zipkin |
| Resilience | Resilience4j |
| Testing | JUnit 5 + Mockito + Testcontainers |
| CI/CD | GitHub Actions |
| Containerisation | Docker + Docker Compose |

---

## Prerequisites

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) (with WSL2 backend on Windows)
- Java 21+
- WSL2 (required for FFmpeg transcoding)
- FFmpeg installed in WSL2 (`sudo apt install ffmpeg`)

---

## Getting Started

```bash