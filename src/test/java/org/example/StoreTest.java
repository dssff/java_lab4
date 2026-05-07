package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тести для перевірки логіки магазину та сортування
 */
class StoreTest {
    private Store store;

    @BeforeEach
    void setUp() {
        store = new Store();
    }

    @Test
    void testAddAndQuantityManagement() {
        Phone phone = new SmartPhone("Apple", "iPhone 15", 999.99, 2023, 128, 3349, OperatingSystem.IOS, 171.0,
                Color.BLACK, 48.0, true);

        // Додаємо 2 штуки
        store.addNewPhone(phone, 2);
        assertEquals(1, store.getInventory().size());
        assertEquals(2, store.getInventory().get(0).getQuantity());

        // Додаємо той самий телефон ще 3 штуки
        store.addNewPhone(phone, 3);
        assertEquals(1, store.getInventory().size());
        assertEquals(5, store.getInventory().get(0).getQuantity());
    }

    @Test
    void testSortingEmptyList() {
        ArrayList<Phone> list = new ArrayList<>();
        // Сортування не повинно викликати помилок
        list.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
        assertTrue(list.isEmpty());
    }

    @Test
    void testSortingSingleElement() {
        ArrayList<Phone> list = new ArrayList<>();
        list.add(new SmartPhone("Apple", "iPhone 15", 999.99, 2023, 128, 3349, OperatingSystem.IOS, 171.0, Color.BLACK,
                48.0, true));

        list.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
        assertEquals(1, list.size());
        assertEquals("Apple", list.get(0).getBrand());
    }

    @Test
    void testSortingByPrice() {
        ArrayList<Phone> list = new ArrayList<>();
        list.add(new SmartPhone("Expensive", "Model X", 2000.0, 2024, 256, 5000, OperatingSystem.ANDROID, 200.0,
                Color.BLACK, 108.0, true));
        list.add(new SmartPhone("Cheap", "Model A", 100.0, 2022, 32, 2000, OperatingSystem.ANDROID, 150.0, Color.WHITE,
                12.0, false));
        list.add(new SmartPhone("Medium", "Model M", 500.0, 2023, 128, 4000, OperatingSystem.ANDROID, 180.0,
                Color.SILVER, 50.0, true));

        list.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));

        assertEquals("Cheap", list.get(0).getBrand());
        assertEquals("Medium", list.get(1).getBrand());
        assertEquals("Expensive", list.get(2).getBrand());
    }

    @Test
    void testSortingByYear() {
        ArrayList<Phone> list = new ArrayList<>();
        list.add(new SmartPhone("Old", "Model 1", 300.0, 2010, 8, 1500, OperatingSystem.OTHER, 120.0, Color.BLACK, 5.0,
                false));
        list.add(new SmartPhone("New", "Model 3", 1000.0, 2025, 512, 5000, OperatingSystem.ANDROID, 210.0, Color.GOLD,
                200.0, true));
        list.add(new SmartPhone("Classic", "Model 2", 500.0, 2020, 64, 3000, OperatingSystem.IOS, 180.0, Color.SILVER,
                12.0, true));
        list.sort((p1, p2) -> Integer.compare(p2.getYear(), p1.getYear()));

        assertEquals("New", list.get(0).getBrand());
        assertEquals("Classic", list.get(1).getBrand());
        assertEquals("Old", list.get(2).getBrand());
    }

    @Test
    void testSortingWithSameValues() {
        ArrayList<Phone> list = new ArrayList<>();
        // Однакові бренди, але різні моделі
        list.add(new SmartPhone("Samsung", "Galaxy S23", 800.0, 2023, 128, 3900, OperatingSystem.ANDROID, 168.0,
                Color.BLACK, 50.0, true));
        list.add(new SmartPhone("Samsung", "Galaxy S21", 500.0, 2021, 128, 4000, OperatingSystem.ANDROID, 171.0,
                Color.SILVER, 12.0, true));
        list.add(new SmartPhone("Apple", "iPhone 15", 999.0, 2023, 128, 3349, OperatingSystem.IOS, 171.0, Color.BLACK,
                48.0, true));

        Comparator<Phone> nameComparator = (p1, p2) -> {
            int res = p1.getBrand().compareToIgnoreCase(p2.getBrand());
            if (res != 0)
                return res;
            return p1.getModel().compareToIgnoreCase(p2.getModel());
        };

        list.sort(nameComparator);

        assertEquals("Apple", list.get(0).getBrand());
        assertEquals("Samsung", list.get(1).getBrand());
        assertEquals("Galaxy S21", list.get(1).getModel()); // S21 перед S23
        assertEquals("Samsung", list.get(2).getBrand());
        assertEquals("Galaxy S23", list.get(2).getModel());
    }
}
