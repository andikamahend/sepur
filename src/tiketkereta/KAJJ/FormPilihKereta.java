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
        lblInfo.setText("Rute: " + asal + " → " + tujuan + " | Tanggal: " + tanggal);
        
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
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        lblHarga = new javax.swing.JLabel();
        rbtnEkonomi = new javax.swing.JRadioButton();
        rbtnBisnis = new javax.swing.JRadioButton();
        rbtnEksekutif = new javax.swing.JRadioButton();
        comboKereta = new javax.swing.JComboBox<>();
        lblInfo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jButton1.setText("Lanjut");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblHarga.setText("Harga: -");

        ButtonGroup.add(rbtnEkonomi);
        rbtnEkonomi.setText("Ekonomi");

        ButtonGroup.add(rbtnBisnis);
        rbtnBisnis.setText("Bisnis");

        ButtonGroup.add(rbtnEksekutif);
        rbtnEksekutif.setText("Eksekutif");

        comboKereta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboKereta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboKeretaActionPerformed(evt);
            }
        });

        lblInfo.setText("  ");

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Silahkan Pilih Kereta");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(196, 196, 196))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel2)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jButton2.setText("Keluar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbtnEkonomi)
                        .addGap(18, 18, 18)
                        .addComponent(rbtnBisnis)
                        .addGap(18, 18, 18)
                        .addComponent(rbtnEksekutif))
                    .addComponent(lblInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboKereta, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap(197, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(comboKereta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnEkonomi)
                    .addComponent(rbtnBisnis)
                    .addComponent(rbtnEksekutif))
                .addGap(18, 18, 18)
                .addComponent(lblHarga)
                .addGap(18, 18, 18)
                .addComponent(lblInfo)
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(118, 118, 118))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void comboKeretaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboKeretaActionPerformed
    aturKetersediaanKelas();
    }//GEN-LAST:event_comboKeretaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new FormRute(noUrut, nim, nama).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormPilihKereta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPilihKereta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPilihKereta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPilihKereta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup ButtonGroup;
    private javax.swing.JComboBox<String> comboKereta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblHarga;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JRadioButton rbtnBisnis;
    private javax.swing.JRadioButton rbtnEkonomi;
    private javax.swing.JRadioButton rbtnEksekutif;
    // End of variables declaration//GEN-END:variables
}
