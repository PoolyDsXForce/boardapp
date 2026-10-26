const express = require('express');
const path = require('path');
const sqlite3 = require('sqlite3').verbose();
const session = require('express-session');

const app = express();
const PORT = 3000;

// Database
const db = new sqlite3.Database('./users.db');

db.run(`
CREATE TABLE IF NOT EXISTS users (
    username TEXT PRIMARY KEY,
    password TEXT
)
`);

// Middleware
app.use(express.urlencoded({ extended: true }));

app.use(
    session({
        secret: 'boardapp-secret',
        resave: false,
        saveUninitialized: false
    })
);

app.use(express.static(path.join(__dirname, 'public')));

// Existing Lab 5 Routes
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'views', 'index.html'));
});

app.get('/documentation', (req, res) => {
    res.sendFile(path.join(__dirname, 'views', 'documentation.html'));
});

app.get('/quickstart', (req, res) => {
    res.sendFile(path.join(__dirname, 'views', 'quickstart.html'));
});

app.get('/feature1', (req, res) => {
    res.sendFile(path.join(__dirname, 'views', 'feature1.html'));
});

app.get('/feature2', (req, res) => {
    res.sendFile(path.join(__dirname, 'views', 'feature2.html'));
});

// Login Page
app.get('/login', (req, res) => {
    res.sendFile(path.join(__dirname, 'views', 'login.html'));
});

// Register Page
app.get('/register', (req, res) => {
    res.sendFile(path.join(__dirname, 'views', 'register.html'));
});

// Handle Registration
app.post('/register', (req, res) => {
    const username = req.body.username;
    const password = req.body.password;

    db.run(
        'INSERT INTO users(username, password) VALUES(?, ?)',
        [username, password],
        function(err) {

            if (err) {
                return res.send(
                    '<h2>Username already exists.</h2><a href="/register">Try Again</a>'
                );
            }

            req.session.username = username;
            res.redirect('/landing');
        }
    );
});

// Handle Login
app.post('/login', (req, res) => {

    const username = req.body.username;
    const password = req.body.password;

    db.get(
        'SELECT * FROM users WHERE username = ? AND password = ?',
        [username, password],
        (err, row) => {

            if (row) {
                req.session.username = username;
                return res.redirect('/landing');
            }

            res.send(
                '<h2>Invalid username or password.</h2><a href="/login">Try Again</a>'
            );
        }
    );
});

// Landing Page
app.get('/landing', (req, res) => {

    if (!req.session.username) {
        return res.redirect('/login');
    }

    res.send(`
        <h1>BoardApp Landing Page</h1>
        <h2>Welcome ${req.session.username}</h2>
        <a href="/logout">Logout</a>
    `);
});

// Logout
app.get('/logout', (req, res) => {

    req.session.destroy(() => {
        res.redirect('/login');
    });

});

app.listen(PORT, () => {
    console.log(`Project site running at http://localhost:${PORT}`);
});