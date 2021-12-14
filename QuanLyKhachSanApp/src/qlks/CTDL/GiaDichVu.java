package qlks.CTDL;

import java.util.Date;

public class GiaDichVu {

    private String maDV;
    private int gia;
    private Date ngay;

    public GiaDichVu(String maDV, int gia) {
        this.maDV = maDV;
        this.gia = gia;
    }
    
    public GiaDichVu() {
        this.maDV = "";
        this.ngay = new Date();
        this.gia = 0;
    }
    
    public GiaDichVu(int gia){
        this.gia = gia;
    }

    public GiaDichVu(String maDV, Date ngay, int gia) {
        this.maDV = maDV;
        this.ngay = ngay;
        this.gia = gia;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getGia() {
        return gia;
    }

}
