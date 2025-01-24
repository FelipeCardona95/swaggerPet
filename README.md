# **Swagger Pet Store API Testing**

This project contains automated tests and performance tests for the Swagger Pet Store API.

---

## **Automated API Tests**

- **Location**: `src/test`
- **Description**: 
  - These tests cover both positive and negative scenarios for the **Pet**, **Store**, and **User** endpoints.
  - The tests are designed following a modular structure for maintainability and scalability.
- **Status**: Fully functional and tested.

---

## **Performance Tests**

- **Location**: `performance-tests/k6`
- **Description**: 
  - These tests are implemented using K6 to validate the API's performance under different loads.
  - They include scenarios for creating, fetching, updating, and deleting pets, as well as tests for store and user endpoints.
- **Status**: 
  - The tests are coded, but most of them return a `500 Internal Server Error`, which is not the expected result.
  - Due to time constraints, these tests are delivered in their current state and require further debugging to address the errors.

---

## **Notes**

This project is delivered as part of a practical exercise. While the automated API tests are complete, the performance tests require additional debugging to meet expected results.

