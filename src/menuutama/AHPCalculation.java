package menuutama;

import java.text.DecimalFormat;

public class AHPCalculation {

    private DecimalFormat df = new DecimalFormat("#.###");

    private static final double[] RI = { 0.0, 0.0, 0.58, 0.90, 1.12, 1.24, 1.32, 1.41, 1.45, 1.49 };

    private double[][] criteriaMatrix = new double[4][4];
    private double[][] criteriaNormalized = new double[4][4];
    private double[] criteriaWeights = new double[4];
    private double[][][] subcriteriaMatrices = new double[4][3][3];
    private double[][][] subcriteriaNormalized = new double[4][3][3];

    private double[][] subcriteriaWeights = {
            { 0.09325, 0.08025, 0.06575 }, // Criterion 1
            { 0.09325, 0.08025, 0.06575 }, // Criterion 2
            { 0.09325, 0.08025, 0.06575 }, // Criterion 3
            { 0.09325, 0.08025, 0.06575 } // Criterion 4
    };

    {
        subcriteriaWeights[0][0] = 0.09325;
        subcriteriaWeights[1][0] = 0.09325;
        subcriteriaWeights[2][0] = 0.09325;
        subcriteriaWeights[3][0] = 0.09325; // sum = 0.373

        subcriteriaWeights[0][1] = 0.08025;
        subcriteriaWeights[1][1] = 0.08025;
        subcriteriaWeights[2][1] = 0.08025;
        subcriteriaWeights[3][1] = 0.08025; // sum = 0.321

        subcriteriaWeights[0][2] = 0.06575;
        subcriteriaWeights[1][2] = 0.06575;
        subcriteriaWeights[2][2] = 0.06575;
        subcriteriaWeights[3][2] = 0.06575; // sum = 0.263
    }

    public AHPCalculation() {
        initializeMatrices();
        calculateAll();
    }

    private void initializeMatrices() {
        criteriaMatrix = new double[][] {
                { 1.0, 3.0, 5.0, 7.0 },
                { 0.333, 1.0, 3.0, 5.0 },
                { 0.200, 0.333, 1.0, 3.0 },
                { 0.143, 0.200, 0.333, 1.0 }
        };
        subcriteriaMatrices[0] = new double[][] {
                { 1.0, 3.0, 5.0 },
                { 0.333, 1.0, 3.0 },
                { 0.200, 0.333, 1.0 }
        };
        subcriteriaMatrices[1] = new double[][] {
                { 1.0, 1.0, 3.0 },
                { 1.0, 1.0, 3.0 },
                { 0.333, 0.333, 1.0 }
        };
        subcriteriaMatrices[2] = new double[][] {
                { 1.0, 3.0, 7.0 },
                { 0.333, 1.0, 5.0 },
                { 0.143, 0.200, 1.0 }
        };
        subcriteriaMatrices[3] = new double[][] {
                { 1.0, 3.0, 5.0 },
                { 0.333, 1.0, 3.0 },
                { 0.200, 0.333, 1.0 }
        };
    }

    public void calculateAll() {
        calculateCriteriaWeights();
    }

    private void calculateCriteriaWeights() {
        double[] columnSums = new double[4];
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                columnSums[j] += criteriaMatrix[i][j];
            }
        }
        for (int i = 0; i < 4; i++) {
            double rowSum = 0;
            for (int j = 0; j < 4; j++) {
                criteriaNormalized[i][j] = criteriaMatrix[i][j] / columnSums[j];
                rowSum += criteriaNormalized[i][j];
            }
            criteriaWeights[i] = rowSum / 4;
        }
    }

    public double calculateFinalScore(int priceCategory, int ccCategory, int fuelCategory, int designCategory) {
        double finalScore = 0.0;

        finalScore += subcriteriaWeights[0][priceCategory];
        finalScore += subcriteriaWeights[1][ccCategory];
        finalScore += subcriteriaWeights[2][fuelCategory];
        finalScore += subcriteriaWeights[3][designCategory];
        return finalScore;
    }

    public double calculateCriteriaConsistencyRatio() {
        return calculateConsistencyRatio(criteriaMatrix, criteriaWeights, 4);
    }

    public double calculateSubcriteriaConsistencyRatio(int criteriaIndex) {
        return calculateConsistencyRatio(subcriteriaMatrices[criteriaIndex], subcriteriaWeights[criteriaIndex], 3);
    }

    private double calculateConsistencyRatio(double[][] matrix, double[] weights, int n) {
        double[] weightedSum = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                weightedSum[i] += matrix[i][j] * weights[j];
            }
        }
        double lambdaMax = 0;
        for (int i = 0; i < n; i++) {
            lambdaMax += weightedSum[i] / weights[i];
        }
        lambdaMax /= n;
        double CI = (lambdaMax - n) / (n - 1);
        double CR = CI / RI[n - 1];
        return CR;
    }

    public double[][] getCriteriaMatrix() {
        return criteriaMatrix;
    }

    public double[][] getCriteriaNormalized() {
        return criteriaNormalized;
    }

    public double[] getCriteriaWeights() {
        return criteriaWeights;
    }

    public double[][] getSubcriteriaWeights() {
        return subcriteriaWeights;
    }

    public double[][][] getSubcriteriaMatrices() {
        return subcriteriaMatrices;
    }

    public double[][][] getSubcriteriaNormalized() {
        return subcriteriaNormalized;
    }

    public String[] getFormattedCriteriaWeights() {
        String[] formatted = new String[4];
        for (int i = 0; i < 4; i++) {
            formatted[i] = df.format(criteriaWeights[i]);
        }
        return formatted;
    }

    public String[][] getFormattedSubcriteriaWeights() {
        String[][] formatted = new String[4][3];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                formatted[i][j] = df.format(subcriteriaWeights[i][j]);
            }
        }
        return formatted;
    }

}
