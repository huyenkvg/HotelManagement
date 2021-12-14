package qlhp.CTDL;

public class CTTienNghi {

    private String maTN, hangPhong;

    public CTTienNghi() {
        this.maTN = "";
        this.hangPhong = "";
    }

    public CTTienNghi(String maTN, String hangPhong) {
        this.maTN = maTN;
        this.hangPhong = hangPhong;
    }

    public CTTienNghi(String hangPhong) {
        this.hangPhong = hangPhong;
    }

    public void setMaTN(String maTN) {
        this.maTN = maTN;
    }

    public String getMaTN() {
        return maTN;
    }

    public void setHangPhong(String hangPhong) {
        this.hangPhong = hangPhong;
    }

    public String getHangPhong() {
        return hangPhong;
    }
}
