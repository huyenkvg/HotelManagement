package qlhp.CTDL;

import java.util.Date;

public class GiaHangPhong {

    

    private String hangPhong, maKM;
    private int gia;
    private Date ngay;

    public GiaHangPhong() {
        this.hangPhong = "";
        this.ngay = new Date();
        this.maKM = "";
        this.gia = 0;
    }

    public GiaHangPhong(String hangPhong, Date ngay, String maKM, int gia) {
        this.hangPhong = hangPhong;
        this.ngay = ngay;
        this.maKM = maKM;
        this.gia = gia;
    }

    public GiaHangPhong(String hangPhong, int gia) {
        this.hangPhong = hangPhong;
        this.gia = gia;
        
        
    }

    public GiaHangPhong(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }




    

    public void setHangPhong(String hangPhong) {
        this.hangPhong = hangPhong;
    }

    public String getHangPhong() {
        return hangPhong;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }

    public String getMaKM() {
        return maKM;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getGia() {
        return gia;
    }

}
