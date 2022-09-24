package chicken.program.customer;

import chicken.program.SelectCustomer;
import chicken.program.StoreList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderMenu extends StoreList {
    String menuName;

    public void printList() throws IOException {
        System.out.println("[안내] 반갑습니다 고객님.");
        System.out.println("[안내] 주문 가능한 목록입니다.");
        System.out.println("-".repeat(25));
        int index = 0;
        while(index < storeName.size()) {
            System.out.printf("1. [%s] %s : %d원\n",
                    storeName.get(index), storeMenu.get(index), menuPrice.get(index));
            index++;
        }
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("[안내] 주문하실 메뉴를 입력해주세요 : ");
        this.menuName = input.readLine();

        if (!storeMenu.contains(menuName)) {
            System.out.println("[오류] 일치하는 메뉴가 존재하지 않습니다.");
            System.out.println("[안내] 이전화면으로 돌아갑니다.");
            SelectCustomer selectCustomer = new SelectCustomer();
            selectCustomer.printInfo();
        }
        else order();
    }

    public void order() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        SelectCustomer selectCustomer = new SelectCustomer();
        int index = 0;
        for(int i = 0; i < storeMenu.size(); i++) {
            if(menuName.equals(storeMenu.get(i))) index = i;
        }
        System.out.printf("[안내] 선택하신 메뉴는 %s 가격은 %d원 입니다.\n" ,menuName, menuPrice.get(index));
        System.out.println("[안내] 정말로 주문하시겠습니까? (Y/N)");
        System.out.print(">>> ");
        String yesNo = input.readLine();
        switch (yesNo) {
            case "Y":
                nowOrder.add(storeName.get(index));
                nowOrder.add(storeMenu.get(index));
                nowOrder.add(Integer.toString(menuPrice.get(index)));
                System.out.println("[안내] 성공적으로 주문되었습니다.");
                System.out.println("[안내] 주문해주셔서 감사합니다.");
                selectCustomer.printInfo();
                break;
            case "N":
                System.out.println("[안내] 주문이 취소되었습니다.");
                System.out.println("[안내] 이전화면으로 돌아갑니다.");
                selectCustomer.printInfo();
                break;
            default:
                System.out.println("[오류] Y 또는 N만 입력 가능합니다.");
                order();
                break;
        }
    }
}
