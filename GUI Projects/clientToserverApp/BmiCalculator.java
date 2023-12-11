package clientToserverApp;

public class BmiCalculator {
    public static double calculate(double height, double weight, boolean isMeters) {
        double bmi;
        if (!isMeters) {
            double heightToMeters = height / 100;
            bmi = weight / Math.pow(heightToMeters, 2);
        } else {
            bmi = weight / Math.pow(height, 2);
        }
        return bmi;
    }

    public static String BMItoCategory(double bmi) {
        String category = "";
        if (bmi < 18.5) {
            category = "underweight";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            category = "healthy";
        } else if (bmi >= 25 && bmi <= 29.9) {
            category = "overweight";
        } else if (bmi >= 30) {
            category = "obese";
        }
        return category;
    }
}