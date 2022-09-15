# 연산자와 I/O

## 연산자

<br>

연산자의 종류 및 우선 순위

| 우선순위 | 연산자 | 내용 |
|:------:|:---:|:---:|
| 1 | (), [] | 괄호 / 대괄호 |
| 2 | !, ~, ++, -- | 부정 / 증감 연산자 |
| 3 | *, /, % | 곰셈 / 나눗셈 연산자 |
| 4 | <, <=, >, >= | 대소 비교 연산자 |
| 5 | && | AND 연산자 |
| 6 | \|\| | OR 연산자 |
| 7 | ? : | 조건 연산자 |
| 8 | =, +=, -=, /=, %= | 대입 / 할당 연산자 |


- 삼항 연산자 : ```int a = (조건문) ? 참일때 : 거짓일때;```

<br>

***

<br>

## 콘솔 입출력(I/O)

<br>

### 🔸 출력

<br>

```System.out.print();``` : 일반 출력

```System.out.println();``` : 출력 후 줄 바꿈

```System.out.printf();``` : 형식대로 출력

| 지시자 | 출력 포맷 |
|:------:|:---:|
| %b | boolean |
| %d | 10진수 |
| %o | 8진수 |
| %x, %X | 16진수 |
| %c | 문자 |
| %s | 문자열 |
| %n | 줄바꿈 |


<br>

### 🔸 입력

<br>

**Scanner**

변수의 타입을 설정해 줄 수 있어서 추가 가공 작업이 불필요해 편리하다.

개행문자(```\n```)나 띄어쓰기(공백)를 경계로 하여 입력 값을 인식한다.

버퍼 사이즈가 1024char이기 때문에 많은 입력을 필요로 할 경우에는 성능상 단점이 있다.

```java
// Scanner 클래스를 import
import java.util.Scanner;            

// Scanner 클래스의 sc 인스턴스를 생성
Scanner sc = new Scanner(System.in); 

// 입력한 내용을 변수에 저장
String str = sc.nextLine();          

// 입력한 내용 출력
System.out.println(str); 
```

<br>

**BufferedReader**

키보드 입력을 Buffer에 전송한 뒤, 버퍼의 내용을 한 번에 묶어서 보낸다.  

개행문자(```\n```)만을 경계로 하여 입력 값을 인식한다.

```Scanner```보다 속도가 빠르다는 장점이 있다.

하지만 입력받은 데이터가 ```String```으로 고정되므로 가공하는 작업이 필요하고,  
예외처리를 반드시 필요로 하기 때문에 ```readLine()```시 마다 ```try/catch```문으로 감싸거나  
```throws IOException```을 통한 예외처리를 해줘야 한다.


```java
// 클래스들을 import
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public static void main(String[] args) throws IOException {

    // 사용자 입력을 받을 수 있는 reader 인스턴스 생성
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    // 입력한 내용을 변수에 저장
    String str = reader.readLine();

    // 입력한 내용 출력
    System.out.println(str);
}
```

<br>

공백을 기준으로 데이터를 가공하고자 할 때는  
```StringTokenizer의 nextToken()``` 메서드나 ```String.split()``` 메서드를 사용한다.

```java
// StringTokenizer .nextToken()
BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
StringTokenizer str = new StringTokenizer(br.readLine());
int num1 = Integer.parseInt(str.nextToken());
int num2 = Integer.parseInt(str.nextToken());

// String.split()
String arr[] = str.split(" ");
```

<br>

***

<br>

## 파일 입출력(I/O)

<br>

### 🔸 입력

<br>

**FileInputStream**

```
// 터미널에서 실행 > test.txt 파일 생성
echo code >> test.txt
```

```java
import java.io.FileInputStream;

try {
    FileInputStream fileInput = new FileInputStream("test.txt");    // 같은 디렉토리
    int i = 0;

    //fileInput.read()의 리턴값을 i에 저장한 후, 값이 -1인지 확인
    while ((i = fileInput.read()) != -1) { 
        System.out.println((char)i);
    }
    fileInput.close();
}
catch (Exception e) {
    System.out.println(e);
}

// 출력 : code
```

<br>

**BufferedInputStream**

```BufferedInputStream```이라는 보조 스트림을 사용하면 성능이 향상되므로 대부분 이것을 사용한다.

