# TeamTodo - 팀 프로젝트 일정 관리 사이트

<div align="center">
  <img src="https://github.com/k-risu/1st-teamproject/raw/main/public/logo400x400.png" alt="TeamTodo Logo" />
</div>

## | 프로젝트 소개

Spring + React.js 를 활용한 프로젝트 단위의 일정 관리 사이트입니다.
팀장과 팀원으로 구성된 프로젝트 일정을 생성하고 팀원 개개인의 일정을 관리할 수 있습니다.

## <font color="#ff0000">|</font> 개발 인원

#### FRONT-END
- 이한샘 : 홈(달력), 프로젝트 화면
- 이유석 : 로그인, 이메일 인증, 마이페이지
- 김기동 : 회원가입, 팀원 상세, 오류페이지

#### BACK-END
- 윤정욱 : 이메일 인증, 홈
	- 명세서 작성, 코드 검토 및 수정
- 이정민 : 회원관리 CRUD (회원가입, 로그인, 마이페이지)
- 류현욱 : 프로젝트 생성, 삭제
- 김수지 : 프로젝트의 일정 CRUD
	- ERD 작성, DB 관리
## <font color="#ff0000">|</font> 개발 환경

#### FRONT_END
![React](https://img.shields.io/badge/react-61DAFB?style=flat-square&logo=react&logoColor=white)
![axios](https://img.shields.io/badge/axios-5A29E4?style=flat-square&logo=axios&logoColor=white)

#### BACK_END

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat-square&logo=spring-boot&logoColor=white)
![MariaDB](https://img.shields.io/badge/MariaDB-003545?style=flat-square&logo=mariadb&logoColor=white)
![Mybatis](https://img.shields.io/badge/Mybatis-891B26?style=flat-square&logo=mybatis&logoColor=black)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=gradle&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=flat-square&logo=swagger&logoColor=black)

공통 : github, Notion, slack, swagger, docker

## | 진행 일정
![image](https://github.com/user-attachments/assets/fc43ad98-45b6-415f-8a3e-264ed5a7ee9c)

#### Phase 1: 기획 및 설계 (2024.0?)

- 요구사항 분석 및 정의
- 데이터베이스 설계
- UI/UX 디자인
- 기술 스택 선정

#### Phase 2: 개발 (2024.0? - 2024.0?)

- 백엔드 API 개발
- 프론트엔드 구현
- 데이터베이스 구축
- 단위 테스트 및 통합 테스트

#### Phase 3: 테스트 및 배포 (2024.0?)

- 사용자 테스트
- 버그 수정 및 성능 최적화
- 서비스 배포
- 모니터링 시스템 구축

## <font color="#ff0000">|</font> 주요 기능

#### 도메인
> http://112.222.157.157:5213/

#### 회원가입
> 이메일 인증을 통한 회원가입. 

> 인증 코드 발송 이후 서버 메모리에 저장. 스레드를 분리하여 3분간의 대기시간 설정. 대기 시간 만료 이후 메모리에서 인증코드 삭제.

> 아이디 찾기와 비밀번호 찾기도 이메일 인증으로 가능.

#### 홈 화면
> 로그인 유저의 프로젝트 일정을 달력에 표시.

> 표시된 프로젝트 클릭시 참여 유저 확인 가능. 유저 클릭 시 해당 유저의 프로필 상세 페이지로 이동.

#### 프로젝트
> 우측 상단 버튼을 통해 프로젝트 생성가능.

> 프로젝트에 추가할 유저는 닉네임으로 검색.

> 생성된 프로젝트는 좌측 사이드바 프로젝트 버튼을 통해 확인 가능

> 프로젝트 대시보드에서 완료처리, 삭제 가능

#### 일정
> 프로젝트 구성원 화면에서 팀원들의 일정 확인 가능

> 팀원 본인 및 팀장은 팀원의 일정을 추가, 삭제 가능

> 생성되어있는 일정 완료 시 마다 프로젝트 진행률 상승

## | 기대 효과
1. 효율적인 팀 프로젝트 관리
2. 실시간 진행 상황 파악
3. 팀원간 원활한 커뮤니케이션
4. 체계적인 일정 관리


## | 향후 개선사항
1. 실시간 알림 기능 추가
2. 모바일 앱 개발
3. 프로젝트 템플릿 기능
4. 통계 및 리포트 기능
5. 파일 공유 시스템 개선

## | 디렉터리 구조

```
├─main
│  ├─java
│  │  └─p1
│  │      └─teamtodo
│  │          │  TeamtodoApplication.java
│  │          │
│  │          ├─common
│  │          │  │  MyFileUtils.java
│  │          │  │  ResponseCode.java
│  │          │  │  ResponseResult.java
│  │          │  │  SwaggerConfiguration.java
│  │          │  │  WebMvcConfig.java
│  │          │  │
│  │          │  ├─config
│  │          │  │      OctetStreamReadMsgConverter.java
│  │          │  │      WebConfig.java
│  │          │  │
│  │          │  └─exception
│  │          │          BadRequestExceptionHandler.java
│  │          │
│  │          ├─home
│  │          │  │  HomeController.java
│  │          │  │  HomeMapper.java
│  │          │  │  HomeService.java
│  │          │  │
│  │          │  └─model
│  │          │      │  UserNoAndPicDto.java
│  │          │      │
│  │          │      └─res
│  │          │              HomeGetRes.java
│  │          │              MembersPicGetRes.java
│  │          │
│  │          ├─mail
│  │          │  │  GetEmailAndCodeReq.java
│  │          │  │  MailController.java
│  │          │  │  MailHandler.java
│  │          │  │  MailService.java
│  │          │  │
│  │          │  └─thread
│  │          │          AuthCode.java
│  │          │          MailCheck.java
│  │          │
│  │          ├─project
│  │          │  │  ProjectController.java
│  │          │  │  ProjectMapper.java
│  │          │  │  ProjectService.java
│  │          │  │
│  │          │  └─model
│  │          │      ├─dto
│  │          │      │      ProjectDetailDto.java
│  │          │      │      ProjectEditDto.java
│  │          │      │      ProjectEditUserDto.java
│  │          │      │      ProjectHomeDto.java
│  │          │      │      ProjectListDto.java
│  │          │      │      ProjectSearchUserDto.java
│  │          │      │
│  │          │      ├─req
│  │          │      │      ProjectCreatePostReq.java
│  │          │      │      ProjectDeleteReq.java
│  │          │      │      ProjectEditPutReq.java
│  │          │      │      ProjectListPaging.java
│  │          │      │      ProjectUserEdit.java
│  │          │      │      ProjectUserLockReq.java
│  │          │      │
│  │          │      └─res
│  │          │              ProjectDetailGetRes.java
│  │          │              ProjectEditGetRes.java
│  │          │              ProjectListGetRes.java
│  │          │              ProjectSearchUserGetRes.java
│  │          │
│  │          ├─schedule
│  │          │  │  ScheduleController.java
│  │          │  │  ScheduleMapper.java
│  │          │  │  ScheduleService.java
│  │          │  │
│  │          │  └─model
│  │          │      ├─dto
│  │          │      │      GetLeaderNoAndScheduledNoDto.java
│  │          │      │      ScheduleDto.java
│  │          │      │
│  │          │      ├─req
│  │          │      │      DeleteSchedule.java
│  │          │      │      ScheduleAddReq.java
│  │          │      │      SchedulePatch.java
│  │          │      │
│  │          │      └─res
│  │          │              ScheduleAddRes.java
│  │          │              ScheduleDetail.java
│  │          │
│  │          └─user
│  │              │  UserController.java
│  │              │  UserMapper.java
│  │              │  UserService.java
│  │              │
│  │              └─model
│  │                  │  UserNickname.java
│  │                  │
│  │                  ├─dto
│  │                  │      DuplicateCheckResult.java
│  │                  │      UserDto.java
│  │                  │      UserInfo.java
│  │                  │      UserLoginInfo.java
│  │                  │      UserProjectInfo.java
│  │                  │
│  │                  ├─req
│  │                  │      ChangePwReq.java
│  │                  │      EditUserPutReq.java
│  │                  │      SignUpReq.java
│  │                  │      UserInfoGetReq.java
│  │                  │      UserSignInReq.java
│  │                  │
│  │                  └─res
│  │                          FindUserIdGetRes.java
│  │                          UserInfoGetRes.java
│  │                          UserSignInRes.java
│  │
│  └─resources
│      │  application-prod.yaml
│      │  application.yaml
│      │  log4jdbc.log4j2.properties
│      │  logback-spring.xml
│      │  test.json
│      ├─mapper
│      │      HomeMapper.xml
│      │      ProjectMapper.xml
│      │      ScheduleMapper.xml
│      │      UserMapper.xml
│      ├─static
│      └─templates
└─test
    └─java
        └─p1
            └─teamtodo
                │  TeamtodoApplicationTests.java
                │
                ├─mail
                │      MailServiceTest.java
                │
                └─user
                        UserServiceTest.java
```
