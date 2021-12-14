package Huong;

import java.util.Date;

public class HoaDon {

    private String  maHD, maNV;
    private int maPT;
    private Date ngayLap;
    private int maPhong, gia;

    public HoaDon() {
    }

    public HoaDon(String maHD, String maNV, Date ngayLap, int maPhong, int gia, int maPT) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.maPT = maPT;
        this.ngayLap = ngayLap;
        this.maPhong = maPhong;
        this.gia = gia;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public int getMaPT() {
        return maPT;
    }

    public void setMaPT(int maPT) {
        this.maPT = maPT;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
    

}
