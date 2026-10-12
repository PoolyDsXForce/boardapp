package com.vincente.cardvault.ui;

import com.vincente.cardvault.model.Card;
import com.vincente.cardvault.model.CardCollection;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.util.UUID;

/** Displays and validates the fields used to create or edit a collection card. */
public final class CardDetailsWindow {
    private final CardCollection collection;
    private final Card existingCard;
    private final Runnable onSave;
    private final TextField playerField = new TextField();
    private final TextField sportField = new TextField();
    private final TextField yearField = new TextField();
    private final TextField setField = new TextField();
    private final TextField numberField = new TextField();
    private final TextField conditionField = new TextField();
    private final TextField valueField = new TextField();
    private final TextField notesField = new TextField();
    private final Label feedback = new Label();

    /** Creates a card form, optionally prefilled for editing. */
    public CardDetailsWindow(CardCollection collection, Card existingCard, Runnable onSave) {
        this.collection = collection;
        this.existingCard = existingCard;
        this.onSave = onSave;
        if (existingCard != null) {
            populateFields(existingCard);
        }
    }

    /** Opens the card form as a modal window owned by the collection window. */
    public void show(Stage owner) {
        Stage stage = new Stage();
        stage.initOwner(owner);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle(existingCard == null ? "Add Card" : "Card Details");
        stage.setMinWidth(420);

        GridPane fields = createFields();
        Button saveButton = new Button(existingCard == null ? "Add to Collection" : "Save Changes");
        Button cancelButton = new Button("Cancel");
        saveButton.setOnAction(event -> save(stage));
        cancelButton.setOnAction(event -> stage.close());
        HBox actions = new HBox(8, saveButton, cancelButton);
        VBox content = new VBox(12, fields, feedback, actions);
        content.setPadding(new Insets(16));
        stage.setScene(new Scene(content, 480, 460));
        stage.show();
    }

    private GridPane createFields() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(9);
        addRow(grid, 0, "Player", playerField);
        addRow(grid, 1, "Sport", sportField);
        addRow(grid, 2, "Year", yearField);
        addRow(grid, 3, "Set / Brand", setField);
        addRow(grid, 4, "Card number", numberField);
        addRow(grid, 5, "Condition", conditionField);
        addRow(grid, 6, "Estimated value ($)", valueField);
        addRow(grid, 7, "Notes", notesField);
        GridPane.setHgrow(playerField, Priority.ALWAYS);
        for (TextField field : new TextField[]{playerField, sportField, yearField, setField,
                numberField, conditionField, valueField, notesField}) {
            field.setMaxWidth(Double.MAX_VALUE);
        }
        return grid;
    }

    private void addRow(GridPane grid, int row, String label, TextField field) {
        grid.add(new Label(label), 0, row);
        grid.add(field, 1, row);
    }

    private void populateFields(Card card) {
        playerField.setText(card.getPlayer());
        sportField.setText(card.getSport());
        yearField.setText(Integer.toString(card.getYear()));
        setField.setText(card.getSetName());
        numberField.setText(card.getCardNumber());
        conditionField.setText(card.getCondition());
        valueField.setText(card.getEstimatedValue().toPlainString());
        notesField.setText(card.getNotes());
    }

    private void save(Stage stage) {
        try {
            UUID id = existingCard == null ? UUID.randomUUID() : existingCard.getId();
            Card card = new Card(id, playerField.getText(), sportField.getText(),
                    Integer.parseInt(yearField.getText().trim()), setField.getText(),
                    numberField.getText(), conditionField.getText(), notesField.getText(),
                    new BigDecimal(valueField.getText().trim()));
            if (existingCard == null) {
                collection.add(card);
            } else {
                collection.update(existingCard.getId(), card);
            }
            onSave.run();
            stage.close();
        } catch (NumberFormatException exception) {
            feedback.setText("Year must be a whole number and estimated value must be numeric.");
        } catch (IllegalArgumentException exception) {
            feedback.setText(exception.getMessage() == null
                    ? "Enter a valid year and estimated value." : exception.getMessage());
        }
    }
}
