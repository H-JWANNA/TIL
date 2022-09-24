package chicken.start;

import chicken.program.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainPage {
    public static void main(String[] args) throws IOException {
        System.out.println("[배달 프로그램 데모 V1]");
        printInitialScreen();
    }

    public static void printInitialScreen() throws IOException {
        System.out.println("-".repeat(25));
        System.out.println("1) 소비자 전용 메뉴");
        System.out.println("2) 가맹주 전용 메뉴");
        System.out.println("0) 프로그램 종료");
        System.out.print(">>> ");

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        try {
            int inputNum = Integer.parseInt(input.readLine());

            switch (inputNum) {
                case 1:
                    SelectCustomer selectCustomer = new SelectCustomer();
                    selectCustomer.printInfo();
                    input.close();
                    break;
                case 2:
                    SelectOwner selectOwner = new SelectOwner();
                    selectOwner.printInfo();
                    input.close();
                    break;
                case 0 :
                    System.out.println("-".repeat(25));
                    System.out.println("[안내] 이용해주셔서 감사합니다.");
                    break;
                default:
                    System.out.println("[오류] 메뉴의 숫자를 확인하고 입력해주세요.");
                    printInitialScreen();
                    input.close();
                    break;
            }
        }
        catch (NumberFormatException e) {
            System.out.println("[오류] 숫자만 입력할 수 있습니다.");
            printInitialScreen();
            input.close();
        }
    }
}
