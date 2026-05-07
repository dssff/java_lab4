package org.example;

import java.util.Objects;

/**
 * Клас, що представляє мобільний телефон.
 */
public abstract class Phone implements Comparable<Phone> {
    private String brand;
    private String model;
    private double price;
    private int year;
    private int storage;
    private int batteryCapacity;
    private OperatingSystem operatingSystem;
    private double weight;
    private Color color;
    private String classType;

    /**
     * Порожній конструктор для Gson.
     */
    public Phone() {
        this.classType = "Phone";
    }

    /**
     * Конструктор для ініціалізації об'єкта Phone.
     */
    public Phone(String brand, String model, double price, int year, int storage,
            int batteryCapacity, OperatingSystem operatingSystem, double weight, Color color) {

        setBrand(brand);
        setModel(model);
        setPrice(price);
        setYear(year);
        setStorage(storage);
        setBatteryCapacity(batteryCapacity);
        setOperatingSystem(operatingSystem);
        setWeight(weight);
        setColor(color);
        this.classType = "Phone";
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    /**
     * Конструктор копіювання для створення нового об'єкта на основі існуючого.
     */
    public Phone(Phone other) {
        if (other == null) {
            throw new IllegalArgumentException("Об'єкт для копіювання не може бути null");
        }
        this.brand = other.brand;
        this.model = other.model;
        this.price = other.price;
        this.year = other.year;
        this.storage = other.storage;
        this.batteryCapacity = other.batteryCapacity;
        this.operatingSystem = other.operatingSystem;
        this.weight = other.weight;
        this.color = other.color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Бренд не може бути порожнім");
        }
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Модель не може бути порожньою");
        }
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Ціна не може бути від'ємною");
        }
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year < 1990 || year > 2030) {
            throw new IllegalArgumentException("Рік має бути в межах від 1990 до 2030");
        }
        this.year = year;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        if (storage <= 0) {
            throw new IllegalArgumentException("Обсяг пам'яті має бути більшим за 0");
        }
        this.storage = storage;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        if (batteryCapacity <= 0) {
            throw new IllegalArgumentException("Ємність батареї має бути більшою за 0");
        }
        this.batteryCapacity = batteryCapacity;
    }

    public OperatingSystem getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(OperatingSystem operatingSystem) {
        if (operatingSystem == null) {
            throw new IllegalArgumentException("Операційна система не може бути null");
        }
        this.operatingSystem = operatingSystem;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Вага має бути більшою за 0");
        }
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("Колір не може бути null");
        }
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
                ", storage=" + storage +
                ", batteryCapacity=" + batteryCapacity +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public int compareTo(Phone other) {
        if (other == null) {
            return 1;
        }
        int brandCompare = this.brand.compareToIgnoreCase(other.brand);
        if (brandCompare != 0) {
            return brandCompare;
        }
        return this.model.compareToIgnoreCase(other.model);
    }
}