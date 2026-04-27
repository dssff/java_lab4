package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Автотести для перевірки валідації класу Phone.
 */
class PhoneTest {

    @Test
    void shouldCreatePhoneWithValidData() {
        Phone phone = new Phone("Apple", "iPhone 17", 799.99, 2025, 256, 3200, "iOS", 170.5, "Black");
        assertEquals("Apple", phone.getBrand());
        assertEquals(799.99, phone.getPrice());
    }

    @Test
    void shouldThrowExceptionWhenEmptyBrandInConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Phone("", "iPhone 17", 799.99, 2025, 256, 3200, "iOS", 170.5, "Black");
        });
    }

    @Test
    void shouldThrowExceptionWhenNegativePriceInConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Phone("Apple", "iPhone 17", -1.0, 2025, 256, 3200, "iOS", 170.5, "Black");
        });
    }

    @Test
    void shouldThrowExceptionWhenInvalidValueInSetter() {
        Phone phone = new Phone("Apple", "iPhone 17", 799.99, 2025, 256, 3200, "iOS", 170.5, "Black");
        assertThrows(IllegalArgumentException.class, () -> {
            phone.setPrice(-100.0);
        });
    }

    @Test
    void shouldThrowExceptionWhenEmptyBrandInSetter() {
        Phone phone = new Phone("Apple", "iPhone 17", 799.99, 2025, 256, 3200, "iOS", 170.5, "Black");
        assertThrows(IllegalArgumentException.class, () -> {
            phone.setBrand("");
        });
    }
}
