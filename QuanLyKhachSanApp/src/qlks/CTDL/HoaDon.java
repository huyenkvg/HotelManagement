package qlks.CTDL;

import java.sql.Date;

public class HoaDon {

    private String  maNV;
    private int maPT, maHD;
    private Date ngayLap;
    private int gia;

    public HoaDon() {
    }

    public HoaDon(int maHD, String maNV, int maPT, Date ngayLap, int gia) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.maPT = maPT;
        this.ngayLap = ngayLap;
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

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
    

}
