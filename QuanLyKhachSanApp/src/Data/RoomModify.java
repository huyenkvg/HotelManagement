package Data;

import Data.DBConection;
import Data.DBConection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import qlks.CTDL.CTPhieuDat;
import qlks.CTDL.CTThue;
import qlks.CTDL.KhachHang;
import qlks.CTDL.PhieuDat;
import qlks.CTDL.PhieuThue;
import qlks.CTDL.Phong;
import qlks.CTDL.ThongTinThuePhong;

public class RoomModify {

    public static Phong TimPhong(int maPhong, ArrayList<Phong> dsphong) {
        for (Phong p : dsphong) {
            if (p.getMaPhong() == maPhong) {
                return p;
            }
        }
        return null;
    }
    public static ArrayList<Phong> layDanhSachPhong() {
        Connection conn = DBConection.layKetNoi();
        ResultSet rs;
        Statement st;
        ArrayList<Phong> dsPhong = new ArrayList<>();
        Phong phong;
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from PHONG");
            while (rs.next()) {

                phong = new Phong();
                phong.setMaPhong(Integer.parseInt(rs.getString("MAPHONG")));
                phong.setTrangThai((rs.getString("TRANGTHAI")));
                phong.setHangPhong((rs.getString("HANGPHONG")));
                dsPhong.add(phong);

            }
            System.out.println("LayDSPhong : load ds Phong xong");
        } catch (SQLException ex) {
            System.out.println("loi load danh sach tra cuu");
        }
        return dsPhong;
    }

    public static int demDanhSachCTPhieuThue(int mapt) {
        
        Connection conn = DBConection.layKetNoi();
        ResultSet rs;
        Statement st;
        int count = 0;
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from CT_THUE Where MAPT ='"+mapt+"'");
            while (rs.next()) {
                
                count++;
            }
            System.out.println("Dem xong CTTHue xong");
        } catch (SQLException ex) {
            System.out.println("Dem khong dươc CTTTHUEEE");
        }
        return count;
    }
    public static ArrayList<PhieuThue> layDanhSachPhieuThue() {
        Connection conn = DBConection.layKetNoi();
        ResultSet rs;
        Statement st;
        ArrayList<PhieuThue> phieus = new ArrayList<>();
        PhieuThue ph;
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from PHIEUTHUE");
            while (rs.next()) {

                ph = new PhieuThue(rs.getString("MANV"), rs.getString("MAKH"), rs.getInt("MAPD"), rs.getInt("MAPT"));
                phieus.add(ph);

            }
            System.out.println("LayDS PHieuThue : load ds Phong xong");
        } catch (SQLException ex) {
            System.out.println("LayDS PHieuThue :loi load danh sach tra cuu Phieu Thue");
        }
