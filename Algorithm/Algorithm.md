# 알고리즘

알고리즘은 문제를 해결하기 위한 최적의 선택을 말하며,  

데이터 구조에 따라 적절한 알고리즘을 선택하는 것이 중요하다.

<br>

### **📌 바로가기**

| [Tree traversal](#tree-traversal)  | [DFS](#dfs-depth-first-search-깊이-우선-탐색)  | [BFS](#bfs-breadth-first-search-너비-우선-탐색)  | [Binary Search](#binary-search-algorithm-이진-탐색-알고리즘) |
|:-:|:-:|:-:|:-:|
| [**Brute Force**](#brute-force-algorithm-완전-탐색-알고리즘) | [**Greedy**](#greedy-algorithm-탐욕-알고리즘) | [**DP**](#dynamic-programming-동적-프로그래밍) | [**Algorithm with Math**](#algorithm-with-math) |

<br>

## 시간 복잡도 (Time Complexity)

입력값의 변화에 따라 연산을 실행할 때, 연산 횟수에 비해 얼마만큼의 시간이 걸리는 지를 나타낸 것

<br>

### Big-O 표기법

Big-O 표기법은 최악의 경우에 대한 시간 복잡도를 나타내는 표기법이다.

<img src = "https://miro.medium.com/max/1400/1*fmbUKjjIjEprF8tiQJWgIg.png" width = "90%"/>

<br>

#### 🔸 **O(1)**

Constant complexity라고 하며, 입력 값의 증가와 관계없이 즉시 출력값을 얻을 수 있다.

```java
String[] arr = new String[] {"A", "B", "C"};

System.out.println(arr[1]);
```

> 이외에도 ArrayList의 ```get()```, Stack과 Queue의 ```pop()```, ```poll()``` 등의 메서드가 O(1)의 시간 복잡도를 가진다.

<br>

#### 🔸 **O(n)**

Linear complexity라고 하며, 입력 값의 증가에 따라 시간 또한 같은 비율로 증가한다.

```java
String[] arr = new String[] {"A", "B", "C"};

for(String i : arr) {
  System.out.println(i);
}
```

> 이외에도 ArrayList의 ```remove()```, ```contains()``` 등 다양한 메서드 들이 O(n)의 시간 복잡도를 가진다.

<br>

#### 🔸 **O(log n)**

Logarithmic complexity라고 하며, ```O(1)``` 다음으로 빠른 시간 복잡도이다.

가장 대표적인 예시로 이진 탐색을 들 수 있다.

```java
int binarySearch1(int key, int low, int high) {
	int mid;

	if(low <= high) {
		mid = (low + high) / 2;

    // 탐색 성공
		if(key == arr[mid]) return mid;

		// 왼쪽 부분 arr[0]부터 arr[mid-1]에서 재탐색 
		else if(key < arr[mid]) return binarySearch1(key ,low, mid-1);  

		// 오른쪽 부분 - arr[mid+1]부터 arr[high]에서 재탐색 
		else return binarySearch1(key, mid+1, high); 
	}

	return -1; // 탐색 실패 
}
```
▲ _Binary Search in Java_

<br>

#### 🔸 **O(n<sup>2</sup>)**

Quadratic complexity라고 하며, 입력 값의 증가에 따라 시간이 제곱의 비율로 증가한다.

```java
int[][] edges = new int[][] {{0, 1}, {1, 3}, {2, 1}};

int max = 0;

for (int[] i : edges) {
  for (int j : i) {
    if (j > max) max = j;
  }
}
```
▲ _2차원 배열의 최댓값 구하기_

<br>

#### 🔸 **O(2<sup>n</sup>)**

Exponential complexity라고 하며, Big-O 표기법 중 가장 느린 시간 복잡도이다.

O(2<sup>n</sup>)의 시간 복잡도를 가진 알고리즘을 구현했다면, 다른 접근 방식을 찾아보자

```java
public int fibonacci(int n) {
	if(n <= 1) {
		return 1;
	}
	return fibonacci(n - 1) + fibonacci (n - 2);
}
```
▲ _피보나치 수열_

<br>

🔹 입력 값에 따른 시간 복잡도

| Complexity | 1 | 10 | 100 |
|:-----------|:--|:--|:------|
| O(1)      | 1 | 1 | 1 |
| O(log n)  | 0 | 2 | 5 |
| O(n)      | 1 | 10 | 100 |
| O(n log n) | 0 | 20 | 461 |
| O(n<sup>2</sup>) | 1 | 100 | 10000 |
| O(2<sup>n</sup>) | 1 | 1024 | 1267650600228229401496703205376 |
| O(n!)      | 1 | 3628880 | 표시 불가능 |

<br>

🔹 정렬 알고리즘의 시간 복잡도

| Sorting<br> Algorithms | Best | Average | Worst |
|:-----------|:--|:--|:------|
|Bubble Sort	|	O(n)|	O(n<sup>2</sup>)|	O(n<sup>2</sup>)|
|Heapsort		|O(n log n)|	O(n log n)	|O(n log n)|
|Insertion Sort	|	O(n)|	O(n<sup>2</sup>)|	O(n<sup>2</sup>)|
|Mergesort	|O(n log n)	|O(n log n)	|O(n log n)|
|Quicksort		|O(n log n)|	O(n log n)|	O(n<sup>2</sup>)|
|Selection Sort	|	O(n<sup>2</sup>)|	O(n<sup>2</sup>)|	O(n<sup>2</sup>)|
|Shell Sort	|	O(n)|	O(n log n<sup>2</sup>)	|O(n log n<sup>2</sup>)|
|Smooth Sort	|O(n)|	O(n log n)|	O(n log n)|

<br>

🔹 자료 구조의 시간 복잡도 (Worst Case)


|Data Structures |Search	|Insert|	Delete|
|:-|:-|:-|:-|
|Array	|O(n)|	N/A	|N/A|
|Sorted Array|	O(log n)	|O(n)|	O(n)|
|Linked List|	O(n)|	O(1)|	O(1)|
|Doubly Linked List	|O(n)|	O(1)	|O(1)|
|Stack|	O(n)	|O(1)	|O(1)|
|Hash table	|	O(n)	|O(n)|	O(n)|
|Binary Search Tree|		O(n)|	O(n)	|O(n)|
|B-Tree	|	O(log n)|	O(log n)|	O(log n)|
|Red-Black tree|	O(log n)|	O(log n)|	O(log n)|
|AVL Tree	|O(log n)|	O(log n)	|O(log n)|

<br>

💡 알고리즘 문제 풀이 시 대략적인 데이터 크기에 따른 시간 복잡도

- n ≤ 1,000,000 : O(n) or O(log n)
- n ≤ 10,000 : O(n<sup>2</sup>)
- n ≤ 500 : O(n<sup>3</sup>)

<br>

***

<br>

## Tree traversal

특정 목적을 위해 트리의 모든 노드를 한 번씩 방문하는 트리 순회

<br>

### 전위 순회 (Preorder Traverse)

Root → 왼쪽 자식 → 오른쪽 자식 순으로 순회

```java
public ArrayList<> preorder(Node root, int depth, ArrayList<> list) {
      if (root != null) {
        list.add(root.getData());
        preorder(root.getLeft(), depth + 1, list);
        preorder(root.getRight(), depth + 1, list);
      }
      return list;
    }
```
▲ _재귀를 통한 전위 순회_

<br>

### 중위 순회 (Inorder Traverse)

왼쪽 자식 → Root → 오른쪽 자식 순으로 순회

```java
public ArrayList<> inorder(Node root, int depth, ArrayList<> list) {
      if (root != null) {
        inorder(root.getLeft(), depth + 1, list);
        list.add(root.getData());
        inorder(root.getRight(), depth + 1, list);
      }
      return list;
    }
```
▲ _재귀를 통한 중위 순회_

<br>

### 후위 순회 (Postorder Traverse)

왼쪽 자식 → 오른쪽 자식 → Root 순으로 순회

```java
public ArrayList<> postorder(Node root, int depth, ArrayList<> list) {
      if (root != null) {
        postorder(root.getLeft(), depth + 1, list);
        postorder(root.getRight(), depth + 1, list);
        list.add(root.getData());
      }
      return list;
    }
```
▲ _재귀를 통한 후위 순회_

<br>

<img src = "https://mblogthumb-phinf.pstatic.net/20120331_173/rlakk11_1333202999001hceVs_JPEG/4.jpg?type=w2" width = "90%" />

위 Tree에서 전위 / 중위 / 후위 순회를 통한 노드 방문 순서는 아래와 같다.

- 전위 순회 : 0 → 1 → 3 → 7 → 8 → 4 → 9 → 10 → 2 → 5 → 11 → 6
- 중위 순회 : 7 → 3 → 8 → 1 → 9 → 4 → 10 → 0 → 11 → 5 → 2 → 6
- 후위 순회 : 7 → 8 → 3 → 9 → 10 → 4 → 1 → 11 → 5 → 6 → 2 → 0

<br>

***

<br>

## DFS / BFS (깊이 우선 탐색 / 너비 우선 탐색)

그래프 자료 구조에서 루트 노드에서 시작하여 완전 탐색을 하는 검색 알고리즘

<br>

### DFS (Depth First Search, 깊이 우선 탐색)

루트 노드(혹은 다른 임의의 노드)에서 다음 분기(branch)로 넘어가기 전에, 해당 분기를 모두 탐색하는 방법이다.  
탐색 후에는 다시 원점으로 돌아가 다른 분기를 탐색한다.

<br>

🔸 **특징**

- 자기 자신을 호출하는 순환 알고리즘의 형태 (재귀/스택)
- 그래프 탐색의 경우 어떤 노드를 방문했었는지 여부를 반드시 검사해야한다.  
  <span style = "color : gray">(검사하지 않을 경우 무한루프에 빠질 수 있다)</span>
  > ex) visit[index] = true;
- 모든 노드를 방문하고자 할 때 DFS를 선택한다.
- 최단 거리를 구하는 문제 중 장애물이 있는 경우에는 DFS가 유리하다.
- BFS보다 간단하다.
- 검색 속도 자체는 BFS에 비해서 느리다.

<br>

```java
// dfs, 재귀, 인접 행렬, i 정점부터 시작한다.    
public static void dfs(int i) {		
    visit[i] = true;		
	
    for(int j=1; j<n+1; j++) {			
        if(map[i][j] == 1 && visit[j] == false) {				
          dfs(j);			
        }		
    }	
}
```
▲ _재귀를 통한 DFS 구현_

<br>
<details>
<summary> &ensp; ✔︎ DFS Sample Questions</summary>
<div markdown="1">
<br>

📌 **BAEKJOON**
| 레벨 | ID | 제목 |
|:--:|:--:|:--:|
| <img src = "https://static.solved.ac/tier_small/9.svg" height = "20"> | 11724 | 연결 요소의 갯수 |
| <img src = "https://static.solved.ac/tier_small/9.svg" height = "20"> | 1012 | 유기농 배추 |
| <img src = "https://static.solved.ac/tier_small/9.svg" height = "20"> | 1743 | 음식물 피하기 |
| <img src = "https://static.solved.ac/tier_small/9.svg" height = "20"> | 10552 | DOM |
| <img src = "https://static.solved.ac/tier_small/10.svg" height = "20"> | 2667 | 단지 번호 붙이기 |
| <img src = "https://static.solved.ac/tier_small/10.svg" height = "20"> | 2583 | 영역 구하기 |
| <img src = "https://static.solved.ac/tier_small/10.svg" height = "20"> | 11403 | 경로 찾기 ⭐️ |
| <img src = "https://static.solved.ac/tier_small/10.svg" height = "20"> | 2468 | 안전 영역 ⭐️ |
| <img src = "https://static.solved.ac/tier_small/11.svg" height = "20"> | 10026 | 적록색약 |
| <img src = "https://static.solved.ac/tier_small/13.svg" height = "20"> | 9466 | 팀 프로젝트 |
| <img src = "https://static.solved.ac/tier_small/18.svg" height = "20"> | 10265 | MT ⭐️ |

### 📋 [더 많은 문제](https://solved.ac/search?query=tag%3Adfs)

</div>
</details>

<br><br>

### BFS (Breadth First Search, 너비 우선 탐색)

루트 노드(혹은 다른 임의의 노드)에서 시작한 인접 노드를 먼저 탐색하는 방법이다.

<br>

🔸 **특징**

- BFS는 재귀적으로 동작하지 않는다.
- 그래프 탐색의 경우 어떤 노드를 방문했었는지 여부를 반드시 검사해야한다.  
  <span style = "color : gray">(검사하지 않을 경우 무한루프에 빠질 수 있다)</span>
  > ex) visit[index] = true;
