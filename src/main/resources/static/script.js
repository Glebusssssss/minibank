const api = '/api';

function showMenu(username) {
    document.getElementById('login').classList.add('hidden');
    document.getElementById('menu').classList.remove('hidden');
    document.getElementById('user').innerText = username;
}

function register() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    fetch(api + '/register', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({username, password})
    })
    .then(res => res.text())
    .then(data => document.getElementById('msg').innerText = data);
}

function loginUser() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    fetch(api + '/login', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({username, password})
    })
    .then(res => res.text())
    .then(data => {
        if (data.includes("successful")) {
            showMenu(username);
        } else {
            document.getElementById('msg').innerText = data;
        }
    });
}

function deposit() {
    const amount = prompt("Enter amount:");
    fetch(api + '/deposit?amount=' + amount, {method: 'POST'})
    .then(res => res.text())
    .then(data => document.getElementById('output').innerText = data);
}

function withdraw() {
    const amount = prompt("Enter amount:");
    fetch(api + '/withdraw?amount=' + amount, {method: 'POST'})
    .then(res => res.text())
    .then(data => document.getElementById('output').innerText = data);
}

function getBalance() {
    fetch(api + '/balance')
    .then(res => res.text())
    .then(data => document.getElementById('output').innerText = data);
}

function getHistory() {
    fetch(api + '/history')
    .then(res => res.json())
    .then(data => {
        let text = "";
        data.forEach(t => {
            text += t.type + ": " + t.amount + "\n";
        });
        document.getElementById('output').innerText = text;
    });
}

function logout() {
    location.reload();
}