public class Test {
    // main method
    public static void main(String[] args) {

        // default values to use in each test
        int count = 4;
        double itemCost = 200;

        // bulk discount objects
        BulkDiscount bd1 = new BulkDiscount(5, 25);
        BulkDiscount bd2 = new BulkDiscount(3, 20);

        System.out.println("Bulk Discount");

        BulkDiscount[] bulkDiscounts = { bd1, bd2 };

        // printing bd1 & bd2
        for (int i = 0; i < bulkDiscounts.length; i++) {
            int minimum = bulkDiscounts[i].getMinimum(); // get methods added to access private properties
            double percent = bulkDiscounts[i].getPercent();

            System.out.println("minimum: " + minimum); // printing the bulk discount properties
            System.out.println("percent: " + percent);
        }

        bd1.computeDiscount(count, itemCost);
        bd2.computeDiscount(count, itemCost);

        System.out.println("Buy N Items Get One Free");

        BuyNItemsGetOneFree free1 = new BuyNItemsGetOneFree(3);
        BuyNItemsGetOneFree free2 = new BuyNItemsGetOneFree(5);
        BuyNItemsGetOneFree free3 = new BuyNItemsGetOneFree(2);

        free1.computeDiscount(count, itemCost);
        free2.computeDiscount(count, itemCost);
        free3.computeDiscount(count, itemCost);
    }
}
