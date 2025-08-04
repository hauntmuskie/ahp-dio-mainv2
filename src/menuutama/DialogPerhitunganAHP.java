
package menuutama;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import koneksi.Koneksi;

/**
 *
 * @author Siti Mawaddah
 */
public class DialogPerhitunganAHP extends javax.swing.JDialog {
        private Connection conn = new Koneksi().connect();
        protected KriteriaAhp kriteria = new KriteriaAhp();
        protected SubKriteriaAhp SubK = new SubKriteriaAhp();
        DecimalFormat df = new DecimalFormat("#.###");
        ArrayList<String> K = new ArrayList<String>();
        ArrayList<Double> KS5x5 = new ArrayList<Double>();
        // Cache for sub-criteria priorities to avoid multiple database calls
        private double[][] subKriteriaPrioritas = null;
        String noIdAlternatif, namaLaptopAlternatif, performaAlternatif, hargaAlternatif, penyimpananAlternatif,
                        dayaTahanBateraiAlternatif, ramAlternatif;
        double nilaiAlternatif, totalNilai;

        /**
         * Creates new form DialogTambahData
         */
        public DialogPerhitunganAHP(java.awt.Frame parent, boolean modal) {
                super(parent, modal);
                initComponents();
                getRelasiId();

                // Disable save button initially until calculation is done
                Simpan.setEnabled(false);

                // add Panel, add panel(sebuah panel)
                Pane.add(PanelPerhitungan);
                Pane.repaint();
                Pane.revalidate();
        }

        void kosong() {
                TotalNilai.setText("");
                // Clear all matrix fields
                k1k1.setText("");
                k1k2.setText("");
                k1k3.setText("");
                k1k4.setText("");
                k1k5.setText("");
                k2k1.setText("");
                k2k2.setText("");
                k2k3.setText("");
                k2k4.setText("");
                k2k5.setText("");
                k3k1.setText("");
                k3k2.setText("");
                k3k3.setText("");
                k3k4.setText("");
                k3k5.setText("");
                k4k1.setText("");
                k4k2.setText("");
                k4k3.setText("");
                k4k4.setText("");
                k4k5.setText("");
                k5k1.setText("");
                k5k2.setText("");
                k5k3.setText("");
                k5k4.setText("");
                k5k5.setText("");

                // Clear normalization matrix fields
                k1k1N.setText("");
                k1k2N.setText("");
                k1k3N.setText("");
                k1k4N.setText("");
                k1k5N.setText("");
                k2k1N.setText("");
                k2k2N.setText("");
                k2k3N.setText("");
                k2k4N.setText("");
                k2k5N.setText("");
                k3k1N.setText("");
                k3k2N.setText("");
                k3k3N.setText("");
                k3k4N.setText("");
                k3k5N.setText("");
                k4k1N.setText("");
                k4k2N.setText("");
                k4k3N.setText("");
                k4k4N.setText("");
                k4k5N.setText("");
                k5k1N.setText("");
                k5k2N.setText("");
                k5k3N.setText("");
                k5k4N.setText("");
                k5k5N.setText("");

                // Clear priority fields
                Prior1.setText("");
                Prior2.setText("");
                Prior3.setText("");
                Prior4.setText("");
                Prior5.setText("");
        }

        // nilai matriks berpasangan kriteria
        public void getMatriksK() {
                k1k1.setText(df.format(kriteria.matriksBerpasangan[0][0]));
                k1k2.setText(df.format(kriteria.matriksBerpasangan[0][1]));
                k1k3.setText(df.format(kriteria.matriksBerpasangan[0][2]));
                k1k4.setText(df.format(kriteria.matriksBerpasangan[0][3]));
                k1k5.setText(df.format(kriteria.matriksBerpasangan[0][4]));
                k2k1.setText(df.format(kriteria.matriksBerpasangan[1][0]));
                k2k2.setText(df.format(kriteria.matriksBerpasangan[1][1]));
                k2k3.setText(df.format(kriteria.matriksBerpasangan[1][2]));
                k2k4.setText(df.format(kriteria.matriksBerpasangan[1][3]));
                k2k5.setText(df.format(kriteria.matriksBerpasangan[1][4]));
                k3k1.setText(df.format(kriteria.matriksBerpasangan[2][0]));
                k3k2.setText(df.format(kriteria.matriksBerpasangan[2][1]));
                k3k3.setText(df.format(kriteria.matriksBerpasangan[2][2]));
                k3k4.setText(df.format(kriteria.matriksBerpasangan[2][3]));
                k3k5.setText(df.format(kriteria.matriksBerpasangan[2][4]));
                k4k1.setText(df.format(kriteria.matriksBerpasangan[3][0]));
                k4k2.setText(df.format(kriteria.matriksBerpasangan[3][1]));
                k4k3.setText(df.format(kriteria.matriksBerpasangan[3][2]));
                k4k4.setText(df.format(kriteria.matriksBerpasangan[3][3]));
                k4k5.setText(df.format(kriteria.matriksBerpasangan[3][4]));
                k5k1.setText(df.format(kriteria.matriksBerpasangan[4][0]));
                k5k2.setText(df.format(kriteria.matriksBerpasangan[4][1]));
                k5k3.setText(df.format(kriteria.matriksBerpasangan[4][2]));
                k5k4.setText(df.format(kriteria.matriksBerpasangan[4][3]));
                k5k5.setText(df.format(kriteria.matriksBerpasangan[4][4]));
        }

        // nilai matriks berpasangan kriteria
        public void getMatriksNorK() {
                k1k1N.setText(df.format(kriteria.matriksNormalisasi[0][0]));
                k1k2N.setText(df.format(kriteria.matriksNormalisasi[0][1]));
                k1k3N.setText(df.format(kriteria.matriksNormalisasi[0][2]));
                k1k4N.setText(df.format(kriteria.matriksNormalisasi[0][3]));
                k1k5N.setText(df.format(kriteria.matriksNormalisasi[0][4]));
                k2k1N.setText(df.format(kriteria.matriksNormalisasi[1][0]));
                k2k2N.setText(df.format(kriteria.matriksNormalisasi[1][1]));
                k2k3N.setText(df.format(kriteria.matriksNormalisasi[1][2]));
                k2k4N.setText(df.format(kriteria.matriksNormalisasi[1][3]));
                k2k5N.setText(df.format(kriteria.matriksNormalisasi[1][4]));
                k3k1N.setText(df.format(kriteria.matriksNormalisasi[2][0]));
                k3k2N.setText(df.format(kriteria.matriksNormalisasi[2][1]));
                k3k3N.setText(df.format(kriteria.matriksNormalisasi[2][2]));
                k3k4N.setText(df.format(kriteria.matriksNormalisasi[2][3]));
                k3k5N.setText(df.format(kriteria.matriksNormalisasi[2][4]));
                k4k1N.setText(df.format(kriteria.matriksNormalisasi[3][0]));
                k4k2N.setText(df.format(kriteria.matriksNormalisasi[3][1]));
                k4k3N.setText(df.format(kriteria.matriksNormalisasi[3][2]));
                k4k4N.setText(df.format(kriteria.matriksNormalisasi[3][3]));
                k4k5N.setText(df.format(kriteria.matriksNormalisasi[3][4]));
                k5k1N.setText(df.format(kriteria.matriksNormalisasi[4][0]));
                k5k2N.setText(df.format(kriteria.matriksNormalisasi[4][1]));
                k5k3N.setText(df.format(kriteria.matriksNormalisasi[4][2]));
                k5k4N.setText(df.format(kriteria.matriksNormalisasi[4][3]));
                k5k5N.setText(df.format(kriteria.matriksNormalisasi[4][4]));
                Prior1.setText(df.format(kriteria.prioritas[0]));
                Prior2.setText(df.format(kriteria.prioritas[1]));
                Prior3.setText(df.format(kriteria.prioritas[2]));
                Prior4.setText(df.format(kriteria.prioritas[3]));
                Prior5.setText(df.format(kriteria.prioritas[4]));

        }

