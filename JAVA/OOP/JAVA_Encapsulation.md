# 캡슐화

캡슐화란 특정 객체 안에 관련된 속성과 기능을 하나의 캡슐로 만들어 데이터를 외부로부터 보호하는 것

캡슐화의 목적
1. 데이터 보호
2. 내부적으로 사용되는 데이터에 대한 불필요한 외부 노출 방지

<br>

## 패키지 (Package)

패키지는 특정한 목적을 공유하는 클래스와 인터페이스의 묶음이다.

```import 패키지명.클래스명``` 또는 ```import 패키지명.*```을 통해 패키지를 불러올 수 있다.

패키지 사용 목적
1. 클래스들을 그룹 단위로 묶어 효과적으로 관리
2. 패키지 설정을 통해 협업 시 클래스명 중복으로 인한 충돌 방지

> 💡 참고 : ```import```문은 컴파일 시에 처리가 되므로 프로그램의 성능에는 영향을 주지 않는다.

<br>

***

<br>

## 접근 제어자

<br>

### 제어자 (Modifier)

제어자는 클래스, 필드, 메서드, 생성자 등에 부가적인 의미를 부여하는 키워드이고,  
크게 **접근 제어자**와 **기타 제어자**로 구분 할 수 있다.

- 접근 제어자 : ```public```, ```protected```, ```default```, ```private```
- 기타 제어자 : ```static```, ```final```, ```abstract```, ```native```, ```transient```, ```synchronized``` 등

<br>

### 접근 제어자 (Access Modifier)

접근 제어자를 사용하면 클래스 외부로의 불필요한 데이터 노출을 방지(data hiding)할 수 있고,  
외부로부터 데이터가 임의로 변경되지 않도록 막을 수 있다.

| 접근제어자 | 클래스 내 | 패키지 내 | 다른 패키지<br>하위 클래스 | 패키지 외 |
|:----------:|:---------:|:---------:|:-----------------------:|:---------:|
|private|O|X|X|X|
|default|O|O|X|X|
|protected|O|O|O|X|
|public|O|O|O|O|

▲ _접근 제어자의 종류와 접근 제한 범위_

<br>

```java
package accessmodifier.inner;

public class PublicClass {
    public int public_num = 1;
    protected int protected_num = 2;
    int default_num = 3;
    private int private_num = 4;
    
    public void a() {
        System.out.println("PublicClass public void");
        System.out.println(public_num);
        System.out.println(protected_num);
        System.out.println(default_num);
        System.out.println(private_num);
    }

    public int[] num_lst = {public_num, protected_int, default_num, private_num};

    public int[] test_arr(int[] arr) {
        return arr;
    }
}
```
▲ _accessmodifier.inner 패키지_

<br>

```java
package accessmodifier.outter;

import accessmodifier.inner.*;

public class ClassAccessModifierOuterPackage{
    public static void main(String[] args) {

        PublicClass public_c = new PublicClass();
        ProtectedClass protected_c = new ProtectedClass();

        public_c.a();
        System.out.println(public_c.test_arr(public_c.num_lst));
    }
}

// 출력 결과
PublicClass public void
1
2
3
4
[I@3b07d329
```
▲ _accessmodifier.inner 패키지 외부의 outter 패키지 클래스_

```private``` 멤버가 같은 클래스의 ```public``` 멤버 안에 정의되면 호출되는 것을 볼 수 있었다.

<br>

***

<br>

## getter와 setter

getter와 setter는 데이터를 보호하기 위해 사용하며,  
주로 ```private``` 접근 제어자가 포함되어 있는 객체의 변수에 데이터를 추가, 수정할 경우 사용한다.

- ```setter``` 메서드는 외부에서 메서드에 접근하여 조건이 맞을 경우 데이터 값을 변경 가능하게 해준다.  
일반적으로 변수명에 ```set```을 붙여서 정의한다.

- ```getter``` 메서드는 ```setter```로 설정한 변수 값을 읽어오는데 사용한다.  
일반적으로 변수명에 ```get```을 붙여서 정의한다.

<br>

```java
public class GetterSetter {
    public static void main(String[] args) {

        AccessModifier a = new AccessModifier();
        a.setNum(5);

        int num = a.getNum();
        System.out.println("num : " + num); // num : 5

        a.setNum(13); // IllegalArgumentException 발생
    }
}

class AccessModifier {
    // private를 사용해 외부로부터 접근 불가능
    private int num;

    public void setNum(int num) {   // 멤버 변수의 값 변경에 제약
        if (num < 1 || num > 10) {
            throw new IllegalArgumentException();
        }
        this.num = num;
    }
    public int getNum() {           // 멤버 변수의 값
        return num;
    }
}
```

▲ Setter를 사용해서, 내부 값의 변화를 제한할 수 있다.

<br>

> 💡 **참고**  
> 
> IntelliJ IDEA에서 MacOS는 ```Option + n```, Windows는 ```Alt + Insert```를 통해  
> ```getter and setter```와 같은 여러 문법을 쉽게 호출 가능하다


<br>

***

_2023.03.22. Modified_

_2022.09.06. Update_