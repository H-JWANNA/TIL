package chicken.program;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SelectNum {
    public static void messagePrint() throws IOException {
        System.out.println("[시스템] 무엇을 도와드릴까요?");

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int selectNum = Integer.parseInt(input.readLine());

        switch (selectNum) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
        }
    }
}
