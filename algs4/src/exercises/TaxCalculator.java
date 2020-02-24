package exercises;

import edu.princeton.cs.algs4.StdOut;

public class TaxCalculator {
    public static void main(String[] args) {
        double salary = 100000;

        double taxedSalary = getTaxedSalary(salary);
        StdOut.println("taxedSalary: " + taxedSalary);

        double tax = calculateTax(taxedSalary);
        StdOut.println("tax: " + tax);
        double afterTaxSalary = salary - tax - 5000;
        StdOut.println("afterTaxSalry: " + afterTaxSalary);
    }

    /**
     *  0 ~ 3000元的部分，交税3%
     * ​	3000 ~ 12000元的部分，交税10%
     * ​	12000 ~ 25000的部分 ， 交税20%
     * ​	25000 ~ 35000的部分，交税25%
     * ​	35000 ~ 55000的部分，交税30%
     * ​	55000 ~ 80000的部分，交税35%
     * ​	超过80000的部分，交税45%
     *
     */
    public static double getTaxedSalary(double salary) {
        double taxedSalary = salary - 5000;

        return taxedSalary > 0 ? taxedSalary : 0;
    }

    public static double calculateTax(double taxedSalary) {
        double tax = 0;
        if (taxedSalary > 0 && taxedSalary <= 3000) { // 0.03
//            2000
            tax = taxedSalary * 0.03;

        } else if (taxedSalary > 3000 && taxedSalary <= 12000) { // 0.1
//            11000 : 3000 + 8000(多出来的部分)
            tax = 3000 * 0.03 + (taxedSalary - 3000) * 0.1;

        } else if (taxedSalary > 12000 && taxedSalary <= 25000) { // 0.2
//            22000: 3000 + 19000(9000 + 10000)
            tax = 3000 * 0.03 + 9000 * 0.1 + (taxedSalary - 12000) * 0.2;

        } else if (taxedSalary > 25000 && taxedSalary <= 35000) { // 0.25
//            30000: 3000 + 27000( 9000 + 18000(13000 + 5000) )
            tax = 3000 * 0.03 + 9000 * 0.1 + 13000 * 0.2 + (taxedSalary - 25000) * 0.25;

        } else if (taxedSalary > 35000 && taxedSalary <= 55000) { // 0.3
//            51000: 3000 + 48000( 9000 + 37000( 13000 + 24000 ( 10000 + 14000) ) )
            tax = 3000 * 0.03 + 9000 * 0.1 + 13000 * 0.2 + 10000 * 0.25 + (taxedSalary - 35000) * 0.3;
        } else if (taxedSalary > 55000 && taxedSalary < 80000) { // 0.35
            tax = 3000 * 0.03 + 9000 * 0.1 + 13000 * 0.2 + 10000 * 0.25 + 20000 * 0.35 + (taxedSalary - 55000) * 0.35;
        } else {
            tax = 3000 * 0.03 + 9000 * 0.1 + 13000 * 0.2 + 10000 * 0.25 + 20000 * 0.35 + 35000 * 0.35 + (taxedSalary - 80000) * 0.45;
        }

        return tax;
    }
//
//    public static double calculateTax(double salary) {
//        double taxedSalary = getTaxedSalary(salary);
//        StdOut.println("taxedSalary: " + taxedSalary);
//
//        if (taxedSalary == 0) {
//            return taxedSalary;
//        }
//        double[] rates  = {0.03, 0.1, 0.2, 0.25, 0.3, 0.35, 0.45};
//        double[] starts = {3000, 12000, 25000, 35000, 55000, 80000};
//
//        if (taxedSalary <= starts[0]) {
//            return taxedSalary * 0.03;
//        }
//
//
//    }
}
