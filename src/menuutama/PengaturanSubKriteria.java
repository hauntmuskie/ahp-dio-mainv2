
package menuutama;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.Koneksi;

/**
 *
 * @author Siti Mawaddah
 */
public class PengaturanSubKriteria extends javax.swing.JPanel {
        private Connection conn = new Koneksi().connect();
        private DefaultTableModel tabmode;

        /**
         * Creates new form Pengaturan
         */
        public PengaturanSubKriteria() {
                initComponents();
                updateDataTabel();
        }

        protected void kosong() {
                cbSubPerforma1.setSelectedIndex(0);
                cbSubPerforma2.setSelectedIndex(0);
                cbSubPerforma3.setSelectedIndex(0);
                cbSubPerforma4.setSelectedIndex(0);
                cbSubPerforma5.setSelectedIndex(0);
                cbSubHarga1.setSelectedIndex(0);
                cbSubHarga2.setSelectedIndex(0);
                cbSubHarga3.setSelectedIndex(0);
                cbSubHarga4.setSelectedIndex(0);
                cbSubHarga5.setSelectedIndex(0);
                cbSubPenyimpanan1.setSelectedIndex(0);
                cbSubPenyimpanan2.setSelectedIndex(0);
                cbSubPenyimpanan3.setSelectedIndex(0);
                cbSubPenyimpanan4.setSelectedIndex(0);
                cbSubPenyimpanan5.setSelectedIndex(0);
                cbSubBaterai1.setSelectedIndex(0);
                cbSubBaterai2.setSelectedIndex(0);
                cbSubBaterai3.setSelectedIndex(0);
                cbSubBaterai4.setSelectedIndex(0);
                cbSubBaterai5.setSelectedIndex(0);
                cbSubRam1.setSelectedIndex(0);
                cbSubRam2.setSelectedIndex(0);
                cbSubRam3.setSelectedIndex(0);
                cbSubRam4.setSelectedIndex(0);
                cbSubRam5.setSelectedIndex(0);
        }

        protected boolean validateSubKriteriaSelection() {
                // Check if at least one sub-criteria for each main criteria is selected
                boolean isPerformaValid = cbSubPerforma1.getSelectedIndex() != 0
                                || cbSubPerforma2.getSelectedIndex() != 0
                                || cbSubPerforma3.getSelectedIndex() != 0 || cbSubPerforma4.getSelectedIndex() != 0
                                || cbSubPerforma5.getSelectedIndex() != 0;

                boolean isHargaValid = cbSubHarga1.getSelectedIndex() != 0 || cbSubHarga2.getSelectedIndex() != 0
                                || cbSubHarga3.getSelectedIndex() != 0 || cbSubHarga4.getSelectedIndex() != 0
                                || cbSubHarga5.getSelectedIndex() != 0;

                boolean isPenyimpananValid = cbSubPenyimpanan1.getSelectedIndex() != 0
                                || cbSubPenyimpanan2.getSelectedIndex() != 0
                                || cbSubPenyimpanan3.getSelectedIndex() != 0
                                || cbSubPenyimpanan4.getSelectedIndex() != 0
                                || cbSubPenyimpanan5.getSelectedIndex() != 0;

                boolean isBateraiValid = cbSubBaterai1.getSelectedIndex() != 0 || cbSubBaterai2.getSelectedIndex() != 0
                                || cbSubBaterai3.getSelectedIndex() != 0 || cbSubBaterai4.getSelectedIndex() != 0
                                || cbSubBaterai5.getSelectedIndex() != 0;

                boolean isRamValid = cbSubRam1.getSelectedIndex() != 0 || cbSubRam2.getSelectedIndex() != 0
                                || cbSubRam3.getSelectedIndex() != 0 || cbSubRam4.getSelectedIndex() != 0
                                || cbSubRam5.getSelectedIndex() != 0;

                return isPerformaValid && isHargaValid && isPenyimpananValid && isBateraiValid && isRamValid;
        }

        protected String getValidationErrorMessage() {
                StringBuilder message = new StringBuilder("Mohon pilih setidaknya satu sub-kriteria untuk kategori:\n");
                boolean hasError = false;

                boolean isPerformaValid = cbSubPerforma1.getSelectedIndex() != 0
                                || cbSubPerforma2.getSelectedIndex() != 0
                                || cbSubPerforma3.getSelectedIndex() != 0 || cbSubPerforma4.getSelectedIndex() != 0
                                || cbSubPerforma5.getSelectedIndex() != 0;

                boolean isHargaValid = cbSubHarga1.getSelectedIndex() != 0 || cbSubHarga2.getSelectedIndex() != 0
                                || cbSubHarga3.getSelectedIndex() != 0 || cbSubHarga4.getSelectedIndex() != 0
                                || cbSubHarga5.getSelectedIndex() != 0;

                boolean isPenyimpananValid = cbSubPenyimpanan1.getSelectedIndex() != 0
                                || cbSubPenyimpanan2.getSelectedIndex() != 0
                                || cbSubPenyimpanan3.getSelectedIndex() != 0
                                || cbSubPenyimpanan4.getSelectedIndex() != 0
                                || cbSubPenyimpanan5.getSelectedIndex() != 0;

                boolean isBateraiValid = cbSubBaterai1.getSelectedIndex() != 0 || cbSubBaterai2.getSelectedIndex() != 0
                                || cbSubBaterai3.getSelectedIndex() != 0 || cbSubBaterai4.getSelectedIndex() != 0
                                || cbSubBaterai5.getSelectedIndex() != 0;

                boolean isRamValid = cbSubRam1.getSelectedIndex() != 0 || cbSubRam2.getSelectedIndex() != 0
                                || cbSubRam3.getSelectedIndex() != 0 || cbSubRam4.getSelectedIndex() != 0
                                || cbSubRam5.getSelectedIndex() != 0;

                if (!isPerformaValid) {
                        message.append("- Performa\n");
                        hasError = true;
                }
                if (!isHargaValid) {
                        message.append("- Harga\n");
                        hasError = true;
                }
                if (!isPenyimpananValid) {
                        message.append("- Penyimpanan\n");
                        hasError = true;
                }
                if (!isBateraiValid) {
                        message.append("- Ketahanan Baterai\n");
                        hasError = true;
                }
                if (!isRamValid) {
                        message.append("- RAM\n");
                        hasError = true;
                }

                return hasError ? message.toString() : "";
        }

