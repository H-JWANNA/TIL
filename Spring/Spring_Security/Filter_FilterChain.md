# Filter์ FilterChain

### ๐ธ Filter

์๋ธ๋ฆฟ ํํฐ(Servlet Filter)๋ ์๋ธ๋ฆฟ ๊ธฐ๋ฐ ์ดํ๋ฆฌ์ผ์ด์์ ์๋ํฌ์ธํธ ์์ฒญ์ด ๋๋ฌํ๊ธฐ ์ ์  

์ค๊ฐ์์ ์์ฒญ์ ๊ฐ๋ก์ฑ ํ ์ด๋ค ์ฒ๋ฆฌ๋ฅผ ํ  ์ ์๋๋ก ํด์ฃผ๋ Java์ ์ปดํฌ๋ํธ์ด๋ค.

<br>

<img src = "../src/Filter.png" width = 500>

โฒ _์๋ธ๋ฆฟ ๊ธฐ๋ฐ ์ดํ๋ฆฌ์ผ์ด์์์ Filter์ ์์น_

<br>

ํด๋ผ์ด์ธํธ๊ฐ ์๋ฒ ์ธก ์ดํ๋ฆฌ์ผ์ด์์ผ๋ก ์์ฒญ์ ์ ์กํ๋ฉด ๊ฐ์ฅ ๋จผ์  Servlet Filter๋ฅผ ๊ฑฐ์น๊ฒ ๋๋ค.

๊ทธ ํ Filter ๋ด์ ์ฒ๋ฆฌ๊ฐ ์๋ฃ๋๋ฉด DispatcherServlet์์ ํด๋ผ์ด์ธํธ ์์ฒญ์ ํธ๋ค๋ฌ์ ๋งคํํ๊ธฐ ์ํ ๋ค์ ์์์ ์งํํ๋ค.

<br>

### ๐ธ Filter Chain

Filter Chain์ ์ฌ๋ฌ๊ฐ์ Filter๊ฐ ์ฒด์ธ์ ํ์ฑํ๊ณ  ์๋ Filter์ ๋ฌถ์์ ์๋ฏธํ๋ค.

<br>

### ๐ก Filter์ FilterChain์ ํน์ฑ

- Servlet FilterChain์ ์์ฒญ URI path๋ฅผ ๊ธฐ๋ฐ์ผ๋ก HttpServletRequest๋ฅผ ์ฒ๋ฆฌํ๋ค.  
  
  ๋ฐ๋ผ์ ํด๋ผ์ด์ธํธ๊ฐ ์๋ฒ ์ธก ์ดํ๋ฆฌ์ผ์ด์์ ์์ฒญ์ ์ ์กํ๋ฉด  
  ์๋ธ๋ฆฟ ์ปจํ์ด๋๋ ์์ฒญ URI ๊ฒฝ๋ก๋ฅผ ๊ธฐ๋ฐ์ผ๋ก ์ด๋ค Filter์ ์ด๋ค Servlet์ ๋งคํํ ์ง ๊ฒฐ์ ํ๋ค.

- Filter๋ FilterChain ์์์ ์์๋ฅผ ์ง์ ํ  ์ ์์ผ๋ฉฐ, ์ง์ ํ ์์์ ๋ฐ๋ผ์ ๋์ํ๊ฒ ํ  ์ ์๋ค.

- FilterChain์์ Filter์ ์์๋ ๋งค์ฐ ์ค์ํ๋ฉฐ,  
  Spring Boot์์ ์ฌ๋ฌ Filter๋ฅผ ๋ฑ๋กํ๊ณ  ์์๋ฅผ ์ง์ ํ๊ธฐ ์ํด์๋ ์๋ 2๊ฐ์ง ๋ฐฉ๋ฒ์ ์ ์ฉํ  ์ ์๋ค.

  - Spring Bean์ผ๋ก ๋ฑ๋ก๋๋ Filter์ ```@Order``` ์ด๋ธํ์ด์์ ์ถ๊ฐํ๊ฑฐ๋,  
    ```Ordered``` ์ธํฐํ์ด์ค๋ฅผ ๊ตฌํํด์ Filter์ ์์๋ฅผ ์ง์ ํ  ์ ์๋ค.

  - ```FilterRegistrationBean```์ ```setOrder()``` ๋ฉ์๋๋ฅผ ์ด์ฉํด Filter์ ์์๋ฅผ ๋ช์์ ์ผ๋ก ์ง์ ํ  ์ ์๋ค.

