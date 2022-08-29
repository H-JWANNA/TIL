# Git

_Git과 Github의 차이_
> **Git** : 소스 코드 기록을 관리하고 추적할 수 있는 **버전관리 시스템**  
> **Github** : Git Repository를 관리할 수 있는 클라우드 기반 서비스

<br>

Git의 영역
- Working Directory
- Staging Area
- Local Repository

GitHub의 영역
- Remote Repository

<br>

<img src = "https://cloudstudio.com.au/wp-content/uploads/2021/06/GitWorkflow-4-768x495.png" width = "80%" />

<span style = "color: gray"> ▲ _Git Workflow & Commands_ </span>

<br>

___

<br>

## Git 명령어

<br>

### 🔸 Workflow관련

<br>

```git add [파일명]``` : Working Directory의 작업 파일을 Staging Area에 옮긴다.  

```git commit -m 'text'``` : Staging Area에 있는 작업 파일을 Local Repository에 Commit한다.  
 text란에 Commit 메시지를 간단하게 적는다. [📋 **_Git Commit Message_**](https://cbea.ms/git-commit/)  
 커밋을 1회이상 한 상태에서는 ```git commit -am 'text'```를 통해서 add를 생략하고 커밋 가능  


```git push [Repo] [Brench]``` : Local Repository에 있는 작업 파일을 Remote Repository에 업로드한다.   

```git fetch``` : <span style = "color: gray"> _미지의영역_ </span>   

```git merge``` : <span style = "color: gray"> _미지의영역_ </span>   

```git pull [Repo] [Brench]``` : Remote Repository에 있는 파일을 Working Directory로 받아온다.

```git restore [파일명]``` : 수정 후 add 하기전에 변경사항 취소  
add 한 후에는 ```git restore --staged file```를 통해 다시 unstage로 만들 수 있음 (수정 사항은 취소 안됨)

```git reset HEAD^``` : 최근 Commit을 취소하고 Work Directory로 돌려보낸다.

<br>

### 🔸 기타 명령어

<br>

```git init``` : 폴더 내에 ```.git```을 생성함으로써 현재 디렉토리를 Git Repository로 생성한다.  

```git status``` : 현재 Git 프로젝트의 상태를 보여준다. (변경, 추가, 삭제 파일 등)  

```git remote```  
- ```add [name(origin)] [URL]``` : URL의 Repository를 name에 불러온다.  
- ```-v``` : 현재 원격 저장소 목록을 확인한다.  
  - ```fretch```의 url은 pull 해오는 장소 ```push```의 url은 push를 하는 장소이다.

```git clone [Repository URL]``` : Github Repository를 내 컴퓨터의 디렉토리로 복제한다.  

```git log``` : 최근 Commit 로그를 확인한다.  

```git checkout``` : <span style = "color: gray"> _미지의영역_ </span>   


### 🔸 추가 용어

```Fork``` : 기존 Open Source Repository를 내 Repository로 복제해온다.  
```Pull Request``` : 내 Repository의 수정 사항을 기존 Repository에 수졍 요청하는 과정  
```Merge``` : 상대방의 작업물과 나의 작업물을 취합하는 과정  
```Branch``` : Main에서 독립적으로 작업을 하기 위한 각각의 영역  
```Upstream``` : 소스 코드 원본을 제공해주는 Repository  
```Downstream``` : 소스 코드를 받아온 Repository



<br>

### [📋 **더 많은 Git 관련 Refernce**](https://backlog.com/git-tutorial/kr/)

<br>

***


<span style="color: gray ; display: inline-block; width: 95%; text-align: right;">_2022.08.29._</span>