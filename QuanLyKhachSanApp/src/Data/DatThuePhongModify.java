/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import qlks.CTDL.CTPhieuDat;
import qlks.CTDL.CTThue;
import qlks.CTDL.DanhSachPhong;
import qlks.CTDL.KhachHang;
import qlks.CTDL.PhieuDat;
import qlks.CTDL.PhieuThue;
import qlks.CTDL.ThongTinThuePhong;

/**
 *
 * @author HUYENKUTE
 */
public class DatThuePhongModify {

    public static KhachHang TimKhach(String maKH) {
        Connection conn = DBConection.layKetNoi();
        ResultSet rs;
        Statement st;
        KhachHang khach = null;
        try {
            st = conn.createStatement();
            System.out.println("select * from Khachhang where cmnd ='" + maKH + "';");
            rs = st.executeQuery("select * from Khachhang where cmnd ='" + maKH + "';");
            if (!rs.next()) {
                return null;
            }
            khach = new KhachHang(rs.getString("CMND"), rs.getString("HO"), rs.getString("TEN"), rs.getString("DIACHI"), rs.getString("SDT"), rs.getString("MASOTHUE"));

        } catch (SQLException ex) {
            System.out.println("loi Tim Kiem Khach Hang Trong DAtabase");
        }
        return khach;
    }

    public static void ThemKhachVaoDB(KhachHang khach) {
        Connection conn = DBConection.layKetNoi();
        Statement st;
        try {
            System.out.println("INSERT INTO KHACHHANG (CMND, HO, TEN, DIACHI, SDT, MASOTHUE) VALUES ('" + khach.getCMND() + "',N'" + khach.getHo() + "',N'" + khach.getTen() + "',N'" + khach.getDiaChi() + "','" + khach.getSDT() + "','" + khach.getMaSoThue() + "');");
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("INSERT INTO KHACHHANG (CMND, HO, TEN, DIACHI, SDT, MASOTHUE) VALUES ('" + khach.getCMND() + "',N'" + khach.getHo() + "',N'" + khach.getTen() + "',N'" + khach.getDiaChi() + "','" + khach.getSDT() + "','" + khach.getMaSoThue() + "');");

        } catch (SQLException ex) {
            System.out.println("!!! --- Khong Update duoc: ADD KHACH HANG Vao SQL");
        }
    }

