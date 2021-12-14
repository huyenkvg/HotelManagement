package DangNhapBanDau;

import QuanLy.FormDatPhong;
import QuanLy.FormThuePhong;
import QuanLy.QuanLyDichVu;
import QuanLy.QuanLyHangPhong;
import QuanLy.QuanLyKhachHang;
import QuanLy.QuanLyNhanVien;
import QuanLy.QuanLyPhong;
import QuanLy.QuanLyTaiKhoan;
import QuanLy.QuanLyTienNghi;
import QuanLy.ThongKeDoanhSo;
import QuanLy.ThongKePhieuDat;
import QuanLy.ThongKePhieuThueKhaDung;
import java.util.Date;
import qlks.CTDL.NhanVien;

public class QuanLyFrame extends javax.swing.JFrame {

    public static NhanVien nhanVienDangDangNhap;
    private static QuanLyNhanVien qlnv = null;
    private static QuanLyTaiKhoan qltk = null;
    public static QuanLyPhong qlp = null;
    private static QuanLyDichVu qldv = null;
    private static QuanLyTienNghi qltn = null;
    private static QuanLyHangPhong qlhp = null;
    private static QuanLyKhachHang qlkh = null;
    private static FormDatPhong dp = null;
    private static FormThuePhong tp = null;
    private static ThongKeDoanhSo tkds = null;
    private static ThongKePhieuDat pdkd = null;
    private static ThongKePhieuThueKhaDung ptkd = null;

    public QuanLyFrame(NhanVien nv) {
        this.nhanVienDangDangNhap = nv;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBarQuanLy = new javax.swing.JMenuBar();
        jMenuQlyHeThong = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuQlyQuanLy = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuThongKe1 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("---[ QUẢN LÝ ]---");
        setIconImage(getIconImage());
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));
        jPanel1.setMaximumSize(new java.awt.Dimension(1496, 806));
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(1496, 806));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/planet-1702788_1920.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel1)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 806, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 720, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 1400;
        gridBagConstraints.ipady = 720;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 43, 0);
        getContentPane().add(jPanel1, gridBagConstraints);

        jMenuBarQuanLy.setBackground(new java.awt.Color(204, 204, 255));
        jMenuBarQuanLy.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenuBarQuanLy.setForeground(new java.awt.Color(255, 255, 255));
        jMenuBarQuanLy.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jMenuBarQuanLy.setPreferredSize(new java.awt.Dimension(195, 30));

        jMenuQlyHeThong.setText("HỆ THỐNG");

        jMenuItem9.setText("Quản Lý Tài Khoản");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenuQlyHeThong.add(jMenuItem9);

        jMenuItem10.setText("Thoát");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenuQlyHeThong.add(jMenuItem10);

        jMenuBarQuanLy.add(jMenuQlyHeThong);

        jMenuQlyQuanLy.setBackground(new java.awt.Color(153, 255, 255));
        jMenuQlyQuanLy.setText("QUẢN LÝ");

        jMenuItem1.setText("Quản Lý Nhân Viên");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuQlyQuanLy.add(jMenuItem1);

        jMenuItem2.setText("Quản Lý Phòng");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenuQlyQuanLy.add(jMenuItem2);

        jMenuItem3.setText("Quản Lý Khách Hàng");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenuQlyQuanLy.add(jMenuItem3);

        jMenuItem4.setText("Quản Lý Dịch Vụ");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenuQlyQuanLy.add(jMenuItem4);

        jMenuItem7.setText("Quản Lý Tiện Nghi");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenuQlyQuanLy.add(jMenuItem7);

        jMenuItem8.setText("Quản Lý Hạng Phòng");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenuQlyQuanLy.add(jMenuItem8);

        jMenuItem11.setText("Đặt Phòng");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenuQlyQuanLy.add(jMenuItem11);

        jMenuItem12.setText("Thuê Phòng");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenuQlyQuanLy.add(jMenuItem12);

        jMenuBarQuanLy.add(jMenuQlyQuanLy);

        jMenuThongKe1.setText("THỐNG KÊ");
        jMenuThongKe1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuThongKe1MouseClicked(evt);
            }
        });

        jMenuItem6.setText("Thống Kê Doanh Số");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenuThongKe1.add(jMenuItem6);

        jMenuItem13.setText("Phiếu Đặt Khả Dụng");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenuThongKe1.add(jMenuItem13);

        jMenuItem5.setText("Phiếu Thuê Khả Dụng");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenuThongKe1.add(jMenuItem5);

        jMenuBarQuanLy.add(jMenuThongKe1);

        setJMenuBar(jMenuBarQuanLy);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        qlnv = new QuanLyNhanVien();
        qlnv.setVisible(true);
