# `우아한남매들` - `캔버스(Canbus)`

해커그라운드 해커톤에 참여하는 `우아한남매들` 팀의 `캔버스(Canbus)`입니다.

## 참고 문서

> 아래 두 링크는 해커톤에서 앱을 개발하면서 참고할 만한 문서들입니다. 이 문서들에서 언급한 서비스 이외에도 더 많은 서비스들이 PaaS, SaaS, 서버리스 형태로 제공되니 참고하세요.

- [순한맛](./REFERENCES_BASIC.md)
- [매운맛](./REFERENCES_ADVANCED.md)

## 제품/서비스 소개

<!-- 아래 링크는 지우지 마세요 -->
[제품/서비스 소개 보기](TOPIC.md)
<!-- 위 링크는 지우지 마세요 -->

## 오픈 소스 라이센스

<!-- 아래 링크는 지우지 마세요 -->
[오픈소스 라이센스 보기](./LICENSE)
<!-- 위 링크는 지우지 마세요 -->

## 설치 방법

> **아래 제공하는 설치 방법을 통해 심사위원단이 여러분의 제품/서비스를 실제 Microsoft 애저 클라우드에 배포하고 설치할 수 있어야 합니다. 만약 아래 설치 방법대로 따라해서 배포 및 설치가 되지 않을 경우 본선에 진출할 수 없습니다.**

### 사전 준비 사항

> 여러분의 제품/서비스를 Microsoft 애저 클라우드에 배포하기 위해 사전에 필요한 준비 사항들을 적어주세요.
>

- Github 계정 - [Github 회원가입](https://github.com/signup)
- Azure 구독
- MySQL Workbench, TablePlus, CLI 환경 등 **DB 연결 도구**
    - [MySQL Workbench 설치 링크](https://dev.mysql.com/downloads/workbench/)
    - 이 문서에서는 MySQL Workbench를 기준으로 설명합니다
    - 본인의 운영체제에 맞는 버전을 선택해주세요
    - Download 버튼을 눌러 설치 패키지를 다운로드 받고 패키지를 실행해 설치 프로세스를 따라 설치해주세요

    - [Windows 설치 가이드](WORKBENCH_INSTALL_WIN.md)

    - [Mac 설치 가이드](WORKBENCH_INSTALL_MAC.md)

## 시작하기

> 여러분의 제품/서비스를 Microsoft 애저 클라우드에 배포하기 위한 절차를 구체적으로 나열해 주세요.
>

**INFRA -> SERVER -> WEB 순으로 진행하셔야 합니다**

[인프라 배포하기](INFRA_DEPLOY.md)

[서버 배포하기](SERVER_DEPLOY.md)

[웹 배포하기](WEB_DEPLOY.md)
