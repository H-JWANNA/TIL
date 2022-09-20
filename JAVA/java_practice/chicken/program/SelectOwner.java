package chicken.program;

import chicken.start.MainPage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SelectOwner {
    public static void printInfo() throws IOException {
        System.out.println("-".repeat(25));
        System.out.println("[안내] 가맹주 전용 메뉴입니다.");
        System.out.println("1) 음식점 등록");
        System.out.println("2) 음식점 삭제");
        System.out.println("3) 메뉴 등록");
        System.out.println("4) 메뉴 삭제");
        System.out.println("5) 별점 확인");
        System.out.println("0) 초기 메뉴");
        System.out.print(">>> ");

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        try {
            int inputNum = Integer.parseInt(input.readLine());

            switch (inputNum) {
                case 1:
                    // UpdateStore
                    input.close();
                    break;
                case 2:
                    // DeleteStore
                    input.close();
                    break;
                case 3:
                    // UpdateMenu
                    input.close();
                    break;
                case 4:
                    // DeleteMenu
                    input.close();
                    break;
                case 5:
                    // CheckStar
                    input.close();
                    break;
                case 0:
                    MainPage.printInitialScreen();
                    input.close();
                    break;
                default:
                    System.out.println("[오류] 메뉴의 숫자를 확인하고 입력해주세요.");
                    printInfo();
                    input.close();
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("[오류] 숫자만 입력할 수 있습니다.");
            printInfo();
            input.close();
        }
    }
}
