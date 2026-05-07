package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

/**
 * Клас для управління підключенням до бази даних та збереженням об'єктів.
 */
public class DatabaseManager {
    private String url;
    private String user;
    private String password;

    public DatabaseManager(String configFilePath) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            props.load(fis);
            this.url = props.getProperty("db.url");
            this.user = props.getProperty("db.user");
            this.password = props.getProperty("db.password");
            
            if (this.url == null || this.user == null || this.password == null) {
                System.out.println("Помилка: неповні параметри підключення в конфігураційному файлі БД.");
            }
        } catch (IOException e) {
            System.out.println("Помилка читання конфігураційного файлу бази даних: " + e.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void insertPhone(Phone phone) {
        String sql = "INSERT INTO phones (type, brand, model, price, year, storage, battery_capacity, " +
                     "operating_system, weight, color, camera_megapixels, has_nfc, satellite_network, " +
                     "antenna_length, has_dual_sim, has_flashlight, secondary_screen_size, fold_mechanism_type) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                     
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            // Базові поля
            pstmt.setString(1, phone.getClassType());
            pstmt.setString(2, phone.getBrand());
            pstmt.setString(3, phone.getModel());
            pstmt.setDouble(4, phone.getPrice());
            pstmt.setInt(5, phone.getYear());
            pstmt.setInt(6, phone.getStorage());
            pstmt.setInt(7, phone.getBatteryCapacity());
            pstmt.setString(8, phone.getOperatingSystem() != null ? phone.getOperatingSystem().name() : null);
            pstmt.setDouble(9, phone.getWeight());
            pstmt.setString(10, phone.getColor() != null ? phone.getColor().name() : null);
            
            // Встановлюємо null для специфічних полів підкласів за замовчуванням
            pstmt.setNull(11, Types.DOUBLE);
            pstmt.setNull(12, Types.BOOLEAN);
            pstmt.setNull(13, Types.VARCHAR);
            pstmt.setNull(14, Types.DOUBLE);
            pstmt.setNull(15, Types.BOOLEAN);
            pstmt.setNull(16, Types.BOOLEAN);
            pstmt.setNull(17, Types.DOUBLE);
            pstmt.setNull(18, Types.VARCHAR);
            
            // Перевизначаємо поля для конкретного підкласу
            if (phone instanceof FoldablePhone) {
                FoldablePhone fp = (FoldablePhone) phone;
                pstmt.setDouble(11, fp.getCameraMegapixels());
                pstmt.setBoolean(12, fp.isHasNFC());
                pstmt.setDouble(17, fp.getSecondaryScreenSize());
                pstmt.setString(18, fp.getFoldMechanismType());
            } else if (phone instanceof SmartPhone) {
                SmartPhone sp = (SmartPhone) phone;
                pstmt.setDouble(11, sp.getCameraMegapixels());
                pstmt.setBoolean(12, sp.isHasNFC());
            } else if (phone instanceof SatellitePhone) {
                SatellitePhone sp = (SatellitePhone) phone;
                pstmt.setString(13, sp.getSatelliteNetwork());
                pstmt.setDouble(14, sp.getAntennaLength());
            } else if (phone instanceof KeypadPhone) {
                KeypadPhone kp = (KeypadPhone) phone;
                pstmt.setBoolean(15, kp.isHasDualSim());
                pstmt.setBoolean(16, kp.isHasFlashlight());
            }
            
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Об'єкт успішно збережено в базі даних.");
            }
        } catch (SQLException e) {
            System.out.println("Помилка виконання SQL-запиту: " + e.getMessage());
        }
    }
}
