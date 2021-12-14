package qlks.CTDL;

public class BoPhan {

    private String maBP, tenBP;

    public BoPhan() {
        this.maBP = "";
        this.tenBP = "";
    }

    public BoPhan(String maBP, String tenBP) {
        this.maBP = maBP;
        this.tenBP = tenBP;
    }

    public String getMaBP() {
        return maBP;
    }

    public void setMaBP(String maBP) {
        this.maBP = maBP;
    }

    public String getTenBP() {
        return tenBP;
    }

    public void setTenBP(String tenBP) {
        this.tenBP = tenBP;
    }

}
