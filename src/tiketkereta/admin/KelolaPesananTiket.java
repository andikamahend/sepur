package tiketkereta.admin;

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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import tiketkereta.Koneksi;
import javax.swing.JTable;
import javax.swing.JScrollPane;


/**
 *
 * @author ASUS
 */
public class KelolaPesananTiket extends javax.swing.JFrame {
    DefaultTableModel tabModel;
    /**
     * Creates new form AdminMenu
     */
    public KelolaPesananTiket() {
        initComponents();
        showData();
        setLocationRelativeTo(this);
    }
private void showData(){
        Object[] baris = {"ID Tiket", "Pemesan", "Rute", "Kereta", "Kelas", "Total Harga", "Tanggal Pesan"};
        tabModel = new DefaultTableModel(null, baris);
        jTablePesanan.setModel(tabModel);
        
        String sql = "SELECT p.id_pemesanan, u.nama, CONCAT(r.asal, ' - ', r.tujuan) AS rute, "
                + "k.nama_kereta, kk.kelas, p.total_harga, p.tanggal_pesan "
                + "FROM pemesanan p "
                + "JOIN user u ON p.id_user = u.id_user "
                + "JOIN rute r ON p.id_rute = r.id_rute "
                + "JOIN kereta_kelas kk ON p.id_kelas = kk.id_kelas "
                + "JOIN kereta k ON r.id_kereta = k.id_kereta "
                + "ORDER BY p.tanggal_pesan DESC";
        
        try {
            Connection conn = Koneksi.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                tabModel.addRow(new Object[]{
                    rs.getString("id_pemesanan"),
                    rs.getString("nama"),
                    rs.getString("rute"),
                    rs.getString("nama_kereta"),
                    rs.getString("kelas"),
                    rs.getString("total_harga"),
                    rs.getString("tanggal_pesan")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  private void tampilkanPreviewDanCetak() {
        if (jTablePesanan.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Tidak ada data untuk dicetak.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Buat komponen UI untuk dialog (dalam bentuk tabel)
        // Membuat tabel baru yang menggunakan model data dari tabel utama
        JTable previewTable = new JTable(jTablePesanan.getModel());
        
        // Menonaktifkan interaksi pada tabel preview (tidak bisa diedit/dipilih)
        previewTable.setEnabled(false);
        previewTable.getTableHeader().setReorderingAllowed(false);
        previewTable.setFillsViewportHeight(true);
        previewTable.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        
        // Masukkan tabel ke dalam JScrollPane
        JScrollPane scrollPane = new JScrollPane(previewTable);
        scrollPane.setPreferredSize(new java.awt.Dimension(800, 400));

        // Tampilkan JOptionPane dengan tombol custom
        Object[] options = {"Lanjutkan Cetak", "Batal"};
        int choice = JOptionPane.showOptionDialog(
            this,
            scrollPane,
            "Preview Cetak Data",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]
        );

        // Proses pilihan user
        if (choice == JOptionPane.YES_OPTION) { // YES_OPTION adalah indeks 0 (tombol "Lanjutkan Cetak")
            cetakDataKePDF(); // Panggil method cetak jika user setuju
        }
    }

 private void cetakDataKePDF() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Simpan Laporan sebagai PDF");
        fileChooser.setFileFilter(new FileNameExtensionFilter("File PDF", "pdf"));
        String defaultFileName = "Laporan_Pesanan_Tiket_" + LocalDate.now().toString() + ".pdf";
        fileChooser.setSelectedFile(new File(defaultFileName));

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File fileToSave = fileChooser.getSelectedFile();
        if (!fileToSave.getAbsolutePath().endsWith(".pdf")) {
            fileToSave = new File(fileToSave.getAbsolutePath() + ".pdf");
        }

        // Inisialisasi dokumen iText versi lama
        Document document = new Document(PageSize.A4.rotate(), 20, 20, 20, 20);

        try {
            // Membuat instance PdfWriter
            PdfWriter.getInstance(document, new FileOutputStream(fileToSave));

            // PENTING: Buka dokumen sebelum menambah konten
            document.open();

            // Membuat Font
            Font fontJudul = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, Font.BOLD);
            Font fontSubJudul = FontFactory.getFont(FontFactory.HELVETICA, 12);
            Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Font.BOLD, Color.WHITE);
            Font fontIsi = FontFactory.getFont(FontFactory.HELVETICA, 9);
            
            // Membuat Header Dokumen
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
            String tanggalCetak = LocalDate.now().format(formatter);
            
            Paragraph judul = new Paragraph("Laporan Data Pesanan Tiket Kereta", fontJudul);
            judul.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph subJudul = new Paragraph("Dicetak pada: " + tanggalCetak, fontSubJudul);
            subJudul.setAlignment(Element.ALIGN_CENTER);
            subJudul.setSpacingAfter(20);

            document.add(judul);
            document.add(subJudul);

            // Membuat Tabel
            PdfPTable table = new PdfPTable(jTablePesanan.getColumnCount());
            table.setWidthPercentage(100);
            table.setWidths(new float[]{5, 15, 20, 20, 10, 15, 15});
            
            // Membuat Header Tabel
            PdfPCell headerCell = new PdfPCell();
            headerCell.setBackgroundColor(new Color(255,153,51)); // Warna oranye seperti header GUI
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPadding(5);
            
            for (int col = 0; col < jTablePesanan.getColumnCount(); col++) {
                headerCell.setPhrase(new Phrase(jTablePesanan.getColumnName(col), fontHeader));
                table.addCell(headerCell);
            }

            // Menambahkan Isi Tabel dengan Zebra-Striping
            Color warnaBaris1 = Color.WHITE;
            Color warnaBaris2 = new Color(240, 240, 240); // Abu-abu muda

            for (int row = 0; row < jTablePesanan.getRowCount(); row++) {
                for (int col = 0; col < jTablePesanan.getColumnCount(); col++) {
                    String cellValue = jTablePesanan.getValueAt(row, col) != null ? jTablePesanan.getValueAt(row, col).toString() : "";
                    PdfPCell dataCell = new PdfPCell(new Phrase(cellValue, fontIsi));
                    dataCell.setBackgroundColor(row % 2 == 0 ? warnaBaris1 : warnaBaris2);
                    dataCell.setPadding(4);
                    table.addCell(dataCell);
                }
            }

            document.add(table);

            JOptionPane.showMessageDialog(this, "Data berhasil dicetak ke PDF!", "Sukses", JOptionPane.INFORMATION_MESSAGE);

            // Buka file PDF secara otomatis
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(fileToSave);
            }

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuat PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka file PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // PENTING: Selalu tutup dokumen
            if (document.isOpen()) {
                document.close();
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnCetakData = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePesanan = new javax.swing.JTable();
        btnLihatDetail = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 153, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MENU KELOLA TIKET KERETA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(252, 252, 252))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        btnCetakData.setText("Cetak Data Tiket Kereta");
        btnCetakData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakDataActionPerformed(evt);
            }
        });

        btnKembali.setText("Kembali");
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });

        jTablePesanan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTablePesanan);

        btnLihatDetail.setText("Lihat Detail Data Tiket Kereta");
        btnLihatDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLihatDetailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCetakData)
                .addGap(303, 303, 303)
                .addComponent(btnLihatDetail)
                .addGap(18, 18, 18)
                .addComponent(btnKembali)
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCetakData)
                    .addComponent(btnKembali)
                    .addComponent(btnLihatDetail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 192, Short.MAX_VALUE))
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

    private void btnCetakDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakDataActionPerformed
    tampilkanPreviewDanCetak(); 
  
    }//GEN-LAST:event_btnCetakDataActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
    new AdminMenu().setVisible(true);
        this.dispose();   // TODO add your handling code here:
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void btnLihatDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLihatDetailActionPerformed
       new LihatDetailPesananTiket().setVisible(true);
       this.dispose();  
    }//GEN-LAST:event_btnLihatDetailActionPerformed

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
            java.util.logging.Logger.getLogger(KelolaPesananTiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KelolaPesananTiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KelolaPesananTiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KelolaPesananTiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
            java.util.logging.Logger.getLogger(KelolaPesananTiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KelolaPesananTiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KelolaPesananTiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KelolaPesananTiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KelolaPesananTiket().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCetakData;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnLihatDetail;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePesanan;
    // End of variables declaration//GEN-END:variables
}
