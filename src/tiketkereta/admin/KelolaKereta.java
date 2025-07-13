package tiketkereta.admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import tiketkereta.Koneksi;
import tiketkereta.Login;

public class KelolaKereta extends javax.swing.JFrame {
        DefaultTableModel tabModel;
        DefaultComboBoxModel<String> comboModel;
        private String selectedKelasId = null;
    public KelolaKereta() {
        initComponents();
        showData();
        loadKereta();
        setLocationRelativeTo(this);
    }
    
     private void loadKereta(){
        comboModel = new DefaultComboBoxModel<>();
        cbKereta.setModel(comboModel);
        
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "SELECT * FROM kereta";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                comboModel.addElement(rs.getString("nama_kereta"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     private void showData(){
        Object[] baris = {"ID Kelas", "Kereta", "Kelas", "Harga"};
        tabModel = new DefaultTableModel(null, baris);
        jTableKelas.setModel(tabModel);
        
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "SELECT kk.id_kelas, k.nama_kereta, kk.kelas, kk.harga "
                    + "FROM kereta_kelas kk JOIN kereta k ON kk.id_kereta = k.id_kereta ORDER BY kk.id_kelas ASC";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                tabModel.addRow(new Object[]{
                    rs.getString("id_kelas"),
                    rs.getString("nama_kereta"),
                    rs.getString("kelas"),
                    rs.getString("harga")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
     private void clear(){
         txtHarga.setText("");
         cbKereta.setSelectedIndex(0);
         buttonGroup1.clearSelection(); // Membersihkan pilihan radio button
         jTableKelas.clearSelection();
         selectedKelasId = null;
         btnKelasKereta.setEnabled(true);
     }
     
     private int getIdKeretaByName(String namaKereta) throws SQLException{
         Connection conn = Koneksi.getConnection();
         String sql = "SELECT id_kereta FROM kereta WHERE nama_kereta = ?";
         PreparedStatement ps = conn.prepareStatement(sql);
         ps.setString(1, namaKereta);
         ResultSet rs = ps.executeQuery();
         if(rs.next()){
             return rs.getInt("id_kereta");
         }
         return 0;
     }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnKelasKereta = new javax.swing.JButton();
        btnEditKelas = new javax.swing.JButton();
        btnHapusKelas = new javax.swing.JButton();
        btnBersihkan = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        rbEkonomi = new javax.swing.JRadioButton();
        rbBisnis = new javax.swing.JRadioButton();
        rbEksekutif = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableKelas = new javax.swing.JTable();
        cbKereta = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 153, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MENU KELOLA KERETA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(236, 236, 236))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(14, 14, 14))
        );

        btnKelasKereta.setText("Tambah Kelas Kereta ");
        btnKelasKereta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKelasKeretaActionPerformed(evt);
            }
        });

