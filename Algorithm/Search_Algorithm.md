# 검색 알고리즘

어떠한 조건을 만족하는 데이터를 찾기 위한 알고리즘

데이터 구조에 따라 적절한 알고리즘을 선택하는 것이 중요하다.

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

<br><br>

***

_2022.09.26. Update_