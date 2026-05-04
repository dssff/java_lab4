package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Завантаження даних при старті
        ArrayList<Phone> phones = FileHandler.loadFromText("input.txt");
        if (!phones.isEmpty()) {
            System.out.println("Дані успішно завантажено з файлу input.txt (" + phones.size() + " об'єктів).");
        }

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Вітаємо в системі управління телефонами!");
        // Основний цикл роботи консольного меню
        while (running) {
            try {
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
                        System.out.println("Збереження даних...");
                        FileHandler.saveToText(phones, "input.txt");
                        System.out.println("Завершення роботи. На все добре!");
                        running = false;
                        break;
                    default:
                        System.out.println("Помилка: Невідома опція. Спробуйте 1, 2 або 3.");
                }
            } catch (Exception e) {
                System.out.println("Критична помилка: " + e.getMessage());
                System.out.println("Повернення до головного меню...");
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
            System.out.println("4. SatellitePhone");
            System.out.println("5. FoldablePhone");
            System.out.println("0. Повернутися до головного меню (Скасувати)");
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
                case "4":
                    createSatellitePhone(scanner, phones);
                    backToMain = true;
                    break;
                case "5":
                    createFoldablePhone(scanner, phones);
                    backToMain = true;
                    break;
                case "0":
                    backToMain = true;
                    break;
                default:
                    System.out.println("Помилка: Невірний вибір. Спробуйте ще раз або введіть 0.");
            }
        }
    }

    /**
     * Створює базовий об'єкт Phone на основі вводу користувача.
     */
    private static void createBasicPhone(Scanner scanner, ArrayList<Phone> phones) {
        System.out.println("\n--- СТВОРЕННЯ БАЗОВОГО ТЕЛЕФОНУ ---");
        try {
            Phone phone = inputCommonData(scanner);
            phones.add(phone);
            System.out.println("Успіх: Базовий телефон додано!");
        } catch (NumberFormatException e) {
            System.out.println("Помилка: Введено некоректний формат числа.");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    /**
     * Створює об'єкт SmartPhone.
     */
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
        } catch (NumberFormatException e) {
            System.out.println("Помилка: Некоректний формат числа.");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    /**
     * Створює об'єкт KeypadPhone.
     */
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
        } catch (NumberFormatException e) {
            System.out.println("Помилка: Некоректний формат числа.");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    /**
     * Створює об'єкт SatellitePhone.
     */
    private static void createSatellitePhone(Scanner scanner, ArrayList<Phone> phones) {
        System.out.println("\n--- СТВОРЕННЯ SATELLITE PHONE ---");
        try {
            Phone base = inputCommonData(scanner);
            System.out.print("Супутникова мережа: ");
            String network = scanner.nextLine();
            System.out.print("Довжина антени (см): ");
            double antenna = Double.parseDouble(scanner.nextLine());

            SatellitePhone sp = new SatellitePhone(base.getBrand(), base.getModel(), base.getPrice(), base.getYear(),
                    base.getStorage(), base.getBatteryCapacity(), base.getOperatingSystem(), base.getWeight(),
                    base.getColor(), network, antenna);
            phones.add(sp);
            System.out.println("Успіх: SatellitePhone додано!");
        } catch (NumberFormatException e) {
            System.out.println("Помилка: Некоректний формат числа.");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    /**
     * Створює об'єкт FoldablePhone.
     */
    private static void createFoldablePhone(Scanner scanner, ArrayList<Phone> phones) {
        System.out.println("\n--- СТВОРЕННЯ FOLDABLE PHONE ---");
        try {
            Phone base = inputCommonData(scanner);
            System.out.print("Кількість мегапікселів камери: ");
            double camera = Double.parseDouble(scanner.nextLine());
            System.out.print("Чи є NFC (true/false): ");
            boolean hasNFC = Boolean.parseBoolean(scanner.nextLine());
            System.out.print("Розмір додаткового екрану (дюйми): ");
            double secondScreen = Double.parseDouble(scanner.nextLine());
            System.out.print("Тип механізму: ");
            String mechanism = scanner.nextLine();

            FoldablePhone fp = new FoldablePhone(base.getBrand(), base.getModel(), base.getPrice(), base.getYear(),
                    base.getStorage(), base.getBatteryCapacity(), base.getOperatingSystem(), base.getWeight(),
                    base.getColor(), camera, hasNFC, secondScreen, mechanism);
            phones.add(fp);
            System.out.println("Успіх: FoldablePhone додано!");
        } catch (NumberFormatException e) {
            System.out.println("Помилка: Некоректний формат числа.");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    /**
     * Зчитує спільні дані для всіх типів телефонів.
     */
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
        OperatingSystem os = OperatingSystem.valueOf(scanner.nextLine().trim().toUpperCase());
        System.out.print("Вага (г): ");
        double weight = Double.parseDouble(scanner.nextLine());
        System.out.print("Оберіть колір (BLACK, WHITE, SILVER, GOLD, BLUE, RED, GREEN): ");
        Color color = Color.valueOf(scanner.nextLine().trim().toUpperCase());

        return new Phone(brand, model, price, year, storage, batteryCapacity, os, weight, color);
    }

    /**
     * Вивів інформації про всі об'єкти
     */
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