- BFS는 방문한 노드들을 차례로 저장한 후 꺼낼 수 있는 자료 구조인 큐를 사용한다.  
  > 즉, 선입선출 원칙으로 탐색
- 깊게(deep) 탐색하기 전에 넓게(wide) 탐색하는 방법이다.
- 두 노드 사이의 최단 경로 혹은 임의의 경로를 찾고 싶을 때 주로 사용한다.

<br>

```java
// bfs, q사용, 인접행렬, i 정점부터 시작한다.	
public static void bfs(int i) {		
    Queue<Integer> q = new LinkedList<>();		
    q.offer(i);		
    visit[i] = true;		

    while(!q.isEmpty()) {			
        int temp = q.poll();			
		
        for(int j=1; j<n+1; j++) {				
            if(map[temp][j] == 1 && visit[j] == false) {					
                q.offer(j);					
                visit[j] = true;				
            }			
        }		
    }	
}
```
▲ _Queue를 통한 BFS 구현_

<br>
<details>
<summary> &ensp; ✔︎ BFS Sample Questions</summary>
<div markdown="1">
<br>

📌 **BAEKJOON**
| 레벨 | ID | 제목 |
|:--:|:--:|:--:|
| <img src = "https://static.solved.ac/tier_small/9.svg" height = "20"> | 1260 | DFS와 BFS |
| <img src = "https://static.solved.ac/tier_small/9.svg" height = "20"> | 2644 | 촌수 계산 |
| <img src = "https://static.solved.ac/tier_small/10.svg" height = "20"> | 2178 | 미로 탐색 |
| <img src = "https://static.solved.ac/tier_small/10.svg" height = "20"> | 7562 | 나이트의 이동 |
| <img src = "https://static.solved.ac/tier_small/10.svg" height = "20"> | 1697 | 숨바꼭질 |
| <img src = "https://static.solved.ac/tier_small/11.svg" height = "20"> | 6593 | 상범 빌딩 |
| <img src = "https://static.solved.ac/tier_small/11.svg" height = "20"> | 7576 | 토마토 |
| <img src = "https://static.solved.ac/tier_small/11.svg" height = "20"> | 5014 | 스타트링크 |
| <img src = "https://static.solved.ac/tier_small/12.svg" height = "20"> | 3055 | 탈출 ⭐️ |
| <img src = "https://static.solved.ac/tier_small/12.svg" height = "20"> | 5427 | 불 ⭐️ |
| <img src = "https://static.solved.ac/tier_small/12.svg" height = "20"> | 16397 | 탈출 |
| <img src = "https://static.solved.ac/tier_small/12.svg" height = "20"> | 9019 | DSLR ⭐️ |
| <img src = "https://static.solved.ac/tier_small/13.svg" height = "20"> | 2206 | 벽 부수고 이동하기 ⭐️ |
| <img src = "https://static.solved.ac/tier_small/13.svg" height = "20"> | 1039 | 교환 ⭐️ |
| <img src = "https://static.solved.ac/tier_small/14.svg" height = "20"> | 1525 | 퍼즐 ⭐️ |

