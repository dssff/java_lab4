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
     * @param processor       процесор (агрегація)
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
     *
     * @param other об'єкт класу Phone, який потрібно скопіювати
     * @throws IllegalArgumentException якщо переданий об'єкт є null
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

    /**
     * Повертає операційну систему телефону.
     */
    public OperatingSystem getOperatingSystem() {
        return operatingSystem;
    }

    /**
     * Встановлює операційну систему телефону.
     */
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

    /**
     * Повертає колір телефону.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Встановлює колір телефону.
     */
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

}