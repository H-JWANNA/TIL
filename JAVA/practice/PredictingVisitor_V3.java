package com.codestates.section1.java_basic;

public class PredictingVisitor_V3 {
    public static void main(String[] args) {
        // 데이터
        double[][] visitors = {{374, 1161}, {385, 1401}, {375, 1621}, {401, 1681}};
        double sum_diff_pow = 0;

        // 안내문 출력
        System.out.println("[인공지능 프로그램 V3]");
        System.out.println("=".repeat(30));

        // 데이터 출력
        System.out.println("[안내] 인공지능 프로그램에 할당된 데이터");
        for (int i = 1; i <= visitors.length; i++) {
            for (int j = 1; j < visitors[i - 1].length; j++) {
                System.out.printf("%d번째 데이터) [실제값] 웹 페이지 방문자 수 : %.1f  [인공지능 예측값] 웹 페이지 방문자 수 : %.1f\n",
                        i, visitors[i - 1][j - 1], visitors[i - 1][j]);
            }
        }

        // 오차 출력
        System.out.println("");
        for (int i = 1; i <= visitors.length; i++) {
            double diff = visitors[i-1][0] - visitors[i-1][1];
            double diff_pow = Math.pow(diff, 2);
            System.out.printf("[%d번째 데이터] 실제 값과 예측 값의 오차 : %.1f\n", i, diff);
            System.out.printf("[안내] 오차의 제곱 : %.1f\n\n", diff_pow);
            sum_diff_pow += diff_pow;
        }
        
        // 오차 제곱 합 출력
        System.out.println("=".repeat(30));
        System.out.printf("[안내] 인공지능 프로그램의 성능(오차의 제곱 합) : %.1f\n", sum_diff_pow);
    }
}
