package com.codestates.section1.java_basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class MultiplicationTable {
    public static void main(String[] args) throws IOException {
        System.out.print("[안내] 희망하는 구구단을 숫자로 입력해주세요 (2 ~ 9) : ");

        // input num 생성
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(input.readLine());
        input.close();

        // 입력값 확인 문구
        System.out.printf("\n%d단이 입력되었습니다.\n", num);

        // 입력값이 범위 밖의 숫자일 경우 프로그램 종료
        if (num < 2 || num > 9) {
            System.out.println("[경고] 구구단은 2단 ~ 9단까지만 선택할 수 있습니다.");
            System.out.println("프로그램을 종료합니다.");
        }

        // 입력값이 범위 내의 숫자일 경우 구구단 실행
        else {
            for (int i = 1; i < 10; i++) {
                System.out.printf("%d * %d = %d\n", num, i, num * i);
            }
        }
    }
}