### 📋 [더 많은 문제](https://solved.ac/search?query=tag%3Abfs%20)

</div>
</details>

<br>

***

<br>

## Brute Force Algorithm (완전 탐색 알고리즘)

완전 탐색 알고리즘은 가능한 모든 경우의 수를 대입하여 해결하는 무차별 대입 방법을 나타내는 알고리즘이다.

🔸 완전 탐색 알고리즘을 사용하는 경우

1. 프로세스 속도를 높이는데 사용할 수 있는 다른 알고리즘이 없을 때 사용

2. 문제를 해결하는 여러 솔루션이 있고 각 솔루션을 확인해야 할 때 사용

<br>

> 💡 Brute Force를 활용한 알고리즘  
> 
> - 순차 검색 알고리즘(Sequential Search)
> - 문열 매칭 알고리즘(Brute-Force String Matching)
> - 선택 정렬 알고리즘(Selection Sort)
> - 버블 정렬 알고리즘(Bubble Sort)
> - 동적 프로그래밍(Dynamic Programming, DP)
> - 트리 구조의 완전 탐색 알고리즘 (BFS, DFS)

<br>
<details>
<summary> &ensp; ✔︎ Brute Force Algorithm Sample Questions</summary>
<div markdown="1">
<br>

📌 **BAEKJOON**
| 레벨 | ID | 제목 |
|:--:|:--:|:--:|
| <img src = "https://static.solved.ac/tier_small/4.svg" height = "20"> | 2231 | 분해합 |
| <img src = "https://static.solved.ac/tier_small/5.svg" height = "20"> | 2309 | 일곱 난쟁이 |
| <img src = "https://static.solved.ac/tier_small/5.svg" height = "20"> | 10448 | 유레카 이론 |
| <img src = "https://static.solved.ac/tier_small/7.svg" height = "20"> | 1018 | 체스판 다시 칠하기 |
| <img src = "https://static.solved.ac/tier_small/8.svg" height = "20"> | 2503 | 숫자 야구 |
| <img src = "https://static.solved.ac/tier_small/9.svg" height = "20"> | 3085 | 사탕게임 |
| <img src = "https://static.solved.ac/tier_small/9.svg" height = "20"> | 1182 | 부분 수열의 합 ⭐️ |

