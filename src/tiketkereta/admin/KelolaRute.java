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


public class KelolaRute extends javax.swing.JFrame {
    DefaultTableModel tabModel;
    DefaultComboBoxModel<String> comboKeretaModel;
    private String selectedRuteId = null;
    
    public KelolaRute() {
        initComponents();
        showData();
        loadKereta();
        setLocationRelativeTo(this);
    }
    
    private void loadKereta(){
        comboKeretaModel = new DefaultComboBoxModel<>();
        cbPilihKereta.setModel(comboKeretaModel);
        
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "SELECT * FROM kereta";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                comboKeretaModel.addElement(rs.getString("nama_kereta"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    
    private void showData(){
        Object[] baris = {"ID Rute", "Kereta", "Asal", "Tujuan"};
        tabModel = new DefaultTableModel(null, baris);
        jTableRute.setModel(tabModel);
        
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "SELECT r.id_rute, k.nama_kereta, r.asal, r.tujuan "
                    + "FROM rute r JOIN kereta k ON r.id_kereta = k.id_kereta ORDER BY r.id_rute ASC";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                tabModel.addRow(new Object[]{
                    rs.getString("id_rute"),
                    rs.getString("nama_kereta"),
                    rs.getString("asal"),
                    rs.getString("tujuan")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void clear(){
        cbPilihKereta.setSelectedIndex(0);
        cbAsal.setSelectedIndex(0);
        cbTujuan.setSelectedIndex(0);
        jTableRute.clearSelection();
        selectedRuteId = null;
        btnRute.setEnabled(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new tiketkereta.ImagePanel(new javax.swing.ImageIcon(getClass().getResource("/bg_admin.jpg")).getImage());
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnRute = new javax.swing.JButton();
        btnEditRute = new javax.swing.JButton();
        btnHapusRute = new javax.swing.JButton();
        btnBersihkan = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableRute = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        cbPilihKereta = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbAsal = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbTujuan = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MENU KELOLA RUTE KERETA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(322, 322, 322))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        btnRute.setBackground(new java.awt.Color(255, 102, 0));
        btnRute.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRute.setForeground(new java.awt.Color(255, 255, 255));
        btnRute.setText("Tambah Rute Kereta ");
        btnRute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRuteActionPerformed(evt);
            }
        });

        btnEditRute.setBackground(new java.awt.Color(255, 102, 0));
        btnEditRute.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditRute.setForeground(new java.awt.Color(255, 255, 255));
        btnEditRute.setText("Edit Rute Kereta");
        btnEditRute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditRuteActionPerformed(evt);
            }
        });

        btnHapusRute.setBackground(new java.awt.Color(255, 0, 0));
        btnHapusRute.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHapusRute.setForeground(new java.awt.Color(255, 255, 255));
        btnHapusRute.setText("Hapus Rute Kereta");
        btnHapusRute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusRuteActionPerformed(evt);
            }
        });

        btnBersihkan.setBackground(new java.awt.Color(255, 102, 0));
        btnBersihkan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBersihkan.setForeground(new java.awt.Color(255, 255, 255));
        btnBersihkan.setText("Bersihkan");
        btnBersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBersihkanActionPerformed(evt);
            }
        });

        btnKembali.setBackground(new java.awt.Color(0, 0, 255));
        btnKembali.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnKembali.setForeground(new java.awt.Color(255, 255, 255));
        btnKembali.setText("Kembali");
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });

        jTableRute.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableRute.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableRuteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableRute);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Pilih Kereta ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Pilih Asal Stasiun");

        cbAsal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jakarta", "Bandung", "Solo", "Yogyakarta", "Malang", "Surabaya" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Pilih Tujuan Stasiun");

        cbTujuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jakarta", "Bandung", "Solo", "Yogyakarta", "Malang", "Surabaya", " " }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbPilihKereta, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbAsal, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(cbTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(116, 116, 116))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnRute)
                                .addGap(34, 34, 34)
                                .addComponent(btnEditRute)
                                .addGap(358, 358, 358)
                                .addComponent(btnKembali)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnBersihkan)
                                .addGap(35, 35, 35)
                                .addComponent(btnHapusRute)
                                .addGap(189, 189, 189)))))
                .addContainerGap())
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
                        .addComponent(cbPilihKereta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbAsal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBersihkan)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRute)
                        .addComponent(btnEditRute)
                        .addComponent(btnKembali)
                        .addComponent(btnHapusRute)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
    new AdminMenu().setVisible(true);
       this.dispose();       
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void btnBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBersihkanActionPerformed
       clear();
    }//GEN-LAST:event_btnBersihkanActionPerformed

    private void btnHapusRuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusRuteActionPerformed
        if (selectedRuteId == null) {
            JOptionPane.showMessageDialog(this, "Pilih data rute yang akan dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.YES_OPTION){
            try {
                Connection conn = Koneksi.getConnection();
                String sql = "DELETE FROM rute WHERE id_rute = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, selectedRuteId);
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
    }//GEN-LAST:event_btnHapusRuteActionPerformed

    private void btnEditRuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditRuteActionPerformed
        if (selectedRuteId == null) {
            JOptionPane.showMessageDialog(this, "Pilih data rute yang akan diupdate!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(cbAsal.getSelectedItem().toString().equals(cbTujuan.getSelectedItem().toString())){
            JOptionPane.showMessageDialog(this, "Asal dan Tujuan tidak boleh sama!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "UPDATE rute SET id_kereta = ?, asal = ?, tujuan = ? WHERE id_rute = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, getIdKeretaByName(cbPilihKereta.getSelectedItem().toString()));
            ps.setString(2, cbAsal.getSelectedItem().toString());
            ps.setString(3, cbTujuan.getSelectedItem().toString());
            ps.setString(4, selectedRuteId);
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil diupdate");
            ps.close();
            conn.close();
            
            showData();
            clear();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mengupdate data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnEditRuteActionPerformed

    private void btnRuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRuteActionPerformed
        if(cbAsal.getSelectedItem().toString().equals(cbTujuan.getSelectedItem().toString())){
            JOptionPane.showMessageDialog(this, "Asal dan Tujuan tidak boleh sama!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "INSERT INTO rute (id_kereta, asal, tujuan) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, getIdKeretaByName(cbPilihKereta.getSelectedItem().toString()));
            ps.setString(2, cbAsal.getSelectedItem().toString());
            ps.setString(3, cbTujuan.getSelectedItem().toString());
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan");
            ps.close();
            conn.close();
            
            showData();
            clear();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menambahkan data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnRuteActionPerformed

    private void jTableRuteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableRuteMouseClicked
        int row = jTableRute.getSelectedRow();
        if (row == -1) return;

        selectedRuteId = jTableRute.getValueAt(row, 0).toString();
        cbPilihKereta.setSelectedItem(jTableRute.getValueAt(row, 1).toString());
        cbAsal.setSelectedItem(jTableRute.getValueAt(row, 2).toString());
        cbTujuan.setSelectedItem(jTableRute.getValueAt(row, 3).toString());
        btnRute.setEnabled(false);
    }//GEN-LAST:event_jTableRuteMouseClicked

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KelolaRute.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KelolaRute.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KelolaRute.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KelolaRute.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KelolaRute().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBersihkan;
    private javax.swing.JButton btnEditRute;
    private javax.swing.JButton btnHapusRute;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnRute;
    private javax.swing.JComboBox<String> cbAsal;
    private javax.swing.JComboBox<String> cbPilihKereta;
    private javax.swing.JComboBox<String> cbTujuan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableRute;
    // End of variables declaration//GEN-END:variables
}
