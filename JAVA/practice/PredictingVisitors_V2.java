package com.codestates.section1.java_basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class PredictingVisitors_V2 {
    public static void main(String[] args) throws IOException {
        // 입력받을 객체 생성
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        // 안내문 출력
        System.out.println("[인공지능 프로그램 V2]");
        System.out.println("=".repeat(30));
        System.out.print("[System] a값을 입력하여 주세요 : ");
        int a = Integer.parseInt(input.readLine());
        System.out.print("[System] b값을 입력하여 주세요 : ");
        int b = Integer.parseInt(input.readLine());
        System.out.println("=".repeat(30));

        // 광고비 정의
        double[] ad = {580, 700, 810, 840};

        // 광고비 출력
        System.out.println("\n[안내] 입력된 '광고비'는 다음과 같습니다.");
        for (int i = 1; i <= ad.length; i++) {
            System.out.printf("%d번째) %.1f원  ", i, ad[i - 1]);
        }

        // AI 결과 출력
        System.out.println("\n\n[안내] AI의 '웹 페이지 방문자' 예측 결과는 다음과 같습니다.");
        for (int i = 1; i <= ad.length; i++) {
            System.out.printf("%d번째 예측) %.1f회 방문\n", i, (a * ad[i - 1]) + b);
        }
    }
}
