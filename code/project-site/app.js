const express = require('express');
const path = require('path');

const app = express();
const PORT = 3000;

app.use(express.static(path.join(__dirname, 'public')));

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

app.listen(PORT, () => {
  console.log(`Project site running at http://localhost:${PORT}`);
});
