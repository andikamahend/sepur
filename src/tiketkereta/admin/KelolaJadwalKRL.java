package tiketkereta.admin;

import tiketkereta.Koneksi; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class KelolaJadwalKRL extends javax.swing.JFrame {
    private String sql;
    private int selectedJadwalId = -1;
    
    public KelolaJadwalKRL() {
         initComponents();
            loadStasiunToComboBox(); // Muat data stasiun ke ComboBox
            tampilkanData(); // Panggil method untuk menampilkan data
            bersihkan(); // Bersihkan form saat pertama kali dibuka
            this.setLocationRelativeTo(null); 
    }

    private void loadStasiunToComboBox() {
            sql = "SELECT nama_stasiun FROM stasiun ORDER BY nama_stasiun ASC";
            // Menggunakan try-with-resources untuk memastikan koneksi dan statement ditutup
            try (Connection conn = Koneksi.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet res = stmt.executeQuery(sql)) {

                cbAsal.removeAllItems(); // Bersihkan item lama
                cbTujuan.removeAllItems(); // Bersihkan item lama
                while(res.next()){
                    String namaStasiun = res.getString("nama_stasiun");
                    cbAsal.addItem(namaStasiun);
                    cbTujuan.addItem(namaStasiun);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal memuat data stasiun: " + e.getMessage());
            }
    }

    // Method untuk menampilkan data dari database ke JTable
    private void tampilkanData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Jadwal");
        model.addColumn("Stasiun Asal");
        model.addColumn("Stasiun Tujuan");
        model.addColumn("Waktu Berangkat");
        jTableKereta.setModel(model);

        sql = "SELECT jk.id_jadwal, s.nama_stasiun, jk.tujuan, jk.waktu " +
              "FROM jadwal_krl jk " +
              "JOIN stasiun s ON jk.id_stasiun = s.id_stasiun " +
              "ORDER BY s.nama_stasiun, jk.waktu";
              
        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery(sql)) {
            
            while (res.next()) {
                model.addRow(new Object[]{
                    res.getInt("id_jadwal"),
                    res.getString("nama_stasiun"),
                    res.getString("tujuan"),
                    res.getTime("waktu").toLocalTime()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal Mengambil Data: " + e.getMessage());
        }
    }

    // Method untuk membersihkan form input
    private void bersihkan() {
        cbAsal.setSelectedIndex(-1);
        cbTujuan.setSelectedIndex(-1);
        timePicker1.clear();
        selectedJadwalId = -1;
        cbAsal.requestFocus();
    }

    // Method untuk mendapatkan id_stasiun dari nama_stasiun
    private int getStasiunId(String namaStasiun) {
        int id = -1;
        sql = "SELECT id_stasiun FROM stasiun WHERE nama_stasiun = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, namaStasiun);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    id = res.getInt("id_stasiun");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error saat mencari ID stasiun: " + e.getMessage());
        }
        return id;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new tiketkereta.ImagePanel(new javax.swing.ImageIcon(getClass().getResource("/bg_admin.jpg")).getImage());
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnJadwal = new javax.swing.JButton();
        btnEditJadwal = new javax.swing.JButton();
        btnHapusJadwal = new javax.swing.JButton();
        btnBersihkan = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableKereta = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        timePicker1 = new com.github.lgooddatepicker.components.TimePicker();
        cbAsal = new javax.swing.JComboBox<>();
        cbTujuan = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));

        jLabel1.setText("MENU KELOLA JADWAL KERETA KRL");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(204, 204, 204))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(14, 14, 14))
        );

        btnJadwal.setText("Tambah Jadwal");
        btnJadwal.setBackground(new java.awt.Color(255, 102, 0));
        btnJadwal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnJadwal.setForeground(new java.awt.Color(255, 255, 255));
        btnJadwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJadwalActionPerformed(evt);
            }
        });

        btnEditJadwal.setText("Edit Jadwal");
        btnEditJadwal.setBackground(new java.awt.Color(255, 102, 0));
        btnEditJadwal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditJadwal.setForeground(new java.awt.Color(255, 255, 255));
        btnEditJadwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditJadwalActionPerformed(evt);
            }
        });

        btnHapusJadwal.setText("Hapus Jadwal");
        btnHapusJadwal.setBackground(new java.awt.Color(255, 0, 0));
        btnHapusJadwal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHapusJadwal.setForeground(new java.awt.Color(255, 255, 255));
        btnHapusJadwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusJadwalActionPerformed(evt);
            }
        });

        btnBersihkan.setText("Bersihkan");
        btnBersihkan.setBackground(new java.awt.Color(255, 102, 0));
        btnBersihkan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBersihkan.setForeground(new java.awt.Color(255, 255, 255));
        btnBersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBersihkanActionPerformed(evt);
            }
        });

        btnKembali.setText("Kembali");
        btnKembali.setBackground(new java.awt.Color(0, 0, 255));
        btnKembali.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnKembali.setForeground(new java.awt.Color(255, 255, 255));
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });

        jLabel5.setText("Stasiun Asal");
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));

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

        jLabel6.setText("Stasiun Tujuan");
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));

        jLabel7.setText("Waktu Berangkat");
        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));

        timePicker1.setBackground(new java.awt.Color(255, 255, 255));

        cbAsal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAsalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbAsal, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(cbTujuan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(240, 240, 240)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(timePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(173, 173, 173)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnJadwal)
                        .addGap(34, 34, 34)
                        .addComponent(btnEditJadwal)
                        .addGap(28, 28, 28)
                        .addComponent(btnBersihkan)
                        .addGap(44, 44, 44)
                        .addComponent(btnHapusJadwal)
                        .addGap(40, 40, 40)
                        .addComponent(btnKembali)
                        .addGap(27, 27, 27))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(12, 12, 12)
                        .addComponent(cbAsal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(timePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(cbTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnJadwal)
                    .addComponent(btnEditJadwal)
                    .addComponent(btnBersihkan)
                    .addComponent(btnKembali)
                    .addComponent(btnHapusJadwal))
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
     bersihkan();
    }//GEN-LAST:event_btnBersihkanActionPerformed

    private void btnHapusJadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusJadwalActionPerformed
        if (selectedJadwalId == -1) {
            JOptionPane.showMessageDialog(this, "Silakan pilih jadwal yang ingin dihapus terlebih dahulu.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus jadwal ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            sql = "DELETE FROM jadwal_krl WHERE id_jadwal = ?";
            try (Connection conn = Koneksi.getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {
                
                pst.setInt(1, selectedJadwalId);
                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Jadwal berhasil dihapus.");
                    tampilkanData();
                    bersihkan();
                } else {
                    JOptionPane.showMessageDialog(this, "Jadwal gagal dihapus.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal menghapus jadwal: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnHapusJadwalActionPerformed

    private void btnEditJadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditJadwalActionPerformed
        if (selectedJadwalId == -1) {
            JOptionPane.showMessageDialog(this, "Silakan pilih jadwal yang ingin diubah terlebih dahulu.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (cbAsal.getSelectedIndex() == -1 || cbTujuan.getSelectedIndex() == -1 || timePicker1.getTime() == null) {
            JOptionPane.showMessageDialog(this, "Semua kolom harus diisi.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String stasiunAsal = cbAsal.getSelectedItem().toString();
        String stasiunTujuan = cbTujuan.getSelectedItem().toString();
        LocalTime waktu = timePicker1.getTime();

        int stasiunId = getStasiunId(stasiunAsal);
        if (stasiunId == -1) {
            JOptionPane.showMessageDialog(this, "Stasiun asal '" + stasiunAsal + "' tidak ditemukan.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        sql = "UPDATE jadwal_krl SET id_stasiun = ?, tujuan = ?, waktu = ? WHERE id_jadwal = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setInt(1, stasiunId);
            pst.setString(2, stasiunTujuan);
            pst.setObject(3, waktu);
            pst.setInt(4, selectedJadwalId);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Jadwal berhasil diubah.");
                tampilkanData();
                bersihkan();
            } else {
                JOptionPane.showMessageDialog(this, "Jadwal gagal diubah.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mengubah jadwal: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditJadwalActionPerformed

    private void btnJadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJadwalActionPerformed
        if (cbAsal.getSelectedIndex() == -1 || cbTujuan.getSelectedIndex() == -1 || timePicker1.getTime() == null) {
            JOptionPane.showMessageDialog(this, "Semua kolom harus diisi.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String stasiunAsal = cbAsal.getSelectedItem().toString();
        String stasiunTujuan = cbTujuan.getSelectedItem().toString();
        LocalTime waktu = timePicker1.getTime();

        int stasiunId = getStasiunId(stasiunAsal);
        if (stasiunId == -1) {
            JOptionPane.showMessageDialog(this, "Stasiun asal '" + stasiunAsal + "' tidak ditemukan.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        sql = "INSERT INTO jadwal_krl (id_stasiun, tujuan, waktu) VALUES (?, ?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setInt(1, stasiunId);
            pst.setString(2, stasiunTujuan);
            pst.setObject(3, waktu);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Jadwal baru berhasil ditambahkan.");
            tampilkanData();
            bersihkan();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menambahkan jadwal: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnJadwalActionPerformed

    private void jTableKeretaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableKeretaMouseClicked
    int baris = jTableKereta.rowAtPoint(evt.getPoint());
        if (baris < 0) return;
        
        selectedJadwalId = (int) jTableKereta.getValueAt(baris, 0);
        String stasiunAsal = jTableKereta.getValueAt(baris, 1).toString();
        String stasiunTujuan = jTableKereta.getValueAt(baris, 2).toString();
        LocalTime waktu = (LocalTime) jTableKereta.getValueAt(baris, 3);
        
        cbAsal.setSelectedItem(stasiunAsal);
        cbTujuan.setSelectedItem(stasiunTujuan);
        timePicker1.setTime(waktu);
    }//GEN-LAST:event_jTableKeretaMouseClicked

    private void cbAsalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAsalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbAsalActionPerformed

    public static void main(String args[]) {
     try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KelolaJadwalKRL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KelolaJadwalKRL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KelolaJadwalKRL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KelolaJadwalKRL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KelolaJadwalKRL().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBersihkan;
    private javax.swing.JButton btnEditJadwal;
    private javax.swing.JButton btnHapusJadwal;
    private javax.swing.JButton btnJadwal;
    private javax.swing.JButton btnKembali;
    private javax.swing.JComboBox<String> cbAsal;
    private javax.swing.JComboBox<String> cbTujuan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableKereta;
    private com.github.lgooddatepicker.components.TimePicker timePicker1;
    // End of variables declaration//GEN-END:variables
}
