package chicken.program;

import chicken.program.customer.OrderCheck;
import chicken.program.customer.OrderMenu;
import chicken.start.MainPage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SelectCustomer {

    public void printInfo() throws IOException {
        System.out.println("-".repeat(25));
        System.out.println("[안내] 소비자 전용 메뉴입니다.");
        System.out.println("1) 메뉴 주문");
        System.out.println("2) 주문 확인");
        System.out.println("0) 초기 메뉴");
        System.out.print(">>> ");

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        try {
            int inputNum = Integer.parseInt(input.readLine());

            switch (inputNum) {
                case 1:
                    OrderMenu orderMenu = new OrderMenu();
                    orderMenu.printList();
                    input.close();
                    break;
                case 2:
                    OrderCheck orderCheck = new OrderCheck();
                    orderCheck.printOrder();
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