//        qlnv = null;
//        if(qlnv != null)
//            qlnv.setVisible(false);
        if(qltk != null)
            qltk.setVisible(false);
        if(qlp != null)
            qlp.setVisible(false);
        if(qldv != null)
            qldv.setVisible(false);
        if(qltn != null)
            qltn.setVisible(false);
        if(qlhp != null)
            qlhp.setVisible(false);
//        if(qlkh != null)
//            qlkh.setVisible(false);
        if(dp != null)
            dp.setVisible(false);
        if(tp != null)
            tp.setVisible(false);
        if(tkds!= null)
            tkds.setVisible(false);
        if(pdkd!= null)
            pdkd.setVisible(false);
        if(ptkd!= null)
            ptkd.setVisible(false);
//        qlnv = null;
        qltk = null;
        qlp = null;
        qldv = null;
        qltn = null;
        qlhp = null;
//        qlkh = null;
        dp = null;
        tp = null;
        tkds = null;
        pdkd = null;
        ptkd = null;
//        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        qltk = new QuanLyTaiKhoan();
        qltk.setVisible(true);
        
        if(qlnv != null)
            qlnv.setVisible(false);
//        if(qltk != null)
//            qltk.setVisible(false);
        if(qlp != null)
            qlp.setVisible(false);
        if(qldv != null)
            qldv.setVisible(false);
        if(qltn != null)
            qltn.setVisible(false);
        if(qlhp != null)
            qlhp.setVisible(false);
        if(qlkh != null)
            qlkh.setVisible(false);
        if(dp != null)
            dp.setVisible(false);
        if(tp != null)
            tp.setVisible(false);
        if(tkds!= null)
            tkds.setVisible(false);
        if(pdkd!= null)
            pdkd.setVisible(false);
        if(ptkd!= null)
            ptkd.setVisible(false);
        qlnv = null;
//        qltk = null;
        qlp = null;
        qldv = null;
        qltn = null;
        qlhp = null;
        qlkh = null;
        dp = null;
        tp = null;
        tkds = null;
        pdkd = null;
        ptkd = null;
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        qlp = new QuanLyPhong(nhanVienDangDangNhap);
        qlp.setVisible(true);
        
//        if(qlnv != null)
//            qlnv.setVisible(false);
//        if(qltk != null)
//            qltk.setVisible(false);
//        if(qlp != null)
//            qlp.setVisible(false);
//        if(qldv != null)
//            qldv.setVisible(false);
//        if(qltn != null)
//            qltn.setVisible(false);
//        if(qlhp != null)
//            qlhp.setVisible(false);
//        if(qlkh != null)
//            qlkh.setVisible(false);
        if(dp != null)
            dp.setVisible(false);
        if(tp != null)
            tp.setVisible(false);
        if(tkds!= null)
            tkds.setVisible(false);
        if(pdkd!= null)
            pdkd.setVisible(false);
        if(ptkd!= null)
            ptkd.setVisible(false);
//        qlnv = null;
//        qltk = null;
////        qlp = null;
//        qldv = null;
//        qltn = null;
//        qlhp = null;
//        qlkh = null;
        dp = null;
        tp = null;
        tkds = null;
        pdkd = null;
        ptkd = null;
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        qldv = new QuanLyDichVu();
        qldv.setVisible(true);
        
        if(qlnv != null)
            qlnv.setVisible(false);
        if(qltk != null)
            qltk.setVisible(false);
        if(qlp != null)
            qlp.setVisible(false);
//        if(qldv != null)
//            qldv.setVisible(false);
        if(qltn != null)
            qltn.setVisible(false);
        if(qlhp != null)
            qlhp.setVisible(false);
        if(qlkh != null)
            qlkh.setVisible(false);
        if(dp != null)
            dp.setVisible(false);
        if(tp != null)
            tp.setVisible(false);
        if(tkds!= null)
            tkds.setVisible(false);
        if(pdkd!= null)
            pdkd.setVisible(false);
        if(ptkd!= null)
            ptkd.setVisible(false);
        qlnv = null;
        qltk = null;
        qlp = null;
