import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    stages: [
        { duration: '1m', target: 10 },
        { duration: '3m', target: 10 },
        { duration: '1m', target: 0 },
    ],
};

export default function () {
    const params = {
        username: 'testuser', // Replace with a valid username
        password: 'password123', // Replace with a valid password
    };

    const response = http.get(`http://localhost:8080/api/v3/user/login`, { params });

    // Validate response
    check(response, {
        'is status 200': (r) => r.status === 200,
        'response time < 500ms': (r) => r.timings.duration < 500,
    });

    sleep(1);
}
