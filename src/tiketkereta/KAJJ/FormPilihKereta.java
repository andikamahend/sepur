package tiketkereta.KAJJ;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import tiketkereta.Koneksi;

public class FormPilihKereta extends javax.swing.JFrame {
    private String asal, tujuan, tanggal;
    private int dewasa, bayi;
    private String noUrut, nim, nama;

    public FormPilihKereta(String asal, String tujuan, String tanggal, int dewasa, int bayi, String noUrut, String nim, String nama) {
        initComponents();
        this.asal = asal;
        this.tujuan = tujuan;
        this.tanggal = tanggal;
        this.dewasa = dewasa;
        this.bayi = bayi;
        this.noUrut = noUrut;
        this.nim = nim;
        this.nama = nama;

        // Menampilkan informasi rute dan tanggal
        lblInfo.setText("Rute: " + asal + " Ke " + tujuan + " | Tanggal: " + tanggal);
        
        // Memuat daftar kereta dari database berdasarkan rute
        isiDaftarKereta(asal, tujuan);

        // Menambahkan listener ke radio button untuk memperbarui harga saat kelas dipilih
        rbtnEkonomi.addActionListener(e -> tampilkanHarga());
        rbtnBisnis.addActionListener(e -> tampilkanHarga());
        rbtnEksekutif.addActionListener(e -> tampilkanHarga());
        
        // Mengatur posisi frame di tengah
        setLocationRelativeTo(null);
    }

    /**
     * Mengisi JComboBox dengan daftar kereta yang tersedia untuk rute yang dipilih.
     */
    private void isiDaftarKereta(String asal, String tujuan) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        try {
            Connection conn = Koneksi.getConnection();
            // Query untuk mengambil nama kereta berdasarkan asal dan tujuan
            String sql = "SELECT DISTINCT k.nama_kereta FROM kereta k " +
                         "JOIN rute r ON k.id_kereta = r.id_kereta WHERE r.asal = ? AND r.tujuan = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, asal);
            ps.setString(2, tujuan);
            ResultSet rs = ps.executeQuery();

            boolean keretaDitemukan = false;
            while (rs.next()) {
                model.addElement(rs.getString("nama_kereta"));
                keretaDitemukan = true;
            }

            // Jika tidak ada kereta, tampilkan pesan dan nonaktifkan komponen
            if (!keretaDitemukan) {
                model.addElement("Tidak ada kereta langsung");
                comboKereta.setEnabled(false);
                rbtnEkonomi.setEnabled(false);
                rbtnBisnis.setEnabled(false);
                rbtnEksekutif.setEnabled(false);
                jButton1.setEnabled(false); // Nonaktifkan tombol Lanjut
            }
            
            comboKereta.setModel(model);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat daftar kereta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Menampilkan harga tiket berdasarkan kereta dan kelas yang dipilih.
     */
    private void tampilkanHarga() {
        String kereta = (String) comboKereta.getSelectedItem();
        String kelas = "";

        if (rbtnEkonomi.isSelected()) kelas = "Ekonomi";
        else if (rbtnBisnis.isSelected()) kelas = "Bisnis";
        else if (rbtnEksekutif.isSelected()) kelas = "Eksekutif";

        if (kereta != null && !kelas.isEmpty()) {
            try {
                Connection conn = Koneksi.getConnection();
                // Query untuk mengambil harga dari tabel kereta_kelas
                String sql = "SELECT harga FROM kereta_kelas kk " +
                             "JOIN kereta k ON kk.id_kereta = k.id_kereta " +
                             "WHERE k.nama_kereta = ? AND kk.kelas = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, kereta);
                ps.setString(2, kelas);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    int harga = rs.getInt("harga");
                    lblHarga.setText("Harga: Rp" + harga);
                } else {
                    lblHarga.setText("Harga: - (Kelas tidak tersedia)");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal mengambil harga: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                lblHarga.setText("Harga: -");
            }
        } else {
            lblHarga.setText("Harga: -");
        }
    }
    
    /**
     * Mengatur ketersediaan radio button kelas berdasarkan kereta yang dipilih.
     */

    private void aturKetersediaanKelas() {
        String keretaTerpilih = (String) comboKereta.getSelectedItem();
        if (keretaTerpilih == null || keretaTerpilih.equals("Tidak ada kereta langsung")) {
            return;
        }

        // Reset semua pilihan
        ButtonGroup.clearSelection();
        lblHarga.setText("Harga: -");
        rbtnEkonomi.setEnabled(false);
        rbtnBisnis.setEnabled(false);
        rbtnEksekutif.setEnabled(false);

        try {
            Connection conn = Koneksi.getConnection();
            String sql = "SELECT kelas FROM kereta_kelas kk JOIN kereta k ON kk.id_kereta = k.id_kereta WHERE k.nama_kereta = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, keretaTerpilih); // Menggunakan variabel yang sudah diperbaiki
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String kelas = rs.getString("kelas");
                if(kelas.equalsIgnoreCase("Ekonomi")){
                    rbtnEkonomi.setEnabled(true);
                } else if(kelas.equalsIgnoreCase("Bisnis")){
                    rbtnBisnis.setEnabled(true);
                } else if(kelas.equalsIgnoreCase("Eksekutif")){
                    rbtnEksekutif.setEnabled(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat kelas kereta: " + e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ButtonGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new tiketkereta.ImagePanel(new javax.swing.ImageIcon(getClass().getResource("/bg_kereta.jpg")).getImage());
        jButton1 = new javax.swing.JButton();
        lblHarga = new javax.swing.JLabel();
        comboKereta = new javax.swing.JComboBox<>();
        lblInfo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        rbtnBisnis = new javax.swing.JRadioButton();
        rbtnEkonomi = new javax.swing.JRadioButton();
        rbtnEksekutif = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1920, 1080));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo_Lanjut.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblHarga.setFont(new java.awt.Font("High Tower Text", 1, 36)); // NOI18N
        lblHarga.setText("Harga: -");

        comboKereta.setFont(new java.awt.Font("High Tower Text", 1, 36)); // NOI18N
        comboKereta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboKereta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboKeretaActionPerformed(evt);
            }
        });