//        for(Phong x: dsPhong  )
//        {
//            System.out.println(x.getMaPhong());
//        }
        return phieus;
    }

    public static void UpdatePhong(Phong phong) {
        Connection ketNoi = DBConection.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("UPDATE PHONG SET TRANGTHAI = ?, HANGPHONG =? WHERE MAPHONG =?");
            ps.setString(1, phong.getTrangThai());
            ps.setString(2, phong.getHangPhong());
            ps.setInt(3, phong.getMaPhong());
//            ps.setDate(4, (Date)d);
//            ps.setString(5, email);
//            ps.setString(6, sDT);
//            ps.setString(7, maBP);
//            ps.setString(8, maNV);
            ps.executeUpdate();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            System.out.println("Khong Update duoc Phong");
        }
    }

    public static ArrayList<PhieuDat> layDanhSachPhieuDat() {
        Connection conn = DBConection.layKetNoi();
        ResultSet rs;
        Statement st;
        ArrayList<PhieuDat> phieus = new ArrayList<>();
        PhieuDat ph;
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from PHIEUDAT");
            while (rs.next()) {

                ph = new PhieuDat(rs.getString("MANV"), rs.getString("MAKH"), rs.getInt("MAPD"));
                phieus.add(ph);

            }
            System.out.println("LayDS PHieu DAt : load ds Phong xong");
        } catch (SQLException ex) {
            System.out.println("LayDS PHieu DAt :loi load danh sach tra cuu Phieu Thue");
        }
        return phieus;
    }

    public static CTThue TimCTThue(String maPhong) {

        Connection conn = DBConection.layKetNoi();
        ResultSet rs;
        Statement st;
        CTThue ctt = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery("Select * from CT_THUE where (MAPHONG = '" + maPhong + "' AND( NGAYDEN <= CURRENT_TIMESTAMP) AND (NGAYDI >=CURRENT_TIMESTAMP));");
            ctt = new CTThue(rs.getInt("MAPT"), rs.getInt("MAPHONG"), rs.getDate("NGAYDEN"), rs.getDate("NGAYDI"));

        } catch (SQLException ex) {
            System.out.println("loi Tim Kiem CTTHUE Trong DAtabase");
        }
        return ctt;
    }

    public static ThongTinThuePhong TraCuuThongTinDatPhong(int maPhong) {
        System.out.println("---------------------- MÃ PHÒNG:" + maPhong);
        Connection conn = DBConection.layKetNoi();
        ResultSet rs;
        Statement st;
        ThongTinThuePhong thongtin = null;

        try {

            st = conn.createStatement();
            rs = st.executeQuery("SELECT PHIEUDAT.MAPD, MAPHONG, NGAYDEN, NGAYDI, MANV, MAKH INTO #TempTable FROM CT_PHIEUDAT, PHIEUDAT WHERE (MAPHONG = '"+maPhong+"' AND( CT_PHIEUDAT.NGAYDEN >= CURRENT_TIMESTAMP)  AND CT_PHIEUDAT.MAPD = PHIEUDAT.MAPD)SELECT MAPD, MAPHONG, NGAYDEN, NGAYDI, MANV, MAPD, CMND, HO, TEN, DIACHI, SDT, MASOTHUE FROM #TempTable, KHACHHANG  	Where #TempTable.MAKH = KHACHHANG.CMND DROP TABLE #TempTable");
            
            if(!rs.next())
                return null;
            
//            CTThue ctt = new CTThue(rs.getInt("MAPT"), rs.getInt("MAPHONG"), dateDen, dateDi);
            CTPhieuDat ctt = new CTPhieuDat(rs.getInt("MAPD"), rs.getInt("MAPHONG"), 0,rs.getDate("NGAYDEN"), rs.getDate("NGAYDI"));
            PhieuDat pthue = new PhieuDat(rs.getString("MANV"), rs.getString("CMND"), rs.getInt("MAPD"));
            KhachHang khach = new KhachHang(rs.getString("CMND"), rs.getString("HO"), rs.getString("TEN"), rs.getString("DIACHI"), rs.getString("SDT"), rs.getString("MASOTHUE"));
            thongtin = new ThongTinThuePhong(ctt, pthue, khach);
            System.out.println("THÔNG TIN DAT:" + maPhong + " :" + ctt.getNgayDen() + " " + ctt.getNgayDi() + " " );
        } catch (SQLException e) {
            System.out.println("loi Tim Kiem CT DAT + bla bla Trong DAtabase");
            e.printStackTrace();
        }
        return thongtin;
    }
    public static ThongTinThuePhong TraCuuThongTinThuePhong(int maPhong) {
        System.out.println("MÃ PHÒNG:" + maPhong);
        Connection conn = DBConection.layKetNoi();
        ResultSet rs;
        Statement st;
        ThongTinThuePhong thongtin = null;

        try {

            st = conn.createStatement();
            rs = st.executeQuery("SELECT PHIEUTHUE.MAPT, MAPHONG, NGAYDEN, NGAYDI, MANV, MAKH, MAPD INTO #TempTable FROM CT_THUE, PHIEUTHUE WHERE (MAPHONG = '"+maPhong+"' AND( CT_THUE.NGAYDEN <= CURRENT_TIMESTAMP) AND (CT_THUE.NGAYDI >=CURRENT_TIMESTAMP) AND CT_THUE.MAPT = PHIEUTHUE.MAPT)SELECT MAPT, MAPHONG, NGAYDEN, NGAYDI, MANV, MAPD, CMND, HO, TEN, DIACHI, SDT, MASOTHUE FROM #TempTable, KHACHHANG  	Where #TempTable.MAKH = KHACHHANG.CMND DROP TABLE #TempTable");
            
            if(!rs.next()){
                
                System.out.println("KHONG THAY: Tim Kiem CTTHUE Trong DAtabase");
                return null;
            }
            
//            CTThue ctt = new CTThue(rs.getInt("MAPT"), rs.getInt("MAPHONG"), dateDen, dateDi);
            CTThue ctt = new CTThue(rs.getInt("MAPT"), rs.getInt("MAPHONG"), rs.getDate("NGAYDEN"), rs.getDate("NGAYDI"));
            PhieuThue pthue = new PhieuThue(rs.getString("MANV"), rs.getString("CMND"), rs.getInt("MAPT"), rs.getInt("MAPT"));
            KhachHang khach = new KhachHang(rs.getString("CMND"), rs.getString("HO"), rs.getString("TEN"), rs.getString("DIACHI"), rs.getString("SDT"), rs.getString("MASOTHUE"));
            thongtin = new ThongTinThuePhong(ctt, pthue, khach);
            System.out.println("THÔNG TIN THUÊ:" + maPhong + " :" + ctt.getNgayDen() + " " + ctt.getNgayDi() + " " );
        } catch (SQLException e) {
            System.out.println("loi Tim Kiem CTTHUE Trong DAtabase");
            e.printStackTrace();
        }
        return thongtin;
    }

    public static void main(String[] args) {
        TraCuuThongTinThuePhong(101);
    }
}
