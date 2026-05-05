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
        ArrayList<Phone> phones = loadDataOnStartup();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Вітаємо в системі управління телефонами!");

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
                        handleSearchSubmenu(scanner, phones);
                        break;
                    case "2":
                        handleCreationSubmenu(scanner, phones);
                        break;
                    case "3":
                        displayPhones(phones);
                        break;
                    case "4":
                        saveDataOnExit(phones);
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
     * Завантажує дані з JSON або TXT файлів при запуску.
     */
    private static ArrayList<Phone> loadDataOnStartup() {
        ArrayList<Phone> phones = new ArrayList<>();
        File jsonFile = new File("input.json");
        File txtFile = new File("input.txt");

        // Беремо дані з JSON (якщо він існує і не порожній)
        if (jsonFile.exists() && jsonFile.length() > 0) {
            phones = FileHandler.loadFromJson("input.json");
            if (!phones.isEmpty()) {
                System.out.println("Дані успішно завантажено з JSON (" + phones.size() + " об'єктів).");
                return phones;
            }
        }

        // 2. Якщо JSON не спрацював, спроба зчитати TXT
        if (txtFile.exists()) {
            phones = FileHandler.loadFromText("input.txt");
            if (!phones.isEmpty()) {
                System.out.println("Дані завантажено з TXT (" + phones.size() + " об'єктів).");
                return phones;
            }
        }

        System.out.println("Файли даних не знайдено або вони порожні. Починаємо з порожньою колекцією.");
        return new ArrayList<>();
    }

    /**
     * Зберігає колекцію у файли перед завершенням роботи.
     */
    private static void saveDataOnExit(ArrayList<Phone> phones) {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("Збереження даних у файли...");
        FileHandler.saveToText(phones, "input.txt");
        FileHandler.saveToJson(phones, "input.json");
        System.out.println("Збереження завершено. На все добре!");
        System.out.println("=".repeat(40));
    }

    /**
     * Відображає підменю пошуку об'єктів.
     */
    private static void handleSearchSubmenu(Scanner scanner, ArrayList<Phone> phones) {
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
                    searchByBrand(scanner, phones);
                    break;
                case "2":
                    searchByYear(scanner, phones);
                    break;
                case "3":
                    searchByOS(scanner, phones);
                    break;
                case "0":
                    backToMain = true;
                    break;
                default:
                    System.out.println("Помилка: Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    private static void searchByBrand(Scanner scanner, ArrayList<Phone> phones) {
        System.out.print("Введіть бренд для пошуку: ");
        String brand = scanner.nextLine();
        ArrayList<Phone> results = findPhonesByBrand(phones, brand);
        displaySearchResults(results);
    }

    private static ArrayList<Phone> findPhonesByBrand(ArrayList<Phone> phones, String brand) {
        ArrayList<Phone> result = new ArrayList<>();
        for (Phone phone : phones) {
            if (phone.getBrand().equalsIgnoreCase(brand)) {
                result.add(phone);
            }
        }
        return result;
    }

    private static void searchByYear(Scanner scanner, ArrayList<Phone> phones) {
        System.out.print("Введіть рік випуску для пошуку: ");
        try {
            int year = Integer.parseInt(scanner.nextLine());
            ArrayList<Phone> results = findPhonesByYear(phones, year);
            displaySearchResults(results);
        } catch (NumberFormatException e) {
            System.out.println("Помилка: Невірний формат року.");
        }
    }

    private static ArrayList<Phone> findPhonesByYear(ArrayList<Phone> phones, int year) {
        ArrayList<Phone> result = new ArrayList<>();
        for (Phone phone : phones) {
            if (phone.getYear() == year) {
                result.add(phone);
            }
        }
        return result;
    }

    private static void searchByOS(Scanner scanner, ArrayList<Phone> phones) {
        System.out.print("Введіть операційну систему (ANDROID, IOS, WINDOWS_PHONE, OTHER): ");
        String osStr = scanner.nextLine();
        try {
            OperatingSystem os = OperatingSystem.valueOf(osStr.trim().toUpperCase());
            ArrayList<Phone> results = findPhonesByOS(phones, os);
            displaySearchResults(results);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: Невідома операційна система.");
        }
    }

    private static ArrayList<Phone> findPhonesByOS(ArrayList<Phone> phones, OperatingSystem os) {
        ArrayList<Phone> result = new ArrayList<>();
        for (Phone phone : phones) {
            if (phone.getOperatingSystem() == os) {
                result.add(phone);
            }
        }
        return result;
    }

    private static void displaySearchResults(ArrayList<Phone> results) {
        if (results.isEmpty()) {
            System.out.println("Жоден об’єкт не відповідає умовам пошуку.");
        } else {
            System.out.println("\n--- РЕЗУЛЬТАТИ ПОШУКУ ---");
            System.out.println("Знайдено об'єктів: " + results.size());
            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i).toString());
            }
        }
    }

    /**
     * Відображає підменю вибору типу об'єкта для створення.
     */
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
                    System.out.println("Помилка: Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    /**
     * Створення базового об'єкта Phone.
     */
    private static void createBasicPhone(Scanner scanner, ArrayList<Phone> phones) {
        System.out.println("\n--- СТВОРЕННЯ БАЗОВОГО ТЕЛЕФОНУ ---");
        try {
            phones.add(inputCommonData(scanner));
            System.out.println("Успіх: Телефон додано!");
        } catch (Exception e) {
            System.out.println("Помилка при створенні: " + e.getMessage());
        }
    }

    /**
     * Створення об'єкта SmartPhone.
     */
    private static void createSmartPhone(Scanner scanner, ArrayList<Phone> phones) {
        System.out.println("\n--- СТВОРЕННЯ SMARTPHONE ---");
        try {
            Phone b = inputCommonData(scanner);
            System.out.print("Кількість мегапікселів камери: ");
            double camera = Double.parseDouble(scanner.nextLine());
            System.out.print("Чи є NFC (true/false): ");
            boolean nfc = Boolean.parseBoolean(scanner.nextLine());

            phones.add(new SmartPhone(b.getBrand(), b.getModel(), b.getPrice(), b.getYear(), b.getStorage(),
                    b.getBatteryCapacity(), b.getOperatingSystem(), b.getWeight(), b.getColor(), camera, nfc));
            System.out.println("Успіх: SmartPhone додано!");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    /**
     * Створення об'єкта KeypadPhone.
     */
    private static void createKeypadPhone(Scanner scanner, ArrayList<Phone> phones) {
        System.out.println("\n--- СТВОРЕННЯ KEYPADPHONE ---");
        try {
            Phone b = inputCommonData(scanner);
            System.out.print("Чи є Dual SIM (true/false): ");
            boolean dualSim = Boolean.parseBoolean(scanner.nextLine());
            System.out.print("Чи є ліхтарик (true/false): ");
            boolean flashlight = Boolean.parseBoolean(scanner.nextLine());

            phones.add(new KeypadPhone(b.getBrand(), b.getModel(), b.getPrice(), b.getYear(), b.getStorage(),
                    b.getBatteryCapacity(), b.getOperatingSystem(), b.getWeight(), b.getColor(), dualSim, flashlight));
            System.out.println("Успіх: KeypadPhone додано!");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    /**
     * Створення об'єкта SatellitePhone.
     */
    private static void createSatellitePhone(Scanner scanner, ArrayList<Phone> phones) {
        System.out.println("\n--- СТВОРЕННЯ SATELLITE PHONE ---");
        try {
            Phone b = inputCommonData(scanner);
            System.out.print("Супутникова мережа: ");
            String network = scanner.nextLine();
            System.out.print("Довжина антени (см): ");
            double antenna = Double.parseDouble(scanner.nextLine());

            phones.add(new SatellitePhone(b.getBrand(), b.getModel(), b.getPrice(), b.getYear(), b.getStorage(),
                    b.getBatteryCapacity(), b.getOperatingSystem(), b.getWeight(), b.getColor(), network, antenna));
            System.out.println("Успіх: SatellitePhone додано!");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    /**
     * Створення об'єкта FoldablePhone.
     */
    private static void createFoldablePhone(Scanner scanner, ArrayList<Phone> phones) {
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

            phones.add(new FoldablePhone(b.getBrand(), b.getModel(), b.getPrice(), b.getYear(), b.getStorage(),
                    b.getBatteryCapacity(), b.getOperatingSystem(), b.getWeight(), b.getColor(), camera, nfc,
                    secondScreen, mechanism));
            System.out.println("Успіх: FoldablePhone додано!");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    /**
     * Допоміжний метод для вводу спільних атрибутів усіх телефонів.
     */
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

    /**
     * Вивів інформації про всі об'єкти
     */
    private static void displayPhones(ArrayList<Phone> phones) {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("--- СПИСОК ТЕЛЕФОНІВ У КОЛЕКЦІЇ ---");
        if (phones.isEmpty()) {
            System.out.println("Список порожній.");
        } else {
            for (int i = 0; i < phones.size(); i++) {
                System.out.println((i + 1) + ". " + phones.get(i).toString());
            }
        }
    }
}
