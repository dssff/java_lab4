package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Головний клас програми для управління колекцією телефонів.
 */
public class Main {

    public static void main(String[] args) {
        // Завантаження даних при старті
        Store store = loadDataOnStartup();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Вітаємо в системі управління телефонами");

        while (running) {
            try {
                System.out.println("\n--- ГОЛОВНЕ МЕНЮ ---");
                System.out.println("1. Пошук об’єкта");
                System.out.println("2. Створити новий об’єкт");
                System.out.println("3. Вивести інформацію про всі об’єкти");
                System.out.println("4. Завершити роботу програми");
                System.out.print("Оберіть опцію: ");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        handleSearchSubmenu(scanner, store);
                        break;
                    case "2":
                        handleCreationSubmenu(scanner, store);
                        break;
                    case "3":
                        displayPhones(store);
                        break;
                    case "4":
                        saveDataOnExit(store);
                        running = false;
                        break;
                    default:
                        System.out.println("Помилка: Невідома опція. Спробуйте 1, 2, 3 або 4.");
                }
            } catch (Exception e) {
                System.out.println("Критична помилка: " + e.getMessage());
            }
        }
        scanner.close();
    }

    /**
     * Завантажує дані з TXT файлу при запуску.
     */
    private static Store loadDataOnStartup() {
        Store store = new Store();
        File txtFile = new File("input.txt");

        if (txtFile.exists()) {
            ArrayList<Phone> phones = FileHandler.loadFromText("input.txt");
            if (!phones.isEmpty()) {
                for (Phone p : phones) {
                    store.addNewPhone(p, 1);
                }
                System.out.println("Дані успішно завантажено з TXT (зчитано рядків: " + phones.size() + ").");
                return store;
            }
        }

        System.out.println("Файли даних не знайдено або вони порожні. Починаємо з порожнім магазином.");
        return store;
    }

    /**
     * Зберігає колекцію у файли перед завершенням роботи.
     */
    private static void saveDataOnExit(Store store) {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("Збереження даних у файли...");

        ArrayList<Phone> allPhones = new ArrayList<>();
        for (StoreItem item : store.getInventory()) {
            // Розгортаємо кількість для збереження по одному об'єкту у рядок
            for (int i = 0; i < item.getQuantity(); i++) {
                allPhones.add(item.getPhone());
            }
        }

        FileHandler.saveToText(allPhones, "input.txt");
        FileHandler.saveToJson(allPhones, "input.json");
        System.out.println("Збереження завершено. На все добре!");
        System.out.println("=".repeat(40));
    }

    /**
     * Відображає підменю пошуку об'єктів.
     */
    private static void handleSearchSubmenu(Scanner scanner, Store store) {
        boolean backToMain = false;
        while (!backToMain) {
            System.out.println("\n--- ПІДМЕНЮ ПОШУКУ ---");
            System.out.println("1. Пошук за брендом");
            System.out.println("2. Пошук за роком випуску");
            System.out.println("3. Пошук за операційною системою");
            System.out.println("0. Повернутися до головного меню");
            System.out.print("Вибір: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    searchByBrand(scanner, store);
                    break;
                case "2":
                    searchByYear(scanner, store);
                    break;
                case "3":
                    searchByOS(scanner, store);
                    break;
                case "0":
                    backToMain = true;
                    break;
                default:
                    System.out.println("Помилка: Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    private static void searchByBrand(Scanner scanner, Store store) {
        System.out.print("Введіть бренд для пошуку: ");
        String brand = scanner.nextLine();
        ArrayList<StoreItem> results = store.findPhonesByBrand(brand);
        displaySearchResults(results);
    }

    private static void searchByYear(Scanner scanner, Store store) {
        System.out.print("Введіть рік випуску для пошуку: ");
        try {
            int year = Integer.parseInt(scanner.nextLine());
            ArrayList<StoreItem> results = store.findPhonesByYear(year);
            displaySearchResults(results);
        } catch (NumberFormatException e) {
            System.out.println("Помилка: Невірний формат року.");
        }
    }

    private static void searchByOS(Scanner scanner, Store store) {
        System.out.print("Введіть операційну систему (ANDROID, IOS, WINDOWS_PHONE, OTHER): ");
        String osStr = scanner.nextLine();
        try {
            OperatingSystem os = OperatingSystem.valueOf(osStr.trim().toUpperCase());
            ArrayList<StoreItem> results = store.findPhonesByOS(os);
            displaySearchResults(results);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: Невідома операційна система.");
        }
    }

    private static void displaySearchResults(ArrayList<StoreItem> results) {
        if (results.isEmpty()) {
            System.out.println("Жоден об’єкт не відповідає умовам пошуку.");
        } else {
            System.out.println("\n--- РЕЗУЛЬТАТИ ПОШУКУ ---");
            System.out.println("Знайдено об'єктів (видів): " + results.size());
            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i).toString());
            }
        }
    }

    /**
     * Відображає підменю вибору типу об'єкта для створення.
     */
    private static void handleCreationSubmenu(Scanner scanner, Store store) {
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
                    createBasicPhone(scanner, store);
                    backToMain = true;
                    break;
                case "2":
                    createSmartPhone(scanner, store);
                    backToMain = true;
                    break;
                case "3":
                    createKeypadPhone(scanner, store);
                    backToMain = true;
                    break;
                case "4":
                    createSatellitePhone(scanner, store);
                    backToMain = true;
                    break;
                case "5":
                    createFoldablePhone(scanner, store);
                    backToMain = true;
                    break;
                case "0":
                    backToMain = true;
                    break;
                default:
                    System.out.println("Помилка: Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    private static int askForQuantity(Scanner scanner) {
        System.out.print("Кількість штук для додавання в магазин: ");
        try {
            int q = Integer.parseInt(scanner.nextLine());
            return Math.max(q, 1);
        } catch (NumberFormatException e) {
            System.out.println("Помилка формату. Буде додано 1 шт.");
            return 1;
        }
    }

    private static void createBasicPhone(Scanner scanner, Store store) {
        System.out.println("\n--- СТВОРЕННЯ БАЗОВОГО ТЕЛЕФОНУ ---");
        try {
            Phone p = inputCommonData(scanner);
            int qty = askForQuantity(scanner);
            store.addNewPhone(p, qty);
            System.out.println("Успіх: Телефон додано до магазину!");
        } catch (Exception e) {
            System.out.println("Помилка при створенні: " + e.getMessage());
        }
    }

    private static void createSmartPhone(Scanner scanner, Store store) {
        System.out.println("\n--- СТВОРЕННЯ SMARTPHONE ---");
        try {
            Phone b = inputCommonData(scanner);
            System.out.print("Кількість мегапікселів камери: ");
            double camera = Double.parseDouble(scanner.nextLine());
            System.out.print("Чи є NFC (true/false): ");
            boolean nfc = Boolean.parseBoolean(scanner.nextLine());

            int qty = askForQuantity(scanner);
            store.addNewPhone(new SmartPhone(b.getBrand(), b.getModel(), b.getPrice(), b.getYear(), b.getStorage(),
                    b.getBatteryCapacity(), b.getOperatingSystem(), b.getWeight(), b.getColor(), camera, nfc), qty);
            System.out.println("Успіх: SmartPhone додано до магазину!");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void createKeypadPhone(Scanner scanner, Store store) {
        System.out.println("\n--- СТВОРЕННЯ KEYPADPHONE ---");
        try {
            Phone b = inputCommonData(scanner);
            System.out.print("Чи є Dual SIM (true/false): ");
            boolean dualSim = Boolean.parseBoolean(scanner.nextLine());
            System.out.print("Чи є ліхтарик (true/false): ");
            boolean flashlight = Boolean.parseBoolean(scanner.nextLine());

            int qty = askForQuantity(scanner);
            store.addNewPhone(new KeypadPhone(b.getBrand(), b.getModel(), b.getPrice(), b.getYear(), b.getStorage(),
                    b.getBatteryCapacity(), b.getOperatingSystem(), b.getWeight(), b.getColor(), dualSim, flashlight),
                    qty);
            System.out.println("Успіх: KeypadPhone додано до магазину!");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void createSatellitePhone(Scanner scanner, Store store) {
        System.out.println("\n--- СТВОРЕННЯ SATELLITE PHONE ---");
        try {
            Phone b = inputCommonData(scanner);
            System.out.print("Супутникова мережа: ");
            String network = scanner.nextLine();
            System.out.print("Довжина антени (см): ");
            double antenna = Double.parseDouble(scanner.nextLine());

            int qty = askForQuantity(scanner);
            store.addNewPhone(new SatellitePhone(b.getBrand(), b.getModel(), b.getPrice(), b.getYear(), b.getStorage(),
                    b.getBatteryCapacity(), b.getOperatingSystem(), b.getWeight(), b.getColor(), network, antenna),
                    qty);
            System.out.println("Успіх: SatellitePhone додано до магазину!");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void createFoldablePhone(Scanner scanner, Store store) {
        System.out.println("\n--- СТВОРЕННЯ FOLDABLE PHONE ---");
        try {
            Phone b = inputCommonData(scanner);
            System.out.print("Мегапікселі камери: ");
            double camera = Double.parseDouble(scanner.nextLine());
            System.out.print("Наявність NFC (true/false): ");
            boolean nfc = Boolean.parseBoolean(scanner.nextLine());
            System.out.print("Розмір додаткового екрану: ");
            double secondScreen = Double.parseDouble(scanner.nextLine());
            System.out.print("Тип механізму: ");
            String mechanism = scanner.nextLine();

            int qty = askForQuantity(scanner);
            store.addNewPhone(new FoldablePhone(b.getBrand(), b.getModel(), b.getPrice(), b.getYear(), b.getStorage(),
                    b.getBatteryCapacity(), b.getOperatingSystem(), b.getWeight(), b.getColor(), camera, nfc,
                    secondScreen, mechanism), qty);
            System.out.println("Успіх: FoldablePhone додано до магазину!");
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
        System.out.print("Рік випуску (1990-2030): ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Пам'ять (ГБ): ");
        int storage = Integer.parseInt(scanner.nextLine());
        System.out.print("Батарея (мАг): ");
        int battery = Integer.parseInt(scanner.nextLine());
        System.out.print("ОС (ANDROID, IOS, WINDOWS_PHONE, OTHER): ");
        OperatingSystem os = OperatingSystem.valueOf(scanner.nextLine().trim().toUpperCase());
        System.out.print("Вага (г): ");
        double weight = Double.parseDouble(scanner.nextLine());
        System.out.print("Колір (BLACK, WHITE, SILVER, GOLD, BLUE, RED, GREEN): ");
        Color color = Color.valueOf(scanner.nextLine().trim().toUpperCase());

        return new Phone(brand, model, price, year, storage, battery, os, weight, color);
    }

    private static void displayPhones(Store store) {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("--- АСОРТИМЕНТ МАГАЗИНУ ---");
        ArrayList<StoreItem> items = store.getInventory();
        if (items.isEmpty()) {
            System.out.println("Магазин порожній.");
        } else {
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i).toString());
            }
        }
    }
}
