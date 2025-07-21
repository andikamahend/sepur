package tiketkereta.KAJJ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import tiketkereta.Koneksi;
import tiketkereta.MenuUtama;

public class RiwayatPemesanan extends javax.swing.JFrame {
    private String idUser, nim, namaMhs;
    DefaultTableModel tabModel;

    public RiwayatPemesanan(String idUser, String nim, String namaMhs) {
        initComponents();
        this.idUser = idUser;
        this.nim = nim;
        this.namaMhs = namaMhs;
        
        // Atur judul tabel
        tabModel = new DefaultTableModel(null, new Object[]{"ID Pesanan", "Tanggal Pesan", "Kereta", "Rute", "Total Bayar"});
        tabelRiwayat.setModel(tabModel);
        
        loadRiwayat();
        setLocationRelativeTo(null);
    }
    
    private void loadRiwayat() {
        // Hapus data lama di tabel
        tabModel.getDataVector().removeAllElements();
        tabModel.fireTableDataChanged();
        
        String sql = "SELECT p.id_pemesanan, p.tanggal_pesan, k.nama_kereta, CONCAT(r.asal, ' -> ', r.tujuan) as rute, p.total_harga " +
                     "FROM pemesanan p " +
                     "JOIN rute r ON p.id_rute = r.id_rute " +
                     "JOIN kereta k ON r.id_kereta = k.id_kereta " +
                     "WHERE p.id_user = ? ORDER BY p.tanggal_pesan DESC";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, Integer.parseInt(idUser));
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Object[] rowData = {
                    rs.getInt("id_pemesanan"),
                    rs.getString("tanggal_pesan"),
                    rs.getString("nama_kereta"),
                    rs.getString("rute"),
                    "Rp" + rs.getInt("total_harga")
                };
                tabModel.addRow(rowData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal memuat riwayat pemesanan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Bkembali = new javax.swing.JButton();
        BlihatD = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelRiwayat = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1920, 1080));

        jPanel1.setBackground(new java.awt.Color(255, 0, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("RIWAYAT");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(256, 256, 256)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        Bkembali.setText("Kembali");
        Bkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BkembaliActionPerformed(evt);
            }
        });

        BlihatD.setText("Lihat Detail");
        BlihatD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BlihatDActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(new javax.swing.border.MatteBorder(new javax.swing.ImageIcon(getClass().getResource("/tiketkereta/batik.png")))); // NOI18N

        tabelRiwayat.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelRiwayat);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(Bkembali)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BlihatD)
                .addGap(55, 55, 55))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Bkembali)
                    .addComponent(BlihatD))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BkembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BkembaliActionPerformed
        new MenuUtama(this.namaMhs, this.nim, this.idUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BkembaliActionPerformed

    private void BlihatDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BlihatDActionPerformed
        int selectedRow = tabelRiwayat.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Silakan pilih salah satu riwayat pesanan terlebih dahulu.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int idPemesanan = (int) tabelRiwayat.getValueAt(selectedRow, 0);

        try (Connection conn = Koneksi.getConnection()) {
            // 1. Ambil data utama pemesanan
            String sqlInfo = "SELECT p.tanggal_pesan, r.asal, r.tujuan, k.nama_kereta, kk.kelas " +
                             "FROM pemesanan p " +
                             "JOIN rute r ON p.id_rute = r.id_rute " +
                             "JOIN kereta_kelas kk ON p.id_kelas = kk.id_kelas " +
                             "JOIN kereta k ON r.id_kereta = k.id_kereta " +
                             "WHERE p.id_pemesanan = ?";
            PreparedStatement psInfo = conn.prepareStatement(sqlInfo);
            psInfo.setInt(1, idPemesanan);
            ResultSet rsInfo = psInfo.executeQuery();
            
            if (rsInfo.next()) {
                String tanggal = rsInfo.getString("tanggal_pesan").split(" ")[0]; // Ambil tanggalnya saja
                String asal = rsInfo.getString("asal");
                String tujuan = rsInfo.getString("tujuan");
                String kereta = rsInfo.getString("nama_kereta");
                String kelas = rsInfo.getString("kelas");

                // 2. Ambil daftar penumpang
                ArrayList<Penumpang> daftarPenumpang = new ArrayList<>();
                String sqlPenumpang = "SELECT * FROM pemesanan_detail WHERE id_pemesanan = ?";
                PreparedStatement psPenumpang = conn.prepareStatement(sqlPenumpang);
                psPenumpang.setInt(1, idPemesanan);
                ResultSet rsPenumpang = psPenumpang.executeQuery();

                while (rsPenumpang.next()) {
                    Penumpang p = new Penumpang(
                        rsPenumpang.getString("nama_penumpang"),
                        rsPenumpang.getString("nik"),
                        rsPenumpang.getString("jenis_kelamin"),
                        rsPenumpang.getString("tipe_penumpang"),
                        rsPenumpang.getInt("usia")
                    );
                    daftarPenumpang.add(p);
                }

                // 3. Buka FormOutput dengan data yang sudah diambil
                FormOutput formOutput = new FormOutput(asal, tujuan, tanggal, kereta, kelas, daftarPenumpang, idUser, nim, namaMhs, false );
                
                // Modifikasi FormOutput agar tidak menyimpan lagi
                formOutput.setTitle("Detail Riwayat Pemesanan");
                // Kita bisa menambahkan method di FormOutput untuk menonaktifkan tombol simpan jika diperlukan
                
                formOutput.setVisible(true);
                this.dispose();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal mengambil detail pesanan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BlihatDActionPerformed

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
            java.util.logging.Logger.getLogger(RiwayatPemesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RiwayatPemesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RiwayatPemesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RiwayatPemesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bkembali;
    private javax.swing.JButton BlihatD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelRiwayat;
    // End of variables declaration//GEN-END:variables
}
