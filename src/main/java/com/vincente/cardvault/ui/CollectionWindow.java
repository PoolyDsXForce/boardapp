package com.vincente.cardvault.ui;

import com.vincente.cardvault.model.Card;
import com.vincente.cardvault.model.CardCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/** Displays the collection and actions for creating, editing, and deleting cards. */
public final class CollectionWindow {
    private final CardCollection collection;
    private final ObservableList<Card> cards;
    private final ListView<Card> cardList;
    private final Button detailsButton;
    private final Button deleteButton;

    /** Creates the window controller for the supplied in-memory collection. */
    public CollectionWindow(CardCollection collection) {
        this.collection = collection;
        cards = FXCollections.observableArrayList(collection.getCards());
        cardList = new ListView<>(cards);
        cardList.setMinHeight(0);
        detailsButton = new Button("View / Edit");
        deleteButton = new Button("Delete");
        configureCardList();
    }

    /** Displays the collection window on the supplied primary stage. */
    public void show(Stage stage) {
        stage.setTitle("CardVault - My Collection");
        stage.setMinWidth(520);
        stage.setMinHeight(360);

        Label heading = new Label("My Collection");
        heading.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Label emptyState = new Label("No cards yet. Add your first card to get started.");
        cardList.setPlaceholder(emptyState);

        Button addButton = new Button("Add Card");
        addButton.setOnAction(event -> openCardDetails(stage, null));
        detailsButton.disableProperty().bind(cardList.getSelectionModel().selectedItemProperty().isNull());
        detailsButton.setOnAction(event ->
                openCardDetails(stage, cardList.getSelectionModel().getSelectedItem()));
        deleteButton.disableProperty().bind(cardList.getSelectionModel().selectedItemProperty().isNull());
        deleteButton.setOnAction(event -> deleteSelectedCard(stage));

        HBox actions = new HBox(8, addButton, detailsButton, deleteButton);
        BorderPane content = new BorderPane();
        content.setPadding(new Insets(16));
        content.setTop(heading);
        content.setCenter(cardList);
        content.setBottom(actions);
        BorderPane.setMargin(heading, new Insets(0, 0, 12, 0));
        BorderPane.setMargin(cardList, new Insets(0, 0, 12, 0));
        stage.setScene(new Scene(content, 640, 440));
        stage.show();
    }

    private void configureCardList() {
        cardList.setCellFactory(list -> new ListCell<>() {
            @Override
            protected void updateItem(Card card, boolean empty) {
                super.updateItem(card, empty);
                if (empty || card == null) {
                    setText(null);
                } else {
                    setText("%s - %d %s #%s ($%s)".formatted(
                            card.getPlayer(), card.getYear(), card.getSetName(),
                            card.getCardNumber(), card.getEstimatedValue().toPlainString()));
                }
            }
        });
    }

    private void openCardDetails(Stage owner, Card selectedCard) {
        new CardDetailsWindow(collection, selectedCard, this::refreshCards).show(owner);
    }

    private void deleteSelectedCard(Stage owner) {
        Card selectedCard = cardList.getSelectionModel().getSelectedItem();
        if (selectedCard == null) {
            return;
        }
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,
                "Remove %s from your collection?".formatted(selectedCard.getPlayer()),
                ButtonType.CANCEL, ButtonType.OK);
        confirmation.initOwner(owner);
        confirmation.setTitle("Delete Card");
        confirmation.setHeaderText("Confirm card removal");
        if (confirmation.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            collection.remove(selectedCard.getId());
            refreshCards();
        }
    }

    private void refreshCards() {
        cards.setAll(collection.getCards());
    }
}
