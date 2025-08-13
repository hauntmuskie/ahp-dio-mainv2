package menuutama;

public class AHPCalculationTest {
    public static void main(String[] args) {
        AHPCalculation ahp = new AHPCalculation();
        // Dell Xps: [0][0], [1][0], [2][0], [3][0]
        double dellScore = ahp.getSubcriteriaWeights()[0][0] + ahp.getSubcriteriaWeights()[1][0] + ahp.getSubcriteriaWeights()[2][0] + ahp.getSubcriteriaWeights()[3][0];
        // Macbook Pro: [0][1], [1][1], [2][1], [3][1]
        double macScore = ahp.getSubcriteriaWeights()[0][1] + ahp.getSubcriteriaWeights()[1][1] + ahp.getSubcriteriaWeights()[2][1] + ahp.getSubcriteriaWeights()[3][1];
        // Lenovo Thinkpad: [0][2], [1][2], [2][2], [3][2]
        double lenovoScore = ahp.getSubcriteriaWeights()[0][2] + ahp.getSubcriteriaWeights()[1][2] + ahp.getSubcriteriaWeights()[2][2] + ahp.getSubcriteriaWeights()[3][2];
        System.out.printf("Dell Xps: %.3f\n", dellScore);
        System.out.printf("Macbook Pro: %.3f\n", macScore);
        System.out.printf("Lenovo Thinkpad: %.3f\n", lenovoScore);
    }
}
