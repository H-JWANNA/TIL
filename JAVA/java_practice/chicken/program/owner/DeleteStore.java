package chicken.program.owner;

import chicken.program.SelectOwner;
import chicken.program.StoreList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class DeleteStore extends StoreList {
    String name;
    public void askDelete() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        SelectOwner selectOwner = new SelectOwner();

        System.out.println("[안내] 가맹주님 반갑습니다.");
        System.out.print("[안내] 삭제하실 상호명을 입력해주세요 : ");
        this.name = input.readLine();

        System.out.println("[경고] 상호를 삭제하시면 해당 상호에 등록된 모든 메뉴가 사라집니다.");
        System.out.println("[경고] 그래도 진행하시겠습니까? (Y/N)");
        System.out.print(">>> ");
        String yesNo = input.readLine();
        switch (yesNo) {
            case "Y":
                if(storeName.contains(name)) Delete();
                else {
                    System.out.println("[오류] 해당하는 가게를 찾을 수 없습니다");
                    System.out.println("[안내] 이전화면으로 돌아갑니다.");
                    selectOwner.printInfo();
                }
                break;
            case "N":
                System.out.println("[안내] 삭제를 취소합니다.");
                System.out.println("[안내] 이전화면으로 돌아갑니다.");
                selectOwner.printInfo();
                break;
            default:
                System.out.println("[오류] Y 또는 N만 입력 가능합니다.");
                askDelete();
        }
    }

    private void Delete() throws IOException {
        System.out.println("-".repeat(25));
        SelectOwner selectOwner2 = new SelectOwner();

        ArrayList<Integer> deleteIndex = new ArrayList<>();

        for(int i = 0; i < storeName.size(); i++) {
            if ((storeName.get(i)).equals(this.name)) deleteIndex.add(i);
        }

        deleteIndex.sort(Comparator.reverseOrder());

        for (int i : deleteIndex) {
            storeName.remove(i);
            storeMenu.remove(i);
            menuPrice.remove(i);
        }

        System.out.println("[안내] 삭제가 완료되었습니다.");
        selectOwner2.printInfo();
    }
}
