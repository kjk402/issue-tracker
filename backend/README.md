# Issue-Tracker

## 팀원

- IOS
  - [쏭](https://github.com/1song2)

- FE
  - [Dico](https://github.com/ha3158987)
  - [ppamppam](https://github.com/ppamppamman)

- BE
  - [Bat](https://github.com/kjk402)
  - [Nas](https://github.com/Malloc72P)
  
### Backend 개발 환경

![spring](https://user-images.githubusercontent.com/59398492/117413573-1ab1be00-af51-11eb-962a-876ec517474c.png)
![docker](https://user-images.githubusercontent.com/59398492/124055813-8bf88380-da5f-11eb-86aa-f78719bf9260.png)
![mysql](https://user-images.githubusercontent.com/59398492/114123158-b89f7200-992c-11eb-9392-e8823d075aa7.png)
![AWS](https://user-images.githubusercontent.com/59398492/114123162-b9380880-992c-11eb-9446-25729659d477.png)

### Database ERD
![image](https://user-images.githubusercontent.com/59398492/124091662-07bef400-da91-11eb-9b6b-d03de6ca08d3.png)

### Backend 기술 스택
- Swagger (API 문서 자동화)
  ![image](https://user-images.githubusercontent.com/59398492/124055075-38396a80-da5e-11eb-88e0-acd6ad1150d5.png)

- 메일 서비스 (Oauth 로 GitHub 회원 가입 시 메일 전송 시스템 구현)
  ![image](https://user-images.githubusercontent.com/59398492/124054994-10e29d80-da5e-11eb-8806-7c585144ad6f.png)

- 관제 (관제 서버 구축하여 서버 다운 감지)
  ![image](https://user-images.githubusercontent.com/59398492/124054342-c7de1980-da5c-11eb-8399-86d900051ea4.png)
  
- 그외 (JDBC Template, [Oauth&JWT](https://github.com/kjk402/issue-tracker/wiki/%5BBE%5D-OAuth-%EB%A1%9C%EA%B7%B8%EC%9D%B8), Docker-Compose, Elastic Search, Kotlin(Query 관리), AWS(EC2, RDS, VPC, S3 이미지 업로드))


## App, Web 동작 화면
### iOS 화면 스크린샷
로그인                       |  이슈목록                   |  이슈목록 / 삭제 Alert       |  이슈목록 / 필터
:-------------------------:|:-------------------------:|:-------------------------:|:-------------------------:
![Login](https://user-images.githubusercontent.com/56751259/122512816-6c676100-d044-11eb-9fff-ee55888e9f85.png) | ![Issues](https://user-images.githubusercontent.com/56751259/122513048-cbc57100-d044-11eb-9f3e-35c4973e3860.png) | ![Delete Alert](https://user-images.githubusercontent.com/56751259/122514284-ad607500-d046-11eb-84ec-c63371489d7a.png) | ![Filter Selection](https://user-images.githubusercontent.com/56751259/122513217-10e9a300-d045-11eb-88d6-43a95ffbb365.png)
* 더 자세한 기능별 화면은 [iOS README](https://github.com/kjk402/issue-tracker/tree/main/iOS)에서 확인 가능

### FE 화면 스크린샷

#### 로그인 페이지 (시작 페이지)
- Github OAuth 로그인 구현

![login(시작) 페이지](https://user-images.githubusercontent.com/65105537/122518922-d126b980-d04c-11eb-9e19-241a978734ff.gif)

<br>

#### 이슈 목록 페이지
- Dropdown 필터 구현
- `이슈 작성` 버튼 클릭 시 이슈 작성 페이지로 이동(react-router)

![issues 페이지](https://user-images.githubusercontent.com/65105537/122519049-f9aeb380-d04c-11eb-8181-f70b472fb42e.gif)

