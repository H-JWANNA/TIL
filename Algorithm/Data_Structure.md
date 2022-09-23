# 자료 구조

자료 구조란 여러 데이터의 묶음을 저장하고, 사용하는 방법을 정의한 것으로

자료 구조의 종류로는 ```Stack```, ```Queue```, ```Graph```, ```Tree```, ```Binary Search Tree```, ```Search Algorithm```, ```Deque```, ```Linked List```, ```Hash Table```, ```Heap Tree``` 등 여러 종류가 존재한다.

<img src = "https://cheris8.github.io/assets/images/PY/datastructure-overview.png"/>

▲ _Type of Data Structure_

<br>

## Stack (스택)

Stack은 data를 순서대로 쌓는 자료 구조이다.

> ex) 인터넷 브라우저 (뒤로가기 앞으로가기)

<br>

<img src = "https://images.velog.io/images/sbinha/post/17a3cf61-fb95-4970-b66c-92a71b99846b/Screenshot%202020-04-20%2019.07.55.png" width = "90%"/>

▲ _Stack_

<br>

### Stack의 특징

1. Last In First Out (LIFO)
   
2. 데이터는 하나씩 넣고 뺄 수 있다.  

3. 하나의 입출력 방향을 가지고 있다.  

<br>

### Stack 메서드

```java
import java.util.Stack;

Stack<T> stack = new Stack<>();
```
▲ _Stack 선언_

<br>

**Stack 메서드**

|기능	|리턴<br>타입	|메서드	|설명|
|:-:|:-:|:-----|:-|
|객체<br>추가|Element|```push(e)``` | Stack에 데이터 추가|
|객체<br>삭제|Element|```pop()``` | 가장 위쪽의 데이터 제거|
||void|```clear()``` | Stack의 데이터 모두 제거|
|객체<br>검색|Element|```peek()``` | Stack 가장 상단의 데이터 출력|
||int|```size()``` | Stack의 크기 출력|
||int|```search(Object o)``` | Object의 index 리턴 <br> index는 스택의 최상단이 1부터 시작 <br> Object를 찾을 수 없는 경우 ```-1``` 리턴|
||boolean|```empty()``` | Stack이 비어있는지 확인|
||boolean|```contains(e)``` | Stack에 Object가 있는지 확인|
||String|```show()```| Stack에 포함된 모든 데이터를 String 타입으로 반환하여 리턴|

<br><br>

## Queue (큐)

Queue는 data가 입력된 순서대로 처리하는 자료 구조이다.

> ex) 프린터의 순차적 출력

<br>

<img src = "https://programmathically.com/wp-content/uploads/2021/06/queue.png" width = "90%"/>

▲ _Queue_

<br>

### Queue의 특징

1. First in First Out (FIFO)

2. 데이터는 하나씩 넣고 뺄 수 있다.

3. 두 개의 입출력 방향을 가지고 있다.  
   입력이 이루어지는 쪽을 ```rear```, 출력이 이루어지는 쪽을 ```front```라고 한다.  
   값을 입력할수록 rear 값이 증가하고, 값을 출력할수록 front 값이 증가한다.

<br>

### Queue 메서드

```java
import java.util.Queue;
import java.util.LinkedList;

Queue<T> queue = new LinkedList<>();
```
▲ _Queue 선언_

<br>

**Queue 메서드**

|기능	|리턴<br>타입	|메서드	|설명|
|:-:|:-:|:-----|:------------------|
|객체<br>추가| boolean | ```add(e)``` | Queue에 데이터 추가 <br> 큐가 꽉 찬 경우 ```IllegalStateException``` 발생 |
|| boolean | ```offer(e)``` | Queue에 데이터 추가 / 실패 시 ```false``` 리턴 |
|객체<br>삭제| Element | ```poll()``` | Queue 가장 앞쪽의 데이터 제거<br>큐가 비어있으면 ```null``` 리턴 |
|| Element | ```remove()``` | 인자가 없는 경우 Queue 가장 앞쪽의 데이터 제거 <br> 큐가 비어있는 경우 ```NoSuchElementException``` 발생  |
||boolean| ```remove(o)``` | 인자가 있는 경우 해당 데이터를 제거 / 실패 시 ```false``` 리턴 |
|| void | ```clear()``` | Queue의 데이터 모두 제거 |
|객체<br>검색| Element | ```peek()```  | Queue 가장 앞쪽의 데이터 출력 <br> 인자가 없는 경우 ```null``` 리턴|
|| Element | ```element()```  | Queue 가장 앞쪽의 데이터 출력 <br> 큐가 비어있는 경우 ```NoSuchElementException``` 발생 |

