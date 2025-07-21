package tiketkereta.admin; // Mendefinisikan bahwa kelas ini berada dalam paket 'tiketkereta.admin'

import javax.swing.JOptionPane; // Mengimpor kelas JOptionPane untuk menampilkan dialog konfirmasi
import tiketkereta.Login;// Mengimpor kelas Login dari paket 'tiketkereta' untuk navigasi kembali ke halaman login

public class AdminMenu extends javax.swing.JFrame {
    public AdminMenu() {
        initComponents();// Memanggil metode untuk menginisialisasi semua komponen GUI
        setLocationRelativeTo(this);// Mengatur posisi jendela agar muncul di tengah layar
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new tiketkereta.ImagePanel(new javax.swing.ImageIcon(getClass().getResource("/bg_admin.jpg")).getImage());
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnKereta = new javax.swing.JButton();
        btnRute = new javax.swing.JButton();
        btnUser = new javax.swing.JButton();
        btnTiketKereta = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnTambahKereta = new javax.swing.JButton();
        btnJadwal = new javax.swing.JButton();
        btnStasiun = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(785, 700));

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));
        jPanel1.setOpaque(false);

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MENU ADMIN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(326, 326, 326))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(14, 14, 14))
        );

        btnKereta.setBackground(new java.awt.Color(0, 102, 255));
        btnKereta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tiketkereta/admin/Logo Kelola Kereta.png"))); // NOI18N
        btnKereta.setBorder(null);
        btnKereta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeretaActionPerformed(evt);
            }
        });

        btnRute.setBackground(new java.awt.Color(0, 102, 255));
        btnRute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tiketkereta/admin/Logo Kelola Rute.png"))); // NOI18N
        btnRute.setBorder(null);
        btnRute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRuteActionPerformed(evt);
            }
        });

        btnUser.setBackground(new java.awt.Color(0, 102, 255));
        btnUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tiketkereta/admin/Logo Lihat User.png"))); // NOI18N
        btnUser.setBorder(null);
        btnUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserActionPerformed(evt);
            }
        });

        btnTiketKereta.setBackground(new java.awt.Color(0, 102, 255));
        btnTiketKereta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tiketkereta/admin/Logo Kelola Tiket.png"))); // NOI18N
        btnTiketKereta.setBorder(null);
        btnTiketKereta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTiketKeretaActionPerformed(evt);
            }
        });

        btnLogout.setBackground(new java.awt.Color(255, 0, 0));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Logout");
        btnLogout.setBorder(null);
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnTambahKereta.setBackground(new java.awt.Color(0, 102, 255));
        btnTambahKereta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tiketkereta/admin/Logo Tambah Kereta.png"))); // NOI18N
        btnTambahKereta.setBorder(null);
        btnTambahKereta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahKeretaActionPerformed(evt);
            }
        });

        btnJadwal.setBackground(new java.awt.Color(0, 102, 255));
        btnJadwal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tiketkereta/admin/Logo Kelola Jadwal.png"))); // NOI18N
        btnJadwal.setBorder(null);
        btnJadwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJadwalActionPerformed(evt);
            }
        });

        btnStasiun.setBackground(new java.awt.Color(0, 102, 255));
        btnStasiun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tiketkereta/admin/Logo Kelola Stasiun.png"))); // NOI18N
        btnStasiun.setBorder(null);
        btnStasiun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStasiunActionPerformed(evt);
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
                        .addComponent(btnTambahKereta, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnKereta, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTiketKereta, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUser, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnJadwal, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(btnStasiun, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRute, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnKereta, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTiketKereta, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUser, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTambahKereta, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnJadwal, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnStasiun, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRute, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(230, Short.MAX_VALUE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKeretaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeretaActionPerformed
    new KelolaKereta().setVisible(true);// Membuat jendela KelolaKereta dan menampilkannya
        this.dispose(); // Menutup jendela AdminMenu saat ini
    }//GEN-LAST:event_btnKeretaActionPerformed

    private void btnTiketKeretaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTiketKeretaActionPerformed
        new KelolaPesananTiket().setVisible(true);
            this.dispose();
    }//GEN-LAST:event_btnTiketKeretaActionPerformed

    private void btnRuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRuteActionPerformed
        new KelolaRute().setVisible(true);
            this.dispose();
    }//GEN-LAST:event_btnRuteActionPerformed

    private void btnUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserActionPerformed
        new LihatUser().setVisible(true);
            this.dispose();
    }//GEN-LAST:event_btnUserActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin logout?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            new Login().setVisible(true);
            this.dispose();
    }       
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnTambahKeretaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahKeretaActionPerformed
        new TambahKereta().setVisible(true);
            this.dispose();
    }//GEN-LAST:event_btnTambahKeretaActionPerformed

    private void btnJadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJadwalActionPerformed
        new KelolaJadwalKRL().setVisible(true);
            this.dispose();
    }//GEN-LAST:event_btnJadwalActionPerformed

    private void btnStasiunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStasiunActionPerformed
        new TambahStasiun().setVisible(true);
            this.dispose();
    }//GEN-LAST:event_btnStasiunActionPerformed
    // --- METODE UTAMA (MAIN METHOD) ---
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
             // Membuat dan menampilkan form GUI 
            public void run() {
                new AdminMenu().setVisible(true);// Membuat objek dari AdminMenu dan membuatnya terlihat di layar
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnJadwal;
    private javax.swing.JButton btnKereta;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRute;
    private javax.swing.JButton btnStasiun;
    private javax.swing.JButton btnTambahKereta;
    private javax.swing.JButton btnTiketKereta;
    private javax.swing.JButton btnUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
