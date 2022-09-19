import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class PredictingVisitors_V1 {
    public static void main(String[] args) throws IOException {
        // 입력받을 객체 생성
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        // 안내문 출력
        System.out.println("[인공지능 프로그램 V1]");
        System.out.println("=".repeat(30));
        System.out.print("[System] 예측에 영향을 주는 원인(광고비)의 값을 알려주세요 (단위 원) : ");
        int val = Integer.parseInt(input.readLine());

        // 변수 정의
        int a = 2;
        int b = 1;

        // 방문자 수 예측
        int visitors = (a * val) + b;

        // 출력
        System.out.printf("[안내] 입력된 광고비는 %d원 입니다.\n", val);
        System.out.printf("\n[안내] AI의 예측(클릭 수)은 %d회 입니다.\n", visitors);
    }
}
