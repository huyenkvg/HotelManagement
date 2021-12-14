package PanelChiaSoDoPhong;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.max;
import java.util.ArrayList;
import javax.swing.JPanel;
import qlks.CTDL.Phong;

public class SoDoPhong extends JPanel implements ActionListener {

    static final int soDeChia = 100;
    static final Color SUACHUA = new Color(255, 131, 198);
    static final Color DADAT = new Color(0, 153, 255);
    static final Color DANGTHUE = new Color(51, 255, 153);
    static final Color CHUADON = new Color(255, 144, 109);
    static final Color TRONG = new Color(255, 255, 204);
    static int DoCaoMotO = 0;
    static int DoRongMotO = 0;
    int WID = 520, HEI = 320;
    int cols = 0;
    int rows = 0;
    Point oBiClick = new Point(-1, -1);
    ArrayList<Phong> danhSachPhong;
    ChiaOSoDo mangLuoi[][] = new ChiaOSoDo[200][200];
    Integer luoiCheck[][] = new Integer[200][200];

    public SoDoPhong() {
        this.mangLuoi = new ChiaOSoDo[200][200];
        initComponents();
    }

    public SoDoPhong(ArrayList<Phong> danhSachPhong) {
        this.mangLuoi = new ChiaOSoDo[200][200];
        this.danhSachPhong = danhSachPhong;
        initComponents();
        int x = 0, y = 0;

        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 200; j++) {
                mangLuoi[i][j] = new ChiaOSoDo();
                luoiCheck[i][j] = 0;
            }
        }
        for (Phong p : danhSachPhong) {
            x = p.getMaPhong() / soDeChia;
            y = p.getMaPhong() % soDeChia;
            cols = max(cols, y);
            rows = max(rows, x);
            luoiCheck[x][y] = 1;
            mangLuoi[x][y].text += p.getMaPhong();
            switch (p.getTrangThai()) {
                case "Dơ":
                    mangLuoi[x][y].color = CHUADON;
                    break;
                case "Đặt":
                    mangLuoi[x][y].color = DADAT;
                    break;
                case "Trống":
                    mangLuoi[x][y].color = TRONG;
                    break;
                case "Sửa":
                    mangLuoi[x][y].color = SUACHUA;
                    break;
                case "Thuê":
                    mangLuoi[x][y].color = DANGTHUE;
                    break;
                default:
                    break;
            }
        }

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {

                mangLuoi[i][j].x = (j - 1) * (WID / cols) + 1;
                mangLuoi[i][j].y = (i - 1) * (HEI / rows) + 1;

            }
        }
        DoRongMotO = WID / cols - 1;
        DoCaoMotO = HEI / rows - 1;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0, 0, 102));
        g2d.fillRect(0, 0, WID, HEI);

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                g2d.setColor(mangLuoi[i][j].color);
                g2d.fillRect(mangLuoi[i][j].x + 1, mangLuoi[i][j].y + 1, DoRongMotO, DoCaoMotO);
                g2d.setFont(new Font("Tahoma", Font.PLAIN, 17));
                g2d.setColor(Color.BLACK);
                if (i == oBiClick.x && j == oBiClick.y) {
                    g2d.setColor(Color.RED);
                    BasicStroke bs = new BasicStroke(4);

                    g2d.setStroke(bs);
                    g2d.drawRect(mangLuoi[i][j].x, mangLuoi[i][j].y, DoRongMotO, DoCaoMotO);
                    g2d.setFont(new Font("Tahoma", Font.BOLD, 24));
                    g2d.setColor(Color.RED);
                }

                g2d.drawString(mangLuoi[i][j].text, (mangLuoi[i][j].x + 3), (mangLuoi[i][j].y + DoCaoMotO - g2d.getFont().getSize() / 2));

            }
        }
    }

    public void thaydoiTrangThaiPhong(Phong phong) {
        int x = phong.getMaPhong() / soDeChia;
        int y = phong.getMaPhong() % soDeChia;
        phong.setTrangThai(phong.getTrangThai());
        switch (phong.getTrangThai()) {
            case "Dơ":
                mangLuoi[x][y].color = CHUADON;
                break;
            case "Đặt":
                mangLuoi[x][y].color = DADAT;
                break;
            case "Trống":
                mangLuoi[x][y].color = TRONG;
                break;
            case "Sửa":
                mangLuoi[x][y].color = SUACHUA;
                break;
            case "Thuê":
                mangLuoi[x][y].color = DANGTHUE;
                break;
            default:
                break;
        }
        repaint();
    }

    public int getRoomIdFromPosition(Point p) {
        int i = p.y / DoCaoMotO + 1;
        int j = p.x / DoRongMotO + 1;
        if (luoiCheck[i][j] != 1) {
            return -1;
        }
        oBiClick.x = i;
        oBiClick.y = j;
        repaint();

        return Integer.parseInt(mangLuoi[i][j].text);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(0, 0, 102));
        setPreferredSize(new java.awt.Dimension(520, 220));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 205, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
