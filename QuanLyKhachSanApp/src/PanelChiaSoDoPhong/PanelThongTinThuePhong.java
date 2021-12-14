package PanelChiaSoDoPhong;

import DangNhapBanDau.NhanVienFrame;
import Data.DBConection;
import static Data.DBConection.layKetNoi;
import Data.RoomModify;
import Huong.TachMaPT;
import Huong.ThongTinMaPT;
import Huong.XuatHoaDonHuong;
import QuanLy.QuanLyDichVu;
import QuanLy.QuanLyPhong;
import static QuanLy.QuanLyPhong.sodo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import qlks.CTDL.CTDichVu;
import qlks.CTDL.DichVu;
import qlks.CTDL.GiaDichVu;
import qlks.CTDL.KhachHang;
import qlks.CTDL.ThongTinThuePhong;
import static qlks.QLKS.layKetNoi;

public class PanelThongTinThuePhong extends javax.swing.JPanel {
    public static int giaPhong = 0;

// Bảng sử dụng dịch vụ    
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs;
    DichVu dv;
    GiaDichVu g;
    CTDichVu ctdv;
    public static int MaPhongDangChon;// Lấy mã phòng của phòng đang chọn
    public static int MaPTCuaPhongDangChon; // Lấy mã phiếu thuê của phòng đang chọn

    ArrayList<DichVu> dsdvsd;
    ArrayList<GiaDichVu> dsgdvsd;
    ArrayList<CTDichVu> dsnsddv;

    public PanelThongTinThuePhong() {
        initComponents();
        lbl_TenKhachHang.setText("---[Trống]---");
        lbl_SdtKhachHang.setText("---[Trống]---");
        lbl_NgayThuePhong.setText("---[Trống]---");
        lbl_NgayTraPhong.setText("---[Trống]---");
        lbl_NgayTraPhong.setEditable(false);
        btn_ThemDV.setVisible(true);
        btn_XuatHoaDon.setEnabled(true);
        jComboBox_DichVu.setVisible(true);
        jButtonSDDV.setEnabled(true);
        
        btn_XuatHoaDon.setEnabled(false);
        hienThi();
        layDSDV();
    }

    public void ResetThongTinThuePhong() {
        lbl_TenKhachHang.setText("---[Trống]---");
        lbl_SdtKhachHang.setText("---[Trống]---");
        lbl_NgayThuePhong.setText("---[Trống]---");
        lbl_NgayTraPhong.setText("---[Trống]---");
        lbl_NgayTraPhong.setEditable(false);
        btn_ThemDV.setVisible(true);
        btn_XuatHoaDon.setEnabled(true);
        jComboBox_DichVu.setVisible(true);
        jButtonSDDV.setEnabled(true);

//        jLabel_TienPhong.setText("000,000,000 đ");
//        jLabel_TienDichVu.setText("000,000,000 đ");
//        jLabel_TongThanhToan.setText("000,000,000 đ");
        btn_XuatHoaDon.setEnabled(false);
//        jPanel_HoaDonThuePhong.setEnabled(false);
    }

    public int daysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public void UpdateThongTinThuePhong(ThongTinThuePhong thongtin) {
        if (thongtin == null) {
            ResetThongTinThuePhong();
            return;
        }
        if (thongtin.khachThue != null) {
            lbl_TenKhachHang.setText(thongtin.khachThue.getHo() + " " + thongtin.khachThue.getTen());
            lbl_SdtKhachHang.setText(thongtin.khachThue.getSDT());

        }
        if (thongtin.phieuThue == null) {

            if (thongtin.phieuDat != null) {
                lbl_NgayThuePhong.setText(thongtin.chiTietDat.getNgayDen().toString());
                lbl_NgayTraPhong.setText(thongtin.chiTietDat.getNgayDi().toString());

            }
            btn_ThemDV.setVisible(false);
            btn_XuatHoaDon.setEnabled(false);
            jComboBox_DichVu.setVisible(false);
            jButtonSDDV.setEnabled(false);

        } else {

            lbl_NgayThuePhong.setText(thongtin.chiTietThue.getNgayDen().toString());
            lbl_NgayTraPhong.setText(thongtin.chiTietThue.getNgayDi().toString());
        }

//        lbl_NgayTraPhong.setText("---[Trống]---");
//        jLabel_TienPhong.setText(tienPhong + " đ");
//        jLabel_TienDichVu.setText(" đ");
//        jLabel_TongThanhToan.setText(" đ");
        btn_XuatHoaDon.setEnabled(false);
//        jPanel_HoaDonThuePhong.setEnabled(false);
    }

