package com.eurder.eurder.domain.item;

public enum UrgencyIndicator {
    STOCK_LOW(1),
    STOCK_MEDIUM(5),
    STOCK_HIGH(10);

    private final Integer urgencyLevel;

    UrgencyIndicator(Integer urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }

    public Integer getUrgencyLevel() {
        return urgencyLevel;
    }
}
