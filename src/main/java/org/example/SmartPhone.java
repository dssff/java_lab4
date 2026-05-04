package org.example;

/**
 * Клас для смартфонів (похідний від Phone).
 */
public class SmartPhone extends Phone {
    private double cameraMegapixels;
    private boolean hasNFC;

    public SmartPhone() {
        setClassType("SmartPhone");
    }

    public SmartPhone(String brand, String model, double price, int year, int storage,
            int batteryCapacity, OperatingSystem operatingSystem, double weight, Color color,
            double cameraMegapixels, boolean hasNFC) {
        super(brand, model, price, year, storage, batteryCapacity, operatingSystem, weight, color);
        setCameraMegapixels(cameraMegapixels);
        this.hasNFC = hasNFC;
        setClassType("SmartPhone");
    }

    public double getCameraMegapixels() {
        return cameraMegapixels;
    }

    public void setCameraMegapixels(double cameraMegapixels) {
        if (cameraMegapixels < 0) {
            throw new IllegalArgumentException("Кількість мегапікселів повинна бути більше 0");
        }
        this.cameraMegapixels = cameraMegapixels;
    }

    public boolean isHasNFC() {
        return hasNFC;
    }

    public void setHasNFC(boolean hasNFC) {
        this.hasNFC = hasNFC;
    }

    @Override
    public String toString() {
        return "SmartPhone{" +
                "brand='" + getBrand() + '\'' +
                ", model='" + getModel() + '\'' +
                ", price=" + getPrice() +
                ", cameraMegapixels=" + cameraMegapixels +
                ", hasNFC=" + hasNFC +
                '}';
    }
}
