import java.util.TreeMap;

import static utilsLabFirst.Helper.*;
import static utilsLabFirst.Polynomial.NewtonPolynomial.calc;

public class LabFirstMain {
    public static void main(String[] args) {
        double pointX = getX();
        int power = getPower();

        TreeMap<Double, Double> map = new TreeMap<>(FUNCTION_TABLE);

        double y = calc(map, power, pointX);

//        System.out.printf("n = %d, x = %.2f, y = %.15f", power, pointX, y);
        System.out.println("n = " + power + ", x = " + pointX + ", y = " + y);
    }
}
