# Spring Framework 특징

<img src = "https://velog.velcdn.com/images/gwichanlee/post/58534983-12f6-456d-a6a9-9e196be4736e/image.png">

▲ _Spring Triangle_

<br>

## POJO (Plain Old Java Object)

POJO는 직역하면 Java로 생성하는 순수한 객체를 말한다.

Spring은 POJO 프로그래밍을 지향하는 Framework이며,  
POJO 프로그래밍으로 작성한 코드라고 하기 위해선 크게 2가지의 규칙이 존재한다.

1. Java나 Java의 스펙에 정의된 것 이외의 기술이나 규약에 얽매이지 않아야 한다.

2. 특정 환경에 종속적이지 않아야 한다.

```java
public class MessageForm extends ActionForm {

	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
}
```

위 코드에서 ```Struts```라는 웹 프레임워크에서 지원하는 ```ActionForm``` 클래스를 상속받는 것을 볼 수 있다..

이러한 경우 나중에 어플리케이션의 요구사항이 변경되어 기술을 변경하기 위해서는   
```Struts```의 클래스를 명시적으로 사용했던 부분을 하나하나 제거하거나 수정해야한다.

<br>

**🔸 POJO 프로그래밍이 필요한 이유**

- 특정 환경이나 기술에 종속적이지 않으면 재사용 가능하고, 확장 가능한 유연한 코드를 작성할 수 있다.
  
- 저수준 레벨의 기술과 환경에 종속적인 코드를 어플리케이션 코드에서 제거함으로써 코드가 깔끔해진다.
  
- 코드가 깔끔해지기 때문에 디버깅이 상대적으로 쉬워진다.
  
- 특정 기술이나 환경에 종속적이지 않기 때문에 테스트가 단순해진다.
  
- **객체지향적인 설계를 제한없이 적용할 수 있다.**

<br>

## IoC (Inversion of Control)

IoC는 직역하면 제어의 역전이라는 뜻으로,  
메서드나 객체의 호출을 개발자가 결정하는 것이 아닌 외부에서 결정되는 것을 의미한다. 

Spring에서는 어플리케이션 흐름의 주도권을 Spring이 갖는다라고 보면 된다.

<br>

## DI (Dependency Injection)

DI는 의존성 주입이라는 뜻으로, IoC 개념을 구체화시킨 것이라고 볼 수 있다.

<br>

```java
public class MenuController {
    public static void main(String[] args) {
        MenuService menuService = new MenuService();
        List<Menu> menuList = menuService.getMenuList();
    }
}

public class MenuService {
    public List<Menu> getMenuList() {
        return null;
    }
}
```

위의 코드에서 ```MenuController``` 클래스는 ```new``` 키워드를 사용해 ```MenuService``` 클래스의 객체를 생성한 후 ```MenuService```의 기능을 사용하고 있다.

이처럼 클래스끼리 객체를 생성해서 참조하는 것을 **의존 관계**라고 한다.

<br>

```java
public class CafeClient {
    public static void main(String[] args) {
        MenuService menuService = new MenuService();
        MenuController controller = new MenuController(menuService);
        List<Menu> menuList = contriller.getMenus();
    }
}
public class MenuController {
    private MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }
    public List<Menu> getMenus() {
        return menuService.getMenuList();
    }
}

public class MenuService {
    public List<Menu> getMenuList() {
        return null;
    }
}
```

방금 전에는 ```MenuController``` 클래스가 ```MenuService```의 객체를 직접 생성한 반면에  
위 코드에서는 ```MenuController``` **생성자**로 ```MenuService```의 객체를 전달 받고있다.

이처럼 생성자를 통해 어떠한 클래스의 객체를 전달받는 것을 **의존성 주입**이라고 하고,  
생성자의 파라미터로 객체를 전달하는 것을 **외부에서 객체를 주입한다**고 한다.

```CafeClient``` 클래스에서 ```MenuController```의 생성자 파라미터로 ```menuService```를 전달하고 있기 때문에 ```CafeClient``` 클래스가 위에서 말한 **외부**가 된다.

