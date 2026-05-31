# 🍽️ Sistema de Recompensas para Restaurantes

Sistema de fidelización de restaurantes con procesamiento de transacciones en tiempo real usando **Apache Kafka**, **Node.js** con **Event-Driven Architecture (EDA)** y **React**.

## 📋 Descripción

Programa de recompensas donde los clientes acumulan **puntos** y **cashback** por sus consumos en restaurantes afiliados. Cada transacción es procesada de forma asíncrona a través de Apache Kafka, calculando recompensas automáticamente.

## 🏗️ Arquitectura

- **Backend**: Node.js + Express con Arquitectura Orientada a Eventos (EDA)
- **Frontend**: React + Vite con diseño moderno
- **Message Broker**: Apache Kafka (KRaft mode)
- **Base de Datos**: PostgreSQL 16
- **Testing**: Jest (85%+ cobertura)

## 🚀 Cómo Ejecutar

### Prerrequisitos
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- [Node.js 20+](https://nodejs.org/)

### 1. Levantar infraestructura
```bash
docker-compose up -d
```

### 2. Iniciar Backend
```bash
cd backend
npm install
npm run dev
```

### 3. Iniciar Frontend
```bash
cd frontend
npm install
npm run dev
```

### 4. Acceder
- **Dashboard**: http://localhost:5173
- **API**: http://localhost:3000/api
- **Kafka UI**: http://localhost:8080

## 🧪 Testing
```bash
cd backend
npm run test:coverage
```

## 📊 SonarCloud
El proyecto está configurado para análisis de calidad con SonarCloud:
- Reliability
- Security
- Maintainability
- Duplications
- Test Coverage (≥ 85%)

## 👥 Equipo
- UTEC - CS3081 Ingeniería de Software
- Laboratorio 8 - Cohesión y Acoplamiento

## 📄 Documentación
Ver [docs/architecture.md](docs/architecture.md) para la documentación completa de la arquitectura.