/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Huong;

import QuanLy.QuanLyKhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import qlks.CTDL.CTThue;
import qlks.CTDL.PhieuThue;
import Data.DBConection;

/**
 *
 * @author thuhu
 */
public class TachMaPT {

    // ===============Táº O Má»˜T PHIáº¾U THUÃŠ Má»šI Dá»°A TRÃŠN PHIáº¾U THUÃŠ CÅ¨===================
    // láº¥y thÃ´ng tin phiáº¿u thuÃª dá»±a trÃªn mÃ£ phÃ²ng láº¥y tá»« CT_ThuÃª
    public static PhieuThue thongtinPTcu(ThongTinMaPT thongTin) {
        Connection ketNoi = DBConection.layKetNoi();
        PhieuThue T = null;
        String sql = "SELECT PHIEUTHUE.*\n"
                + "FROM PHIEUTHUE, CT_THUE\n"
                + "WHERE PHIEUTHUE.MAPT = CT_THUE.MAPT AND CT_THUE.MAPHONG = '" + thongTin.getPhongTT() + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                T = new PhieuThue( rs.getString("MANV"), rs.getString("MAKH"), rs.getInt("MAPD"),rs.getInt("MAPT"));
            };
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return T;
    }

    // táº¡o phiáº¿u thuÃª má»›i dá»±a trÃªn phiáº¿u thuÃª cÅ©
    public static PhieuThue themPT(ThongTinMaPT thongTin) {
        PhieuThue tm = thongtinPTcu(thongTin);
        Connection ketNoi = DBConection.layKetNoi();
        String sql = "INSERT INTO PHIEUTHUE (MANV, MAKH, MAPD) VALUES(?, ?, ?)";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, tm.getMaNV());
            ps.setString(2, tm.getMaKH());
            ps.setString(3, null);
            //  ps.setString(3, ChuyenInt(tm.getMaPD()));
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                tm.setMaPT(generatedKeys.getInt(1));
                thongTin.setMaPTCu(generatedKeys.getInt(1));
                System.out.println("Ma phiÃ©u thuÃª má»›i nÃ¨eee: " + thongTin.getMaPTCu());
            }

        } catch (SQLException ex) {
            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tm;
    }


    // update vÃ o CT_THUE, CT_DICHVU
    public static void updateCT(ThongTinMaPT thongTin) {
        Connection ketNoi = DBConection.layKetNoi();
        // PhieuThue thueUD = themPT(thongTin);
        System.out.println("Ma phiÃ©u thuÃª á»Ÿ updateCT_Thue nÃ¨eee: " + thongTin.getMaPTCu()+", MAPHONG = "+thongTin.getPhongTT());
        String sql = "exec sp_MSforeachtable \"ALTER TABLE ? NOCHECK CONSTRAINT all\"\n"
                + "UPDATE CT_THUE SET MAPT = " + thongTin.getMaPTCu() + " WHERE MAPHONG = " + thongTin.getPhongTT() + "\n"
                + "UPDATE CT_DICHVU SET MAPT = " + thongTin.getMaPTCu() + " WHERE MAPHONG = " + thongTin.getPhongTT() + "\n"
                + "exec sp_MSforeachtable \"ALTER TABLE ? WITH CHECK CHECK CONSTRAINT all\"";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String ChuyenDate(java.sql.Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateFormat = formatter.format(date);
        return dateFormat;
    }

    public String ChuyenInt(int ma) {
        String chuyen = Integer.toString(ma);
        return chuyen;
    }

    public int ChuyenStringSangInt(String ma) {
        int i = Integer.parseInt(ma);
        return i;
    }

    public static void main(String[] args) {
//        ThongTinMaPT thongTin = new ThongTinMaPT(7, 101);
//        themPT(thongTin);
//        updateCT(thongTin);

    }

}
