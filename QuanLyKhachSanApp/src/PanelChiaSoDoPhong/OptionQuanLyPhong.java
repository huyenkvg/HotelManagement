package PanelChiaSoDoPhong;

import Data.RoomModify;
import static PanelChiaSoDoPhong.SoDoPhong.CHUADON;
import static PanelChiaSoDoPhong.SoDoPhong.DADAT;
import static PanelChiaSoDoPhong.SoDoPhong.DANGTHUE;
import static PanelChiaSoDoPhong.SoDoPhong.SUACHUA;
import static PanelChiaSoDoPhong.SoDoPhong.TRONG;
import QuanLy.FormXemPhieuDat_Va_ThuePhong;
import javax.swing.JOptionPane;
import qlks.CTDL.KhachHang;
import qlks.CTDL.Phong;
import qlks.CTDL.ThongTinThuePhong;

public class OptionQuanLyPhong extends javax.swing.JFrame {

    Phong phong;
    int maphieudat;
    public static ThongTinThuePhong thongtin;
    SoDoPhong sodo;

    public OptionQuanLyPhong() {
        initComponents();
        jPanelTrangThai.setVisible(false);
    }

    public OptionQuanLyPhong(Phong phong, SoDoPhong sodo) {
        initComponents();
        this.phong = phong;
        this.sodo = sodo;
        jPanelTrangThai.setLocation(0, 0);
        jPanel_Options.setLocation(0, 0);
        this.setTitle("Bạn Muốn làm gì phòng [" + phong.getMaPhong() + "]");
        jPanelTrangThai.setVisible(false);
        switch (phong.getTrangThai()) {
            case "Dơ":
//                        jButton_DatPhong.setEnabled(false);
//                        jButton_ThuePhong.setEnabled(false);
                break;
            case "Đặt":
                
                          jButton_ThayTrangThai.setEnabled(false);
//                        jButton_DatPhong.setEnabled(false);
//                        jButton_ThayTrangThai.setEnabled(false);
//                        jButton_HuyDatPhong.setEnabled(true);
                break;
            case "Trống":
                break;
            case "Sửa":
//                        jButton_DatPhong.setEnabled(false);
//                        jButton_ThuePhong.setEnabled(false);
                break;
            case "Thuê":
                           jButton_ThayTrangThai.setEnabled(false);
//                        jButton_DatPhong.setEnabled(false);
//                        jButton_ThuePhong.setEnabled(false);

                break;
            default:
                break;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Options = new javax.swing.JPanel();
        jButton_ThayTrangThai = new javax.swing.JButton();
        jPanelTrangThai = new javax.swing.JPanel();
        btn_ChuaDon = new javax.swing.JButton();
        btn_SuaChua = new javax.swing.JButton();
        btnTrong = new javax.swing.JButton();

        setTitle("Bạn Muốn làm gì tui?");
        setBackground(new java.awt.Color(153, 153, 255));
        setResizable(false);

        jPanel_Options.setBackground(new java.awt.Color(204, 255, 255));
        jPanel_Options.setPreferredSize(new java.awt.Dimension(350, 250));

        jButton_ThayTrangThai.setBackground(new java.awt.Color(0, 0, 153));
        jButton_ThayTrangThai.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_ThayTrangThai.setForeground(new java.awt.Color(255, 255, 255));
        jButton_ThayTrangThai.setText("THAY ĐỔI TRẠNG THÁI");
        jButton_ThayTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ThayTrangThaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_OptionsLayout = new javax.swing.GroupLayout(jPanel_Options);
        jPanel_Options.setLayout(jPanel_OptionsLayout);
        jPanel_OptionsLayout.setHorizontalGroup(
            jPanel_OptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_OptionsLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jButton_ThayTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel_OptionsLayout.setVerticalGroup(
            jPanel_OptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_OptionsLayout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jButton_ThayTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        jPanelTrangThai.setPreferredSize(new java.awt.Dimension(350, 250));

        btn_ChuaDon.setBackground(new java.awt.Color(255, 144, 109));
        btn_ChuaDon.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_ChuaDon.setText("CHƯA DỌN");
        btn_ChuaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ChuaDonActionPerformed(evt);
            }
        });

        btn_SuaChua.setBackground(new java.awt.Color(255, 131, 198));
        btn_SuaChua.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_SuaChua.setText("ĐANG SỬA CHỮA");
        btn_SuaChua.setPreferredSize(new java.awt.Dimension(120, 25));
        btn_SuaChua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaChuaActionPerformed(evt);
            }
        });

        btnTrong.setBackground(new java.awt.Color(255, 255, 204));
        btnTrong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTrong.setText("TRỐNG");
        btnTrong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelTrangThaiLayout = new javax.swing.GroupLayout(jPanelTrangThai);
        jPanelTrangThai.setLayout(jPanelTrangThaiLayout);
        jPanelTrangThaiLayout.setHorizontalGroup(
            jPanelTrangThaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTrangThaiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTrangThaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTrong, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_SuaChua, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ChuaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanelTrangThaiLayout.setVerticalGroup(
            jPanelTrangThaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTrangThaiLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(btn_ChuaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_SuaChua, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTrong, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_Options, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_Options, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_ThayTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ThayTrangThaiActionPerformed
        // TODO add your handling code here:
        jPanelTrangThai.setVisible(true);
        jPanel_Options.setVisible(false);


    }//GEN-LAST:event_jButton_ThayTrangThaiActionPerformed
   
    private void btn_ChuaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ChuaDonActionPerformed
        // TODO add your handling code here:
        phong.setTrangThai("Dơ");
        sodo.thaydoiTrangThaiPhong(phong);
        RoomModify.UpdatePhong(phong);
        this.setVisible(false);

    }//GEN-LAST:event_btn_ChuaDonActionPerformed

    private void btn_SuaChuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaChuaActionPerformed
        // TODO add your handling code here:
        phong.setTrangThai("Sửa");
        sodo.thaydoiTrangThaiPhong(phong);
        RoomModify.UpdatePhong(phong);
        this.setVisible(false);
    }//GEN-LAST:event_btn_SuaChuaActionPerformed

    private void btnTrongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrongActionPerformed
        // TODO add your handling code here:
        phong.setTrangThai("Trống");
        sodo.thaydoiTrangThaiPhong(phong);
        RoomModify.UpdatePhong(phong);
        this.setVisible(false);
    }//GEN-LAST:event_btnTrongActionPerformed

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
            java.util.logging.Logger.getLogger(OptionQuanLyPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OptionQuanLyPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OptionQuanLyPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OptionQuanLyPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new OptionQuanLyPhong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTrong;
    private javax.swing.JButton btn_ChuaDon;
    private javax.swing.JButton btn_SuaChua;
    private javax.swing.JButton jButton_ThayTrangThai;
    private javax.swing.JPanel jPanelTrangThai;
    private javax.swing.JPanel jPanel_Options;
    // End of variables declaration//GEN-END:variables
}
