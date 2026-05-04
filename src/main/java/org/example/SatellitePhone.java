package org.example;

/**
 * Клас для супутникових телефонів (похідний від Phone).
 */
public class SatellitePhone extends Phone {
    private String satelliteNetwork;
    private double antennaLength;

    public SatellitePhone() {
        setClassType("SatellitePhone");
    }

    public SatellitePhone(String brand, String model, double price, int year, int storage,
                          int batteryCapacity, OperatingSystem operatingSystem, double weight, Color color,
                          String satelliteNetwork, double antennaLength) {
        super(brand, model, price, year, storage, batteryCapacity, operatingSystem, weight, color);
        setSatelliteNetwork(satelliteNetwork);
        setAntennaLength(antennaLength);
        setClassType("SatellitePhone");
    }

    public String getSatelliteNetwork() {
        return satelliteNetwork;
    }

    public void setSatelliteNetwork(String satelliteNetwork) {
        if (satelliteNetwork == null || satelliteNetwork.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва супутникової мережі не може бути порожньою");
        }
        this.satelliteNetwork = satelliteNetwork;
    }

    public double getAntennaLength() {
        return antennaLength;
    }

    public void setAntennaLength(double antennaLength) {
        if (antennaLength <= 0) {
            throw new IllegalArgumentException("Довжина антени повинна бути більше 0");
        }
        this.antennaLength = antennaLength;
    }

    @Override
    public String toString() {
        return "SatellitePhone{" +
                "brand='" + getBrand() + '\'' +
                ", model='" + getModel() + '\'' +
                ", network='" + satelliteNetwork + '\'' +
                ", antenna=" + antennaLength + "cm" +
                ", price=" + getPrice() +
                '}';
    }
}
