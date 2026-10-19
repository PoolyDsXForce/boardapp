package com.vincente.cardvault.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/** An immutable sports-card record with validated collection details. */
public final class Card {
    private final UUID id;
    private final String player;
    private final String sport;
    private final int year;
    private final String setName;
    private final String cardNumber;
    private final String condition;
    private final String notes;
    private final BigDecimal estimatedValue;

    /**
     * Creates a card with a new identity.
     *
     * @param player featured player or subject
     * @param sport sport associated with the card
     * @param year release year
     * @param setName card set or brand
     * @param cardNumber printed card number
     * @param condition collector's condition description
     * @param notes optional collector notes
     * @param estimatedValue non-negative estimated value
     */
    public Card(String player, String sport, int year, String setName, String cardNumber,
                String condition, String notes, BigDecimal estimatedValue) {
        this(UUID.randomUUID(), player, sport, year, setName, cardNumber, condition, notes,
                estimatedValue);
    }

    /**
     * Creates a card with a stable identity, used when editing an existing collection entry.
     *
     * @param id stable card identity
     * @param player featured player or subject
     * @param sport sport associated with the card
     * @param year release year
     * @param setName card set or brand
     * @param cardNumber printed card number
     * @param condition collector's condition description
     * @param notes optional collector notes
     * @param estimatedValue non-negative estimated value
     */
    public Card(UUID id, String player, String sport, int year, String setName, String cardNumber,
                String condition, String notes, BigDecimal estimatedValue) {
        this.id = Objects.requireNonNull(id, "Card ID cannot be null");
        this.player = requireText(player, "Player");
        this.sport = requireText(sport, "Sport");
        if (year <= 0) {
            throw new IllegalArgumentException("Year must be greater than zero");
        }
        this.year = year;
        this.setName = requireText(setName, "Set name");
        this.cardNumber = requireText(cardNumber, "Card number");
        this.condition = requireText(condition, "Condition");
        this.notes = notes == null ? "" : notes.trim();
        this.estimatedValue = Objects.requireNonNull(estimatedValue, "Estimated value cannot be null");
        if (estimatedValue.signum() < 0) {
            throw new IllegalArgumentException("Estimated value cannot be negative");
        }
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
        return value.trim();
    }

    public UUID getId() {
        return id;
    }

    public String getPlayer() {
        return player;
    }

    public String getSport() {
        return sport;
    }

    public int getYear() {
        return year;
    }

    public String getSetName() {
        return setName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCondition() {
        return condition;
    }

    public String getNotes() {
        return notes;
    }

    public BigDecimal getEstimatedValue() {
        return estimatedValue;
    }
}
