package com.codestates.section1.java_basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class FibonacciNum {
    public static void main(String[] args) throws IOException {
        // 입력받을 객체 생성
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        // 안내문 출력
        System.out.println("[안내] 피보나치 수열 프로그램 시작");
        System.out.print("원하는 수열의 개수를 입력해주세요 : ");
        int input_num = Integer.parseInt(input.readLine());

        // 수열 출력하기
        System.out.println("[피보나치 수열 출력]");

        for(int number: numbers(input_num)) {
            System.out.println(number);
        }

        System.out.println("[안내] 프로그램을 종료합니다.");
    }

    // 피보나치 수열 구하는 numbers 배열
    private static int[] numbers(int num) {
        int[] fib = new int[num];

        for (int i = 0; i < num; i++) {
            if (i <= 1) fib[i] = 1;
            else fib[i] = fib[i-1] + fib[i-2];
        }

        return fib;
    }
}