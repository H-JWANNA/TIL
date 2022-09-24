package chicken.program.customer;

import chicken.program.SelectCustomer;
import chicken.program.StoreList;

import java.io.IOException;

public class OrderCheck extends StoreList {
    public void printOrder() throws IOException {
        SelectCustomer selectCustomer = new SelectCustomer();
        System.out.println("[안내] 현재 주문 내역은 아래와 같습니다.");
        System.out.println("-".repeat(25));

        System.out.printf("[%s] %s : %s원\n",
                nowOrder.get(0),
                nowOrder.get(1),
                nowOrder.get(2));
        selectCustomer.printInfo();
    }
}