        // nilai prioritas untuk sub-kriteria dari kriteria yang tersedia
        // Menampilkan nilai prioritas sub-kriteria hasil AHP ke tampilan GUI
        public void getPrioritasSub() {
                getKriteria(); // Ambil daftar kriteria dari database atau list

                // Load sub-criteria priorities from database
                double[][] subKriteriaData = loadSubKriteriaPrioritas();

                // Kriteria K1 (Performa)
                PriorS11.setText(df.format(subKriteriaData[0][0]));
                PriorS12.setText(df.format(subKriteriaData[0][1]));
                PriorS13.setText(df.format(subKriteriaData[0][2]));
                PriorS14.setText(df.format(subKriteriaData[0][3]));
                PriorS15.setText(df.format(subKriteriaData[0][4]));

                // Kriteria K2 (Harga)
                PriorS21.setText(df.format(subKriteriaData[1][0]));
                PriorS22.setText(df.format(subKriteriaData[1][1]));
                PriorS23.setText(df.format(subKriteriaData[1][2]));
                PriorS24.setText(df.format(subKriteriaData[1][3]));
                PriorS25.setText(df.format(subKriteriaData[1][4]));

                // Kriteria K3 (Penyimpanan)
                PriorS31.setText(df.format(subKriteriaData[2][0]));
                PriorS32.setText(df.format(subKriteriaData[2][1]));
                PriorS33.setText(df.format(subKriteriaData[2][2]));
                PriorS34.setText(df.format(subKriteriaData[2][3]));
                PriorS35.setText(df.format(subKriteriaData[2][4]));

                // Kriteria K4 (Ketahanan Baterai)
                PriorS41.setText(df.format(subKriteriaData[3][0]));
                PriorS42.setText(df.format(subKriteriaData[3][1]));
                PriorS43.setText(df.format(subKriteriaData[3][2]));
                PriorS44.setText(df.format(subKriteriaData[3][3]));
                PriorS45.setText(df.format(subKriteriaData[3][4]));

                // Kriteria K5 (RAM)
                PriorS51.setText(df.format(subKriteriaData[4][0]));
                PriorS52.setText(df.format(subKriteriaData[4][1]));
                PriorS53.setText(df.format(subKriteriaData[4][2]));
                PriorS54.setText(df.format(subKriteriaData[4][3]));
                PriorS55.setText(df.format(subKriteriaData[4][4]));
        }

