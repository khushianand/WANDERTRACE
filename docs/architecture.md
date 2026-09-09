# WanderTrace Architecture

See the root [README](../README.md) for the current runnable vertical slice. Public memory resolution is available at `GET /api/public/memories/{token}`, backed by Flyway/PostgreSQL, and returns render-safe DTO data only. Authenticated management, upload storage, and deployment hardening are planned as independent vertical slices.
