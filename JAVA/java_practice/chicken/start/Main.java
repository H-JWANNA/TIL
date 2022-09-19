package chicken.start;

import chicken.program.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("[치킨의 민족 프로그램 V1]");
        System.out.println("-".repeat(25));
        System.out.println("1) [가맹주전용] 음식점 등록");
        System.out.println("2) [소비자전용] 음식 주문");
        System.out.println("3) [소비자전용] 별점 등록");
        System.out.println("4) 별점 조회");
        System.out.println("5) 프로그램 종료");

        SelectNum.messagePrint();
    }
}