```java
import java.io.FileInputStream;
import java.io.BufferedInputStream;

try {
    FileInputStream fileInput = new FileInputStream("test.txt");
    BufferedInputStream bufferedInput = new BufferedInputStream(fileInput);
    int i = 0;
    while ((i = bufferedInput.read()) != -1) {
        System.out.print((char)i);
    }
    fileInput.close();
}

catch (Exception e) {
    System.out.println(e);
}

출력 : code
```

### 📋 [_**InputStream 공식 문서**_](https://docs.oracle.com/javase/7/docs/api/java/io/InputStream.html)

<br><br>

**FileReader**

```FileInputStream```은 ```byte``` 기반 스트림이라서 입출력 단위가 1byte다.

Java의 ```char```타입은 2byte이기 때문에 이를 위해 문자 기반 스트림 ```FileReader```가 있다.

```java
import java.io.*;

public static void main(String[] args) throws IOException {
    try {
        // testfile.txt 내용 > 안녕하세요
        String fileName = "testfile.txt";
        FileReader file = new FileReader(fileName);

        int data = 0;

        while((data=file.read()) != -1) {
            System.out.print((char)data);
        }
        file.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
}

// 출력 : 안녕하세요

// FileInputStream으로 출력하면 "ìëíì¸ì"와 같이 출력됨
```

<br>

**BufferedReader**

위의 콘솔 입력에서 작성한 ```BuffredReader```와 같다.

원래는 파일 입력시 Reader의 성능을 개선하는 용도이지만  
매개변수를 ```InputStreamReader```로 받아서 콘솔 입력 시에 성능 개선 용도로 사용했다.

```java
try {
    String fileName = "testfile.txt";
    FileReader file = new FileReader(fileName);
    BufferedReader buffered = new BufferedReader(file);

    int data = 0;

    while((data=buffered.read()) != -1) {
        System.out.print((char)data);
    }
    file.close();
}
catch (IOException e) {
    e.printStackTrace();
}
```

### 📋 [_**FileReader 공식 문서**_](https://docs.oracle.com/javase/7/docs/api/java/io/Reader.html)

<br><br>

### 🔸 출력

<br>

**FileOutputStream**

```java
import java.io.FileOutputStream;

try {
    FileOutputStream fileOutput = new FileOutputStream("testfile.txt");
    String word = "code";

    byte b[] = word.getBytes();
    fileOutput.write(b);
    fileOutput.close();
}
catch (Exception e) {
    System.out.println(e);
}

// 프로젝트 하위에 code라는 문자열이 입력된 testfile.txt 파일이 생성
```

### 📋 [_**OutputStream 공식 문서**_](https://docs.oracle.com/javase/7/docs/api/java/io/OutputStream.html)

<br><br>

**FileWriter**

```java
try {
    String fileName = "testfile2.txt";
    FileWriter writer = new FileWriter(fileName);

    String str = "파일 쓰기";
    writer.write(str);
    writer.close();
}
catch (IOException e) {
    e.printStackTrace();
}
```

### 📋 [_**FileWriter 공식 문서**_](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/Writer.html)

<br>

### File

```File``` 클래스를 통해 파일과 디렉토리에 접근할 수 있다.

```java
import java.io.*;

File file = new File("../testfile.txt");

System.out.println(file.getPath());
System.out.println(file.getParent());
System.out.println(file.getCanonicalPath());
System.out.println(file.canWrite());

// 출력
..\testfile.txt
..
C:\Users\JWANNA\~\testfile.txt
false
```

<br>

파일 생성을 위해서는 파일 인스턴스 생성시에 아래와 같이 하면 된다.
1. 첫번째 인자에 경로
2. 두번째 인자에 파일명
3. ```createNewFile()``` 메서드 작성

```java
File file = new File("./", "newTestFile.txt");
file.createNewFile();
```

<br>

```java
File parentDir = new File("./");    // 현재 디렉토리
File[] list = parentDir.listFiles();

String prefix = "code"; // 해당 문자열 붙임

for(int i =0; i <list.length; i++) {
   String fileName = list[i].getName();

if(fileName.endsWith("txt") && !fileName.startsWith("code")) {
       list[i].renameTo(new File(parentDir, prefix + fileName));
   }
}
```
▲ _현재 디렉토리(.)에서 확장자가 .txt인 파일명 앞에 "code"라는 문자열을 붙이는 예제_

<br>

### 📋 [_**File 공식 문서**_](https://docs.oracle.com/javase/7/docs/api/java/io/File.html)

<br>

***

_Modified 2022.09.16._

_Modified 2022.09.03._

_Update 2022.08.30._