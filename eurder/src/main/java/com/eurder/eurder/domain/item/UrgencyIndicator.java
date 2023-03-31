package com.eurder.eurder.domain.item;

public enum UrgencyIndicator {
    STOCK_LOW(1),
    STOCK_MEDIUM(2),
    STOCK_HIGH(3);

    private final Integer urgencyLevel;

    UrgencyIndicator(Integer urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }

    public Integer getUrgencyLevel() {
        return urgencyLevel;
    }
}