### 📋 [더 많은 문제](https://solved.ac/search?query=tag%3Abruteforcing%20)

</div>
</details>

<br>

***

<br>

## Greedy Algorithm (탐욕 알고리즘)

선택의 순간마다 당장 눈앞에 보이는 최적의 상황만을 쫓아 최종적인 해답에 도달하는 방법  
항상 최적의 결과를 도출하지는 않지만, 최적에 가까운 값을 빠르게 도출해낼 수 있다는 장점이 있다.

🔸 탐욕 알고리즘의 문제 해결 단계

1. 선택 절차(Selection Procedure)  
   현재 상태에서의 최적의 해답을 선택

2. 적절성 검사(Feasibility Check)  
   선택된 해가 문제의 조건을 만족하는지 검사

3. 해답 검사(Solution Check)  
   원래의 문제가 해결되었는지 검사하고, 해결되지 않았다면 선택 절차로 돌아가 위의 과정을 반복

<br>

🔸 탐욕 알고리즘을 적용할 수 있는 조건

1. 탐욕적 선택 속성(Greedy Choice Property)  
   앞의 선택이 이후의 선택에 영향을 주지 않아야 한다.
2. 최적 부분 구조(Optimal Substructure)  
   문제에 대한 최종 해결 방법은 부분 문제에 대한 최적 문제 해결 방법으로 구성된다.

