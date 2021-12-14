package QuanLy;

import Data.DBConection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class ThongKePhieuDat extends javax.swing.JFrame {

    Vector ds = null;
    Vector loaiTru = null;
    int selectedID = -1;

    public ThongKePhieuDat() {
        initComponents();
        jButton_Xem.setEnabled(false);
        this.setTitle("PHIẾU ĐẶT KHẢ DỤNG");
        layPD();
    }

    public void layPD() {
        Connection ketNoi = DBConection.layKetNoi();
        Vector vt;
        Vector v;
        ds = new Vector();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT MAPD, MAKH FROM PHIEUDAT");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vt = new Vector();
                vt.add(rs.getString("MAPD"));
                vt.add(rs.getString("MAKH"));
                ds.add(vt);
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }

        ketNoi = DBConection.layKetNoi();
        loaiTru = new Vector();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT PD.MAPD, PD.MAKH\n"
                    + "FROM PHIEUTHUE PT, PHIEUDAT PD\n"
                    + "WHERE PT.MAPD = PD.MAPD");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vt = new Vector();
                vt.add(rs.getString("MAPD"));
                vt.add(rs.getString("MAKH"));
                loaiTru.add(vt);
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        int i = 0, n = ds.size();
        Boolean kt = true;
        while (i < n) {
            kt = true;
            vt = (Vector) ds.get(i);
            for (int j = 0; j < loaiTru.size(); j++) {
                v = (Vector) loaiTru.get(j);
                if (((String) vt.get(0)).equals((String) v.get(0))) {
                    ds.remove(i);
                    kt = false;
                    break;
                }
            }
            if (kt == true) {
                i++;
            } else {
                n--;
            }
        }
        DefaultTableModel dtm = (DefaultTableModel) jTable_PDKhaDung.getModel();
        dtm.setNumRows(0);
        for (i = 0; i < ds.size(); i++) {
            dtm.addRow((Vector) ds.get(i));
        }
        jTable_PDKhaDung.setModel(dtm);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_PDKhaDung = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextField_TimCMND = new javax.swing.JTextField();
        jButton_Tim = new javax.swing.JButton();
        jButton_LoadLai = new javax.swing.JButton();
        jButton_Xem = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Phiếu đặt khả dụng");

        jTable_PDKhaDung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ PHIẾU ĐẶT", "CMND KHÁCH HÀNG"
            }
        ));
        jTable_PDKhaDung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_PDKhaDungMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_PDKhaDung);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Tìm CMND:");

        jButton_Tim.setText("Tìm");
        jButton_Tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_TimActionPerformed(evt);
            }
        });

        jButton_LoadLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlks/hinh/UPDATE.png"))); // NOI18N
        jButton_LoadLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LoadLaiActionPerformed(evt);
            }
        });

        jButton_Xem.setBackground(new java.awt.Color(255, 255, 255));
        jButton_Xem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_Xem.setForeground(new java.awt.Color(0, 0, 204));
        jButton_Xem.setText("XEM PHIẾU ĐẶT");
        jButton_Xem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_XemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton_Xem, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(63, 63, 63)
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(jTextField_TimCMND, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton_Tim)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton_LoadLai, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(207, 207, 207)
                            .addComponent(jLabel1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_TimCMND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Tim)
                    .addComponent(jButton_LoadLai, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_Xem, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_LoadLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LoadLaiActionPerformed
        layPD();
        jTextField_TimCMND.setText("");
        selectedID = -1;
        jButton_Xem.setEnabled(false);
    }//GEN-LAST:event_jButton_LoadLaiActionPerformed

    private void jButton_TimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_TimActionPerformed
        String s = jTextField_TimCMND.getText();
        Vector vt = null;
        int n = ds.size();
        int i = 0;
        DefaultTableModel dtm = (DefaultTableModel) jTable_PDKhaDung.getModel();
        dtm.setNumRows(0);
        while (i < n) {
            vt = (Vector) ds.get(i);

            if (((String) vt.get(1)).contains(s) == false) {
                ds.remove(i);
                n--;
            } else {
                dtm.addRow(vt);
                i++;
            }
        }
        jTable_PDKhaDung.setModel(dtm);
        selectedID = -1;
        jButton_Xem.setEnabled(false);
    }//GEN-LAST:event_jButton_TimActionPerformed

    private void jTable_PDKhaDungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_PDKhaDungMouseClicked
        selectedID = jTable_PDKhaDung.getSelectedRow();
        System.out.println("----->" + ds.get(selectedID).toString());
        if (selectedID != -1) {
            jButton_Xem.setEnabled(true);
        }
    }//GEN-LAST:event_jTable_PDKhaDungMouseClicked

    private void jButton_XemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_XemActionPerformed
        Vector vt = (Vector) ds.get(selectedID);
        FormXemPhieuDat_Va_ThuePhong frame = new FormXemPhieuDat_Va_ThuePhong(Integer.parseInt(vt.get(0).toString()));
        
//        frame.maPhieuDat = Integer.parseInt(vt.get(0).toString());
        frame.setVisible(true);
    }//GEN-LAST:event_jButton_XemActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongKePhieuDat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_LoadLai;
    private javax.swing.JButton jButton_Tim;
    private javax.swing.JButton jButton_Xem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_PDKhaDung;
    private javax.swing.JTextField jTextField_TimCMND;
    // End of variables declaration//GEN-END:variables
}
