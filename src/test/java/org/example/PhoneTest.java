package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Автотести для перевірки валідації класу Phone.
 */
class PhoneTest {

    @Test
    void shouldCreatePhoneWithValidData() {
        Processor processor = new Processor("A19", 8, 3.8);
        Phone phone = new Phone("Apple", "iPhone 17", 799.99, 2025, 256, 3200, OperatingSystem.IOS, 170.5, Color.BLACK, processor);
        
        assertEquals("Apple", phone.getBrand());
        assertEquals(799.99, phone.getPrice());
        assertEquals(OperatingSystem.IOS, phone.getOperatingSystem());
        assertEquals(Color.BLACK, phone.getColor());
        assertEquals(processor, phone.getProcessor());
    }

    @Test
    void shouldThrowExceptionWhenEmptyBrandInConstructor() {
        Processor processor = new Processor("A19", 8, 3.8);
        assertThrows(IllegalArgumentException.class, () -> {
            new Phone("", "iPhone 17", 799.99, 2025, 256, 3200, OperatingSystem.IOS, 170.5, Color.BLACK, processor);
        });
    }

    @Test
    void shouldThrowExceptionWhenNegativePriceInConstructor() {
        Processor processor = new Processor("A19", 8, 3.8);
        assertThrows(IllegalArgumentException.class, () -> {
            new Phone("Apple", "iPhone 17", -1.0, 2025, 256, 3200, OperatingSystem.IOS, 170.5, Color.BLACK, processor);
        });
    }

    @Test
    void shouldCorrectlyCopyPhoneUsingCopyConstructor() {
        Processor processor = new Processor("A19", 8, 3.8);
        Phone original = new Phone("Apple", "iPhone 17", 799.99, 2025, 256, 3200, OperatingSystem.IOS, 170.5, Color.BLACK, processor);
        Phone copy = new Phone(original);

        assertEquals(original, copy);
        assertNotSame(original, copy);
        assertNotSame(original.getProcessor(), copy.getProcessor()); // Deep copy of processor
        assertEquals(original.getProcessor().getModel(), copy.getProcessor().getModel());
    }


    @Test
    void shouldThrowExceptionWhenProcessorIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Phone("Apple", "iPhone 17", 799.99, 2025, 256, 3200, OperatingSystem.IOS, 170.5, Color.BLACK, null);
        });
    }
}
