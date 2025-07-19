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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import tiketkereta.Koneksi;
import javax.swing.JTable;
import javax.swing.JScrollPane;



public class LihatDetailPesananTiket extends javax.swing.JFrame {
    DefaultTableModel tabModel;
    /**
     * Creates new form AdminMenu
     */
    public LihatDetailPesananTiket() {
        initComponents();
        showAllDetails(); // Mengganti nama method agar lebih jelas
        setLocationRelativeTo(this);
    }

    
    private void showAllDetails() {
        // Mendefinisikan header kolom untuk tabel
        Object[] baris = {
            "ID Tiket", "Pemesan", "Penumpang", "NIK", "Jenis Kelamin", 
            "Usia", "Tipe Penumpang", "Rute", "Kereta", "Kelas", "Total Harga", "Tanggal Pesan"
        };
        tabModel = new DefaultTableModel(null, baris);
        jTableDetail.setModel(tabModel);

        // Query SQL untuk menggabungkan data dari beberapa tabel
        String sql = "SELECT " +
                     "p.id_pemesanan, " +
                     "u.nama AS nama_pemesan, " +
                     "pd.nama_penumpang, " +
                     "pd.nik, " +
                     "pd.jenis_kelamin, " +
                     "pd.usia, " +
                     "pd.tipe_penumpang, " +
                     "CONCAT(r.asal, ' - ', r.tujuan) AS rute, " +
                     "k.nama_kereta, " +
                     "kk.kelas, " +
                     "p.total_harga, " +
                     "p.tanggal_pesan " +
                     "FROM pemesanan_detail pd " +
                     "JOIN pemesanan p ON pd.id_pemesanan = p.id_pemesanan " +
                     "JOIN user u ON p.id_user = u.id_user " +
                     "JOIN rute r ON p.id_rute = r.id_rute " +
                     "JOIN kereta_kelas kk ON p.id_kelas = kk.id_kelas " +
                     "JOIN kereta k ON r.id_kereta = k.id_kereta " +
                     "ORDER BY p.id_pemesanan, pd.id_detail";

        try {
            Connection conn = Koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Menambahkan baris data ke dalam tabel model
                tabModel.addRow(new Object[]{
                    rs.getInt("id_pemesanan"),
                    rs.getString("nama_pemesan"),
                    rs.getString("nama_penumpang"),
                    rs.getString("nik"),
                    rs.getString("jenis_kelamin"),
                    rs.getInt("usia"),
                    rs.getString("tipe_penumpang"),
                    rs.getString("rute"),
                    rs.getString("nama_kereta"),
                    rs.getString("kelas"),
                    rs.getInt("total_harga"),
                    rs.getTimestamp("tanggal_pesan")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal memuat detail pesanan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
     private void tampilkanPreviewDanCetak() {
        if (jTableDetail.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Tidak ada data untuk dicetak.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Buat komponen UI untuk dialog (dalam bentuk tabel)
        JTable previewTable = new JTable(jTableDetail.getModel());
        
        // Menonaktifkan interaksi pada tabel preview
        previewTable.setEnabled(false);
        previewTable.getTableHeader().setReorderingAllowed(false);
        previewTable.setFillsViewportHeight(true);
        previewTable.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        
        // Masukkan tabel ke dalam JScrollPane
        JScrollPane scrollPane = new JScrollPane(previewTable);
        scrollPane.setPreferredSize(new java.awt.Dimension(900, 500)); // Ukuran disesuaikan

        // Tampilkan JOptionPane dengan tombol custom
        Object[] options = {"Lanjutkan Cetak", "Batal"};
        int choice = JOptionPane.showOptionDialog(
            this,
            scrollPane,
            "Preview Cetak Data Detail",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]
        );

        // Proses pilihan user
        if (choice == JOptionPane.YES_OPTION) {
            cetakDataKePDF();
        }
    }
    
    private void cetakDataKePDF() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Simpan Laporan Detail sebagai PDF");
        fileChooser.setFileFilter(new FileNameExtensionFilter("File PDF", "pdf"));
        String defaultFileName = "Laporan_Detail_Pesanan_" + LocalDate.now().toString() + ".pdf";
        fileChooser.setSelectedFile(new File(defaultFileName));

        if (fileChooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File fileToSave = fileChooser.getSelectedFile();
        if (!fileToSave.getAbsolutePath().endsWith(".pdf")) {
            fileToSave = new File(fileToSave.getAbsolutePath() + ".pdf");
        }

        // Gunakan ukuran kertas yang lebih lebar (LEGAL) karena banyak kolom
        Document document = new Document(PageSize.LEGAL.rotate(), 15, 15, 15, 15);

        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileToSave));
            document.open();

            // Font
            Font fontJudul = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.BOLD);
            Font fontSubJudul = FontFactory.getFont(FontFactory.HELVETICA, 11);
            Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, Font.BOLD, Color.WHITE);
            Font fontIsi = FontFactory.getFont(FontFactory.HELVETICA, 7);

            // Header Dokumen
            Paragraph judul = new Paragraph("Laporan Detail Pesanan Tiket Kereta", fontJudul);
            judul.setAlignment(Element.ALIGN_CENTER);
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm:ss");
            Paragraph subJudul = new Paragraph("Dicetak pada: " + java.time.LocalDateTime.now().format(formatter), fontSubJudul);
            subJudul.setAlignment(Element.ALIGN_CENTER);
            subJudul.setSpacingAfter(15);

            document.add(judul);
            document.add(subJudul);

            // Tabel
            PdfPTable table = new PdfPTable(jTableDetail.getColumnCount());
            table.setWidthPercentage(100);
            // Atur lebar kolom agar sesuai dengan konten detail
            table.setWidths(new float[]{4, 10, 10, 11, 8, 4, 7, 12, 12, 7, 8, 10});

            // Header Tabel
            PdfPCell headerCell = new PdfPCell();
            headerCell.setBackgroundColor(new Color(255, 153, 51));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPadding(4);

            for (int col = 0; col < jTableDetail.getColumnCount(); col++) {
                headerCell.setPhrase(new Phrase(jTableDetail.getColumnName(col), fontHeader));
                table.addCell(headerCell);
            }

            // Isi Tabel
            for (int row = 0; row < jTableDetail.getRowCount(); row++) {
                for (int col = 0; col < jTableDetail.getColumnCount(); col++) {
                    String cellValue = jTableDetail.getValueAt(row, col) != null ? jTableDetail.getValueAt(row, col).toString() : "";
                    PdfPCell dataCell = new PdfPCell(new Phrase(cellValue, fontIsi));
                    dataCell.setBackgroundColor((row % 2 == 0) ? Color.WHITE : new Color(240, 240, 240));
                    dataCell.setPadding(3);
                    table.addCell(dataCell);
                }
            }

            document.add(table);

            JOptionPane.showMessageDialog(this, "Data detail berhasil dicetak ke PDF!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(fileToSave);
            }

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuat PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
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
        btnCetakDetail = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDetail = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 153, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MENU LIHAT DETAIL TIKET KERETA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(667, Short.MAX_VALUE)
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

        btnCetakDetail.setText("Cetak Data Detail Tiket Kereta");
        btnCetakDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakDetailActionPerformed(evt);
            }
        });

        btnKembali.setText("Kembali");
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });

        jTableDetail.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableDetail);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCetakDetail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnKembali)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCetakDetail)
                    .addComponent(btnKembali))
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

    private void btnCetakDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakDetailActionPerformed
    tampilkanPreviewDanCetak();
    }//GEN-LAST:event_btnCetakDetailActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
    new KelolaPesananTiket().setVisible(true);
        this.dispose();      // TODO add your handling code here:
    }//GEN-LAST:event_btnKembaliActionPerformed

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
            java.util.logging.Logger.getLogger(LihatDetailPesananTiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LihatDetailPesananTiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LihatDetailPesananTiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LihatDetailPesananTiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(LihatDetailPesananTiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LihatDetailPesananTiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LihatDetailPesananTiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LihatDetailPesananTiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LihatDetailPesananTiket().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCetakDetail;
    private javax.swing.JButton btnKembali;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDetail;
    // End of variables declaration//GEN-END:variables
}
