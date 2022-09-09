import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Bankomat {
    public static void main(String[] args) {
        Bankomat bankomat = new Bankomat();
        bankomat.menu(bankomat);
        bankomat.outputMoney();
        bankomat.inputMoney();
    }

    int kartSum = 30000;
    ArrayList<Integer> money10 = new ArrayList<Integer>(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10));
    ArrayList<Integer> money50 = new ArrayList<Integer>(Arrays.asList(50, 50, 50, 50, 50, 50, 50, 50, 50, 50));
    ArrayList<Integer> money100 = new ArrayList<Integer>(Arrays.asList(100, 100, 100, 100, 100, 100, 100, 100, 100, 100));

    int sumBank = (money10.size() *10) + (money50.size() * 50) + (money100.size() * 100);
    void menu(Bankomat bankomat) {
        System.out.println("Чтобы снять наличные, нажмите 1");
        System.out.println("Чтобы пополнить счет, нажмите 2");
        System.out.println("Проверить баланс, нажмите 3");
        System.out.println("Посмотреть максимальную сумму для снятия, нажмите 4");
        System.out.println("Чтобы завершить работу, нажмите 5");
        Scanner scanner = new Scanner(System.in);
        Integer number = scanner.nextInt();
        switch (number) {
            case 1:
                bankomat.outputMoney();
                bankomat.infoBalance();
                System.out.println();
                bankomat.menu(bankomat);
                break;
            case 2:
                bankomat.inputMoney();
                bankomat.infoBalance();
                System.out.println();
                bankomat.menu(bankomat);
                break;
            case 3:
                bankomat.infoBalance();
                System.out.println();
                bankomat.menu(bankomat);
                break;
            case 4:
                int sum10 = money10.size() * 10;
                int sum50 = money50.size() * 50;
                int sum100 = money100.size() * 100;
                int sumBancomat = sum10 + sum50 + sum100;
                System.out.println("Максимальная сумма: " + sumBancomat);
                System.out.println("В банкомате имеются купюры: 10 рублей " + money10.size() + " шт.");
                System.out.println("В банкомате имеются купюры: 50 рублей " + money50.size() + " шт.");
                System.out.println("В банкомате имеются купюры: 100 рублей " + money100.size() + " шт.");
                System.out.println();
                bankomat.menu(bankomat);
                break;
            case 5:
                System.exit(0);
        }
        if (number > 5 || number < 0) {
            throw new MoneyException("Такой операции нет");
        }
    }

    void infoBalance() {
        System.out.println("Баланс: " + kartSum);
    }

    void outputMoney() {
        System.out.println("Введите сумму для снятия");
        int ten = 0;
        int fifty = 0;
        int hundred = 0;
        Scanner scanner = new Scanner(System.in);
        Integer output = scanner.nextInt();
        if (output % 10 != 0) {
            throw new MoneyException("Неверная сумма");
        }
        if (output < 1000) {
            hundred = output / 100 * 100;
            fifty = (output - hundred) / 50 * 50;
            ten = (output - hundred - fifty) / 10 * 10;
        }
        if (hundred >= 100) {
            int i = hundred / 100;
            for (int j = 0; j < i; j++) {
                money100.remove(j);
            }
        }
        if (fifty >= 50) {
            int i = fifty / 50;
            for (int j = 0; j < i; j++) {
                money50.remove(j);
            }
        }
        if (ten >= 10) {
            int i = ten / 10;
            for (int j = 0; j < i; j++) {
                money10.remove(j);
            }
        }
            if (sumBank < output) {
                throw new MoneyException("Отсутствуют купюры в банкомате");
            }
            if (output <= kartSum) {
                kartSum = kartSum - output;
            } else if (output > kartSum) {
                throw new MoneyException("У вас недостаточно средств");
            }

        }

        void inputMoney () {
            System.out.println("Внесите сумму");
            Scanner scanner = new Scanner(System.in);
            Integer input = scanner.nextInt();
            if (input == 10) {
                money10.add(input);
                kartSum = input + kartSum;
            } else if (input == 50) {
                money50.add(input);
                kartSum = input + kartSum;
            } else if (input == 100) {
                money100.add(input);
                kartSum = input + kartSum;
            } else if (input != 10 && input != 50 && input != 100) {
                System.out.println("Банкомат не принимает такие купюры!");
            }
        }
    }