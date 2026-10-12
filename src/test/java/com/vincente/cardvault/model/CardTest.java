package com.vincente.cardvault.model;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class CardTest {
    @Test
    public void createsCardWithTrimmedValuesAndGeneratedId() {
        Card card = card("  Alex Morgan  ", " Soccer ", 2024, " National ", " 15 ",
                " Near Mint ", "  Rookie card  ", new BigDecimal("12.50"));

        assertNotNull(card.getId());
        assertEquals("Alex Morgan", card.getPlayer());
        assertEquals("Soccer", card.getSport());
        assertEquals(2024, card.getYear());
        assertEquals("National", card.getSetName());
        assertEquals("15", card.getCardNumber());
        assertEquals("Near Mint", card.getCondition());
        assertEquals("Rookie card", card.getNotes());
        assertEquals(new BigDecimal("12.50"), card.getEstimatedValue());
    }

    @Test
    public void keepsProvidedIdAndAllowsEmptyOptionalNotes() {
        UUID id = UUID.randomUUID();
        Card card = new Card(id, "Alex Morgan", "Soccer", 2024, "National",
                "15", "Near Mint", null, BigDecimal.ZERO);

        assertEquals(id, card.getId());
        assertEquals("", card.getNotes());
        assertEquals(BigDecimal.ZERO, card.getEstimatedValue());
    }

    @Test
    public void rejectsNullId() {
        assertThrows(NullPointerException.class, () ->
                new Card(null, "Alex Morgan", "Soccer", 2024, "National",
                        "15", "Near Mint", "", BigDecimal.ONE));
    }

    @Test
    public void rejectsBlankRequiredText() {
        assertThrows(IllegalArgumentException.class, () ->
                card(" ", "Soccer", 2024, "National", "15", "Near Mint", "", BigDecimal.ONE));
        assertThrows(IllegalArgumentException.class, () ->
                card("Alex Morgan", null, 2024, "National", "15", "Near Mint", "", BigDecimal.ONE));
        assertThrows(IllegalArgumentException.class, () ->
                card("Alex Morgan", "Soccer", 2024, "\t", "15", "Near Mint", "", BigDecimal.ONE));
        assertThrows(IllegalArgumentException.class, () ->
                card("Alex Morgan", "Soccer", 2024, "National", "  ", "Near Mint", "", BigDecimal.ONE));
        assertThrows(IllegalArgumentException.class, () ->
                card("Alex Morgan", "Soccer", 2024, "National", "15", "", "", BigDecimal.ONE));
    }

    @Test
    public void rejectsNonPositiveYear() {
        assertThrows(IllegalArgumentException.class, () ->
                card("Alex Morgan", "Soccer", 0, "National", "15", "Near Mint", "", BigDecimal.ONE));
    }

    @Test
    public void rejectsNullAndNegativeEstimatedValue() {
        assertThrows(NullPointerException.class, () ->
                card("Alex Morgan", "Soccer", 2024, "National", "15", "Near Mint", "", null));
        assertThrows(IllegalArgumentException.class, () ->
                card("Alex Morgan", "Soccer", 2024, "National", "15", "Near Mint", "",
                        new BigDecimal("-0.01")));
    }

    private Card card(String player, String sport, int year, String setName, String cardNumber,
                      String condition, String notes, BigDecimal value) {
        return new Card(player, sport, year, setName, cardNumber, condition, notes, value);
    }
}
