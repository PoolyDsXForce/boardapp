package com.vincente.cardvault.model;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CardCollectionTest {
    @Test
    public void startsEmptyAndExposesImmutableSnapshot() {
        CardCollection collection = new CardCollection();

        assertEquals(List.of(), collection.getCards());
        assertThrows(UnsupportedOperationException.class,
                () -> collection.getCards().add(card()));
    }

    @Test
    public void addsCardsInOrder() {
        CardCollection collection = new CardCollection();
        Card first = card();
        Card second = card();

        collection.add(first);
        collection.add(second);

        assertEquals(List.of(first, second), collection.getCards());
    }

    @Test
    public void rejectsNullAndDuplicateCards() {
        CardCollection collection = new CardCollection();
        Card card = card();
        collection.add(card);

        assertThrows(NullPointerException.class, () -> collection.add(null));
        Card duplicateId = new Card(card.getId(), "Alex Morgan", "Soccer", 2024,
                "National", "15", "Near Mint", "", BigDecimal.ONE);
        assertThrows(IllegalArgumentException.class, () -> collection.add(duplicateId));
    }

    @Test
    public void updatesCardInPlaceWithoutChangingOrder() {
        CardCollection collection = new CardCollection();
        Card first = card();
        Card second = card();
        collection.add(first);
        collection.add(second);
        Card updated = new Card(first.getId(), "Alex Morgan", "Soccer", 2024,
                "National", "15", "Mint", "Graded", new BigDecimal("20.00"));

        collection.update(first.getId(), updated);

        assertEquals(List.of(updated, second), collection.getCards());
    }

    @Test
    public void rejectsInvalidUpdateRequests() {
        CardCollection collection = new CardCollection();
        Card existing = card();
        collection.add(existing);
        UUID unknownId = UUID.randomUUID();

        assertThrows(NullPointerException.class, () -> collection.update(null, existing));
        assertThrows(NullPointerException.class, () -> collection.update(existing.getId(), null));
        assertThrows(IllegalArgumentException.class,
                () -> collection.update(unknownId, cardWithId(unknownId)));
        assertThrows(IllegalArgumentException.class,
                () -> collection.update(existing.getId(), card()));
    }

    @Test
    public void removesCardAndRejectsUnknownId() {
        CardCollection collection = new CardCollection();
        Card existing = card();
        collection.add(existing);

        collection.remove(existing.getId());

        assertEquals(List.of(), collection.getCards());
        assertThrows(NullPointerException.class, () -> collection.remove(null));
        assertThrows(IllegalArgumentException.class, () -> collection.remove(UUID.randomUUID()));
    }

    private Card card() {
        return new Card("Alex Morgan", "Soccer", 2024, "National",
                UUID.randomUUID().toString(), "Near Mint", "", BigDecimal.ONE);
    }

    private Card cardWithId(UUID id) {
        return new Card(id, "Alex Morgan", "Soccer", 2024, "National",
                "15", "Near Mint", "", BigDecimal.ONE);
    }
}