<br>

<img src = "https://images.slideplayer.com/13/3937168/slides/slide_3.jpg" width = "80%"/>

> 최적의 해답 ```20센트```를 선택하고, 다음 ```20센트```는 들어갈 수 없으니 ```10센트```를 선택하는 식으로 문제를 해결하는 방식이다.

<br>
<details>
<summary> &ensp; ✔︎ Greedy Algorithm Sample Questions</summary>
<div markdown="1">
<br>

📌 **BAEKJOON**
| 레벨 | ID | 제목 |
|:--:|:--:|:--:|
| <img src = "https://static.solved.ac/tier_small/5.svg" height = "20"> | 4796 | 캠핑 |
| <img src = "https://static.solved.ac/tier_small/6.svg" height = "20"> | 17509 | And the Winner Is... Ourselves! |
| <img src = "https://static.solved.ac/tier_small/7.svg" height = "20"> | 11047 | 동전 0 |
| <img src = "https://static.solved.ac/tier_small/8.svg" height = "20"> | 1449 | 수리공 항승 |
| <img src = "https://static.solved.ac/tier_small/10.svg" height = "20"> | 1931 | 회의실 배정 |
| <img src = "https://static.solved.ac/tier_small/11.svg" height = "20"> | 11000 | 강의실 배정 |
| <img src = "https://static.solved.ac/tier_small/11.svg" height = "20"> | 2212 | 센서 |
| <img src = "https://static.solved.ac/tier_small/11.svg" height = "20"> | 15748 | Rest Stops ⭐️ |
| <img src = "https://static.solved.ac/tier_small/13.svg" height = "20"> | 13904 | 과제 ⭐️ |
| <img src = "https://static.solved.ac/tier_small/13.svg" height = "20"> | 1493 | 박스 채우기 ⭐️ |
| <img src = "https://static.solved.ac/tier_small/15.svg" height = "20"> | 1700 | 멀티탭 스케줄링 |

