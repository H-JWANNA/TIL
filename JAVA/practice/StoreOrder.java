package com.codestates.section1.java_basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class StoreOrder {
    // 입력받을 객체 생성
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    // 필드 생성
    int menu_num;
    String[] menu_arr;
    int[] menu_count;

    public StoreOrder() {
        this.menu_arr = new String[5];
        this.menu_count = new int[5];
        Arrays.fill(menu_arr, "");
    }

    // 프로그램 기능 확인
    private void showMenu() {
        System.out.println("-".repeat(30));
        System.out.println("1. 물건 정보(제품명) 등록하기");
        System.out.println("2. 물건 정보(제품명) 등록 취소하기");
        System.out.println("3. 물건 넣기 (제품 입고)");
        System.out.println("4. 물건 빼기 (제품 출고)");
        System.out.println("5. 재고 조회");
        System.out.println("6. 프로그램 종료");
        System.out.println("-".repeat(30));
    }

    // 프로그램 기능 선택
    private int selectMenu() throws IOException {
        System.out.print("[System] 원하는 기능의 번호를 입력하세요 : ");
        try {
            menu_num = Integer.parseInt(input.readLine());
            if (menu_num > 6 || menu_num < 1) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("1 ~ 6의 숫자만 입력 가능합니다.");
            selectMenu();
        }
        return menu_num;
    }

    // 1. 물건 정보 등록하기
    private void prod_input() throws IOException {
        System.out.print("[System] 제품 등록을 원하는 제품명을 입력하세요 : ");
        String menu_name = input.readLine();

        for (int i = 0; i < menu_arr.length; i++) {
            // 빈칸이 없을 때
            if (i == menu_arr.length - 1) {
                System.out.println("[Error] 등록 가능한 빈 칸이 없습니다.");
                System.out.println("[Error] 등록 취소 후 다시 등록해주세요.");
            }
            // 빈칸에 물건 등록
            if ("".equals(menu_arr[i])) {
                menu_arr[i] = menu_name;
                System.out.println("[System] 등록이 완료되었습니다.");
                printMenu();
                break;
            }
        }

    }

    // 2. 물건 정보 등록 취소하기
    private void prod_remove() throws IOException {
        System.out.print("[System] 제품 등록 취소를 원하는 제품명을 입력하세요 : ");
        String menu_name = input.readLine();

        for (int i = 0; i < menu_arr.length; i++) {
            // 제품이 없을 때
            if (i == menu_arr.length - 1) {
                System.out.println("[Error] 취소를 원하는 제품이 없습니다.");
                break;
            }
            // 제품 확인 후 취소
            if (menu_arr[i].equals(menu_name)) {
                if (menu_count[i] == 0) {
                    menu_arr[i] = "";
                    System.out.println("[System] 제품 취소가 완료되었습니다.");
                    printMenu();
                }
                else {
                    System.out.println("[Error] 수량이 남은 제품은 등록 취소할 수 없습니다.");
                }
                break;
            }
        }

    }

    // 3. 물건 넣기 (제품 입고)
    private void prod_amount_add() throws IOException {
        System.out.println("[물건의 수량을 추가합니다. (입고)");
        printMenu();
        System.out.print("수량을 추가할 제품명을 입력하세요 : ");
        String menu_name = input.readLine();
        System.out.print("[System] 추가할 수량을 입력해주세요 : ");
        int menu_amount = Integer.parseInt(input.readLine());

        for (int i = 0; i < menu_arr.length; i++) {
            // 제품이 없을 때
            if (i == menu_arr.length - 1) {
                System.out.println("[Error] 일치하는 제품이 없습니다.");
                break;
            }
            // 제품 확인
            if (menu_arr[i].equals(menu_name)) {
                menu_count[i] = menu_amount;
                System.out.println("[Clear] 정상적으로 제품의 수량 추가가 완료되었습니다.");
                printMenu();
                break;
            }
        }


    }

    // 4. 물건 빼기 (제품 출고)
    private void prod_amount_decrease() throws IOException {
        System.out.println("[System] 제품의 출고를 진행합니다.");
        printMenu();
        System.out.print("[System] 출고를 진행할 제품명을 입력하세요 : ");
        String menu_name = input.readLine();
        System.out.print("[System] 원하는 출고량을 입력하세요 : ");
        int menu_amount = Integer.parseInt(input.readLine());

        for (int i = 0; i < menu_arr.length; i++) {
            // 제품이 없을 때
            if (i == menu_arr.length - 1) {
                System.out.println("[Error] 일치하는 제품이 없습니다.");
                break;
            }
            // 제품 확인
            if (menu_arr[i].equals(menu_name)) {
                // 정상 출고
                if (menu_count[i] - menu_amount >= 0) {
                    menu_count[i] -= menu_amount;
                    System.out.println("[Clear] 정상적으로 출고가 완료되었습니다.");
                    printMenu();
                }
                // 입고량보다 출고량이 많을 때
                else {
                    System.out.println("[Error] 출고량은 마이너스(-)가 될 수 없습니다.");
                }
                break;
            }
        }

    }

    // 5. 재고 조회
    private void printMenu() {
        System.out.println("[System] 현재 등록된 제품 및 수량 ▼");
        for (int i = 0; i < menu_arr.length; i++) {
            System.out.print(">> ");
            if (menu_arr[i].isEmpty()) {
                System.out.println("등록 가능");
            } else {
                System.out.printf("%s : %d개\n", menu_arr[i], menu_count[i]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력받을 객체 생성
        StoreOrder storeOrder = new StoreOrder();

        // 안내문 출력
        System.out.println("[Item_Storage_V3]\n" +
                "-".repeat(30) +
                "\n[System] 홍정완 점장님 어서오세요." +
                "\n[System] 해당 프로그램의 기능입니다.");

        do {
            storeOrder.showMenu();

            switch (storeOrder.selectMenu()) {
                case 1:
                    storeOrder.prod_input();
                    break;
                case 2:
                    storeOrder.prod_remove();
                    break;
                case 3:
                    storeOrder.prod_amount_add();
                    break;
                case 4:
                    storeOrder.prod_amount_decrease();
                    break;
                case 5:
                    storeOrder.printMenu();
                    break;
            }

        } while (storeOrder.menu_num != 6);
        {
            System.out.println("\n[System] 프로그램을 종료합니다.");
            System.out.println("[System] 감사합니다.");
        }
    }
}
