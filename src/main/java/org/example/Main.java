package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Phone> phones = new ArrayList<>();

        // Початкові дані для демонстрації
        phones.add(new Phone("Apple", "iPhone 17", 799.99, 2025, 256, 3200, "iOS", 170.5, "Black"));
        phones.add(new Phone("Samsung", "Galaxy S23", 900.00, 2024, 128, 3900, "Android", 168.0, "White"));

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Вітаємо в системі управління телефонами!");

        // Основний цикл роботи консольного меню
        while (running) {
            System.out.println("\n--- МЕНЮ ---");
            System.out.println("1. Створити новий телефон");
            System.out.println("2. Вивести всі телефони");
            System.out.println("3. Вийти");
            System.out.print("Оберіть опцію: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createNewPhone(scanner, phones);
                    break;
                case "2":
                    displayPhones(phones);
                    break;
                case "3":
                    System.out.println("Завершення роботи. На все добре!");
                    running = false;
                    break;
                default:
                    System.out.println("Помилка: Невідома опція. Спробуйте '1', '2' або '3'.");
            }
        }
        scanner.close();
    }

    // Створює новий телефон, зчитуючи дані з консолі

    private static void createNewPhone(Scanner scanner, ArrayList<Phone> phones) {
        System.out.println("\n--- СТВОРЕННЯ ТЕЛЕФОНУ ---");
        boolean success = false;

        // Цикл триває, поки користувач не введе всі дані без помилок
        while (!success) {
            try {
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

                System.out.print("Операційна система: ");
                String os = scanner.nextLine();

                System.out.print("Вага (г): ");
                double weight = Double.parseDouble(scanner.nextLine());

                System.out.print("Колір: ");
                String color = scanner.nextLine();

                // Спроба створення об'єкта
                Phone newPhone = new Phone(brand, model, price, year, storage, batteryCapacity, os, weight, color);
                phones.add(newPhone);

                System.out.println("\nУспіх: Телефон додано до списку!");
                success = true; // Дані коректні виходимо з циклу

            } catch (NumberFormatException e) {
                System.out.println("\n[ПОМИЛКА ВВОДУ]: Очікувалось числове значення, а введено текст.");
                System.out.println("Будь ласка, почніть введення з початку.");
            } catch (IllegalArgumentException e) {
                System.out.println("\n[ПОМИЛКА ВАЛІДАЦІЇ]: " + e.getMessage());
                System.out.println("Будь ласка, почніть введення з початку, враховуючи обмеження.");
            } catch (Exception e) {
                System.out.println("\n[НЕПЕРЕДБАЧЕНА ПОМИЛКА]: " + e.getMessage());
                System.out.println("Спробуйте ще раз.");
            }
        }
    }

    // Вивід списка усіх збережених телефонів

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
