package tiketkereta.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import tiketkereta.Koneksi;


public class TambahKereta extends javax.swing.JFrame {
     DefaultTableModel tabModel;
     private String selectedKeretaId = null; 
    public TambahKereta() {
        initComponents();
        showData();
        setLocationRelativeTo(this);
    }
    private void showData(){
        Object[] baris = {"ID Kereta", "Nama Kereta"};
        tabModel = new DefaultTableModel(null, baris);
        jTableKereta.setModel(tabModel);
        
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "SELECT * FROM kereta ORDER BY id_kereta ASC";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                String id_kereta = rs.getString("id_kereta");
                String nama_kereta = rs.getString("nama_kereta");
                Object[] data = {id_kereta, nama_kereta};
                tabModel.addRow(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal memuat data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clear(){
        txtNamaKereta.setText("");
        jTableKereta.clearSelection(); // Menghapus seleksi pada tabel
        selectedKeretaId = null; // Reset ID yang dipilih
        btnKereta.setEnabled(true); // Aktifkan kembali tombol tambah
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnKereta = new javax.swing.JButton();
        btnEditKereta = new javax.swing.JButton();
        btnHapusKelas = new javax.swing.JButton();
        btnBersihkan = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();
        txtNamaKereta = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableKereta = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 153, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MENU TAMBAH KERETA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(256, 256, 256))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(14, 14, 14))
        );

        btnKereta.setText("Tambah Kereta ");
        btnKereta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeretaActionPerformed(evt);
            }
        });

        btnEditKereta.setText("Edit Kereta");
        btnEditKereta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditKeretaActionPerformed(evt);
            }
        });

        btnHapusKelas.setText("Hapus Kereta");
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

        txtNamaKereta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaKeretaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Nama Kereta");

        jTableKereta.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableKereta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableKeretaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableKereta);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNamaKereta, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnKereta)
                                .addGap(34, 34, 34)
                                .addComponent(btnEditKereta)
                                .addGap(35, 35, 35)
                                .addComponent(btnHapusKelas)
                                .addGap(41, 41, 41)
                                .addComponent(btnBersihkan)
                                .addGap(36, 36, 36)
                                .addComponent(btnKembali)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNamaKereta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnKereta)
                    .addComponent(btnEditKereta)
                    .addComponent(btnHapusKelas)
                    .addComponent(btnBersihkan)
                    .addComponent(btnKembali))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 82, Short.MAX_VALUE))
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

    private void txtNamaKeretaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaKeretaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaKeretaActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
     new AdminMenu().setVisible(true);
        this.dispose();  
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void btnBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBersihkanActionPerformed
        clear();
    }//GEN-LAST:event_btnBersihkanActionPerformed

    private void btnHapusKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusKelasActionPerformed
        if (selectedKeretaId == null) {
            JOptionPane.showMessageDialog(this, "Pilih data kereta yang akan dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.YES_OPTION){
            try {
                Connection conn = Koneksi.getConnection();
                // Hapus data dari child table terlebih dahulu jika ada foreign key constraint
                // Contoh: DELETE FROM kereta_kelas WHERE id_kereta = ?
                // Contoh: DELETE FROM rute WHERE id_kereta = ?
                
                String sql = "DELETE FROM kereta WHERE id_kereta = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, selectedKeretaId);
                int result = ps.executeUpdate();
                
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
                } else {
                    JOptionPane.showMessageDialog(this, "Data gagal dihapus", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
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

    private void btnEditKeretaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditKeretaActionPerformed
        if (selectedKeretaId == null) {
            JOptionPane.showMessageDialog(this, "Pilih data kereta yang akan diupdate!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String namaKereta = txtNamaKereta.getText();
        if (namaKereta.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama kereta tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
         try {
            Connection conn = Koneksi.getConnection();
            String sql = "UPDATE kereta SET nama_kereta = ? WHERE id_kereta = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, namaKereta);
            ps.setString(2, selectedKeretaId);
            int result = ps.executeUpdate();
            
            if (result > 0) {
                 JOptionPane.showMessageDialog(this, "Data berhasil diupdate");
            } else {
                 JOptionPane.showMessageDialog(this, "Data gagal diupdate", "Error", JOptionPane.ERROR_MESSAGE);
            }
           
            ps.close();
            conn.close();
            
            showData();
            clear();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mengupdate data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnEditKeretaActionPerformed

    private void btnKeretaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeretaActionPerformed
        String namaKereta = txtNamaKereta.getText();
        
        if (namaKereta.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama kereta tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Connection conn = Koneksi.getConnection();
            String sql = "INSERT INTO kereta (nama_kereta) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, namaKereta);
            
            int result = ps.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan");
            } else {
                JOptionPane.showMessageDialog(this, "Data gagal ditambahkan", "Error", JOptionPane.ERROR_MESSAGE);
            }

            ps.close();
            conn.close();

            showData();
            clear();
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(this, "Gagal menambahkan data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
             e.printStackTrace();
        }
    }//GEN-LAST:event_btnKeretaActionPerformed

    private void jTableKeretaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableKeretaMouseClicked
       int row = jTableKereta.getSelectedRow();
        if (row == -1) {
            return;
        }
        // Simpan ID yang dipilih
        selectedKeretaId = jTableKereta.getValueAt(row, 0).toString();
        
        // Tampilkan nama kereta di text field
        txtNamaKereta.setText(jTableKereta.getValueAt(row, 1).toString());
        
        btnKereta.setEnabled(false); 
    }//GEN-LAST:event_jTableKeretaMouseClicked

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
            java.util.logging.Logger.getLogger(TambahKereta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TambahKereta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TambahKereta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TambahKereta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TambahKereta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBersihkan;
    private javax.swing.JButton btnEditKereta;
    private javax.swing.JButton btnHapusKelas;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnKereta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableKereta;
    private javax.swing.JTextField txtNamaKereta;
    // End of variables declaration//GEN-END:variables
}
