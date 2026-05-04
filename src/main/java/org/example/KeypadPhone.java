package org.example;

/**
 * Клас для кнопкочних телефонів (похідний від Phone).
 */
public class KeypadPhone extends Phone {
    private boolean hasDualSim;
    private boolean hasFlashlight;

    public KeypadPhone() {
        setClassType("KeypadPhone");
    }

    public KeypadPhone(String brand, String model, double price, int year, int storage,
            int batteryCapacity, OperatingSystem operatingSystem, double weight, Color color,
            boolean hasDualSim, boolean hasFlashlight) {
        super(brand, model, price, year, storage, batteryCapacity, operatingSystem, weight, color);
        this.hasDualSim = hasDualSim;
        this.hasFlashlight = hasFlashlight;
        setClassType("KeypadPhone");
    }

    public boolean isHasDualSim() {
        return hasDualSim;
    }

    public void setHasDualSim(boolean hasDualSim) {
        this.hasDualSim = hasDualSim;
    }

    public boolean isHasFlashlight() {
        return hasFlashlight;
    }

    public void setHasFlashlight(boolean hasFlashlight) {
        this.hasFlashlight = hasFlashlight;
    }

    @Override
    public String toString() {
        return "KeypadPhone{" +
                "brand='" + getBrand() + '\'' +
                ", model='" + getModel() + '\'' +
                ", price=" + getPrice() +
                ", hasDualSim=" + hasDualSim +
                ", hasFlashlight=" + hasFlashlight +
                '}';
    }
}
