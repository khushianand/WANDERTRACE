# Collaboration and authorization

Every trip is a protected collaboration boundary. A global application role (`USER` or `ADMIN`) is distinct from a trip membership role. `trip_members` gives each user exactly one `OWNER`, `EDITOR`, or `VIEWER` role per trip.

| Role | View | Edit content | Manage collaborators | Delete trip |
|---|---:|---:|---:|---:|
| OWNER | yes | yes | yes | yes |
| EDITOR | yes | yes | no | no |
| VIEWER | yes | no | no | no |

The API derives the current user from the HTTP session, then resolves membership server-side through `TripAccessService`. Clients never send an owner ID or permission claim to authorize a request. Collaborators receive neither database nor object-storage credentials; their browser communicates only with the authenticated API.
