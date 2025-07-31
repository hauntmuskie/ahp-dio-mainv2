
package menuutama;

import java.awt.Color;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.table.DefaultTableModel;
import koneksi.Koneksi;

/**
 *
 * @author Siti Mawaddah
 */
public class DialogTambahData extends javax.swing.JDialog {
    private Connection conn = new Koneksi().connect();
    private DefaultTableModel tabmode;

    /**
     * Creates new form DialogTambahData
     */
    public DialogTambahData(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        // add Panel, add panel(sebuah panel)
        Pane.add(PanelKandidat);
        Pane.repaint();
        Pane.revalidate();
    }

    public void setDataTabel(String id_laptop, String nama_laptop, String merk, String harga, String processor,
            String ram, String storage, String battery_life, String performa, String kategori_harga,
            String kategori_penyimpanan, String kategori_daya_tahan, String kategori_ram) {
        tNoId.setText(id_laptop);
        tNamaLaptop.setText(nama_laptop);
        tMerk.setText(merk);
        tNoBobot.setText(harga); // Price field
        tTahun.setText(processor); // Processor field
        cbPerforma.setSelectedItem(performa);
        cbHarga.setSelectedItem(kategori_harga);
        cbPenyimpanan.setSelectedItem(kategori_penyimpanan);
        cbKetepatanKetahananBaterai.setSelectedItem(kategori_daya_tahan);
        cbKetepatanRam.setSelectedItem(kategori_ram);
    }

    protected void kosong() {
        tNoId.setText("");
        tNamaLaptop.setText("");
        tMerk.setText("");
        tNoBobot.setText("");
        tTahun.setText("");
        cbPerforma.setSelectedIndex(0);
        cbHarga.setSelectedIndex(0);
        cbPenyimpanan.setSelectedIndex(0);
        cbKetepatanKetahananBaterai.setSelectedIndex(0);
        cbKetepatanRam.setSelectedIndex(0);
    }

