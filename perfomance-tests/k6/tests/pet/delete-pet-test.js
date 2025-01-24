import http from 'k6/http';
import { check } from 'k6';

export let options = {
  stages: [
    { duration: '10s', target: 5 },
    { duration: '20s', target: 15 },
    { duration: '10s', target: 5 },
  ],
};

export default function () {
  const petId = Math.floor(Math.random() * 100000);
  const url = `http://localhost:8080/api/v3/pet/${petId}`;

  const res = http.del(url);
  check(res, {
    'is status 200 or 404': (r) => r.status === 200 || r.status === 404,
    'response time < 300ms': (r) => r.timings.duration < 300,
  });
}
