package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {

    private ArrayList<Phone> testPhones;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        testPhones = new ArrayList<>();

        testPhones.add(new SmartPhone("Apple", "iPhone 15", 1000, 2023, 128, 3349,
                OperatingSystem.IOS, 187, Color.BLACK, 48.0, true));

        testPhones.add(new KeypadPhone("Nokia", "3310", 50, 2000, 1, 900,
                OperatingSystem.OTHER, 133, Color.BLUE, false, true));

        testPhones.add(new SatellitePhone("Iridium", "9555", 1500, 2022, 2, 2000,
                OperatingSystem.OTHER, 266, Color.SILVER, "Iridium Network", 15.5));

        testPhones.add(new FoldablePhone("Samsung", "Z Fold 5", 1800, 2023, 512, 4400,
                OperatingSystem.ANDROID, 253, Color.GOLD, 50.0, true, 6.2, "Hinge V2"));
    }

    @Test
    void testJsonPersistence() {
        String filePath = tempDir.resolve("test_phones.json").toString();

        FileHandler.saveToJson(testPhones, filePath);

        ArrayList<Phone> loadedPhones = FileHandler.loadFromJson(filePath);

        assertEquals(testPhones.size(), loadedPhones.size(), "Кількість об'єктів має збігатися");

        assertTrue(loadedPhones.get(0) instanceof SmartPhone, "Перший об'єкт має бути SmartPhone");
        assertEquals(48.0, ((SmartPhone) loadedPhones.get(0)).getCameraMegapixels(), 0.01);

        assertTrue(loadedPhones.get(3) instanceof FoldablePhone, "Останній об'єкт має бути FoldablePhone");
        assertEquals("Hinge V2", ((FoldablePhone) loadedPhones.get(3)).getFoldMechanismType());
    }

    @Test
    void testTextPersistence() {
        String filePath = tempDir.resolve("test_phones.txt").toString();

        FileHandler.saveToText(testPhones, filePath);

        ArrayList<Phone> loadedPhones = FileHandler.loadFromText(filePath);

        assertEquals(testPhones.size(), loadedPhones.size(), "Кількість об'єктів у TXT має збігатися");

        Phone p2 = loadedPhones.get(1);
        assertEquals("Nokia", p2.getBrand());
        assertTrue(p2 instanceof KeypadPhone);
        assertTrue(((KeypadPhone) p2).isHasFlashlight());

        Phone p3 = loadedPhones.get(2);
        assertEquals("Iridium Network", ((SatellitePhone) p3).getSatelliteNetwork());
    }

    @Test
    void testEmptyFiles() {
        String jsonPath = tempDir.resolve("non_existent.json").toString();
        String txtPath = tempDir.resolve("non_existent.txt").toString();

        ArrayList<Phone> jsonList = FileHandler.loadFromJson(jsonPath);
        ArrayList<Phone> txtList = FileHandler.loadFromText(txtPath);

        assertNotNull(jsonList);
        assertTrue(jsonList.isEmpty());
        assertNotNull(txtList);
        assertTrue(txtList.isEmpty());
    }
}