    private void insertDataPaket() {
        String sql = "INSERT INTO data_laptop (id_laptop, nama_laptop, merk, harga, processor, ram, storage, battery_life, performa, kategori_harga, kategori_penyimpanan, kategori_daya_tahan, kategori_ram) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, tNoId.getText());
            stat.setString(2, tNamaLaptop.getText());
            stat.setString(3, tMerk.getText());
            stat.setString(4, tNoBobot.getText()); // harga (price)
            stat.setString(5, tTahun.getText()); // processor
            stat.setString(6, "8GB"); // ram (default value)
            stat.setString(7, "256GB SSD"); // storage (default value)
            stat.setString(8, "8 jam"); // battery_life (default value)
            stat.setString(9, cbPerforma.getSelectedItem().toString());
            stat.setString(10, cbHarga.getSelectedItem().toString());
            stat.setString(11, cbPenyimpanan.getSelectedItem().toString());
            stat.setString(12, cbKetepatanKetahananBaterai.getSelectedItem().toString());
            stat.setString(13, cbKetepatanRam.getSelectedItem().toString());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "DATA Berhasil Disimpan");
            kosong();
            tNoId.requestFocus();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan " + e);
        }
    }

    private void editDataPaket() {
        try {
            String sql = "UPDATE data_laptop set nama_laptop=?, merk=?, harga=?, processor=?, ram=?, storage=?, battery_life=?, performa=?, kategori_harga=?, kategori_penyimpanan=?, kategori_daya_tahan=?, kategori_ram=? WHERE id_laptop=?";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, tNamaLaptop.getText());
            stat.setString(2, tMerk.getText());
            stat.setString(3, tNoBobot.getText()); // harga (price)
            stat.setString(4, tTahun.getText()); // processor
            stat.setString(5, "8GB"); // ram (default)
            stat.setString(6, "256GB SSD"); // storage (default)
            stat.setString(7, "8 jam"); // battery_life (default)
            stat.setString(8, cbPerforma.getSelectedItem().toString());
            stat.setString(9, cbHarga.getSelectedItem().toString());
            stat.setString(10, cbPenyimpanan.getSelectedItem().toString());
            stat.setString(11, cbKetepatanKetahananBaterai.getSelectedItem().toString());
            stat.setString(12, cbKetepatanRam.getSelectedItem().toString());
            stat.setString(13, tNoId.getText()); // WHERE condition
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "DATA Berhasil Diubah");
            kosong();
            tNoId.requestFocus();
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelKandidat = new javax.swing.JPanel();
        tombolEdit = new javax.swing.JLabel();
        judul = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        cbPerforma = new javax.swing.JComboBox<>();
        cbHarga = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        cbPenyimpanan = new javax.swing.JComboBox<>();
        cbKetepatanKetahananBaterai = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        cbKetepatanRam = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        tNamaLaptop = new javax.swing.JTextField();
        tNoBobot = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tTahun = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        tNoId = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tMerk = new javax.swing.JTextField();
        tombolTambah = new javax.swing.JLabel();
        IsiKosong = new javax.swing.JOptionPane();
        Pane = new javax.swing.JPanel();

        PanelKandidat.setBackground(new java.awt.Color(255, 237, 192));

        tombolEdit.setBackground(new java.awt.Color(26, 42, 128));
        tombolEdit.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tombolEdit.setForeground(new java.awt.Color(255, 237, 192));
        tombolEdit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tombolEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Icon Ubah Small.png"))); // NOI18N
        tombolEdit.setText("UBAH");
        tombolEdit.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tombolEdit.setOpaque(true);
        tombolEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tombolEditMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tombolEditMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tombolEditMouseExited(evt);
            }
        });

        judul.setBackground(new java.awt.Color(26, 42, 128));
        judul.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        judul.setForeground(new java.awt.Color(204, 255, 255));
        judul.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        judul.setText(" Tambah Data Laptop");
        judul.setOpaque(true);

        jPanel12.setBackground(new java.awt.Color(26, 42, 128));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Penilaian Bobot Laptop", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(255, 237, 192))); // NOI18N
        jPanel12.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        jLabel25.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 237, 192));
        jLabel25.setText("Performa");

        jLabel26.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 237, 192));
        jLabel26.setText("Harga");

        cbPerforma.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cbPerforma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Performa -", "High-End", "Mid-Range", "Entry-Level", "Basic\t", "Low-End" }));
        cbPerforma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPerformaActionPerformed(evt);
            }
        });

        cbHarga.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cbHarga.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Harga -", "Sangat Terjangkau", "Terjangkau ", "Normal ", "Mahal ", "Sangat Mahal ", " " }));

        jLabel27.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 237, 192));
        jLabel27.setText("Penyimpanan");

        cbPenyimpanan.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cbPenyimpanan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Penyimpanan -", "Sangat Besar ", "Besar", "Sedang ", "Kecil ", "Sangat Kecil ", " " }));

        cbKetepatanKetahananBaterai.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cbKetepatanKetahananBaterai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Ketahanan Baterai -", "Sangat Lama ", "Lama", "Sedang ", "Kurang", "Sangat Kurang ", " " }));

        jLabel28.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 237, 192));
        jLabel28.setText("Ketahanan Baterai");

        cbKetepatanRam.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cbKetepatanRam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Ram -", "32GB (Sangat Tinggi) ", "16GB (Tinggi) ", "12GB ( Sedang ) ", "8GB (Rendah) ", "4GB (Sangat Rendah) ", " " }));

        jLabel29.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 237, 192));
        jLabel29.setText("Ram");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addGap(23, 23, 23)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cbHarga, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbPenyimpanan, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbKetepatanRam, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbKetepatanKetahananBaterai, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbPerforma, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(cbPerforma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(cbHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(cbPenyimpanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(cbKetepatanKetahananBaterai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(cbKetepatanRam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(26, 42, 128));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Laptop", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(255, 237, 192))); // NOI18N
        jPanel11.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 237, 192));
        jLabel6.setText("Nama Laptop");

        jLabel21.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 237, 192));
        jLabel21.setText("Tahun Produksi");

        jLabel22.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 237, 192));
        jLabel22.setText("Bobot Laptop");

        tNamaLaptop.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        tNoBobot.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        tTahun.setColumns(20);
        tTahun.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tTahun.setRows(5);
        jScrollPane3.setViewportView(tTahun);

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 237, 192));
        jLabel9.setText("No Id");

        tNoId.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 237, 192));
        jLabel8.setText("Merk Laptop");

        tMerk.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addGap(48, 48, 48)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tNamaLaptop)
                    .addComponent(tNoId)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tNoBobot)
                    .addComponent(tMerk))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tNoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tNamaLaptop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tMerk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tNoBobot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(130, Short.MAX_VALUE))
        );

        tombolTambah.setBackground(new java.awt.Color(26, 42, 128));
        tombolTambah.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tombolTambah.setForeground(new java.awt.Color(255, 237, 192));
        tombolTambah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tombolTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Icon Tambah Small.png"))); // NOI18N
        tombolTambah.setText("TAMBAH");
        tombolTambah.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tombolTambah.setOpaque(true);
        tombolTambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tombolTambahMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tombolTambahMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tombolTambahMouseExited(evt);
            }
        });

        javax.swing.GroupLayout PanelKandidatLayout = new javax.swing.GroupLayout(PanelKandidat);
        PanelKandidat.setLayout(PanelKandidatLayout);
        PanelKandidatLayout.setHorizontalGroup(
            PanelKandidatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKandidatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelKandidatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelKandidatLayout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelKandidatLayout.createSequentialGroup()
                        .addComponent(tombolTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tombolEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addComponent(judul, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelKandidatLayout.setVerticalGroup(
            PanelKandidatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKandidatLayout.createSequentialGroup()
                .addComponent(judul, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(PanelKandidatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tombolTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tombolEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelKandidatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(169, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("dialog1"); // NOI18N

        Pane.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(888, 577));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tombolTambahMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tombolTambahMouseClicked
        // TODO add your handling code here:
        if (!tNamaLaptop.getText().equals("") && !tMerk.getText().equals("") && !tNoBobot.getText().equals("")
                && !tTahun.getText().equals("")
                && cbPerforma.getSelectedIndex() != 0 && cbHarga.getSelectedIndex() != 0
                && cbPenyimpanan.getSelectedIndex() != 0
                && cbKetepatanKetahananBaterai.getSelectedIndex() != 0 && cbKetepatanRam.getSelectedIndex() != 0) {
            insertDataPaket();
            dispose();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Mohon isi semua kolom isian pada form !", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }// GEN-LAST:event_tombolTambahMouseClicked

    private void tombolTambahMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tombolTambahMouseEntered
        // TODO add your handling code here:
        tombolTambah.setBackground(new Color(0, 51, 153));
        tombolTambah.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_tombolTambahMouseEntered

    private void tombolTambahMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tombolTambahMouseExited
        // TODO add your handling code here:
        tombolTambah.setBackground(new Color(0, 51, 102));
    }// GEN-LAST:event_tombolTambahMouseExited

    private void tombolEditMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tombolEditMouseClicked
        // TODO add your handling code here:
        editDataPaket();
        dispose();
    }// GEN-LAST:event_tombolEditMouseClicked

    private void tombolEditMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tombolEditMouseEntered
        // TODO add your handling code here:
        tombolEdit.setBackground(new Color(0, 51, 153));
        tombolEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_tombolEditMouseEntered

    private void tombolEditMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tombolEditMouseExited
        // TODO add your handling code here:
        tombolEdit.setBackground(new Color(0, 51, 102));
    }// GEN-LAST:event_tombolEditMouseExited

    private void cbPerformaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cbPerformaActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_cbPerformaActionPerformed

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DialogTambahData.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogTambahData.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogTambahData.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogTambahData.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }
        // </editor-fold>
        // </editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogTambahData dialog = new DialogTambahData(new javax.swing.JFrame(), true);
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
    private javax.swing.JOptionPane IsiKosong;
    private javax.swing.JPanel Pane;
    private javax.swing.JPanel PanelKandidat;
    private javax.swing.JComboBox<String> cbHarga;
    private javax.swing.JComboBox<String> cbKetepatanKetahananBaterai;
    private javax.swing.JComboBox<String> cbKetepatanRam;
    private javax.swing.JComboBox<String> cbPenyimpanan;
    private javax.swing.JComboBox<String> cbPerforma;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel judul;
    private javax.swing.JTextField tMerk;
    private javax.swing.JTextField tNamaLaptop;
    private javax.swing.JTextField tNoBobot;
    private javax.swing.JTextField tNoId;
    private javax.swing.JTextArea tTahun;
    private javax.swing.JLabel tombolEdit;
    private javax.swing.JLabel tombolTambah;
    // End of variables declaration//GEN-END:variables

    void show(JRootPane rootPane) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }
}
