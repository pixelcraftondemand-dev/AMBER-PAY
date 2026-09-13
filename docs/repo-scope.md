# Repository Scope

Status legend: ✅ implemented + verified · 🔶 designed but not built · ⛔ not started

| Area | Status | Notes |
|---|---|---|
| Ledger service (Rust) | ✅ | Core accounting engine, holds, idempotency, balances, fee math, reconciliation, gRPC server, tests |
| Core API (auth, KYC, fraud, provider adapters, webhook dispatch, transaction state machine) | 🔶 | Designed in docs/architecture.md and related docs; no server implementation exists in this repository |
| Web client SPA | ✅ (thin view layer) | Vite + React shell exists; it is not a complete end-to-end product without the missing Core API |
| Product-grade authentication/session layer | 🔶 | Design documents exist; backend auth service not built |
| Provider integrations / rails | 🔶 | Rail adapters are planned; no live provider implementation is in this repo |
| Webhook dispatch / outbox consumers | 🔶 | Design exists; consumer implementation is not started here |
| Java or Spring Boot Core API service | ⛔ | Not started in this repository |
| Production deployment with working end-to-end flows | ⛔ | Not possible yet until the Core API and integrations are built |

## Important note

This repository is currently the ledger service only. The missing service is not a "small gap" in the same codebase; it is a separate backend that must be implemented and wired in before the UI can complete real requests.