        btnEditKelas.setText("Edit Kelas Kereta");
        btnEditKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditKelasActionPerformed(evt);
            }
        });

        btnHapusKelas.setText("Hapus Kelas Kereta");
        btnHapusKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusKelasActionPerformed(evt);
            }
        });

        btnBersihkan.setText("Bersihkan");
        btnBersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBersihkanActionPerformed(evt);
            }
        });

        btnKembali.setText("Kembali");
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Pilih Kereta");

        txtHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Kelas");

        buttonGroup1.add(rbEkonomi);
        rbEkonomi.setText("Ekonomi");
        rbEkonomi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbEkonomiActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbBisnis);
        rbBisnis.setText("Bisnis");
        rbBisnis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbBisnisActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbEksekutif);
        rbEksekutif.setText("Eksekutif");
        rbEksekutif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbEksekutifActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Harga");

        jTableKelas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableKelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableKelasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableKelas);

        cbKereta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKeretaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbKereta, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnKelasKereta))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btnEditKelas)
                        .addGap(35, 35, 35)
                        .addComponent(btnHapusKelas)
                        .addGap(41, 41, 41)
                        .addComponent(btnBersihkan)
                        .addGap(36, 36, 36)
                        .addComponent(btnKembali)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rbEkonomi)
                                .addGap(15, 15, 15)
                                .addComponent(rbBisnis)
                                .addGap(18, 18, 18)
                                .addComponent(rbEksekutif))
                            .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbEkonomi)
                            .addComponent(rbBisnis)
                            .addComponent(rbEksekutif)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbKereta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnKelasKereta)
                    .addComponent(btnEditKelas)
                    .addComponent(btnHapusKelas)
                    .addComponent(btnBersihkan)
                    .addComponent(btnKembali))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKelasKeretaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKelasKeretaActionPerformed
        String kelas = "";
        if (rbEkonomi.isSelected()) {
            kelas = "Ekonomi";
        } else if (rbBisnis.isSelected()) {
            kelas = "Bisnis";
        } else if (rbEksekutif.isSelected()) {
            kelas = "Eksekutif";
        }

        if (kelas.isEmpty() || txtHarga.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua data wajib diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "INSERT INTO kereta_kelas (id_kereta, kelas, harga) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, getIdKeretaByName(cbKereta.getSelectedItem().toString()));
            ps.setString(2, kelas);
            ps.setInt(3, Integer.parseInt(txtHarga.getText()));
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan");
            ps.close();
            conn.close();
            
            showData();
            clear();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menambahkan data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harga harus berupa angka!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnKelasKeretaActionPerformed

    private void btnBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBersihkanActionPerformed
        clear();
    }//GEN-LAST:event_btnBersihkanActionPerformed

    private void btnEditKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditKelasActionPerformed
       if (selectedKelasId == null) {
            JOptionPane.showMessageDialog(this, "Pilih data yang akan diupdate!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String kelas = "";
        if (rbEkonomi.isSelected()) {
            kelas = "Ekonomi";
        } else if (rbBisnis.isSelected()) {
            kelas = "Bisnis";
        } else if (rbEksekutif.isSelected()) {
            kelas = "Eksekutif";
        }

        if (kelas.isEmpty() || txtHarga.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua data wajib diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "UPDATE kereta_kelas SET id_kereta = ?, kelas = ?, harga = ? WHERE id_kelas = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, getIdKeretaByName(cbKereta.getSelectedItem().toString()));
            ps.setString(2, kelas);
            ps.setInt(3, Integer.parseInt(txtHarga.getText()));
            ps.setString(4, selectedKelasId);
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil diupdate");
            ps.close();
            conn.close();
            
            showData();
            clear();
            
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(this, "Gagal mengupdate data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harga harus berupa angka!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEditKelasActionPerformed

    private void btnHapusKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusKelasActionPerformed
        if (selectedKelasId == null) {
            JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.YES_OPTION){
            try {
                Connection conn = Koneksi.getConnection();
                String sql = "DELETE FROM kereta_kelas WHERE id_kelas = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, selectedKelasId);
                ps.executeUpdate();
                
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
                ps.close();
                conn.close();
                
                showData();
                clear();
            } catch (SQLException e) {
                 JOptionPane.showMessageDialog(this, "Gagal menghapus data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnHapusKelasActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
    new AdminMenu().setVisible(true);
        this.dispose();   // TODO add your handling code here:
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void txtHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaActionPerformed

    private void rbEkonomiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbEkonomiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbEkonomiActionPerformed

    private void rbBisnisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbBisnisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbBisnisActionPerformed

    private void rbEksekutifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbEksekutifActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbEksekutifActionPerformed

    private void jTableKelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableKelasMouseClicked
       int row = jTableKelas.getSelectedRow();
        if (row == -1) return;

        selectedKelasId = jTableKelas.getValueAt(row, 0).toString();
        cbKereta.setSelectedItem(jTableKelas.getValueAt(row, 1).toString());
        
        String kelas = jTableKelas.getValueAt(row, 2).toString();
        if(kelas.equalsIgnoreCase("Ekonomi")){
            rbEkonomi.setSelected(true);
        } else if(kelas.equalsIgnoreCase("Bisnis")){
            rbBisnis.setSelected(true);
        } else if(kelas.equalsIgnoreCase("Eksekutif")){
            rbEksekutif.setSelected(true);
        }
        
        txtHarga.setText(jTableKelas.getValueAt(row, 3).toString());
        btnKelasKereta.setEnabled(false);
    }//GEN-LAST:event_jTableKelasMouseClicked

    private void cbKeretaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKeretaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbKeretaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KelolaKereta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KelolaKereta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KelolaKereta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KelolaKereta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KelolaKereta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBersihkan;
    private javax.swing.JButton btnEditKelas;
    private javax.swing.JButton btnHapusKelas;
    private javax.swing.JButton btnKelasKereta;
    private javax.swing.JButton btnKembali;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbKereta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableKelas;
    private javax.swing.JRadioButton rbBisnis;
    private javax.swing.JRadioButton rbEkonomi;
    private javax.swing.JRadioButton rbEksekutif;
    private javax.swing.JTextField txtHarga;
    // End of variables declaration//GEN-END:variables
}
