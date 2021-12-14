/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlks.CTDL;

/**
 *
 * @author HUYENKUTE
 */
public class ThongTinThuePhong {
    public CTThue chiTietThue;
    public PhieuThue phieuThue = null;
    public KhachHang khachThue;
    public PhieuDat phieuDat = null;
    public CTPhieuDat chiTietDat;

    public ThongTinThuePhong(CTThue chiTietThue, PhieuThue phieuThue, KhachHang khachThue) {
        this.chiTietThue = chiTietThue;
        this.phieuThue = phieuThue;
        this.khachThue = khachThue;
    }
    public ThongTinThuePhong(CTPhieuDat chiTietDat, PhieuDat phieuDat, KhachHang khachThue) {
        this.chiTietDat = chiTietDat;
        this.phieuDat = phieuDat;
        this.khachThue = khachThue;
    }
    
    
}
