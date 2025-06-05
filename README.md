# 🏙️ AI 기반 아파트 및 동네 추천 플랫폼
![](./images/1.png)

## 📌 프로젝트 개요
AI 기반 대화 추천, 아파트 실거래 정보, 관심 목록, 리뷰, 문의 기능을 통합한 부동산 정보 플랫폼입니다.

---

## 🚀 주요 기능

### 🤖 AI 추천 기능
- **사용자 대화 기반 추천**
  - 대화 이력 분석을 통해 동네 또는 아파트 추천
- **동네 추천**
  - Pinecone 유사도 검색을 통해 관련 동네 추천
  - 결과는 JSON 형식 반환
- **아파트 추천**
  - Pinecone 유사도 검색을 통해 조건에 맞는 아파트 추천
- **데이터 업로드**
  - OpenAI Embedding API를 활용해 벡터화 후 Pinecone 저장
- **유사 검색**
  - 텍스트 임베딩 기반 유사 동네/아파트 검색
 
**AI 채팅 화면**
![](./images/16.png)

---
### 메인 화면
![](./images/2.png)
![](./images/3.png)

### 🏢 APT 기능
- 지역 기반 아파트 검색 (동/구/시도 코드 기반)
- Trie 기반 아파트/동 이름 키워드 검색
- 실거래가 정보 제공 (연도/월/일 정렬)
- Kakao API를 활용한 지도 및 길찾기 기능

**지도 기반 UI/UX**
![](./images/4.png)
**Trie 기반 검색**
![](./images/3_1.png)
**주변 편의시설**
![](./images/5.png)
**지적편집도 / 지형**
![](./images/6.png)
**지도 기반 필터링**
![](./images/7.png)
**실거래가 정보 및 주변 상세 정보**
![](./images/8.png)
![](./images/9.png)
**길찾기**
![](./images/10.png)

---

### 📬 1:1 문의
- 문의 작성/조회/수정/삭제
- 이메일 알림 발송
- 파일 첨부 기능 (추후 확장 가능)

**1:1 문의**
![](./images/11.png)
**자주 묻는 질문**
![](./images/12.png)
**공지사항**
![](./images/13.png)

---

### ⭐ 관심 목록 (Interest)
- 최근 열람 및 북마크 관리 (아파트, 동네)
- 북마크 상태 확인 및 삭제
- 로컬 스토리지 ↔ DB 동기화

**최근 본 목록**
![](./images/14.png)
**찜**
![](./images/15.png)

---

### 📝 리뷰 기능
- 리뷰 작성 (이미지 첨부 가능)
- 리뷰 조회 (아파트별/사용자별/평점 필터)
- 리뷰 수정/삭제
- 업로드 이미지 조회 (URL 반환)

**마이페이지 / 리뷰**
![](./images/17.png)
![](./images/18.png)

---

### 👤 사용자 기능 (User)
- 회원가입, 프로필 수정, 비밀번호 암호화
- 소셜 로그인 (카카오 OAuth)
- 세션 기반 로그인/로그아웃
- 이메일 인증 (코드 전송 및 검증)

![](./images/19.png)
![](./images/20.png)

---

## 🧱 기술 스택

| 구분        | 기술                                                   |
|-------------|--------------------------------------------------------|
| Backend     | Spring Boot, MySQL, Redis                              |
| Frontend    | Vue 3, Vite, Axios, Chart.js, Kakao Map API            |
| AI Server   | FastAPI (Python), OpenAI API, Pinecone                 |
| 인증/보안   | Spring Security, OAuth2, Email 인증                    |
| 기타 도구   | Docker, GitHub Actions or Jenkins (선택)               |

---

## Use Case Diagram
![](./images/apt_diagram.png)

## ERD
![](./images/APT_ERD.png)

## 클래스 다이어그램
**AI**
![](./images/ai.png)
**APT**
![](./images/apt.png)
**Board**
![](./images/board.png)
**Interest**
![](./images/interest.png)
**Review**
![](./images/review.png)
**User**
![](./images/user.png)
