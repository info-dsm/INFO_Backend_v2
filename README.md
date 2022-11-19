# INFO_v2_Backend


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
![INFO 메인](https://user-images.githubusercontent.com/59428479/202425751-3967bee2-46b5-4c88-9760-bd953cf09740.png)


![INFO 채용 공고 정보](https://user-images.githubusercontent.com/59428479/202425793-a0e16f1d-e9d5-444b-97f2-1cae99baed67.png)





 INFO Project Backend 서버입니다. 기존, 모놀리식 아키텍처를 채택하여 빠르게 구성한 INFO_v1_Backend 서버를 MSA, Hexagornal, DDD로 설계하려 노력했습니다. 






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

<img width="873" alt="Hexagornal Architecture" src="https://user-images.githubusercontent.com/59428479/202425478-fe91b4d3-a83e-4668-b6fe-b7ff4ab3756b.png">



- SOLID 원칙 준수의 용이
- 계층 별 의존성 최소화
    - 비즈니스 계층이 표현 계층이나 데이터 액세스 로직에 의존하지 않도록함.


```
📦 
├─ .gitignore
├─ README.md
├─ apiGateway
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
│                       │  └─ GlobalExceptionHandler.kt
│                       ├─ filter
│                       │  └─ GlobalFilter.kt
│                       └─ property
│                          └─ JwtProperty.kt
├─ applies
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
│        │              │  │     └─ dto
│        │              │  │        └─ respnose
│        │              │  │           └─ AppliesResponse.kt
│        │              │  └─ output
│        │              │     ├─ event
│        │              │     │  ├─ UpdateNoticeProducer.kt
│        │              │     │  └─ configuration
│        │              │     │     ├─ KafkaConfiguration.kt
│        │              │     │     └─ KafkaProperty.kt
│        │              │     ├─ persistence
│        │              │     │  ├─ AppliesAdapter.kt
│        │              │     │  └─ repository
│        │              │     │     └─ AppliesRepository.kt
│        │              │     └─ rest
│        │              │        ├─ FileFeignClient.kt
│        │              │        ├─ FileFeignClientFallback.kt
│        │              │        ├─ NoticeFeignClient.kt
│        │              │        ├─ NoticeFeignClientFallback.kt
│        │              │        ├─ UserFeignClient.kt
│        │              │        └─ UserFeignClientFallback.kt
│        │              ├─ application
│        │              │  ├─ port
│        │              │  │  ├─ input
│        │              │  │  │  ├─ ApplyAppliesUsecase.kt
│        │              │  │  │  ├─ ApproveAppliesUsecase.kt
│        │              │  │  │  ├─ CancelApplyUsecase.kt
│        │              │  │  │  ├─ LoadAppliesUsecase.kt
│        │              │  │  │  └─ RejectAppliesUsecase.kt
│        │              │  │  └─ output
│        │              │  │     ├─ cancel
│        │              │  │     │  └─ CancelApplyPort.kt
│        │              │  │     ├─ load
│        │              │  │     │  ├─ LoadAppliesPort.kt
│        │              │  │     │  ├─ LoadNoticePort.kt
│        │              │  │     │  └─ LoadStudentPort.kt
│        │              │  │     ├─ notice
│        │              │  │     │  └─ UpdateNoticePort.kt
│        │              │  │     ├─ save
│        │              │  │     │  ├─ SaveAppliesPort.kt
│        │              │  │     │  └─ UpdateNoticeAppliesCountPort.kt
│        │              │  │     └─ upload
│        │              │  │        └─ UploadResumePort.kt
│        │              │  └─ service
│        │              │     ├─ ApplyApplies.kt
│        │              │     ├─ ApproveApplies.kt
│        │              │     ├─ CancelApply.kt
│        │              │     ├─ LoadApplies.kt
│        │              │     └─ RejectApplies.kt
│        │              └─ domain
│        │                 ├─ Applies.kt
│        │                 ├─ notice
│        │                 │  └─ AppliesNotice.kt
│        │                 └─ user
│        │                    └─ Applicant.kt
│        └─ resources
│           └─ application.yml
├─ auth
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
│        │              │  ├─ configuration
│        │              │  │  ├─ KafkaConfiguration.kt
│        │              │  │  └─ KafkaProperty.kt
│        │              │  ├─ input
│        │              │  │  ├─ event
│        │              │  │  │  └─ AuthConsumer.kt
│        │              │  │  └─ rest
│        │              │  │     ├─ AuthController.kt
│        │              │  │     ├─ configuration
│        │              │  │     │  └─ AuthExceptionHandler.kt
│        │              │  │     └─ dto
│        │              │  │        ├─ request
│        │              │  │        │  ├─ LoginRequest.kt
│        │              │  │        │  └─ TokenReissueRequest.kt
│        │              │  │        └─ response
│        │              │  │           └─ TokenResponse.kt
│        │              │  └─ output
│        │              │     ├─ event
│        │              │     │  ├─ SaveUserAdapter.kt
│        │              │     │  └─ SendEmailAdapter.kt
│        │              │     ├─ persisetnece
│        │              │     │  ├─ CodePersistenceAdapter.kt
│        │              │     │  └─ repository
│        │              │     │     └─ CodeRepository.kt
│        │              │     └─ rest
│        │              │        ├─ UserFeignClient.kt
│        │              │        └─ UserFeignClientFallback.kt
│        │              ├─ application
│        │              │  ├─ configuration
│        │              │  │  └─ SecurityConfiguration.kt
│        │              │  ├─ env
│        │              │  │  └─ JwtProperty.kt
│        │              │  ├─ port
│        │              │  │  ├─ input
│        │              │  │  │  ├─ CheckCodeUsecase.kt
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
│        │              │  │     └─ UserServicePort.kt
│        │              │  └─ service
│        │              │     ├─ AuthDetailsService.kt
│        │              │     ├─ CheckCode.kt
│        │              │     ├─ Login.kt
│        │              │     ├─ Reissue.kt
│        │              │     ├─ SendAuthenticationCode.kt
│        │              │     ├─ Signup.kt
│        │              │     └─ TokenProvider.kt
│        │              └─ domain
│        │                 └─ Code.kt
│        └─ resources
│           └─ application.yml
├─ board
│  ├─ build.gradle.kts
│  └─ src
│     └─ main
│        ├─ kotlin
│        │  └─ com
│        │     └─ info
│        │        └─ info_v2_backend
│        │           └─ board
│        │              ├─ BoardApplication.kt
│        │              ├─ business
│        │              │  ├─ BoardService.kt
│        │              │  └─ BoardServiceImpl.kt
│        │              └─ presentation
│        │                 └─ BoardController.kt
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
│                       ├─ event
│                       │  └─ KafkaTopics.kt
│                       ├─ exception
│                       │  ├─ BusinessException.kt
│                       │  ├─ ErrorCode.kt
│                       │  └─ ErrorResponse.kt
│                       ├─ file
│                       │  ├─ FileConvert.kt
│                       │  └─ dto
│                       │     ├─ CompanyFileClassificationType.kt
│                       │     ├─ FileDto.kt
│                       │     ├─ RegisterCompanyFileDto.kt
│                       │     ├─ UploadCompanyFileDto.kt
│                       │     ├─ response
│                       │     │  ├─ CompanyFileResponse.kt
│                       │     │  └─ FileResponse.kt
│                       │     └─ type
│                       │        ├─ DocsExt.kt
│                       │        ├─ FileType.kt
│                       │        └─ ImageExt.kt
│                       ├─ filter
│                       │  └─ ExceptionFilter.kt
│                       ├─ notice
│                       │  └─ NoticeDto.kt
│                       └─ user
│                          ├─ Generation.kt
│                          ├─ StudentDto.kt
│                          └─ UserDto.kt
├─ commonEntity
│  ├─ build.gradle.kts
│  └─ src
│     └─ main
│        └─ kotlin
│           └─ com
│              └─ info
│                 └─ info_v2_backend
│                    └─ commonEntity
│                       └─ entity
│                          └─ TimeEntity.kt
├─ company
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
│        │              │  │  │  ├─ RegisterCompanyFileConsumer.kt
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
│        │              │  │           │  └─ register
│        │              │  │           │     ├─ CompanyContactRequest.kt
│        │              │  │           │     ├─ CompanyInformationRequest.kt
│        │              │  │           │     ├─ CompanyNameRequest.kt
│        │              │  │           │     └─ RegisterCompanyRequest.kt
│        │              │  │           └─ response
│        │              │  │              ├─ CompanyIntroductionResponse.kt
│        │              │  │              ├─ MaximumCompanyResponse.kt
│        │              │  │              └─ MinimumCompanyResponse.kt
│        │              │  └─ output
│        │              │     ├─ event
│        │              │     │  ├─ UserAdapter.kt
│        │              │     │  └─ configuration
│        │              │     │     └─ FormConfiguration.kt
│        │              │     ├─ persistence
│        │              │     │  ├─ BusinessAreaAdapter.kt
│        │              │     │  ├─ BusinessAreaTaggedAdapter.kt
│        │              │     │  ├─ CompanyAdapater.kt
│        │              │     │  └─ repository
│        │              │     │     ├─ BusinessAreaRepository.kt
│        │              │     │     ├─ BusinessAreaTaggedRepository.kt
│        │              │     │     └─ CompanyRepository.kt
│        │              │     └─ rest
│        │              │        ├─ AuthFeignClient.kt
│        │              │        ├─ AuthFeignClientFallbackFactory.kt
│        │              │        ├─ FileFeignClient.kt
│        │              │        ├─ FileFeignClientFallbackFactory.kt
│        │              │        ├─ UserFeignClient.kt
│        │              │        └─ configuration
│        │              │           └─ FeignAuthConfiguration.kt
│        │              ├─ application
│        │              │  ├─ configuration
│        │              │  │  └─ SecurityConfiguration.kt
│        │              │  ├─ port
│        │              │  │  ├─ input
│        │              │  │  │  ├─ EditCompanyUsecase.kt
│        │              │  │  │  ├─ LoadBusinessAreaUsecase.kt
│        │              │  │  │  ├─ LoadCompanyUsecase.kt
│        │              │  │  │  ├─ MakeAssociatedUsecase.kt
│        │              │  │  │  ├─ RegisterCompanyUsecase.kt
│        │              │  │  │  ├─ RemoveCompanyFileUsecase.kt
│        │              │  │  │  ├─ UpdateLastNoticedCompanyUsecase.kt
│        │              │  │  │  └─ file
│        │              │  │  │     ├─ AddCompanyFileUsecase.kt
│        │              │  │  │     ├─ ChangeCompanyFileUsecase.kt
│        │              │  │  │     └─ RegisterCompanyFileUsecase.kt
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
│        │              │  │     │  └─ SaveContactorPort.kt
│        │              │  │     ├─ file
│        │              │  │     │  └─ CompanyFilePort.kt
│        │              │  │     └─ user
│        │              │  │        └─ LoadContactorPort.kt
│        │              │  └─ service
│        │              │     ├─ AddIntroductionFile.kt
│        │              │     ├─ ChangeBusinessCertificate.kt
│        │              │     ├─ EditCompany.kt
│        │              │     ├─ LoadBusinessArea.kt
│        │              │     ├─ LoadCompany.kt
│        │              │     ├─ MakeAssociated.kt
│        │              │     ├─ RegisterCompany.kt
│        │              │     ├─ RegisterCompanyFile.kt
│        │              │     ├─ RemoveCompanyFile.kt
│        │              │     └─ UpdateLastNoticedCompany.kt
│        │              └─ domain
│        │                 ├─ Company.kt
│        │                 ├─ ContactorId.kt
│        │                 ├─ businessArea
│        │                 │  ├─ BusinessArea.kt
│        │                 │  ├─ BusinessAreaTagged.kt
│        │                 │  └─ BusinessAreaTaggedIdClass.kt
│        │                 ├─ information
│        │                 │  ├─ AddressInfo.kt
│        │                 │  └─ CompanyInformation.kt
│        │                 ├─ introduction
│        │                 │  └─ CompanyIntroduction.kt
│        │                 ├─ name
│        │                 │  └─ CompanyName.kt
│        │                 └─ status
│        │                    └─ CompanyCreationStatus.kt
│        └─ resources
│           └─ application.yml
├─ email
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
│        │              │  │  └─ web
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
│        │              │        ├─ adapter
│        │              │        │  ├─ UserFeignClient.kt
│        │              │        │  └─ UserFiegnClientFallback.kt
│        │              │        └─ configuration
│        │              │           └─ HystrixConfiguration.kt
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
│        │                 └─ user
│        │                    ├─ Sender.kt
│        │                    └─ Target.kt
│        └─ resources
│           ├─ application.yml
│           └─ templates
│              └─ notification.html
├─ employment
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
│        │              │  │     └─ dto
│        │              │  │        └─ request
│        │              │  │           └─ EmploymentResponse.kt
│        │              │  └─ output
│        │              │     ├─ persistence
│        │              │     │  ├─ EmploymentAdapter.kt
│        │              │     │  └─ repository
│        │              │     │     └─ EmploymentRepository.kt
│        │              │     └─ rest
│        │              │        ├─ AppliesFeignClient.kt
│        │              │        ├─ AppliesFeignClientFallback.kt
│        │              │        ├─ CompanyFeignClient.kt
│        │              │        ├─ CompanyFeignClientFallback.kt
│        │              │        ├─ NoticeFeignClient.kt
│        │              │        └─ NoticeFeignClientFallback.kt
│        │              ├─ application
│        │              │  ├─ port
│        │              │  │  ├─ input
│        │              │  │  │  ├─ ConfirmEmploymentUsecase.kt
│        │              │  │  │  ├─ EmployStudentUsecase.kt
│        │              │  │  │  ├─ FailEmploymentUsecase.kt
│        │              │  │  │  └─ LoadEmploymentUsecase.kt
│        │              │  │  └─ output
│        │              │  │     ├─ LoadAppliesStudentPort.kt
│        │              │  │     ├─ LoadCompanyPort.kt
│        │              │  │     ├─ LoadEmploymentPort.kt
│        │              │  │     ├─ LoadNoticePort.kt
│        │              │  │     └─ SaveEmploymentPort.kt
│        │              │  └─ service
│        │              │     ├─ ConfirmEmployment.kt
│        │              │     ├─ EmployStudent.kt
│        │              │     ├─ FailEmployment.kt
│        │              │     └─ LoadEmployment.kt
│        │              └─ domain
│        │                 ├─ Employment.kt
│        │                 ├─ company
│        │                 │  ├─ EmploymentCompany.kt
│        │                 │  └─ EmploymentContactor.kt
│        │                 ├─ notice
│        │                 │  └─ EmploymentNotice.kt
│        │                 ├─ status
│        │                 │  └─ EmploymentStatus.kt
│        │                 └─ student
│        │                    └─ EmployedStudent.kt
│        └─ resources
│           └─ application.yml
├─ eureka
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
│        │              │  ├─ configuration
│        │              │  │  ├─ KafkaConfiguration.kt
│        │              │  │  └─ KafkaProperty.kt
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
│        │              │     │  └─ RegisterCompanyFileAdapter.kt
│        │              │     └─ persistence
│        │              │        ├─ LoadCompanyFileAdapter.kt
│        │              │        ├─ RemoveFileAdapter.kt
│        │              │        ├─ ResumeFileAdapter.kt
│        │              │        ├─ SaveCompanyFileAdapter.kt
│        │              │        └─ repository
│        │              │           ├─ CompanyFileRepostiory.kt
│        │              │           ├─ FileRepository.kt
│        │              │           └─ ResumeRepository.kt
│        │              ├─ application
│        │              │  ├─ configuration
│        │              │  │  └─ AsyncConfiguration.kt
│        │              │  ├─ port
│        │              │  │  ├─ input
│        │              │  │  │  ├─ LoadCompanyFileUsecase.kt
│        │              │  │  │  ├─ RemoveCompanyFileUsecase.kt
│        │              │  │  │  ├─ UploadCompanyFileUsecase.kt
│        │              │  │  │  └─ UploadResumeUsecase.kt
│        │              │  │  └─ output
│        │              │  │     ├─ LoadCompanyFilePort.kt
│        │              │  │     ├─ RegisterCompanyFilePort.kt
│        │              │  │     ├─ RemoveFilePort.kt
│        │              │  │     ├─ UploadFilePort.kt
│        │              │  │     └─ save
│        │              │  │        ├─ SaveCompanyFilePort.kt
│        │              │  │        └─ SaveResumeFilePort.kt
│        │              │  └─ service
│        │              │     ├─ LoadCompanyFile.kt
│        │              │     ├─ RemoveCompanyFile.kt
│        │              │     └─ UploadFile.kt
│        │              └─ domain
│        │                 ├─ File.kt
│        │                 ├─ applicant
│        │                 │  └─ Reporter.kt
│        │                 ├─ applies
│        │                 │  └─ Resume.kt
│        │                 ├─ company
│        │                 │  └─ CompanyFile.kt
│        │                 └─ notice
│        │                    ├─ AttachmentNotice.kt
│        │                    └─ FormAttachment.kt
│        └─ resources
│           └─ application.yml
├─ gradle
│  └─ wrapper
│     ├─ gradle-wrapper.jar
│     └─ gradle-wrapper.properties
├─ gradlew
├─ gradlew.bat
├─ notice
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
│        │              │  ├─ input
│        │              │  │  └─ rest
│        │              │  │     ├─ NoticeController.kt
│        │              │  │     └─ dto
│        │              │  │        ├─ request
│        │              │  │        │  ├─ CreateNoticeRequest.kt
│        │              │  │        │  ├─ EditNoticeRequest.kt
│        │              │  │        │  ├─ NoticeOpenPeriodRequest.kt
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
│        │              │  │           ├─ LanguageResponse.kt
│        │              │  │           ├─ MaximumNoticeResponse.kt
│        │              │  │           ├─ MinimumNoticeResponse.kt
│        │              │  │           ├─ certificate
│        │              │  │           │  └─ CertificateResponse.kt
│        │              │  │           ├─ classification
│        │              │  │           │  ├─ BigClassificationResponse.kt
│        │              │  │           │  └─ ClassificationResponse.kt
│        │              │  │           └─ technology
│        │              │  │              └─ TechnologyResponse.kt
│        │              │  └─ output
│        │              │     ├─ event
│        │              │     │  └─ CompanyAdapter.kt
│        │              │     ├─ persistence
│        │              │     │  ├─ BigRecruitmentClassificationAdapter.kt
│        │              │     │  ├─ NoticeAdapter.kt
│        │              │     │  ├─ SmallRecruitmentClassificationAdapter.kt
│        │              │     │  └─ repository
│        │              │     │     ├─ NoticeRepository.kt
│        │              │     │     ├─ RecruitmentBigClassificationRepository.kt
│        │              │     │     └─ RecruitmentSmallClassificationRepository.kt
│        │              │     └─ rest
│        │              │        └─ CompanyFeignClient.kt
│        │              ├─ application
│        │              │  ├─ port
│        │              │  │  ├─ input
│        │              │  │  │  ├─ ConcludeNoticeUsecase.kt
│        │              │  │  │  ├─ CreateNoticeUsecase.kt
│        │              │  │  │  ├─ EditNoticeUsecase.kt
│        │              │  │  │  ├─ LoadNoticeUsecase.kt
│        │              │  │  │  └─ RemoveNoticeUsecase.kt
│        │              │  │  └─ output
│        │              │  │     ├─ LoadCompanyPort.kt
│        │              │  │     ├─ LoadNoticePort.kt
│        │              │  │     ├─ RemoveNoticePort.kt
│        │              │  │     ├─ SaveNoticePort.kt
│        │              │  │     ├─ UpdateCompanyPort.kt
│        │              │  │     ├─ bigClassification
│        │              │  │     │  ├─ LoadBigClassificationPort.kt
│        │              │  │     │  └─ SaveBigClassificationPort.kt
│        │              │  │     └─ smallClassification
│        │              │  │        ├─ LoadSmallClassificationPort.kt
│        │              │  │        └─ SaveSmallClassificationPort.kt
│        │              │  └─ service
│        │              │     ├─ ConcludeNotice.kt
│        │              │     ├─ CreateNotice.kt
│        │              │     ├─ EditNotice.kt
│        │              │     ├─ LoadNotice.kt
│        │              │     └─ RemoveNotice.kt
│        │              └─ domain
│        │                 ├─ Notice.kt
│        │                 ├─ certificate
│        │                 │  ├─ Certificate.kt
│        │                 │  ├─ CertificateUsage.kt
│        │                 │  └─ CertificateUsageIdClass.kt
│        │                 ├─ company
│        │                 │  └─ NoticeCompany.kt
│        │                 ├─ interview
│        │                 │  ├─ InterviewProcess.kt
│        │                 │  └─ InterviewProcessUsage.kt
│        │                 ├─ language
│        │                 │  ├─ Language.kt
│        │                 │  ├─ LanguageUsage.kt
│        │                 │  └─ LanguageUsageIdClass.kt
│        │                 ├─ openPeriod
│        │                 │  └─ NoticeOpenPeriod.kt
│        │                 ├─ recruitmentBusiness
│        │                 │  ├─ RecruitmentBigClassification.kt
│        │                 │  └─ RecruitmentSmallClassification.kt
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
│        │                 └─ workPlace
│        │                    └─ WorkPlace.kt
│        └─ resources
│           └─ application.yml
├─ settings.gradle.kts
└─ user
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
         │              │  │              ├─ ContactorResponse.kt
         │              │  │              └─ CustomGrantedAuthority.kt
         │              │  └─ output
         │              │     └─ persistence
         │              │        ├─ UserPersistenceAdapter.kt
         │              │        └─ repository
         │              │           ├─ ContactorRepository.kt
         │              │           ├─ StudentRepository.kt
         │              │           ├─ TeacherRepository.kt
         │              │           └─ UserRepository.kt
         │              ├─ application
         │              │  ├─ configuration
         │              │  │  └─ SecurityConfiguration.kt
         │              │  ├─ port
         │              │  │  ├─ input
         │              │  │  │  ├─ LoadCommonUserDetailsUsecase.kt
         │              │  │  │  ├─ LoadContactorUsecase.kt
         │              │  │  │  ├─ LoadPasswordHintUsecase.kt
         │              │  │  │  ├─ LoadStudentUsecase.kt
         │              │  │  │  └─ SaveUserUsecase.kt
         │              │  │  └─ output
         │              │  │     ├─ LoadContactorPort.kt
         │              │  │     ├─ LoadStudentPort.kt
         │              │  │     ├─ LoadUserPort.kt
         │              │  │     └─ SaveUserPort.kt
         │              │  └─ service
         │              │     ├─ LoadCommonUserDetails.kt
         │              │     ├─ LoadPasswordHint.kt
         │              │     ├─ LoadUser.kt
         │              │     └─ SaveUser.kt
         │              └─ domain
         │                 ├─ Contactor.kt
         │                 ├─ Role.kt
         │                 ├─ Student.kt
         │                 ├─ Teacher.kt
         │                 └─ User.kt
         └─ resources
            └─ application.yml
```
©generated by [Project Tree Generator](https://woochanleee.github.io/project-tree-generator)
