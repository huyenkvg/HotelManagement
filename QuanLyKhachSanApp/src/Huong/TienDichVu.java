/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Huong;

import java.util.Date;
import qlks.CTDL.CTDichVu;
import qlks.CTDL.DichVu;
import qlks.CTDL.GiaDichVu;

/**
 *
 * @author thuhu
 */
public class TienDichVu {
//     private int maPhong;
//    private String tenDV;
//    private Date thoigian;
//    private int giaDichVu;
//    private int tongTienDV;
    private DichVu dichVu;
    private CTDichVu ctDichVu;

    public TienDichVu(DichVu dichVu, CTDichVu ctDichVu) {
        this.dichVu = dichVu;
        this.ctDichVu = ctDichVu;
    }

    public DichVu getDichVu() {
        return dichVu;
    }

    public CTDichVu getCtDichVu() {
        return ctDichVu;
    }

    public void setDichVu(DichVu dichVu) {
        this.dichVu = dichVu;
    }

    public void setCtDichVu(CTDichVu ctDichVu) {
        this.ctDichVu = ctDichVu;
    }
     
    
}
   
   