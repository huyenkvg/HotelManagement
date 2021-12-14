package qlks.CTDL;

import java.sql.Date;

public class CTThue {

    private int maPT;
    private int maPhong;
    private Date ngayDen, ngayDi;

    public CTThue() {
    }

    public CTThue(int maPT, int maPhong, Date ngayDen, Date ngayDi) {
        this.maPT = maPT;
        this.maPhong = maPhong;
        this.ngayDen = ngayDen;
        this.ngayDi = ngayDi;
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

    public Date getNgayDen() {
        return ngayDen;
    }

    public void setNgayDen(Date ngayDen) {
        this.ngayDen = ngayDen;
    }

    public Date getNgayDi() {
        return ngayDi;
    }

    public void setNgayDi(Date ngayDi) {
        this.ngayDi = ngayDi;
    }
    
 
}
