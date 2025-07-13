package tiketkereta.KRL;

import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import tiketkereta.MenuUtama;

public class FormStasiun extends javax.swing.JFrame {
    private String namamhs, nim, noUrut;
    private Map<String, String> jadwalMap = new HashMap<>();
    
    public FormStasiun(String namamhs, String nim, String noUrut) {
        this.namamhs = namamhs;
        this.nim = nim;
        this.noUrut = noUrut;
        initComponents();
        isiJadwal();
        isiComboBox();
    }

private void isiJadwal() {
    jadwalMap.put("Yogyakarta", "06:00 (Tujuan: Palur), 07:05 (Tujuan: Palur), 08:49 (Tujuan: Palur), 10:56 (Tujuan: Palur), 13:57 (Tujuan: Palur), 16:10 (Tujuan: Palur), 18:08 (Tujuan: Palur), 20:15 (Tujuan: Palur)");
    
    jadwalMap.put("Lempuyangan", "06:06 (Tujuan: Palur), 07:10 (Tujuan: Palur), 08:54 (Tujuan: Palur), 11:01 (Tujuan: Palur), 14:02 (Tujuan: Palur), 16:15 (Tujuan: Palur), 18:13 (Tujuan: Palur), 20:20 (Tujuan: Palur), 06:30 (Tujuan: Yogyakarta), 09:00 (Tujuan: Yogyakarta), 17:00 (Tujuan: Yogyakarta)");
    
    jadwalMap.put("Maguwo", "06:13 (Tujuan: Palur), 07:17 (Tujuan: Palur), 09:01 (Tujuan: Palur), 11:08 (Tujuan: Palur), 14:10 (Tujuan: Palur), 16:22 (Tujuan: Palur), 18:20 (Tujuan: Palur), 20:27 (Tujuan: Palur), 06:36 (Tujuan: Yogyakarta), 09:06 (Tujuan: Yogyakarta), 17:06 (Tujuan: Yogyakarta)");
    
    jadwalMap.put("Brambanan", "06:21 (Tujuan: Palur), 07:25 (Tujuan: Palur), 09:09 (Tujuan: Palur), 11:16 (Tujuan: Palur), 14:19 (Tujuan: Palur), 16:30 (Tujuan: Palur), 18:28 (Tujuan: Palur), 20:36 (Tujuan: Palur), 06:45 (Tujuan: Yogyakarta), 09:15 (Tujuan: Yogyakarta), 17:15 (Tujuan: Yogyakarta)");
    
    jadwalMap.put("Srowot", "06:28 (Tujuan: Palur), 07:32 (Tujuan: Palur), 09:16 (Tujuan: Palur), 11:23 (Tujuan: Palur), 14:26 (Tujuan: Palur), 16:37 (Tujuan: Palur), 18:35 (Tujuan: Palur), 20:43 (Tujuan: Palur), 06:52 (Tujuan: Yogyakarta), 09:22 (Tujuan: Yogyakarta), 17:22 (Tujuan: Yogyakarta)");

    jadwalMap.put("Klaten", "06:35 (Tujuan: Palur), 07:39 (Tujuan: Palur), 09:23 (Tujuan: Palur), 11:30 (Tujuan: Palur), 14:33 (Tujuan: Palur), 16:44 (Tujuan: Palur), 18:42 (Tujuan: Palur), 20:50 (Tujuan: Palur), 07:00 (Tujuan: Yogyakarta), 09:30 (Tujuan: Yogyakarta), 17:30 (Tujuan: Yogyakarta)");

    jadwalMap.put("Ceper", "06:44 (Tujuan: Palur), 07:48 (Tujuan: Palur), 09:32 (Tujuan: Palur), 11:39 (Tujuan: Palur), 14:42 (Tujuan: Palur), 16:53 (Tujuan: Palur), 18:51 (Tujuan: Palur), 20:59 (Tujuan: Palur), 07:08 (Tujuan: Yogyakarta), 09:38 (Tujuan: Yogyakarta), 17:38 (Tujuan: Yogyakarta)");

    jadwalMap.put("Delanggu", "06:51 (Tujuan: Palur), 07:55 (Tujuan: Palur), 09:39 (Tujuan: Palur), 11:46 (Tujuan: Palur), 14:49 (Tujuan: Palur), 17:00 (Tujuan: Palur), 18:58 (Tujuan: Palur), 21:06 (Tujuan: Palur), 07:15 (Tujuan: Yogyakarta), 09:45 (Tujuan: Yogyakarta), 17:45 (Tujuan: Yogyakarta)");

    jadwalMap.put("Gawok", "06:57 (Tujuan: Palur), 08:01 (Tujuan: Palur), 09:45 (Tujuan: Palur), 11:52 (Tujuan: Palur), 14:56 (Tujuan: Palur), 17:07 (Tujuan: Palur), 19:04 (Tujuan: Palur), 21:12 (Tujuan: Palur), 07:22 (Tujuan: Yogyakarta), 09:51 (Tujuan: Yogyakarta), 17:52 (Tujuan: Yogyakarta)");

    jadwalMap.put("Purwosari", "07:04 (Tujuan: Palur), 08:09 (Tujuan: Palur), 09:52 (Tujuan: Palur), 12:00 (Tujuan: Palur), 15:03 (Tujuan: Palur), 17:15 (Tujuan: Palur), 19:11 (Tujuan: Palur), 21:19 (Tujuan: Palur), 07:28 (Tujuan: Yogyakarta), 09:57 (Tujuan: Yogyakarta), 17:58 (Tujuan: Yogyakarta)");

    jadwalMap.put("Solo Balapan", "07:10 (Tujuan: Palur), 08:16 (Tujuan: Palur), 10:00 (Tujuan: Palur), 12:06 (Tujuan: Palur), 15:09 (Tujuan: Palur), 17:22 (Tujuan: Palur), 19:17 (Tujuan: Palur), 21:28 (Tujuan: Palur), 07:35 (Tujuan: Yogyakarta), 10:03 (Tujuan: Yogyakarta), 18:05 (Tujuan: Yogyakarta)");

    jadwalMap.put("Solo Jebres", "07:15 (Tujuan: Palur), 08:23 (Tujuan: Palur), 10:06 (Tujuan: Palur), 12:12 (Tujuan: Palur), 15:15 (Tujuan: Palur), 17:28 (Tujuan: Palur), 19:23 (Tujuan: Palur), 21:34 (Tujuan: Palur), 07:41 (Tujuan: Yogyakarta), 10:09 (Tujuan: Yogyakarta), 18:11 (Tujuan: Yogyakarta)");

    jadwalMap.put("Palur", "07:20 (Tujuan: Yogyakarta), 08:29 (Tujuan: Yogyakarta), 10:12 (Tujuan: Yogyakarta), 12:18 (Tujuan: Yogyakarta), 15:21 (Tujuan: Yogyakarta), 17:35 (Tujuan: Yogyakarta), 19:28 (Tujuan: Yogyakarta), 21:40 (Tujuan: Yogyakarta)");
}
    
    private void isiComboBox() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (String stasiun : jadwalMap.keySet()) {
            model.addElement(stasiun);
        }
        jComboBox1.setModel(model);

        jComboBox1.addActionListener(e -> tampilkanJadwal());
    }
    private void tampilkanJadwal() {
        String stasiun = (String) jComboBox1.getSelectedItem();
        String jadwal = jadwalMap.getOrDefault(stasiun, "Tidak ada jadwal untuk stasiun ini.");
        jTextArea1.setText("Jadwal KRL di stasiun " + stasiun + ":\n" + jadwal.replaceAll(", ", "\n"));
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 0, 51));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Jadwal KRL");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(233, 233, 233)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Kembali");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new MenuUtama(namamhs, nim, noUrut).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(FormStasiun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormStasiun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormStasiun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormStasiun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
