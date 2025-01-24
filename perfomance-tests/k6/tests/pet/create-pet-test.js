import http from "k6/http";
import { check } from "k6";
import { validPetPayload, invalidPetPayload } from "../../resources/testData.js";

export let options = {
  stages: [
    { duration: "1m", target: 10 },
    { duration: "2m", target: 10 },
    { duration: "1m", target: 0 },
  ],
};

export default function () {
  const url = "http://localhost:8080/api/v3/pet";

  const params = {
    headers: {
      "Content-Type": "application/json",
    },
  };

  // Usar el payload válido
  const res = http.post(url, JSON.stringify(validPetPayload), params);

  check(res, {
    "is status 200": (r) => r.status === 200,
    "response time < 300ms": (r) => r.timings.duration < 300,
  });

  // Validar con payload inválido
  const invalidRes = http.post(url, JSON.stringify(invalidPetPayload), params);

  check(invalidRes, {
    "is status 400": (r) => r.status === 400,
  });
}