<br>

***

<br>

## Filter์ FilterChain ๊ตฌํ

<br>

### 1. Filter ์ธํฐํ์ด์ค ๊ตฌํ

```java
public class FirstFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("== First Filter ์์ฑ ==");
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        System.out.println("== First Filter ์์ ==");
        chain.doFilter(request, response);
        System.out.println("== First Filter ์ข๋ฃ ==");
    }

    @Override
    public void destroy() {
        System.out.println("== First Filter Destroy ==");
        Filter.super.destroy();
    }
}
```

<br>

- ```init()```  
  
  ์์ฑํ Filter์ ๋ํ ์ด๊ธฐํ ์์ ์งํ. ์ดํ๋ฆฌ์ผ์ด์ ์คํ ์ ๋์ํ๋ค.

<br>

- ```doFilter()```  

  Filter๊ฐ ์ฒ๋ฆฌํ๋ ์ค์ง์ ์ธ ๋ก์ง ๊ตฌํ. ์์ฒญ์ด ๋ค์ด์ฌ ๊ฒฝ์ฐ ๋์ํ๋ค.

  - ```chain.doFilter()``` ํธ์ถ ์ด์ ์ ์ ์ฒ๋ฆฌ ์์, ํธ์ถ ์ดํ์๋ ํ์ฒ๋ฆฌ ์์์ ๋ํ ๋ก์ง์ ๊ตฌํํ  ์ ์๋ค.

<br>

- ```destroy()```

  Filter๊ฐ ์ปจํ์ด๋์์ ์ข๋ฃ๋  ๋ ํธ์ถ, Filter๊ฐ ์ฌ์ฉํ ์์์ ๋ฐ๋ฉํ๋ ์ฒ๋ฆฌ ๋ก์ง ๊ตฌํ

<br><br>

### 2. Filter ์ ์ฉ์ ์ํ Filter Configuration ๊ตฌ์ฑ

```java
@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean<FirstFilter> firstFilterRegister() {

        FilterRegistrationBean<FirstFilter> registrationBean = 
                                new FilterRegistrationBean<>(new FirstFilter());

        return registrationBean;
    }
}
```

<br>

- Servlet Filter๋ ```FilterRegistrationBean``` ํด๋์ค์ **์์ฑ์**๋ก **Filter ์ธํฐํ์ด์ค ๊ตฌํ ๊ฐ์ฒด**๋ฅผ ๋๊ฒจ์ฃผ๋ ํํ๋ก ๋ฑ๋กํ  ์ ์๋ค.

<br><br>

### 3. ์ฌ๋ฌ Filter์ ์์ ์ ์ฉ

Filter๋ ๋ค๋ฅธ Filter์ Sevlet์ ์ํฅ์ ์ฃผ๊ธฐ ๋๋ฌธ์ **์คํ ์์๊ฐ ์ค์ํ๋ค.**

<br>

```java
@Configuration
public class Config {

    @Bean
    public FilterRegistrationBean<FirstFilter> firstFilterRegister() {

        FilterRegistrationBean<FirstFilter> registrationBean = 
                                new FilterRegistrationBean<>(new FirstFilter());

        registrationBean.setOrder(1);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<SecondFilter> secondFilterRegister() {

        FilterRegistrationBean<SecondFilter> registrationBean = 
                                new FilterRegistrationBean<>(new SecondFilter());

        registrationBean.setOrder(2);
        
        return registrationBean;
    }
}
```

<br>

- ```setOrder()```  
  
  ์ง์ ๋ ์์๋ก ์คํ๋๋๋ก ์์๋ฅผ ์ง์ ํ  ์ ์๋ค.

<br><br>

***

_2022.12.04. Update_