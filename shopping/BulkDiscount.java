public class BulkDiscount extends DiscountPolicy {
    private int minimum;
    private double percent;

    // constructors
    public BulkDiscount(int minimum, double percent) {
        this.minimum = minimum;
        this.percent = percent;
    }

    // get methods added to access some properties in the test class
    public int getMinimum() {
        return minimum;
    }

    public double getPercent() {
        return percent;
    }

    // computeDiscount method
    @Override
    public double computeDiscount(int count, double itemCost) {
        if (count > minimum) {
            System.out.println("The item count [" + count + "] is more than minimum [" + minimum + "]. Discount ["
                    + percent / 100 * itemCost + "] for item cost [" + itemCost + "] has been determined.");
            return percent / 100 * itemCost; // computes discount percent by item cost
        } else {
            System.out.println("The quantity purchased of items [" + count + "] is not more than minimum [" + minimum
                    + "]. No discount applied.");
            return 0; // no discount
        }
    }
}
