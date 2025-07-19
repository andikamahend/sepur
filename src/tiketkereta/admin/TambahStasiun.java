package tiketkereta.admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import tiketkereta.Koneksi;


public class TambahStasiun extends javax.swing.JFrame { 
    private int selectedStasiunId = -1;
    public TambahStasiun() {
        initComponents();
        tampilkanData();
        bersihkan();
        this.setLocationRelativeTo(null); // Form tampil di tengah layar
    }
    
// Method untuk menampilkan data dari database ke JTable
    private void tampilkanData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Stasiun");
        model.addColumn("Nama Stasiun");
        jTableKereta.setModel(model);

        String sql = "SELECT * FROM stasiun ORDER BY id_stasiun ASC";

        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery(sql)) {

            while (res.next()) {
                model.addRow(new Object[]{
                    res.getInt("id_stasiun"),
                    res.getString("nama_stasiun")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal Mengambil Data Stasiun: " + e.getMessage());
        }
    }

    // Method untuk membersihkan form input
    private void bersihkan() {
        txtNamaStasiun.setText("");
        selectedStasiunId = -1;
        txtNamaStasiun.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnStasiun = new javax.swing.JButton();
        btnEditStasiun = new javax.swing.JButton();
        btnHapusStasiun = new javax.swing.JButton();
        btnBersihkan = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();
        txtNamaStasiun = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableKereta = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 153, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MENU TAMBAH STASIUN");

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

        btnStasiun.setText("Tambah Stasiun");
        btnStasiun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStasiunActionPerformed(evt);
            }
        });

        btnEditStasiun.setText("Edit Stasiun");
        btnEditStasiun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditStasiunActionPerformed(evt);
            }
        });

        btnHapusStasiun.setText("Hapus Stasiun");
        btnHapusStasiun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusStasiunActionPerformed(evt);
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

        txtNamaStasiun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaStasiunActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Nama Stasiun");

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNamaStasiun, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnStasiun)
                                .addGap(34, 34, 34)
                                .addComponent(btnEditStasiun)
                                .addGap(35, 35, 35)
                                .addComponent(btnHapusStasiun)
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
                .addComponent(txtNamaStasiun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStasiun)
                    .addComponent(btnEditStasiun)
                    .addComponent(btnHapusStasiun)
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

    private void txtNamaStasiunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaStasiunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaStasiunActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
     new AdminMenu().setVisible(true);
        this.dispose();  
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void btnBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBersihkanActionPerformed
     bersihkan();
    }//GEN-LAST:event_btnBersihkanActionPerformed

    private void btnHapusStasiunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusStasiunActionPerformed
         if (selectedStasiunId == -1) {
            JOptionPane.showMessageDialog(this, "Silakan pilih stasiun yang ingin dihapus.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus stasiun ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM stasiun WHERE id_stasiun = ?";
            try (Connection conn = Koneksi.getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setInt(1, selectedStasiunId);
                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Stasiun berhasil dihapus.");
                    tampilkanData();
                    bersihkan();
                } else {
                    JOptionPane.showMessageDialog(this, "Stasiun gagal dihapus.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal menghapus stasiun: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnHapusStasiunActionPerformed

    private void btnEditStasiunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditStasiunActionPerformed
       if (selectedStasiunId == -1) {
            JOptionPane.showMessageDialog(this, "Silakan pilih stasiun yang ingin diubah.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String namaStasiun = txtNamaStasiun.getText().trim();
        if (namaStasiun.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama stasiun tidak boleh kosong.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "UPDATE stasiun SET nama_stasiun = ? WHERE id_stasiun = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, namaStasiun);
            pst.setInt(2, selectedStasiunId);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Nama stasiun berhasil diubah.");
                tampilkanData();
                bersihkan();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal mengubah nama stasiun.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mengubah stasiun: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditStasiunActionPerformed

    private void btnStasiunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStasiunActionPerformed
       String namaStasiun = txtNamaStasiun.getText().trim();
        if (namaStasiun.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama stasiun tidak boleh kosong.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "INSERT INTO stasiun (nama_stasiun) VALUES (?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, namaStasiun);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Stasiun baru berhasil ditambahkan.");
            tampilkanData();
            bersihkan();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menambahkan stasiun: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnStasiunActionPerformed

    private void jTableKeretaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableKeretaMouseClicked
      int baris = jTableKereta.rowAtPoint(evt.getPoint());
        if (baris < 0) return;

        selectedStasiunId = (int) jTableKereta.getValueAt(baris, 0);
        String namaStasiun = jTableKereta.getValueAt(baris, 1).toString();

        txtNamaStasiun.setText(namaStasiun);
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
            java.util.logging.Logger.getLogger(TambahStasiun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TambahStasiun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TambahStasiun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TambahStasiun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TambahStasiun().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBersihkan;
    private javax.swing.JButton btnEditStasiun;
    private javax.swing.JButton btnHapusStasiun;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnStasiun;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableKereta;
    private javax.swing.JTextField txtNamaStasiun;
    // End of variables declaration//GEN-END:variables
}
