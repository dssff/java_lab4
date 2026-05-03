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


}
