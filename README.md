# INFO_v2_Backend


> **INFO 프로젝트**는 기존 **직접 담당자와 연락하여** 이루어지던 
*대덕sw마이스터고 현장 실습생 채용 의뢰를 전산화하여* 
소통 과정에서 발생하는 불필요한 연락을 제거하고, **웹 어플리케이션**으로 전환하여 **학교 산학부 담당자** 부재 시에도 **원활한 채용 프로세스 진행**이 가능하게 하여  `가용성` 및 `안정성` 확보를 기대할 수 있습니다.
>







> **대덕소프트웨어 마이스터고등학교 
*채용의뢰 서비스***
> 

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
