package chicken.program.owner;

import chicken.program.SelectOwner;
import chicken.program.StoreList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UpdateStore extends StoreList{
    String name;
    String menu;
    int price;
    public void printInfo() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        SelectOwner selectOwner = new SelectOwner();

        System.out.println("[안내] 가맹주님 반갑습니다.");
        System.out.print("[안내] 상호명을 입력해주세요 : ");
        this.name = input.readLine();
        storeName.add(this.name);

        System.out.print("[안내] 대표 메뉴를 입력해주세요 : ");
        this.menu = input.readLine();
        storeMenu.add(this.menu);

        try {
            System.out.print("[안내] 메뉴 가격을 입력해주세요 : ");
            this.price = Integer.parseInt(input.readLine());
            menuPrice.add(this.price);
        } catch (NumberFormatException e) {
            System.out.println("[오류] 숫자만 입력할 수 있습니다.");
            printInfo();
            input.close();
        }

        System.out.printf("[시스템] [%s] %s : %d 정상적으로 등록되었습니다.\n", name, menu, price);
        selectOwner.printInfo();
    }
}
