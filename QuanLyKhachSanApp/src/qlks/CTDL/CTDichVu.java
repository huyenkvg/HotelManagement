package qlks.CTDL;

import java.util.Date;

public class CTDichVu {

    private String maDV;
    private int maPT, maPhong;
    private Date ngaySuDung;

    public CTDichVu(String maDV, int maPT, int maPhong, Date ngaySuDung) {
        this.maDV = maDV;
        this.maPT = maPT;
        this.maPhong = maPhong;
        this.ngaySuDung = ngaySuDung;
    }

    public CTDichVu() {
        this.maDV = "";
        this.maPT = 0;
        this.maPhong = 0;
        this.ngaySuDung = new Date();
    }

//    public CTDichVu(int maPhong, Date ngaySuDung) {
//        this.maPhong = maPhong;
//        this.ngaySuDung = this.ngaySuDung;
//    }

    public CTDichVu(Date ngaySuDung) {
        this.ngaySuDung = ngaySuDung;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public int getMaPT() {
        return maPT;
    }

    public void setMaPT(int maPT) {
        this.maPT = maPT;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public Date getNgaySuDung() {
        return ngaySuDung;
    }

    public void setNgaySuDung(Date ngaySuDung) {
        this.ngaySuDung = ngaySuDung;
    }

}
