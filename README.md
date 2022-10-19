# 배달공동구매 프로젝트

### 프로젝트 목적
<a>1인 가구의 증가로 식당별 최소 주문 금액, 배달비용에 대하여 부담을 느낄 사람들을 위하여 게시글을 통해 사람들을 모집하여 배달비, 금액을 share 하기 위함</a>
### 개발툴
Java11, SpringBoot 2.7.4 , Gradle, JPA, JavaScript, HTML, CSS, Thymeleaf, 카카오맵 API
### 사용방법 
1) 회원가입 아이디, 사진첨부, 닉네임, 비밀번호, 등급, 휴대폰번호, 주소, 우편번호  
2) 로그인  
3) 내 위치 확인 : 회원가입시 작성했던 주소를 기반으로 내 현재 위치를 마커로 표시함  
4) 픽업장소 선택 : 3 에서 선택한 주소에서 가장 가까운 픽업장소 선택  
5) 카테고리 선택 : 희망하는 음식 카테고리 선택  
6) 식당 조회 : 픽업장소의 주소(시,구 기준 일치) 와 카테고리가 일치하는 식당 찾아서 평점 위주로 내림차순으로 정렬  
7) 최종 확인 : 4 ~ 6 선택 내역을 확인  
8) 게시글 작성  

### 화면  
   ![image](https://user-images.githubusercontent.com/96707563/196575084-0c2e9f72-7ef2-43de-97f3-7d0bf46dab5e.png)
![image](https://user-images.githubusercontent.com/96707563/196575343-46278f08-86cc-4add-afe9-528789dd5381.png)
![image](https://user-images.githubusercontent.com/96707563/196575432-986816b8-ed42-472c-86c3-57a9f5d7540d.png)
