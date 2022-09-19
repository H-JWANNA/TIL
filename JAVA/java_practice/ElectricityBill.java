import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class ElectricityBill {
    public static void main(String[] args) throws IOException{
        // 간단 전기요금 청구액 계산방법 (실제와 다름)
        // 1. 기본 요금 (원 단위 미만 절사) > int
        // 2. 부가가치세 = 기본 요금 * 0.1 (원 단위 미만 반올림)
        // 3. 전력산업기반기금 = 기본요금 * 0.037 (10원 미만 절사)
        // 4. 청구요금합계 1+2+3 (10원 미만 절사)

        // 주택용 전기요금 (저압) 계산법
        // 저압의 경우 100kWh 이하는 kWh당 60.7원, 100kWh 초과는 125.9원, 200kWh 초과는 187.9원, 300kWh 초과는 280.6원, 400kWh 초과는 417.7원, 500kWh 초과는 670.6원의 전력량 요금을 내야한다.

        System.out.println("=".repeat(28));
        System.out.println("주택용 전기요금(저압) 계산기");
        System.out.println("=".repeat(28));

        // 입력
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("\n사용량을 입력하세요(단위:kWh) : ");
        int charge = Integer.parseInt(input.readLine());
        int basic_charge;

        // 1. 기본요금 계산 (원 단위 미만 절사)
        if (charge <= 100) {
            basic_charge = (int) (charge * 60.7);
        }
        else if (charge <= 200) {
            double basic_charge_200 = (charge - 100) * 125.9;
            double basic_charge_100 = 100 * 60.7;
            basic_charge = (int) (basic_charge_100 + basic_charge_200);
        }
        else if (charge <= 300) {
            double basic_charge_300 = (charge - 200) * 187.9;
            double basic_charge_200 = 100 * 125.9;
            double basic_charge_100 = 100 * 60.7;
            basic_charge = (int) (basic_charge_100 + basic_charge_200 + basic_charge_300);
        }
        else if (charge <= 400) {
            double basic_charge_400 = (charge - 300) * 280.6;
            double basic_charge_300 = 100 * 187.9;
            double basic_charge_200 = 100 * 125.9;
            double basic_charge_100 = 100 * 60.7;
            basic_charge = (int) (basic_charge_100 + basic_charge_200 + basic_charge_300 + basic_charge_400);
        }
        else if (charge <= 500) {
            double basic_charge_500 = (charge - 400) * 417.7;
            double basic_charge_400 = 100 * 280.6;
            double basic_charge_300 = 100 * 187.9;
            double basic_charge_200 = 100 * 125.9;
            double basic_charge_100 = 100 * 60.7;
            basic_charge = (int) (basic_charge_100 + basic_charge_200 + basic_charge_300 + basic_charge_400 + basic_charge_500);
        }
        else {
            double basic_charge_500_plus = (charge - 500) * 670.6;
            double basic_charge_500 = 100 * 417.7;
            double basic_charge_400 = 100 * 280.6;
            double basic_charge_300 = 100 * 187.9;
            double basic_charge_200 = 100 * 125.9;
            double basic_charge_100 = 100 * 60.7;
            basic_charge = (int) (basic_charge_100 + basic_charge_200 + basic_charge_300 + basic_charge_400 + basic_charge_500 + basic_charge_500_plus);
        }

        // 2. 부가가치세 계산 (원 단위 미만 절사)
        int vat = (int) Math.round(0.1 * basic_charge);

        // 3. 전력산업기반기금 계산 (10원 미만 절사) > Math.round()에 10을 나누고 10을 곱함
        int industry_based_fund = (int) Math.round((0.037*basic_charge/10)*10 );

        // 4. 청구요금합계 (10원 미만 절사)
        int bill = Math.round((basic_charge + vat + industry_based_fund)/10)*10;

        // 출력
        System.out.println(charge + "kWh의 전기 요금은 " + bill + "원 입니다.");

    }
}
