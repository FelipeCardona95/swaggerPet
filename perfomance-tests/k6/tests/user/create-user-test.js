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
    const payload = JSON.stringify({
        id: Math.floor(Math.random() * 10000),
        username: `user${Math.floor(Math.random() * 1000)}`,
        firstName: 'Test',
        lastName: 'User',
        email: 'testuser@example.com',
        password: 'password123',
        phone: '1234567890',
        userStatus: 1,
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    const response = http.post('http://localhost:8080/api/v3/user', payload, params);

    // Validate response
    check(response, {
        'is status 200': (r) => r.status === 200,
        'response time < 500ms': (r) => r.timings.duration < 500,
    });

    sleep(1);
}
