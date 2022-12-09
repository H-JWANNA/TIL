# 배포 자동화

배포 자동화란 한번의 클릭 혹은 명령어 입력을 통해 배포 과정을 자동으로 진행하는 것을 말한다.

<br>

배포 자동화가 필요한 이유는 다음과 같다.

- 수동적이고 반복적인 배포 과정을 자동화하여 시간을 절약할 수 있다.

- 휴먼 에러)를 방지할 수 있다.

  > ❓ **휴먼 에러 (Human Error)**
  >
  > 사람이 수동적으로 배포 과정을 진행하는 중에 생기는 실수를 의미한다.

<br>

### 배포 자동화 파이프라인

<br>

배포에서 파이프라인(Pipeline)은 **소스 코드의 관리부터 실제 서비스로의 배포 과정을 연결하는 구조**를 말한다.

파이프라인은 전체 배포 과정을 여러 단계(Stages)로 분리하며,  

순차적으로 실행된 단계마다 주어진 작업(Actions)을 수행한다.

<br>

<img src = "./src/pipeline.png" width = 600>

<br>

1. **Source Stage**  
   : 원격 저장소에 관리되고 있는 소스 코드에 변경 사항이 일어날 경우, 이를 감지하고 다음 단계로 전달한다.

2. **Build Stage**  
   : Source 단계에서 전달받은 코드를 컴파일・빌드・테스트하여 가공한 후, 결과물을 다음 단계로 전달한다.

3. **Deploy Stage**  
   : Build 단계에서 전달받은 결과물을 실제 서비스에 반영하는 작업을 수행한다.

<br>

> 🚨 파이프라인의 단계는 상황과 필요에 따라 세분화되거나 간소화될 수 있다.

<br>

***

<br>

## 🛠 AWS Pipeline을 통한 배포 자동화

<br>

### AWS 개발자 도구

<br>

AWS 개발자 도구 섹션에서 제공하는 서비스를 활용하여 배포 자동화 파이프라인을 구축할 수 있다.

<br>

**🔸 CodeCommit**

Source Stage를 구성할 때 사용하며, Github와 유사한 서비스를 제공하는 **버전 관리 도구**이다.

Github에 비해 보안 관련 기능에 강점을 가지지만,  

사용 요금에 대한 부담이 있을 수 있다.

<br>

**🔸 CodeBuild**

Build Stage에서 사용하며, **유닛 테스트, 컴파일, 빌드**와 같은 Build Stage에서 필수적으로 실행되어야할 작업들을 명령어를 통해 실행할 수 있다.

> 사용자가 작성한 ```buildspec.yml``` 파일을 참조하여 작업을 수행한다.

<br>

**🔸 CodeDeploy**

Deploy Stage에서 사용되며, 실행되고 있는 서버 어플리케이션에 **실시간으로 변경사항을 전달**할 수 있다.

또한 S3 서비스를 통해 S3 버킷으로 업로드 된 **정적 웹사이트에도 변경사항을 실시간으로 전달하고 반영**할 수 있다.

> 사용자가 작성한 ```appspec.yml``` 파일을 참조하여 작업을 수행한다.

<br>

**🔸 CodePipeline**

각 단계를 연결하는 파이프라인을 구축할 때 사용한다.

<br><br>

### 개발 환경 구축

<br>

먼저 EC2 인스턴스에 배포 자동화에 필요한 프로그램들을 설치해야한다.

<br>

**1. Java 설치**

```bash
$ sudo apt update
$ sudo apt install openjdk-11-jre-headless
```

<br>

**2. AWS CLI 설치**

```bash
$ curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
$ sudo apt install unzip
$ unzip awscliv2.zip
$ sudo ./aws/install

# 설치 여부 확인
$ aws --version
```

<br>

**3. CodeDeploy Agent 설치**

```bash
$ sudo apt update
$ sudo apt install ruby-full  # [Y / n] 선택시 Y 입력
$ sudo apt install wget
$ cd /home/ubuntu
$ sudo wget https://aws-codedeploy-ap-northeast-2.s3.ap-northeast-2.amazonaws.com/latest/install
$ sudo chmod +x ./install
$ sudo ./install auto > /tmp/logfile

# 서비스 실행 상태 확인 (active → 실행 중)
$ sudo service codedeploy-agent status
```

<br><br>

### EC2 인스턴스 역할 부여

<br>

1️⃣ EC2 → 인스턴스 → 인스턴스 선택 후 ```[태그]``` 확인

