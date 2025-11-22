# API Documentation — Merchant & Product

This file documents the REST APIs for Merchant and Product modules and includes ready-to-import cURL commands you can paste into Postman (Import → Raw Text → Paste cURL).

Base URL (dev profile):
- http://localhost:8888

Headers for requests that send JSON:
- Content-Type: application/json

Notes:
- No authentication is implemented by default. In production you must secure these endpoints.
- Merchant `passwordHash` is never returned by dashboard/profile endpoints.

---

## Merchant APIs

### 1) Signup Merchant
- Method: POST
- Path: /api/merchant/signup
- Description: Create a new merchant (stores password hashed).
- Body (JSON):
```json
{
  "merchantId": 1,
  "businessName": "Acme Ltd",
  "contactEmail": "owner@acme.com",
  "contactPhone": "+1-555-1234",
  "merchantAccountNumber": "ACC-100",
  "settlementCurrency": "USD",
  "password": "PlainTextPass"
}
```
- cURL (paste into Postman Import → Raw Text):

```bash
curl -X POST "http://localhost:8888/api/merchant/signup" \
  -H "Content-Type: application/json" \
  -d '{"merchantId":1,"businessName":"Acme Ltd","contactEmail":"owner@acme.com","contactPhone":"+1-555-1234","merchantAccountNumber":"ACC-100","settlementCurrency":"USD","password":"PlainTextPass"}'
```

### 2) Login Merchant
- Method: POST
- Path: /api/merchant/login
- Description: Authenticate merchant by merchantId and password (returns Merchant profile on success).
- Body (JSON):
```json
{
  "merchantId": 1,
  "password": "PlainTextPass"
}
```
- cURL:

```bash
curl -X POST "http://localhost:8888/api/merchant/login" \
  -H "Content-Type: application/json" \
  -d '{"merchantId":1,"password":"PlainTextPass"}'
```

### 3) Get Merchant Profile
- Method: GET
- Path: /api/merchant/{id}
- Description: Returns merchant profile (safe DTO; excludes passwordHash).
- Example (merchantId = 1):

```bash
curl "http://localhost:8888/api/merchant/1"
```

### 4) Update Merchant Profile
- Method: PUT
- Path: /api/merchant/{id}
- Description: Update merchant profile fields (businessName, contactEmail, contactPhone, settlementCurrency).
- Body (JSON example):
```json
{
  "businessName": "Acme Ltd v2",
  "contactEmail": "admin@acme.com",
  "contactPhone": "+1-555-9999",
  "settlementCurrency": "USD"
}
```
- cURL (update merchant 1):

```bash
curl -X PUT "http://localhost:8888/api/merchant/1" \
  -H "Content-Type: application/json" \
  -d '{"businessName":"Acme Ltd v2","contactEmail":"admin@acme.com","contactPhone":"+1-555-9999","settlementCurrency":"USD"}'
```

### 5) Merchant Dashboard
- Method: GET
- Path: /api/merchant/{id}/dashboard
- Description: Aggregated dashboard DTO (merchant profile + placeholder metrics). Returns 404 if merchant not found.
- Example (merchantId = 1):

```bash
curl "http://localhost:8888/api/merchant/1/dashboard"
```

Response (example - placeholders):
```json
{
  "merchant": { /* MerchantProfileDto */ },
  "currentBalance": 0,
  "transactionsLast7Days": 0,
  "amountLast7Days": 0,
  "pendingSettlements": 0,
  "recentTransactions": []
}
```

---

## Product APIs
Products are linked to merchants. Use `merchantId` in the product DTO when creating a product.

### Product DTO example (create/update)
```json
{
  "merchantId": 1,
  "name": "Widget Pro",
  "description": "High-quality widget",
  "price": 49.95,
  "currency": "USD"
}
```

### 1) Create Product
- Method: POST
- Path: /api/products
- Description: Create a product for a merchant. `merchantId` must reference an existing merchant.
- cURL:

```bash
curl -X POST "http://localhost:8888/api/products" \
  -H "Content-Type: application/json" \
  -d '{"merchantId":1,"name":"Widget Pro","description":"High-quality widget","price":49.95,"currency":"USD"}'
```

### 2) Update Product
- Method: PUT
- Path: /api/products/{id}
- Description: Update product fields (name, description, price, currency).
- cURL (update product 123):

```bash
curl -X PUT "http://localhost:8888/api/products/123" \
  -H "Content-Type: application/json" \
  -d '{"name":"Widget Pro v2","price":59.95}'
```

### 3) Delete Product
- Method: DELETE
- Path: /api/products/{id}
- cURL (delete product 123):

```bash
curl -X DELETE "http://localhost:8888/api/products/123"
```

### 4) Get Product
- Method: GET
- Path: /api/products/{id}
- cURL (get product 123):

```bash
curl "http://localhost:8888/api/products/123"
```

### 5) List Products by Merchant
- Method: GET
- Path: /api/products/merchant/{merchantId}
- Description: Returns list of products that belong to the merchant.
- cURL (list merchant 1 products):

```bash
curl "http://localhost:8888/api/products/merchant/1"
```

---

## Importing to Postman
1. Open Postman → Import → Raw Text.
2. Paste any of the cURL commands above and click Import.
3. The request will appear in Postman; set environment variable `baseUrl` if you prefer and replace `http://localhost:8888` with `{{baseUrl}}`.

Example: change the cURL to use `{{baseUrl}}`:
```bash
curl -X POST "{{baseUrl}}/api/merchant/signup" -H "Content-Type: application/json" -d '{ ... }'
```

## Security & Next Steps
- Add authentication (JWT or session) to protect endpoints.
- Authorize actions so a merchant can only manage their own products and view their own dashboard.
- Add input validation (price > 0, currency length 3) and pagination for product lists.
- Replace dashboard placeholders with real aggregates once transactions/settlements are available.

---

If you'd like, I can also:
- Export a Postman Collection JSON (fully populated) and add it to the repo for direct import.
- Convert all controller responses to use DTOs consistently (e.g., change `/api/merchant/{id}` to return `MerchantProfileDto`).
- Add validation annotations to product DTOs and return proper validation error bodies.

Tell me which of those you'd like next and I'll implement it.
