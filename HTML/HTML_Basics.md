# HTML


들어가기에 앞서 _**웹페이지에서 HTML, CSS, JS의 역할은?**_
> HTML은 구조(Structure)를 담당하는 Markup Language  
> CSS는 스타일(Style, Presentation)을 담당하는 Style Sheets Language  
> JavaSctipt는 상호작용(Interaction)을 담당하는 Programming Language

<br>

## HTML (HyperText Markup Language)

### 💡 Markup Language란?

- 마크업 언어(Markup Language)는 태그(```<tag>```) 등을 이용하여 문서나, 데이터의 논리적인 구조릴 명시하기 위한 규칙들을 정의한 언어의 일종입니다.

HTML은 정적인 페이지만을 만들기 때문에 프로그래밍 언어라고 정의하긴 어렵다.

HTML의 요소(Element)는 태그(Tag)와 내용(Contents)으로 구성되어 있다.

HTML은 트리구조로 형성되어 있다.

<br>

***

<br>

## HTML Tag (HTML 태그)

<br>

<img src = "https://www.advancedwebranking.com/seo/wp-content/uploads/2021/11/elements.png" width="100%">  

*시작에 앞서 웹페이지에서 많이 사용되는 태그를 알아보았다.*

<br>

<img src = "https://www.hackerhero.com/blog/wp-content/uploads/2020/04/important-html-tags-1024x617.png" width = "100%">

*각 태그의 중요도와 사용량은 비슷한 것 같다.*

<br>

### 🔸 &lt;html&gt;
- 웹페이지를 시작할 때 정의하는 태그
- 이 페이지가 HTML이라는 걸 정의하는 건가? 사실 정확히 이해는 못했다.

### 🔸 &lt;head&gt;
- head 태그 안에 인코딩 언어 정의나 css나 js와의 링크를 한다. 
- &lt;title&gt;, &lt;meta&gt;, &lt;link&gt; 등이 head 태그 안에 들어간다.
  
> &lt;title&gt; : 제목표시줄이나 탭 창에 표시되는 내용  
>&lt;meta&gt; : 문자의 인코딩에 주로 사용. ex) ```<meta charset = "UTF-8" />```   
>&lt;link&gt; : 외부 파일을 연결할 때 사용. ex) ```<link rel="stylesheet" href="test.css" />```  
>-  다른 폴더의 파일을 연결할 때는 ```test.css```자리에 ```../CSS/test.css```와 같이 사용  
>-  ```../```를 사용하면 상위폴더로 이동  
>-  ```<link>```를 사용하면 CSS파일 연결, ```<style>```은 HTML 내부에서 스타일 정의 (```<head>```에서 정의)

### 🔸 &lt;body&gt;
- 말 그대로 몸통. 우리가 보는 웹 브라우저의 실제 표시 부분

### 🔸 &lt;header&gt;
- 머리말? 제목?

### 🔸 &lt;footer&gt;
- 꼬리말? 보통 제작 정보나 저작권 정보, 우편 주소, 연락처 정보등을 작성

### 🔸 &lt;h1~6&gt;
- 제목을 표시할 때 사용. 1에서 6으로 갈수록 작아짐
- Markdown 문법이 Markup 문법이라는 것을 여기서 알았음

### 🔸 &lt;div&gt;
- 컨텐츠들을 묶어야 할 때 사용한다.
> ```<div>```와 ```<span>```의 차이점 : div 태그는 한 줄을 사용하고, span은 컨텐츠 크기만큼 공간을 차지  
> - ```<div>```는 Block-level Element, ```<span>```은 Inline Element로 구분할 수 있다.   
> <br>   
> 
> ```<div>```와 ```<section>```의 차이점 : &lt;section&gt; 태그는 제목, 바닥 글, 하위 섹션, 페이지의 주요 부분, 탭 인터페이스의 페이지와 같은 관계있는 요소를 분리할때 사용한다.<br>   
> ```<div>```와 ```<article>```의 차이점 : &lt;article&gt; 태그는 웹 로그 기사, 포럼 게시물 또는 댓글과 같이 문서 혹은 요소가 독립적으로 존재할 수 있을 때 사용한다.
> - ```<section>```과 ```<article>``` 두 태그는 ```<div>```와 같은 Block 속성 태그이지만 Semantic tag이다  
> - 🔔 Semanctic tag는 모두 ```<div>```와 같은 기능을 수행하지만, 태그에 의미를 부여해서 데이터를 효율적으로 추출할 수 있게끔 한다.

### 🔸 &lt;a&gt;
- anchor의 축약어로 외부 문서나 사이트를 연결할 때 사용한다. ex) ```<a href="url"> text </a>```
> ```<a>```에서 사용할 수 있는 속성 값
> - target = "_blank" : 새 탭(창)에서 열기
> - target = "_self" : 현재 탭(창)에서 열기
> - target = "_parent" : 현재 화면을 불러낸 부모 탭(창)에서 열기, 없으면 현재 탭(창)
> - target = "_top" : 최상위 탭(창)에서 열기
> - title = "text" : 링크에 커서를 올렸을 때 "text" 툴팁 생성

### 🔸 &lt;img&gt;
- 이미지 삽입. ex) ```<img src = "이미지 경로">```
> ```<img>```에서 사용할 수 있는 속성 값
> - "width = 50vw" : 너비 50% &emsp; "min-width = 100px" : 최소 너비 100px
> - "height = 50vw" : 높이 50% &emsp; "min-height = 100px" : 최소 높이 100px
> - 여러 속성을 삽입할 때에는 ```"width = 50vw ; min-width = 100px"```와 같이 따옴표("") 안에 세미콜론(;)으로 구분

### 🔸 &lt;p&gt;
- Paragraph. 문단을 생성함. Block-level Element

### 🔸 &lt;ul&gt;
- Unordered List. 순서가 없는 List
>- iPhone  
>- iPad  
>- iPod

### 🔸 &lt;ol&gt;
- Ordered List. 순서가 있는 List
>1. iPhone  
>2. iPad  
>3. iPod

### 🔸 &lt;li&gt;
- List Item. ```<ul>```과 ```<ol>```에서 항목을 나열할 때 사용

### 🔸 &lt;input&gt;
- 사용자가 정보를 입력하는 부분을 만들때 사용
> ```<input type = " ">```에서 사용할 수 있는 속성 값
> - text : 한 줄짜리 텍스트 상자, placeholder=" " 속성을 통해 기본 힌트 설정
> - password : 비밀번호 입력 상자
> - search : 검색 상자
> - checkbox : 체크박스, checked 속성을 통해 미리 체크가 되어있게 할 수 있음
> - radio : 라디오버튼, name=" " 속성을 통해 2개의 라디오버튼 중 택 1이 가능하게 할 수 있음
> - button : 버튼, ```<button>text</button>``` 형식으로도 제작 가능

### 🔸 &lt;br&gt;
- 줄바꿈

### 🔸 &lt;nav&gt;
- 문서 연결 링크. ```<div>```와 비슷한 역할을 하는 semantic tag

<br>

### 📋 **더욱 자세한 태그는 [_MDN_](https://developer.mozilla.org/ko/docs/Web/HTML/Element) 참조**

<br>

***


<span style="color: gray ; display: inline-block; width: 95%; text-align: right;">_2022.08.23._</span>