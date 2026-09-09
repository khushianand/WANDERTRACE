# Running WanderTrace from the VS Code terminal

This guide assumes that you have already cloned the repository and opened its
root folder (`WANDERTRACE`) in Visual Studio Code.

## 1. Open the VS Code terminal

Use **Terminal → New Terminal**, or press <kbd>Ctrl</kbd>+<kbd>`</kbd>. Confirm
that the terminal is in the repository root:

```bash
pwd
```

The output should end in `WANDERTRACE`. If it does not, change into the cloned
project directory (replace the example path with your real clone location):

```bash
cd /path/to/WANDERTRACE
```

## 2. Create your local environment file

Copy the supplied template once:

```bash
cp .env.example .env
```

Open `.env` in VS Code and replace the placeholder values for at least
`POSTGRES_PASSWORD`, `S3_SECRET_KEY`, and `ADMIN_PASSWORD`. Do **not** commit
this file: it can contain local secrets.

## Option A — Run the entire application with Docker (recommended)

### Requirements

- Docker Desktop (or Docker Engine plus the Compose plugin) must be installed
  and running.
- Verify this before continuing:

```bash
docker --version
docker compose version
```

### Start everything

From the repository root, build and start all services:

```bash
docker compose up --build
```

Leave this terminal open while using the app. Docker starts these services:

- PostgreSQL database
- MinIO object-storage service
- Spring Boot backend API
- React frontend

When startup completes, open these URLs in a browser:

```text
Application:       http://localhost:5173
Demo travel trace: http://localhost:5173/m/DEMO-PARIS-2026
API health:        http://localhost:8080/actuator/health
Swagger API UI:    http://localhost:8080/swagger-ui/index.html
MinIO console:     http://localhost:9001
```

### Stop Docker services

In the terminal running Compose, press <kbd>Ctrl</kbd>+<kbd>C</kbd>. Then run:

```bash
docker compose down
```

To also delete the local PostgreSQL data volume and begin with an empty local
database next time, run the following instead. This permanently deletes the
Docker-managed local database data:

```bash
docker compose down -v
```

## Option B — Run the frontend and backend in development mode

Use this option when you want hot reload while editing. You need Node.js/npm,
Java 21, Maven, and PostgreSQL. Check the installed tools:

```bash
node --version
npm --version
java --version
mvn --version
```

### 1. Start PostgreSQL

If you do not already have PostgreSQL running locally, use Docker to start only
the database service:

```bash
docker compose up -d postgres
```

Check that it is ready:

```bash
docker compose ps
```

The database uses the `POSTGRES_DB`, `POSTGRES_USER`, and
`POSTGRES_PASSWORD` values in `.env`.

### 2. Start the backend

Open a **new** VS Code terminal. From the repository root, run:

```bash
cd backend
DATABASE_URL=jdbc:postgresql://localhost:5432/wandertrace \
DATABASE_USERNAME=wandertrace \
DATABASE_PASSWORD='your-postgres-password' \
mvn spring-boot:run
```

Replace `your-postgres-password` with the same `POSTGRES_PASSWORD` you put in
`.env`. If you changed `POSTGRES_DB` or `POSTGRES_USER`, also replace
`wandertrace` in the command with those values.

Keep this terminal open. On first startup, Flyway creates the schema and seeds
the demo data. Verify the backend from another terminal or a browser:

```bash
curl http://localhost:8080/actuator/health
```

### 3. Start the frontend

Open another **new** VS Code terminal. From the repository root, run:

```bash
cd frontend
npm install
npm run dev
```

Vite will print a local URL, normally `http://localhost:5173`. Open it in your
browser, then visit the demo page:

```text
http://localhost:5173/m/DEMO-PARIS-2026
```

The frontend development server forwards requests beginning with `/api` to the
backend at `http://localhost:8080`, so both terminals must stay running.

### 4. Stop development-mode services

Press <kbd>Ctrl</kbd>+<kbd>C</kbd> in the frontend terminal and the backend
terminal. If you started PostgreSQL with Docker, stop it from the repository
root:

```bash
docker compose down
```

## Useful checks

Run frontend checks from the `frontend` directory:

```bash
npm run lint
npm run test
npm run build
```

Run backend tests from the `backend` directory:

```bash
mvn test
```