<br>

### 📋 [**_추가 : Circle Queue_**](https://haruhiism.tistory.com/144)

<br><br>

## Tree

Tree는 단방향 그래프의 구조로 하나의 최상위 뿌리(Root)로부터 가지가 사방으로 뻗은 형태의 자료 구조이다.

데이터를 순차적으로 나열시킨 선형 구조가 아닌,  
하나의 데이터 아래에 여러 데이터가 존재할 수 있는 **비선형 구조**이다.

> ex) 폴더 디렉토리, 조직도, 토너먼트 대진표

<br>

<img src = "https://miro.medium.com/max/720/1*pJTKvuoJZZdW8VJ17GILww.png" width = "90%"/>

▲ _Tree_

<br>

### Tree의 특징

- 깊이 (depth)  
  루트부터 하위 계층의 특정 노드까지의 깊이를 표현할 수 있다.  
  루트에서부터 0으로 시작하여 하위 계층으로 연결될 수록 1씩 증가한다.

- 레벨 (Level)  
  같은 깊이를 가지는 노드를 묶어서 레벨로 표현하고,  
  같은 레벨에 나란히 있는 노드를 형제 노드(Sibling Node)라고 한다.

- 높이 (Height)  
  리프 노드(자식이 없는 노드)로부터 루트까지의 높이를 표현할 수있다.  
  리프에서부터 0으로 시작하여 부모 노드로 연결될 수록 1씩 증가한다.

- 서브 트리 (Sub Tree)  
  루트에서 뻗어 나오는 큰 트리의 내부에, 트리 구조를 갖춘 **작은 트리**를 말한다.  
  부모와 자식 둘 이상 연결되면 최소 크기의 서브 트리가 만들어진다.

<br><br>

## Graph

그래프는 여러 점들이 서로 복잡하게 연결된 관계를 표현한 자료 구조이다.

> ex) 네비게이션

<br>

<img src ="https://media.springernature.com/lw685/springer-static/image/art%3A10.1038%2Fs41598-021-93161-4/MediaObjects/41598_2021_93161_Fig2_HTML.png" width = "90%">

▲ _Graph_

<br>

### Graph의 특징

- 두 점 사이가 이어져있을 경우 **직접적인 관계**라고 하며,  
  몇 개의 점과 선 사이에 걸쳐서 이어질 경우 **간접적인 관계**라고 한다.

- 하나의 점을 정점(vertex)이라고 하고, 하나의 선을 간선(edge)이라고 한다.  
  간선의 경우 단방향 간선과 양방향 간선이 존재한다.

<br>

### Graph의 표현 방식

<br>

**인접 행렬**

두 점을 직접적으로 이어주는 간선이 있다면 두 정점은 **인접하다**라고 표현할 수 있다.  

인접 행렬은 정점들이 서로 인접한 상태인지를 표시한 행렬로 2차원 배열로 나타낸다.  
이어져있다면 ```1 (true)```, 이어져 있지 않다면 ```0 (false)```으로 표시한다.

위 그림 (c)를 인접 행렬로 표현하면  
3은 1과 4로 향하므로 ```[3][1] == 1```, ```[3][4] == 1```과 같이 표현할 수 있다.

> 인접 행렬은 최단 경로를 찾을 때 주로 사용한다.  
> 단, 메모리 낭비가 심하다는 단점이 있다.  
> 
> 또한, 두 정점 사이에 관계가 있는지 없는지 확인하기에 용이하다.

<br>

**인접 리스트**

각 정점이 어떤 정점과 인접하는지를 리스트의 형태로 표현한 것이다.  
<span style = "color : gray"> _(주로 LinkedList를 사용)_ </span>

