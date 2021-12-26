package utilsLabFirst;

import java.util.Map;
import java.util.TreeMap;

import static java.lang.Math.abs;
import static java.lang.String.format;
import static java.util.Collections.max;
import static java.util.Collections.min;

public class Polynomial {
    public static class NewtonPolynomial {
        public static double calc(TreeMap<Double, Double> table, int power, double pointX) {
            if (table.containsKey(pointX)) {
                return table.get(pointX);
            }

            check(table, power, pointX);

            table = getTablePart(table, pointX, power);
            Double[] YN = table.values().toArray(Double[]::new);
            Double[] XN = table.keySet().toArray(Double[]::new);
            Double[] coefficients = findCoefficient(XN, YN, power);
            double result = coefficients[0];
            double d = 1;

            for (int i = 1; i <= power; i++) {
                d *= (pointX - XN[i - 1]);
                result += coefficients[i] * d;
            }
            return result;
        }

        private static void check(Map<Double, Double> table, int power, double pointX) {
            if (power > 4 || power < 0) {
                throw new IllegalArgumentException("Polynomial power should be: 0 < n > 4");
            }

            if (power > table.size() - 1) {
                throw new IllegalArgumentException("Points a less than polynomial power");
            }

            double min = min(table.keySet());
            double max = max(table.keySet());

            if (pointX < min || pointX > max) {
                throw new IllegalArgumentException(format("Value of point x should be from: %f, to: %f", min, max));
            }
        }

        private static TreeMap<Double, Double> getTablePart(TreeMap<Double, Double> table, double x, int n) {
            TreeMap<Double, Double> map = new TreeMap<>();
            Double[] x_n = table.keySet().toArray(Double[]::new);
            int pos = getFirstPosition(x_n, x, n);
            for (int i = pos; i < pos + n + 1; i++) {
                double key = x_n[i];
                map.put(key, table.get(key));
            }
            return map;
        }

        private static int getFirstPosition(Double[] XN, double x, int n) {
            int position = 0;
            while (position < XN.length && XN[position] < x) {
                position++;
            }
            position = checkMinPosition(position, n);
            position = checkMaxPosition(position, n, XN.length);
            return position;
        }

        private static int checkMinPosition(int pos, int n) {
            double t = pos - (double) n / 2;
            return (t <= 0) ? 0 : (int) t;
        }

        private static int checkMaxPosition(int pos, int n, int size) {
            double t = pos + abs(pos - (double) n / 2);
            return (t > size) ? n : pos;
        }

        private static Double[] findCoefficient(Double[] XN, Double[] YN, int n) {
            Double[] coefficients = new Double[n + 1];
            coefficients[0] = YN[0];

            for (int i = 1; i <= n; i++) {
                Double[] resultY = new Double[n - i + 1];
                for (int j = 0; j <= n - i; j++) {
                    resultY[j] = (YN[j] - YN[j + 1]) / (XN[j] - XN[j + i]);
                }
                YN = resultY;

                coefficients[i] = YN[0];
            }
            return coefficients;
        }
    }

    public static class LagrangePolynomial {

        public static double calc(TreeMap<Double, Double> table, double x) {
            if (table.containsKey(x)) {
                return table.get(x);
            }
            check(table, x);
            Double[] y_n = table.values().toArray(Double[]::new);
            Double[] x_n = table.keySet().toArray(Double[]::new);
            double l = 0;
            for (int i = 0; i < table.size(); i++) {
                double p = 1;
                for (int j = 0; j < table.size(); j++) {
                    if (i != j) {
                        p *= (x - x_n[j]) / (x_n[i] - x_n[j]);
                    }
                }
                l += y_n[i] * p;
            }
            return l;
        }

        private static void check(Map<Double, Double> table, double x) {
            double min = min(table.keySet());
            double max = max(table.keySet());
            if (x < min || x > max) {
                throw new IllegalArgumentException(format("Value of point x should be from: %f, to: %f", min, max));
            }
        }
    }
}