- Key - Value 쌍의 값이 설정되어 있다.

<br>

2️⃣ 인스턴스 선택 후 ```[보안]``` → ```[IAM 역할]``` → ```[권한]``` 확인

- **AmazonEC2RoleForSSM** : Session Manager를 통해 EC2 인스턴스에 연결하기 위한 권한

- **AmazonS3FullAccess**

- **AmazonEC2RoleforAWSCodeDeploy**

- **AWSCodeDeployRole**

- **AmazonSSMFullAccess**

위의 권한들 선택 후 ```[정책 연결]```

<br>

3️⃣ ```[IAM 역할]``` → ```[신뢰 관계]``` → ```[신뢰 정책 편집]```

- 신뢰 관계란, 해당 역할을 취할 수 있는 서비스나 사용자를 명시하는 부분이다.

- Access 정책 부여를 통해 역할을 생성했지만, Access 할 수 있는 서비스를 신뢰 관계에서 명시함으로써 역할이 확실히 완성된다.

<br>

```json
"Service": "ec2.amazonaws.com"

  ▼ 아래와 같이 수정 ▼

"Service": ["ec2.amazonaws.com", "codedeploy.ap-northeast-2.amazonaws.com"]
```

<br><br>

### EC2를 활용한 파이프라인 구축 예제

<br>

1️⃣ 로컬 환경의 ```project_name/DeployServer``` 경로에 ```appspec.yml``` 파일을 생성

- ```appspec.yml```은 CodeDeploy-Agent가 인식하는 파일이다.

- CodeDeploy에서 지정한 각 단계에 맞춰 어떠한 Shell Script를 실행하는지 지정한다.
  
  <br>

  ```yml
  version: 0.0
  os: linux

  files:
    - source: /
      destination: /home/ubuntu/build

  hooks:
    BeforeInstall:
      - location: server_clear.sh
        timeout: 3000
        runas: root
    AfterInstall:
      - location: initialize.sh
        timeout: 3000
        runas: root
    ApplicationStart:
      - location: server_start.sh
        timeout: 3000
        runas: root
    ApplicationStop:
      - location: server_stop.sh
        timeout: 3000
        runas: root
  ```

  ▲ _appspec.yml_

<br><br>

2️⃣ 로컬 환경의 ```project_name/DeployServer``` 경로에 ```buildspec.yml``` 파일을 생성

- ```buildspec.yml```은 CodeBuild-Agent가 인식하는 파일이다.

- CodeBuild에서 지정한 각 단계에 맞춰 동작을 특정하여 명령한다.

  <br>

  ```yml
  version: 0.2

  phases:
    install:
      runtime-versions:
        java: corretto11
    build:
      commands:
        - echo Build Starting on `date`
        - cd DeployServer
        - chmod +x ./gradlew
        - ./gradlew build
    post_build:
      commands:
        - echo $(basename ./DeployServer/build/libs/*.jar)
  artifacts:
    files:
      - DeployServer/build/libs/*.jar
      - DeployServer/scripts/**
      - DeployServer/appspec.yml
    discard-paths: yes
  ```

  ▲ _buildspec.yml_

<br><br>

3️⃣ 프로젝트 최상위에 ```scripts``` 디렉토리를 생성하고,  
&emsp; ```initialize.sh```, ```server_clear.sh```, ```server_start.sh```, ```server_stop.sh``` 파일을 생성

- 각 파일은 ```appspec.yml```이 구성하고 있는 배포 수명 주기에 따라서 실행된다.

  <br>

  ```yml
  #!/usr/bin/env bash
  chmod +x /home/ubuntu/build/**
  ```

  ▲ _initialize.sh는 빌드 결과물을 실행할 수 있도록 **실행 권한을 추가**한다._

  <br>

  ```yml
  #!/usr/bin/env bash
  rm -rf /home/ubuntu/build
  ```

  ▲ _server_clear.sh는 빌드 결과물이 저장 되어있는 build 디렉토리를 제거한다._

  <br>

  ```yml
  #!/usr/bin/env bash
  cd /home/ubuntu/build
  sudo nohup java -jar DeployServer-0.0.1-SNAPSHOT.jar > /dev/null 2> /dev/null < /dev/null &
  ```

  ▲ _server_start.sh는 ```DeployServer-0.0.1-SNAPSHOT.jar```라는 빌드 결과물을 실행한다._

  <br>

  ```yml
  #!/usr/bin/env bash
  sudo pkill -f 'java -jar'
  ```

  ▲ _server_stop.sh는 실행중인 Spring Boot 프로젝트를 종료한다._

