
const express = require('express')
const path = require('path');
const axios = require('axios');
const cors = require('cors'); // CORS 패키지 추가
const app = express()

app.use(cors({
  origin: '*' // 허용할 도메인
})); // CORS 미들웨어 추가
app.use(express.json()); // JSON 파싱 미들웨어 추가

// 라우트 정의
app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'auth', 'signin', 'signin.html'));
});

app.get('/main', (req, res) => {
  res.sendFile(path.join(__dirname, 'main', 'main.html'));
});

app.get('/register', (req, res) => {
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

app.get('/busRoute', (req, res) => {
  res.setHeader('Access-Control-Allow-origin', '*');
  res.setHeader('Access-Control-Allow-Credentials', 'true'); // 쿠키 주고받기 허용
  res.sendFile(path.join(__dirname, 'busRoute', 'busRoute.html'));
});

app.get('/bus', async (req, res) => {
  res.setHeader('Access-Control-Allow-origin', '*');
  res.setHeader('Access-Control-Allow-Credentials', 'true'); // 쿠키 주고받기 허용

  try {
    // 외부 API에서 데이터 가져오기
    const externalApiUrl = 'https://canbus-server.wittybeach-50fa98b2.westus2.azurecontainerapps.io/bus/passengers';
    const externalResponse = await axios.get(externalApiUrl);
    const data = externalResponse.data;

    // OpenAI API 요청 데이터 설정
    const requestBody = {
      messages: [
        {
          "role": "system",
          "content": "You are an AI assistant that helps people find information."
        },
        {
          "role": "user",
          "content": "당신은 최적의 버스 경로를 짜야합니다. 탑승객 여러명이 있고 각자 출발 장소, 출발 시간, 목적지 정보를 가지고 있습니다. 이러한 정보가 주어졌을때 최소한의 경로로 많은 사람을 태울 수 있는 버스 경로를 만들어주세요. 시간이 많이 차이나는 경우 버스를 여러 호차로 분리할 수 있습니다. 승객은 무조건 모두 태워야합니다. 추정 시간을 예측할 수 없더라도 무조건 예상해서 넣어야합니다.\\n아래와 같은 형태로 JSON 응답을 해야합니다. 대답은 하지마세요. 오직 JSON만 반환하세요.\\n[  \\n    { \\n        \\\"duration\\\": \\\"추정 1시간\\\",  \\n        \\\"timeRange\\\": \\\"오후 6시 - 오후 7시\\\",  \\n        \\\"busNumber\\\": \\\"1호차\\\",  \\n        \\\"stations\\\": [ // Station\\n            {  \\n                \\\"name\\\": \\\"의성(터미널)\\\",  \\n                \\\"time\\\": \\\"오후 6시\\\"  \\n            },  \\n            {  \\n                \\\"name\\\": \\\"의성농공단지\\\",  \\n                \\\"time\\\": \\\"오후 6시 15분\\\"  \\n            },  \\n            {  \\n                \\\"name\\\": \\\"봉양\\\",  \\n                \\\"time\\\": \\\"오후 7시\\\"  \\n            }  \\n        ]  \\n    },  \\n    {  \\n        \\\"duration\\\": \\\"추정 30분\\\",  \\n        \\\"timeRange\\\": \\\"오전 12시 20분 - 오전 12시 50분\\\",  \\n        \\\"busNumber\\\": \\\"2호차\\\",  \\n        \\\"stations\\\": [  \\n            {  \\n                \\\"name\\\": \\\"의성농공단지\\\",  \\n                \\\"time\\\": \\\"오전 12시 20분\\\"  \\n            },  \\n            {  \\n                \\\"name\\\": \\\"봉양\\\",  \\n                \\\"time\\\": \\\"오전 12시 50분\\\"  \\n            }  \\n        ]  \\n    }  \\n]  \\n\\n[{ // Passanger\\n    “id”:1,\\n    “departureTime”:”오후 6시\\\",\\n    “departure”:”의성(터미널)\\\",\\n    “arrival”:”의성농공단지\\\"\\n},\\n{\\n    “id”:2,\\n    “departureTime”:”오후 6시 15분\\\",\\n    “departure”:”의성농공단지\\\",\\n    “arrival”:”봉양\\\"\\n},\\n{\\n    “id”:3,\\n    “departureTime”:”오전 12시 20분\\\",\\n    “departure”:”의성농공단지\\\",\\n    “arrival”:”봉양\\\"\\n}]\\n위와 같은 형태로 질문을 하면 거리 시간 등을 고려해서 최적의 경로를 만들어내세요. \\n예상시간은 추정하기 어렵더라도 오차 범위가 커도 상관 없으니 무조건 추정하세요.\""
        },
        {
          "role": "assistant",
          "content": "[\n    { \n        \"duration\": \"1시간\",  \n        \"timeRange\": \"오후 6시 - 오후 7시\",  \n        \"busNumber\": \"1호차\",  \n        \"stations\": [\n            {  \n                \"name\": \"의성(터미널)\",  \n                \"time\": \"오후 6시\"  \n            },  \n            {  \n                \"name\": \"의성농공단지\",  \n                \"time\": \"오후 6시 15분\"  \n            },  \n            {  \n                \"name\": \"봉양\",  \n                \"time\": \"오후 7시\"  \n            }  \n        ]  \n    },  \n    {  \n        \"duration\": \"30분\",  \n        \"timeRange\": \"오전 12시 20분 - 오전 12시 50분\",  \n        \"busNumber\": \"2호차\",  \n        \"stations\": [  \n            {  \n                \"name\": \"의성농공단지\",  \n                \"time\": \"오전 12시 20분\"  \n            },  \n            {  \n                \"name\": \"봉양\",  \n                \"time\": \"오전 12시 50분\"  \n            }  \n        ]  \n    }  \n]"
        },
        {
          "role": "user",
          "content": "[\n    {\n        \"id\": 1,\n        \"arrival\": \"경북 의성군 금성면 산운리 640-4\",\n        \"departure\": \"경북 의성군 금성면 수정리 306\",\n        \"departureTime\": \"16:50\",\n        \"user\": {\n            \"id\": 3,\n            \"identifier\": \"test123\",\n            \"password\": \"2bbe0c48b91a7d1b8a6753a8b9cbe1db16b84379f3f91fe115621284df7a48f1cd71e9beb90ea614c7bd924250aa9e446a866725e685a65df5d139a5cd180dc9\"\n        }\n    }\n]"
        },
        {
          "role": "assistant",
          "content": "[\n    { \n        \"duration\": \"추정 1시간\",  \n        \"timeRange\": \"16:50 - 17:50\",  \n        \"busNumber\": \"1호차\",  \n        \"stations\": [\n            {  \n                \"name\": \"경북 의성군 금성면 수정리 306\",  \n                \"time\": \"16:50\"  \n            },  \n            {  \n                \"name\": \"경북 의성군 금성면 산운리 640-4\",  \n                \"time\": \"17:50\"  \n            }  \n        ]  \n    }\n]"
        },
        {
          "role": "user",
          "content": "시간은 오전/오후 00시 00분 과 같은 형식으로 표현하세요."
        },
        {
          "role": "assistant",
          "content": "[\n    { \n        \"duration\": \"추정 1시간\",  \n        \"timeRange\": \"오후 4시 50분 - 오후 5시 50분\",  \n        \"busNumber\": \"1호차\",  \n        \"stations\": [\n            {  \n                \"name\": \"경북 의성군 금성면 수정리 306\",  \n                \"time\": \"오후 4시 50분\"  \n            },  \n            {  \n                \"name\": \"경북 의성군 금성면 산운리 640-4\",  \n                \"time\": \"오후 5시 50분\"  \n            }  \n        ]  \n    }\n]"
        },
        {
          role: 'user',
          content: JSON.stringify(data) // 외부 API 응답 데이터를 JSON 문자열로 변환하여 포함
        }
      ],
      max_tokens: 800,
      temperature: 0.7,
      frequency_penalty: 0,
      presence_penalty: 0,
      top_p: 0.95,
      stop: null
    };

    // OpenAI API 호출
    const openaiResponse = await axios.post(openaiApiUrl, requestBody, {
      headers: {
        'Content-Type': 'application/json',
        'api-key': openaiApiKey
      }
    });

    // JSON 데이터 한 줄로 변환
    const jsonData = openaiResponse.data.choices[0].message.content
      .replace(/\n/g, '')
      .replace(/^```json|```$/g, '');

    // 클라이언트에 JSON 데이터 반환
    res.setHeader('Content-Type', 'application/json');
    res.send(jsonData);

  } catch (error) {
    console.error('Error:', error);
    res.status(500).send('Error processing the request');
  }
});

// 서버 시작
app.listen(3000, () => {
  console.log('Server running on port 3000');
});