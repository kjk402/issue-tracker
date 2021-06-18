# iOS

## 화면 스크린샷
로그인                       |  이슈목록                   |  이슈목록 / 삭제 Alert       |  이슈목록 / 필터
:-------------------------:|:-------------------------:|:-------------------------:|:-------------------------:
![Login](https://user-images.githubusercontent.com/56751259/122512816-6c676100-d044-11eb-9fff-ee55888e9f85.png) | ![Issues](https://user-images.githubusercontent.com/56751259/122513048-cbc57100-d044-11eb-9f3e-35c4973e3860.png) | ![Delete Alert](https://user-images.githubusercontent.com/56751259/122514284-ad607500-d046-11eb-84ec-c63371489d7a.png) | ![Filter Selection](https://user-images.githubusercontent.com/56751259/122513217-10e9a300-d045-11eb-88d6-43a95ffbb365.png)

## 지원 기능

### 1. GitHub OAuth 로그인
![GitHub OAuth Login](https://user-images.githubusercontent.com/56751259/122514734-62932d00-d047-11eb-8556-f0874a4fc1c9.gif)
* 서버에서 가져온 `client_id`와 `scope`으로 GitHub에 `Authorization Code`를 요청한다.
* 반환된 `Authorization Code`를 서버로 보내 `User` 정보를 요청한다.
* 로그인이 성공할 시 이슈목록으로 이동하며 `내 계정`탭의 아이템 이미지가 `User`의 프로필 이미지로 대체된다.

### 2. 아래로 당겨서 검색바 노출
![Pull To Search](https://user-images.githubusercontent.com/56751259/122514891-99694300-d047-11eb-9999-9a66d8bd00ff.gif)
* 처음 이슈목록 화면으로 들어 오면 검색바는 숨겨져 있고 이슈 목록 하단 `아래로 당기면 검색바가 보여요!👀`라는 안내 문구 보인다.
* 화면을 아래로 스크롤 하면 숨겨져 있던 검색바가 나타나며 안내 문구는 사라진다.

### 3. 유동적 글자 크기 조절 지원 (Dynamic Type)
![Dynamic Type](https://user-images.githubusercontent.com/56751259/122515324-3d52ee80-d048-11eb-8649-265b653acf46.gif)
* 접근성 지원의 일환으로 사용자 설정에 따라 텍스트 크기가 유동적으로 변한다.
* `설정 - 손쉬운 사용 - 디스플레이 및 텍스트 크기 - 더 큰 텍스트`에서 선호하는 글자 크기 조절