//        qldv = null;
        qltn = null;
        qlhp = null;
        qlkh = null;
        dp = null;
        tp = null;
        tkds = null;
        pdkd = null;
        ptkd = null;
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        qltn = new QuanLyTienNghi();
        qltn.setVisible(true);
        
        if(qlnv != null)
            qlnv.setVisible(false);
        if(qltk != null)
            qltk.setVisible(false);
        if(qlp != null)
            qlp.setVisible(false);
        if(qldv != null)
            qldv.setVisible(false);
//        if(qltn != null)
//            qltn.setVisible(false);
        if(qlhp != null)
            qlhp.setVisible(false);
        if(qlkh != null)
            qlkh.setVisible(false);
        if(dp != null)
            dp.setVisible(false);
        if(tp != null)
            tp.setVisible(false);
        if(tkds!= null)
            tkds.setVisible(false);
        if(pdkd!= null)
            pdkd.setVisible(false);
        if(ptkd!= null)
            ptkd.setVisible(false);
        qlnv = null;
        qltk = null;
        qlp = null;
        qldv = null;
//        qltn = null;
        qlhp = null;
        qlkh = null;
        dp = null;
        tp = null;
        tkds = null;
        pdkd = null;
        ptkd = null;
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        qlhp = new QuanLyHangPhong();
        qlhp.setVisible(true);
        
        if(qlnv != null)
            qlnv.setVisible(false);
        if(qltk != null)
            qltk.setVisible(false);
        if(qlp != null)
            qlp.setVisible(false);
        if(qldv != null)
            qldv.setVisible(false);
        if(qltn != null)
            qltn.setVisible(false);
//        if(qlhp != null)
//            qlhp.setVisible(false);
        if(qlkh != null)
            qlkh.setVisible(false);
        if(dp != null)
            dp.setVisible(false);
        if(tp != null)
            tp.setVisible(false);
        if(tkds!= null)
            tkds.setVisible(false);
        if(pdkd!= null)
            pdkd.setVisible(false);
        if(ptkd!= null)
            ptkd.setVisible(false);
        qlnv = null;
        qltk = null;
        qlp = null;
        qldv = null;
        qltn = null;
//        qlhp = null;
        qlkh = null;
        dp = null;
        tp = null;
        tkds = null;
        pdkd = null;
        ptkd = null;
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        qlkh = new QuanLyKhachHang();
        qlkh.setVisible(true);
        
//        if(qlnv != null)
//            qlnv.setVisible(false);
        if(qltk != null)
            qltk.setVisible(false);
        if(qlp != null)
            qlp.setVisible(false);
        if(qldv != null)
            qldv.setVisible(false);
        if(qltn != null)
            qltn.setVisible(false);
        if(qlhp != null)
            qlhp.setVisible(false);
//        if(qlkh != null)
//            qlkh.setVisible(false);
        if(dp != null)
            dp.setVisible(false);
        if(tp != null)
            tp.setVisible(false);
        if(tkds!= null)
            tkds.setVisible(false);
        if(pdkd!= null)
            pdkd.setVisible(false);
        if(ptkd!= null)
            ptkd.setVisible(false);
//        qlnv = null;
        qltk = null;
        qlp = null;
        qldv = null;
        qltn = null;
        qlhp = null;
//        qlkh = null;
        dp = null;
        tp = null;
        tkds = null;
        pdkd = null;
        ptkd = null;
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        dp = new FormDatPhong();
        dp.setVisible(true);
        
//        if(qlnv != null)
//            qlnv.setVisible(false);
//        if(qltk != null)
//            qltk.setVisible(false);
        if(qlp != null)
            qlp.setVisible(false);
//        if(qldv != null)
//            qldv.setVisible(false);
//        if(qltn != null)
//            qltn.setVisible(false);
//        if(qlhp != null)
//            qlhp.setVisible(false);
        if(qlkh != null)
            qlkh.setVisible(false);
//        if(dp != null)
//            dp.setVisible(false);
        if(tp != null)
            tp.setVisible(false);
        if(tkds!= null)
            tkds.setVisible(false);
        if(pdkd!= null)
            pdkd.setVisible(false);
        if(ptkd!= null)
            ptkd.setVisible(false);
//        qlnv = null;
//        qltk = null;
        qlp = null;
//        qldv = null;
//        qltn = null;
//        qlhp = null;
        qlkh = null;
////        dp = null;
        tp = null;
        tkds = null;
        pdkd = null;
        ptkd = null;
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        tp = new FormThuePhong();
        tp.setVisible(true);
        
//        if(qlnv != null)
//            qlnv.setVisible(false);
//        if(qltk != null)
//            qltk.setVisible(false);
        if(qlp != null)
            qlp.setVisible(false);
