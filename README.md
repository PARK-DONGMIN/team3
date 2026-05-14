# 🚴 Travel Leaf — 자전거 여행 일정 생성 AI 서비스

> 자전거 여행 계획을 AI가 자동으로 생성해주고, 커뮤니티를 통해 여행 정보를 공유할 수 있는 플랫폼입니다.
> LLM 기반 게시글 추천, 욕설 자동 감지, 게시글 요약 등 AI 기능을 갖춘 5인 팀 프로젝트입니다.

🔗 **프론트엔드 레포:** [team3_react](https://github.com/PARK-DONGMIN/team3_react)
📅 **개발 기간:** 2025.11.13 ~ 2026.02.12 (13주, 5인 팀 프로젝트)

---

## 📌 주요 기능

- **자전거 여행 일정 추천** — AI 기반 여행 계획 자동 생성
- **커뮤니티 게시판** — 게시글 CRUD, 댓글 관리, 좋아요·신고 기능
- **LLM 기반 AI 기능** — 게시글 유사도 분석 및 추천, 자동 태그 생성, 게시글 요약
- **욕설·스팸 자동 감지** — 댓글·게시글 내 부적절한 내용 자동 신고
- **사용자 맞춤 추천** — 좋아요·즐겨찾기 활동 기반 관심 게시글 추천
- **AI 이미지 분석** — 사진 속 장소 추정 기능
- **날씨 분석** — 날짜별 날씨 정보 제공
- **공지사항** — 관리자 공지 등록, 상단 고정, 카테고리 분류

---

## 🛠 기술 스택

### Backend
| 분류 | 기술 |
|------|------|
| Language | Java 21 |
| Framework | Spring Boot 3 |
| 인증 | Spring Security |
| ORM | JPA |
| DB | Oracle 18c XE, MySQL |

### AI / Model
| 분류 | 기술 |
|------|------|
| Language | Python |
| Framework | FastAPI |
| LLM | Ollama 기반 LLM 모델 |
| 인프라 | NVIDIA H200 GPU 서버 |

### Frontend
| 분류 | 기술 |
|------|------|
| Language | HTML5, CSS3, JavaScript (ES6) |
| Framework | React, Bootstrap |

---

## 🏗 시스템 아키텍처

```
사용자
  │
  └── Spring Boot (웹 서버)
        ├── Oracle / MySQL (DB)
        ├── FastAPI (AI 서버) ──── NVIDIA H200 GPU
        │     └── Ollama LLM 모델
        └── OpenAI API (이미지 분석, 요약 등)
```

> AI 모델 서버를 별도 FastAPI 서비스로 분리하여 웹 서버와 API 방식으로 통신,
> GPU 가속을 활용한 추론 성능 최적화

---

## 📁 프로젝트 구조

```
src/main/java/dev/jpa/
├── ai/                        # AI 기능 (일정 추천, 임베딩, 요약, 욕설 감지)
│   ├── moderation/            # 욕설·스팸 자동 감지
│   ├── summary/               # 게시글 요약
│   └── user_profile_embedding/ # 사용자 활동 기반 추천
├── posts/                     # 게시글 CRUD
├── comments/                  # 댓글 관리
├── comments_reports/          # 댓글 신고
├── tag/                       # 자동 태그 생성
├── admin/                     # 관리자 기능
├── survey/                    # 설문
└── user/                      # 사용자 관리
```

---

## 👨‍💻 담당 역할 (박동민)

- 데이터 아키텍트 — ERD 설계 및 데이터 구조 정의
- NVIDIA H200 GPU 서버에서 Ollama 기반 LLM 모델 배포
- 커뮤니티 관련 테이블 CRUD 구현 (게시글, 댓글, 좋아요, 신고)
- LLM 기반 기능 구현
  - 게시글 유사도 분석 및 추천
  - 게시글 자동 태그 생성
  - 댓글·게시글 욕설 감지 및 자동 신고
- LLM 활용 데이터 테이블 설계
  - `POSTS_QUALITY` — 게시글 품질 평가
  - `POSTS_SUMMARY` — 게시글 요약 저장
  - `USER_PROFILE_EMBEDDINGS` — 사용자 활동 기반 추천 시스템
- Spring Boot 기반 REST API 설계 및 구현


---

## 👨‍💻 개발자

| 항목 | 내용 |
|------|------|
| 이름 | 박동민 |
| 이메일 | pdm6547@naver.com |
| GitHub | [@PARK-DONGMIN](https://github.com/PARK-DONGMIN) |
