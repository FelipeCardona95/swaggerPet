import http from 'k6/http';
import { check } from 'k6';

export let options = {
  vus: 20, // Virtual Users
  duration: '1m', // Run for 1 minute
};

export default function () {
  const petId = Math.floor(Math.random() * 100000);
  const url = `http://localhost:8080/api/v3/pet/${petId}`;

  const res = http.get(url);
  check(res, {
    'is status 200 or 404': (r) => r.status === 200 || r.status === 404,
    'response time < 300ms': (r) => r.timings.duration < 300,
  });
}
