package org.example;

/**
 * Клас для смартфонів з гнучким екраном (похідний від SmartPhone).
 */
public class FoldablePhone extends SmartPhone {
    private double secondaryScreenSize;
    private String foldMechanismType;

    public FoldablePhone(String brand, String model, double price, int year, int storage,
            int batteryCapacity, OperatingSystem operatingSystem, double weight, Color color,
            double cameraMegapixels, boolean hasNFC,
            double secondaryScreenSize, String foldMechanismType) {
        super(brand, model, price, year, storage, batteryCapacity, operatingSystem, weight, color, cameraMegapixels,
                hasNFC);
        setSecondaryScreenSize(secondaryScreenSize);
        setFoldMechanismType(foldMechanismType);
    }

    public double getSecondaryScreenSize() {
        return secondaryScreenSize;
    }

    public void setSecondaryScreenSize(double secondaryScreenSize) {
        if (secondaryScreenSize < 0) {
            throw new IllegalArgumentException("Розмір додаткового екрану не може бути менше 0");
        }
        this.secondaryScreenSize = secondaryScreenSize;
    }

    public String getFoldMechanismType() {
        return foldMechanismType;
    }

    public void setFoldMechanismType(String foldMechanismType) {
        if (foldMechanismType == null || foldMechanismType.trim().isEmpty()) {
            throw new IllegalArgumentException("Тип механізму не може бути порожнім");
        }
        this.foldMechanismType = foldMechanismType;
    }

    @Override
    public String toString() {
        return "FoldablePhone{" +
                "brand='" + getBrand() + '\'' +
                ", model='" + getModel() + '\'' +
                ", mainCamera=" + getCameraMegapixels() + "MP" +
                ", secondScreen=" + secondaryScreenSize + "\"" +
                ", mechanism='" + foldMechanismType + '\'' +
                ", price=" + getPrice() +
                '}';
    }
}