<br><br>

4️⃣ AWS CodeDeploy → 배포 → 어플리케이션 → ```[어플리케이션 생성]```

- 리소스명으로 어플리케이션 이름을 입력

- **EC2/온프레미스**로 컴퓨팅 플랫폼을 설정

<br><br>

5️⃣ 생성된 어플리케이션의 ```[배포 그룹]``` → ```[배포 그룹 생성]```

- 배포 그룹의 이름은 ```리소스명-group``` 형태로 입력

- **EC2 인스턴스에 연결되어있는 IAM역할**로 서비스 역할 영역을 설정

- **Amazon EC2 인스턴스**로 환경 구성 선택 후, 위에서 확인한 Name 태그의 Key - Value를 태그 그룹에 입력

- 로드 밸런싱 활성화 체크 해제

<br><br>

6️⃣ AWS CodePipeline → 파이프라인 → ```[파이프라인 생성]```

- 리소스명으로 파이프라인 이름을 입력

- Github(버전2)로 소스 공급자 설정 후, ```[GitHub에 연결]``` → Only select repositories

- 레포지토리 이름과 브랜치 이름 설정

- 출력 아티팩트 형식은 **CodePipeline 기본값**으로 설정

- 빌드 스테이지의 빌드 공급자는 **AWS CodeBuild**로 설정 후, ```[프로젝트 생성]```

  - 리소스명으로 프로젝트 이름 입력

  - 운영 체제는 **Amazon Linux 2**로 설정

  - 런타임은 **Standard**로 설정

  - 이미지는 **aws/codebuild/amazonlinux2-x86_64-standard:3.0**으로 설정

  - Buildspec은 **DeployServer/buildspec.yml**을 입력

  <br>

- 배포 스테이지  

  - 배포 공급자는 **AWS CodeDeploy**로 설정

  - 리전은 **아시아 태평양(서울)**로 설정

  - 어플리케이션 이름은 생성해둔 어플리케이션 이름 입력

  - 배포 그룹은 생성해둔 배포 그룹 이름 입력

  <br>

7️⃣ 파이프라인 생성과 동시에 **소스 코드의 배포가 자동으로 실행**

<br>

> 🚨 **Deploy Stage에서 실패가 발생하는 경우**
>
> EC2 인스턴스의 터미널에 ```cd /opt/codedeploy-agent/deployment-root/deployment-logs```를 입력
>
> 해당 위치의 로그 파일을 확인하여 stdout, stderr 확인 가능

<br><br>

### 환경 변수 설정

<br>

1️⃣ AWS Parameter Store → ```[파라미터 생성]```

- 이름에 환경 변수명 ```/spring-boot-aws/리소스명/spring.datasource.url```을 입력

  > ```/prefix/name/key```의 순서로 네이밍 규칙에 맞게 작성되어야 한다.

  <br>

- 원하는 유형으로 값을 저장

- 값에 원하는 환경 변수를 입력

<br><br>

2️⃣ build.gradle 파일 수정

```java
dependencies {
    implementation 'org.springframework.cloud:spring-cloud-starter-aws-parameter-store-config'
}

dependencyManagement {
	imports {
		mavenBom "org.springframework.cloud:spring-cloud-starter-parent:Hoxton.SR12"
	}
}
```

▲ _build.gradle 코드 추가_

<br><br>

3️⃣ ```/src/main/resources/``` 경로에 ```bootstrap.yml``` 파일 생성

```yml
aws:
  paramstore:
    enabled: true
    prefix: /spring-boot-aws
    name: # 리소스명 작성
    profileSeparator: _
```

▲ _bootstrap.yml은 AWS Parameter Store에 저장된 변수를 조회하는 파일이다._

<br><br>

4️⃣ ```application.properties``` 수정

```properties
server.port=8080
spring.jpa.database=mysql
spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect
# spring.datasource.url=jdbc:mysql://{AWS RDS Endpoint}/test?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC
# spring.datasource.username={RDS Mysql Admin id}
# spring.datasource.password={RDS Mysql Admin password}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
# config.domain={AWS S3 Endpoint}
```

▲ _Parameter Store에 환경 변수를 추가했으므로, 해당 설정들은 필요하지 않다._

<br>

5️⃣ Commit, Push 한 후, 파이프라인을 통해 변경사항을 전달

<br>

***

<br>

## 🛠 Github Actions를 통한 배포 자동화


<br><br>

***

_2022.12.10. Update_