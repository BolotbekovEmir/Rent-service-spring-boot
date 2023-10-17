package kg.mega.rentserviceproject.enums;

public enum DiscountValue {
    NO_DISCOUNT_ID(1L),
    LONG_TERM_LEASES_DISCOUNT_ID(2L),
    DAYS_FOR_DISCOUNT(7L);

    private final long discountValue;

    DiscountValue(long discountValue) {
        this.discountValue = discountValue;
    }

    public long getDiscountValue() {
        return discountValue;
    }
}
