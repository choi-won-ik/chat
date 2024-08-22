# Chatting Page

### 프로젝트 기간
2023.09.09. ~ 2023.11.11
### 개발 인원
1명(개인 프로젝트)

### 요약

간단한 회원가입을 통한 채팅 웹

* * * 

프로젝트 개발환경은 다음과 같습니다.

#### 개발환경
* SpringBoot JPA
* Java
* Gradle
* MySQL
* Docker
* kafka

#### 구현 부분
* 실시간 의사소통 기능
    - Stomp와 sockJS를 설정
  
    ![stomp와sockJS](https://github.com/user-attachments/assets/3621cea3-7548-4d5c-a338-6313d0d52f49)

    ![WebSocket설정](https://github.com/user-attachments/assets/2b048555-5951-4abc-81b9-f9fdda9f2d61)

    - kakfa 설정

    ![docker cmd](https://github.com/user-attachments/assets/0158192e-28e7-4624-8590-1af581b258d6)
    
    ![kafka프로듀서](https://github.com/user-attachments/assets/a8eae357-fd6b-403f-b062-0881433a74b6)

    ![kafka 리스너](https://github.com/user-attachments/assets/44975331-de91-4fa0-8c5b-47b6011bfc85)

     

* 채팅페이지
    - 부재중 온 메시지 표시
        + 확인하면 표시가 사라짐
    - 모달 및 드롭다운으로 채팅할 유저 검색

#### 채팅페이지

![P20240228_012149670_26B20883-3524-454D-8CD0-82C271DAFC2B](https://github.com/choi-won-ik/chat/assets/140231082/0207f8e4-a6d0-427c-b4dc-7b503e383c5c)


