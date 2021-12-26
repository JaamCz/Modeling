package utilsLabFirst;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import static java.util.Map.of;

public class Helper {
    private final static Scanner sc = new Scanner(System.in);

    public static double getX() {
        System.out.println("Enter value of x: ");
        return sc.nextDouble();
    }

    public static int getPower() {
        System.out.println("Enter polynomial power: ");
        int power = sc.nextInt();

        if (power > 4 || power < 0) {
            throw new IllegalArgumentException("Polynomial power should be: 0 < n > 4");
        }

        return power;
    }

    public static Map<Double, Double> getTableXY() {
        Map<Double, Double> table = new TreeMap<>();
        System.out.println("Enter number of points: ");
        int points = sc.nextInt();

        if (points < 0) {
            throw new IllegalArgumentException("Points must be greater than 0");
        }

        for (int i = 0; i < points; i++) {
            System.out.println("Enter x y: ");
            table.put(sc.nextDouble(), sc.nextDouble());
        }

        return table;
    }

    public final static Map<Double, Double> FUNCTION_TABLE = of(
            0.0, 1.0,
            0.15, 0.838771,
            0.3, 0.655336,
            0.45, 0.450447,
            0.6, 0.225336,
            0.75, -0.01831,
            0.9, -0.27839,
            1.05, -0.55243
    );
}
