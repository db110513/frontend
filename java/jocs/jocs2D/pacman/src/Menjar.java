import java.awt.*;

public class Menjar {

    private int x, y;  // Posició
    private boolean menjat;

    public Menjar(int x, int y) {
        this.x = x;
        this.y = y;
        this.menjat = false;
    }

    public void dibuixaMenjar(Graphics g) {
        if (!menjat) {
            g.setColor(Color.WHITE);
            g.fillOval(x, y, 10, 10);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 10, 10);
    }

    public boolean isMenjat() {
        return menjat;
    }

    public void setMenjat(boolean menjat) {
        this.menjat = menjat;
    }

}
