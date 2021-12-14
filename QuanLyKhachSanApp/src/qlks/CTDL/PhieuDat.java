package qlks.CTDL;

public class PhieuDat {

    private String maNV, maKH;
    private int maPD;

    public PhieuDat(String maNV, String maKH, int maPD) {
        this.maNV = maNV;
        this.maKH = maKH;
        this.maPD = maPD;
    }

    public PhieuDat() {
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public int getMaPD() {
        return maPD;
    }

    public void setMaPD(int maPD) {
        this.maPD = maPD;
    }
    
}