    public static boolean ThemPhieuDatVaoDB(PhieuDat phieu) {
        Connection ketNoi = DBConection.layKetNoi();
        try {
            System.out.println("INSERT INTO PHIEUDAT (MANV, MAKH) VALUES('" + phieu.getMaNV() + "','" + phieu.getMaKH() + "');");
            Statement stmt = ketNoi.createStatement();
            int row = (stmt.executeUpdate("INSERT INTO PHIEUDAT (MANV, MAKH) VALUES('" + phieu.getMaNV() + "','" + phieu.getMaKH() + "');", Statement.RETURN_GENERATED_KEYS));

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                row = generatedKeys.getInt(1);
            }
            phieu.setMaPD(row);
            System.out.println("MA PD VUA UPDATE: " + row + "  :  " + phieu.getMaPD());
            return true;

        } catch (SQLException ex) {
            System.out.println("Khong Update duoc: ADD Phieu Dat Vao SQL");
        }
        return false;
    }

    public static boolean ThemPhieuThueVaoDB(PhieuThue phieu) {
        Connection ketNoi = DBConection.layKetNoi();
        try {
            String obj = "null";
            if (phieu.getMaPD() != -1) {
                obj = "'" + phieu.getMaPD() + "'";
            }
            System.out.println("INSERT INTO PHIEUTHUE (MANV, MAKH, MAPD) VALUES('" + phieu.getMaNV() + "','" + phieu.getMaKH() + "'," + obj + ");");
            Statement stmt = ketNoi.createStatement();
            int row = (stmt.executeUpdate("INSERT INTO PHIEUTHUE (MANV, MAKH, MAPD) VALUES('" + phieu.getMaNV() + "','" + phieu.getMaKH() + "'," + obj + ");", Statement.RETURN_GENERATED_KEYS));

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                row = generatedKeys.getInt(1);
            }
            phieu.setMaPT(row);
            System.out.println("MA PT VUA UPDATE: " + row + "  :  " + phieu.getMaPT());
            return true;

        } catch (SQLException ex) {
            System.out.println("Khong Update duoc: ADD Phieu Thue Vao SQL");
        }
        return false;
    }

    public static boolean ThemCTThueVaoDB(CTThue ctthue) {
        Connection ketNoi = DBConection.layKetNoi();
        System.out.println("INSERT INTO CT_THUE VALUES ("+ctthue.getMaPT()+","+ctthue.getMaPhong()+",getDate(), DATEADD(hh, 13, "+ctthue.getNgayDi()+")");
        try {
            PreparedStatement ps = ketNoi.prepareStatement("INSERT INTO CT_THUE VALUES (?,?,getDate(),dateadd(hh, 13, '" + ctthue.getNgayDi()+"'))");
            ps.setInt(1, ctthue.getMaPT());
            ps.setInt(2, ctthue.getMaPhong());
            //ps.setDate(3, ctthue.getNgayDen());
            //ps.setDate(4, ctthue.getNgayDi());
//            ps.setDate(5, (Date) d);
//            ps.setString(6, email);
//            ps.setString(7, sDT);
//            ps.setString(8, maBP);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatThuePhongModify.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean ThemCTDatVaoDB(CTPhieuDat ct) {
        Connection ketNoi = DBConection.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("INSERT INTO CT_PHIEUDAT VALUES (?,?,?,?)");
            ps.setInt(1, ct.getMaPD());
            ps.setInt(2, ct.getMaPhong());
            ps.setDate(3, ct.getNgayDen());
            ps.setDate(4, ct.getNgayDi());
//            ps.setDate(5, (Date) d);
//            ps.setString(6, email);
//            ps.setString(7, sDT);
//            ps.setString(8, maBP);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatThuePhongModify.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void HuyBoPhietThue(int maPt) {
        Connection conn = DBConection.layKetNoi();
        ResultSet rs;
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate("DELETE FROM  PHIEUTHUE WHERE MAPT = '"+maPt+"' ;");
            System.out.println("HUY PHIEU Thue thanh cong :D hehe");
        } catch (SQLException ex) {
            System.out.println("HUY PHIEu ThueTHAT BAI !!!!!!!!");
        }
        return;
    }
    public static void HuyBoPhietDat(int maPD) {
        
        DanhSachPhong ds = TraCuuDSChiTietPhietDat(maPD);
        
        
        Connection conn = DBConection.layKetNoi();
        ResultSet rs;
        Statement st;
        try {
            st = conn.createStatement();
            for(CTPhieuDat pd: ds.dsCTDat){
                st.executeUpdate("Update PHONG set TRANGTHAI = N'Trống' where MAPHONG = "+pd.getMaPhong()+";");
            }
            st.executeUpdate("DELETE FROM  CT_PHIEUDAT WHERE MAPD = '"+maPD+"'; DELETE FROM  PHIEUDAT WHERE MAPD = '"+maPD+"' ;");
            System.out.println("HUY PHIEU DAT thanh cong :D hehe");
            
        } catch (SQLException ex) {
            System.out.println("HUY PHIEUDAT THAT BAI !!!!!!!!");
        }
        return;
    }
    public static DanhSachPhong TraCuuDSChiTietPhietDat(int maPD) {
        Connection conn = DBConection.layKetNoi();
        ResultSet rs;
        Statement st;
        ArrayList<PhieuDat> phieus = new ArrayList<>();
        DanhSachPhong ds = new DanhSachPhong();
        PhieuDat ph = null;
        CTPhieuDat ctd;
        try {
            st = conn.createStatement();
            rs = st.executeQuery("(select * INTO #temp from PHIEUDAT where MAPD = '" + maPD + "') select * from #temp, CT_PhieuDat where (#temp.mapd = CT_PhieuDat.mapd) drop table #temp");
            while (rs.next()) {
                if (ph == null) {
                    ph = new PhieuDat(rs.getString("MANV"), rs.getString("MAKH"), rs.getInt("MAPD"));
                    ph.setMaPD(rs.getInt("MAPD"));
                    ds.setPhieudat(ph);
                    
                }
                
                ctd = new CTPhieuDat(rs.getInt("MAPD"), rs.getInt("MAPHONG"), 0, rs.getDate("NGAYDEN"), rs.getDate("NGAYDI"));
                ds.dsCTDat.add(ctd);
            }
            System.out.println("LayDS PHieu DAt : load ds Phong xong");
        } catch (SQLException ex) {
            System.out.println("LayDS PHieu DAt :loi load danh sach tra cuu Phieu Thue");
        }
        return ds;
    }
    

}
