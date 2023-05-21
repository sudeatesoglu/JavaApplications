public interface DiscountPolicy {
    public abstract double computeDiscount(int count, double itemCost);
}