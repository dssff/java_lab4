package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Автотести для перевірки валідації класу Phone.
 */
class PhoneTest {

    @Test
    void shouldCreatePhoneWithValidData() {

        Phone phone = new SmartPhone("Apple", "iPhone 17", 799.99, 2025, 256, 3200, OperatingSystem.IOS, 170.5,
                Color.BLACK, 48.0, true);

        assertEquals("Apple", phone.getBrand());
        assertEquals(799.99, phone.getPrice());
        assertEquals(OperatingSystem.IOS, phone.getOperatingSystem());
        assertEquals(Color.BLACK, phone.getColor());
    }

    @Test
    void shouldThrowExceptionWhenEmptyBrandInConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SmartPhone("", "iPhone 17", 799.99, 2025, 256, 3200, OperatingSystem.IOS, 170.5, Color.BLACK, 48.0,
                    true);
        });
    }

    @Test
    void shouldThrowExceptionWhenNegativePriceInConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SmartPhone("Apple", "iPhone 17", -1.0, 2025, 256, 3200, OperatingSystem.IOS, 170.5, Color.BLACK, 48.0,
                    true);
        });
    }

    @Test
    void shouldCorrectlyCopyPhoneUsingCopyConstructor() {
        Phone original = new SmartPhone("Apple", "iPhone 17", 799.99, 2025, 256, 3200, OperatingSystem.IOS, 170.5,
                Color.BLACK, 48.0, true);
        SmartPhone copy = new SmartPhone(original.getBrand(), original.getModel(), original.getPrice(),
                original.getYear(), original.getStorage(), original.getBatteryCapacity(),
                original.getOperatingSystem(), original.getWeight(), original.getColor(), 48.0, true);

        assertEquals(original.getBrand(), copy.getBrand());
        assertEquals(original.getModel(), copy.getModel());
    }

    @Test
    void shouldCreateSmartPhoneWithSpecificData() {
        SmartPhone sp = new SmartPhone("Apple", "iPhone 15", 999.99, 2023, 128, 3349, OperatingSystem.IOS, 171.0,
                Color.BLACK, 48.0, true);
        assertEquals(48.0, sp.getCameraMegapixels());
        assertTrue(sp.isHasNFC());
        assertEquals("Apple", sp.getBrand());
    }

    @Test
    void shouldCreateKeypadPhoneWithSpecificData() {
        KeypadPhone kp = new KeypadPhone("Samsung", "B310E", 30.0, 2014, 1, 800, OperatingSystem.OTHER, 75.0,
                Color.WHITE, true, true);
        assertTrue(kp.isHasDualSim());
        assertTrue(kp.isHasFlashlight());
        assertEquals("Samsung", kp.getBrand());
    }

    @Test
    void testPolymorphismInList() {
        java.util.ArrayList<Phone> list = new java.util.ArrayList<>();
        list.add(new SmartPhone("Nokia", "XR21", 499.0, 2023, 128, 4800, OperatingSystem.ANDROID, 231.0, Color.GREEN,
                64.0, true));
        list.add(new SmartPhone("Apple", "iPhone 15", 999.99, 2023, 128, 3349, OperatingSystem.IOS, 171.0, Color.BLACK,
                48.0, true));
        list.add(new KeypadPhone("Samsung", "B310E", 30.0, 2014, 1, 800, OperatingSystem.OTHER, 75.0, Color.WHITE, true,
                true));
        list.add(new SatellitePhone("Iridium", "9555", 1200.0, 2021, 1, 2200, OperatingSystem.OTHER, 266.0, Color.BLACK,
                "Iridium", 12.0));
        list.add(new FoldablePhone("Samsung", "Fold 5", 1800.0, 2023, 512, 4400, OperatingSystem.ANDROID, 253.0,
                Color.BLACK, 50.0, true, 6.2, "Flex"));

        assertEquals(5, list.size());
        assertTrue(list.get(0) instanceof Phone);
        assertTrue(list.get(1) instanceof SmartPhone);
        assertTrue(list.get(2) instanceof KeypadPhone);
        assertTrue(list.get(3) instanceof SatellitePhone);
        assertTrue(list.get(4) instanceof FoldablePhone);
    }

    @Test
    void testSortingLogic() {
        java.util.ArrayList<Phone> list = new java.util.ArrayList<>();
        list.add(new SmartPhone("Samsung", "Galaxy S23", 800, 2023, 256, 3900, OperatingSystem.ANDROID, 168,
                Color.BLACK, 50, true));
        list.add(new SmartPhone("Apple", "iPhone 15", 900, 2023, 128, 3300, OperatingSystem.IOS, 171, Color.BLUE, 48,
                true));
        list.add(new SmartPhone("Apple", "iPhone 14", 700, 2022, 128, 3200, OperatingSystem.IOS, 172, Color.SILVER, 12,
                true));

        list.sort(null);

        // Очікуваний порядок: Apple iPhone 14, Apple iPhone 15, Samsung Galaxy S23
        assertEquals("Apple", list.get(0).getBrand());
        assertEquals("iPhone 14", list.get(0).getModel());

        assertEquals("Apple", list.get(1).getBrand());
        assertEquals("iPhone 15", list.get(1).getModel());

        assertEquals("Samsung", list.get(2).getBrand());
        assertEquals("Galaxy S23", list.get(2).getModel());
    }

    @Test
    void shouldCreateSatellitePhoneWithSpecificData() {
        SatellitePhone sp = new SatellitePhone("Iridium", "Extreme", 1500.0, 2022, 1, 2500, OperatingSystem.OTHER,
                247.0, Color.BLACK, "Iridium Network", 15.5);
        assertEquals("Iridium Network", sp.getSatelliteNetwork());
        assertEquals(15.5, sp.getAntennaLength());
    }

    @Test
    void shouldCreateFoldablePhoneWithSpecificData() {
        FoldablePhone fp = new FoldablePhone("Samsung", "Galaxy Z Fold 5", 1800.0, 2023, 512, 4400,
                OperatingSystem.ANDROID, 253.0, Color.BLUE, 50.0, true, 6.2, "Flex Hinge");
        assertEquals(6.2, fp.getSecondaryScreenSize());
        assertEquals("Flex Hinge", fp.getFoldMechanismType());
    }

    @Test
    void shouldThrowExceptionWhenNegativeAntennaLength() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SatellitePhone("Iridium", "9555", 1200.0, 2021, 1, 2200, OperatingSystem.OTHER, 266.0, Color.BLACK,
                    "Iridium", -5.0);
        });
    }
}