    public void layDSDV() {
      //  jComboBox_DichVu.removeAllItems();
        con = DBConection.layKetNoi();
        String SQL = "SELECT TENDICHVU FROM DICHVU";
        try {
            pst = con.prepareStatement(SQL);
            rs = pst.executeQuery();
            while (rs.next()) {
                jComboBox_DichVu.addItem(rs.getString("TENDICHVU"));
            }
        } catch (SQLException ex) {
        }
    }

    public ArrayList<DichVu> layDSDichVuSD() {
        ArrayList<DichVu> dsdvsd = new ArrayList<>();
        con = DBConection.layKetNoi();
        final String SQL = "SELECT DICHVU.MADV,DICHVU.TENDICHVU "
                + "FROM  DICHVU "
                + "INNER JOIN CT_DICHVU ON DICHVU.MADV=CT_DICHVU.MADV "
                + "WHERE CT_DICHVU.MAPHONG = "+ MaPhongDangChon +" AND CT_DICHVU.MAPT = "+ MaPTCuaPhongDangChon +"";
        try {
            pst = con.prepareStatement(SQL);
            rs = pst.executeQuery();
            while (rs.next()) {
                dv = new DichVu(rs.getString("MADV"), rs.getString("TENDICHVU"));
                dsdvsd.add(dv);
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException ex) {
        }
        return dsdvsd;
    }

    public ArrayList<CTDichVu> layDSNSD() {
         dsnsddv = new ArrayList<>();
        con = DBConection.layKetNoi();

        final String SQL = "SELECT DICHVU.TENDICHVU,CT_DICHVU.NGAYSUDUNG,GIADICHVU.GIA "
                + "FROM  DICHVU "
                + "INNER JOIN CT_DICHVU ON DICHVU.MADV=CT_DICHVU.MADV "
                + "INNER JOIN GIADICHVU ON CT_DICHVU.MADV=GIADICHVU.MADV "
                + "WHERE CT_DICHVU.MAPHONG = " + MaPhongDangChon + " AND CT_DICHVU.MAPT = " + MaPTCuaPhongDangChon + "";
        try {
            pst = con.prepareStatement(SQL);
            rs = pst.executeQuery();
            while (rs.next()) {
                ctdv = new CTDichVu(rs.getDate("NGAYSUDUNG"));
                dsnsddv.add(ctdv);
            }
            rs.close();
            pst.close();
            con.close();
        } catch (java.sql.SQLException ex) {
        }
        return dsnsddv;
    }

//    public ArrayList<GiaDichVu> layDSGDVSD() {
//        ArrayList<GiaDichVu> dsgsddv = new ArrayList<>();
//        con = DBConection.layKetNoi();
//
//        final String SQL = "SELECT DICHVU.TENDICHVU,CT_DICHVU.NGAYSUDUNG,GIADICHVU.GIA "
//                + "FROM  DICHVU "
//                + "INNER JOIN CT_DICHVU ON DICHVU.MADV=CT_DICHVU.MADV "
//                + "INNER JOIN GIADICHVU ON CT_DICHVU.MADV=GIADICHVU.MADV "
//                + "WHERE CT_DICHVU.MAPHONG = " + MaPhongDangChon + " AND CT_DICHVU.MAPT = " + MaPTCuaPhongDangChon + "";
//        try {
//            pst = con.prepareStatement(SQL);
//            rs = pst.executeQuery();
//            while (rs.next()) {
//                g = new GiaDichVu(rs.getInt("GIA"));
//                dsgsddv.add(g);
//            }
//            rs.close();
//            pst.close();
//            con.close();
//        } catch (java.sql.SQLException ex) {
//        }
//        return dsgsddv;
//    }

    public String layDonGia(String maDV) {
        Connection kn = qlks.QLKS.layKetNoi();
        try {
            CallableStatement c = kn.prepareCall("{call loadDG (?)}");
            c.setString(1, maDV);
            ResultSet rs = c.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDichVu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public void hienThi() {
        jLabel_TenDanhSach.setText("Danh sách dịch vụ phòng [" + MaPhongDangChon + "] đã sử dụng");
        String colTieuDe1[] = new String[]{"Tên dịch vụ", "Giá", "Ngày sử dụng"};
        dsdvsd = layDSDichVuSD(); //ten dv
        //dsgdvsd = layDSGDVSD(); // giá
        dsnsddv = layDSNSD(); // ngày sd dv

        DefaultTableModel model = new DefaultTableModel(colTieuDe1, 0);
        Object[] row;
        for (int i = 0; i < dsdvsd.size() && i < dsnsddv.size(); i++) {
            row = new Object[3];
            row[0] = dsdvsd.get(i).getTenDV();
            row[1] = layDonGia(dsdvsd.get(i).getMaDV());
            row[2] = dsnsddv.get(i).getNgaySuDung();
            model.addRow(row);
        }
        jTable1.setModel(model);

    }

    public void suDungDV() {
        
        con = DBConection.layKetNoi();
        String DV = (String) jComboBox_DichVu.getSelectedItem();

        String SQL = "DECLARE @madv nvarchar(10) "
                + "set @madv=(SELECT MADV FROM DICHVU WHERE TENDICHVU=N'" + DV + "') "
                + "INSERT INTO CT_DICHVU(MAPT,MAPHONG,MADV,NGAYSUDUNG) VALUES (" + MaPTCuaPhongDangChon + ", " + MaPhongDangChon + ",@madv,CURRENT_TIMESTAMP)";
        try {

            pst = con.prepareStatement(SQL);
            pst.execute();
            con.close();
            pst.close();

        } catch (java.sql.SQLException ex) {

        }
        hienThi();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox_DichVu = new javax.swing.JComboBox<>();
        btn_ThemDV = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButtonSDDV = new javax.swing.JButton();
        jLabel_TenDanhSach = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        lbl_TenKhachHang = new javax.swing.JLabel();
        lbl_SdtKhachHang = new javax.swing.JLabel();
        lbl_NgayThuePhong = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lbl_NgayTraPhong = new javax.swing.JTextField();
        btn_XuatHoaDon = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 204, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin thuê phòng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 51))); // NOI18N

        jComboBox_DichVu.setBackground(new java.awt.Color(0, 117, 255));
        jComboBox_DichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_DichVuActionPerformed(evt);
            }
        });

        btn_ThemDV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_ThemDV.setText("...");
        btn_ThemDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemDVActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Tên khách hàng:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Sđt:");

        jButtonSDDV.setBackground(new java.awt.Color(51, 102, 255));
        jButtonSDDV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonSDDV.setText("Sử Dụng Dịch Vụ");
        jButtonSDDV.setEnabled(false);
        jButtonSDDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSDDVActionPerformed(evt);
            }
        });

        jLabel_TenDanhSach.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel_TenDanhSach.setText(" Danh sách dịch vụ đã sử dụng");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Ngày nhận:");

        jTable1.setBackground(new java.awt.Color(85, 163, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Ngày trả:");

        lbl_TenKhachHang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_TenKhachHang.setForeground(new java.awt.Color(255, 0, 45));
        lbl_TenKhachHang.setText("XXX");

        lbl_SdtKhachHang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_SdtKhachHang.setForeground(new java.awt.Color(255, 0, 45));
        lbl_SdtKhachHang.setText("XXX");

        lbl_NgayThuePhong.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_NgayThuePhong.setText("XXX");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("Tên DV:");

        lbl_NgayTraPhong.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_NgayTraPhong.setText("XXX");

        btn_XuatHoaDon.setBackground(new java.awt.Color(51, 0, 153));
        btn_XuatHoaDon.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_XuatHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btn_XuatHoaDon.setText("THANH TOÁN - TRẢ PHÒNG");
        btn_XuatHoaDon.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_XuatHoaDon.setEnabled(false);
        btn_XuatHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XuatHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel_TenDanhSach)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_XuatHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox_DichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_ThemDV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jButtonSDDV, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addGap(18, 18, 18)
                            .addComponent(lbl_NgayTraPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(32, 32, 32))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbl_NgayThuePhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_TenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_SdtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(31, 31, 31)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_NgayTraPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(55, 55, 55)
                .addComponent(jLabel_TenDanhSach)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 218, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSDDV)
                    .addComponent(btn_ThemDV)
                    .addComponent(jComboBox_DichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(52, 52, 52)
                .addComponent(btn_XuatHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(52, 52, 52)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbl_TenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(lbl_SdtKhachHang)))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(lbl_NgayThuePhong))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(224, 224, 224)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ThemDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemDVActionPerformed
        // TODO add your handling code here:
        QuanLyDichVu qldv = new QuanLyDichVu();
        qldv.setVisible(true);
    }//GEN-LAST:event_btn_ThemDVActionPerformed

    private void jButtonSDDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSDDVActionPerformed
        // TODO add your handling code here:
        suDungDV();
        hienThi();
    }//GEN-LAST:event_jButtonSDDVActionPerformed

    private void jComboBox_DichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_DichVuActionPerformed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jComboBox_DichVuActionPerformed

    private void btn_XuatHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XuatHoaDonActionPerformed
        if (RoomModify.demDanhSachCTPhieuThue(MaPTCuaPhongDangChon) > 1) {
            // TACHS MA PTHUE MOI
            ThongTinMaPT thongTin = new ThongTinMaPT(MaPTCuaPhongDangChon, MaPhongDangChon);
            TachMaPT.themPT(thongTin);
            TachMaPT.updateCT(thongTin);
            MaPTCuaPhongDangChon = thongTin.getMaPTCu(); // Tên là MPT cũ nhung thật ra đã được cập nhật thành mã pt mới rồi nha
            // THANH TOAN
            XuatHoaDonHuong xuatFrame = new XuatHoaDonHuong(MaPTCuaPhongDangChon);
            xuatFrame.setVisible(true);
            
        } else if (RoomModify.demDanhSachCTPhieuThue(MaPTCuaPhongDangChon) == 1) {
            //ThongTinMaPT thongTin = new ThongTinMaPT(MaPTCuaPhongDangChon, MaPhongDangChon);
            // THANH TOAN
            XuatHoaDonHuong xuatFrame = new XuatHoaDonHuong(MaPTCuaPhongDangChon);
            xuatFrame.setVisible(true);
            
        }
        if(DangNhapBanDau.DangNhapForm.nv != null && DangNhapBanDau.DangNhapForm.nv.qlp != null) DangNhapBanDau.DangNhapForm.nv.qlp.setVisible(false);
        else if (DangNhapBanDau.DangNhapForm.ql != null  && DangNhapBanDau.DangNhapForm.ql.qlp!=null) DangNhapBanDau.DangNhapForm.ql.qlp.setVisible(false);  
        sodo = new SoDoPhong(RoomModify.layDanhSachPhong());        
        sodo.repaint();
        
    }//GEN-LAST:event_btn_XuatHoaDonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ThemDV;
    private javax.swing.JButton btn_XuatHoaDon;
    private javax.swing.JButton jButtonSDDV;
    private javax.swing.JComboBox<String> jComboBox_DichVu;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel_TenDanhSach;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_NgayThuePhong;
    private javax.swing.JTextField lbl_NgayTraPhong;
    private javax.swing.JLabel lbl_SdtKhachHang;
    private javax.swing.JLabel lbl_TenKhachHang;
    // End of variables declaration//GEN-END:variables

    public void ChoThanhToan(boolean t) {
        btn_XuatHoaDon.setEnabled(t);
        btn_ThemDV.setEnabled(t);
        jButtonSDDV.setEnabled(t);
    }

}
