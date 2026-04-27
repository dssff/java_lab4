package org.example;

import java.util.Objects;

/**
 * Клас мобільного телефону.
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
     * Конструктор з усіма параметрами.
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

    @return 
    public String getBrand() {
        return brand;
    }

    /** Встановлює бренд */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @return модель 
    public String getModel() {
        return model;
    }

    /** Встановлює модель */
    public void setModel(String model) {
        this.model = model;
    }

    @return 
    public double getPrice() {
        return price;
    }

    /** Встановлює ціну */
    public void setPrice(double price) {
        this.price = price;
    }

    @return 
    public int getYear() {
        return year;
    }

    /** Встановлює рік випуску */
    public void setYear(int year) {
        this.year = year;
    }

    @return 
    public int getStorage() {
        return storage;
    }

    /** Встановлює обсяг пам'яті */
    public void setStorage(int storage) {
        this.storage = storage;
    }

    @return 
    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    /** Встановлює ємність батареї */
    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    @return 
    public String getOperatingSystem() {
        return operatingSystem;
    }

    /** Встановлює ОС */
    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    @return 
    public double getWeight() {
        return weight;
    }

    /** Встановлює вагу */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @return 
    public String getColor() {
        return color;
    }

    /** Встановлює колір */
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
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
                ", storage=" + storage + " GB" +
                ", batteryCapacity=" + batteryCapacity + " mAh" +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", weight=" + weight + " g" +
                ", color='" + color + '\'' +
                '}';
    }
}