위 그림 (c)를 인접 리스트로 표현하면 아래와 같이 표현할 수 있다.   
- 1 → 2 → /
- 2 → 3 → /
- 3 → 1 → 4 → /
- 4 → /

순서는 보통 중요하지 않으며, 우선 순위를 다뤄야한다면 Queue, Heap 등을 사용하는 것이 좋다.

> 인접 리스트는 메모리를 효율적으로 사용하고 싶을 때 사용한다.

<br>
<details>
<summary>알아두면 좋은 그래프 용어들 (Click)</summary>
<div markdown="1">
<br>

|용어| 설명|
|:-:|:-|
|정점 (vertex) | 노드(node)라고도 하며 데이터가 저장되는 그래프의 기본 원소|
|간선 (edge) | 정점 간의 관계를 나타낸 선 (정점을 이어주는 선)|
|인접 정점<br>(adjacent vertex) | 하나의 정점에서 간선에 의해 직접 연결되어 있는 정점|
|가중치 그래프<br>(weighted Graph)| 연결의 강도(추가적인 정보)가 얼마나 되는지 적혀져 있는 그래프<br>인접 행렬을 예로 들자면 ```1``` 값이 아닌 관계에서 의미있는 값을 저장|
|비가중치 그래프<br>(unweighted Graph)| 연결의 강도가 적혀져 있지 않는 그래프|
|무방향 그래프<br>(undirected graph)| 예를 들어 서울에서 부산으로 갈 수 있듯, 부산에서 서울로 가는 것도 가능한데,<br>이것을 무방향 그래프라고 한다. <br>하지만 단방향(directed) 그래프로 구현된다면 서울에서 부산을 갈 수 있지만, 부산에서 서울로 가는 것은 불가능하다. <br>만약 두 지점이 일방통행 도로로 이어져 있다면 단방향인 간선으로 표현할 수 있다.|
|진입차수<br>(in-degree) <br> 진출차수<br>(out-degree)| 한 정점에 진입(들어오는 간선)하고 진출(나가는 간선)하는 간선이 몇 개인지를 나타낸다.|
|인접<br>(adjacency)| 두 정점 간에 간선이 직접 이어져 있다면 이 두 정점은 인접한 정점이라고 한다.|
|자기 루프<br>(self loop)| 정점에서 진출하는 간선이 곧바로 자기 자신에게 진입하는 경우 <br>다른 정점을 거치지 않는다는 것이 특징|
|사이클 (cycle)| 한 정점에서 출발하여 다시 해당 정점으로 돌아갈 수 있다면 사이클이 있다고 표현한다.|

</div>
</details>

<br><br>

## Binary Search Tree

이진 트리(Binary Tree)는 자식 노드가 최대 2개인 노드들로 구성된 트리를 말하고,  
이진 탐색 트리(Binary Search Tree)는 모든 왼쪽 자식의 값이 루트나 부모보다 작고,  
모든 오른쪽 자식의 값이 루트나 부모보다 큰 값을 가진다.

> 이진 탐색 트리는 입력되는 값의 순서에 따라 한쪽으로 노드들이 몰리게 될 수 있다.  
> 균형이 잡히지 않은 트리는 탐색하는 데 시간이 더 걸리는 경우도 있기 때문에  
> 이 문제를 해결하기 위해 삽입과 삭제마다 트리의 구조를 재조정하는 알고리즘을 추가할 수 있다.

<br>

<img src = "https://static.packt-cdn.com/products/9781789801736/graphics/C09581_08_02.jpg" width = "90%">

▲ _Binary Search Tree_

<br>

### 이진 트리의 종류

- **정 이진 트리 (Full binary tree)**  
  각 노드가 0개 혹은 2개의 자식 노드를 가진다.

- **완전 이진 트리 (Complete binary tree)**  
  마지막 레벨을 제외한 모든 노드가 가득 차 있어야 하며,  
  마지막 레벨의 노드는 왼쪽부터 채워져야 한다.

- **포화 이진 트리 (Perfect binary tree)**  
  정 이진 트리이면서 완전 이진 트리인 경우  
  모든 리프 노드의 레벨이 동일하고, 모든 레벨이 가득 채워져 있어야 한다.

<br>

***

_2022.09.22. Update_