package qlks.CTDL;

import java.sql.Date;

public class CTPhieuDat {

    private int maPD;
    private int maPhong, sLPhong;
    private Date ngayDen, ngayDi;
    
    public CTPhieuDat() {
    }

    public CTPhieuDat(int maPD, int maPhong, int sLPhong, Date ngayDen, Date ngayDi) {
        this.maPD = maPD;
        this.maPhong = maPhong;
        this.sLPhong = sLPhong;
        this.ngayDen = ngayDen;
        this.ngayDi = ngayDi;
    }
    

    public int getMaPD() {
        return maPD;
    }

    public void setMaPD(int maPD) {
        this.maPD = maPD;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public int getsLPhong() {
        return sLPhong;
    }

    public void setsLPhong(int sLPhong) {
        this.sLPhong = sLPhong;
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
