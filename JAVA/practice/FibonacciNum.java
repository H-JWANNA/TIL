package com.codestates.section1.java_basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class FibonacciNum {
    // 입력 받을 객체 생성
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    // 수열 개수 입력받기
    private int getUserInput() throws IOException {
        System.out.print("원하는 수열의 개수를 입력해주세요 : ");
        return Integer.parseInt(input.readLine());
    }

    // 피보나치 수열 구하기
    private int[] getFibonacci(int num) {
        int[] fib = new int[num];

        for (int i = 0; i < num; i++) {
            if (i <= 1) fib[i] = 1;
            else fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }

    // 수열 출력
    private void printNumbers(int[] arr) {
        System.out.println("[피보나치 수열 출력]");

        for (int number : arr) {
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws IOException {
        // 객체 생성
        FibonacciNum fibNum = new FibonacciNum();

        System.out.println("[안내] 피보나치 수열 프로그램 시작");
        
        // 메서드 실행
        fibNum.printNumbers(fibNum.getFibonacci(fibNum.getUserInput()));

        System.out.println("[안내] 프로그램을 종료합니다.");
    }
}