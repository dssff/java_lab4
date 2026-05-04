package org.example;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    /**
     * Зберігає колекцію телефонів у текстовий файл.
     */
    public static void saveToText(ArrayList<Phone> phones, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Phone p : phones) {
                writer.println(serializeToText(p));
            }
        } catch (IOException e) {
            System.out.println("Помилка при збереженні у TXT: " + e.getMessage());
        }
    }

    /**
     * Зчитує колекцію телефонів з текстового файлу.
     */
    public static ArrayList<Phone> loadFromText(String fileName) {
        ArrayList<Phone> phones = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) return phones;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNum = 0;
            while ((line = reader.readLine()) != null) {
                lineNum++;
                try {
                    Phone p = deserializeFromText(line);
                    if (p != null) phones.add(p);
                } catch (Exception e) {
                    System.out.println("Помилка у рядку " + lineNum + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка при читанні TXT: " + e.getMessage());
        }
        return phones;
    }

    private static String serializeToText(Phone p) {
        StringBuilder sb = new StringBuilder();
        sb.append(p.getClassType()).append(";");
        sb.append(p.getBrand()).append(";");
        sb.append(p.getModel()).append(";");
        sb.append(p.getPrice()).append(";");
        sb.append(p.getYear()).append(";");
        sb.append(p.getStorage()).append(";");
        sb.append(p.getBatteryCapacity()).append(";");
        sb.append(p.getOperatingSystem().name()).append(";");
        sb.append(p.getWeight()).append(";");
        sb.append(p.getColor().name());

        if (p instanceof FoldablePhone) {
            FoldablePhone fp = (FoldablePhone) p;
            sb.append(";").append(fp.getCameraMegapixels()).append(";").append(fp.isHasNFC())
              .append(";").append(fp.getSecondaryScreenSize()).append(";").append(fp.getFoldMechanismType());
        } else if (p instanceof SmartPhone) {
            SmartPhone sp = (SmartPhone) p;
            sb.append(";").append(sp.getCameraMegapixels()).append(";").append(sp.isHasNFC());
        } else if (p instanceof KeypadPhone) {
            KeypadPhone kp = (KeypadPhone) p;
            sb.append(";").append(kp.isHasDualSim()).append(";").append(kp.isHasFlashlight());
        } else if (p instanceof SatellitePhone) {
            SatellitePhone sat = (SatellitePhone) p;
            sb.append(";").append(sat.getSatelliteNetwork()).append(";").append(sat.getAntennaLength());
        }
        return sb.toString();
    }

    private static Phone deserializeFromText(String line) {
        String[] parts = line.split(";");
        if (parts.length < 10) return null;

        String type = parts[0];
        String brand = parts[1];
        String model = parts[2];
        double price = Double.parseDouble(parts[3]);
        int year = Integer.parseInt(parts[4]);
        int storage = Integer.parseInt(parts[5]);
        int battery = Integer.parseInt(parts[6]);
        OperatingSystem os = OperatingSystem.valueOf(parts[7]);
        double weight = Double.parseDouble(parts[8]);
        Color color = Color.valueOf(parts[9]);

        switch (type) {
            case "Phone":
                return new Phone(brand, model, price, year, storage, battery, os, weight, color);
            case "SmartPhone":
                return new SmartPhone(brand, model, price, year, storage, battery, os, weight, color,
                        Double.parseDouble(parts[10]), Boolean.parseBoolean(parts[11]));
            case "KeypadPhone":
                return new KeypadPhone(brand, model, price, year, storage, battery, os, weight, color,
                        Boolean.parseBoolean(parts[10]), Boolean.parseBoolean(parts[11]));
            case "SatellitePhone":
                return new SatellitePhone(brand, model, price, year, storage, battery, os, weight, color,
                        parts[10], Double.parseDouble(parts[11]));
            case "FoldablePhone":
                return new FoldablePhone(brand, model, price, year, storage, battery, os, weight, color,
                        Double.parseDouble(parts[10]), Boolean.parseBoolean(parts[11]),
                        Double.parseDouble(parts[12]), parts[13]);
            default:
                return null;
        }
    }
}
