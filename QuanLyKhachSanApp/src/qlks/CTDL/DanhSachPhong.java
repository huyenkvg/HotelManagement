/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlks.CTDL;

import java.util.ArrayList;

/**
 *
 * @author HUYENKUTE
 */
public class DanhSachPhong {
    PhieuDat phieudat = null;
    PhieuThue phieuthue = null;  
    
    public ArrayList<CTPhieuDat> dsCTDat;
    public ArrayList<CTThue> dsCTThue;

    public DanhSachPhong(PhieuDat phieudat, PhieuThue phieuthue, ArrayList<CTPhieuDat> dsCTDat, ArrayList<CTThue> dsCTThue) {
        this.phieudat = phieudat;
        this.phieuthue = phieuthue;
        this.dsCTDat = dsCTDat;
        this.dsCTThue = dsCTThue;
    }

    public DanhSachPhong() {
        dsCTDat = new ArrayList<>();
        dsCTThue = new ArrayList<>();
    }
    

    public PhieuDat getPhieudat() {
        return phieudat;
    }

    public void setPhieudat(PhieuDat phieudat) {
        this.phieudat = phieudat;
    }

    public PhieuThue getPhieuthue() {
        return phieuthue;
    }

    public void setPhieuthue(PhieuThue phieuthue) {
        this.phieuthue = phieuthue;
    }

    public ArrayList<CTPhieuDat> getDsCTDat() {
        return dsCTDat;
    }

    public void setDsCTDat(ArrayList<CTPhieuDat> dsCTDat) {
        this.dsCTDat = dsCTDat;
    }

    public ArrayList<CTThue> getDsCTThue() {
        return dsCTThue;
    }

    public void setDsCTThue(ArrayList<CTThue> dsCTThue) {
        this.dsCTThue = dsCTThue;
    }
    


    
    
}
