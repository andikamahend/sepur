package tiketkereta.KAJJ;
import java.util.ArrayList;
import java.util.HashMap;
public class FormOutput extends javax.swing.JFrame {
private String rincianDiskon = "";
private HashMap<String, HashMap<String, Integer>> hargaMap;
    public FormOutput(String asal, String tujuan, String tanggal,
                        String kereta, String kelas,
                        ArrayList<Penumpang> daftarPenumpang,
                        String noUrut, String nim, String namamhs) {
        initComponents();
        isiHargaKereta();
        txtOutput.append("Mahasiswa: " + namamhs + " | NIM: " + nim + " | No Urut: " + noUrut + "\n");
        txtOutput.append("=======================================\n");
        txtOutput.append("      CETAK TIKET PEMESANAN KERETA\n");
        txtOutput.append("=======================================\n");
        txtOutput.append("Rute      : " + asal + " → " + tujuan + "\n");
        txtOutput.append("Tanggal   : " + tanggal + "\n");
        txtOutput.append("Kereta    : " + kereta + "\n");
        txtOutput.append("Kelas     : " + kelas + "\n");
        txtOutput.append("---------------------------------------\n");
        int totalHarga = hitungTotalHarga(kereta, kelas, daftarPenumpang);
        for (int i = 0; i < daftarPenumpang.size(); i++) {
            Penumpang p = daftarPenumpang.get(i);
            txtOutput.append("Penumpang " + (i + 1) + ":\n");
            txtOutput.append("Nama         : " + p.nama + "\n");
            txtOutput.append("NIK          : " + p.nik + "\n");
            txtOutput.append("Jenis Kelamin: " + p.jenisKelamin + "\n");
            txtOutput.append("Usia         : " + p.usia + " tahun\n");
            txtOutput.append("Tipe         : " + p.tipePenumpang + "\n");
            txtOutput.append("---------------------------------------\n");
        }
        txtOutput.append("Total Penumpang : " + daftarPenumpang.size() + "\n");
        txtOutput.append("Harga per Orang : Rp" + hargaMap.get(kereta).get(kelas) + "\n");
        txtOutput.append(rincianDiskon + "\n");
        txtOutput.append("TOTAL BAYAR     : Rp" + totalHarga + "\n");
    }

    private void isiHargaKereta() {
        hargaMap = new HashMap<>();
        HashMap<String, Integer> argo = new HashMap<>();
        argo.put("Eksekutif", 150000);
        hargaMap.put("Argo Parahyangan", argo);
        HashMap<String, Integer> pangrango = new HashMap<>();
        pangrango.put("Ekonomi", 80000);
        pangrango.put("Eksekutif", 120000);
        hargaMap.put("Pangrango Ekspres", pangrango);
        HashMap<String, Integer> lodaya = new HashMap<>();
        lodaya.put("Ekonomi", 90000);
        lodaya.put("Eksekutif", 140000);
        hargaMap.put("Lodaya", lodaya);
        HashMap<String, Integer> mataram = new HashMap<>();
        mataram.put("Bisnis", 110000);
        hargaMap.put("Mataram", mataram);
        HashMap<String, Integer> malioboro = new HashMap<>();
        malioboro.put("Ekonomi", 95000);
        malioboro.put("Eksekutif", 130000);
        hargaMap.put("Malioboro Ekspres", malioboro);
        HashMap<String, Integer> gajayana = new HashMap<>();
        gajayana.put("Eksekutif", 170000);
        hargaMap.put("Gajayana", gajayana);
        HashMap<String, Integer> jayabaya = new HashMap<>();
        jayabaya.put("Ekonomi", 85000);
        hargaMap.put("Jayabaya", jayabaya);
        HashMap<String, Integer> gumarang = new HashMap<>();
        gumarang.put("Ekonomi", 95000);
        gumarang.put("Eksekutif", 145000);
        hargaMap.put("Gumarang", gumarang);
        HashMap<String, Integer> taksaka = new HashMap<>();
        taksaka.put("Eksekutif", 160000);
        hargaMap.put("Taksaka", taksaka);
        HashMap<String, Integer> fajar = new HashMap<>();
        fajar.put("Eksekutif", 155000);
        hargaMap.put("Fajar Utama", fajar);
    }
    private int hitungTotalHarga(String kereta, String kelas, ArrayList<Penumpang> daftarPenumpang) {
        int hargaDasar = hargaMap.get(kereta).getOrDefault(kelas, 0);
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

        // Simpan ke variabel output tambahan supaya bisa ditampilkan
        this.rincianDiskon = "\nJumlah Lansia (diskon 50%): " + jumlahLansia +
                             "\nJumlah Bayi (gratis): " + jumlahBayi +
                             "\nDiskon Lansia: Rp" + diskonLansia +
                             "\nDiskon Bayi: Rp" + diskonBayi +
                             "\nBiaya Admin: Rp" + biayaAdmin;

        return total + biayaAdmin;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtOutput = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtOutput.setBackground(new java.awt.Color(204, 204, 204));
        txtOutput.setColumns(20);
        txtOutput.setForeground(new java.awt.Color(153, 153, 255));
        txtOutput.setRows(5);
        jScrollPane1.setViewportView(txtOutput);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtOutput;
    // End of variables declaration//GEN-END:variables
}
