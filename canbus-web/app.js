
const express = require('express')
const path = require('path');
const app = express()

// 라우트 정의
app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'main', 'main.html'));
});

app.get('/auth/login', (req, res) => {
  res.sendFile(path.join(__dirname, 'auth', 'signin', 'signin.html'));
});

app.get('/auth/register', (req, res) => {
  res.sendFile(path.join(__dirname, 'auth', 'signup', 'signup.html'));
});

app.get('/map', (req, res) => {
  res.sendFile(path.join(__dirname, 'kakaomap', 'map.html'));
});

app.get('/sheet', (req, res) => {
  res.sendFile(path.join(__dirname, 'sheet', 'sheet.html'));
});

app.get('/mybus', (req, res) => {
  res.sendFile(path.join(__dirname, 'busRoute', 'busRouteSheet.html'));
});


// 서버 시작
app.listen(3000, () => {
  console.log('Server running on port 3000');
});