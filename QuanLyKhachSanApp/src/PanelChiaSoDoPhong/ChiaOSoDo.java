package PanelChiaSoDoPhong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import static java.lang.Math.PI;

public class ChiaOSoDo {

    public int x, y;
    public int x1, y1;
    public Color color = Color.GRAY;
    public String text = "";

    public ChiaOSoDo(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public ChiaOSoDo() {
    }

    public ChiaOSoDo(int x, int y) {
        this.x = x;
        this.y = y;
        color = Color.GRAY;
    }
}
