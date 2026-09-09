# API

All successful responses have `{ "success": true, "data": ..., "message": null }`; failures use a consistent envelope and never contain stack traces.

## Auth
- `POST /api/auth/register`, `POST /api/auth/login`, `POST /api/auth/logout`, `GET /api/auth/me`

## Collaborative trips
- `GET|POST /api/trips`, `GET|PUT|DELETE /api/trips/{id}`
- `GET|POST /api/trips/{id}/members`, `PATCH|DELETE /api/trips/{tripId}/members/{userId}`

## Content
- `GET|POST /api/trips/{tripId}/destinations`, `GET|PUT|DELETE /api/destinations/{id}`
- `GET|POST /api/destinations/{id}/memories`, `GET|PUT|DELETE /api/memories/{id}`
- `GET|POST /api/memories/{id}/media`, `PATCH|DELETE /api/media/{id}`
- `GET /api/public/memories/{token}`

Authenticated endpoints derive identity from the HTTP session and authorize via the content's parent trip membership. Only owners/editors can change content; only owners manage members. The public endpoint only returns active NFC tags for `PUBLIC` memories.
