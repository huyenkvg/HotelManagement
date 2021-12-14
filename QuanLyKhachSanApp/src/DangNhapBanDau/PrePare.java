package DangNhapBanDau;

import Data.DBConection;
import Data.RoomModify;
import QuanLy.FormDatPhong;
import QuanLy.QuanLyNhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import qlks.CTDL.CTThue;
import qlks.CTDL.KhachHang;
import qlks.CTDL.Phong;

public class PrePare {

    public static boolean KiemTraCT_ThuePhongNay(int maPhong) {
        Connection conn = DBConection.layKetNoi();
        ResultSet rs, hd;
        Statement st;
        CTThue ph;
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from CT_THUE where GETDATE() > NGAYDI AND MAPHONG = '" + maPhong + "';");

            while (rs.next()) {
                ph = new CTThue(rs.getInt("MAPT"), rs.getInt("MAPHONG"), rs.getDate("ngayDen"), rs.getDate("ngayDi"));
                hd = st.executeQuery("select MAHD from HOADON where MAPT = '" + ph.getMaPT() + "';");
                if (!hd.next()) // KHONG TIM THAY HOA DON CUA PHIEU THUE NAY 
                {
                    st.executeUpdate("Update  CT_THUE set NGAYDI = DATEADD(hh, 1, GETDATE()) where MAPT ='" + ph.getMaPT() + "' AND MAPHONG = '" + maPhong + "';");
                    return true;
                }
            }
            System.out.println("SQL: Kiem Tra ds CTThue VA Hoa đơn cua phong [" + maPhong + "]xong : khong thay qua han thue");
            return false;
        } catch (SQLException ex) {
            System.out.println("SQL: LKiem Tra ds CTThue VA Hoa đơn cua phong [" + maPhong + "] that bai");
            ex.printStackTrace();
        }
        return false;
    }

    public static void UpDateNgayThueChuaThanhToan() {
        ArrayList<Phong> dsPhong = RoomModify.layDanhSachPhong();
        ArrayList<CTThue> chitiets = null;
        for (Phong p : dsPhong) {
            if (p.getTrangThai().equals("Thuê")) {
                // Phòng này chưa trả tiền
                if (!KiemTraCT_ThuePhongNay(p.getMaPhong())) {
                    System.out.println("ĐÃ kiểm TRA Và UpDate");
                }
            }
        }
    }

    public static void xoaPhieuThueCucManh() {
        ArrayList<Phong> dsPhong = RoomModify.layDanhSachPhong();
        Vector <String> phong = new Vector<>();
        String temp = "";
        for (int i = 0; i < dsPhong.size(); i++) {
            temp = "";
            if (dsPhong.get(i).getTrangThai().equals("Đặt") == true) {
                Connection ketNoi = qlks.QLKS.layKetNoi();
                try {
                    PreparedStatement ps = ketNoi.prepareStatement("SELECT MAPD "
                                                                 + "FROM CT_PHIEUDAT "
                                                                 + "WHERE NGAYDEN < GETDATE() AND MAPHONG = '" + dsPhong.get(i).getMaPhong() + "'");
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        temp = rs.getString("MAPD");
                    }
                    rs.close();
                    ps.close();
                    ketNoi.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PrePare.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (temp.equals("") == false) {
                    ketNoi = qlks.QLKS.layKetNoi();
                    try {
                        PreparedStatement ps = ketNoi.prepareStatement("select MAPHONG "
                                                                    + "from CT_PHIEUDAT "
                                                                    + "where MAPD = '" + temp + "'");
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            phong.add(rs.getString("MAPHONG"));
                        }
                        rs.close();
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(PrePare.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        PreparedStatement ps = ketNoi.prepareStatement("delete from CT_PHIEUDAT where MAPD = ?");
                        ps.setString(1, temp);
                        ps.executeUpdate();
                    } catch (SQLException ex) {
                        Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        PreparedStatement ps = ketNoi.prepareStatement("delete from PHIEUDAT where MAPD = ?");
                        ps.setString(1, temp);
                        ps.executeUpdate();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    for(int j = 0; j < phong.size(); j++){
                        ketNoi = qlks.QLKS.layKetNoi();
                        try {
                            PreparedStatement ps = ketNoi.prepareStatement("UPDATE PHONG SET TRANGTHAI = ? WHERE MAPHONG = ?");
                            ps.setString(1, "Trống");
                            ps.setString(2, phong.get(j) + "");
                            ps.executeUpdate();
                            ps.close();
                            ketNoi.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                dsPhong = RoomModify.layDanhSachPhong();
            }
        }
    }
    public static void main(String[] args) {
       DangNhapForm.nhanVienDangDangNhap.getHo();
    }
}
