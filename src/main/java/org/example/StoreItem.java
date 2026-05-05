package org.example;

/**
 * Клас-обгортка для зберігання товару (Phone) та його кількості в магазині.
 */
public class StoreItem {
    private Phone phone;
    private int quantity;

    public StoreItem(Phone phone, int quantity) {
        if (phone == null) {
            throw new IllegalArgumentException("Телефон не може бути null");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Кількість не може бути від'ємною");
        }
        this.phone = phone;
        this.quantity = quantity;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        if (phone == null) {
            throw new IllegalArgumentException("Телефон не може бути null");
        }
        this.phone = phone;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Кількість не може бути від'ємною");
        }
        this.quantity = quantity;
    }

    public void addQuantity(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Не можна додати від'ємну кількість");
        }
        this.quantity += amount;
    }

    @Override
    public String toString() {
        return "Кількість: " + quantity + " шт. | Товар: " + phone.toString();
    }
}
