package org.example;

import java.util.UUID;

/**
 * Інтерфейс для об'єктів, що мають унікальний ідентифікатор UUID.
 */
public interface Identifiable {
    UUID getUuid();
}
