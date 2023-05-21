public class BuyNItemsGetOneFree implements DiscountPolicy {
    private int n;

    // constructor
    public BuyNItemsGetOneFree(int n) {
        this.n = n;
    }

    // computeDiscount method
    @Override
    public double computeDiscount(int count, double itemCost) {
        int getFree = count / n;
        System.out.println(
                "Discount amount by the condition 'buy n [" + n + "] items to get free' is " + getFree * itemCost);
        return getFree * itemCost;
    }
}