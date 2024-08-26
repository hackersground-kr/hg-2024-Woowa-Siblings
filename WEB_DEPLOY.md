# WEB_DEPLOY

### 웹 배포

[hg-2024-Woowa-Siblings](https://github.com/hackersground-kr/hg-2024-Woowa-Siblings) 레포지토리에는 canbus-server, canbus-web 두가지 모듈이 존재하는데,

canbus-server 모듈 안에 있는 파일이 변경된 commit을 main에 push 하게 되면 Github Actions workflow가 작동해서 서버 배포가 자동으로 진행된다.
(.gitignore 주석만 살짝 수정하는 식으로 테스트하시면 됩니다)