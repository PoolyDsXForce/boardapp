package com.vincente.cardvault.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/** Maintains the current session's ordered collection of unique cards. */
public final class CardCollection {
    private final List<Card> cards = new ArrayList<>();

    /** Returns an immutable snapshot of the current cards in collection order. */
    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    /** Adds a card unless its identity is already present. */
    public void add(Card card) {
        Objects.requireNonNull(card, "Card cannot be null");
        if (findById(card.getId()) != null) {
            throw new IllegalArgumentException("Card is already in the collection");
        }
        cards.add(card);
    }

    /** Replaces the card with the given identity without changing its collection position. */
    public void update(UUID cardId, Card updatedCard) {
        Objects.requireNonNull(cardId, "Card ID cannot be null");
        Objects.requireNonNull(updatedCard, "Updated card cannot be null");
        if (!cardId.equals(updatedCard.getId())) {
            throw new IllegalArgumentException("Updated card ID must match the selected card");
        }
        int index = indexOf(cardId);
        if (index < 0) {
            throw new IllegalArgumentException("Card was not found in the collection");
        }
        cards.set(index, updatedCard);
    }

    /** Removes the card with the given identity. */
    public void remove(UUID cardId) {
        Objects.requireNonNull(cardId, "Card ID cannot be null");
        int index = indexOf(cardId);
        if (index < 0) {
            throw new IllegalArgumentException("Card was not found in the collection");
        }
        cards.remove(index);
    }

    private Card findById(UUID cardId) {
        return cards.stream().filter(card -> card.getId().equals(cardId)).findFirst().orElse(null);
    }

    private int indexOf(UUID cardId) {
        for (int index = 0; index < cards.size(); index++) {
            if (cards.get(index).getId().equals(cardId)) {
                return index;
            }
        }
        return -1;
    }
}
