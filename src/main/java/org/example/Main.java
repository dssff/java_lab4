package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Phone> phones = new ArrayList<>();

        phones.add(new Phone("Apple", "iPhone 17", 799.99, 2025));
        phones.add(new Phone("Samsung", "Galaxy S23", 900.00, 2024));
        phones.add(new Phone("Google", "Pixel 7 Pro", 1199.00, 2025));
        phones.add(new Phone("Xiaomi", "Redmi 9 ", 199.00, 2019));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть дані для 5-го телефону:");

        System.out.print("Бренд: ");
        String brand = scanner.nextLine();

        System.out.print("Модель: ");
        String model = scanner.nextLine();

        int year = 0;
        boolean validYear = false;
        while (!validYear) {
            System.out.print("Рік випуску: ");
            try {
                year = Integer.parseInt(scanner.nextLine());
                if (year < 1990 || year > 2030) {
                    System.out.println("Помилка: Некоректний рік. Спробуйте ще раз.");
                } else {
                    validYear = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Помилка: Введіть коректне число. Спробуйте ще раз.");
            }
        }

        double price = 0.0;
        boolean validPrice = false;

        while (!validPrice) {
            System.out.print("Ціна: ");
            try {
                price = Double.parseDouble(scanner.nextLine());
                if (price < 0) {
                    System.out.println("Помилка: Ціна не може бути від'ємною. Спробуйте ще раз.");
                } else {
                    validPrice = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Помилка: Введіть коректне значення. Спробуйте ще раз.");
            }
        }

        phones.add(new Phone(brand, model, price, year));

        System.out.println("\nСписок усіх телефонів:");
        for (Phone phone : phones) {
            System.out.println(phone.toString());
        }

        scanner.close();
    }
}
