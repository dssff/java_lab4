package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Головний клас GUI додатка на JavaFX.
 */
public class MainApp extends Application {

    private Store store;
    private ListView<String> phoneListView;
    private ObservableList<String> phoneObservableList;
    private TextArea detailArea;

    @Override
    public void start(Stage primaryStage) {
        store = new Store();
        // Завантаження початкових даних
        ArrayList<Phone> phones = FileHandler.loadFromText("input.txt");
        for (Phone p : phones) {
            store.addNewPhone(p, 1);
        }

        primaryStage.setTitle("Phone Store");

        // --- ЛІВА ПАНЕЛЬ: Форма додавання ---
        VBox addPanel = new VBox(10);
        addPanel.setPadding(new Insets(15));
        addPanel.setMinWidth(300);

        Label headerAdd = new Label("Додати новий телефон");
        headerAdd.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.getItems().addAll("SmartPhone", "KeypadPhone", "SatellitePhone", "FoldablePhone");
        typeBox.setValue("SmartPhone");

        TextField brandField = new TextField();
        brandField.setPromptText("Бренд");

        TextField modelField = new TextField();
        modelField.setPromptText("Модель");

        TextField priceField = new TextField();
        priceField.setPromptText("Ціна");

        Button addButton = new Button("Додати");
        addButton.setMaxWidth(Double.MAX_VALUE);

        addPanel.getChildren().addAll(headerAdd, new Label("Тип:"), typeBox, brandField, modelField, priceField,
                addButton);

        // --- ЦЕНТРАЛЬНА ПАНЕЛЬ: Список ---
        VBox listPanel = new VBox(10);
        listPanel.setPadding(new Insets(15));
        HBox.setHgrow(listPanel, Priority.ALWAYS);

        Label headerList = new Label("Колекція телефонів (UUID)");
        headerList.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        phoneObservableList = FXCollections.observableArrayList();
        phoneListView = new ListView<>(phoneObservableList);
        updateListView();

        listPanel.getChildren().addAll(headerList, phoneListView);

        // --- ПРАВА ПАНЕЛЬ: Пошук та деталі ---
        VBox searchPanel = new VBox(10);
        searchPanel.setPadding(new Insets(15));
        searchPanel.setMinWidth(350);

        Label headerSearch = new Label("Пошук за UUID");
        headerSearch.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        TextField searchField = new TextField();
        searchField.setPromptText("Введіть UUID...");

        Button searchButton = new Button("Знайти");
        searchButton.setMaxWidth(Double.MAX_VALUE);

        detailArea = new TextArea();
        detailArea.setEditable(false);
        detailArea.setWrapText(true);
        detailArea.setPromptText("Інформація про об'єкт...");

        searchPanel.getChildren().addAll(headerSearch, searchField, searchButton, new Label("Детальна інформація:"),
                detailArea);

        // --- ЛОГІКА КНОПОК ---

        addButton.setOnAction(e -> {
            try {
                String type = typeBox.getValue();
                String brand = brandField.getText();
                String model = modelField.getText();
                double price = Double.parseDouble(priceField.getText());

                Phone newPhone;
                // Створюємо об'єкт залежно від типу (з дефолтними значеннями для специфічних
                // полів)
                switch (type) {
                    case "KeypadPhone":
                        newPhone = new KeypadPhone(brand, model, price, 2024, 1, 1000, OperatingSystem.OTHER, 100.0,
                                Color.BLACK, true, true);
                        break;
                    case "SatellitePhone":
                        newPhone = new SatellitePhone(brand, model, price, 2024, 1, 2000, OperatingSystem.OTHER, 200.0,
                                Color.BLACK, "Inmarsat", 10.0);
                        break;
                    case "FoldablePhone":
                        newPhone = new FoldablePhone(brand, model, price, 2024, 256, 4000, OperatingSystem.ANDROID,
                                230.0, Color.BLUE, 50.0, true, 6.0, "Hinge");
                        break;
                    default: // SmartPhone
                        newPhone = new SmartPhone(brand, model, price, 2024, 128, 3500, OperatingSystem.ANDROID, 180.0,
                                Color.SILVER, 50.0, true);
                }

                store.addNewPhone(newPhone, 1);
                updateListView();
                clearFields(brandField, modelField, priceField);

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Об'єкт додано! UUID: " + newPhone.getUuid());
                alert.show();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Помилка введення: " + ex.getMessage());
                alert.show();
            }
        });

        searchButton.setOnAction(e -> {
            String uuidStr = searchField.getText();
            StoreItem result = store.findPhoneByUuid(uuidStr);
            if (result != null) {
                detailArea.setText(result.getPhone().toString());
            } else {
                detailArea.setText("Об'єкт не знайдено.");
                Alert alert = new Alert(Alert.AlertType.WARNING, "Об'єкт не знайдено або формат UUID некоректний.");
                alert.show();
            }
        });

        // --- ГОЛОВНИЙ LAYOUT ---
        HBox mainLayout = new HBox(addPanel, listPanel, searchPanel);
        Scene scene = new Scene(mainLayout, 1000, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateListView() {
        phoneObservableList.clear();
        for (StoreItem item : store.getInventory()) {
            Phone p = item.getPhone();
            phoneObservableList
                    .add(p.getBrand() + " " + p.getModel() + " | ID: " + p.getUuid().toString().substring(0, 8));
        }
    }

    private void clearFields(TextField... fields) {
        for (TextField f : fields)
            f.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
