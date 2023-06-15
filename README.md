# INFO_v2_Backend

| 서버 개발 & 데브옵스 | [qj0r9j0vc2](http://github.com/qj0r9j0vc2) |
| --- | --- |

<br/>
<br/>


It also preparing [OAuth2 service](https://github.com/qj0r9j0vc2/info-oauth2-server)
> **INFO 프로젝트**는 기존 **직접 담당자와 연락하여** 이루어지던 
*대덕sw마이스터고 현장 실습생 채용 의뢰를 전산화하여* 
소통 과정에서 발생하는 불필요한 연락을 제거하고, **웹 어플리케이션**으로 전환하여 **학교 산학부 담당자** 부재 시에도 **원활한 채용 프로세스 진행**이 가능하게 하기 위해 구상되었으며, `가용성` 및 `안정성` 확보를 기대할 수 있습니다.
>




1. 프로젝트 배경 💡
    
    > 🍃 기존 대덕sw마이스터고 현장실습생 채용 의뢰는
    > 
    > 
    > > "학교로 연락" -> "회사가 채용 의뢰서 작성" -> "각종 서류 검토 및 회사 담당자와 연락" -> "학생 연계"
    > > 
    > 
    >  위와 같은 구조로 이루어져있었습니다. 
    >  하지만 이 구조에서는 학교와 회사 인사 담당자 사이의 불필요한 연락🔥이 불가피하게 많아질 수밖에 없었으며, 🍃한 해에 채용 의뢰 희망 기업이 몇십 개 씩이나 존재하는 대덕sw마이스터고에서는🎨 나날이 증가하는 it 업계 규모와 더불어 졸업생들의 뛰어난 산업계에서의 직무 능력에서 비롯된 영향 등으로 인해 늘어나는 채용 의뢰 건수를 담당하기에 업무적으로 일부 어려움을 겪기도 하였습니다.
    > 
    >  이에, 이러한 기존 문제를 해결하고자 회사에서 학교로 이어지는 채용 의뢰 프로세스💡를 웹 어플리케이션🍃으로 전환하여 서비스의 가용성과 안정성을 높일 뿐 아니라, 기존 교내 프로젝트와의 다양한 협업을 도모하여 추후 발전할 가능성을 염두 해두고 있습니다.🔥
    > 
    
    > 많은 학생들이 학교로 채용 의뢰를 한 기업을 조회하기 위해 산학 협력부 외벽에 부착된 채용 공고를 확인하지만, 산학부의 경우 다른 부서와 다르게 1층에 단독으로 위치해있어 학생들이 다가가기 어려울 뿐만 아니라 거리상으로도 주변에 학생이 이용하는 장소가 다른 교무실에 비해 현저히 적기에 접근성 또한 떨어집니다.💡
     이에, 학생들로 하여금 채용을 의뢰한 기업들⭐️을 확인하기 쉽고, 신규 채용 공고를 확인하기 용이하도록 웹 어플리케이션으로 서비스를 제공하여 브라우저를 통해 손쉽게 이용할 수 있도록 합니다.🔮
    > 
2. 서비스 대상 🔥
    1. 대덕 sw 마이스터고 산학 협력부
        1. 교사 분들의 채용 과정에서의 복잡성을 해소하고, 학생, 회사 간의 연계성을 확대하여 업무 부담 최소화.
    2. 대덕 sw 마이스터고 재학생
        1. 취업이 눈앞에 다가온 3학년 재학생 분들이 신규 기업체를 확인하기 위하여, 또는 본인의 적성에 부합하는 회사를 찾기 위하여 매번 산학 협력부 공고를 확인하러 다른 층을 방문하는 비효율 제거.
    3. 채용 의뢰 희망 기업체
        1. 채용 의뢰를 위하여 직접 연락을 하고, 필요한 정보가 무엇인지 등 사람 대 사람으로써 이루어지는 작업을 시스템화 하여 프로그램으로 처리하도록 함으로써 사람에 의해 발생할 수 있는 문제에 대한 부담을 제거하고, 학교 업무시간에 대한 제약 없이 언제든지 채용 의뢰 관련 작업을 수행할 수 있도록 함.
        
        
        
<img width="1728" alt="info_main_4" src="https://user-images.githubusercontent.com/59428479/227166346-ccb45b9f-bd60-40e0-9b39-1b229bbbed6d.png">
<img width="1727" alt="info_main_1" src="https://user-images.githubusercontent.com/59428479/227166209-7eb04518-7cbc-40fa-bf8c-e86ff620cae6.png">
<img width="1728" alt="info_main_2" src="https://user-images.githubusercontent.com/59428479/227166267-b222d401-14ba-4bd9-9a2e-f46e7c94f274.png">
<img width="1728" alt="info_main_3" src="https://user-images.githubusercontent.com/59428479/227166291-72b93231-163e-4478-996c-038d112f0bd8.png">






 INFO Project Backend 서버입니다. 기존, 모놀리식 아키텍처를 채택하여 빠르게 구성한 INFO_v1_Backend 서버를 MSA, Hexagonal, DDD로 설계하려 노력했습니다. 






# Event Storming
---
![INFO 프로젝트 Event Storming](https://user-images.githubusercontent.com/59428479/202425081-ae43e6c8-35bc-42a1-be51-0f4416fc4bad.jpg)



# MSA
---

![INFO_v2_Backened_Architecture](https://user-images.githubusercontent.com/59428479/202425324-034b440d-3499-4e41-ac2f-3bba6453547a.png)



- 서비스를 유저, 권한, 채용, 모집공고, 이메일, 회사, 파일 등으로 나누어 분리합니다.
- Auth 서비스에서 Login 로직을 구현하여 토큰을 발행하고, API Gateway에서 Token을 파싱하여 헤더에 값을 추가하여 각 서비스에서 권한을 검증하고, 현재 사용자의 정보를 참조합니다.

# Hexagonal(포트 및 어댑터 아키텍처)
---

<img width="873" alt="Hexagonal Architecture" src="https://user-images.githubusercontent.com/59428479/202425478-fe91b4d3-a83e-4668-b6fe-b7ff4ab3756b.png">



- SOLID 원칙 준수의 용이
- 계층 별 의존성 최소화
    - 비즈니스 계층이 표현 계층이나 데이터 액세스 로직에 의존하지 않도록함.
    
    
    
    
    
    
 작성자: 안진우(https://github.com/qj0r9j0vc2)

```
📦 
├─ .github
│  ├─ labeler.yml
│  ├─ release-drafter-announcement.yml
│  ├─ release-drafter-applies.yml
│  ├─ release-drafter-auth.yml
│  ├─ release-drafter-company.yml
│  ├─ release-drafter-email.yml
│  ├─ release-drafter-employment.yml
│  ├─ release-drafter-file.yml
│  ├─ release-drafter-notice.yml
│  ├─ release-drafter-user.yml
│  └─ workflows
│     ├─ labeler.yml
│     ├─ release-drafter-demo.yml
│     └─ release-drafter.yml
├─ .gitignore
├─ README.md
├─ announcement
│  ├─ Dockerfile
│  ├─ build.gradle.kts
│  └─ src
│     └─ main
│        ├─ kotlin
│        │  └─ com
│        │     └─ info
│        │        └─ info_v2_backend
│        │           └─ announcement
│        │              ├─ AnnouncementApplication.kt
│        │              ├─ business
│        │              │  └─ service
│        │              │     ├─ AnnouncementService.kt
│        │              │     └─ AnnouncementServiceImpl.kt
│        │              ├─ global
│        │              │  └─ exception
│        │              │     └─ AnnouncementExceptionHandler.kt
│        │              ├─ infra
│        │              │  └─ feign
│        │              │     ├─ FileFeignClient.kt
│        │              │     ├─ FileFeignClientFallback.kt
│        │              │     └─ config
│        │              │        ├─ FeignConfiguration.kt
│        │              │        └─ FeignErrorDecoder.kt
│        │              ├─ persistance
│        │              │  ├─ entity
│        │              │  │  ├─ Announcement.kt
│        │              │  │  └─ AnnouncementType.kt
│        │              │  └─ repository
│        │              │     └─ AnnouncementRepository.kt
│        │              └─ presentation
│        │                 ├─ AnnouncementController.kt
│        │                 └─ dto
│        │                    ├─ request
│        │                    │  └─ CreateAnnouncementRequest.kt
│        │                    └─ response
│        │                       ├─ MaximumAnnouncementResponse.kt
│        │                       └─ MinimumAnnouncementResponse.kt
│        └─ resources
│           └─ application.yml
├─ apiGateway
│  ├─ Dockerfile
│  ├─ build.gradle.kts
│  └─ src
│     └─ main
│        └─ kotlin
│           └─ com
│              └─ info
│                 └─ info_v2_backend
│                    └─ apiGateway
│                       ├─ ApiGatewayApplication.kt
│                       ├─ exception
│                       │  └─ GatewayExceptionHandler.kt
│                       ├─ filter
│                       │  └─ GlobalFilter.kt
│                       └─ property
│                          └─ JwtProperty.kt
├─ applies
│  ├─ Dockerfile
│  ├─ build.gradle.kts
│  └─ src
│     └─ main
│        ├─ kotlin
│        │  └─ com
│        │     └─ info
│        │        └─ info_v2_backend
│        │           └─ applies
│        │              ├─ AppliesApplication.kt
│        │              ├─ adapter
│        │              │  ├─ input
│        │              │  │  └─ rest
│        │              │  │     ├─ AppliesController.kt
│        │              │  │     ├─ configuration
│        │              │  │     │  └─ AppliesExceptionHandler.kt
│        │              │  │     └─ dto
│        │              │  │        ├─ request
│        │              │  │        │  └─ RejectAppliesRequest.kt
│        │              │  │        └─ respnose
│        │              │  │           └─ AppliesResponse.kt
│        │              │  └─ output
│        │              │     ├─ event
│        │              │     │  ├─ UpdateNoticeProducer.kt
│        │              │     │  └─ configuration
│        │              │     │     ├─ KafkaConfiguration.kt
│        │              │     │     └─ KafkaProperty.kt
│        │              │     ├─ persistence
│        │              │     │  ├─ AppliesPersistenceAdapter.kt
│        │              │     │  └─ repository
│        │              │     │     └─ AppliesRepository.kt
│        │              │     └─ rest
│        │              │        ├─ CompanyFeignClient.kt
│        │              │        ├─ CompanyFeignClientFallbackFactory.kt
│        │              │        ├─ FileFeignClient.kt
│        │              │        ├─ FileFeignClientFallback.kt
│        │              │        ├─ NoticeFeignClient.kt
│        │              │        ├─ NoticeFeignClientFallback.kt
│        │              │        ├─ UserFeignClient.kt
│        │              │        ├─ UserFeignClientFallback.kt
│        │              │        └─ configuration
│        │              │           ├─ FeignConfiguration.kt
│        │              │           └─ FeignErrorDecoder.kt
│        │              ├─ application
│        │              │  ├─ port
│        │              │  │  ├─ input
│        │              │  │  │  ├─ ApplyAppliesUsecase.kt
│        │              │  │  │  ├─ ApproveAppliesUsecase.kt
│        │              │  │  │  ├─ CancelApplyUsecase.kt
│        │              │  │  │  ├─ LoadAppliesUsecase.kt
│        │              │  │  │  └─ RejectAppliesUsecase.kt
│        │              │  │  └─ output
│        │              │  │     ├─ applies
│        │              │  │     │  ├─ CancelApplyPort.kt
│        │              │  │     │  ├─ LoadAppliesPort.kt
│        │              │  │     │  └─ SaveAppliesPort.kt
│        │              │  │     ├─ company
│        │              │  │     │  └─ LoadCompanyPort.kt
│        │              │  │     ├─ notice
│        │              │  │     │  ├─ LoadNoticePort.kt
│        │              │  │     │  ├─ UpdateNoticeAppliesCountPort.kt
│        │              │  │     │  └─ UpdateNoticePort.kt
│        │              │  │     ├─ resume
│        │              │  │     │  └─ ResumePort.kt
│        │              │  │     └─ student
│        │              │  │        └─ LoadStudentPort.kt
│        │              │  └─ service
│        │              │     ├─ ApplyApplies.kt
│        │              │     ├─ ApproveApplies.kt
│        │              │     ├─ CancelApply.kt
│        │              │     ├─ LoadApplies.kt
│        │              │     └─ RejectApplies.kt
│        │              └─ domain
│        │                 ├─ Applies.kt
│        │                 ├─ company
│        │                 │  └─ AppliesCompany.kt
│        │                 ├─ notice
│        │                 │  └─ AppliesNotice.kt
│        │                 ├─ time
│        │                 │  └─ TimeEntity.kt
│        │                 └─ user
│        │                    └─ Applicant.kt
│        └─ resources
│           └─ application.yml
├─ auth
│  ├─ Dockerfile
│  ├─ build.gradle.kts
│  └─ src
│     └─ main
│        ├─ kotlin
│        │  └─ com
│        │     └─ info
│        │        └─ info_v2_backend
│        │           └─ auth
│        │              ├─ AuthApplication.kt
│        │              ├─ adapter
│        │              │  ├─ input
│        │              │  │  └─ rest
│        │              │  │     ├─ AuthController.kt
│        │              │  │     ├─ configuration
│        │              │  │     │  └─ AuthExceptionHandler.kt
│        │              │  │     ├─ dto
│        │              │  │     │  ├─ request
│        │              │  │     │  │  ├─ ChangePasswordRequest.kt
│        │              │  │     │  │  ├─ CheckTokenExpiredTimeRequest.kt
│        │              │  │     │  │  ├─ LoginCompanyRequest.kt
│        │              │  │     │  │  ├─ LoginRequest.kt
│        │              │  │     │  │  ├─ SaveStudentRequest.kt
│        │              │  │     │  │  └─ TokenReissueRequest.kt
│        │              │  │     │  └─ response
│        │              │  │     │     └─ TokenResponse.kt
│        │              │  │     └─ vaildation
│        │              │  │        ├─ SchoolEmail.kt
│        │              │  │        └─ SchoolEmailValidator.kt
│        │              │  └─ output
│        │              │     ├─ event
│        │              │     │  ├─ SaveUserAdapter.kt
│        │              │     │  ├─ SendEmailAdapter.kt
│        │              │     │  └─ configuration
│        │              │     │     ├─ KafkaConfiguration.kt
│        │              │     │     └─ KafkaProperty.kt
│        │              │     ├─ persisetnece
│        │              │     │  ├─ CodePersistenceAdapter.kt
│        │              │     │  └─ repository
│        │              │     │     └─ CodeRepository.kt
│        │              │     └─ rest
│        │              │        ├─ UserFeignClient.kt
│        │              │        ├─ UserFeignClientFallback.kt
│        │              │        └─ configuration
│        │              │           ├─ FeignConfiguration.kt
│        │              │           └─ FeignErrorDecoder.kt
│        │              ├─ application
│        │              │  ├─ configuration
│        │              │  │  └─ SecurityConfiguration.kt
│        │              │  ├─ env
│        │              │  │  ├─ AuthenticationCodeProperty.kt
│        │              │  │  └─ JwtProperty.kt
│        │              │  ├─ port
│        │              │  │  ├─ input
│        │              │  │  │  ├─ ChangePasswordUsecase.kt
│        │              │  │  │  ├─ CheckCodeUsecase.kt
│        │              │  │  │  ├─ CheckTokenExpiredTimeUsecase.kt
│        │              │  │  │  ├─ LoadPasswordHintUsecase.kt
│        │              │  │  │  ├─ LoginUsecase.kt
│        │              │  │  │  ├─ ReissueUsecase.kt
│        │              │  │  │  ├─ SendAuthenticationCodeUsecase.kt
│        │              │  │  │  ├─ StudentSignupUsecase.kt
│        │              │  │  │  └─ TeacherSignupUsecase.kt
│        │              │  │  └─ output
│        │              │  │     ├─ LoadCodePort.kt
│        │              │  │     ├─ RemoveCodePort.kt
│        │              │  │     ├─ SaveCodePort.kt
│        │              │  │     ├─ SaveUserPort.kt
│        │              │  │     ├─ SendEmailPort.kt
│        │              │  │     └─ userFeignPort
│        │              │  │        ├─ ChangePasswordPort.kt
│        │              │  │        ├─ LoadContactorPort.kt
│        │              │  │        ├─ LoadUserDetailsPort.kt
│        │              │  │        └─ UserFeignPort.kt
│        │              │  └─ service
│        │              │     ├─ AuthDetailsService.kt
│        │              │     ├─ ChangePassword.kt
│        │              │     ├─ CheckCode.kt
│        │              │     ├─ CheckTokenExpiredTime.kt
│        │              │     ├─ Login.kt
│        │              │     ├─ Reissue.kt
│        │              │     ├─ SendAuthenticationCode.kt
│        │              │     ├─ Signup.kt
│        │              │     └─ TokenProvider.kt
│        │              └─ domain
│        │                 └─ Code.kt
│        └─ resources
│           └─ application.yml
├─ build.gradle.kts
├─ comment
│  └─ src
│     └─ main
│        └─ kotlin
│           └─ com
│              └─ info
│                 └─ info_v2_backend
│                    └─ comment
│                       └─ CommentApplication.kt
├─ common
│  ├─ build.gradle.kts
│  └─ src
│     └─ main
│        └─ kotlin
│           └─ com
│              └─ info
│                 └─ info_v2_backend
│                    └─ common
│                       ├─ applies
│                       │  ├─ AppliesDto.kt
│                       │  └─ AppliesStatus.kt
│                       ├─ auth
│                       │  ├─ Auth.kt
│                       │  ├─ AuthenticationCodeDto.kt
│                       │  ├─ AuthenticationCodeType.kt
│                       │  └─ HeaderProperty.kt
│                       ├─ company
│                       │  └─ CompanyDto.kt
│                       ├─ email
│                       │  ├─ EmailTemplateType.kt
│                       │  └─ dto
│                       │     ├─ SendEmailNotificationRequest.kt
│                       │     └─ SendEmailTextRequest.kt
│                       ├─ employment
│                       │  ├─ EmploymentDto.kt
│                       │  └─ EmploymentStatus.kt
│                       ├─ event
│                       │  └─ KafkaTopics.kt
│                       ├─ exception
│                       │  ├─ BusinessException.kt
│                       │  ├─ ErrorCode.kt
│                       │  └─ ErrorResponse.kt
│                       ├─ file
│                       │  ├─ FileConvert.kt
│                       │  ├─ FileProperty.kt
│                       │  └─ dto
│                       │     ├─ CompanyFileClassificationType.kt
│                       │     ├─ FileDto.kt
│                       │     ├─ RegisterCompanyFileDto.kt
│                       │     ├─ UploadCompanyFileDto.kt
│                       │     ├─ request
│                       │     │  ├─ GenerateFileListRequest.kt
│                       │     │  └─ GenerateFileRequest.kt
│                       │     ├─ response
│                       │     │  ├─ AnnouncementFileResponse.kt
│                       │     │  ├─ AttachmentResponse.kt
│                       │     │  ├─ CompanyFileResponse.kt
│                       │     │  ├─ FileResponse.kt
│                       │     │  ├─ PresignedUrlListResponse.kt
│                       │     │  ├─ PresignedUrlResponse.kt
│                       │     │  └─ UserFileResponse.kt
│                       │     └─ type
│                       │        ├─ DocsExt.kt
│                       │        ├─ FileType.kt
│                       │        └─ ImageExt.kt
│                       ├─ filter
│                       │  └─ ExceptionFilter.kt
│                       ├─ logs
│                       │  └─ LogFormat.kt
│                       ├─ notice
│                       │  ├─ NoticeDto.kt
│                       │  ├─ NoticeFileDto.kt
│                       │  └─ UpdateNoticeAppliesCountDto.kt
│                       └─ user
│                          ├─ ContactorDto.kt
│                          ├─ Generation.kt
│                          ├─ Role.kt
│                          ├─ StudentDto.kt
│                          ├─ TeacherDto.kt
│                          └─ UserDto.kt
├─ company
│  ├─ Dockerfile
│  ├─ build.gradle.kts
│  └─ src
│     └─ main
│        ├─ kotlin
│        │  └─ com
│        │     └─ info
│        │        └─ info_v2_backend
│        │           └─ company
│        │              ├─ CompanyApplication.kt
│        │              ├─ adapter
│        │              │  ├─ configuration
│        │              │  │  ├─ KafkaConfiguration.kt
│        │              │  │  └─ KafkaProperty.kt
│        │              │  ├─ input
│        │              │  │  ├─ event
│        │              │  │  │  └─ UpdateCompanyLastNoticedConsumer.kt
│        │              │  │  └─ web
│        │              │  │     └─ rest
│        │              │  │        ├─ CompanyController.kt
│        │              │  │        ├─ configuration
│        │              │  │        │  └─ CompanyExceptionHandler.kt
│        │              │  │        └─ dto
│        │              │  │           ├─ request
│        │              │  │           │  ├─ edit
│        │              │  │           │  │  ├─ EditCompanyInformationRequest.kt
│        │              │  │           │  │  ├─ EditCompanyRequest.kt
│        │              │  │           │  │  └─ EditContactorRequest.kt
│        │              │  │           │  ├─ interviewReview
│        │              │  │           │  │  ├─ EditInterviewReviewRequest.kt
│        │              │  │           │  │  └─ WriteInterviewReviewRequest.kt
│        │              │  │           │  └─ register
│        │              │  │           │     ├─ CompanyContactRequest.kt
│        │              │  │           │     ├─ CompanyInformationRequest.kt
│        │              │  │           │     ├─ CompanyNameRequest.kt
│        │              │  │           │     └─ RegisterCompanyRequest.kt
│        │              │  │           └─ response
│        │              │  │              ├─ CompanyIntroductionResponse.kt
│        │              │  │              ├─ MaximumCompanyResponse.kt
│        │              │  │              ├─ MinimumCompanyResponse.kt
│        │              │  │              └─ interviewReview
│        │              │  │                 ├─ MaximumInterviewReviewResponse.kt
│        │              │  │                 └─ MinimumInterviewReviewRespone.kt
│        │              │  └─ output
│        │              │     ├─ event
│        │              │     │  └─ UserAdapter.kt
│        │              │     ├─ persistence
│        │              │     │  ├─ mongo
│        │              │     │  │  ├─ MongoCompanyDocumentAdapter.kt
│        │              │     │  │  ├─ configuration
│        │              │     │  │  │  ├─ DataSourceProperty.kt
│        │              │     │  │  │  └─ MongoConfiguration.kt
│        │              │     │  │  └─ repository
│        │              │     │  │     └─ CompanyDocumentRepository.kt
│        │              │     │  └─ rdb
│        │              │     │     ├─ BusinessAreaAdapter.kt
│        │              │     │     ├─ BusinessAreaTaggedAdapter.kt
│        │              │     │     ├─ CompanyAdapter.kt
│        │              │     │     ├─ CompanyClassificationPreferenceAdapter.kt
│        │              │     │     ├─ InterviewReviewAdapter.kt
│        │              │     │     └─ repository
│        │              │     │        ├─ BusinessAreaRepository.kt
│        │              │     │        ├─ BusinessAreaTaggedRepository.kt
│        │              │     │        ├─ CompanyClassificationPreferenceRepository.kt
│        │              │     │        ├─ CompanyRepository.kt
│        │              │     │        └─ InterviewReviewRepository.kt
│        │              │     └─ rest
│        │              │        ├─ AuthFeignClient.kt
│        │              │        ├─ AuthFeignClientFallbackFactory.kt
│        │              │        ├─ EmploymentFeignClient.kt
│        │              │        ├─ EmploymentFeignClientFalllback.kt
│        │              │        ├─ FileFeignClient.kt
│        │              │        ├─ FileFeignClientFallbackFactory.kt
│        │              │        ├─ NoticeFeignClient.kt
│        │              │        ├─ NoticeFeignClientFallbackFactory.kt
│        │              │        ├─ UserFeignClient.kt
│        │              │        ├─ UserFeingClientFallback.kt
│        │              │        └─ configuration
│        │              │           ├─ FeignConfiguration.kt
│        │              │           └─ FeignErrorDecoder.kt
│        │              ├─ application
│        │              │  ├─ configuration
│        │              │  │  └─ SecurityConfiguration.kt
│        │              │  ├─ port
│        │              │  │  ├─ input
│        │              │  │  │  ├─ businessArea
│        │              │  │  │  │  ├─ AddBusinessAreaUsecase.kt
│        │              │  │  │  │  └─ LoadBusinessAreaUsecase.kt
│        │              │  │  │  ├─ company
│        │              │  │  │  │  ├─ CountCompanyUsecase.kt
│        │              │  │  │  │  ├─ EditCompanyUsecase.kt
│        │              │  │  │  │  ├─ FailCompanyUsecase.kt
│        │              │  │  │  │  ├─ LoadCompanyUsecase.kt
│        │              │  │  │  │  ├─ MakeAssociatedUsecase.kt
│        │              │  │  │  │  ├─ MakeLeadingUsecase.kt
│        │              │  │  │  │  └─ RegisterCompanyUsecase.kt
│        │              │  │  │  ├─ file
│        │              │  │  │  │  ├─ AddCompanyFileUsecase.kt
│        │              │  │  │  │  ├─ ChangeCompanyFileUsecase.kt
│        │              │  │  │  │  └─ RemoveCompanyFileUsecase.kt
│        │              │  │  │  ├─ interviewReview
│        │              │  │  │  │  ├─ DeleteInterviewReviewUsecase.kt
│        │              │  │  │  │  ├─ EditInterviewReviewUsecase.kt
│        │              │  │  │  │  ├─ LoadInterviewReviewUsecase.kt
│        │              │  │  │  │  └─ WriteInterviewReviewUsecase.kt
│        │              │  │  │  ├─ notice
│        │              │  │  │  │  └─ UpdateLastNoticedCompanyUsecase.kt
│        │              │  │  │  └─ preference
│        │              │  │  │     ├─ LoadMyCompanyPreferenceInfoUsecase.kt
│        │              │  │  │     └─ SetCompanyClassificationPreferenceUsecase.kt
│        │              │  │  └─ output
│        │              │  │     ├─ CheckEmailCodePort.kt
│        │              │  │     ├─ businessArea
│        │              │  │     │  ├─ LoadBusinessAreaPort.kt
│        │              │  │     │  ├─ LoadBusinessAreaTaggedByCompanyNumberPort.kt
│        │              │  │     │  ├─ SaveBusinessAreaPort.kt
│        │              │  │     │  └─ SaveBusinessAreaTaggedPort.kt
│        │              │  │     ├─ company
│        │              │  │     │  ├─ LoadCompanyPort.kt
│        │              │  │     │  ├─ SaveCompanyPort.kt
│        │              │  │     │  ├─ SaveContactorPort.kt
│        │              │  │     │  └─ SearchCompanyPort.kt
│        │              │  │     ├─ employment
│        │              │  │     │  └─ LoadEmploymentPort.kt
│        │              │  │     ├─ file
│        │              │  │     │  └─ CompanyFilePort.kt
│        │              │  │     ├─ interviewReview
│        │              │  │     │  ├─ DeleteInterviewReviewPort.kt
│        │              │  │     │  ├─ LoadInterviewReviewPort.kt
│        │              │  │     │  └─ WriteInterviewReviewPort.kt
│        │              │  │     ├─ notice
│        │              │  │     │  └─ NoticePort.kt
│        │              │  │     ├─ preference
│        │              │  │     │  ├─ LoadCompanyClassificationPort.kt
│        │              │  │     │  └─ SaveCompanyClassificationPreferencePort.kt
│        │              │  │     └─ user
│        │              │  │        └─ LoadUserPort.kt
│        │              │  └─ service
│        │              │     ├─ businessArea
│        │              │     │  ├─ AddBusinessArea.kt
│        │              │     │  └─ LoadBusinessArea.kt
│        │              │     ├─ company
│        │              │     │  ├─ AddIntroductionFile.kt
│        │              │     │  ├─ ChangeBusinessCertificate.kt
│        │              │     │  ├─ CountCompany.kt
│        │              │     │  ├─ EditCompany.kt
│        │              │     │  ├─ FailCompany.kt
│        │              │     │  ├─ LoadCompany.kt
│        │              │     │  ├─ MakeAssociated.kt
│        │              │     │  ├─ MakeLeading.kt
│        │              │     │  ├─ RegisterCompany.kt
│        │              │     │  └─ RemoveCompanyFile.kt
│        │              │     ├─ interview
│        │              │     │  ├─ DeleteInterviewReview.kt
│        │              │     │  ├─ EditInterviewReview.kt
│        │              │     │  ├─ LoadInterviewReview.kt
│        │              │     │  └─ WriteInterviewReview.kt
│        │              │     ├─ notice
│        │              │     │  └─ UpdateLastNoticedCompany.kt
│        │              │     └─ preference
│        │              │        ├─ LoadMyCompanyPreferenceInfo.kt
│        │              │        └─ SetCompanyClassificationPreference.kt
│        │              └─ domain
│        │                 ├─ Company.kt
│        │                 ├─ ContactorId.kt
│        │                 ├─ businessArea
│        │                 │  ├─ BusinessArea.kt
│        │                 │  ├─ BusinessAreaTagged.kt
│        │                 │  └─ BusinessAreaTaggedIdClass.kt
│        │                 ├─ classification
│        │                 │  ├─ CompanyClassification.kt
│        │                 │  ├─ CompanyClassificationPreference.kt
│        │                 │  └─ CompanyClassificationPreferenceIdClass.kt
│        │                 ├─ document
│        │                 │  └─ CompanyDocument.kt
│        │                 ├─ information
│        │                 │  ├─ AddressInfo.kt
│        │                 │  └─ CompanyInformation.kt
│        │                 ├─ interview
│        │                 │  ├─ InterviewReview.kt
│        │                 │  ├─ InterviewReviewStudent.kt
│        │                 │  └─ InterviewType.kt
│        │                 ├─ introduction
│        │                 │  └─ CompanyIntroduction.kt
│        │                 ├─ name
│        │                 │  └─ CompanyName.kt
│        │                 ├─ status
│        │                 │  └─ CompanyCreationStatus.kt
│        │                 └─ time
│        │                    └─ TimeEntity.kt
│        └─ resources
│           └─ application.yml
├─ docker-compose.yml
├─ email
│  ├─ Dockerfile
│  ├─ build.gradle.kts
│  └─ src
│     └─ main
│        ├─ kotlin
│        │  └─ com
│        │     └─ info
│        │        └─ info_v2_backend
│        │           └─ email
│        │              ├─ EmailApplication.kt
│        │              ├─ adapter
│        │              │  ├─ input
│        │              │  │  ├─ event
│        │              │  │  │  ├─ adapter
│        │              │  │  │  │  └─ EmailConsumer.kt
│        │              │  │  │  └─ configuration
│        │              │  │  │     ├─ KafkaConfiguration.kt
│        │              │  │  │     └─ KafkaProperty.kt
│        │              │  │  └─ rest
│        │              │  │     ├─ EmailController.kt
│        │              │  │     └─ configuration
│        │              │  │        └─ EmailExceptionHandler.kt
│        │              │  └─ output
│        │              │     ├─ email
│        │              │     │  ├─ adapter
│        │              │     │  │  └─ SmtpSendAdapter.kt
│        │              │     │  └─ configuration
│        │              │     │     ├─ MailConfiguration.kt
│        │              │     │     └─ MailProperty.kt
│        │              │     ├─ event
│        │              │     │  └─ UserEventAdapter.kt
│        │              │     ├─ persistence
│        │              │     │  ├─ adapter
│        │              │     │  │  └─ EmailRecordPersistencePortAdapter.kt
│        │              │     │  └─ repository
│        │              │     │     └─ EmailRecordRepository.kt
│        │              │     └─ rest
│        │              │        ├─ CloudflareFeignClient.kt
│        │              │        ├─ FeignConfiguration.kt
│        │              │        ├─ UserFeignClient.kt
│        │              │        ├─ UserFiegnClientFallback.kt
│        │              │        ├─ configuration
│        │              │        │  └─ FeignErrorDecoder.kt
│        │              │        └─ dto
│        │              │           └─ CloudflareMailDto.kt
│        │              ├─ application
│        │              │  ├─ port
│        │              │  │  ├─ input
│        │              │  │  │  └─ SendEmailUsecase.kt
│        │              │  │  └─ output
│        │              │  │     ├─ EmailRecordPersistencePort.kt
│        │              │  │     ├─ LoadEmailUserPort.kt
│        │              │  │     └─ SmtpSendPort.kt
│        │              │  └─ service
│        │              │     └─ SendEmail.kt
│        │              └─ domain
│        │                 ├─ EmailRecord.kt
│        │                 ├─ EmailStatus.kt
│        │                 ├─ content
│        │                 │  ├─ EmailContent.kt
│        │                 │  └─ EmailDataConverter.kt
│        │                 ├─ time
│        │                 │  └─ TimeEntity.kt
│        │                 └─ user
│        │                    ├─ Sender.kt
│        │                    └─ Target.kt
│        └─ resources
│           ├─ application.yml
│           └─ templates
│              └─ notification.html
├─ employment
│  ├─ Dockerfile
│  ├─ build.gradle.kts
│  └─ src
│     └─ main
│        ├─ kotlin
│        │  └─ com
│        │     └─ info
│        │        └─ info_v2_backend
│        │           └─ employment
│        │              ├─ EmploymentApplication.kt
│        │              ├─ adapter
│        │              │  ├─ input
│        │              │  │  └─ rest
│        │              │  │     ├─ EmploymentController.kt
│        │              │  │     ├─ configuration
│        │              │  │     │  └─ EmploymentExceptionHandler.kt
│        │              │  │     └─ dto
│        │              │  │        ├─ request
│        │              │  │        │  └─ CreateGenerationGradeRequest.kt
│        │              │  │        └─ response
│        │              │  │           ├─ AnonymousEmploymentListResponse.kt
│        │              │  │           └─ EveryGenerationClassInformationResponse.kt
│        │              │  └─ output
│        │              │     ├─ persistence
│        │              │     │  ├─ EmploymentAdapter.kt
│        │              │     │  ├─ GenerationAdapter.kt
│        │              │     │  └─ repository
│        │              │     │     ├─ EmploymentRepository.kt
│        │              │     │     ├─ GenerationClassRepository.kt
│        │              │     │     └─ GenerationGradeRepository.kt
│        │              │     └─ rest
│        │              │        ├─ CompanyFeignClient.kt
│        │              │        ├─ CompanyFeignClientFallback.kt
│        │              │        ├─ UserFeignClient.kt
│        │              │        ├─ UserFeignClientFallback.kt
│        │              │        └─ configuration
│        │              │           ├─ FeignAuthConfiguration.kt
│        │              │           └─ FeignErrorDecoder.kt
│        │              ├─ application
│        │              │  ├─ port
│        │              │  │  ├─ input
│        │              │  │  │  ├─ ConfirmEmploymentUsecase.kt
│        │              │  │  │  ├─ CreateGenerationUsecase.kt
│        │              │  │  │  ├─ EmployStudentUsecase.kt
│        │              │  │  │  ├─ FailEmploymentUsecase.kt
│        │              │  │  │  ├─ LoadEmploymentUsecase.kt
│        │              │  │  │  └─ LoadGenerationUsecase.kt
│        │              │  │  └─ output
│        │              │  │     ├─ LoadCompanyPort.kt
│        │              │  │     ├─ LoadEmploymentPort.kt
│        │              │  │     ├─ LoadUserPort.kt
│        │              │  │     ├─ SaveEmploymentPort.kt
│        │              │  │     └─ generation
│        │              │  │        ├─ LoadGenerationPort.kt
│        │              │  │        └─ SaveGenerationPort.kt
│        │              │  └─ service
│        │              │     ├─ ConfirmEmployment.kt
│        │              │     ├─ CreateGeneration.kt
│        │              │     ├─ EmployStudent.kt
│        │              │     ├─ FailEmployment.kt
│        │              │     ├─ LoadEmployment.kt
│        │              │     └─ LoadGeneration.kt
│        │              └─ domain
│        │                 ├─ Employment.kt
│        │                 ├─ company
│        │                 │  ├─ EmploymentCompany.kt
│        │                 │  └─ EmploymentContactor.kt
│        │                 ├─ generation
│        │                 │  ├─ GenerationClass.kt
│        │                 │  ├─ GenerationClassIdClass.kt
│        │                 │  └─ GenerationGrade.kt
│        │                 ├─ student
│        │                 │  └─ EmployedStudent.kt
│        │                 └─ time
│        │                    └─ TimeEntity.kt
│        └─ resources
│           └─ application.yml
├─ eureka
│  ├─ Dockerfile
│  ├─ build.gradle.kts
│  └─ src
│     └─ main
│        ├─ kotlin
│        │  └─ com
│        │     └─ info
│        │        └─ info_v2_backend
│        │           └─ eureka
│        │              └─ EurekaApplication.kt
│        └─ resources
│           └─ application.yml
├─ file
│  ├─ Dockerfile
│  ├─ build.gradle.kts
│  └─ src
│     └─ main
│        ├─ kotlin
│        │  └─ com
│        │     └─ info
│        │        └─ info_v2_backend
│        │           └─ file
│        │              ├─ FileApplication.kt
│        │              ├─ adapter
│        │              │  ├─ input
│        │              │  │  └─ rest
│        │              │  │     └─ FileController.kt
│        │              │  └─ output
│        │              │     ├─ aws
│        │              │     │  ├─ S3Uploader.kt
│        │              │     │  └─ configuration
│        │              │     │     ├─ S3Configuration.kt
│        │              │     │     └─ S3Property.kt
│        │              │     ├─ event
│        │              │     │  ├─ ChangeCompanyStatusAdapter.kt
│        │              │     │  └─ configuration
│        │              │     │     ├─ KafkaConfiguration.kt
│        │              │     │     └─ KafkaProperty.kt
│        │              │     └─ persistence
│        │              │        ├─ AnnouncementFileAdapter.kt
│        │              │        ├─ AttachmentFileAdapter.kt
│        │              │        ├─ CompanyFileAdapter.kt
│        │              │        ├─ RemoveFileAdapter.kt
│        │              │        ├─ ResumeFileAdapter.kt
│        │              │        ├─ UserFileAdapter.kt
│        │              │        └─ repository
│        │              │           ├─ AnnouncementFileRepository.kt
│        │              │           ├─ AttachmentRepository.kt
│        │              │           ├─ CompanyFileRepository.kt
│        │              │           ├─ FileRepository.kt
│        │              │           ├─ ResumeRepository.kt
│        │              │           └─ UserProfilePhotoRepository.kt
│        │              ├─ application
│        │              │  ├─ configuration
│        │              │  │  └─ AsyncConfiguration.kt
│        │              │  ├─ port
│        │              │  │  ├─ input
│        │              │  │  │  ├─ UploadFileUsecase.kt
│        │              │  │  │  ├─ announce
│        │              │  │  │  │  ├─ LoadAnnouncementFileUsecase.kt
│        │              │  │  │  │  └─ UploadAnnouncementFileUsecase.kt
│        │              │  │  │  ├─ applies
│        │              │  │  │  │  ├─ LoadResumeUsecase.kt
│        │              │  │  │  │  ├─ RemoveResumeUsecase.kt
│        │              │  │  │  │  └─ UploadResumeUsecase.kt
│        │              │  │  │  ├─ company
│        │              │  │  │  │  ├─ LoadCompanyFileUsecase.kt
│        │              │  │  │  │  ├─ RemoveCompanyFileUsecase.kt
│        │              │  │  │  │  └─ UploadCompanyFileUsecase.kt
│        │              │  │  │  ├─ notice
│        │              │  │  │  │  ├─ LoadAttachmentUsecase.kt
│        │              │  │  │  │  ├─ RemoveAttachmentUsecase.kt
│        │              │  │  │  │  └─ UploadAttachmentUsecase.kt
│        │              │  │  │  └─ user
│        │              │  │  │     ├─ LoadUserFileUsecase.kt
│        │              │  │  │     └─ UploadUserFileUsecase.kt
│        │              │  │  └─ output
│        │              │  │     ├─ RemoveFilePort.kt
│        │              │  │     ├─ UploadFilePort.kt
│        │              │  │     ├─ announce
│        │              │  │     │  ├─ LoadAnnouncementFilePort.kt
│        │              │  │     │  └─ SaveAnnouncementFilePort.kt
│        │              │  │     ├─ applies
│        │              │  │     │  ├─ LoadResumePort.kt
│        │              │  │     │  ├─ RemoveResumePort.kt
│        │              │  │     │  └─ SaveResumeFilePort.kt
│        │              │  │     ├─ company
│        │              │  │     │  ├─ ChangeCompanyStatusPort.kt
│        │              │  │     │  ├─ LoadCompanyFilePort.kt
│        │              │  │     │  ├─ RemoveCompanyFilePort.kt
│        │              │  │     │  └─ SaveCompanyFilePort.kt
│        │              │  │     ├─ notice
│        │              │  │     │  ├─ LoadAttachmentPort.kt
│        │              │  │     │  ├─ RemoveAttachmentPort.kt
│        │              │  │     │  └─ SaveAttachmentPort.kt
│        │              │  │     └─ user
│        │              │  │        ├─ LoadUserFilePort.kt
│        │              │  │        └─ SaveUserFilePort.kt
│        │              │  └─ service
│        │              │     ├─ UploadFile.kt
│        │              │     ├─ announcement
│        │              │     │  ├─ LoadAnnouncementFile.kt
│        │              │     │  └─ UploadAnnouncementFile.kt
│        │              │     ├─ applies
│        │              │     │  ├─ LoadResume.kt
│        │              │     │  ├─ RemoveResume.kt
│        │              │     │  └─ UploadResume.kt
│        │              │     ├─ company
│        │              │     │  ├─ LoadCompanyFile.kt
│        │              │     │  ├─ RemoveCompanyFile.kt
│        │              │     │  └─ UploadCompanyFile.kt
│        │              │     ├─ notice
│        │              │     │  ├─ LoadAttachment.kt
│        │              │     │  ├─ RemoveAttachment.kt
│        │              │     │  └─ UploadAttachment.kt
│        │              │     └─ user
│        │              │        ├─ LoadUserFile.kt
│        │              │        └─ UploadUserFile.kt
│        │              └─ domain
│        │                 ├─ File.kt
│        │                 ├─ announcement
│        │                 │  └─ AnnouncementFile.kt
│        │                 ├─ applicant
│        │                 │  └─ Reporter.kt
│        │                 ├─ applies
│        │                 │  └─ Resume.kt
│        │                 ├─ company
│        │                 │  └─ CompanyFile.kt
│        │                 ├─ notice
│        │                 │  ├─ Attachment.kt
│        │                 │  └─ AttachmentNotice.kt
│        │                 ├─ time
│        │                 │  └─ TimeEntity.kt
│        │                 └─ user
│        │                    └─ UserProfilePhoto.kt
│        └─ resources
│           └─ application.yml
├─ gradle
│  └─ wrapper
│     ├─ gradle-wrapper.jar
│     └─ gradle-wrapper.properties
├─ gradlew
├─ gradlew.bat
├─ notice
│  ├─ Dockerfile
│  ├─ build.gradle.kts
│  └─ src
│     └─ main
│        ├─ kotlin
│        │  └─ com
│        │     └─ info
│        │        └─ info_v2_backend
│        │           └─ notice
│        │              ├─ NoticeApplication.kt
│        │              ├─ adapter
│        │              │  ├─ configuration
│        │              │  │  └─ KafkaConfiguration.kt
│        │              │  ├─ input
│        │              │  │  ├─ event
│        │              │  │  │  └─ NoticeApplicantCountConsumer.kt
│        │              │  │  └─ rest
│        │              │  │     ├─ NoticeController.kt
│        │              │  │     ├─ conifguration
│        │              │  │     │  └─ NoticeExceptionHandler.kt
│        │              │  │     └─ dto
│        │              │  │        ├─ request
│        │              │  │        │  ├─ CreateNoticeRequest.kt
│        │              │  │        │  ├─ EditNoticeRequest.kt
│        │              │  │        │  ├─ NoticeOpenPeriodRequest.kt
│        │              │  │        │  ├─ classification
│        │              │  │        │  │  └─ AddClassificationRequest.kt
│        │              │  │        │  ├─ openPeriod
│        │              │  │        │  │  └─ EditNoticeOpenPeriodRequest.kt
│        │              │  │        │  ├─ pay
│        │              │  │        │  │  ├─ EditEmploymentPayRequest.kt
│        │              │  │        │  │  ├─ EditPayRequest.kt
│        │              │  │        │  │  ├─ EmploymentPayRequest.kt
│        │              │  │        │  │  └─ PayRequest.kt
│        │              │  │        │  ├─ support
│        │              │  │        │  │  ├─ EditMealSupportRequest.kt
│        │              │  │        │  │  ├─ MealSupportRequest.kt
│        │              │  │        │  │  ├─ WelfareRequest.kt
│        │              │  │        │  │  └─ WorkTimeRequest.kt
│        │              │  │        │  ├─ welfare
│        │              │  │        │  │  └─ EditWelfareRequest.kt
│        │              │  │        │  ├─ workPlace
│        │              │  │        │  │  ├─ EditWorkPlaceRequest.kt
│        │              │  │        │  │  └─ WorkPlaceRequest.kt
│        │              │  │        │  └─ worktime
│        │              │  │        │     └─ EditWorkTimeRequest.kt
│        │              │  │        └─ response
│        │              │  │           ├─ AdminMaximumNoticeResponse.kt
│        │              │  │           ├─ LanguageResponse.kt
│        │              │  │           ├─ MaximumNoticeResponse.kt
│        │              │  │           ├─ MinimumNoticeResponse.kt
│        │              │  │           ├─ MinimumNoticeWithApproveStatusResponse.kt
│        │              │  │           ├─ certificate
│        │              │  │           │  └─ CertificateResponse.kt
│        │              │  │           ├─ classification
│        │              │  │           │  ├─ BigClassificationResponse.kt
│        │              │  │           │  └─ ClassificationResponse.kt
│        │              │  │           ├─ interview
│        │              │  │           │  └─ InterviewProcessResponse.kt
│        │              │  │           └─ technology
│        │              │  │              └─ TechnologyResponse.kt
│        │              │  └─ output
│        │              │     ├─ event
│        │              │     │  └─ CompanyAdapter.kt
│        │              │     ├─ persistence
│        │              │     │  ├─ jdbc
│        │              │     │  │  ├─ LoadNoticeAdapter.kt
│        │              │     │  │  ├─ NoticeQueryBlocks.kt
│        │              │     │  │  ├─ configuration
│        │              │     │  │  │  ├─ DatasourceConfiguration.kt
│        │              │     │  │  │  └─ JdbcConfiguration.kt
│        │              │     │  │  ├─ dto
│        │              │     │  │  │  └─ LanguageUsageDto.kt
│        │              │     │  │  └─ mapper
│        │              │     │  │     ├─ JdbcCertificateResponseMapper.kt
│        │              │     │  │     ├─ JdbcInterviewProcessMapMapper.kt
│        │              │     │  │     ├─ JdbcLanguageResponseDtoMapper.kt
│        │              │     │  │     ├─ JdbcNoticeVoMapper.kt
│        │              │     │  │     ├─ JdbcTechnologyResponseMapper.kt
│        │              │     │  │     └─ vo
│        │              │     │  │        └─ NoticeVo.kt
│        │              │     │  └─ jpa
│        │              │     │     ├─ BigRecruitmentClassificationAdapter.kt
│        │              │     │     ├─ NoticeAdapter.kt
│        │              │     │     ├─ SmallRecruitmentClassificationAdapter.kt
│        │              │     │     ├─ certificate
│        │              │     │     │  ├─ CertificateAdapter.kt
│        │              │     │     │  └─ CertificateUsageAdapter.kt
│        │              │     │     ├─ classification
│        │              │     │     │  ├─ SmallClassificationUsageAdapter.kt
│        │              │     │     │  └─ SmallClassificationUsageRepository.kt
│        │              │     │     ├─ language
│        │              │     │     │  └─ LanguageAdapter.kt
│        │              │     │     ├─ preference
│        │              │     │     │  └─ NoticePreferenceAdapter.kt
│        │              │     │     ├─ repository
│        │              │     │     │  ├─ CertificateRepository.kt
│        │              │     │     │  ├─ CertificateUsageRepository.kt
│        │              │     │     │  ├─ LanguageRepository.kt
│        │              │     │     │  ├─ LanguageUsageRepository.kt
│        │              │     │     │  ├─ NoticePreferenceRepository.kt
│        │              │     │     │  ├─ NoticeRepository.kt
│        │              │     │     │  ├─ RecruitmentBigClassificationRepository.kt
│        │              │     │     │  ├─ RecruitmentSmallClassificationRepository.kt
│        │              │     │     │  ├─ TechnologyRepository.kt
│        │              │     │     │  └─ TechnologyUsageRepository.kt
│        │              │     │     └─ technology
│        │              │     │        ├─ TechnologyAdapter.kt
│        │              │     │        └─ TechnologyUsageAdapter.kt
│        │              │     └─ rest
│        │              │        ├─ CompanyFeignClient.kt
│        │              │        ├─ CompanyFeignClientFallback.kt
│        │              │        ├─ FileFeignClient.kt
│        │              │        ├─ FileFeignClientFallback.kt
│        │              │        └─ configuration
│        │              │           ├─ FeignConfiguration.kt
│        │              │           └─ FeignErrorDecoder.kt
│        │              ├─ application
│        │              │  ├─ port
│        │              │  │  ├─ input
│        │              │  │  │  ├─ ApproveNoticeUsecase.kt
│        │              │  │  │  ├─ ConcludeNoticeUsecase.kt
│        │              │  │  │  ├─ CountOpenNoticeUsecase.kt
│        │              │  │  │  ├─ CreateNoticeUsecase.kt
│        │              │  │  │  ├─ EditNoticeUsecase.kt
│        │              │  │  │  ├─ LoadNoticeUsecase.kt
│        │              │  │  │  ├─ LoadWaitingNoticeUsecase.kt
│        │              │  │  │  ├─ RemoveNoticeUsecase.kt
│        │              │  │  │  ├─ UpdateNoticeApplicantCountUsecase.kt
│        │              │  │  │  ├─ certificate
│        │              │  │  │  │  └─ LoadCertificateUsecase.kt
│        │              │  │  │  ├─ change
│        │              │  │  │  │  └─ ChangeAttachmentUsecase.kt
│        │              │  │  │  ├─ classification
│        │              │  │  │  │  ├─ AddClassificationUsecase.kt
│        │              │  │  │  │  └─ LoadClassificationUsecase.kt
│        │              │  │  │  ├─ interview
│        │              │  │  │  │  └─ LoadInterviewProcessUsecase.kt
│        │              │  │  │  ├─ language
│        │              │  │  │  │  ├─ AddLanguageUsecase.kt
│        │              │  │  │  │  └─ LoadLanguageUsecase.kt
│        │              │  │  │  ├─ noticePreference
│        │              │  │  │  │  ├─ LoadMyNoticePreferenceInfoUsecase.kt
│        │              │  │  │  │  └─ SetNoticePreferenceUsecase.kt
│        │              │  │  │  └─ technology
│        │              │  │  │     ├─ AddTechnologyUsecase.kt
│        │              │  │  │     └─ LoadTechnologyUsecase.kt
│        │              │  │  └─ output
│        │              │  │     ├─ LoadCompanyPort.kt
│        │              │  │     ├─ LoadNoticePort.kt
│        │              │  │     ├─ LoadWithConditionPort.kt
│        │              │  │     ├─ RemoveNoticePort.kt
│        │              │  │     ├─ SaveNoticePort.kt
│        │              │  │     ├─ UpdateCompanyPort.kt
│        │              │  │     ├─ bigClassification
│        │              │  │     │  ├─ LoadBigClassificationPort.kt
│        │              │  │     │  └─ SaveBigClassificationPort.kt
│        │              │  │     ├─ certificate
│        │              │  │     │  ├─ LoadCertificatePort.kt
│        │              │  │     │  ├─ RemoveCertificateUsagePort.kt
│        │              │  │     │  └─ SaveCertificateUsagePort.kt
│        │              │  │     ├─ file
│        │              │  │     │  └─ FilePort.kt
│        │              │  │     ├─ language
│        │              │  │     │  ├─ AddLanguagePort.kt
│        │              │  │     │  ├─ LoadLanguagePort.kt
│        │              │  │     │  └─ SaveLanguageUsagePort.kt
│        │              │  │     ├─ noticePreference
│        │              │  │     │  ├─ LoadNoticePreferencePort.kt
│        │              │  │     │  └─ SaveNoticePreferencePort.kt
│        │              │  │     ├─ smallClassification
│        │              │  │     │  ├─ LoadSmallClassificationPort.kt
│        │              │  │     │  ├─ LoadSmallClassificationUsagePort.kt
│        │              │  │     │  ├─ SaveSmallClassificationPort.kt
│        │              │  │     │  └─ SaveSmallClassificationUsagePort.kt
│        │              │  │     └─ technology
│        │              │  │        ├─ LoadTechnologyPort.kt
│        │              │  │        ├─ RemoveTechnologyUsagePort.kt
│        │              │  │        └─ SaveTechnologyUsagePort.kt
│        │              │  └─ service
│        │              │     ├─ ApproveNotice.kt
│        │              │     ├─ ConcludeNotice.kt
│        │              │     ├─ CreateNotice.kt
│        │              │     ├─ EditNotice.kt
│        │              │     ├─ LoadNotice.kt
│        │              │     ├─ LoadWaitingNotice.kt
│        │              │     ├─ RemoveNotice.kt
│        │              │     ├─ UpdateNoticeApplicantCount.kt
│        │              │     ├─ certificate
│        │              │     │  └─ LoadCertificate.kt
│        │              │     ├─ change
│        │              │     │  └─ ChangeAttachment.kt
│        │              │     ├─ classification
│        │              │     │  ├─ AddClassification.kt
│        │              │     │  └─ LoadClassification.kt
│        │              │     ├─ interview
│        │              │     │  └─ LoadInterviewProcess.kt
│        │              │     ├─ language
│        │              │     │  ├─ AddLanguage.kt
│        │              │     │  └─ LoadLanguage.kt
│        │              │     ├─ noticePreference
│        │              │     │  ├─ LoadMyNoticePreferenceInfo.kt
│        │              │     │  └─ SetNoticePreference.kt
│        │              │     └─ technology
│        │              │        ├─ AddTechnology.kt
│        │              │        └─ LoadTechnology.kt
│        │              └─ domain
│        │                 ├─ Notice.kt
│        │                 ├─ certificate
│        │                 │  ├─ Certificate.kt
│        │                 │  ├─ CertificateUsage.kt
│        │                 │  └─ CertificateUsageIdClass.kt
│        │                 ├─ company
│        │                 │  └─ NoticeCompany.kt
│        │                 ├─ interview
│        │                 │  └─ InterviewProcess.kt
│        │                 ├─ language
│        │                 │  ├─ Language.kt
│        │                 │  ├─ LanguageUsage.kt
│        │                 │  └─ LanguageUsageIdClass.kt
│        │                 ├─ noticePreference
│        │                 │  ├─ NoticePreference.kt
│        │                 │  └─ NoticePreferenceIdClass.kt
│        │                 ├─ openPeriod
│        │                 │  └─ NoticeOpenPeriod.kt
│        │                 ├─ recruitmentBusiness
│        │                 │  ├─ RecruitmentBigClassification.kt
│        │                 │  ├─ RecruitmentSmallClassification.kt
│        │                 │  └─ RecruitmentSmallClassificationUsage.kt
│        │                 ├─ status
│        │                 │  └─ NoticeWaitingStatus.kt
│        │                 ├─ support
│        │                 │  ├─ MealSupport.kt
│        │                 │  ├─ Pay.kt
│        │                 │  ├─ Welfare.kt
│        │                 │  └─ WorkTime.kt
│        │                 ├─ technology
│        │                 │  ├─ Technology.kt
│        │                 │  ├─ TechnologyUsage.kt
│        │                 │  └─ TechnologyUsageIdClass.kt
│        │                 ├─ time
│        │                 │  └─ TimeEntity.kt
│        │                 └─ workPlace
│        │                    └─ WorkPlace.kt
│        └─ resources
│           └─ application.yml
├─ settings.gradle.kts
└─ user
   ├─ Dockerfile
   ├─ build.gradle.kts
   └─ src
      └─ main
         ├─ kotlin
         │  └─ com
         │     └─ info
         │        └─ info_v2_backend
         │           └─ user
         │              ├─ UserApplication.kt
         │              ├─ adapter
         │              │  ├─ configuration
         │              │  │  ├─ KafkaConfiguration.kt
         │              │  │  └─ KafkaProperty.kt
         │              │  ├─ input
         │              │  │  ├─ event
         │              │  │  │  └─ SaveUserEventConsumer.kt
         │              │  │  └─ web
         │              │  │     └─ rest
         │              │  │        ├─ UserController.kt
         │              │  │        ├─ configuration
         │              │  │        │  └─ UserExceptionHandler.kt
         │              │  │        └─ dto
         │              │  │           ├─ request
         │              │  │           │  ├─ SaveContactorDto.kt
         │              │  │           │  ├─ SaveStudentDto.kt
         │              │  │           │  ├─ SaveTeacherDto.kt
         │              │  │           │  └─ SaveUserDto.kt
         │              │  │           └─ response
         │              │  │              ├─ CommonUserDetails.kt
         │              │  │              └─ CustomGrantedAuthority.kt
         │              │  └─ output
         │              │     ├─ persistence
         │              │     │  ├─ UserPersistenceAdapter.kt
         │              │     │  └─ repository
         │              │     │     ├─ ContactorRepository.kt
         │              │     │     ├─ ProfilePhotoRepository.kt
         │              │     │     ├─ StudentRepository.kt
         │              │     │     ├─ TeacherRepository.kt
         │              │     │     └─ UserRepository.kt
         │              │     └─ rest
         │              │        ├─ FileFeignClient.kt
         │              │        ├─ FileFeignClientFallbackFactory.kt
         │              │        └─ configuration
         │              │           ├─ FeignConfiguration.kt
         │              │           └─ FeignErrorDecoder.kt
         │              ├─ application
         │              │  ├─ configuration
         │              │  │  └─ SecurityConfiguration.kt
         │              │  ├─ port
         │              │  │  ├─ input
         │              │  │  │  ├─ ChangePasswordUsecase.kt
         │              │  │  │  ├─ ChangeUserProfilePhotoUsecase.kt
         │              │  │  │  ├─ LoadCommonUserDetailsUsecase.kt
         │              │  │  │  ├─ LoadContactorUsecase.kt
         │              │  │  │  ├─ LoadPasswordHintUsecase.kt
         │              │  │  │  ├─ LoadStudentUsecase.kt
         │              │  │  │  ├─ LoadTeacherUsecase.kt
         │              │  │  │  └─ SaveUserUsecase.kt
         │              │  │  └─ output
         │              │  │     ├─ LoadContactorPort.kt
         │              │  │     ├─ LoadStudentPort.kt
         │              │  │     ├─ LoadTeacherPort.kt
         │              │  │     ├─ LoadUserPort.kt
         │              │  │     ├─ SaveUserPort.kt
         │              │  │     └─ UserFilePort.kt
         │              │  └─ service
         │              │     ├─ ChangePassword.kt
         │              │     ├─ ChangeUserProfilePhoto.kt
         │              │     ├─ LoadCommonUserDetails.kt
         │              │     ├─ LoadPasswordHint.kt
         │              │     ├─ LoadUser.kt
         │              │     └─ SaveUser.kt
         │              └─ domain
         │                 ├─ Contactor.kt
         │                 ├─ Student.kt
         │                 ├─ Teacher.kt
         │                 ├─ User.kt
         │                 ├─ profile
         │                 │  └─ ProfilePhoto.kt
         │                 └─ time
         │                    └─ TimeEntity.kt
         └─ resources
            └─ application.yml
```
©generated by [Project Tree Generator](https://woochanleee.github.io/project-tree-generator)
