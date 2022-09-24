package chicken.program;

import chicken.program.owner.DeleteStore;
import chicken.program.owner.UpdateStore;
import chicken.start.MainPage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SelectOwner {

    public void printInfo() throws IOException {
        System.out.println("-".repeat(25));
        System.out.println("[안내] 가맹주 전용 메뉴입니다.");
        System.out.println("1) 음식점 등록");
        System.out.println("2) 음식점 삭제");
        System.out.println("0) 초기 메뉴");
        System.out.print(">>> ");

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        try {
            int inputNum = Integer.parseInt(input.readLine());

            switch (inputNum) {
                case 1:
                    UpdateStore updateStore = new UpdateStore();
                    updateStore.printInfo();
                    input.close();
                    break;
                case 2:
                    DeleteStore deleteStore = new DeleteStore();
                    deleteStore.askDelete();
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
