import http from 'k6/http';
import { check } from 'k6';

export let options = {
  stages: [
    { duration: '30s', target: 10 },
    { duration: '1m', target: 30 },
    { duration: '30s', target: 0 },
  ],
};

export default function () {
  const url = 'http://localhost:8080/api/v3/pet';
  const payload = JSON.stringify({
    id: 1, // Use a static or known ID
    name: 'UpdatedTestPet',
    category: { id: 1, name: 'UpdatedCategory' },
    photoUrls: ['https://example.com/updated-photo'],
    tags: [{ id: 1, name: 'UpdatedTag' }],
    status: 'sold',
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  const res = http.put(url, payload, params);
  check(res, {
    'is status 200': (r) => r.status === 200,
  });
}