<br>

### 🔸 강한 결합 (Tight Coupling)

의존성 주입을 사용할 때, 염두에 두어야 하는 부분은  
클래스 내부에서 외부 클래스의 객체를 생성하기 위한 **```new``` 키워드를 쓸지 말지 여부**를 결정하는 부분이다.

어플리케이션 코드 내부에서 직접적으로 ```new``` 키워드를 사용할 경우 객체지향 설계의 관점에서 문제가 발생할 수 있기 때문이다.

<br>

방금 전의 코드에서 데이터 조회 API를 위한 Stub을 준비하게 된다면 아래와 같이 수정될 수 있을 것이다.

> ❓ **Stub**   
> 
> 메서드가 호출될 때 미리 준비된 멱등성을 가진 데이터를 응답하는 것을 말한다.  
> 임의의 고정된 데이터를 호출하므로 항상 동일한 데이터를 리턴하게 된다.  

```java
public class CafeClient {
    public static void main(String[] args) {
        MenuServiceStub menuService = new MenuServiceStub();
        MenuController controller = new MenuController(menuService);
        List<Menu> menuList = contriller.getMenus();
    }
}
public class MenuController {
    private MenuServiceStub menuService;

    public MenuController(MenuServiceStub menuService) {
        this.menuService = menuService;
    }
    public List<Menu> getMenus() {
        return menuService.getMenuList();
    }
}

public class MenuServiceStub {
    public List<Menu> getMenuList() {
        return List.of(
            new Menu(1, "아메리카노", 2500),
            new Menu(2, "에스프레소", 2500),
            new Menu(3, "카페 라떼", 4000)
        );
    }
}
```

```getMenuList```를 Stub 데이터로 채우고, ```MenuService``` 클래스를 ```MenuServiceStub``` 클래스로 전부 변경해주었다.

```new```를 사용해 의존 객체를 생성하면 클래스간에 **강하게 결합(Tight Coupling)** 되어 있어서  
해당 클래스를 사용하는 모든 클래스들을 수정할 수 밖에 없다.

<br>

### 🔸 느슨한 결합 (Loose Coupling)

어떤 클래스가 일반화된 구성 요소에 의존하고 있을 때, 클래스간에 **느슨하게 결합(Loose Coupling)** 되어 있다고 한다.

느슨한 결합을 사용하는 대표적인 방법으로는 **인터페이스**를 사용하는 방법이 있다.

```java
public class CafeClient {
    public static void main(String[] args) {
        MenuService menuService = new MenuServiceStub();
        MenuController controller = new MenuController(menuService);
        List<Menu> menuList = contriller.getMenus();
    }
}
public class MenuController {
    private MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }
    public List<Menu> getMenus() {
        return menuService.getMenuList();
    }
}

// 인터페이스 생성
public interface MenuService {
    List<Menu> getMenuList();
}

public class MenuServiceStub implements MenuService {
    public List<Menu> getMenuList() {
        return List.of(
            new Menu(1, "아메리카노", 2500),
            new Menu(2, "에스프레소", 2500),
            new Menu(3, "카페 라떼", 4000)
        );
    }
}
```

위의 코드를 보면, ```CafeClient``` 클래스에서 ```MenuServiceStub``` 클래스의 객체를 생성해  
```MenuService``` 인터페이스에 업캐스팅(Upcasting)한 것을 볼 수 있다.

> ❓ **업캐스팅(Upcasting)**  
> 
> 하위 클래스의 정보를 담을 수 있는 객체에 상위 클래스의 자료형을 부여해서 상위 클래스처럼 사용하게 하는 것

<br>

```MenuController``` 클래스가 생성자로 ```MenuService``` 인터페이스를 주입받았기 때문에  
```MenuService``` 인터페이스의 구현 클래스라면 모든 클래스가 주입받을 수 있다.

<br>

## AOP (Aspect Oriented Programming)

<br>

## PSA (Portable Service Abstraction)

<br><br>

***

_2022.10.11. Update_