package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Автотести для перевірки валідації класу Phone.
 */
class PhoneTest {

    @Test
    void shouldCreatePhoneWithValidData() {
        Phone phone = new Phone("Apple", "iPhone 17", 799.99, 2025, 256, 3200, OperatingSystem.IOS, 170.5, Color.BLACK);
        
        assertEquals("Apple", phone.getBrand());
        assertEquals(799.99, phone.getPrice());
        assertEquals(OperatingSystem.IOS, phone.getOperatingSystem());
        assertEquals(Color.BLACK, phone.getColor());
    }

    @Test
    void shouldThrowExceptionWhenEmptyBrandInConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Phone("", "iPhone 17", 799.99, 2025, 256, 3200, OperatingSystem.IOS, 170.5, Color.BLACK);
        });
    }

    @Test
    void shouldThrowExceptionWhenNegativePriceInConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Phone("Apple", "iPhone 17", -1.0, 2025, 256, 3200, OperatingSystem.IOS, 170.5, Color.BLACK);
        });
    }

    @Test
    void shouldCorrectlyCopyPhoneUsingCopyConstructor() {
        Phone original = new Phone("Apple", "iPhone 17", 799.99, 2025, 256, 3200, OperatingSystem.IOS, 170.5, Color.BLACK);
        Phone copy = new Phone(original);
 
        assertEquals(original, copy);
        assertNotSame(original, copy);
    }


    @Test
    void shouldCreateSmartPhoneWithSpecificData() {
        SmartPhone sp = new SmartPhone("Apple", "iPhone 15", 999.99, 2023, 128, 3349, OperatingSystem.IOS, 171.0, Color.BLACK, 48.0, true);
        assertEquals(48.0, sp.getCameraMegapixels());
        assertTrue(sp.isHasNFC());
        assertEquals("Apple", sp.getBrand());
    }

    @Test
    void shouldCreateKeypadPhoneWithSpecificData() {
        KeypadPhone kp = new KeypadPhone("Samsung", "B310E", 30.0, 2014, 1, 800, OperatingSystem.OTHER, 75.0, Color.WHITE, true, true);
        assertTrue(kp.isHasDualSim());
        assertTrue(kp.isHasFlashlight());
        assertEquals("Samsung", kp.getBrand());
    }

    @Test
    void testPolymorphismInList() {
        java.util.ArrayList<Phone> list = new java.util.ArrayList<>();
        list.add(new Phone("Nokia", "1100", 20.0, 2003, 1, 850, OperatingSystem.OTHER, 93.0, Color.SILVER));
        list.add(new SmartPhone("Apple", "iPhone 15", 999.99, 2023, 128, 3349, OperatingSystem.IOS, 171.0, Color.BLACK, 48.0, true));
        
        assertTrue(list.get(0) instanceof Phone);
        assertTrue(list.get(1) instanceof SmartPhone);
        assertEquals("Nokia", list.get(0).getBrand());
        assertEquals("Apple", list.get(1).getBrand());
    }
}