        protected void updateDataTabel() {
                Object[] Baris = {
                                "Kode Kriteria",
                                "Nama Kriteria",
                                "Nama Sub Kriteria",
                                "Kepentingan Sub Kriteria"
                };
                tabmode = new DefaultTableModel(null, Baris);
                tabelSubKriteria.setModel(tabmode);
                String sql = "SELECT * FROM sub_kriteria ORDER BY kd_kriteria, no_sub";
                try {
                        java.sql.Statement stat = conn.createStatement();
                        ResultSet hasil = stat.executeQuery(sql);
                        while (hasil.next()) {
                                String a = hasil.getString("kd_kriteria");
                                String b = hasil.getString("nama_kriteria");
                                String c = hasil.getString("nama_sub_kriteria");
                                String d = hasil.getString("prioritas_kepentingan");

                                String[] data = { a, b, c, d };
                                tabmode.addRow(data);
                        }
                } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, e);
                }
        }

        protected void getDataTabel() {
                String sql = "SELECT nama_sub_kriteria FROM sub_kriteria ORDER BY kd_kriteria, no_sub";
                int n = 1;
                try {
                        java.sql.Statement stat = conn.createStatement();
                        ResultSet hasil = stat.executeQuery(sql);
                        while (hasil.next()) {
                                String a = hasil.getString("nama_sub_kriteria");
                                if (n == 1) {
                                        cbSubPerforma1.setSelectedItem(a);
                                } else if (n == 2) {
                                        cbSubPerforma2.setSelectedItem(a);
                                } else if (n == 3) {
                                        cbSubPerforma3.setSelectedItem(a);
                                } else if (n == 4) {
                                        cbSubPerforma4.setSelectedItem(a);
                                } else if (n == 5) {
                                        cbSubPerforma5.setSelectedItem(a);
                                } else if (n == 6) {
                                        cbSubHarga1.setSelectedItem(a);
                                } else if (n == 7) {
                                        cbSubHarga2.setSelectedItem(a);
                                } else if (n == 8) {
                                        cbSubHarga3.setSelectedItem(a);
                                } else if (n == 9) {
                                        cbSubHarga4.setSelectedItem(a);
                                } else if (n == 10) {
                                        cbSubHarga5.setSelectedItem(a);
                                } else if (n == 11) {
                                        cbSubPenyimpanan1.setSelectedItem(a);
                                } else if (n == 12) {
                                        cbSubPenyimpanan2.setSelectedItem(a);
                                } else if (n == 13) {
                                        cbSubPenyimpanan3.setSelectedItem(a);
                                } else if (n == 14) {
                                        cbSubPenyimpanan4.setSelectedItem(a);
                                } else if (n == 15) {
                                        cbSubPenyimpanan5.setSelectedItem(a);
                                } else if (n == 16) {
                                        cbSubBaterai1.setSelectedItem(a);
                                } else if (n == 17) {
                                        cbSubBaterai2.setSelectedItem(a);
                                } else if (n == 18) {
                                        cbSubBaterai3.setSelectedItem(a);
                                } else if (n == 19) {
                                        cbSubBaterai4.setSelectedItem(a);
                                } else if (n == 20) {
                                        cbSubBaterai5.setSelectedItem(a);
                                } else if (n == 21) {
                                        cbSubRam1.setSelectedItem(a);
                                } else if (n == 22) {
                                        cbSubRam2.setSelectedItem(a);
                                } else if (n == 23) {
                                        cbSubRam3.setSelectedItem(a);
                                } else if (n == 24) {
                                        cbSubRam4.setSelectedItem(a);
                                } else if (n == 25) {
                                        cbSubRam5.setSelectedItem(a);
                                }
                                n++;
                        }
                } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, e);
                }
        }

        // masukan data subkriteria
        protected void insertDataSubKriteria() {
                try {
                        int n = 1, nPerforma = 1, nHarga = 1, nPenyimpanan = 1, nDayaTahanBaterai = 1, nRam = 1, i = 1;
                        do {
                                String kepentingan;
                                double bobotPrioritas;
                                String sql = "INSERT INTO sub_kriteria (no_sub, kd_kriteria, nama_kriteria, nama_sub_kriteria, prioritas_kepentingan, bobot_prioritas) VALUES (?, ?, ?, ?, ?, ?)";
                                PreparedStatement stat = conn.prepareStatement(sql);
                                java.sql.Statement statCek = conn.createStatement();
                                String sqlSub = "SELECT kd_kriteria, nama_kriteria FROM kriteria";
                                String sqlPerforma = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Performa'";
                                String sqlHarga = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Harga'";
                                String sqlPenyimpanan = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Penyimpanan'";
                                String sqlDayaTahanBaterai = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Ketahanan Baterai'";
                                String sqlRam = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='RAM'";
                                ResultSet hasil = statCek.executeQuery(sqlSub);
                                if (n == 1) {
                                        hasil = statCek.executeQuery(sqlPerforma);
                                        stat.setString(1, Integer.toString(i));
                                        i++;
                                        while (hasil.next()) {
                                                String a = hasil.getString("kd_kriteria");
                                                String b = hasil.getString("nama_kriteria");
                                                stat.setString(2, a);
                                                stat.setString(3, b);
                                        }
                                        if (nPerforma == 1) {
                                                stat.setString(4, cbSubPerforma1.getSelectedItem().toString());
                                                kepentingan = "Sangat Penting ke-1";
                                                bobotPrioritas = 0.503;
                                        } else if (nPerforma == 2) {
                                                stat.setString(4, cbSubPerforma2.getSelectedItem().toString());
                                                kepentingan = "Penting ke-2";
                                                bobotPrioritas = 0.260;
                                        } else if (nPerforma == 3) {
                                                stat.setString(4, cbSubPerforma3.getSelectedItem().toString());
                                                kepentingan = "Cukup Penting ke-3";
                                                bobotPrioritas = 0.134;
                                        } else if (nPerforma == 4) {
                                                stat.setString(4, cbSubPerforma4.getSelectedItem().toString());
                                                kepentingan = "Biasa ke-4";
                                                bobotPrioritas = 0.068;
                                        } else {
                                                stat.setString(4, cbSubPerforma5.getSelectedItem().toString());
                                                kepentingan = "Kurang Penting ke-5";
                                                bobotPrioritas = 0.035;
                                                n++;
                                        }
                                        stat.setString(5, kepentingan);
                                        stat.setDouble(6, bobotPrioritas);
                                        stat.executeUpdate();
                                        nPerforma++;
                                } else if (n == 2) {
                                        hasil = statCek.executeQuery(sqlHarga);
                                        stat.setString(1, Integer.toString(i));
                                        i++;
                                        while (hasil.next()) {
                                                String a = hasil.getString("kd_kriteria");
                                                String b = hasil.getString("nama_kriteria");
                                                stat.setString(2, a);
                                                stat.setString(3, b);
                                        }
                                        if (nHarga == 1) {
                                                stat.setString(4, cbSubHarga1.getSelectedItem().toString());
                                                kepentingan = "Sangat Penting ke-1";
                                                bobotPrioritas = 0.445;
                                        } else if (nHarga == 2) {
                                                stat.setString(4, cbSubHarga2.getSelectedItem().toString());
                                                kepentingan = "Penting ke-2";
                                                bobotPrioritas = 0.292;
                                        } else if (nHarga == 3) {
                                                stat.setString(4, cbSubHarga3.getSelectedItem().toString());
                                                kepentingan = "Cukup Penting ke-3";
                                                bobotPrioritas = 0.133;
                                        } else if (nHarga == 4) {
                                                stat.setString(4, cbSubHarga4.getSelectedItem().toString());
                                                kepentingan = "Biasa ke-4";
                                                bobotPrioritas = 0.096;
                                        } else {
                                                stat.setString(4, cbSubHarga5.getSelectedItem().toString());
                                                kepentingan = "Kurang Penting ke-5";
                                                bobotPrioritas = 0.035;
                                                n++;
                                        }
                                        stat.setString(5, kepentingan);
                                        stat.setDouble(6, bobotPrioritas);
                                        stat.executeUpdate();
                                        nHarga++;
                                } else if (n == 3) {
                                        hasil = statCek.executeQuery(sqlPenyimpanan);
                                        stat.setString(1, Integer.toString(i));
                                        i++;
                                        while (hasil.next()) {
                                                String a = hasil.getString("kd_kriteria");
                                                String b = hasil.getString("nama_kriteria");
                                                stat.setString(2, a);
                                                stat.setString(3, b);
                                        }
                                        if (nPenyimpanan == 1) {
                                                stat.setString(4, cbSubPenyimpanan1.getSelectedItem().toString());
                                                kepentingan = "Sangat Penting ke-1";
                                                bobotPrioritas = 0.408;
                                        } else if (nPenyimpanan == 2) {
                                                stat.setString(4, cbSubPenyimpanan2.getSelectedItem().toString());
                                                kepentingan = "Penting ke-2";
                                                bobotPrioritas = 0.251;
                                        } else if (nPenyimpanan == 3) {
                                                stat.setString(4, cbSubPenyimpanan3.getSelectedItem().toString());
                                                kepentingan = "Cukup Penting ke-3";
                                                bobotPrioritas = 0.167;
                                        } else if (nPenyimpanan == 4) {
                                                stat.setString(4, cbSubPenyimpanan4.getSelectedItem().toString());
                                                kepentingan = "Biasa ke-4";
                                                bobotPrioritas = 0.109;
                                        } else {
                                                stat.setString(4, cbSubPenyimpanan5.getSelectedItem().toString());
                                                kepentingan = "Kurang Penting ke-5";
                                                bobotPrioritas = 0.066;
                                                n++;
                                        }
                                        stat.setString(5, kepentingan);
                                        stat.setDouble(6, bobotPrioritas);
                                        stat.executeUpdate();
                                        nPenyimpanan++;
                                } else if (n == 4) {
                                        hasil = statCek.executeQuery(sqlDayaTahanBaterai);
                                        stat.setString(1, Integer.toString(i));
                                        i++;
                                        while (hasil.next()) {
                                                String a = hasil.getString("kd_kriteria");
                                                String b = hasil.getString("nama_kriteria");
                                                stat.setString(2, a);
                                                stat.setString(3, b);
                                        }
                                        if (nDayaTahanBaterai == 1) {
                                                stat.setString(4, cbSubBaterai1.getSelectedItem().toString());
                                                kepentingan = "Sangat Penting ke-1";
                                                bobotPrioritas = 0.454;
                                        } else if (nDayaTahanBaterai == 2) {
                                                stat.setString(4, cbSubBaterai2.getSelectedItem().toString());
                                                kepentingan = "Penting ke-2";
                                                bobotPrioritas = 0.267;
                                        } else if (nDayaTahanBaterai == 3) {
                                                stat.setString(4, cbSubBaterai3.getSelectedItem().toString());
                                                kepentingan = "Cukup Penting ke-3";
                                                bobotPrioritas = 0.149;
                                        } else if (nDayaTahanBaterai == 4) {
                                                stat.setString(4, cbSubBaterai4.getSelectedItem().toString());
                                                kepentingan = "Biasa ke-4";
                                                bobotPrioritas = 0.082;
                                        } else {
                                                stat.setString(4, cbSubBaterai5.getSelectedItem().toString());
                                                kepentingan = "Kurang Penting ke-5";
                                                bobotPrioritas = 0.049;
                                                n++;
                                        }
                                        stat.setString(5, kepentingan);
                                        stat.setDouble(6, bobotPrioritas);
                                        stat.executeUpdate();
                                        nDayaTahanBaterai++;
                                } else {
                                        hasil = statCek.executeQuery(sqlRam);
                                        stat.setString(1, Integer.toString(i));
                                        i++;
                                        while (hasil.next()) {
                                                String a = hasil.getString("kd_kriteria");
                                                String b = hasil.getString("nama_kriteria");
                                                stat.setString(2, a);
                                                stat.setString(3, b);
                                        }
                                        if (nRam == 1) {
                                                stat.setString(4, cbSubRam1.getSelectedItem().toString());
                                                kepentingan = "Sangat Penting ke-1";
                                                bobotPrioritas = 0.416;
                                        } else if (nRam == 2) {
                                                stat.setString(4, cbSubRam2.getSelectedItem().toString());
                                                kepentingan = "Penting ke-2";
                                                bobotPrioritas = 0.262;
                                        } else if (nRam == 3) {
                                                stat.setString(4, cbSubRam3.getSelectedItem().toString());
                                                kepentingan = "Cukup Penting ke-3";
                                                bobotPrioritas = 0.161;
                                        } else if (nRam == 4) {
                                                stat.setString(4, cbSubRam4.getSelectedItem().toString());
                                                kepentingan = "Biasa ke-4";
                                                bobotPrioritas = 0.099;
                                        } else {
                                                stat.setString(4, cbSubRam5.getSelectedItem().toString());
                                                kepentingan = "Kurang Penting ke-5";
                                                bobotPrioritas = 0.062;
                                                n++;
                                        }
                                        stat.setString(5, kepentingan);
                                        stat.setDouble(6, bobotPrioritas);
                                        stat.executeUpdate();
                                        nRam++;
                                }
                        } while (n <= 5);
                        JOptionPane.showMessageDialog(null, "DATA Berhasil Disimpan");
                        kosong();
                        updateDataTabel();
                } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Data Gagal Disimpan " + e);
                }
        }

        protected void hapusDataSubKriteria() {
                int ok = JOptionPane.showConfirmDialog(null, "Hapus semua data sub-kriteria?", "Konfirmasi Dialog",
                                JOptionPane.YES_NO_OPTION);
                if (ok == 0) {
                        String sql = "DELETE FROM sub_kriteria";
                        try {
                                PreparedStatement stat = conn.prepareStatement(sql);
                                stat.executeUpdate();
                                JOptionPane.showMessageDialog(null, "Data Berhasil diHapus ");
                                kosong();
                                updateDataTabel();
                        } catch (SQLException e) {
                                JOptionPane.showMessageDialog(null, "Data Gagal diHapus " + e);
                        }
                }
        }

        protected void editDataSubKriteria() {
                try {
                        int n = 1, nPerforma = 1, nHarga = 1, nPenyimpanan = 1, nDayaTahanBaterai = 1, nRam = 1, i = 1;
                        do {
                                String kepentingan;
                                double bobotPrioritas;
                                String sql = "UPDATE sub_kriteria set kd_kriteria=?, nama_kriteria=?, nama_sub_kriteria=?, prioritas_kepentingan=?, bobot_prioritas=? WHERE no_sub=?";
                                PreparedStatement stat = conn.prepareStatement(sql);
                                java.sql.Statement statCek = conn.createStatement();
                                String sqlSub = "SELECT kd_kriteria, nama_kriteria FROM kriteria";
                                String sqlPerforma = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Performa'";
                                String sqlHarga = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Harga'";
                                String sqlPenyimpanan = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Penyimpanan'";
                                String sqlDayaTahanBaterai = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Ketahanan Baterai'";
                                String sqlRam = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='RAM'";
                                ResultSet hasil = statCek.executeQuery(sqlSub);
                                if (n == 1) {
                                        hasil = statCek.executeQuery(sqlPerforma);
                                        stat.setString(6, Integer.toString(i));
                                        i++;
                                        while (hasil.next()) {
                                                String a = hasil.getString("kd_kriteria");
                                                String b = hasil.getString("nama_kriteria");
                                                stat.setString(1, a);
                                                stat.setString(2, b);
                                        }
                                        if (nPerforma == 1) {
                                                stat.setString(3, cbSubPerforma1.getSelectedItem().toString());
                                                kepentingan = "Sangat Penting ke-1";
                                                bobotPrioritas = 0.503;
                                        } else if (nPerforma == 2) {
                                                stat.setString(3, cbSubPerforma2.getSelectedItem().toString());
                                                kepentingan = "Penting ke-2";
                                                bobotPrioritas = 0.260;
                                        } else if (nPerforma == 3) {
                                                stat.setString(3, cbSubPerforma3.getSelectedItem().toString());
                                                kepentingan = "Cukup Penting ke-3";
                                                bobotPrioritas = 0.134;
                                        } else if (nPerforma == 4) {
                                                stat.setString(3, cbSubPerforma4.getSelectedItem().toString());
                                                kepentingan = "Biasa ke-4";
                                                bobotPrioritas = 0.068;
                                        } else {
                                                stat.setString(3, cbSubPerforma5.getSelectedItem().toString());
                                                kepentingan = "Kurang Penting ke-5";
                                                bobotPrioritas = 0.035;
                                                n++;
                                        }
                                        stat.setString(4, kepentingan);
                                        stat.setDouble(5, bobotPrioritas);
                                        stat.executeUpdate();
                                        nPerforma++;
                                } else if (n == 2) {
                                        hasil = statCek.executeQuery(sqlHarga);
                                        stat.setString(6, Integer.toString(i));
                                        i++;
                                        while (hasil.next()) {
                                                String a = hasil.getString("kd_kriteria");
                                                String b = hasil.getString("nama_kriteria");
                                                stat.setString(1, a);
                                                stat.setString(2, b);
                                        }
                                        if (nHarga == 1) {
                                                stat.setString(3, cbSubHarga1.getSelectedItem().toString());
                                                kepentingan = "Sangat Penting ke-1";
                                                bobotPrioritas = 0.445;
                                        } else if (nHarga == 2) {
                                                stat.setString(3, cbSubHarga2.getSelectedItem().toString());
                                                kepentingan = "Penting ke-2";
                                                bobotPrioritas = 0.292;
                                        } else if (nHarga == 3) {
                                                stat.setString(3, cbSubHarga3.getSelectedItem().toString());
                                                kepentingan = "Cukup Penting ke-3";
                                                bobotPrioritas = 0.133;
                                        } else if (nHarga == 4) {
                                                stat.setString(3, cbSubHarga4.getSelectedItem().toString());
                                                kepentingan = "Biasa ke-4";
                                                bobotPrioritas = 0.096;
                                        } else {
                                                stat.setString(3, cbSubHarga5.getSelectedItem().toString());
                                                kepentingan = "Kurang Penting ke-5";
                                                bobotPrioritas = 0.035;
                                                n++;
                                        }
                                        stat.setString(4, kepentingan);
                                        stat.setDouble(5, bobotPrioritas);
                                        stat.executeUpdate();
                                        nHarga++;
                                } else if (n == 3) {
                                        hasil = statCek.executeQuery(sqlPenyimpanan);
                                        stat.setString(6, Integer.toString(i));
                                        i++;
                                        while (hasil.next()) {
                                                String a = hasil.getString("kd_kriteria");
                                                String b = hasil.getString("nama_kriteria");
                                                stat.setString(1, a);
                                                stat.setString(2, b);
                                        }
                                        if (nPenyimpanan == 1) {
                                                stat.setString(3, cbSubPenyimpanan1.getSelectedItem().toString());
                                                kepentingan = "Sangat Penting ke-1";
                                                bobotPrioritas = 0.408;
                                        } else if (nPenyimpanan == 2) {
                                                stat.setString(3, cbSubPenyimpanan2.getSelectedItem().toString());
                                                kepentingan = "Penting ke-2";
                                                bobotPrioritas = 0.251;
                                        } else if (nPenyimpanan == 3) {
                                                stat.setString(3, cbSubPenyimpanan3.getSelectedItem().toString());
                                                kepentingan = "Cukup Penting ke-3";
                                                bobotPrioritas = 0.167;
                                        } else if (nPenyimpanan == 4) {
                                                stat.setString(3, cbSubPenyimpanan4.getSelectedItem().toString());
                                                kepentingan = "Biasa ke-4";
                                                bobotPrioritas = 0.109;
                                        } else {
                                                stat.setString(3, cbSubPenyimpanan5.getSelectedItem().toString());
                                                kepentingan = "Kurang Penting ke-5";
                                                bobotPrioritas = 0.066;
                                                n++;
                                        }
                                        stat.setString(4, kepentingan);
                                        stat.setDouble(5, bobotPrioritas);
                                        stat.executeUpdate();
                                        nPenyimpanan++;
                                } else if (n == 4) {
                                        hasil = statCek.executeQuery(sqlDayaTahanBaterai);
                                        stat.setString(6, Integer.toString(i));
                                        i++;
                                        while (hasil.next()) {
                                                String a = hasil.getString("kd_kriteria");
                                                String b = hasil.getString("nama_kriteria");
                                                stat.setString(1, a);
                                                stat.setString(2, b);
                                        }
                                        if (nDayaTahanBaterai == 1) {
                                                stat.setString(3, cbSubBaterai1.getSelectedItem().toString());
                                                kepentingan = "Sangat Penting ke-1";
                                                bobotPrioritas = 0.454;
                                        } else if (nDayaTahanBaterai == 2) {
                                                stat.setString(3, cbSubBaterai2.getSelectedItem().toString());
                                                kepentingan = "Penting ke-2";
                                                bobotPrioritas = 0.267;
                                        } else if (nDayaTahanBaterai == 3) {
                                                stat.setString(3, cbSubBaterai3.getSelectedItem().toString());
                                                kepentingan = "Cukup Penting ke-3";
                                                bobotPrioritas = 0.149;
                                        } else if (nDayaTahanBaterai == 4) {
                                                stat.setString(3, cbSubBaterai4.getSelectedItem().toString());
                                                kepentingan = "Biasa ke-4";
                                                bobotPrioritas = 0.082;
                                        } else {
                                                stat.setString(3, cbSubBaterai5.getSelectedItem().toString());
                                                kepentingan = "Kurang Penting ke-5";
                                                bobotPrioritas = 0.049;
                                                n++;
                                        }
                                        stat.setString(4, kepentingan);
                                        stat.setDouble(5, bobotPrioritas);
                                        stat.executeUpdate();
                                        nDayaTahanBaterai++;
                                } else {
                                        hasil = statCek.executeQuery(sqlRam);
                                        stat.setString(6, Integer.toString(i));
                                        i++;
                                        while (hasil.next()) {
                                                String a = hasil.getString("kd_kriteria");
                                                String b = hasil.getString("nama_kriteria");
                                                stat.setString(1, a);
                                                stat.setString(2, b);
                                        }
                                        if (nRam == 1) {
                                                stat.setString(3, cbSubRam1.getSelectedItem().toString());
                                                kepentingan = "Sangat Penting ke-1";
                                                bobotPrioritas = 0.416;
                                        } else if (nRam == 2) {
                                                stat.setString(3, cbSubRam2.getSelectedItem().toString());
                                                kepentingan = "Penting ke-2";
                                                bobotPrioritas = 0.262;
                                        } else if (nRam == 3) {
                                                stat.setString(3, cbSubRam3.getSelectedItem().toString());
                                                kepentingan = "Cukup Penting ke-3";
                                                bobotPrioritas = 0.161;
                                        } else if (nRam == 4) {
                                                stat.setString(3, cbSubRam4.getSelectedItem().toString());
                                                kepentingan = "Biasa ke-4";
                                                bobotPrioritas = 0.099;
                                        } else {
                                                stat.setString(3, cbSubRam5.getSelectedItem().toString());
                                                kepentingan = "Kurang Penting ke-5";
                                                bobotPrioritas = 0.062;
                                                n++;
                                        }
                                        stat.setString(4, kepentingan);
                                        stat.setDouble(5, bobotPrioritas);
                                        stat.executeUpdate();
                                        nRam++;
                                }
                        } while (n <= 5);
                        JOptionPane.showMessageDialog(null, "DATA Berhasil Diubah");
                        kosong();
                        updateDataTabel();
                } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Data Gagal Diubah " + e);
                }
        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                judul = new javax.swing.JLabel();
                jPanel1 = new javax.swing.JPanel();
                jScrollPane1 = new javax.swing.JScrollPane();
                tabelSubKriteria = new javax.swing.JTable();
                tombolSimpan = new javax.swing.JButton();
                tombolEdit = new javax.swing.JButton();
                tombolHapus = new javax.swing.JButton();
                jLabel2 = new javax.swing.JLabel();
                jScrollPane2 = new javax.swing.JScrollPane();
                jPanel2 = new javax.swing.JPanel();
                jPanel3 = new javax.swing.JPanel();
                jLabel12 = new javax.swing.JLabel();
                cbSubPerforma1 = new javax.swing.JComboBox<>();
                jLabel13 = new javax.swing.JLabel();
                cbSubPerforma2 = new javax.swing.JComboBox<>();
                cbSubPerforma3 = new javax.swing.JComboBox<>();
                jLabel14 = new javax.swing.JLabel();
                jLabel15 = new javax.swing.JLabel();
                cbSubPerforma4 = new javax.swing.JComboBox<>();
                jLabel16 = new javax.swing.JLabel();
                cbSubPerforma5 = new javax.swing.JComboBox<>();
                jPanel4 = new javax.swing.JPanel();
                jLabel17 = new javax.swing.JLabel();
                cbSubHarga1 = new javax.swing.JComboBox<>();
                jLabel18 = new javax.swing.JLabel();
                jLabel19 = new javax.swing.JLabel();
                jLabel20 = new javax.swing.JLabel();
                jLabel21 = new javax.swing.JLabel();
                cbSubHarga2 = new javax.swing.JComboBox<>();
                cbSubHarga3 = new javax.swing.JComboBox<>();
                cbSubHarga4 = new javax.swing.JComboBox<>();
                cbSubHarga5 = new javax.swing.JComboBox<>();
                jPanel5 = new javax.swing.JPanel();
                jLabel22 = new javax.swing.JLabel();
                cbSubPenyimpanan1 = new javax.swing.JComboBox<>();
                jLabel23 = new javax.swing.JLabel();
                cbSubPenyimpanan2 = new javax.swing.JComboBox<>();
                cbSubPenyimpanan3 = new javax.swing.JComboBox<>();
                jLabel24 = new javax.swing.JLabel();
                jLabel27 = new javax.swing.JLabel();
                cbSubPenyimpanan4 = new javax.swing.JComboBox<>();
                jLabel28 = new javax.swing.JLabel();
                cbSubPenyimpanan5 = new javax.swing.JComboBox<>();
                jPanel8 = new javax.swing.JPanel();
                jLabel29 = new javax.swing.JLabel();
                cbSubBaterai1 = new javax.swing.JComboBox<>();
                jLabel30 = new javax.swing.JLabel();
                cbSubBaterai2 = new javax.swing.JComboBox<>();
                cbSubBaterai3 = new javax.swing.JComboBox<>();
                jLabel37 = new javax.swing.JLabel();
                cbSubBaterai4 = new javax.swing.JComboBox<>();
                jLabel38 = new javax.swing.JLabel();
                jLabel39 = new javax.swing.JLabel();
                cbSubBaterai5 = new javax.swing.JComboBox<>();
                jPanel9 = new javax.swing.JPanel();
                jLabel33 = new javax.swing.JLabel();
                cbSubRam1 = new javax.swing.JComboBox<>();
                jLabel34 = new javax.swing.JLabel();
                cbSubRam2 = new javax.swing.JComboBox<>();
                cbSubRam3 = new javax.swing.JComboBox<>();
                jLabel35 = new javax.swing.JLabel();
                cbSubRam4 = new javax.swing.JComboBox<>();
                jLabel36 = new javax.swing.JLabel();
                cbSubRam5 = new javax.swing.JComboBox<>();
                jLabel40 = new javax.swing.JLabel();

                setBackground(new java.awt.Color(255, 237, 192));
                setPreferredSize(new java.awt.Dimension(990, 700));

                judul.setBackground(new java.awt.Color(26, 42, 128));
                judul.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
                judul.setForeground(new java.awt.Color(204, 255, 255));
                judul.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                judul.setText("  Pengaturan Bobot Kepentingan Sub Kriteria");
                judul.setOpaque(true);

                jPanel1.setBackground(new java.awt.Color(255, 237, 192));
                jPanel1.setPreferredSize(new java.awt.Dimension(990, 620));

                tabelSubKriteria.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                tabelSubKriteria.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null }
                                },
                                new String[] {
                                                "Kode Kriteria", "Nama Kriteria", "Nama Sub Kriteria",
                                                "Kepentingan Sub Kriteria"
                                }));
                tabelSubKriteria.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tabelSubKriteriaMouseClicked(evt);
                        }
                });
                jScrollPane1.setViewportView(tabelSubKriteria);

                tombolSimpan.setBackground(new java.awt.Color(26, 42, 128));
                tombolSimpan.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                tombolSimpan.setForeground(new java.awt.Color(255, 237, 192));
                tombolSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Icon Simpan Small.png"))); // NOI18N
                tombolSimpan.setText("SIMPAN");
                tombolSimpan.setBorder(null);
                tombolSimpan.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                tombolSimpanActionPerformed(evt);
                        }
                });

                tombolEdit.setBackground(new java.awt.Color(26, 42, 128));
                tombolEdit.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                tombolEdit.setForeground(new java.awt.Color(255, 237, 192));
                tombolEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Icon Ubah Small.png"))); // NOI18N
                tombolEdit.setText("UBAH");
                tombolEdit.setBorder(null);
                tombolEdit.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                tombolEditActionPerformed(evt);
                        }
                });

                tombolHapus.setBackground(new java.awt.Color(26, 42, 128));
                tombolHapus.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                tombolHapus.setForeground(new java.awt.Color(255, 237, 192));
                tombolHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Icon Hapus Small.png"))); // NOI18N
                tombolHapus.setText("HAPUS");
                tombolHapus.setBorder(null);
                tombolHapus.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                tombolHapusActionPerformed(evt);
                        }
                });

                jLabel2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
                jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                jLabel2.setText("Catatan : Edit data, klik data pada tabel terlebih dahulu");

                jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                jPanel3.setBackground(new java.awt.Color(26, 42, 128));
                jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(
                                javax.swing.BorderFactory.createTitledBorder(null, "Performa\n",
                                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                                new java.awt.Font("SansSerif", 1, 12),
                                                new java.awt.Color(255, 237, 192)),
                                "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12),
                                new java.awt.Color(255, 255, 255))); // NOI18N

                jLabel12.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel12.setForeground(new java.awt.Color(255, 237, 192));
                jLabel12.setText("Performa Sangat Penting ke-1");

                cbSubPerforma1.setModel(
                                new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Performa -",
                                                "High-End", "Mid-Range", "Entry-Level", "Basic\t", "Low-End" }));

                jLabel13.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel13.setForeground(new java.awt.Color(255, 237, 192));
                jLabel13.setText("Performa Penting ke-2");

                cbSubPerforma2.setModel(
                                new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Performa -",
                                                "High-End", "Mid-Range", "Entry-Level", "Basic\t", "Low-End" }));

                cbSubPerforma3.setModel(
                                new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Performa -",
                                                "High-End", "Mid-Range", "Entry-Level", "Basic\t", "Low-End" }));

                jLabel14.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel14.setForeground(new java.awt.Color(255, 237, 192));
                jLabel14.setText("Performa Cukup Penting ke-3");

                jLabel15.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel15.setForeground(new java.awt.Color(255, 237, 192));
                jLabel15.setText("Performa Biasa ke-4");

                cbSubPerforma4.setModel(
                                new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Performa -",
                                                "High-End", "Mid-Range", "Entry-Level", "Basic\t", "Low-End" }));

                jLabel16.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel16.setForeground(new java.awt.Color(255, 237, 192));
                jLabel16.setText("Performa Kurang Penting ke-5");

                cbSubPerforma5.setModel(
                                new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Performa -",
                                                "High-End", "Mid-Range", "Entry-Level", "Basic\t", "Low-End" }));

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel12)
                                                                                .addComponent(jLabel13))
                                                                .addGap(51, 51, 51)
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(cbSubPerforma2, 0,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(cbSubPerforma1, 0,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)))
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel16)
                                                                                .addComponent(jLabel15)
                                                                                .addComponent(jLabel14))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(jPanel3Layout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(cbSubPerforma4, 0,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(cbSubPerforma5, 0,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(cbSubPerforma3, 0,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))));
                jPanel3Layout.setVerticalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel12)
                                                                                .addComponent(cbSubPerforma1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel13)
                                                                                .addComponent(cbSubPerforma2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel14)
                                                                                .addComponent(cbSubPerforma3,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel15)
                                                                                .addComponent(cbSubPerforma4,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel16)
                                                                                .addComponent(cbSubPerforma5,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))));

                jPanel4.setBackground(new java.awt.Color(26, 42, 128));
                jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(
                                javax.swing.BorderFactory.createTitledBorder(null, "Harga",
                                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                                new java.awt.Font("SansSerif", 1, 12),
                                                new java.awt.Color(255, 237, 192)),
                                "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12),
                                new java.awt.Color(255, 255, 255))); // NOI18N

                jLabel17.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel17.setForeground(new java.awt.Color(255, 237, 192));
                jLabel17.setText("Harga Sangat Penting ke-1");

                cbSubHarga1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
                                "- Pilih Sub Kriteria Harga -",
                                "Sangat Terjangkau", "Terjangkau ", "Normal ", "Mahal ", "Sangat Mahal ", " " }));

                jLabel18.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel18.setForeground(new java.awt.Color(255, 237, 192));
                jLabel18.setText("Harga Penting ke-2");

                jLabel19.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel19.setForeground(new java.awt.Color(255, 237, 192));
                jLabel19.setText("Harga Cukup Penting ke-3");

                jLabel20.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel20.setForeground(new java.awt.Color(255, 237, 192));
                jLabel20.setText("Harga Biasa ke-4");

                jLabel21.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel21.setForeground(new java.awt.Color(255, 237, 192));
                jLabel21.setText("Harga Kurang Penting ke-5");

                cbSubHarga2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
                                "- Pilih Sub Kriteria Harga -",
                                "Sangat Terjangkau", "Terjangkau ", "Normal ", "Mahal ", "Sangat Mahal ", " " }));

                cbSubHarga3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
                                "- Pilih Sub Kriteria Harga -",
                                "Sangat Terjangkau", "Terjangkau", "Normal", "Mahal", "Sangat Mahal" }));

                cbSubHarga4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
                                "- Pilih Sub Kriteria Harga -",
                                "Sangat Terjangkau", "Terjangkau", "Normal", "Mahal", "Sangat Mahal" }));

                cbSubHarga5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
                                "- Pilih Sub Kriteria Harga -",
                                "Sangat Terjangkau", "Terjangkau ", "Normal ", "Mahal ", "Sangat Mahal ", " " }));

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel17)
                                                                                .addComponent(jLabel18)
                                                                                .addComponent(jLabel19)
                                                                                .addComponent(jLabel21)
                                                                                .addComponent(jLabel20))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(jPanel4Layout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(cbSubHarga1,
                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                0, 175,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(cbSubHarga2,
                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                0,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(cbSubHarga3,
                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                0,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(cbSubHarga4,
                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                0,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(cbSubHarga5,
                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                0,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))));
                jPanel4Layout.setVerticalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel17)
                                                                                .addComponent(cbSubHarga1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(cbSubHarga2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel18))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(cbSubHarga3,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel19))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(cbSubHarga4,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel20))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(cbSubHarga5,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel21))));

                jPanel5.setBackground(new java.awt.Color(26, 42, 128));
                jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(
                                javax.swing.BorderFactory.createTitledBorder(null, "Penyimpanan",
                                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                                new java.awt.Font("SansSerif", 1, 12),
                                                new java.awt.Color(255, 237, 192)),
                                "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12),
                                new java.awt.Color(255, 255, 255))); // NOI18N

                jLabel22.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel22.setForeground(new java.awt.Color(255, 237, 192));
                jLabel22.setText("Penyimpanan Sangat Penting ke-1");

                cbSubPenyimpanan1
                                .setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
                                                "- Pilih Sub Kriteria Penyimpanan -",
                                                "Sangat Besar ", "Besar", "Sedang ", "Kecil ", "Sangat Kecil ", " " }));
                cbSubPenyimpanan1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                cbSubPenyimpanan1ActionPerformed(evt);
                        }
                });

                jLabel23.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel23.setForeground(new java.awt.Color(255, 237, 192));
                jLabel23.setText("Penyimpanan Penting ke-2");

                cbSubPenyimpanan2
                                .setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
                                                "- Pilih Sub Kriteria Penyimpanan -",
                                                "Sangat Besar ", "Besar", "Sedang ", "Kecil ", "Sangat Kecil ", " " }));
                cbSubPenyimpanan2.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                cbSubPenyimpanan2ActionPerformed(evt);
                        }
                });

                cbSubPenyimpanan3
                                .setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
                                                "- Pilih Sub Kriteria Penyimpanan -",
                                                "Sangat Besar ", "Besar", "Sedang ", "Kecil ", "Sangat Kecil ", " " }));

                jLabel24.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel24.setForeground(new java.awt.Color(255, 237, 192));
                jLabel24.setText("Penyimpanan Cukup Penting ke-3");

                jLabel27.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel27.setForeground(new java.awt.Color(255, 237, 192));
                jLabel27.setText("Penyimpanan Biasa ke-4");

                cbSubPenyimpanan4
                                .setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
                                                "- Pilih Sub Kriteria Penyimpanan -",
                                                "Sangat Besar ", "Besar", "Sedang ", "Kecil ", "Sangat Kecil ", " " }));

                jLabel28.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel28.setForeground(new java.awt.Color(255, 237, 192));
                jLabel28.setText("Penyimpanan Kurang Penting ke-5");

                cbSubPenyimpanan5
                                .setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
                                                "- Pilih Sub Kriteria Penyimpanan -",
                                                "Sangat Besar ", "Besar", "Sedang ", "Kecil ", "Sangat Kecil ", " " }));

                javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                jPanel5.setLayout(jPanel5Layout);
                jPanel5Layout.setHorizontalGroup(
                                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout
                                                                .createSequentialGroup()
                                                                .addGroup(jPanel5Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel22)
                                                                                .addComponent(jLabel23)
                                                                                .addComponent(jLabel24)
                                                                                .addComponent(jLabel27)
                                                                                .addComponent(jLabel28))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(jPanel5Layout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(cbSubPenyimpanan5, 0,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(cbSubPenyimpanan4, 0,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(cbSubPenyimpanan3, 0,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(cbSubPenyimpanan2, 0,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(cbSubPenyimpanan1, 0,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))));
                jPanel5Layout.setVerticalGroup(
                                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel5Layout.createSequentialGroup()
                                                                .addGroup(jPanel5Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel22)
                                                                                .addComponent(cbSubPenyimpanan1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel5Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel23)
                                                                                .addComponent(cbSubPenyimpanan2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel5Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel24)
                                                                                .addComponent(cbSubPenyimpanan3,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel5Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel27)
                                                                                .addComponent(cbSubPenyimpanan4,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel5Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel28)
                                                                                .addComponent(cbSubPenyimpanan5,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))));

                jPanel8.setBackground(new java.awt.Color(26, 42, 128));
                jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(
                                javax.swing.BorderFactory.createTitledBorder(null, "Ketahanan Baterai",
                                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                                new java.awt.Font("SansSerif", 1, 12),
                                                new java.awt.Color(255, 237, 192)),
                                "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12),
                                new java.awt.Color(255, 255, 255))); // NOI18N

                jLabel29.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel29.setForeground(new java.awt.Color(255, 237, 192));
                jLabel29.setText("Ketahanan Baterai Sangat Penting ke-1");

                cbSubBaterai1.setModel(
                                new javax.swing.DefaultComboBoxModel<>(new String[] {
                                                "- Pilih Sub Kriteria Ketahanan Baterai -",
                                                "Sangat Lama ", "Lama", "Sedang ", "Kurang", "Sangat Kurang " }));

                jLabel30.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel30.setForeground(new java.awt.Color(255, 237, 192));
                jLabel30.setText("Ketahanan Baterai Penting ke-2");

                cbSubBaterai2.setModel(
                                new javax.swing.DefaultComboBoxModel<>(new String[] {
                                                "- Pilih Sub Kriteria Ketahanan Baterai -",
                                                "Sangat Lama ", "Lama", "Sedang ", "Kurang", "Sangat Kurang " }));

                cbSubBaterai3.setModel(
                                new javax.swing.DefaultComboBoxModel<>(new String[] {
                                                "- Pilih Sub Kriteria Ketahanan Baterai -",
                                                "Sangat Lama ", "Lama", "Sedang ", "Kurang", "Sangat Kurang " }));

                jLabel37.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel37.setForeground(new java.awt.Color(255, 237, 192));
                jLabel37.setText("Ketahanan Baterai Cukup Penting ke-3");

                cbSubBaterai4.setModel(
                                new javax.swing.DefaultComboBoxModel<>(new String[] {
                                                "- Pilih Sub Kriteria Ketahanan Baterai -",
                                                "Sangat Lama ", "Lama", "Sedang ", "Kurang", "Sangat Kurang " }));

                jLabel38.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel38.setForeground(new java.awt.Color(255, 237, 192));
                jLabel38.setText("Ketahanan Baterai Biasa ke-4");

                jLabel39.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel39.setForeground(new java.awt.Color(255, 237, 192));
                jLabel39.setText("Ketahanan Baterai Kurang Penting ke-5");

                cbSubBaterai5.setModel(
                                new javax.swing.DefaultComboBoxModel<>(new String[] {
                                                "- Pilih Sub Kriteria Ketahanan Baterai -",
                                                "Sangat Lama ", "Lama", "Sedang ", "Kurang", "Sangat Kurang " }));

                javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                jPanel8.setLayout(jPanel8Layout);
                jPanel8Layout.setHorizontalGroup(
                                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout
                                                                .createSequentialGroup()
                                                                .addComponent(jLabel29)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(cbSubBaterai1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                                .addComponent(jLabel30)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(cbSubBaterai2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                                .addComponent(jLabel37)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(cbSubBaterai3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                                .addComponent(jLabel38)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(cbSubBaterai4,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                                .addComponent(jLabel39)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(cbSubBaterai5,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)));
                jPanel8Layout.setVerticalGroup(
                                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                                .addGroup(jPanel8Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel29)
                                                                                .addComponent(cbSubBaterai1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel8Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel30)
                                                                                .addComponent(cbSubBaterai2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel8Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel37)
                                                                                .addComponent(cbSubBaterai3,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel8Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel38)
                                                                                .addComponent(cbSubBaterai4,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel8Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel39)
                                                                                .addComponent(cbSubBaterai5,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))));

                jPanel9.setBackground(new java.awt.Color(26, 42, 128));
                jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(
                                javax.swing.BorderFactory.createTitledBorder(null, "Ram",
                                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                                new java.awt.Font("SansSerif", 1, 12),
                                                new java.awt.Color(255, 237, 192)),
                                "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12),
                                new java.awt.Color(255, 255, 255))); // NOI18N

                jLabel33.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel33.setForeground(new java.awt.Color(255, 237, 192));
                jLabel33.setText("Ram Sangat Penting ke-1");

                cbSubRam1.setModel(new javax.swing.DefaultComboBoxModel<>(
                                new String[] { "- Pilih Sub Kriteria Ram -", "32GB (Sangat Tinggi) ", "16GB (Tinggi) ",
                                                "12GB ( Sedang ) ", "8GB (Rendah) ", "4GB (Sangat Rendah) " }));

                jLabel34.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel34.setForeground(new java.awt.Color(255, 237, 192));
                jLabel34.setText("Ram Penting ke-2");

                cbSubRam2.setModel(new javax.swing.DefaultComboBoxModel<>(
                                new String[] { "- Pilih Sub Kriteria Ram -", "32GB (Sangat Tinggi) ", "16GB (Tinggi) ",
                                                "12GB ( Sedang ) ", "8GB (Rendah) ", "4GB (Sangat Rendah) " }));

                cbSubRam3.setModel(new javax.swing.DefaultComboBoxModel<>(
                                new String[] { "- Pilih Sub Kriteria Ram -", "32GB (Sangat Tinggi) ", "16GB (Tinggi) ",
                                                "12GB ( Sedang ) ", "8GB (Rendah) ", "4GB (Sangat Rendah) " }));

                jLabel35.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel35.setForeground(new java.awt.Color(255, 237, 192));
                jLabel35.setText("Ram Cukup Penting ke-3");

                cbSubRam4.setModel(new javax.swing.DefaultComboBoxModel<>(
                                new String[] { "- Pilih Sub Kriteria Ram -", "32GB (Sangat Tinggi) ", "16GB (Tinggi) ",
                                                "12GB ( Sedang ) ", "8GB (Rendah) ", "4GB (Sangat Rendah) " }));

                jLabel36.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel36.setForeground(new java.awt.Color(255, 237, 192));
                jLabel36.setText("Ram Biasa ke-4");

                cbSubRam5.setModel(new javax.swing.DefaultComboBoxModel<>(
                                new String[] { "- Pilih Sub Kriteria Ram -", "32GB (Sangat Tinggi) ", "16GB (Tinggi) ",
                                                "12GB ( Sedang ) ", "8GB (Rendah) ", "4GB (Sangat Rendah) ", " " }));

                jLabel40.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                jLabel40.setForeground(new java.awt.Color(255, 237, 192));
                jLabel40.setText("Ram Kurang Penting ke-5");

                javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                jPanel9.setLayout(jPanel9Layout);
                jPanel9Layout.setHorizontalGroup(
                                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout
                                                                .createSequentialGroup()
                                                                .addComponent(jLabel33)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(cbSubRam1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel9Layout.createSequentialGroup()
                                                                .addComponent(jLabel34)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(cbSubRam2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel9Layout.createSequentialGroup()
                                                                .addComponent(jLabel35)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(cbSubRam3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout
                                                                .createSequentialGroup()
                                                                .addGroup(jPanel9Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel36)
                                                                                .addComponent(jLabel40))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(jPanel9Layout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(cbSubRam4, 0,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(cbSubRam5, 0,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))));
                jPanel9Layout.setVerticalGroup(
                                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel9Layout.createSequentialGroup()
                                                                .addGroup(jPanel9Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel33)
                                                                                .addComponent(cbSubRam1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel9Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel34)
                                                                                .addComponent(cbSubRam2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel9Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel35)
                                                                                .addComponent(cbSubRam3,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel9Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel36)
                                                                                .addComponent(cbSubRam4,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel9Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel40)
                                                                                .addComponent(cbSubRam5,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))));

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel2Layout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(jPanel4,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jPanel5,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jPanel8,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jPanel9,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jPanel3,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addContainerGap(68, Short.MAX_VALUE)));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jPanel3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel4,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel5,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel8,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel9,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(958, Short.MAX_VALUE)));

                jPanel3.getAccessibleContext().setAccessibleName("Kualitas");

                jScrollPane2.setViewportView(jPanel2);

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(20, 20, 20)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel2)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(tombolSimpan,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                120,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(tombolEdit,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                120,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(tombolHapus,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                120,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(jScrollPane1,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                501,
                                                                                                Short.MAX_VALUE))
                                                                .addGap(10, 10, 10)
                                                                .addComponent(jScrollPane2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                449,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(tombolSimpan,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                35,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(tombolEdit,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                35,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(tombolHapus,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                35,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(jScrollPane1,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                260,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jLabel2))
                                                                                .addComponent(jScrollPane2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                344,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(254, Short.MAX_VALUE)));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(judul, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(judul,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                74,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                614, Short.MAX_VALUE)
                                                                .addContainerGap()));
        }// </editor-fold>//GEN-END:initComponents

        private void tombolSimpanActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_tombolSimpanActionPerformed
                // TODO add your handling code here:
                if (validateSubKriteriaSelection()) {
                        insertDataSubKriteria();
                } else {
                        String errorMessage = getValidationErrorMessage();
                        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                }
        }// GEN-LAST:event_tombolSimpanActionPerformed

        private void tombolEditActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_tombolEditActionPerformed
                // TODO add your handling code here:
                if (validateSubKriteriaSelection()) {
                        editDataSubKriteria();
                } else {
                        String errorMessage = getValidationErrorMessage();
                        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                }
        }// GEN-LAST:event_tombolEditActionPerformed

        private void tombolHapusActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_tombolHapusActionPerformed
                hapusDataSubKriteria();
        }// GEN-LAST:event_tombolHapusActionPerformed

        private void tabelSubKriteriaMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tabelSubKriteriaMouseClicked
                getDataTabel();
        }// GEN-LAST:event_tabelSubKriteriaMouseClicked

        private void cbSubPenyimpanan2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cbSubPenyimpanan2ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_cbSubPenyimpanan2ActionPerformed

        private void cbSubPenyimpanan1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cbSubPenyimpanan1ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_cbSubPenyimpanan1ActionPerformed

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JComboBox<String> cbSubBaterai1;
        private javax.swing.JComboBox<String> cbSubBaterai2;
        private javax.swing.JComboBox<String> cbSubBaterai3;
        private javax.swing.JComboBox<String> cbSubBaterai4;
        private javax.swing.JComboBox<String> cbSubBaterai5;
        private javax.swing.JComboBox<String> cbSubHarga1;
        private javax.swing.JComboBox<String> cbSubHarga2;
        private javax.swing.JComboBox<String> cbSubHarga3;
        private javax.swing.JComboBox<String> cbSubHarga4;
        private javax.swing.JComboBox<String> cbSubHarga5;
        private javax.swing.JComboBox<String> cbSubPenyimpanan1;
        private javax.swing.JComboBox<String> cbSubPenyimpanan2;
        private javax.swing.JComboBox<String> cbSubPenyimpanan3;
        private javax.swing.JComboBox<String> cbSubPenyimpanan4;
        private javax.swing.JComboBox<String> cbSubPenyimpanan5;
        private javax.swing.JComboBox<String> cbSubPerforma1;
        private javax.swing.JComboBox<String> cbSubPerforma2;
        private javax.swing.JComboBox<String> cbSubPerforma3;
        private javax.swing.JComboBox<String> cbSubPerforma4;
        private javax.swing.JComboBox<String> cbSubPerforma5;
        private javax.swing.JComboBox<String> cbSubRam1;
        private javax.swing.JComboBox<String> cbSubRam2;
        private javax.swing.JComboBox<String> cbSubRam3;
        private javax.swing.JComboBox<String> cbSubRam4;
        private javax.swing.JComboBox<String> cbSubRam5;
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
        private javax.swing.JLabel jLabel27;
        private javax.swing.JLabel jLabel28;
        private javax.swing.JLabel jLabel29;
        private javax.swing.JLabel jLabel30;
        private javax.swing.JLabel jLabel33;
        private javax.swing.JLabel jLabel34;
        private javax.swing.JLabel jLabel35;
        private javax.swing.JLabel jLabel36;
        private javax.swing.JLabel jLabel37;
        private javax.swing.JLabel jLabel38;
        private javax.swing.JLabel jLabel39;
        private javax.swing.JLabel jLabel40;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JPanel jPanel4;
        private javax.swing.JPanel jPanel5;
        private javax.swing.JPanel jPanel8;
        private javax.swing.JPanel jPanel9;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JScrollPane jScrollPane2;
        private javax.swing.JLabel judul;
        private javax.swing.JTable tabelSubKriteria;
        private javax.swing.JButton tombolEdit;
        private javax.swing.JButton tombolHapus;
        private javax.swing.JButton tombolSimpan;
        // End of variables declaration//GEN-END:variables
}