### 📋 [더 많은 문제](https://solved.ac/search?query=tag%3Agreedy%20)

</div>
</details>


<br>

***

<br>

## Binary Search Algorithm (이진 탐색 알고리즘)

정렬된 상태의 데이터를 절반씩 범위를 나눠 분할 정복기법으로 특정 값을 찾아내는 알고리즘

<br>

### 📋 [이진 탐색 알고리즘의 구현](#🔸-olog-n)

<br>
<details>
<summary> &ensp; ✔︎ Binary Search Algorithm Sample Questions</summary>
<div markdown="1">
<br>

📌 **BAEKJOON**
| 레벨 | ID | 제목 |
|:--:|:--:|:--:|
| <img src = "https://static.solved.ac/tier_small/8.svg" height = "20"> | 2512 | 예산 |
| <img src = "https://static.solved.ac/tier_small/9.svg" height = "20"> | 2805 | 나무 자르기 |
| <img src = "https://static.solved.ac/tier_small/9.svg" height = "20"> | 6236 | 용돈 관리 |
| <img src = "https://static.solved.ac/tier_small/9.svg" height = "20"> | 1654 | 랜선 자르기 |
| <img src = "https://static.solved.ac/tier_small/10.svg" height = "20"> | 2343 | 기타 레슨 |
| <img src = "https://static.solved.ac/tier_small/12.svg" height = "20"> | 2110 | 공유기 설치 |
| <img src = "https://static.solved.ac/tier_small/12.svg" height = "20"> | 16434 | 드래곤 앤 던전 ⭐️ |
| <img src = "https://static.solved.ac/tier_small/14.svg" height = "20"> | 15732 | 도토리 숨기기 ⭐️ |
| <img src = "https://static.solved.ac/tier_small/14.svg" height = "20"> | 1300 | K번째 수 ⭐️ |

### 📋 [더 많은 문제](https://solved.ac/search?query=tag%3Abinary_search%20)

</div>
</details>

<br>

***

<br>

## Dynamic Programming (동적 프로그래밍)

주어진 문제를 여러 개의 부분으로 나누어 푼 다음, 그 결과들로 주어진 문제를 해결하는 알고리즘

단, 분할 정복과 다르게 **하나의 문제를 한 번만 푼다는 점**에서 다르다.

중복에 대한 문제를 메모이제이션(Memoization)을 통해 해결하며,  
Greedy 알고리즘보다 폭 넓은 범위에서 근사치가 아닌 **정확한 값**을 얻어낸다.

> ex) 피보나치 수열 재귀 문제 해결 (이미 계산된 결과는 저장)

<br>

```java
public int fib(int n) {
  if(n < 2) return n;

  return fib(n - 2) + fib(n - 1);
}
```
▲ _일반 피보나치 수열_

<br>

피보나치 수열의 단점으로 꼽히는 문제는 재귀 호출에 의한 O(2<sup>n</sup>)의 시간 복잡도이다.

<br>

<img src = "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FufkZW%2FbtraTbd0GQF%2FkUnH9bbEB4UK5rK59OGc4K%2Fimg.png" width = "90%"/>