//        if(qldv != null)
//            qldv.setVisible(false);
//        if(qltn != null)
//            qltn.setVisible(false);
//        if(qlhp != null)
//            qlhp.setVisible(false);
        if(qlkh != null)
            qlkh.setVisible(false);
        if(dp != null)
            dp.setVisible(false);
//        if(tp != null)
//            tp.setVisible(false);
        if(tkds!= null)
            tkds.setVisible(false);
        if(pdkd!= null)
            pdkd.setVisible(false);
        if(ptkd!= null)
            ptkd.setVisible(false);
//        qlnv = null;
//        qltk = null;
        qlp = null;
//        qldv = null;
//        qltn = null;
//        qlhp = null;
        qlkh = null;
        dp = null;
//        tp = null;
        tkds = null;
        pdkd = null;
        ptkd = null;
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        pdkd = new ThongKePhieuDat();
        pdkd.setVisible(true);
        
        if(qlnv != null)
            qlnv.setVisible(false);
        if(qltk != null)
            qltk.setVisible(false);
        if(qlp != null)
            qlp.setVisible(false);
        if(qldv != null)
            qldv.setVisible(false);
        if(qltn != null)
            qltn.setVisible(false);
        if(qlhp != null)
            qlhp.setVisible(false);
        if(qlkh != null)
            qlkh.setVisible(false);
        if(dp != null)
            dp.setVisible(false);
        if(tp != null)
            tp.setVisible(false);
        if(tkds!= null)
            tkds.setVisible(false);
//        if(pdkd!= null)
//            pdkd.setVisible(false);
        if(ptkd!= null)
            ptkd.setVisible(false);
        qlnv = null;
        qltk = null;
        qlp = null;
        qldv = null;
        qltn = null;
        qlhp = null;
        qlkh = null;
        dp = null;
        tp = null;
        tkds = null;
//        pdkd = null;
        ptkd = null;
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        ThongKeDoanhSo ds = new ThongKeDoanhSo();
        ds.setVisible(true);
        
        if(qlnv != null)
            qlnv.setVisible(false);
        if(qltk != null)
            qltk.setVisible(false);
        if(qlp != null)
            qlp.setVisible(false);
        if(qldv != null)
            qldv.setVisible(false);
        if(qltn != null)
            qltn.setVisible(false);
        if(qlhp != null)
            qlhp.setVisible(false);
        if(qlkh != null)
            qlkh.setVisible(false);
        if(dp != null)
            dp.setVisible(false);
        if(tp != null)
            tp.setVisible(false);
//        if(tkds!= null)
//            tkds.setVisible(false);
        if(pdkd!= null)
            pdkd.setVisible(false);
        if(ptkd!= null)
            ptkd.setVisible(false);
        qlnv = null;
        qltk = null;
        qlp = null;
        qldv = null;
        qltn = null;
        qlhp = null;
        qlkh = null;
        dp = null;
        tp = null;
//        tkds = null;
        pdkd = null;
        ptkd = null;
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuThongKe1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuThongKe1MouseClicked

    }//GEN-LAST:event_jMenuThongKe1MouseClicked

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        DangNhapForm dn = new DangNhapForm();
        dn.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        ptkd = new ThongKePhieuThueKhaDung();
        ptkd.setVisible(true);
        
        if(qlnv != null)
            qlnv.setVisible(false);
        if(qltk != null)
            qltk.setVisible(false);
        if(qlp != null)
            qlp.setVisible(false);
        if(qldv != null)
            qldv.setVisible(false);
        if(qltn != null)
            qltn.setVisible(false);
        if(qlhp != null)
            qlhp.setVisible(false);
        if(qlkh != null)
            qlkh.setVisible(false);
        if(dp != null)
            dp.setVisible(false);
        if(tp != null)
            tp.setVisible(false);
        if(tkds!= null)
            tkds.setVisible(false);
        if(pdkd!= null)
            pdkd.setVisible(false);
//        if(ptkd!= null)
//            ptkd.setVisible(false);
        qlnv = null;
        qltk = null;
        qlp = null;
        qldv = null;
        qltn = null;
        qlhp = null;
        qlkh = null;
        dp = null;
        tp = null;
        tkds = null;
        pdkd = null;
//        ptkd = null;
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new QuanLyFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBarQuanLy;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenu jMenuQlyHeThong;
    private javax.swing.JMenu jMenuQlyQuanLy;
    private javax.swing.JMenu jMenuThongKe1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
