
package menuutama;

import java.text.DecimalFormat;

/**
 *
 * @author Siti Mawaddah
 */
public class SubKriteriaAhp {
    /**
     * n banyak kriteria
     */
    protected static int nBanyak5x5 = 5;
    /**
     * Random Index Consistency (RI)
     */
    protected static double RI[] = { 0.0, 0.0, 0.58, 0.90, 1.12, 1.24, 1.32, 1.41, 1.45, 1.49, 1.51, 1.48, 1.56, 1.57,
            1.59 };

    /**
     * Sebuah Matriks berpasangan
     */

    static double[][] matriksBerpasangan5x5 = new double[nBanyak5x5][nBanyak5x5];
    static double[] jumlahMatriksBerpasangan5x5 = new double[nBanyak5x5];

    /**
     * Matriks Normalisasi Kriteria
     */
    static double[][] matriksNormalisasi5x5 = new double[nBanyak5x5][nBanyak5x5];
    static double[] jumlahMatriksNormalisasi5x5 = new double[nBanyak5x5];
    static double[] prioritas5x5 = new double[nBanyak5x5];
    static double[] prioritasSub5x5 = new double[nBanyak5x5];

    /**
     * Matriks Penjumlahan Kriteria
     */
    static double[][] matriksPenjumlahan5x5 = new double[nBanyak5x5][nBanyak5x5];
    static double[] jumlahMatriksPenjumlahan5x5 = new double[nBanyak5x5];

    /**
     * cek konsistensi rasio kriteria
     */
    static double[] jumlahCekKonsistensi5x5 = new double[nBanyak5x5];

    /**
     * Format desimal
     */
    static DecimalFormat df = new DecimalFormat("#.##");

    /**
     * Menentukan IR dari nBanyak4x4 kriteria/sub
     */
    static double IR;

    // Dapatkan Nilai IR
    static double getIR(int nBanyak) {
        if (nBanyak == 1 || nBanyak == 2) {
            IR = RI[0];
        } else if (nBanyak == 3) {
            IR = RI[2];
        } else if (nBanyak == 4) {
            IR = RI[3];
        } else if (nBanyak == 5) {
            IR = RI[4];
        } else if (nBanyak == 6) {
            IR = RI[5];
        } else if (nBanyak == 7) {
            IR = RI[6];
        } else if (nBanyak == 8) {
            IR = RI[7];
        } else if (nBanyak == 9) {
            IR = RI[8];
        } else if (nBanyak == 10) {
            IR = RI[9];
        }
        return IR;
    }

    // set Nilai
    public static void setNilaiKriteria5x5() {
        // nilai dari matriks berapasangan kriteria
        double matriks[][] = {
                { 1, 3.0, 5.0, 7.0, 9.0 },
                { 1.0 / 3.0, 1, 3.0, 5.0, 7.0 },
                { 1.0 / 5.0, 1.0 / 3.0, 1, 3.0, 5.0 },
                { 1.0 / 7.0, 1.0 / 5.0, 1.0 / 3.0, 1, 3.0 },
                { 1.0 / 9.0, 1.0 / 7.0, 1.0 / 5.0, 1.0 / 3.0, 1 }
        };
        for (int row = 0; row < nBanyak5x5; row++) {
            for (int col = 0; col < nBanyak5x5; col++) {
                matriksBerpasangan5x5[row][col] = matriks[row][col];
            }
        }
    }

    // Membuat Matriks Kriteria Normalisasi Metode AHP
    public static void MatriksBerpasangan5x5() {
        // masukkan nilai matriks berpasangan
        setNilaiKriteria5x5();
        // Jumlah PerKolom Pada Matriks berpasangan
        for (int row = 0; row < nBanyak5x5; row++) {
            for (int col = 0; col < nBanyak5x5; col++) {
                jumlahMatriksBerpasangan5x5[col] += matriksBerpasangan5x5[row][col];
            }
        }
    }

    // membuat matriks normalisasi kriteria
    public static void MatriksNormalisasi5x5() {
        // perhitungan nilai dari matriks normalisasi kriteria
        // Jumlah setiap baris dan nilai prioritas
        for (int row = 0; row < nBanyak5x5; row++) {
            for (int col = 0; col < nBanyak5x5; col++) {
                matriksNormalisasi5x5[row][col] = matriksBerpasangan5x5[row][col] / jumlahMatriksBerpasangan5x5[col];
                jumlahMatriksNormalisasi5x5[row] += matriksNormalisasi5x5[row][col];
                prioritas5x5[row] = jumlahMatriksNormalisasi5x5[row] / nBanyak5x5;
            }
        }

        // mencari nilai terbesar atau maks dari prioritas untuk prioritas SubKriteria
        double maxNum = prioritas5x5[0];
        for (double j : prioritas5x5) {
            if (j > maxNum)
                maxNum = j;
        }
        for (int i = 0; i < nBanyak5x5; i++) {
            // Perhitungan Prioritas SubKriteria
            prioritasSub5x5[i] = prioritas5x5[i] / maxNum;
        }
    }

    // membuat matriks penjumlahan setiap baris untuk kriteria
    public static void MatriksPenjumlahan5x5() {
        for (int row = 0; row < nBanyak5x5; row++) {
            for (int col = 0; col < nBanyak5x5; col++) {
                matriksPenjumlahan5x5[row][col] = matriksBerpasangan5x5[row][col] * prioritas5x5[col];
                jumlahMatriksPenjumlahan5x5[row] += matriksPenjumlahan5x5[row][col];
            }
        }
    }

    // cek konsistensi rasio kriteria
    public static String getCekKonsistensi5x5() {
        double totalJumlah = 0;
        for (int row = 0; row < nBanyak5x5; row++) {
            jumlahCekKonsistensi5x5[row] = jumlahMatriksPenjumlahan5x5[row] + prioritas5x5[row];
            totalJumlah += jumlahCekKonsistensi5x5[row];
        }
        double ir = getIR(nBanyak5x5);
        double lamdaMaks = totalJumlah / nBanyak5x5;
        double CI = (lamdaMaks - nBanyak5x5) / (nBanyak5x5 - 1);
        double CR = CI / ir;
        if (CR <= 0.1) {
            return "Konsisten";
        } else {
            return "Tidak Konsisten";
        }
    }

    public SubKriteriaAhp() {
        MatriksBerpasangan5x5();
        MatriksNormalisasi5x5();
        MatriksPenjumlahan5x5();
    }
}
