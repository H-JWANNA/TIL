# Layout

> ```display : flex``` &ensp;-  &ensp;  Flexbox에 대해 이해하기


<br>

## HTML 구성

<br>

콘턴츠의 흐름에 따라 수직분할과 수평분할을 적용하여 작업을 진행한다.

수직분할한 요소를 수평으로 구분하여 콘텐츠가 세로로 배치되게 한다.

<br>

***

<br>

## Flexbox

<br>

### 💡 Flexbox의 특징
- 부모에게 말을 걸어 자식을 조종한다. (Container > Item)
- 2가지 축(main axis, cross axis)으로 구성되어 있지만, 축의 방향이 항상 일정하지는 않다.

<br>

### flex container 속성
- ```flex-direction:``` ```row```_(default)_, ```row-reverse```, ```column```, ```column-reverse```
    - flex container의 **main axis 방향을 결정**
- ```flex-wrap:``` ```nowrap```_(default)_, ```wrap```, ```wrap-reverse```
  - 요소 자동 **줄바꿈**
- ```flex-flow:``` ```flex-direction + flex-wrap``` 인자 2개를 받는다 (direction wrap 순으로)
- ```justify-align:``` main axis를 기준으로 하는 정렬
  - ```flex-start``` : start 정렬
  - ```flex-end``` : end 정렬
  - ```center``` : 가운데 정렬
  - ```space-between```, ```space-around```, ```space-evenly``` : 양쪽 정렬 (between이 간격 가장 넓음)


- ```align-items:``` cross axis를 기준으로 하는 정렬
  - ```flex-start``` : start 정렬
  - ```flex-end``` : end 정렬
  - ```center``` : 가운데 정렬
  - ```baseline```, ```scratch```_(default)_ 는 아직 잘 모름. 

- ```align-self:``` align-items 인자 안에서 items 옵션을 무시하고 설정가능
- ```align-content:``` 여러줄의 content가 있을 경우 줄간격 지정
  - ```flex-start``` : start쪽으로 정렬
  - ```flex-end``` : end쪽으로 정렬
  - ```center``` : 가운데쪽으로 정렬
  - ```space-between```, ```space-around```, ```space-evenly``` : 양쪽 정렬 (between이 간격 가장 넓음)
  - ```baseline```, ```scratch```_(default)_ 는 아직 잘 모름. 

<br>

### flex item 속성
- ```flex:``` ```flex-grow``` ```flex-shrink``` ```flex-basis``` 순으로 작성. 기본값은 ```flex: 0 1 auto```
- ```flex-grow:``` 팽창 지수. 자식들의 팽창지수 비율만큼 공간을 차지
- ```flex-shrink:``` 수축 지수. ```flex-grow```와 함께 사용은 미추천. ```width```나 ```flex-basis``` 속성에 따른 비율
- ```flex-basis:``` 기본 크기. ```auto```로 설정하면, content 크기만큼 기본값 설정.
  - ```flex-basis: 0```과 ```flex-basis: auto``` 중 ```flex-grow``` 속성 값이 동일할 때 box 크기가 동일한 것은 전자
- ```order:``` ```order: <interger>```방식을 통해 item의 순서를 설정(..., -1, 0, 1, ...)

<br>

### &emsp; 📋 **[_flex 참조한 사이트_](https://d2.naver.com/helloworld/8540176)**

<br>

***


<span style="color: gray ; display: inline-block; width: 95%; text-align: right;">_2022.08.24._</span>