        // menentukan kriteria pada kode K1, K2, K3, K4, K5
        public void getKriteria() {
                String sql = "SELECT nama_kriteria FROM kriteria ORDER BY kd_kriteria";
                try {
                        java.sql.Statement stat = conn.createStatement();
                        ResultSet hasil = stat.executeQuery(sql);
                        while (hasil.next()) {
                                String a = hasil.getString("nama_kriteria");
                                K.add(a);
                        }
                } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, e);
                }
        }

        // Helper method to load sub-criteria priorities from database
        private double[][] loadSubKriteriaPrioritas() {
                if (subKriteriaPrioritas != null) {
                        return subKriteriaPrioritas; // Return cached data if available
                }

                subKriteriaPrioritas = new double[5][5];

                // Load prioritas sub-kriteria untuk setiap kriteria dari database
                for (int k = 1; k <= 5; k++) {
                        String sql = "SELECT bobot_prioritas FROM sub_kriteria WHERE kd_kriteria='K" + k
                                        + "' ORDER BY no_sub";
                        try {
                                java.sql.Statement stat = conn.createStatement();
                                ResultSet hasil = stat.executeQuery(sql);
                                int index = 0;
                                while (hasil.next() && index < 5) {
                                        subKriteriaPrioritas[k - 1][index] = hasil.getDouble("bobot_prioritas");
                                        index++;
                                }
                        } catch (SQLException e) {
                                JOptionPane.showMessageDialog(null,
                                                "Error loading sub-criteria for K" + k + ": " + e.getMessage());
                                // Fallback to default values if database error
                                for (int i = 0; i < 5; i++) {
                                        subKriteriaPrioritas[k - 1][i] = SubKriteriaAhp.prioritasSub5x5[i];
                                }
                        }
                }

                return subKriteriaPrioritas;
        }

        // Mendapatkan alternatif dari data laptop yang ada
        public void getAlternatif() {
                String sql = "SELECT DISTINCT * FROM data_laptop WHERE id_laptop='"
                                + cbIdLaptop.getSelectedItem().toString()
                                + "'";
                try {
                        java.sql.Statement stat = conn.createStatement();
                        ResultSet hasil = stat.executeQuery(sql);
                        while (hasil.next()) {
                                String a = hasil.getString("id_laptop");
                                String b = hasil.getString("nama_laptop");
                                String c = hasil.getString("performa");
                                String d = hasil.getString("kategori_harga");
                                String e = hasil.getString("kategori_penyimpanan");
                                String f = hasil.getString("kategori_daya_tahan");
                                String g = hasil.getString("kategori_ram");
                                noIdAlternatif = a;
                                namaLaptopAlternatif = b;
                                performaAlternatif = c;
                                hargaAlternatif = d;
                                penyimpananAlternatif = e;
                                dayaTahanBateraiAlternatif = f;
                                ramAlternatif = g;
                        }
                } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, e);
                }
        }

        // melakukan perhitungan dari alternatif yang dipilih
        // untuk mendapatkan hasil penilaian
        public void getPenilaian() {
                totalNilai = 0;

                // Load sub-criteria priorities from database
                double[][] subKriteriaData = loadSubKriteriaPrioritas();

                // K1: Performa
                if (performaAlternatif.equals("High-End")) {
                        nilaiAlternatif = subKriteriaData[0][0] * kriteria.prioritas[0];
                } else if (performaAlternatif.equals("Mid-Range")) {
                        nilaiAlternatif = subKriteriaData[0][1] * kriteria.prioritas[0];
                } else if (performaAlternatif.equals("Entry-Level")) {
                        nilaiAlternatif = subKriteriaData[0][2] * kriteria.prioritas[0];
                } else if (performaAlternatif.equals("Basic")) {
                        nilaiAlternatif = subKriteriaData[0][3] * kriteria.prioritas[0];
                } else {
                        nilaiAlternatif = subKriteriaData[0][4] * kriteria.prioritas[0];
                }
                totalNilai += nilaiAlternatif;

                // K2: Harga
                if (hargaAlternatif.equals("Sangat Terjangkau")) {
                        nilaiAlternatif = subKriteriaData[1][0] * kriteria.prioritas[1];
                } else if (hargaAlternatif.equals("Terjangkau")) {
                        nilaiAlternatif = subKriteriaData[1][1] * kriteria.prioritas[1];
                } else if (hargaAlternatif.equals("Normal")) {
                        nilaiAlternatif = subKriteriaData[1][2] * kriteria.prioritas[1];
                } else if (hargaAlternatif.equals("Mahal")) {
                        nilaiAlternatif = subKriteriaData[1][3] * kriteria.prioritas[1];
                } else {
                        nilaiAlternatif = subKriteriaData[1][4] * kriteria.prioritas[1];
                }
                totalNilai += nilaiAlternatif;

                // K3: Penyimpanan
                if (penyimpananAlternatif.equals("Sangat Besar")) {
                        nilaiAlternatif = subKriteriaData[2][0] * kriteria.prioritas[2];
                } else if (penyimpananAlternatif.equals("Besar")) {
                        nilaiAlternatif = subKriteriaData[2][1] * kriteria.prioritas[2];
                } else if (penyimpananAlternatif.equals("Sedang")) {
                        nilaiAlternatif = subKriteriaData[2][2] * kriteria.prioritas[2];
                } else if (penyimpananAlternatif.equals("Kecil")) {
                        nilaiAlternatif = subKriteriaData[2][3] * kriteria.prioritas[2];
                } else {
                        nilaiAlternatif = subKriteriaData[2][4] * kriteria.prioritas[2];
                }
                totalNilai += nilaiAlternatif;

                // K4: Daya Tahan Baterai
                if (dayaTahanBateraiAlternatif.equals("Sangat Lama")) {
                        nilaiAlternatif = subKriteriaData[3][0] * kriteria.prioritas[3];
                } else if (dayaTahanBateraiAlternatif.equals("Lama")) {
                        nilaiAlternatif = subKriteriaData[3][1] * kriteria.prioritas[3];
                } else if (dayaTahanBateraiAlternatif.equals("Sedang")) {
                        nilaiAlternatif = subKriteriaData[3][2] * kriteria.prioritas[3];
                } else if (dayaTahanBateraiAlternatif.equals("Kurang")) {
                        nilaiAlternatif = subKriteriaData[3][3] * kriteria.prioritas[3];
                } else {
                        nilaiAlternatif = subKriteriaData[3][4] * kriteria.prioritas[3];
                }
                totalNilai += nilaiAlternatif;

                // K5: RAM
                if (ramAlternatif.equals("Sangat Tinggi")) {
                        nilaiAlternatif = subKriteriaData[4][0] * kriteria.prioritas[4];
                } else if (ramAlternatif.equals("Tinggi")) {
                        nilaiAlternatif = subKriteriaData[4][1] * kriteria.prioritas[4];
                } else if (ramAlternatif.equals("Sedang")) {
                        nilaiAlternatif = subKriteriaData[4][2] * kriteria.prioritas[4];
                } else if (ramAlternatif.equals("Rendah")) {
                        nilaiAlternatif = subKriteriaData[4][3] * kriteria.prioritas[4];
                } else { // Sangat Rendah
                        nilaiAlternatif = subKriteriaData[4][4] * kriteria.prioritas[4];
                }
                totalNilai += nilaiAlternatif;

                TotalNilai.setText(df.format(totalNilai));
        }

        // Update rankings for all records in seleksi table
        private void updateRankings() {
                try {
                        // First, get all records ordered by hasil_penilaian descending
                        String selectSql = "SELECT id_laptop FROM seleksi ORDER BY hasil_penilaian DESC";
                        java.sql.Statement selectStat = conn.createStatement();
                        ResultSet rs = selectStat.executeQuery(selectSql);

                        // Update rankings
                        String updateSql = "UPDATE seleksi SET ranking = ? WHERE id_laptop = ?";
                        PreparedStatement updateStat = conn.prepareStatement(updateSql);

                        int rank = 1;
                        while (rs.next()) {
                                updateStat.setInt(1, rank);
                                updateStat.setString(2, rs.getString("id_laptop"));
                                updateStat.executeUpdate();
                                rank++;
                        }

                } catch (SQLException e) {
                        System.err.println("Error updating rankings: " + e);
                }
        }

        // Mendapatkan relasi pada combobox pada database data laptop
        public void getRelasiId() {
                String sql = "SELECT DISTINCT id_laptop, nama_laptop FROM data_laptop ORDER BY id_laptop";
                try {
                        java.sql.Statement stat = conn.createStatement();
                        ResultSet hasil = stat.executeQuery(sql);
                        while (hasil.next()) {
                                String a = hasil.getString("id_laptop");
                                cbIdLaptop.addItem(a);
                        }
                } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, e);
                }
        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                PanelPerhitungan = new javax.swing.JPanel();
                judul = new javax.swing.JLabel();
                jScrollPane1 = new javax.swing.JScrollPane();
                jPanel1 = new javax.swing.JPanel();
                mulaiHitung = new javax.swing.JButton();
                jPanel2 = new javax.swing.JPanel();
                jLabel2 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                jLabel6 = new javax.swing.JLabel();
                k1k1 = new javax.swing.JTextField();
                k1k2 = new javax.swing.JTextField();
                k1k3 = new javax.swing.JTextField();
                k1k4 = new javax.swing.JTextField();
                k2k1 = new javax.swing.JTextField();
                k2k2 = new javax.swing.JTextField();
                k2k3 = new javax.swing.JTextField();
                k2k4 = new javax.swing.JTextField();
                k3k1 = new javax.swing.JTextField();
                k3k2 = new javax.swing.JTextField();
                k3k3 = new javax.swing.JTextField();
                k3k4 = new javax.swing.JTextField();
                k4k1 = new javax.swing.JTextField();
                k4k2 = new javax.swing.JTextField();
                k4k3 = new javax.swing.JTextField();
                k4k4 = new javax.swing.JTextField();
                jLabel7 = new javax.swing.JLabel();
                jLabel8 = new javax.swing.JLabel();
                jLabel9 = new javax.swing.JLabel();
                jLabel10 = new javax.swing.JLabel();
                jLabel33 = new javax.swing.JLabel();
                k5k1 = new javax.swing.JTextField();
                k5k2 = new javax.swing.JTextField();
                k5k3 = new javax.swing.JTextField();
                k5k4 = new javax.swing.JTextField();
                jLabel34 = new javax.swing.JLabel();
                k1k5 = new javax.swing.JTextField();
                k2k5 = new javax.swing.JTextField();
                k3k5 = new javax.swing.JTextField();
                k4k5 = new javax.swing.JTextField();
                k5k5 = new javax.swing.JTextField();
                jPanel3 = new javax.swing.JPanel();
                jLabel11 = new javax.swing.JLabel();
                jLabel12 = new javax.swing.JLabel();
                jLabel13 = new javax.swing.JLabel();
                jLabel14 = new javax.swing.JLabel();
                jLabel15 = new javax.swing.JLabel();
                k1k1N = new javax.swing.JTextField();
                k1k2N = new javax.swing.JTextField();
                k1k3N = new javax.swing.JTextField();
                k1k4N = new javax.swing.JTextField();
                k2k1N = new javax.swing.JTextField();
                k2k2N = new javax.swing.JTextField();
                k2k3N = new javax.swing.JTextField();
                k2k4N = new javax.swing.JTextField();
                k3k1N = new javax.swing.JTextField();
                k3k2N = new javax.swing.JTextField();
                k3k3N = new javax.swing.JTextField();
                k3k4N = new javax.swing.JTextField();
                k4k1N = new javax.swing.JTextField();
                k4k2N = new javax.swing.JTextField();
                k4k3N = new javax.swing.JTextField();
                k4k4N = new javax.swing.JTextField();
                jLabel16 = new javax.swing.JLabel();
                jLabel17 = new javax.swing.JLabel();
                jLabel18 = new javax.swing.JLabel();
                jLabel19 = new javax.swing.JLabel();
                jLabel20 = new javax.swing.JLabel();
                Prior1 = new javax.swing.JTextField();
                Prior2 = new javax.swing.JTextField();
                Prior3 = new javax.swing.JTextField();
                Prior4 = new javax.swing.JTextField();
                jLabel35 = new javax.swing.JLabel();
                k1k5N = new javax.swing.JTextField();
                k2k5N = new javax.swing.JTextField();
                k3k5N = new javax.swing.JTextField();
                k4k5N = new javax.swing.JTextField();
                jLabel36 = new javax.swing.JLabel();
                k5k1N = new javax.swing.JTextField();
                k5k2N = new javax.swing.JTextField();
                k5k3N = new javax.swing.JTextField();
                k5k4N = new javax.swing.JTextField();
                k5k5N = new javax.swing.JTextField();
                Prior5 = new javax.swing.JTextField();
                Simpan = new javax.swing.JButton();
                jPanel4 = new javax.swing.JPanel();
                jLabel23 = new javax.swing.JLabel();
                jLabel28 = new javax.swing.JLabel();
                jLabel29 = new javax.swing.JLabel();
                jLabel30 = new javax.swing.JLabel();
                jLabel31 = new javax.swing.JLabel();
                jLabel21 = new javax.swing.JLabel();
                PriorS11 = new javax.swing.JTextField();
                PriorS12 = new javax.swing.JTextField();
                PriorS13 = new javax.swing.JTextField();
                PriorS14 = new javax.swing.JTextField();
                jLabel24 = new javax.swing.JLabel();
                PriorS21 = new javax.swing.JTextField();
                PriorS22 = new javax.swing.JTextField();
                PriorS23 = new javax.swing.JTextField();
                PriorS24 = new javax.swing.JTextField();
                jLabel25 = new javax.swing.JLabel();
                PriorS31 = new javax.swing.JTextField();
                PriorS32 = new javax.swing.JTextField();
                PriorS33 = new javax.swing.JTextField();
                PriorS34 = new javax.swing.JTextField();
                jLabel26 = new javax.swing.JLabel();
                PriorS41 = new javax.swing.JTextField();
                PriorS42 = new javax.swing.JTextField();
                PriorS43 = new javax.swing.JTextField();
                PriorS44 = new javax.swing.JTextField();
                jLabel37 = new javax.swing.JLabel();
                jLabel38 = new javax.swing.JLabel();
                PriorS51 = new javax.swing.JTextField();
                PriorS52 = new javax.swing.JTextField();
                PriorS53 = new javax.swing.JTextField();
                PriorS54 = new javax.swing.JTextField();
                PriorS15 = new javax.swing.JTextField();
                PriorS25 = new javax.swing.JTextField();
                PriorS35 = new javax.swing.JTextField();
                PriorS45 = new javax.swing.JTextField();
                PriorS55 = new javax.swing.JTextField();
                jPanel6 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                jLabel27 = new javax.swing.JLabel();
                jLabel22 = new javax.swing.JLabel();
                namaLaptop = new javax.swing.JTextField();
                TotalNilai = new javax.swing.JTextField();
                cbIdLaptop = new javax.swing.JComboBox<>();
                jLabel32 = new javax.swing.JLabel();
                jLabel39 = new javax.swing.JLabel();
                jLabel40 = new javax.swing.JLabel();
                jLabel41 = new javax.swing.JLabel();
                jLabel42 = new javax.swing.JLabel();
                jLabel43 = new javax.swing.JLabel();
                jLabel44 = new javax.swing.JLabel();
                Pane = new javax.swing.JPanel();

                PanelPerhitungan.setBackground(new java.awt.Color(178, 176, 232));

                judul.setBackground(new java.awt.Color(26, 42, 128));
                judul.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
                judul.setForeground(new java.awt.Color(204, 255, 255));
                judul.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                judul.setText(" Hitung Hasil Penilaian Laptop");
                judul.setOpaque(true);

                jPanel1.setBackground(new java.awt.Color(178, 176, 232));
                jPanel1.setPreferredSize(new java.awt.Dimension(1200, 600));

                mulaiHitung.setBackground(new java.awt.Color(26, 42, 128));
                mulaiHitung.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                mulaiHitung.setForeground(new java.awt.Color(255, 255, 255));
                mulaiHitung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Icon Hitung Small.png"))); // NOI18N
                mulaiHitung.setText("HITUNG");
                mulaiHitung.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                mulaiHitungActionPerformed(evt);
                        }
                });

                jPanel2.setBackground(new java.awt.Color(26, 42, 128));
                jPanel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

                jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel2.setForeground(new java.awt.Color(255, 237, 192));
                jLabel2.setText("Matriks Perbandingan Kriteria");

                jLabel3.setForeground(new java.awt.Color(255, 237, 192));
                jLabel3.setText("K1");

                jLabel4.setForeground(new java.awt.Color(255, 237, 192));
                jLabel4.setText("K2");

                jLabel5.setForeground(new java.awt.Color(255, 237, 192));
                jLabel5.setText("K3");

                jLabel6.setForeground(new java.awt.Color(255, 237, 192));
                jLabel6.setText("K4");

                k1k1.setEditable(false);
                k1k1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k1k2.setEditable(false);
                k1k2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k1k3.setEditable(false);
                k1k3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k1k4.setEditable(false);
                k1k4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k2k1.setEditable(false);
                k2k1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k2k2.setEditable(false);
                k2k2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k2k3.setEditable(false);
                k2k3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k2k4.setEditable(false);
                k2k4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k3k1.setEditable(false);
                k3k1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k3k2.setEditable(false);
                k3k2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k3k3.setEditable(false);
                k3k3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k3k4.setEditable(false);
                k3k4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k4k1.setEditable(false);
                k4k1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k4k2.setEditable(false);
                k4k2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k4k3.setEditable(false);
                k4k3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k4k4.setEditable(false);
                k4k4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                jLabel7.setForeground(new java.awt.Color(255, 237, 192));
                jLabel7.setText("K1");

                jLabel8.setForeground(new java.awt.Color(255, 237, 192));
                jLabel8.setText("K2");

                jLabel9.setForeground(new java.awt.Color(255, 237, 192));
                jLabel9.setText("K3");

                jLabel10.setForeground(new java.awt.Color(255, 237, 192));
                jLabel10.setText("K5");

                jLabel33.setForeground(new java.awt.Color(255, 237, 192));
                jLabel33.setText("K5");

                k5k1.setEditable(false);
                k5k1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k5k2.setEditable(false);
                k5k2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k5k3.setEditable(false);
                k5k3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k5k4.setEditable(false);
                k5k4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                k5k4.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                k5k4ActionPerformed(evt);
                        }
                });

                jLabel34.setForeground(new java.awt.Color(255, 237, 192));
                jLabel34.setText("K4");

                k1k5.setEditable(false);
                k1k5.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k2k5.setEditable(false);
                k2k5.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k3k5.setEditable(false);
                k3k5.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k4k5.setEditable(false);
                k4k5.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k5k5.setEditable(false);
                k5k5.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel2)
                                                                                                .addContainerGap(
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(jLabel4)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                .addComponent(k2k1,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                41,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                10,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(k2k2,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                41,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addGap(10, 10, 10)
                                                                                                                                .addComponent(k2k3,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                41,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                .addComponent(k2k4,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                41,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addGap(10, 10, 10))
                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                .addComponent(jLabel3)
                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                                                                false)
                                                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                                                .addComponent(k1k1,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                41,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                                                                                .addComponent(k1k2,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                41,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                                .addGap(10, 10, 10)
                                                                                                                                                                                                .addComponent(k1k3,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                41,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                                                .addGap(15, 15, 15)
                                                                                                                                                                                                .addComponent(jLabel7)
                                                                                                                                                                                                .addGap(38, 38, 38)
                                                                                                                                                                                                .addComponent(jLabel8)
                                                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                                                                                .addComponent(jLabel9)
                                                                                                                                                                                                .addGap(25, 25, 25)))
                                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                                .addComponent(k1k4,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                41,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                                                .addGap(14, 14, 14)
                                                                                                                                                                                                .addComponent(
                                                                                                                                                                                                                jLabel34))))
                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                                .addComponent(jLabel5)
                                                                                                                                                                                .addComponent(jLabel6)
                                                                                                                                                                                .addComponent(jLabel33))
                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                                                                false)
                                                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                                                .addComponent(k3k1,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                41,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                                                                                .addComponent(k3k2,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                41,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                                                .addComponent(k4k1,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                41,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                                                                                .addComponent(k4k2,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                41,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                                                .addComponent(k5k1,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                41,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                                                                                .addComponent(k5k2,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                41,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                                                                                .addGap(10, 10, 10)
                                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                                .addComponent(k3k3,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                41,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                .addComponent(k4k3,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                41,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                .addComponent(k5k3,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                41,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                                .addComponent(k5k4,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                41,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                .addComponent(k3k4,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                41,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                .addComponent(k4k4,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                41,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE)))
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(k1k5,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(13, 13, 13)
                                                                                                                                .addComponent(jLabel10))
                                                                                                                .addComponent(k3k5,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(k4k5,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(k5k5,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(k2k5,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(18, 18, 18)))));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel10)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(k1k5,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(k2k5,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(k3k5,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(k4k5,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(k5k5,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel2)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                .addComponent(jLabel9)
                                                                                                                                                .addComponent(jLabel34,
                                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING))
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                .addComponent(jLabel3)
                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                                                                .addComponent(k1k3,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                .addComponent(k1k2,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                .addComponent(k1k4,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                .addComponent(k1k1,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                                .addComponent(jLabel7)
                                                                                                                                .addComponent(jLabel8)))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(k2k1,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jLabel4)
                                                                                                                .addComponent(k2k2,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(k2k3,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(k2k4,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(k3k1,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jLabel5)
                                                                                                                .addComponent(k3k2,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(k3k3,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(k3k4,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(k4k1,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jLabel6)
                                                                                                                .addComponent(k4k2,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(k4k3,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(k4k4,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(k5k1,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jLabel33)
                                                                                                                .addComponent(k5k2,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(k5k3,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(k5k4,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addContainerGap(15, Short.MAX_VALUE)));

                jPanel3.setBackground(new java.awt.Color(26, 42, 128));
                jPanel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

                jLabel11.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel11.setForeground(new java.awt.Color(255, 237, 192));
                jLabel11.setText("Matriks Normalisasi");

                jLabel12.setForeground(new java.awt.Color(255, 237, 192));
                jLabel12.setText("K1");

                jLabel13.setForeground(new java.awt.Color(255, 237, 192));
                jLabel13.setText("K2");

                jLabel14.setForeground(new java.awt.Color(255, 237, 192));
                jLabel14.setText("K3");

                jLabel15.setForeground(new java.awt.Color(255, 237, 192));
                jLabel15.setText("K4");

                k1k1N.setEditable(false);
                k1k1N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k1k2N.setEditable(false);
                k1k2N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k1k3N.setEditable(false);
                k1k3N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k1k4N.setEditable(false);
                k1k4N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k2k1N.setEditable(false);
                k2k1N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k2k2N.setEditable(false);
                k2k2N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k2k3N.setEditable(false);
                k2k3N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k2k4N.setEditable(false);
                k2k4N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k3k1N.setEditable(false);
                k3k1N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k3k2N.setEditable(false);
                k3k2N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k3k3N.setEditable(false);
                k3k3N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k3k4N.setEditable(false);
                k3k4N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k4k1N.setEditable(false);
                k4k1N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k4k2N.setEditable(false);
                k4k2N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k4k3N.setEditable(false);
                k4k3N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k4k4N.setEditable(false);
                k4k4N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                jLabel16.setForeground(new java.awt.Color(255, 237, 192));
                jLabel16.setText("K1");

                jLabel17.setForeground(new java.awt.Color(255, 237, 192));
                jLabel17.setText("K2");

                jLabel18.setForeground(new java.awt.Color(255, 237, 192));
                jLabel18.setText("K3");

                jLabel19.setForeground(new java.awt.Color(255, 237, 192));
                jLabel19.setText("K4");

                jLabel20.setForeground(new java.awt.Color(255, 237, 192));
                jLabel20.setText("Prioritas");

                Prior1.setEditable(false);
                Prior1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                Prior2.setEditable(false);
                Prior2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                Prior3.setEditable(false);
                Prior3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                Prior4.setEditable(false);
                Prior4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                jLabel35.setForeground(new java.awt.Color(255, 237, 192));
                jLabel35.setText("K5");

                k1k5N.setEditable(false);
                k1k5N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k2k5N.setEditable(false);
                k2k5N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k3k5N.setEditable(false);
                k3k5N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k4k5N.setEditable(false);
                k4k5N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                jLabel36.setForeground(new java.awt.Color(255, 237, 192));
                jLabel36.setText("K5");

                k5k1N.setEditable(false);
                k5k1N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k5k2N.setEditable(false);
                k5k2N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k5k3N.setEditable(false);
                k5k3N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k5k4N.setEditable(false);
                k5k4N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                k5k5N.setEditable(false);
                k5k5N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                Prior5.setEditable(false);
                Prior5.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel3Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel3Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(jPanel3Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(jLabel13)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                .addComponent(k2k1N,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                41,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addGroup(jPanel3Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(jLabel14)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                .addComponent(k3k1N,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                41,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addGroup(jPanel3Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(jLabel15)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                .addComponent(k4k1N,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                41,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addGroup(jPanel3Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(jLabel12)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                .addComponent(k1k1N,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                41,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addGroup(jPanel3Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(29, 29, 29)
                                                                                                                                .addComponent(jLabel16))
                                                                                                                .addGroup(jPanel3Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(jLabel36)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                .addComponent(k5k1N,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                41,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel3Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(k2k2N,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(k3k2N,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(k4k2N,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(k1k2N,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGroup(jPanel3Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(14, 14, 14)
                                                                                                                                .addComponent(jLabel17))
                                                                                                                .addComponent(k5k2N,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel3Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(k1k3N,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(k2k3N,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(k3k3N,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(k4k3N,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGroup(jPanel3Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(11, 11, 11)
                                                                                                                                .addComponent(jLabel18))
                                                                                                                .addComponent(k5k3N,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel3Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(k1k4N,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(k2k4N,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(k3k4N,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(k4k4N,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGroup(jPanel3Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(12, 12, 12)
                                                                                                                                .addComponent(jLabel19))
                                                                                                                .addComponent(k5k4N,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel3Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(k1k5N,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(k2k5N,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(k3k5N,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(k4k5N,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGroup(jPanel3Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(12, 12, 12)
                                                                                                                                .addComponent(jLabel35))
                                                                                                                .addComponent(k5k5N,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel3Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(Prior4,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jLabel20)
                                                                                                                .addComponent(Prior3,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(Prior1,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(Prior2,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(Prior5,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                .addComponent(jLabel11))
                                                                .addContainerGap(18, Short.MAX_VALUE)));
                jPanel3Layout.setVerticalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel11)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(jPanel3Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                .addGroup(jPanel3Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(jPanel3Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(jLabel16)
                                                                                                                                .addGap(5, 5, 5)
                                                                                                                                .addGroup(jPanel3Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                                                .addComponent(k1k1N,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addComponent(jLabel12))
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                .addGroup(jPanel3Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                                                .addComponent(k2k1N,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addComponent(jLabel13))
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                .addGroup(jPanel3Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                                                .addComponent(k3k1N,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addComponent(jLabel14))
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                .addGroup(jPanel3Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                                                .addComponent(k4k1N,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addComponent(jLabel15))
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                .addGroup(jPanel3Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                                                .addComponent(k5k1N,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addComponent(jLabel36)))
                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                jPanel3Layout.createSequentialGroup()
                                                                                                                                                .addComponent(jLabel20)
                                                                                                                                                .addGap(5, 5, 5)
                                                                                                                                                .addComponent(Prior1,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addPreferredGap(
                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                                .addComponent(Prior2,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addPreferredGap(
                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                                .addComponent(Prior3,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addPreferredGap(
                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                                .addComponent(Prior4,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addPreferredGap(
                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                                .addComponent(Prior5,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                .addGroup(jPanel3Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(jLabel17)
                                                                                                                .addGap(5, 5, 5)
                                                                                                                .addComponent(k1k2N,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addPreferredGap(
                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                .addComponent(k2k2N,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addPreferredGap(
                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                .addComponent(k3k2N,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addPreferredGap(
                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                .addComponent(k4k2N,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addPreferredGap(
                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                .addComponent(k5k2N,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                jPanel3Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(jLabel18)
                                                                                                                                .addGap(5, 5, 5)
                                                                                                                                .addComponent(k1k3N,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                .addComponent(k2k3N,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                .addComponent(k3k3N,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                .addComponent(k4k3N,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                .addComponent(k5k3N,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                .addGroup(jPanel3Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel19)
                                                                                                .addGap(5, 5, 5)
                                                                                                .addComponent(k1k4N,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(k2k4N,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(k3k4N,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(k4k4N,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(k5k4N,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel3Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel35)
                                                                                                .addGap(5, 5, 5)
                                                                                                .addComponent(k1k5N,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(k2k5N,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(k3k5N,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(k4k5N,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(k5k5N,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                Simpan.setBackground(new java.awt.Color(26, 42, 128));
                Simpan.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                Simpan.setForeground(new java.awt.Color(255, 255, 255));
                Simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Icon Simpan Small.png"))); // NOI18N
                Simpan.setText("SIMPAN");
                Simpan.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                SimpanActionPerformed(evt);
                        }
                });

                jPanel4.setBackground(new java.awt.Color(26, 42, 128));

                jLabel23.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel23.setForeground(new java.awt.Color(255, 237, 192));
                jLabel23.setText("Prioritas Sub Kriteria Sesuai Kriteria");

                jLabel28.setForeground(new java.awt.Color(255, 237, 192));
                jLabel28.setText("K1");

                jLabel29.setForeground(new java.awt.Color(255, 237, 192));
                jLabel29.setText("K2");

                jLabel30.setForeground(new java.awt.Color(255, 237, 192));
                jLabel30.setText("K3");

                jLabel31.setForeground(new java.awt.Color(255, 237, 192));
                jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel31.setText("K4");

                jLabel21.setForeground(new java.awt.Color(255, 237, 192));
                jLabel21.setText("Prioritas Sub Kriteria");

                PriorS11.setEditable(false);
                PriorS11.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                PriorS12.setEditable(false);
                PriorS12.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                PriorS13.setEditable(false);
                PriorS13.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                PriorS14.setEditable(false);
                PriorS14.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                jLabel24.setForeground(new java.awt.Color(255, 237, 192));
                jLabel24.setText("Prioritas Sub Kriteria");

                PriorS21.setEditable(false);
                PriorS21.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                PriorS22.setEditable(false);
                PriorS22.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                PriorS23.setEditable(false);
                PriorS23.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                PriorS24.setEditable(false);
                PriorS24.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                jLabel25.setForeground(new java.awt.Color(255, 237, 192));
                jLabel25.setText("Prioritas Sub Kriteria");

                PriorS31.setEditable(false);
                PriorS31.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                PriorS32.setEditable(false);
                PriorS32.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                PriorS33.setEditable(false);
                PriorS33.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                PriorS34.setEditable(false);
                PriorS34.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                jLabel26.setForeground(new java.awt.Color(255, 237, 192));
                jLabel26.setText("Prioritas Sub Kriteria");

                PriorS41.setEditable(false);
                PriorS41.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                PriorS42.setEditable(false);
                PriorS42.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                PriorS43.setEditable(false);
                PriorS43.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                PriorS44.setEditable(false);
                PriorS44.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                jLabel37.setForeground(new java.awt.Color(255, 237, 192));
                jLabel37.setText("Prioritas Sub Kriteria");

                jLabel38.setForeground(new java.awt.Color(255, 237, 192));
                jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel38.setText("K5");

                PriorS51.setEditable(false);
                PriorS51.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                PriorS52.setEditable(false);
                PriorS52.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                PriorS53.setEditable(false);
                PriorS53.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                PriorS54.setEditable(false);
                PriorS54.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                PriorS54.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                PriorS54ActionPerformed(evt);
                        }
                });

                PriorS15.setEditable(false);
                PriorS15.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                PriorS15.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                PriorS15ActionPerformed(evt);
                        }
                });

                PriorS25.setEditable(false);
                PriorS25.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                PriorS35.setEditable(false);
                PriorS35.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                PriorS45.setEditable(false);
                PriorS45.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                PriorS55.setEditable(false);
                PriorS55.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel4Layout
                                                                                                .createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addComponent(jLabel23))
                                                                                .addGroup(jPanel4Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(15, 15, 15)
                                                                                                .addComponent(jLabel21)
                                                                                                .addGap(15, 15, 15)
                                                                                                .addComponent(jLabel24)
                                                                                                .addGap(15, 15, 15)
                                                                                                .addComponent(jLabel25)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(jLabel26)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(jLabel37)))
                                                                .addContainerGap(28, Short.MAX_VALUE))
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(PriorS14)
                                                                                .addComponent(PriorS13)
                                                                                .addComponent(PriorS12)
                                                                                .addComponent(PriorS11,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                102,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(PriorS15,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                102,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(jPanel4Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(42, 42, 42)
                                                                                                .addComponent(jLabel28)))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel4Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel4Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(PriorS22)
                                                                                                                .addComponent(PriorS21,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                102,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(PriorS23)
                                                                                                                .addComponent(PriorS24)
                                                                                                                .addComponent(PriorS25,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                102,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addGroup(jPanel4Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(PriorS31,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                102,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(PriorS32,
                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                .addComponent(PriorS33,
                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                .addComponent(PriorS34,
                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                .addComponent(PriorS35,
                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                102,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addGroup(jPanel4Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                false)
                                                                                                                .addComponent(PriorS44)
                                                                                                                .addComponent(PriorS43)
                                                                                                                .addComponent(PriorS42)
                                                                                                                .addComponent(PriorS41,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                102,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(PriorS45,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                102,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addGroup(jPanel4Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                false)
                                                                                                                .addComponent(PriorS52)
                                                                                                                .addComponent(PriorS51)
                                                                                                                .addComponent(PriorS53)
                                                                                                                .addComponent(PriorS54)
                                                                                                                .addComponent(PriorS55,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                102,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                                .addGroup(jPanel4Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(41, 41, 41)
                                                                                                .addComponent(jLabel29)
                                                                                                .addGap(100, 100, 100)
                                                                                                .addComponent(jLabel30)
                                                                                                .addGap(102, 102, 102)
                                                                                                .addComponent(jLabel31)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(jLabel38)
                                                                                                .addGap(58, 58, 58)))));

                jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL,
                                new java.awt.Component[] { PriorS11, PriorS12, PriorS13, PriorS14, PriorS21, PriorS22,
                                                PriorS23,
                                                PriorS24, PriorS31, PriorS32, PriorS33, PriorS34, PriorS41, PriorS42,
                                                PriorS43, PriorS44 });

                jPanel4Layout.setVerticalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel23)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel21)
                                                                                .addComponent(jLabel24)
                                                                                .addComponent(jLabel25)
                                                                                .addComponent(jLabel26)
                                                                                .addComponent(jLabel37))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel29,
                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(jPanel4Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(jLabel28)
                                                                                                .addComponent(jLabel30)
                                                                                                .addComponent(jLabel31)
                                                                                                .addComponent(jLabel38)))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(PriorS11,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                20,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(PriorS21,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                20,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(PriorS31,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                20,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(PriorS41,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                20,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(PriorS51,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                20,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(PriorS12,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(PriorS22,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(PriorS32,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(PriorS42,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(PriorS52,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(PriorS13,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(PriorS23,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(PriorS33,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(PriorS43,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(PriorS53,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(PriorS14,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(PriorS24,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(PriorS34,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(PriorS44,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(PriorS54,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(PriorS15,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(PriorS25,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(PriorS35,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(PriorS45,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(PriorS55,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(24, Short.MAX_VALUE)));

                jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL,
                                new java.awt.Component[] { PriorS11, PriorS12, PriorS13, PriorS14, PriorS21, PriorS22,
                                                PriorS23,
                                                PriorS24, PriorS31, PriorS32, PriorS33, PriorS34, PriorS41, PriorS42,
                                                PriorS43, PriorS44 });

                jPanel6.setBackground(new java.awt.Color(26, 42, 128));

                jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(255, 237, 192));
                jLabel1.setText("ID Laptop");

                jLabel27.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel27.setForeground(new java.awt.Color(255, 237, 192));
                jLabel27.setText("Nama Laptop");

                jLabel22.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel22.setForeground(new java.awt.Color(255, 237, 192));
                jLabel22.setText("Total Penilaian Laptop");

                namaLaptop.setEditable(false);
                namaLaptop.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                namaLaptop.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                namaLaptopActionPerformed(evt);
                        }
                });

                TotalNilai.setEditable(false);
                TotalNilai.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                TotalNilai.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                TotalNilaiActionPerformed(evt);
                        }
                });

                cbIdLaptop.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                cbIdLaptop.addItemListener(new java.awt.event.ItemListener() {
                        public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                cbIdLaptopItemStateChanged(evt);
                        }
                });

                jLabel32.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel32.setForeground(new java.awt.Color(255, 237, 192));
                jLabel32.setText("Pilih Data Laptop");

                javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                jPanel6.setLayout(jPanel6Layout);
                jPanel6Layout.setHorizontalGroup(
                                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel6Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel6Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel32,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                130,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(jPanel6Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel6Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jLabel1,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                130,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGroup(jPanel6Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                false)
                                                                                                                                .addComponent(jLabel27,
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(jLabel22,
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addGroup(jPanel6Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                false)
                                                                                                                .addComponent(namaLaptop)
                                                                                                                .addComponent(TotalNilai)
                                                                                                                .addComponent(cbIdLaptop,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                150,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                jPanel6Layout.setVerticalGroup(
                                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel6Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel32,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                28,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(1, 1, 1)
                                                                .addGroup(jPanel6Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                28,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(cbIdLaptop,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel6Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel27,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                28,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(namaLaptop,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(12, 12, 12)
                                                                .addGroup(jPanel6Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel22,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                28,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(TotalNilai,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(18, Short.MAX_VALUE)));

                jLabel39.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel39.setForeground(new java.awt.Color(22, 65, 53));
                jLabel39.setText("Keterangan:");

                jLabel40.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel40.setForeground(new java.awt.Color(22, 65, 53));
                jLabel40.setText("K1 = Performa");

                jLabel41.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel41.setForeground(new java.awt.Color(22, 65, 53));
                jLabel41.setText("K2 = Harga");

                jLabel42.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel42.setForeground(new java.awt.Color(22, 65, 53));
                jLabel42.setText("K3 = Penyimpanan");

                jLabel43.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel43.setForeground(new java.awt.Color(22, 65, 53));
                jLabel43.setText("K4 = Ketahanan Baterai");

                jLabel44.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel44.setForeground(new java.awt.Color(22, 65, 53));
                jLabel44.setText("K5 = Ram");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(20, 20, 20)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(mulaiHitung,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                100,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(Simpan))
                                                                                .addComponent(jLabel39)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jPanel6,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jLabel40)
                                                                                                                .addComponent(jLabel42)
                                                                                                                .addComponent(jLabel43)
                                                                                                                .addComponent(jLabel44)
                                                                                                                .addComponent(jLabel41))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(jPanel2,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addGap(18, 18, 18)
                                                                                                                                .addComponent(jPanel3,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addComponent(jPanel4,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(mulaiHitung)
                                                                                .addComponent(Simpan))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                false)
                                                                                                                .addComponent(jPanel2,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(jPanel3,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE))
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(jPanel4,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jPanel6,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(jLabel39,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                23,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jLabel40)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jLabel41)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jLabel42)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jLabel43)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jLabel44)))
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                jScrollPane1.setViewportView(jPanel1);

                javax.swing.GroupLayout PanelPerhitunganLayout = new javax.swing.GroupLayout(PanelPerhitungan);
                PanelPerhitungan.setLayout(PanelPerhitunganLayout);
                PanelPerhitunganLayout.setHorizontalGroup(
                                PanelPerhitunganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(judul, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                1006,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE));
                PanelPerhitunganLayout.setVerticalGroup(
                                PanelPerhitunganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(PanelPerhitunganLayout.createSequentialGroup()
                                                                .addComponent(judul,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                62,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPane1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                583,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)));

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setName("dialog3"); // NOI18N

                Pane.setLayout(new java.awt.CardLayout());

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(Pane, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(Pane, javax.swing.GroupLayout.DEFAULT_SIZE, 700,
                                                                Short.MAX_VALUE));

                setSize(new java.awt.Dimension(1031, 708));
                setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents
         // tombol mulai perhitungan

        private void mulaiHitungActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_mulaiHitungActionPerformed
                // Validate that a laptop is selected
                if (cbIdLaptop.getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(null, "Silakan pilih laptop terlebih dahulu!");
                        return;
                }

                try {
                        // Clear cache to ensure fresh data for each calculation
                        subKriteriaPrioritas = null;

                        // Get AHP calculation - using correct method names
                        kriteria.MatriksBerpasangan();
                        kriteria.MatriksNormalisasi();
                        kriteria.MatriksPenjumlahan();

                        // Populate the matrices and calculation results
                        getMatriksK();
                        getMatriksNorK();
                        getPrioritasSub();
                        getAlternatif();
                        getPenilaian();

                        // Enable save button after successful calculation
                        Simpan.setEnabled(true);

                        JOptionPane.showMessageDialog(null,
                                        "Perhitungan AHP berhasil dilakukan untuk " + namaLaptopAlternatif + "!");

                } catch (Exception e) {
                        System.err.println("Error during calculation: " + e);
                        JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat perhitungan: " + e.getMessage());
                        Simpan.setEnabled(false);
                }
        }// GEN-LAST:event_mulaiHitungActionPerformed

        // mendapatkan nama laptop dari id yang dipilih
        private void cbIdLaptopItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_cbIdLaptopItemStateChanged
                // Clear cache and fields when laptop selection changes
                subKriteriaPrioritas = null;
                kosong();

                // Disable save button when selection changes
                Simpan.setEnabled(false);

                if (cbIdLaptop.getSelectedItem() != null) {
                        String sql = "SELECT DISTINCT nama_laptop FROM data_laptop WHERE id_laptop='"
                                        + cbIdLaptop.getSelectedItem().toString() + "';";
                        try {
                                java.sql.Statement stat = conn.createStatement();
                                ResultSet hasil = stat.executeQuery(sql);
                                while (hasil.next()) {
                                        String b = hasil.getString("nama_laptop");
                                        namaLaptop.setText(b);
                                }
                        } catch (SQLException e) {
                                JOptionPane.showMessageDialog(null, e);
                        }
                }
        }// GEN-LAST:event_cbIdLaptopItemStateChanged
         // simpan data

        private void SimpanActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_SimpanActionPerformed
                // Validate input data before saving
                if (noIdAlternatif == null || noIdAlternatif.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Silakan pilih laptop terlebih dahulu!");
                        return;
                }

                if (TotalNilai.getText() == null || TotalNilai.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Silakan hitung hasil penilaian terlebih dahulu!");
                        return;
                }

                try {
                        // Validate that TotalNilai is a valid decimal number
                        double hasilPenilaian = Double.parseDouble(TotalNilai.getText().trim());

                        // Check if record already exists
                        String checkSql = "SELECT COUNT(*) as count FROM seleksi WHERE id_laptop = ?";
                        PreparedStatement checkStat = conn.prepareStatement(checkSql);
                        checkStat.setString(1, noIdAlternatif);
                        ResultSet checkResult = checkStat.executeQuery();
                        checkResult.next();

                        String sql;
                        PreparedStatement stat;

                        if (checkResult.getInt("count") > 0) {
                                // Update existing record
                                sql = "UPDATE seleksi SET nama_laptop = ?, hasil_penilaian = ? WHERE id_laptop = ?";
                                stat = conn.prepareStatement(sql);
                                stat.setString(1, namaLaptopAlternatif);
                                stat.setDouble(2, hasilPenilaian);
                                stat.setString(3, noIdAlternatif);
                        } else {
                                // Insert new record
                                sql = "INSERT INTO seleksi (id_laptop, nama_laptop, hasil_penilaian, ranking) VALUES (?, ?, ?, ?)";
                                stat = conn.prepareStatement(sql);
                                stat.setString(1, noIdAlternatif);
                                stat.setString(2, namaLaptopAlternatif);
                                stat.setDouble(3, hasilPenilaian);
                                stat.setInt(4, 0); // ranking will be calculated later
                        }

                        stat.executeUpdate();

                        // Update rankings for all records
                        updateRankings();

                        JOptionPane.showMessageDialog(null, "DATA berhasil disimpan dengan ID: " + noIdAlternatif);

                        // Disable save button to prevent duplicate entries
                        Simpan.setEnabled(false);
                        kosong();

                } catch (NumberFormatException e) {
                        System.err.println("Error: Invalid number format - " + e);
                        JOptionPane.showMessageDialog(null,
                                        "Format nilai penilaian tidak valid: " + TotalNilai.getText());
                } catch (SQLException e) {
                        System.err.println("Error: " + e);
                        JOptionPane.showMessageDialog(null, "Data gagal disimpan: " + e.getMessage());
                }
        }// GEN-LAST:event_SimpanActionPerformed

        private void k5k4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_k5k4ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_k5k4ActionPerformed

        private void PriorS54ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_PriorS54ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_PriorS54ActionPerformed

        private void PriorS15ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_PriorS15ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_PriorS15ActionPerformed

        private void namaLaptopActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_namaLaptopActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_namaLaptopActionPerformed

        private void TotalNilaiActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_TotalNilaiActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_TotalNilaiActionPerformed

        /**
         * @param args the command line arguments
         */
        public static void main(String args[]) {
                /* Set the Nimbus look and feel */
                // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
                // (optional) ">
                /*
                 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
                 * look and feel.
                 * For details see
                 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
                 */
                try {
                        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                                        .getInstalledLookAndFeels()) {
                                if ("Nimbus".equals(info.getName())) {
                                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                        break;
                                }
                        }
                } catch (ClassNotFoundException ex) {
                        java.util.logging.Logger.getLogger(DialogPerhitunganAHP.class.getName()).log(
                                        java.util.logging.Level.SEVERE,
                                        null, ex);
                } catch (InstantiationException ex) {
                        java.util.logging.Logger.getLogger(DialogPerhitunganAHP.class.getName()).log(
                                        java.util.logging.Level.SEVERE,
                                        null, ex);
                } catch (IllegalAccessException ex) {
                        java.util.logging.Logger.getLogger(DialogPerhitunganAHP.class.getName()).log(
                                        java.util.logging.Level.SEVERE,
                                        null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(DialogPerhitunganAHP.class.getName()).log(
                                        java.util.logging.Level.SEVERE,
                                        null, ex);
                }
                // </editor-fold>
                // </editor-fold>
                // </editor-fold>
                // </editor-fold>

                /* Create and display the dialog */
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                DialogPerhitunganAHP dialog = new DialogPerhitunganAHP(new javax.swing.JFrame(), true);
                                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                                        @Override
                                        public void windowClosing(java.awt.event.WindowEvent e) {
                                                System.exit(0);
                                        }
                                });
                                dialog.setVisible(true);
                        }
                });
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JPanel Pane;
        private javax.swing.JPanel PanelPerhitungan;
        private javax.swing.JTextField Prior1;
        private javax.swing.JTextField Prior2;
        private javax.swing.JTextField Prior3;
        private javax.swing.JTextField Prior4;
        private javax.swing.JTextField Prior5;
        private javax.swing.JTextField PriorS11;
        private javax.swing.JTextField PriorS12;
        private javax.swing.JTextField PriorS13;
        private javax.swing.JTextField PriorS14;
        private javax.swing.JTextField PriorS15;
        private javax.swing.JTextField PriorS21;
        private javax.swing.JTextField PriorS22;
        private javax.swing.JTextField PriorS23;
        private javax.swing.JTextField PriorS24;
        private javax.swing.JTextField PriorS25;
        private javax.swing.JTextField PriorS31;
        private javax.swing.JTextField PriorS32;
        private javax.swing.JTextField PriorS33;
        private javax.swing.JTextField PriorS34;
        private javax.swing.JTextField PriorS35;
        private javax.swing.JTextField PriorS41;
        private javax.swing.JTextField PriorS42;
        private javax.swing.JTextField PriorS43;
        private javax.swing.JTextField PriorS44;
        private javax.swing.JTextField PriorS45;
        private javax.swing.JTextField PriorS51;
        private javax.swing.JTextField PriorS52;
        private javax.swing.JTextField PriorS53;
        private javax.swing.JTextField PriorS54;
        private javax.swing.JTextField PriorS55;
        private javax.swing.JButton Simpan;
        private javax.swing.JTextField TotalNilai;
        private javax.swing.JComboBox<String> cbIdLaptop;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel10;
        private javax.swing.JLabel jLabel11;
        private javax.swing.JLabel jLabel12;
        private javax.swing.JLabel jLabel13;
        private javax.swing.JLabel jLabel14;
        private javax.swing.JLabel jLabel15;
        private javax.swing.JLabel jLabel16;
        private javax.swing.JLabel jLabel17;
        private javax.swing.JLabel jLabel18;
        private javax.swing.JLabel jLabel19;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel20;
        private javax.swing.JLabel jLabel21;
        private javax.swing.JLabel jLabel22;
        private javax.swing.JLabel jLabel23;
        private javax.swing.JLabel jLabel24;
        private javax.swing.JLabel jLabel25;
        private javax.swing.JLabel jLabel26;
        private javax.swing.JLabel jLabel27;
        private javax.swing.JLabel jLabel28;
        private javax.swing.JLabel jLabel29;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel30;
        private javax.swing.JLabel jLabel31;
        private javax.swing.JLabel jLabel32;
        private javax.swing.JLabel jLabel33;
        private javax.swing.JLabel jLabel34;
        private javax.swing.JLabel jLabel35;
        private javax.swing.JLabel jLabel36;
        private javax.swing.JLabel jLabel37;
        private javax.swing.JLabel jLabel38;
        private javax.swing.JLabel jLabel39;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel40;
        private javax.swing.JLabel jLabel41;
        private javax.swing.JLabel jLabel42;
        private javax.swing.JLabel jLabel43;
        private javax.swing.JLabel jLabel44;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JLabel jLabel9;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JPanel jPanel4;
        private javax.swing.JPanel jPanel6;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JLabel judul;
        private javax.swing.JTextField k1k1;
        private javax.swing.JTextField k1k1N;
        private javax.swing.JTextField k1k2;
        private javax.swing.JTextField k1k2N;
        private javax.swing.JTextField k1k3;
        private javax.swing.JTextField k1k3N;
        private javax.swing.JTextField k1k4;
        private javax.swing.JTextField k1k4N;
        private javax.swing.JTextField k1k5;
        private javax.swing.JTextField k1k5N;
        private javax.swing.JTextField k2k1;
        private javax.swing.JTextField k2k1N;
        private javax.swing.JTextField k2k2;
        private javax.swing.JTextField k2k2N;
        private javax.swing.JTextField k2k3;
        private javax.swing.JTextField k2k3N;
        private javax.swing.JTextField k2k4;
        private javax.swing.JTextField k2k4N;
        private javax.swing.JTextField k2k5;
        private javax.swing.JTextField k2k5N;
        private javax.swing.JTextField k3k1;
        private javax.swing.JTextField k3k1N;
        private javax.swing.JTextField k3k2;
        private javax.swing.JTextField k3k2N;
        private javax.swing.JTextField k3k3;
        private javax.swing.JTextField k3k3N;
        private javax.swing.JTextField k3k4;
        private javax.swing.JTextField k3k4N;
        private javax.swing.JTextField k3k5;
        private javax.swing.JTextField k3k5N;
        private javax.swing.JTextField k4k1;
        private javax.swing.JTextField k4k1N;
        private javax.swing.JTextField k4k2;
        private javax.swing.JTextField k4k2N;
        private javax.swing.JTextField k4k3;
        private javax.swing.JTextField k4k3N;
        private javax.swing.JTextField k4k4;
        private javax.swing.JTextField k4k4N;
        private javax.swing.JTextField k4k5;
        private javax.swing.JTextField k4k5N;
        private javax.swing.JTextField k5k1;
        private javax.swing.JTextField k5k1N;
        private javax.swing.JTextField k5k2;
        private javax.swing.JTextField k5k2N;
        private javax.swing.JTextField k5k3;
        private javax.swing.JTextField k5k3N;
        private javax.swing.JTextField k5k4;
        private javax.swing.JTextField k5k4N;
        private javax.swing.JTextField k5k5;
        private javax.swing.JTextField k5k5N;
        private javax.swing.JButton mulaiHitung;
        private javax.swing.JTextField namaLaptop;
        // End of variables declaration//GEN-END:variables

        void show(JRootPane rootPane) {
                throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods,
                                                                               // choose
                                                                               // Tools | Templates.
        }
}
