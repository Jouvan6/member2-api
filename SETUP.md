# Project Setup Guide

This document explains how to run the Graduation Project services locally.

---

# 🧩 System Overview

The system is composed of two microservices:

* **Member-1** → FastAPI (Network Intelligence Service)
* **Member-2** → Spring Boot (Risk Scoring Service)

Integration Flow:

Member-1 → analyzes domain → sends indicators → Member-2 → returns risk score.

---

# ⚙️ Requirements

## General

* Python 3.12
* Java JDK 17
* Maven
* Git

---

# 🚀 Running Member-1 (FastAPI)

## 1. Navigate to project folder

```bash
cd member-1
```

## 2. Create virtual environment

```bash
python -m venv venv
```

## 3. Activate environment

Windows:

```bash
venv\Scripts\activate
```

## 4. Install dependencies

```bash
pip install -r requirements.txt
```

## 5. Environment Variables

Create `.env` file:

```
MEMBER2_API_URL=http://localhost:8001/api/risk/analyze
ENABLE_MEMBER2_FORWARD=true
```

## 6. Run API

```bash
python -m uvicorn app.main:app --reload --port 8000
```

Member-1 runs on:

```
http://localhost:8000
```

Health Check:

```
/health
```

---

# ☕ Running Member-2 (Spring Boot)

## 1. Navigate to project folder

```bash
cd member2-api
```

## 2. Build project

```bash
mvn clean package
```

## 3. Run service

```bash
java -jar target/member2-api-0.0.1-SNAPSHOT.jar --server.port=8001
```

Member-2 runs on:

```
http://localhost:8001
```

Endpoint:

```
POST /api/risk/analyze
```

---

# 🔌 Integration Test

Send request to Member-1:

```bash
curl -X POST http://localhost:8000/analyze \
 -H "Content-Type: application/json" \
 -d "{\"domain\":\"example.com\"}"
```

Expected response includes:

```
risk_score
risk_level
member2_response
```

---

# 🧠 Notes

* `.env` file is ignored by Git for security.
* `.env.example` is provided as template.
* Member-2 must be running before Member-1 integration works.

---

# 👩‍💻 Author

Graduation Project – Network Intelligence & Risk Scoring System
