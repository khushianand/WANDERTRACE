# Authentication

Registration and login use BCrypt password hashes and server-side HTTP sessions. The frontend must use credentialed requests; passwords and session identifiers are never kept in localStorage. Session IDs are rotated after login/registration to reduce fixation risk.
