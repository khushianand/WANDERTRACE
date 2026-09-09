# Media storage and iPhone media

PostgreSQL stores **only media metadata**: media type, generated storage key, URLs, captions, MIME type, byte size, and ordering. Original binary files are written through `MediaStorageService`, keeping controllers and content services independent of a particular provider.

The current development implementation uses generated, traversal-safe object keys in an application-controlled media root. The service boundary is intentionally compatible with a future MinIO/S3 implementation; credentials remain exclusively server-side. Public render URLs can later become signed URLs, CloudFront URLs, or a controlled media delivery endpoint without changing media CRUD contracts.

## Original-first policy

WanderTrace preserves the uploaded original because travel memories are not disposable web assets. The intended processing model is: **original → display derivative → thumbnail/poster**. Derivative processing is deliberately a future asynchronous job so memory creation and uploads remain responsive. HEIC/HEIF originals are retained for later browser-compatible conversion; MP4/QuickTime originals are retained for later web streaming derivatives.

Accepted smartphone media: JPEG, PNG, WebP, HEIC/HEIF; MP4 and QuickTime; MP3, M4A/MP4, WAV. Initial configurable server limits are 25 MB images, 500 MB video, and 50 MB audio. File extensions and MIME type must agree, filenames cannot contain paths, and storage keys are generated rather than accepted from clients.
