package DangNhapBanDau;

import QuanLy.QuanLyNhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import qlks.CTDL.NhanVien;

public class DangNhapForm extends javax.swing.JFrame {

    public static NhanVien nhanVienDangDangNhap;
    public static QuanLyFrame ql = null;
    public static NhanVienFrame nv = null;

    public DangNhapForm() {
        initComponents();
        this.setTitle("ỨNG DỤNG QUẢN LÝ KHÁCH SẠN NHÓM 16");
        PrePare.UpDateNgayThueChuaThanhToan();
        PrePare.xoaPhieuThueCucManh();
        jPanelKhungDangNhap.setVisible(true);
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanelKhungDangNhap = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField_User = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPasswordField = new javax.swing.JPasswordField();
        jButtonDangNhap = new javax.swing.JButton();
        jButton_Thoat = new javax.swing.JButton();
        jLabelThongBaoThongTinDangNhap = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ứng Dụng Quản Lý Khách Sạn Nhóm [...]");
        setBackground(new java.awt.Color(153, 255, 153));

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 460));

        jPanelKhungDangNhap.setBackground(new java.awt.Color(0, 204, 204));
        jPanelKhungDangNhap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelKhungDangNhap.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelKhungDangNhap.setPreferredSize(new java.awt.Dimension(700, 260));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Username:");

        jTextField_User.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Password:");

        jButtonDangNhap.setBackground(new java.awt.Color(80, 80, 234));
        jButtonDangNhap.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jButtonDangNhap.setForeground(new java.awt.Color(0, 51, 51));
        jButtonDangNhap.setText("ĐĂNG NHẬP");
        jButtonDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDangNhapActionPerformed(evt);
            }
        });

        jButton_Thoat.setBackground(new java.awt.Color(255, 102, 102));
        jButton_Thoat.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jButton_Thoat.setForeground(new java.awt.Color(0, 51, 51));
        jButton_Thoat.setText("THOÁT");
        jButton_Thoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ThoatActionPerformed(evt);
            }
        });

        jLabelThongBaoThongTinDangNhap.setForeground(new java.awt.Color(255, 0, 0));
        jLabelThongBaoThongTinDangNhap.setText("   ");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlks/hinh/PASSWORD.png"))); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlks/hinh/USER.png"))); // NOI18N

        javax.swing.GroupLayout jPanelKhungDangNhapLayout = new javax.swing.GroupLayout(jPanelKhungDangNhap);
        jPanelKhungDangNhap.setLayout(jPanelKhungDangNhapLayout);
        jPanelKhungDangNhapLayout.setHorizontalGroup(
            jPanelKhungDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelKhungDangNhapLayout.createSequentialGroup()
                .addGroup(jPanelKhungDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelKhungDangNhapLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton_Thoat, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelKhungDangNhapLayout.createSequentialGroup()
                        .addGroup(jPanelKhungDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelKhungDangNhapLayout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(jLabel4)
                                .addGap(17, 17, 17))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelKhungDangNhapLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanelKhungDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelKhungDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelThongBaoThongTinDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                            .addComponent(jPasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                            .addComponent(jTextField_User))))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        jPanelKhungDangNhapLayout.setVerticalGroup(
            jPanelKhungDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelKhungDangNhapLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanelKhungDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelKhungDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField_User, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanelKhungDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelKhungDangNhapLayout.createSequentialGroup()
                        .addGroup(jPanelKhungDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabelThongBaoThongTinDangNhap))
                    .addComponent(jLabel4))
                .addGap(25, 25, 25)
                .addGroup(jPanelKhungDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Thoat, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        jLabel3.setFont(new java.awt.Font("Serif", 1, 28)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("QUẢN LÝ KHÁCH SẠN");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlks/hinh/HOTEL.png"))); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlks/hinh/LOBBY3.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jLabel7)
                .addGap(42, 42, 42))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(5, 5, 5)))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(180, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelKhungDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(180, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelKhungDangNhap, 255, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            Logger.getLogger(DangNhapForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }

    private void jButtonDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDangNhapActionPerformed
        String user = jTextField_User.getText();
        char[] temp = jPasswordField.getPassword();
        String pass = String.valueOf(temp);
        pass = maHoaPass(pass);
        boolean kt = false;
        String maBP = "";
        NhanVien nhanvien = null;
        Connection ketNoi = qlks.QLKS.layKetNoi();
        Vector vt;
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM DANGNHAP WHERE [USER] = '" + user + "' AND PASS = '" + pass + "'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                kt = true;
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(DangNhapForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (kt == false) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu không đúng!");
        } else {
            ketNoi = qlks.QLKS.layKetNoi();
            try {
                PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM NHANVIEN where MANV = '" + user + "'");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    nhanvien = new NhanVien(rs.getString("MANV"), rs.getString("HO"), rs.getString("TEN"), rs.getString("PHAI"), rs.getDate("NGAYSINH"), rs.getString("EMAIL"), rs.getString("SDT"), rs.getString("MABP"));
                }
                rs.close();
                ps.close();
                ketNoi.close();
            } catch (SQLException ex) {
                Logger.getLogger(DangNhapForm.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.nhanVienDangDangNhap = nhanvien;

            if (nhanvien.getMaBP().equals("QL")) {
                ql = new QuanLyFrame(nhanvien);
                ql.setVisible(true);
                this.setVisible(false);
            } else {
                nv = new NhanVienFrame(nhanvien);
                nv.setVisible(true);
                this.setVisible(false);
            }
        }
    }//GEN-LAST:event_jButtonDangNhapActionPerformed

    private void jButton_ThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ThoatActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton_ThoatActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangNhapForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDangNhap;
    private javax.swing.JButton jButton_Thoat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelThongBaoThongTinDangNhap;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelKhungDangNhap;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JTextField jTextField_User;
    // End of variables declaration//GEN-END:variables
}