▲ _fib(5)를 실행했을 때, 발생하는 재귀_

<br>

위의 사진에서만 보더라도 fib(2)에 대한 값이 저장되어 있지 않아서  
fib(2)가 불필요한 재귀를 반복하는 것을 볼 수 있다.

<br>

```java
int fib(int n){
  if(n < 2) return n;

  // 값이 존재하면 재귀호출 하지 않고 리턴
  if(dp[n] != 0) return dp[n];

  // 존재하지 않는다면 재귀호출 하여 값을 보관
  dp[n] = fib(n - 2) + fib(n - 1);

  return dp[n];
 }
```
▲ _Memoization을 통한 피보나치 수열_

<br>

만약 ```dp[n]```에 해당 값이 저장되어 있다면, 재귀호출을 하지 않고 해당 값을 리턴하기 때문에  
위의 코드는 O(n)의 시간 복잡도를 가지게 된다.

<br>
<details>
<summary> &ensp; ✔︎ Dynamic Programming Sample Questions</summary>
<div markdown="1">
<br>

📌 **BAEKJOON**
| 레벨 | ID | 제목 |
|:--:|:--:|:--:|
| <img src = "https://static.solved.ac/tier_small/8.svg" height = "20"> | 1463 | 1로 만들기 |
| <img src = "https://static.solved.ac/tier_small/8.svg" height = "20"> | 2193 | 이진수 |
| <img src = "https://static.solved.ac/tier_small/8.svg" height = "20"> | 1904 | 01타일 |
| <img src = "https://static.solved.ac/tier_small/8.svg" height = "20"> | 11726 | 2xn 타일링 |
| <img src = "https://static.solved.ac/tier_small/8.svg" height = "20"> | 11727 | 2xn 타일링 2 |
| <img src = "https://static.solved.ac/tier_small/8.svg" height = "20"> | 11051 | 이항 계수 2  |
| <img src = "https://static.solved.ac/tier_small/9.svg" height = "20"> | 1699 | 제곱수의 합  |
| <img src = "https://static.solved.ac/tier_small/9.svg" height = "20"> | 11055 | 가장 큰 증가 부분 수열 ⭐️ |
| <img src = "https://static.solved.ac/tier_small/10.svg" height = "20"> | 9465 | 스티커 |
| <img src = "https://static.solved.ac/tier_small/10.svg" height = "20"> | 11052 | 카드 구매하기  |
| <img src = "https://static.solved.ac/tier_small/10.svg" height = "20"> | 10844 | 쉬운 계단 수  |
| <img src = "https://static.solved.ac/tier_small/10.svg" height = "20"> | 11057 | 오르막 수  |
| <img src = "https://static.solved.ac/tier_small/11.svg" height = "20"> | 2294 | 동전 2  |
| <img src = "https://static.solved.ac/tier_small/11.svg" height = "20"> | 12865 | 평범한 배낭 ⭐️ |
| <img src = "https://static.solved.ac/tier_small/11.svg" height = "20"> | 16500 | 문자열 판별 ⭐️ |

### 📋 [더 많은 문제](https://solved.ac/search?query=tag%3Adp)

</div>
</details>

<br>

***

<br>

## Algorithm with Math

<br>

### 최대공약수 (Greatest Common Divisor, GCD)

> 0이 아닌 2개 이상의 정수의 공통 약수 중 가장 큰 수

<br>

### 최소 공배수 (Least Common Multiple, LCM) 

> 0이 아닌 2개 이상의 정수의 양의 공배수 중 가장 작은 수

<br>

```java
int num1 = 8;
int num2 = 12;

int gcd = 0;  // 최대 공약수

for(int i = 1; i <= num1 && i <= num2; i++) {
  if(num1 % i == 0 && num2 % i == 0) {
    max = i;
  }
}

int lcm = (num1 * num2) / gcd;  // 최소 공배수

// 최대 공약수
System.out.println(gcd);

// 최소 공배수
System.out.println(lcm);
```

▲ _쉽게 풀 수 있는 방법_

<br>

