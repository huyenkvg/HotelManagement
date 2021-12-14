package QuanLy;

import Data.DBConection;
import Data.DatThuePhongModify;
import Data.RoomModify;
import Huong.XuatHoaDonHuong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ThongKePhieuThueKhaDung extends javax.swing.JFrame {

    Vector ds = null;
    Vector loaiTru = null;
    int selectedID = -1;

    public ThongKePhieuThueKhaDung() {
        initComponents();
        jButton_TraPhong.setEnabled(false);
        this.setTitle("Phiếu THUÊ khả dụng");
        layPT();
    }

    private void layPT() {
        Connection ketNoi = DBConection.layKetNoi();
        Vector vt;
        Vector v;
        ds = new Vector();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT MAPT, MAKH FROM PHIEUTHUE");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vt = new Vector();
                vt.add(rs.getString("MAPT"));
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
            PreparedStatement ps = ketNoi.prepareStatement("SELECT PT.MAPT, PT.MAKH\n"
                    + "FROM PHIEUTHUE PT, HOADON HD\n"
                    + "WHERE PT.MAPT = HD.MAPT");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vt = new Vector();
                vt.add(rs.getString("MAPT"));
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
        DefaultTableModel dtm = (DefaultTableModel) jTable_PThueKhaDung.getModel();
        dtm.setNumRows(0);
        for (i = 0; i < ds.size(); i++) {
            dtm.addRow((Vector) ds.get(i));
        }
        jTable_PThueKhaDung.setModel(dtm);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_PThueKhaDung = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextField_TimCMND = new javax.swing.JTextField();
        jButton_Tim = new javax.swing.JButton();
        jButton_LoadLai = new javax.swing.JButton();
        jButton_TraPhong = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Phiếu Thuê khả dụng");

        jTable_PThueKhaDung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ PHIẾU THUÊ", "CMND KHÁCH HÀNG"
            }
        ));
        jTable_PThueKhaDung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_PThueKhaDungMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_PThueKhaDung);

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

        jButton_TraPhong.setBackground(new java.awt.Color(255, 255, 255));
        jButton_TraPhong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_TraPhong.setForeground(new java.awt.Color(0, 0, 204));
        jButton_TraPhong.setText("TRẢ PHÒNG-THANH TOÁN ");
        jButton_TraPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_TraPhongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField_TimCMND, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Tim)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_LoadLai, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton_TraPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_TimCMND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Tim)
                    .addComponent(jButton_LoadLai, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_TraPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_LoadLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LoadLaiActionPerformed
        layPT();
        jTextField_TimCMND.setText("");
        selectedID = -1;
        jButton_TraPhong.setEnabled(false);
    }//GEN-LAST:event_jButton_LoadLaiActionPerformed

    private void jButton_TimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_TimActionPerformed
        String s = jTextField_TimCMND.getText();
        Vector vt = null;
        int n = ds.size();
        int i = 0;
        DefaultTableModel dtm = (DefaultTableModel) jTable_PThueKhaDung.getModel();
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
        jTable_PThueKhaDung.setModel(dtm);
        selectedID = -1;
        jButton_TraPhong.setEnabled(false);
    }//GEN-LAST:event_jButton_TimActionPerformed

    private void jTable_PThueKhaDungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_PThueKhaDungMouseClicked
        // TODO add your handling code here:
        selectedID = jTable_PThueKhaDung.getSelectedRow();
        System.out.println("----->" + ds.get(selectedID).toString());
        if (selectedID != -1) {
            jButton_TraPhong.setEnabled(true);
        }
    }//GEN-LAST:event_jTable_PThueKhaDungMouseClicked

    private void jButton_TraPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_TraPhongActionPerformed
        // TODO add your handling code here:
        int maptDangChon;
        System.out.println("----->" + ds.get(selectedID).toString());

        Vector vt = (Vector) ds.get(selectedID);
        maptDangChon = Integer.parseInt(vt.get(0).toString());
        if (RoomModify.demDanhSachCTPhieuThue(maptDangChon) == 0) {

            if (JOptionPane.showConfirmDialog(null, "Phiếu Thuê này Không Có Chi Thuê!\n Hủy bỏ?", "Thông Báo", JOptionPane.YES_NO_OPTION) == 0) {
                DatThuePhongModify.HuyBoPhietThue(maptDangChon);
            }
            jButton_TraPhong.setEnabled(false);
        } else {
            XuatHoaDonHuong xuat = new XuatHoaDonHuong(maptDangChon);
            xuat.setVisible(true);
            return;
        }

    }//GEN-LAST:event_jButton_TraPhongActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongKePhieuThueKhaDung().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_LoadLai;
    private javax.swing.JButton jButton_Tim;
    private javax.swing.JButton jButton_TraPhong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_PThueKhaDung;
    private javax.swing.JTextField jTextField_TimCMND;
    // End of variables declaration//GEN-END:variables
}