        lblInfo.setFont(new java.awt.Font("High Tower Text", 1, 36)); // NOI18N
        lblInfo.setText("  ");

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        jLabel2.setText("Silahkan Pilih Kereta");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(735, 735, 735)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tiketkereta/Icon Kembali.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        ButtonGroup.add(rbtnBisnis);
        rbtnBisnis.setContentAreaFilled(false);
        rbtnBisnis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/EK.png"))); // NOI18N

        ButtonGroup.add(rbtnEkonomi);
        rbtnEkonomi.setContentAreaFilled(false);
        rbtnEkonomi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/EKSSNG.png"))); // NOI18N
        rbtnEkonomi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnEkonomiActionPerformed(evt);
            }
        });

        ButtonGroup.add(rbtnEksekutif);
        rbtnEksekutif.setContentAreaFilled(false);
        rbtnEksekutif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ESSNG.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("High Tower Text", 1, 24)); // NOI18N
        jLabel3.setText("BISNIS");

        jLabel4.setFont(new java.awt.Font("High Tower Text", 1, 24)); // NOI18N
        jLabel4.setText("EKSEKUTIF");

        jLabel5.setFont(new java.awt.Font("High Tower Text", 1, 24)); // NOI18N
        jLabel5.setText("EKONOMI");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 20, Short.MAX_VALUE)
                        .addComponent(rbtnEksekutif)
                        .addGap(0, 0, 0)
                        .addComponent(rbtnBisnis)
                        .addGap(0, 0, 0)
                        .addComponent(rbtnEkonomi))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(791, 791, 791)
                                    .addComponent(comboKereta, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(606, 606, 606)
                                    .addComponent(lblHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(680, 680, 680)
                                    .addComponent(jButton2)
                                    .addGap(339, 339, 339)
                                    .addComponent(jButton1))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(491, 491, 491)
                .addComponent(jLabel5)
                .addGap(268, 268, 268))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(295, 295, 295)
                    .addComponent(jLabel4)
                    .addContainerGap(1490, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(comboKereta, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtnEksekutif)
                    .addComponent(rbtnBisnis)
                    .addComponent(rbtnEkonomi))
                .addGap(57, 57, 57)
                .addComponent(lblInfo)
                .addGap(18, 18, 18)
                .addComponent(lblHarga)
                .addGap(85, 85, 85)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addGap(85, 85, 85))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(364, 364, 364)
                    .addComponent(jLabel4)
                    .addContainerGap(538, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new FormRute(noUrut, nim, nama).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void comboKeretaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboKeretaActionPerformed
        aturKetersediaanKelas();
    }//GEN-LAST:event_comboKeretaActionPerformed

    private void rbtnEkonomiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnEkonomiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnEkonomiActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String keretaDipilih = comboKereta.getSelectedItem().toString();
        String kelas = "";
        if (rbtnEkonomi.isSelected()) kelas = "Ekonomi";
        else if (rbtnBisnis.isSelected()) kelas = "Bisnis";
        else if (rbtnEksekutif.isSelected()) kelas = "Eksekutif";

        if (kelas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih salah satu kelas kereta!");
            return;
        }
        if(lblHarga.getText().contains("tidak tersedia")){
            JOptionPane.showMessageDialog(this, "Kelas yang Anda pilih tidak tersedia untuk kereta ini.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int totalPenumpang = dewasa + bayi;
        ArrayList<Penumpang> daftarPenumpang = new ArrayList<>();

        new FormDataPenumpang(
            asal, tujuan, tanggal,
            keretaDipilih, kelas,
            1, totalPenumpang, daftarPenumpang,
            noUrut, nim, nama,
            dewasa, bayi
        ).setVisible(true);

        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup ButtonGroup;
    private javax.swing.JComboBox<String> comboKereta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblHarga;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JRadioButton rbtnBisnis;
    private javax.swing.JRadioButton rbtnEkonomi;
    private javax.swing.JRadioButton rbtnEksekutif;
    // End of variables declaration//GEN-END:variables
}
