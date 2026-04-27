package org.example;

import java.util.Objects;

/**
 * Клас, що представляє мобільний телефон.
 */
public class Phone {
    private String brand;
    private String model;
    private double price;
    private int year;
    private int storage;
    private int batteryCapacity;
    private String operatingSystem;
    private double weight;
    private String color;

    /**
     * Конструктор для ініціалізації об'єкта Phone.
     *
     * @param brand           бренд
     * @param model           модель
     * @param price           ціна
     * @param year            рік випуску
     * @param storage         обсяг пам'яті
     * @param batteryCapacity ємність батареї
     * @param operatingSystem операційна система
     * @param weight          вага
     * @param color           колір
     */
    public Phone(String brand, String model, double price, int year, int storage, 
                 int batteryCapacity, String operatingSystem, double weight, String color) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.year = year;
        this.storage = storage;
        this.batteryCapacity = batteryCapacity;
        this.operatingSystem = operatingSystem;
        this.weight = weight;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Double.compare(phone.price, price) == 0 &&
               year == phone.year &&
               storage == phone.storage &&
               batteryCapacity == phone.batteryCapacity &&
               Double.compare(phone.weight, weight) == 0 &&
               Objects.equals(brand, phone.brand) &&
               Objects.equals(model, phone.model) &&
               Objects.equals(operatingSystem, phone.operatingSystem) &&
               Objects.equals(color, phone.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, price, year, storage, batteryCapacity, operatingSystem, weight, color);
    }

    @Override
    public String toString() {
        return "Phone{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", year=" + year +
                ", storage=" + storage +
                ", batteryCapacity=" + batteryCapacity +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }
}