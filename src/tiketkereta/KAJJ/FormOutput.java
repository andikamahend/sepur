package tiketkereta.KAJJ;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import tiketkereta.Koneksi;


public class FormOutput extends javax.swing.JFrame {
    private String rincianDiskon = "";
    private int totalHargaFinal;
    private String asal, tujuan, tanggal, kereta, kelas;
    private ArrayList<Penumpang> daftarPenumpang;
    private String idUser, nim, namaMhs;

    public FormOutput(String asal, String tujuan, String tanggal,
                        String kereta, String kelas,
                        ArrayList<Penumpang> daftarPenumpang,
                        String idUser, String nim, String namamhs, boolean isNewBooking) {
        initComponents();
        
        this.asal = asal;
        this.tujuan = tujuan;
        this.tanggal = tanggal;
        this.kereta = kereta;
        this.kelas = kelas;
        this.daftarPenumpang = daftarPenumpang;
        this.idUser = idUser;
        this.nim = nim;
        this.namaMhs = namamhs;

        tampilkanOutput();
        if (isNewBooking) {
            simpanPemesanan();
        }
        setLocationRelativeTo(null);
    }
    
    /**
     * Mengambil harga dasar per tiket dari database.
     */
    private int getHargaDasar(String namaKereta, String namaKelas) {
        try (Connection conn = Koneksi.getConnection()) {
            String sql = "SELECT harga FROM kereta_kelas kk JOIN kereta k ON kk.id_kereta = k.id_kereta WHERE k.nama_kereta = ? AND kk.kelas = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, namaKereta);
            ps.setString(2, namaKelas);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("harga");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Mengembalikan 0 jika harga tidak ditemukan
    }

    /**
     * Menghitung total harga tiket termasuk diskon dan biaya admin.
     * Mengembalikan harga dasar per orang untuk ditampilkan.
     */
    private int hitungTotalHarga(String kereta, String kelas, ArrayList<Penumpang> daftarPenumpang) {
        int hargaDasar = getHargaDasar(kereta, kelas);
        if (hargaDasar == 0) {
            JOptionPane.showMessageDialog(this, "Harga untuk kereta/kelas ini tidak ditemukan.", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        int total = 0;
        int jumlahLansia = 0;
        int jumlahBayi = 0;

        for (Penumpang p : daftarPenumpang) {
            if (p.tipePenumpang.equalsIgnoreCase("Bayi")) {
                jumlahBayi++;
                continue;
            } else if (p.usia > 60) {
                jumlahLansia++;
                total += hargaDasar / 2;
            } else {
                total += hargaDasar;
            }
        }

        int diskonLansia = jumlahLansia * (hargaDasar / 2);
        int diskonBayi = jumlahBayi * hargaDasar;
        int biayaAdmin = 5000;

        this.rincianDiskon = "\nJumlah Lansia (diskon 50%): " + jumlahLansia +
                             "\nJumlah Bayi (gratis): " + jumlahBayi +
                             "\nDiskon Lansia: Rp" + diskonLansia +
                             "\nDiskon Bayi: Rp" + diskonBayi +
                             "\nBiaya Admin: Rp" + biayaAdmin;

        this.totalHargaFinal = total + biayaAdmin;
        return hargaDasar;
    }
    
    /**
     * Menampilkan rincian lengkap pemesanan di JTextArea.
     */
private void tampilkanOutput() {
    int hargaPerOrang = hitungTotalHarga(kereta, kelas, daftarPenumpang);

    // Set font menggunakan Font dari AWT dengan safety fallback
    java.awt.Font fontMono = new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12);
    txtOutput.setFont(fontMono);

    // Mulai menampilkan output
    StringBuilder sb = new StringBuilder();
    sb.append("=====================================================\n");
    sb.append("                 CETAK TIKET KERETA API              \n");
    sb.append("=====================================================\n");
    sb.append(String.format("Rute    : %-15s -> %-15s\n", asal, tujuan));
    sb.append(String.format("Tanggal : %-10s\n", tanggal));
    sb.append(String.format("Kereta  : %-15s Kelas: %s\n", kereta, kelas));
    sb.append("-----------------------------------------------------\n");

    for (int i = 0; i < daftarPenumpang.size(); i++) {
        Penumpang p = daftarPenumpang.get(i);
        sb.append(String.format("Penumpang %d\n", i + 1));
        sb.append(String.format("Nama         : %-25s\n", p.nama));
        sb.append(String.format("NIK          : %-25s\n", p.nik));
        sb.append(String.format("Jenis Kelamin: %-10s\n", p.jenisKelamin));
        sb.append(String.format("Usia         : %-3d tahun\n", p.usia));
        sb.append(String.format("Tipe         : %-10s\n", p.tipePenumpang));
        sb.append("-----------------------------------------------------\n");
    }

    sb.append(String.format("Total Penumpang : %d\n", daftarPenumpang.size()));
    sb.append(String.format("Harga per Orang : Rp %,d\n", hargaPerOrang));
    sb.append(String.format("%s\n", rincianDiskon));
    sb.append(String.format("TOTAL BAYAR     : Rp %,d\n", totalHargaFinal));

    // Tampilkan di txtOutput
    txtOutput.setText(sb.toString());
}

    
    private int getIdDariNama(String query, String nama) throws SQLException {
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, nama);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }
    
    private void simpanPemesanan(){
        Connection conn = null;
        try {
            conn = Koneksi.getConnection();
            conn.setAutoCommit(false); 

            int idKereta = getIdDariNama("SELECT id_kereta FROM kereta WHERE nama_kereta = ?", kereta);
            int idKelas = getIdDariNama("SELECT id_kelas FROM kereta_kelas WHERE id_kereta = " + idKereta + " AND kelas = ?", kelas);
            
            String ruteQuery = "SELECT id_rute FROM rute WHERE asal = ? AND tujuan = ? AND id_kereta = ?";
            int idRute;
            try (PreparedStatement psRute = conn.prepareStatement(ruteQuery)) {
                psRute.setString(1, asal);
                psRute.setString(2, tujuan);
                psRute.setInt(3, idKereta);
                ResultSet rsRute = psRute.executeQuery();
                idRute = rsRute.next() ? rsRute.getInt(1) : 0;
            }

            if(idRute == 0 || idKelas == 0) {
                throw new SQLException("ID Rute atau Kelas tidak ditemukan di database.");
            }

            String sqlPemesanan = "INSERT INTO pemesanan (id_user, id_rute, id_kelas, total_harga, tanggal_pesan) VALUES (?, ?, ?, ?, NOW())";
            int idPemesanan;
            try (PreparedStatement psPemesanan = conn.prepareStatement(sqlPemesanan, Statement.RETURN_GENERATED_KEYS)) {
                psPemesanan.setInt(1, Integer.parseInt(idUser));
                psPemesanan.setInt(2, idRute);
                psPemesanan.setInt(3, idKelas);
                psPemesanan.setInt(4, totalHargaFinal);
                psPemesanan.executeUpdate();
                
                ResultSet generatedKeys = psPemesanan.getGeneratedKeys();
                if (generatedKeys.next()) {
                    idPemesanan = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Gagal membuat pemesanan, tidak ada ID yang dihasilkan.");
                }
            }
            
            String sqlDetail = "INSERT INTO pemesanan_detail (id_pemesanan, nama_penumpang, nik, jenis_kelamin, usia, tipe_penumpang) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement psDetail = conn.prepareStatement(sqlDetail)) {
                for (Penumpang p : daftarPenumpang) {
                    psDetail.setInt(1, idPemesanan);
                    psDetail.setString(2, p.nama);
                    psDetail.setString(3, p.nik);
                    psDetail.setString(4, p.jenisKelamin);
                    psDetail.setInt(5, p.usia);
                    psDetail.setString(6, p.tipePenumpang);
                    psDetail.addBatch();
                }
                psDetail.executeBatch();
            }

            conn.commit();
            JOptionPane.showMessageDialog(this, "Pemesanan berhasil disimpan ke database!");

        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menyimpan data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }
    
    private void cetakTiketPdf() {
        // 1. Membuat dan menampilkan dialog penyimpanan file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Simpan E-Tiket sebagai PDF");
        fileChooser.setFileFilter(new FileNameExtensionFilter("File PDF (*.pdf)", "pdf"));
        
        // Membuat nama file default yang unik dan deskriptif
        String defaultFileName = "E-Tiket_" + kereta.replaceAll("\\s", "") + "_" + System.currentTimeMillis() + ".pdf";
        fileChooser.setSelectedFile(new File(defaultFileName));

        int userSelection = fileChooser.showSaveDialog(this);

        // 2. Lanjutkan hanya jika pengguna menekan tombol "Save"
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            
            // Memastikan file memiliki ekstensi .pdf
            if (!fileToSave.getAbsolutePath().endsWith(".pdf")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".pdf");
            }
            
            Document document = new Document(PageSize.A4, 36, 36, 36, 36);

            try {
                // Menggunakan path dari JFileChooser
                PdfWriter.getInstance(document, new FileOutputStream(fileToSave));
                document.open();

                // === DEFINISI WARNA DAN FONT ===
                Color oranyeKAI = new Color(255, 121, 0);
                Font fontJudul = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, oranyeKAI);
                Font fontSubJudul = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.BLACK);
                Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA, 8, Color.GRAY);
                Font fontIsi = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Color.BLACK);
                Font fontHeaderTabel = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9, Color.WHITE);
                Font fontIsiTabel = FontFactory.getFont(FontFactory.HELVETICA, 9, Color.BLACK);
                
                // === HEADER UTAMA ===
                PdfPTable headerTabel = new PdfPTable(2);
                headerTabel.setWidthPercentage(100);
                headerTabel.setWidths(new float[]{1, 4});
                
                PdfPCell logoCell = new PdfPCell(new Phrase("SEPOR", fontJudul));
                logoCell.setBorder(PdfPCell.NO_BORDER);
                headerTabel.addCell(logoCell);
                
                PdfPCell judulCell = new PdfPCell(new Phrase("E-Tiket Kereta Api", fontSubJudul));
                judulCell.setBorder(PdfPCell.NO_BORDER);
                judulCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                judulCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                headerTabel.addCell(judulCell);
                
                document.add(headerTabel);
                
                // Garis pemisah
                document.add(new Paragraph("=========================================================================="));
                
                // === DETAIL PERJALANAN ===
                document.add(new Paragraph(" "));
                Paragraph pPerjalanan = new Paragraph("DETAIL PERJALANAN", fontSubJudul);
                pPerjalanan.setSpacingAfter(8f);
                document.add(pPerjalanan);
                
                PdfPTable tabelPerjalanan = new PdfPTable(2);
                tabelPerjalanan.setWidthPercentage(100);
                tabelPerjalanan.setWidths(new float[]{1, 4});
                tabelPerjalanan.addCell(createKeyValueCell("Kereta Api", kereta + " (" + kelas + ")", fontHeader, fontIsi));
                tabelPerjalanan.addCell(createKeyValueCell("Tanggal", tanggal, fontHeader, fontIsi));
                tabelPerjalanan.addCell(createKeyValueCell("Rute", asal + " -> " + tujuan, fontHeader, fontIsi, 2));
                document.add(tabelPerjalanan);

                // === DETAIL PENUMPANG ===
                document.add(new Paragraph(" "));
                Paragraph pPenumpang = new Paragraph("DETAIL PENUMPANG", fontSubJudul);
                pPenumpang.setSpacingAfter(8f);
                document.add(pPenumpang);

                PdfPTable tabelPenumpang = new PdfPTable(3);
                tabelPenumpang.setWidthPercentage(100);
                tabelPenumpang.setWidths(new float[]{3, 3, 1.5f});
                tabelPenumpang.addCell(createHeaderCell("Nama", oranyeKAI));
                tabelPenumpang.addCell(createHeaderCell("No. Identitas (NIK)", oranyeKAI));
                tabelPenumpang.addCell(createHeaderCell("Tipe", oranyeKAI));

                for (Penumpang p : daftarPenumpang) {
                    tabelPenumpang.addCell(new Phrase(p.nama, fontIsiTabel));
                    tabelPenumpang.addCell(new Phrase(p.nik, fontIsiTabel));
                    tabelPenumpang.addCell(new Phrase(p.tipePenumpang, fontIsiTabel));
                }

                document.add(tabelPenumpang);
                document.add(new Paragraph(" "));

                document.add(new Paragraph("Terima kasih telah menggunakan layanan SEPOR. Selamat menikmati perjalanan Anda.", fontHeader));
                
                // Pesan sukses menampilkan path yang dipilih pengguna
                JOptionPane.showMessageDialog(this, "File PDF berhasil dibuat di:\n" + fileToSave.getAbsolutePath(), "Cetak Berhasil", JOptionPane.INFORMATION_MESSAGE);
                
                // Coba buka file PDF yang baru dibuat
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(fileToSave);
                }

            } catch (DocumentException | IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Gagal membuat file PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                document.close();
            }
        }
    }
    
    private PdfPCell createHeaderCell(String text, Color background) {
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9, Color.WHITE);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBackgroundColor(background);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(8);
        return cell;
    }
    
    private PdfPCell createKeyValueCell(String label, String value, Font fontLabel, Font fontValue) {
        return createKeyValueCell(label, value, fontLabel, fontValue, 1);
    }
    
    private PdfPCell createKeyValueCell(String label, String value, Font fontLabel, Font fontValue, int colspan) {
        PdfPCell cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setColspan(colspan);
        cell.addElement(new Phrase(label, fontLabel));
        cell.addElement(new Phrase(value, fontValue));
        return cell;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtOutput = new javax.swing.JTextArea();
        BKembali = new javax.swing.JButton();
        Bprint = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtOutput.setBackground(new java.awt.Color(204, 204, 204));
        txtOutput.setColumns(20);
        txtOutput.setRows(5);
        txtOutput.setBorder(new javax.swing.border.MatteBorder(new javax.swing.ImageIcon(getClass().getResource("/tiketkereta/batik.png")))); // NOI18N
        jScrollPane1.setViewportView(txtOutput);

        BKembali.setText("KEMBALI");
        BKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BKembaliActionPerformed(evt);
            }
        });

        Bprint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tiketkereta/KAJJ/print.png"))); // NOI18N
        Bprint.setBorder(null);
        Bprint.setContentAreaFilled(false);
        Bprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BprintActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 0, 0));

        jLabel1.setFont(new java.awt.Font("High Tower Text", 1, 48)); // NOI18N
        jLabel1.setText("RIWAYAT");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(BKembali)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Bprint)
                .addGap(32, 32, 32))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Bprint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(BKembali)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BKembaliActionPerformed
        new tiketkereta.MenuUtama(namaMhs, nim, idUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BKembaliActionPerformed

    private void BprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BprintActionPerformed
    cetakTiketPdf(); 
    }//GEN-LAST:event_BprintActionPerformed

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
            java.util.logging.Logger.getLogger(FormOutput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormOutput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormOutput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormOutput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BKembali;
    private javax.swing.JButton Bprint;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtOutput;
    // End of variables declaration//GEN-END:variables
}
