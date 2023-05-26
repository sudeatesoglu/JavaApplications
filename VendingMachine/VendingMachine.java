public class VendingMachine {
    public static void main(String[] args) {

        try {
            evaluateMoney("coin");
        } catch (NotBanknoteException | CounterfeitMoneyException e) {
            e.printStackTrace();
        }
        try {
            evaluateMoney("counterfeit");
        } catch (CounterfeitMoneyException | NotBanknoteException e) {
            e.printStackTrace();
        }
        try {
            evaluateMoney("banknote");
        } catch (NotBanknoteException | CounterfeitMoneyException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Machine has been closed. Come again!");
        }

    }

    private static void evaluateMoney(String money) throws NotBanknoteException, CounterfeitMoneyException {

        if (money == "coin") {
            throw new NotBanknoteException("Invalid Currency Type"); // invalid currency type
        } else if (money == "counterfeit") {
            throw new CounterfeitMoneyException("Counterfeit Money!");
        } else {
            System.out.println("The money '" + money + "' has been accepted.");
        }

    }
}