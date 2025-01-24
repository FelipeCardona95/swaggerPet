import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    stages: [
        { duration: '1m', target: 10 }, // Ramp-up to 10 users
        { duration: '3m', target: 10 }, // Hold at 10 users
        { duration: '1m', target: 0 },  // Ramp-down
    ],
};

export default function () {
    const response = http.get('http://localhost:8080/api/v3/store/inventory');

    // Validate response
    check(response, {
        'is status 200': (r) => r.status === 200,
        'response time < 500ms': (r) => r.timings.duration < 500,
    });

    sleep(1);
}
