# Online Judge — React + Spring Boot (Starter Monorepo)

Production-leaning skeleton with:
- React + Vite
- Spring Boot 3 (Java 21), Postgres, Redis, RabbitMQ
- AWS S3 (via MinIO in dev) for submissions and testcases
- Flyway migrations
- Redis cache for contest rankings
- RabbitMQ queue for judge & ranking tasks
- Jacoco coverage gate (80%+), Vitest coverage gate (80%+)
- Dockerfiles + docker-compose for local dev
- CI workflow for GitHub Actions

## Quick start (dev)
```bash
docker compose -f infra/compose/docker-compose.dev.yml up -d
# Backend
cd apps/api-spring && ./gradlew test
# Frontend
cd ../../apps/web && pnpm i && pnpm dev
```

