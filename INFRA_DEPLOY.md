# INFRA_DEPLOY

**아래의 “리소스”라는 용어는 진행하고 있는 그 챕터의 리소스를 말합니다 (제목)**

### Azure Database for MySQL

---

1. 리소스 만들기 버튼을 클릭하고, 유연한 서버 탭의 만들기를 클릭한다.
2. 본인 팀의 리소스 그룹을 선택한다.
3. 서버 이름을 기입한다
4. 관리자 사용자 이름, 암호, 암호 확인을 기입한다.
5. 네트워킹 탭에서 가서 `+ 0.0.0.0 - 255.255.255.255 추가`를 선택한다.
6. 검토 + 만들기를 선택한다.
7. 만들기를 누르고 리소스가 생성될 때까지 대기한다.
8. https://dl.cacerts.digicert.com/DigiCertGlobalRootCA.crt.pem 링크에서 pem 파일을 다운받는다.
9. 리소스의 설정 - 연결 탭에 들어가 적혀있는 연결 방법을 따라 DB에 연결한다. (이 밑은 MySQL Workbench를 기준으로 설명한다)
    1. **MySQL 연결** 탭에서 **+** 기호를 클릭하여 새 연결을 추가합니다.
    2. **연결 이름** 필드에 연결 이름을 입력합니다.
    3. 연결 형식으로 **표준(TCP/IP)**을(를) 선택합니다.
    4. 호스트 이름 필드에 **woowasiblings-db.mysql.database.azure.com**을(를) 입력하세요.
    5. 사용자 이름으로 **woowasiblings**을(를) 입력한 후 **암호**을(를) 입력합니다.
    6. **SSL 탭**(으)로 이동하여 SSL 사용 필드를 필수로 업데이트합니다.
    7. **SL CA 파일** 필드에 아까 다운로드 받은 **DigiCertGlobalRootCA.crt.pem** 파일의 파일 위치를 입력합니다.
    8. **연결 테스트**을(를) 클릭하여 연결을 테스트합니다.
    9. 연결에 성공하면 **확인**을(를) 클릭하여 연결을 저장합니다.
10. 만든 연결을 통해 DB에 연결합니다.
11. Schema 탭을 우클릭하고, Create Schema 버튼을 클릭합니다.
12. Schema Name 칸에 canbus_db를 기입하고 Add 버튼을 클릭합니다.

### Container Registry

---

1. 리소스 만들기 버튼을 클릭한다.
2. 본인 팀의 리소스 그룹을 선택한다.
3. 레지스트리 이름을 기입한다.
4. 검토 + 만들기를 선택한다.
5. 만들기를 누르고 리소스가 생성될 때까지 대기한다.
6. 설정 - 액세스 키에 가서 관리 사용자 박스에 체크를 한다.
7. 사용자 이름과 password를 각각 복사 해놓는다
8. Github 팀 Repository - Setting - Secrets and Variables - Actions 탭에서 New repository secret 버튼을 누른다.
9. Name 란에 AZURE_USERNAME을 기입하고, Secret 란에 복사 해둔 사용자 이름을 넣고 Add secret 버튼을 누른다.
10. Name 란에 AZURE_PASSWORD을 기입하고, Secret 란에 복사 해둔 password를 넣고 Add secret 버튼을 누른다.

### Azure Credentials

---

(bash, powershell, ternimal 등 CLI 환경에서 진행한다)

1. Azure CLI 기준으로 다음의 명령어를 입력하면 로그인 화면으로 이동하게 되는데, Azure 구독이 있는 계정으로 로그인을 하면 json이 반환된다.

    ```bash
    az login
    az ad sp create-for-rbac --name <my-credentials-name> --role contributor --scopes /subscriptions/<AZURE_SUBSCRIPTION_ID>/resourceGroups/<my-resource-group> --json-auth --output json
    ```

2. Github 팀 Repository - Setting - Secrets and Variables - Actions 탭에서 New repository secret 버튼을 누른다.
3. Name 란에 AZURE_CREDENTIALS를 기입하고, Secret 란에 위에 명령어를 치고 나온 json을 붙여넣고
   Add secret 버튼을 누른다.
