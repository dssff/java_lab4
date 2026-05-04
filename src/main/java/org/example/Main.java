package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Phone> phones = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Вітаємо в системі управління телефонами!");
        // Основний цикл роботи консольного меню
        while (running) {
            System.out.println("\n--- ГОЛОВНЕ МЕНЮ ---");
            System.out.println("1. Створити новий об’єкт");
            System.out.println("2. Вивести інформацію про всі об’єкти");
            System.out.println("3. Завершити роботу програми");
            System.out.print("Оберіть опцію: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    handleCreationSubmenu(scanner, phones);
                    break;
                case "2":
                    displayPhones(phones);
                    break;
                case "3":
                    System.out.println("Завершення роботи.");
                    running = false;
                    break;
                default:
                    System.out.println("Помилка: Невідома опція.");
            }
        }
        scanner.close();
    }

    // Підменю для вибору типу об'єкта
    private static void handleCreationSubmenu(Scanner scanner, ArrayList<Phone> phones) {
        boolean backToMain = false;
        while (!backToMain) {
            System.out.println("\n--- ОБЕРІТЬ ТИП ОБ'ЄКТА ---");
            System.out.println("1. Базовий Phone");
            System.out.println("2. SmartPhone");
            System.out.println("3. KeypadPhone");
            System.out.println("4. SatellitePhone ");
            System.out.println("5. FoldablePhone");
            System.out.println("0. Повернутися до головного меню");
            System.out.print("Вибір: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    createBasicPhone(scanner, phones);
                    backToMain = true;
                    break;
                case "2":
                    createSmartPhone(scanner, phones);
                    backToMain = true;
                    break;
                case "3":
                    createKeypadPhone(scanner, phones);
                    backToMain = true;
                    break;
                case "0":
                    backToMain = true;
                    break;
                default:
                    System.out.println("Помилка: Невірний тип.");
            }
        }
    }

    private static void createBasicPhone(Scanner scanner, ArrayList<Phone> phones) {
        System.out.println("\n--- СТВОРЕННЯ БАЗОВОГО ТЕЛЕФОНУ ---");
        try {
            Phone phone = inputCommonData(scanner);
            phones.add(phone);
            System.out.println("Успіх: Базовий телефон додано!");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void createSmartPhone(Scanner scanner, ArrayList<Phone> phones) {
        System.out.println("\n--- СТВОРЕННЯ SMARTPHONE ---");
        try {
            Phone base = inputCommonData(scanner);
            System.out.print("Кількість мегапікселів камери: ");
            double camera = Double.parseDouble(scanner.nextLine());
            System.out.print("Чи є NFC (true/false): ");
            boolean hasNFC = Boolean.parseBoolean(scanner.nextLine());

            SmartPhone sp = new SmartPhone(base.getBrand(), base.getModel(), base.getPrice(), base.getYear(),
                    base.getStorage(), base.getBatteryCapacity(), base.getOperatingSystem(), base.getWeight(),
                    base.getColor(), camera, hasNFC);
            phones.add(sp);
            System.out.println("Успіх: SmartPhone додано!");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void createKeypadPhone(Scanner scanner, ArrayList<Phone> phones) {
        System.out.println("\n--- СТВОРЕННЯ KEYPADPHONE ---");
        try {
            Phone base = inputCommonData(scanner);
            System.out.print("Чи є Dual SIM (true/false): ");
            boolean dualSim = Boolean.parseBoolean(scanner.nextLine());
            System.out.print("Чи є ліхтарик (true/false): ");
            boolean flashlight = Boolean.parseBoolean(scanner.nextLine());

            KeypadPhone kp = new KeypadPhone(base.getBrand(), base.getModel(), base.getPrice(), base.getYear(),
                    base.getStorage(), base.getBatteryCapacity(), base.getOperatingSystem(), base.getWeight(),
                    base.getColor(), dualSim, flashlight);
            phones.add(kp);
            System.out.println("Успіх: KeypadPhone додано!");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static Phone inputCommonData(Scanner scanner) {
        System.out.print("Бренд: ");
        String brand = scanner.nextLine();
        System.out.print("Модель: ");
        String model = scanner.nextLine();
        System.out.print("Ціна: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Рік випуску: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Обсяг пам'яті (ГБ): ");
        int storage = Integer.parseInt(scanner.nextLine());
        System.out.print("Ємність батареї (мАг): ");
        int batteryCapacity = Integer.parseInt(scanner.nextLine());
        System.out.print("Оберіть ОС (ANDROID, IOS, WINDOWS_PHONE, OTHER): ");
        OperatingSystem os = OperatingSystem.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Вага (г): ");
        double weight = Double.parseDouble(scanner.nextLine());
        System.out.print("Оберіть колір (BLACK, WHITE, SILVER, GOLD, BLUE, RED, GREEN): ");
        Color color = Color.valueOf(scanner.nextLine().toUpperCase());

        return new Phone(brand, model, price, year, storage, batteryCapacity, os, weight, color);
    }

    private static void displayPhones(ArrayList<Phone> phones) {
        System.out.println("\n--- СПИСОК ТЕЛЕФОНІВ ---");
        if (phones.isEmpty()) {
            System.out.println("Список порожній.");
        } else {
            for (int i = 0; i < phones.size(); i++) {
                System.out.println((i + 1) + ". " + phones.get(i).toString());
            }
        }
    }
}
