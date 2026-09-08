# Media storage

Media metadata belongs in PostgreSQL; media binary objects belong in MinIO locally or S3 in production. The bucket is server-side infrastructure, never exposed as a collaborator credential. Upload routes must validate type, extension, and configured size limits before writing generated object keys.
