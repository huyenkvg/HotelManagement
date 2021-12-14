package QuanLy;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.JOptionPane;

public class QuanLyTaiKhoan extends javax.swing.JFrame {

    Vector ds = null;

    public QuanLyTaiKhoan() {
        initComponents();
        this.setTitle("QUẢN LÝ TÀI KHOẢN");
        khoiTaoBaoLoi();
        khoiTaoTXT();
        layDSTaiKhoan();
    }

    private void khoiTaoBaoLoi() {
        jLabel_user.setText(" ");
        jLabel_pass.setText(" ");
    }

    private void khoiTaoTXT() {
        jTextField_user.setEditable(true);
        jTextField_user.setText("");
        jTextField_pass.setText("");
    }

    private void layDSTaiKhoan() {
        DefaultTableModel dtm = (DefaultTableModel) jTable_TaiKhoan.getModel();
        dtm.setNumRows(0);
        Connection ketNoi = qlks.QLKS.layKetNoi();
        Vector vt;
        ds = new Vector();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT [USER], PASS, MABP FROM DANGNHAP, NHANVIEN WHERE DANGNHAP.[USER] = NHANVIEN.MANV");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vt = new Vector();
                vt.add(rs.getString("USER"));
                //vt.add(rs.getString("PASS"));
                vt.add(rs.getString("MABP"));
                ds.add(vt);
                dtm.addRow(vt);
            }
            jTable_TaiKhoan.setModel(dtm);
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void themUser(String user, String pass) {
        pass = maHoaPass(pass);
        Connection ketNoi = qlks.QLKS.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("INSERT INTO DANGNHAP VALUES (?,?)");
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void xoaUser(String user) {
        Connection ketNoi = qlks.QLKS.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("DELETE FROM DANGNHAP WHERE [USER] = ?");
            ps.setString(1, user);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void suaUser(String user, String pass) {
        pass = maHoaPass(pass);
        Connection ketNoi = qlks.QLKS.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("UPDATE DANGNHAP SET PASS = ? WHERE [USER] = ?");
            ps.setString(1, pass);
            ps.setString(2, user);
            ps.executeUpdate();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String maHoaPass(String s) {
        String temp = "";
        Connection ketNoi = qlks.QLKS.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT CONVERT(VARCHAR(32), HashBytes('MD5', '" + s + "'), 2) as md5");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                temp = rs.getString(1);
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelNenFrame = new javax.swing.JPanel();
        jPanelThongTinNhanVien = new javax.swing.JPanel();
        jButton_Them = new javax.swing.JButton();
        jButton_Xoa = new javax.swing.JButton();
        jButton_Sua = new javax.swing.JButton();
        jButton_XoaTXT = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField_user = new javax.swing.JTextField();
        jTextField_pass = new javax.swing.JTextField();
        jLabel_user = new javax.swing.JLabel();
        jLabel_pass = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_TaiKhoan = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setTitle("Quản Lý Thông Tin Tài Khoản");
        setBackground(new java.awt.Color(153, 153, 255));
        setResizable(false);

        jPanelNenFrame.setBackground(new java.awt.Color(102, 153, 255));
        jPanelNenFrame.setMinimumSize(new java.awt.Dimension(1200, 600));
        jPanelNenFrame.setPreferredSize(new java.awt.Dimension(1200, 620));

        jPanelThongTinNhanVien.setBackground(new java.awt.Color(153, 204, 255));
        jPanelThongTinNhanVien.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Thông tin tài khoản người dùng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 51))); // NOI18N
        jPanelThongTinNhanVien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanelThongTinNhanVien.setPreferredSize(new java.awt.Dimension(1160, 300));

        jButton_Them.setBackground(new java.awt.Color(51, 51, 255));
        jButton_Them.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlks/hinh/THEM.png"))); // NOI18N
        jButton_Them.setText("THÊM");
        jButton_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ThemActionPerformed(evt);
            }
        });

        jButton_Xoa.setBackground(new java.awt.Color(0, 153, 255));
        jButton_Xoa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlks/hinh/DELETE1.png"))); // NOI18N
        jButton_Xoa.setText("XÓA");
        jButton_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_XoaActionPerformed(evt);
            }
        });

        jButton_Sua.setBackground(new java.awt.Color(255, 102, 102));
        jButton_Sua.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlks/hinh/SUA.png"))); // NOI18N
        jButton_Sua.setText("SỬA");
        jButton_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SuaActionPerformed(evt);
            }
        });

        jButton_XoaTXT.setBackground(new java.awt.Color(255, 153, 102));
        jButton_XoaTXT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_XoaTXT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlks/hinh/RESET.png"))); // NOI18N
        jButton_XoaTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_XoaTXTActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Username:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Password:");

        jTextField_user.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jTextField_pass.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel_user.setForeground(new java.awt.Color(255, 0, 0));
        jLabel_user.setText("jLabel5");

        jLabel_pass.setForeground(new java.awt.Color(255, 0, 0));
        jLabel_pass.setText("jLabel5");

        javax.swing.GroupLayout jPanelThongTinNhanVienLayout = new javax.swing.GroupLayout(jPanelThongTinNhanVien);
        jPanelThongTinNhanVien.setLayout(jPanelThongTinNhanVienLayout);
        jPanelThongTinNhanVienLayout.setHorizontalGroup(
            jPanelThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelThongTinNhanVienLayout.createSequentialGroup()
                .addGroup(jPanelThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelThongTinNhanVienLayout.createSequentialGroup()
                        .addGap(392, 392, 392)
                        .addComponent(jButton_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_XoaTXT))
                    .addGroup(jPanelThongTinNhanVienLayout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addGroup(jPanelThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelThongTinNhanVienLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(26, 26, 26)
                                .addGroup(jPanelThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_user)
                                    .addComponent(jTextField_user, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelThongTinNhanVienLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(28, 28, 28)
                                .addGroup(jPanelThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_pass)
                                    .addComponent(jTextField_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanelThongTinNhanVienLayout.setVerticalGroup(
            jPanelThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelThongTinNhanVienLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanelThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_user, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_user)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_pass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(jPanelThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_XoaTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        jScrollPane1.setPreferredSize(new java.awt.Dimension(1160, 200));

        jTable_TaiKhoan.setBackground(new java.awt.Color(125, 141, 242));
        jTable_TaiKhoan.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable_TaiKhoan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable_TaiKhoan.setForeground(new java.awt.Color(0, 51, 51));
        jTable_TaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "USERNAME", "MÃ BỘ PHẬN"
            }
        ));
        jTable_TaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_TaiKhoanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_TaiKhoan);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText(" Danh sách người dùng");

        javax.swing.GroupLayout jPanelNenFrameLayout = new javax.swing.GroupLayout(jPanelNenFrame);
        jPanelNenFrame.setLayout(jPanelNenFrameLayout);
        jPanelNenFrameLayout.setHorizontalGroup(
            jPanelNenFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNenFrameLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelNenFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanelThongTinNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelNenFrameLayout.setVerticalGroup(
            jPanelNenFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNenFrameLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanelThongTinNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jLabel1.getAccessibleContext().setAccessibleName("Danh sách nhân viên");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelNenFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 914, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelNenFrame, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean checkUserDeThem(String user) {
        boolean kt = false;
        if (user.equals("")) {
            jLabel_user.setText("User không được để trống!");
            return kt;
        }
        Connection ketNoi = qlks.QLKS.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM NHANVIEN where MANV = '" + user + "'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                kt = true;
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (kt == false) {
            jLabel_user.setText("Nhân viên này không tồn tại!");
            return kt;
        }
        ketNoi = qlks.QLKS.layKetNoi();
        try {
            ketNoi = qlks.QLKS.layKetNoi();
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM DANGNHAP WHERE [USER] = '" + user + "'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                kt = false;
                jLabel_user.setText("User này đã tồn tại!");
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kt;
    }
    
    private boolean checkUserDeSua(String user) {
        boolean kt = false;
        if (user.equals("")) {
            jLabel_user.setText("User không được để trống!");
            return kt;
        }
        Connection ketNoi = qlks.QLKS.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM DANGNHAP where [USER] = '" + user + "'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                kt = true;
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (kt == false) {
            jLabel_user.setText("User này không tồn tại để sửa!");
            return kt;
        }
//        ketNoi = qlks.QLKS.layKetNoi();
//        try {
//            ketNoi = qlks.QLKS.layKetNoi();
//            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM DANGNHAP WHERE [USER] = '" + user + "'");
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                kt = false;
//                jLabel_user.setText("User này đã tồn tại!");
//            }
//            rs.close();
//            ps.close();
//            ketNoi.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return kt;
    }

    private boolean checkPass(String pass) {
        if (pass.equals("")) {
            jLabel_pass.setText("Password không được để trống!");
            return false;
        }
        String mau = "[A-Za-z0-9]{5,15}";
        if (pass.matches(mau) == false) {
            jLabel_pass.setText("Password không hợp lệ!");
            return false;
        } else {
            return true;
        }
    }

    private boolean kiemTraUserDeXoa(String user) {
        boolean kt = false;
        Connection ketNoi = qlks.QLKS.layKetNoi();
        try {
            ketNoi = qlks.QLKS.layKetNoi();
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM DANGNHAP WHERE [USER] = '" + user + "'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                kt = true;
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (kt == false) {
            jLabel_user.setText("User này không tồn tại để xóa!");
        }
        return kt;
    }

    private void jButton_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ThemActionPerformed
        boolean kt = true;
        String user = jTextField_user.getText();
        String pass = jTextField_pass.getText();
        if (checkUserDeThem(user) == false) {
            kt = false;
        } else {
            jLabel_user.setText(" ");
        }
        if (checkPass(pass) == false) {
            kt = false;
        } else {
            jLabel_pass.setText(" ");
        }

        if (kt == true) {
            themUser(user, pass);
            khoiTaoBaoLoi();
            khoiTaoTXT();
            layDSTaiKhoan();
            JOptionPane.showMessageDialog(this, "Thêm tài khoản thành công!");
        }
    }//GEN-LAST:event_jButton_ThemActionPerformed

    private void jButton_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SuaActionPerformed
        boolean kt = true;
        String user = jTextField_user.getText();
        String pass = jTextField_pass.getText();

        if (checkUserDeSua(user) == false) {
            kt = false;
        } 
        else {
            jLabel_user.setText(" ");
        }
        if (checkPass(pass) == false) {
            kt = false;
        } 
        else {
            jLabel_pass.setText(" ");
        }

        if (kt == true) {
            jTextField_user.setEditable(true);
            suaUser(user, pass);
            khoiTaoBaoLoi();
            khoiTaoTXT();
            layDSTaiKhoan();
            JOptionPane.showMessageDialog(this, "Sửa tài khoản thành công!");
        }
    }//GEN-LAST:event_jButton_SuaActionPerformed

    private void jButton_XoaTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_XoaTXTActionPerformed
        khoiTaoTXT();
        khoiTaoBaoLoi();
    }//GEN-LAST:event_jButton_XoaTXTActionPerformed

    private void jTable_TaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_TaiKhoanMouseClicked
        int i = jTable_TaiKhoan.getSelectedRow();
        Vector vt = (Vector) ds.get(i);
        jTextField_user.setEditable(false);

        jTextField_user.setText((String) vt.get(0));
    }//GEN-LAST:event_jTable_TaiKhoanMouseClicked

    private void jButton_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_XoaActionPerformed
        String user = jTextField_user.getText();

        boolean kt = kiemTraUserDeXoa(user);

        if (kt == true) {
            int ret = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa user này?", "Xác nhận", 0);
            if (ret == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (ret == JOptionPane.OK_OPTION) {
                xoaUser(user);
                layDSTaiKhoan();
                khoiTaoTXT();
                khoiTaoBaoLoi();
                jTextField_user.setEditable(true);
                JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công!");
            }
        }
    }//GEN-LAST:event_jButton_XoaActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyTaiKhoan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Sua;
    private javax.swing.JButton jButton_Them;
    private javax.swing.JButton jButton_Xoa;
    private javax.swing.JButton jButton_XoaTXT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel_pass;
    private javax.swing.JLabel jLabel_user;
    private javax.swing.JPanel jPanelNenFrame;
    private javax.swing.JPanel jPanelThongTinNhanVien;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_TaiKhoan;
    private javax.swing.JTextField jTextField_pass;
    private javax.swing.JTextField jTextField_user;
    // End of variables declaration//GEN-END:variables
}