```java
/*
반복문을 통한 유클리드 호제법의 구현
*/

int gcd(int a, int b) {
  if (a < b) {  // 유클리드 호제법의 조건
    // a를 b로 나누어야 하기 때문에 b가 더 크다면 a와 b를 바꿔준다.
    int temp = a;
    a = b;
    b = temp;
  } 
  while(b != 0) { // 유클리드 호제법
    int r = a % b;
    a = b;
    b = r;
  }
  return a; // 최대 공약수
}

int lcm(int a, int b) {
  return (a * b) / gcd(a, b); // 최소 공배수
}


/*
재귀를 통한 유클리드 호제법의 구현
*/

int gcd(int a, int b) {
  // 큰 숫자를 작은 숫자로 나눈 나머지 r
  int r = a % b;

  // r이 0이 되면 나누게 된 작은 숫자 b가 최대 공약수이다.
  if (r == 0) return b;

  // r이 0이 되지 않으면 작은 수 -> 큰 수, 나머지 -> 작은 수로 옮겨 계산한다.
  return gcd(b, r);
}
```

▲ _유클리드 호제법_

<br>

### 조합 (Combination)

<br>

> 서로 다른 n개에서 **순서 없이** r개를 뽑는 경우의 수

```java
public class AlgorithmwithMath {
   public static void combination(int[] arr, boolean[] visited, int start, int depth, int r){
      if(depth == r){
         for(int i=0; i<arr.length; i++){
            if(visited[i]) System.out.print(arr[i]);
         }
         System.out.println();
         return;
      }
      for(int i=start; i<arr.length; i++){
         if(!visited[i]){
            visited[i] = true;
            combination(arr, visited, i+1, depth+1, r);
            visited[i] = false;
         }
      }
   }

   public static void main(String[] args){
      int[] arr = {1, 2, 3};
      int r = 2;
      combination(arr, new boolean[arr.length], 0, 0, r);
   }
}
```

<br>

### 순열 (Permutation)

<br>

> 서로 다른 n개에서 r개를 뽑아서 **정렬**하는 경우의 수 (nPr)  
> 
> 순서가 존재한다.

```java
public class AlgorithmwithMath {
   public static void permutation(int[] arr, int[] out, boolean[] visited, int depth, int r){
      if(depth == r){
         for(int num: out) System.out.print(num);
            System.out.println();
            return;
      }
      for(int i=0; i<arr.length; i++){
         if(!visited[i]){
            visited[i] = true;
            out[depth] = arr[i];
            permutation(arr, out, visited, depth+1, r);
            visited[i] = false;
         }
      }
   }

   public static void main(String[] args){
      int[] arr = {1, 2, 3};
      int r = 2;
      permutation(arr, new int[r], new boolean[arr.length], 0, r);
   }
}
```

<br>

### 멱집합 (Power Set)

> 해당 집합의 모든 부분 집합을 모아둔 집합 (주로 집합을 사용하여 해결)
>
> ex) 집합 {1, 2, 3}의  
> 부분 집합 : {}, {1}, {2}, {3}, {1, 2}, {1, 3}, {2, 3}, {1, 2, 3}  
> 멱집합 : { {}, {1}, {2}, {3}, {1, 2}, {1, 3}, {2, 3}, {1, 2, 3} }

<br>

```java
int[] nums = { 1, 2, 3 };

// 최대 원소의 개수
int max_cnt;

// 각 부분 집합을 저장할 배열
int[] subset;

public void main(String[] args) {
  // 원소를 선택하는 개수 0 ~ 3개.
   for (int i = 0; i <= 3; i++) {
      max_cnt = i;
      subset = new int[i];
      // 대상 집합에서 원소를 0 ~ 3개를 선택하는 조합을 모두 구한다.
      Combination(0, 0);
   }
}

private void Combination(int cnt, int k) {
   if (cnt == max_cnt) {
      System.out.println(Arrays.toString(subset));
      return;
   }
   for (int i = k; i < nums.length; i++) {
      subset[cnt] = nums[i];
      Combination(cnt + 1, i + 1);
   }
}
```


<br><br>

***

_2022.09.29. Update_

_2022.09.28. Update_

_2022.09.27. Update_

_2022.09.26. Update_