package org.example;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Клас для роботи з файлами.
 */
public class FileHandler {

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Phone.class, new PhoneAdapter())
            .create();

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
     * Зчитує дані з текстового файлу.
     */
    public static ArrayList<Phone> loadFromText(String fileName) {
        ArrayList<Phone> phones = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists())
            return phones;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    Phone p = deserializeFromText(line);
                    if (p != null)
                        phones.add(p);
                } catch (Exception e) {
                    System.out.println("Помилка зчитування рядка: " + line + " (" + e.getMessage() + ")");
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка при відкритті файлу TXT: " + e.getMessage());
        }
        return phones;
    }

    /**
     * Зберігає колекцію у JSON.
     */
    public static void saveToJson(ArrayList<Phone> phones, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(phones, writer);
        } catch (IOException e) {
            System.out.println("Помилка запису JSON: " + e.getMessage());
        }
    }

    /**
     * Читає колекцію з JSON.
     */
    public static ArrayList<Phone> loadFromJson(String fileName) {
        File file = new File(fileName);
        if (!file.exists())
            return new ArrayList<>();

        try (FileReader reader = new FileReader(file)) {
            Type listType = new TypeToken<ArrayList<Phone>>() {
            }.getType();
            ArrayList<Phone> phones = gson.fromJson(reader, listType);
            return (phones != null) ? phones : new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Помилка обробки JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static String serializeToText(Phone p) {
        StringBuilder sb = new StringBuilder();
        sb.append(p.getClassType()).append(";")
                .append(p.getBrand()).append(";")
                .append(p.getModel()).append(";")
                .append(p.getPrice()).append(";")
                .append(p.getYear()).append(";")
                .append(p.getStorage()).append(";")
                .append(p.getBatteryCapacity()).append(";")
                .append(p.getOperatingSystem().name()).append(";")
                .append(p.getWeight()).append(";")
                .append(p.getColor().name());

        if (p instanceof SmartPhone && !(p instanceof FoldablePhone)) {
            SmartPhone s = (SmartPhone) p;
            sb.append(";").append(s.getCameraMegapixels()).append(";").append(s.isHasNFC());
        } else if (p instanceof KeypadPhone) {
            KeypadPhone k = (KeypadPhone) p;
            sb.append(";").append(k.isHasDualSim()).append(";").append(k.isHasFlashlight());
        } else if (p instanceof SatellitePhone) {
            SatellitePhone s = (SatellitePhone) p;
            sb.append(";").append(s.getSatelliteNetwork()).append(";").append(s.getAntennaLength());
        } else if (p instanceof FoldablePhone) {
            FoldablePhone f = (FoldablePhone) p;
            sb.append(";").append(f.getCameraMegapixels()).append(";").append(f.isHasNFC())
                    .append(";").append(f.getSecondaryScreenSize()).append(";").append(f.getFoldMechanismType());
        }
        return sb.toString();
    }

    private static Phone deserializeFromText(String line) {
        String[] parts = line.split(";");
        if (parts.length < 10)
            return null;

        String type = parts[0];
        String brand = parts[1];
        String model = parts[2];
        double price = Double.parseDouble(parts[3]);
        int year = Integer.parseInt(parts[4]);
        int storage = Integer.parseInt(parts[5]);
        int battery = Integer.parseInt(parts[6]);
        OperatingSystem os = OperatingSystem.valueOf(parts[7].trim().toUpperCase());
        double weight = Double.parseDouble(parts[8]);
        Color color = Color.valueOf(parts[9].trim().toUpperCase());

        switch (type) {
            case "SmartPhone":
                return new SmartPhone(brand, model, price, year, storage, battery, os, weight, color,
                        Double.parseDouble(parts[10]), Boolean.parseBoolean(parts[11]));
            case "KeypadPhone":
                return new KeypadPhone(brand, model, price, year, storage, battery, os, weight, color,
                        Boolean.parseBoolean(parts[10]), Boolean.parseBoolean(parts[11]));
            case "SatellitePhone":
                return new SatellitePhone(brand, model, price, year, storage, battery, os, weight, color, parts[10],
                        Double.parseDouble(parts[11]));
            case "FoldablePhone":
                return new FoldablePhone(brand, model, price, year, storage, battery, os, weight, color,
                        Double.parseDouble(parts[10]), Boolean.parseBoolean(parts[11]), Double.parseDouble(parts[12]),
                        parts[13]);
            default:
                return new Phone(brand, model, price, year, storage, battery, os, weight, color);
        }
    }

    private static class PhoneAdapter implements JsonDeserializer<Phone> {
        private final Gson baseGson = new Gson();

        @Override
        public Phone deserialize(JsonElement j, Type t, JsonDeserializationContext c) throws JsonParseException {
            JsonObject jsonObject = j.getAsJsonObject();
            JsonElement typeElement = jsonObject.get("classType");

            if (typeElement == null)
                return baseGson.fromJson(j, Phone.class);

            String type = typeElement.getAsString();
            switch (type) {
                case "SmartPhone":
                    return baseGson.fromJson(j, SmartPhone.class);
                case "KeypadPhone":
                    return baseGson.fromJson(j, KeypadPhone.class);
                case "SatellitePhone":
                    return baseGson.fromJson(j, SatellitePhone.class);
                case "FoldablePhone":
                    return baseGson.fromJson(j, FoldablePhone.class);
                default:
                    return baseGson.fromJson(j, Phone.class);
            }
        }
    }
}