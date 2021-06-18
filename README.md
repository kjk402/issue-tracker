# issue-tracker
### team-09

## 팀원

- IOS
    - [쏭](https://github.com/1song2)

- FE
    - [Dico](https://github.com/ha3158987)
    - [ppamppam](https://github.com/ppamppamman)

- BE
    - [Bat](https://github.com/kjk402)
    - [Nas](https://github.com/Malloc72P)

### 배포 주소
| 구분 | 주소 | 
| ---- | ---- |
|  BE  | http://ec2-13-124-158-166.ap-northeast-2.compute.amazonaws.com/api/swagger-ui.html |

### 브랜치 관리 규칙
GitHub의 이슈와 프로젝트를 적극적으로 사용하자.

main 브랜치를 디폴트로한다.
main 브랜치에는 작업하지 않고,
ios/dev, fe/dev, be/dev로 나누어 작업한다.

클래스명/feature/기능 형태로 브랜치 이름을 짓는다.
ex) ios/feature/test

feature에서 작업 후 클래스/dev 쪽에 pr을 보내는 방식으로 진행한다.

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


<br>

#### 이슈 작성 페이지
- Markdown으로 작성 가능한 Editor 구현

![addIssue 페이지](https://user-images.githubusercontent.com/65105537/122519129-134ffb00-d04d-11eb-8a03-17fbda0f73b9.gif)

<br>

