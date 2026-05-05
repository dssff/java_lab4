package org.example;

import java.util.ArrayList;

/**
 * Клас Store, що управляє колекцією товарів (StoreItem).
 */
public class Store {
    private ArrayList<StoreItem> inventory;

    public Store() {
        this.inventory = new ArrayList<>();
    }

    /**
     * Додає новий телефон у колекцію.
     * Якщо телефон вже є збільшує його кількість на задане значення.
     */
    public void addNewPhone(Phone ph, int quantity) {
        if (ph == null || quantity <= 0) {
            return;
        }

        for (StoreItem item : inventory) {
            if (item.getPhone().equals(ph)) {
                item.addQuantity(quantity);
                return;
            }
        }

        inventory.add(new StoreItem(ph, quantity));
    }

    public ArrayList<StoreItem> getInventory() {
        return inventory;
    }

    /**
     * Пошук товарів за брендом.
     */
    public ArrayList<StoreItem> findPhonesByBrand(String brand) {
        ArrayList<StoreItem> result = new ArrayList<>();
        for (StoreItem item : inventory) {
            if (item.getPhone().getBrand().equalsIgnoreCase(brand)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Пошук товарів за роком випуску.
     */
    public ArrayList<StoreItem> findPhonesByYear(int year) {
        ArrayList<StoreItem> result = new ArrayList<>();
        for (StoreItem item : inventory) {
            if (item.getPhone().getYear() == year) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Пошук товарів за операційною системою.
     */
    public ArrayList<StoreItem> findPhonesByOS(OperatingSystem os) {
        ArrayList<StoreItem> result = new ArrayList<>();
        for (StoreItem item : inventory) {
            if (item.getPhone().getOperatingSystem() == os) {
                result.add(item);
            }
        }
        return result;
    }
}
