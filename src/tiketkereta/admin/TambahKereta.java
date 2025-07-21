package tiketkereta.admin;

// Mengimpor kelas-kelas yang diperlukan untuk koneksi database (Connection, PreparedStatement, ResultSet, SQLException, Statement)
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// Mengimpor kelas untuk menampilkan dialog atau pesan pop-up
import javax.swing.JOptionPane;
// Mengimpor kelas untuk model tabel, yang mengatur data di dalam JTable
import javax.swing.table.DefaultTableModel;
// Mengimpor kelas Koneksi dari paket tiketkereta untuk mendapatkan koneksi ke database
import tiketkereta.Koneksi;


public class TambahKereta extends javax.swing.JFrame {
    // Mendeklarasikan variabel untuk model tabel
    DefaultTableModel tabModel;
    // Variabel untuk menyimpan ID kereta yang dipilih dari tabel, digunakan untuk update/delete
    private String selectedKeretaId = null; 
    
    public TambahKereta() {
         initComponents(); // Memanggil metode untuk membuat dan mengatur semua komponen GUI
        showData(); // Memanggil metode untuk menampilkan data dari database ke tabel
        setLocationRelativeTo(this); // Mengatur posisi jendela agar muncul di tengah layar
    }
    
    // Metode untuk mengambil data dari database dan menampilkannya di JTable
    private void showData(){
        // Menentukan judul kolom untuk tabel
        Object[] baris = {"ID Kereta", "Nama Kereta"};
        // Membuat model tabel baru dengan judul kolom yang sudah ditentukan
        tabModel = new DefaultTableModel(null, baris);
        // Menetapkan model tabel ke komponen jTableKereta
        jTableKereta.setModel(tabModel);
        
        // Blok try-catch untuk menangani error yang mungkin terjadi saat interaksi dengan database
        try {
            // Mendapatkan koneksi ke database dari kelas Koneksi
            Connection conn = Koneksi.getConnection();
            // String SQL untuk mengambil semua data dari tabel 'kereta' dan mengurutkannya berdasarkan id_kereta
            String sql = "SELECT * FROM kereta ORDER BY id_kereta ASC";
            // Membuat statement untuk mengeksekusi query SQL
            Statement st = conn.createStatement();
            // Mengeksekusi query dan menyimpan hasilnya di objek ResultSet
            ResultSet rs = st.executeQuery(sql);
            
            // Looping untuk membaca setiap baris data dari hasil query
            while(rs.next()){
                // Mengambil data dari kolom 'id_kereta'
                String id_kereta = rs.getString("id_kereta");
                // Mengambil data dari kolom 'nama_kereta'
                String nama_kereta = rs.getString("nama_kereta");
                // Membuat array objek yang berisi data satu baris
                Object[] data = {id_kereta, nama_kereta};
                // Menambahkan baris data tersebut ke model tabel
                tabModel.addRow(data);
            }
        } catch (SQLException e) {
            // Mencetak jejak error ke konsol jika terjadi kesalahan SQL
            e.printStackTrace();
            // Menampilkan dialog pesan error kepada pengguna
            JOptionPane.showMessageDialog(this, "Gagal memuat data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
   // Metode untuk membersihkan inputan form dan status pilihan
    private void clear(){
        // Mengosongkan field input nama kereta
        txtNamaKereta.setText("");
        // Menghapus seleksi baris pada tabel
        jTableKereta.clearSelection();
        // Mereset ID kereta yang dipilih menjadi null
        selectedKeretaId = null;
        // Mengaktifkan kembali tombol tambah (yang mungkin dinonaktifkan saat mode edit)
        btnKereta.setEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new tiketkereta.ImagePanel(new javax.swing.ImageIcon(getClass().getResource("/bg_admin.jpg")).getImage());
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

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
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

        btnKereta.setBackground(new java.awt.Color(255, 102, 0));
        btnKereta.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnKereta.setForeground(new java.awt.Color(255, 255, 255));
        btnKereta.setText("Tambah Kereta ");
        btnKereta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeretaActionPerformed(evt);
            }
        });

        btnEditKereta.setBackground(new java.awt.Color(255, 102, 0));
        btnEditKereta.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditKereta.setForeground(new java.awt.Color(255, 255, 255));
        btnEditKereta.setText("Edit Kereta");
        btnEditKereta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditKeretaActionPerformed(evt);
            }
        });

        btnHapusKelas.setBackground(new java.awt.Color(255, 0, 0));
        btnHapusKelas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHapusKelas.setForeground(new java.awt.Color(255, 255, 255));
        btnHapusKelas.setText("Hapus Kereta");
        btnHapusKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusKelasActionPerformed(evt);
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

        txtNamaKereta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaKeretaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
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
                .addGap(0, 77, Short.MAX_VALUE))
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
        // Mengecek apakah ada data yang sudah dipilih dari tabel.
        // 'selectedKeretaId' akan null jika pengguna belum mengklik baris manapun di tabel.
        if (selectedKeretaId == null) {
            // Jika tidak ada data yang dipilih, tampilkan pesan peringatan.
            JOptionPane.showMessageDialog(this, "Pilih data kereta yang akan dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            // Hentikan eksekusi metode ini.
            return;
        }

        // Menampilkan dialog konfirmasi untuk memastikan pengguna benar-benar ingin menghapus data.
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

        // Memeriksa apakah pengguna menekan tombol "YES" pada dialog konfirmasi.
        if(confirm == JOptionPane.YES_OPTION){
            // Blok try-catch untuk menangani kemungkinan error saat berinteraksi dengan database.
            try {
                // Mendapatkan koneksi ke database.
                Connection conn = Koneksi.getConnection();

                // Menyiapkan perintah SQL untuk menghapus data dari tabel 'kereta' berdasarkan 'id_kereta'.
                // Tanda tanya (?) adalah placeholder yang akan diisi nanti untuk keamanan (mencegah SQL Injection).
                String sql = "DELETE FROM kereta WHERE id_kereta = ?";
                // Membuat objek PreparedStatement dari perintah SQL.
                PreparedStatement ps = conn.prepareStatement(sql);
                // Mengisi placeholder pertama (?) dengan nilai dari variabel 'selectedKeretaId'.
                ps.setString(1, selectedKeretaId);
                // Menjalankan perintah DELETE dan menyimpan jumlah baris yang terpengaruh (seharusnya 1 jika berhasil).
                int result = ps.executeUpdate();

                // Memeriksa apakah ada baris yang berhasil dihapus (result > 0).
                if (result > 0) {
                    // Jika berhasil, tampilkan pesan sukses.
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
                } else {
                    // Jika tidak ada baris yang terpengaruh, tampilkan pesan gagal.
                    JOptionPane.showMessageDialog(this, "Data gagal dihapus", "Error", JOptionPane.ERROR_MESSAGE);
                }

                // Menutup PreparedStatement untuk melepaskan sumber daya.
                ps.close();
                // Menutup koneksi database.
                conn.close();

                // Memuat ulang data di tabel agar data yang baru dihapus hilang dari tampilan.
                showData();
                // Membersihkan form input dan status seleksi.
                clear();
            } catch (SQLException e) {
                // Jika terjadi error SQL selama proses, tampilkan pesan error kepada pengguna.
                JOptionPane.showMessageDialog(this, "Gagal menghapus data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                // Mencetak detail error ke konsol untuk debugging.
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnHapusKelasActionPerformed

    private void btnEditKeretaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditKeretaActionPerformed
        // Memeriksa apakah ada data kereta yang sudah dipilih dari tabel.
        // Jika 'selectedKeretaId' bernilai null, artinya tidak ada baris yang dipilih.
        if (selectedKeretaId == null) {
            // Tampilkan pesan peringatan jika belum ada data yang dipilih.
            JOptionPane.showMessageDialog(this, "Pilih data kereta yang akan diupdate!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            // Hentikan eksekusi metode agar tidak melanjutkan proses update.
            return;
        }

        // Mengambil teks dari field input 'txtNamaKereta'.
        String namaKereta = txtNamaKereta.getText();
        // Memeriksa apakah field nama kereta kosong (setelah menghapus spasi di awal dan akhir).
        if (namaKereta.trim().isEmpty()) {
            // Jika kosong, tampilkan pesan peringatan.
            JOptionPane.showMessageDialog(this, "Nama kereta tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            // Hentikan eksekusi metode.
            return;
        }

        // Blok try-catch untuk menangani error yang mungkin terjadi saat koneksi atau eksekusi query database.
        try {
            // Mendapatkan koneksi ke database dari kelas Koneksi.
            Connection conn = Koneksi.getConnection();
            // Menyiapkan perintah SQL UPDATE untuk mengubah 'nama_kereta' berdasarkan 'id_kereta'.
            // Tanda tanya (?) adalah placeholder yang akan diisi dengan nilai untuk keamanan.
            String sql = "UPDATE kereta SET nama_kereta = ? WHERE id_kereta = ?";
            // Membuat objek PreparedStatement dari perintah SQL.
            PreparedStatement ps = conn.prepareStatement(sql);
            // Mengisi placeholder pertama (?) dengan nilai dari variabel 'namaKereta'.
            ps.setString(1, namaKereta);
            // Mengisi placeholder kedua (?) dengan ID kereta yang dipilih.
            ps.setString(2, selectedKeretaId);
            // Menjalankan perintah UPDATE dan menyimpan jumlah baris yang berhasil diubah.
            int result = ps.executeUpdate();

            // Memeriksa apakah ada baris yang berhasil diupdate (result > 0).
            if (result > 0) {
                // Jika berhasil, tampilkan pesan sukses.
                JOptionPane.showMessageDialog(this, "Data berhasil diupdate");
            } else {
                // Jika tidak ada baris yang berubah, tampilkan pesan gagal.
                JOptionPane.showMessageDialog(this, "Data gagal diupdate", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Menutup PreparedStatement untuk melepaskan sumber daya.
            ps.close();
            // Menutup koneksi database.
            conn.close();

            // Memuat ulang data di tabel untuk menampilkan perubahan terbaru.
            showData();
            // Membersihkan form input dan mereset status pilihan.
            clear();
        } catch (SQLException e) {
            // Jika terjadi error SQL, tangkap pengecualiannya.
            // Tampilkan dialog pesan error kepada pengguna.
            JOptionPane.showMessageDialog(this, "Gagal mengupdate data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            // Cetak detail error ke konsol untuk membantu proses debugging.
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnEditKeretaActionPerformed

    private void btnKeretaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeretaActionPerformed
        // Mengambil teks (nama kereta) dari komponen input bernama txtNamaKereta.
        String namaKereta = txtNamaKereta.getText();

        // Memeriksa apakah input namaKereta kosong atau hanya berisi spasi.
        // .trim() menghapus spasi di awal dan akhir, .isEmpty() memeriksa apakah hasilnya kosong.
        if (namaKereta.trim().isEmpty()) {
            // Jika kosong, tampilkan dialog peringatan kepada pengguna.
            JOptionPane.showMessageDialog(this, "Nama kereta tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            // Hentikan eksekusi metode ini agar tidak melanjutkan ke database.
            return;
        }

        // Blok try-catch untuk menangani error yang mungkin terjadi saat proses ke database.
        try {
            // Mendapatkan koneksi aktif ke database.
            Connection conn = Koneksi.getConnection();
            // Menyiapkan perintah SQL untuk memasukkan data baru ke tabel 'kereta'.
            // '?' adalah placeholder yang akan diisi dengan data untuk mencegah SQL Injection.
            String sql = "INSERT INTO kereta (nama_kereta) VALUES (?)";
            // Membuat objek PreparedStatement dari perintah SQL.
            PreparedStatement ps = conn.prepareStatement(sql);
            // Mengisi placeholder pertama (?) dengan nilai dari variabel 'namaKereta'.
            ps.setString(1, namaKereta);

            // Menjalankan perintah INSERT dan menyimpan jumlah baris yang berhasil ditambahkan.
            int result = ps.executeUpdate();
            // Memeriksa apakah ada baris yang berhasil ditambahkan (result > 0).
            if (result > 0) {
                // Jika berhasil, tampilkan pesan sukses.
                JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan");
            } else {
                // Jika tidak, tampilkan pesan gagal.
                JOptionPane.showMessageDialog(this, "Data gagal ditambahkan", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Menutup PreparedStatement untuk melepaskan sumber daya.
            ps.close();
            // Menutup koneksi database.
            conn.close();

            // Memanggil metode showData() untuk memperbarui tampilan tabel dengan data baru.
            showData();
            // Memanggil metode clear() untuk membersihkan field input.
            clear();
        } catch (SQLException e) {
            // Jika terjadi error SQL selama proses di dalam blok 'try', tangkap error tersebut.
            // Tampilkan dialog pesan error kepada pengguna.
            JOptionPane.showMessageDialog(this, "Gagal menambahkan data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            // Cetak detail error ke konsol untuk membantu developer melakukan debugging.
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_btnKeretaActionPerformed

    private void jTableKeretaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableKeretaMouseClicked
        // Mendapatkan indeks (nomor) baris yang sedang dipilih oleh pengguna di tabel jTableKereta.
        int row = jTableKereta.getSelectedRow();

        // Memeriksa apakah ada baris yang dipilih. Metode getSelectedRow() akan mengembalikan -1 jika tidak ada baris yang dipilih.
        if (row == -1) {
            // Jika tidak ada baris yang dipilih, hentikan eksekusi metode ini.
            return;
        }
        // Mengambil nilai dari baris yang dipilih (row) dan kolom pertama (indeks 0).
        // Nilai ini (ID kereta) diubah menjadi String dan disimpan dalam variabel selectedKeretaId.
        selectedKeretaId = jTableKereta.getValueAt(row, 0).toString();

        // Mengambil nilai dari baris yang dipilih (row) dan kolom kedua (indeks 1).
        // Nilai ini (nama kereta) ditampilkan di dalam text field txtNamaKereta.
        txtNamaKereta.setText(jTableKereta.getValueAt(row, 1).toString());

        // Menonaktifkan tombol 'btnKereta' (kemungkinan tombol untuk "Tambah Data Baru").
        // Ini biasanya dilakukan agar pengguna fokus pada proses update atau delete, bukan menambah data baru secara tidak sengaja.
        btnKereta.setEnabled(false);
    }//GEN-LAST:event_jTableKeretaMouseClicked

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
