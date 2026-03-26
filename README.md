# ⚽ Football Data Fetcher

A lightweight Spring Boot microservice/worker responsible for synchronizing real-time football data from a third-party API to an internal relational database.

## 📝 Overview
This project contains a single, focused processing flow: fetching today's matches via API, parsing the complex JSON response into independent entities, and persisting them into the database while maintaining strict data integrity and relational mapping.

## ✨ How it works

The `MatchFetchService` processes data using a **Smart Upsert** mechanism to optimize database performance:

1. **Fetch Data:** Makes an HTTP request via the `FootballDataApiClient` to retrieve the match list for a 24-hour window (from `today` to `today + 1`).
2. **Update (If the match exists):** * Compares the `lastUpdated` timestamp from the API response with the existing database record.
   * If there is a change (e.g., a new goal scored, half-time whistle), the system extracts and updates only the `Season` and `MatchScore` entities, avoiding unnecessary database writes.
3. **Insert (If it's a new match):**
   * Automatically parses and creates (or links if already existing) hierarchical satellite data in the following order: `Area` ➡️ `Competition` ➡️ `Season` ➡️ `Team` (Home & Away).
   * Finally, it aggregates all the foreign keys and saves the new `Match` entity along with its corresponding `MatchScore`.

## 💻 Tech Stack
* **Java / Spring Boot**
* **Spring Data JPA:** Handling complex relational data mappings (One-to-Many, Many-to-One).
* **REST Client:** Managing synchronous HTTP requests (utilizing `.block()`).
