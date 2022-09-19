import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

interface Menu {
    String toString();
    int menuPrice();
}

class Gimbab implements Menu {
    @Override
    public String toString() {
        return "김밥";
    }

    @Override
    public int menuPrice() {
        return 3000;
    }
}

class EggGimbab implements Menu {
    @Override
    public String toString() {
        return "계란김밥";
    }

    @Override
    public int menuPrice() {
        return 4000;
    }
}

class ChungmuGimbab implements Menu {
    @Override
    public String toString() {
        return "충무김밥";
    }

    @Override
    public int menuPrice() {
        return 4000;
    }
}

class RiceCake implements Menu {
    @Override
    public String toString() {
        return "떡볶이";
    }

    @Override
    public int menuPrice() {
        return 5000;
    }
}

public class RestaurantKiosk {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int count;

    private int selectMenu() throws IOException {
        System.out.println("[안내] 원하시는 메뉴의 번호를 선택해주세요.");
        Menu menu1 = new Gimbab();
        Menu menu2 = new EggGimbab();
        Menu menu3 = new ChungmuGimbab();
        Menu menu4 = new RiceCake();
        System.out.printf("1) %s(%d원) ", menu1.toString(), menu1.menuPrice());
        System.out.printf("2) %s(%d원) ", menu2.toString(), menu2.menuPrice());
        System.out.printf("3) %s(%d원) ", menu3.toString(), menu3.menuPrice());
        System.out.printf("4) %s(%d원) ", menu4.toString(), menu4.menuPrice());
        System.out.println("");
        int menuNum = Integer.parseInt(input.readLine());

        // 예외 처리
        try {
            if (menuNum > 4 || menuNum < 1) {
                System.out.println("[경고] 메뉴에 포함된 번호를 입력해주세요.\n");
                return selectMenu();
            }
        } catch (NumberFormatException e) {
            System.out.println("[경고] 숫자만 입력해주세요.\n");
            return selectMenu();
        }

        return menuNum;
    }

    private int countMenu() throws IOException {
        System.out.println("-".repeat(30));
        System.out.println("[안내] 선택하신 메뉴의 수량을 입력해주세요.");
        System.out.println("(※ 최대 주문가능 수량 : 99)");
        int menuAmount = Integer.parseInt(input.readLine());

        // 예외 처리
        try {
            if (menuAmount > 99 || menuAmount < 1) {
                System.out.printf("[경고] %d개는 입력하실 수 없습니다.\n\n", menuAmount);
                return countMenu();
            }
        } catch (NumberFormatException e) {
            System.out.println("[경고] 숫자만 입력해주세요.\n");
            return countMenu();
        }

        return menuAmount;
    }

    public static void main(String[] args) throws IOException {
        RestaurantKiosk restaurantKiosk = new RestaurantKiosk();
        // 안내문 출력
        System.out.println("[안내] 안녕하세요. 김가네 김밥에 오신것을 환영합니다.");
        System.out.println("=".repeat(30));

        switch (restaurantKiosk.selectMenu()) {
            case 1: {
                Menu gimbab = new Gimbab();
                count = restaurantKiosk.countMenu();
                System.out.printf("[안내] 주문하신 %s %d개의 총 금액은 %d원 입니다.", gimbab.toString(), count, count * gimbab.menuPrice());
                break;
            }
            case 2: {
                Menu egggimbab = new EggGimbab();
                count = restaurantKiosk.countMenu();
                System.out.printf("[안내] 주문하신 %s %d개의 총 금액은 %d원 입니다.", egggimbab.toString(), count, count * egggimbab.menuPrice());
                break;
            }
            case 3: {
                Menu chungmugimbab = new ChungmuGimbab();
                count = restaurantKiosk.countMenu();
                System.out.printf("[안내] 주문하신 %s %d개의 가격은 %d원 입니다.", chungmugimbab.toString(), count, count * chungmugimbab.menuPrice());
                break;
            }
            case 4: {
                Menu ricecake = new RiceCake();
                count = restaurantKiosk.countMenu();
                System.out.printf("[안내] 주문하신 %s %d개의 총 금액은 %d원 입니다.", ricecake.toString(), count, count * ricecake.menuPrice());
                break;
            }
        }
        System.out.println("\n[안내] 이용해주셔서 감사합니다.");

    }
}
