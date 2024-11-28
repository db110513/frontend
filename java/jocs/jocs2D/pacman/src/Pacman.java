import java.awt.*;
import java.awt.event.KeyEvent;

public class Pacman {

    private int x, y;  // Posició
    private int vx, vy;  // Velocitat
    private TaulerJoc taulerJoc;

    public Pacman(TaulerJoc taulerJoc) {
        this.taulerJoc = taulerJoc;
        x = 0;
        y = 0;
        vx = 0;
        vy = 0;
    }

    public void mou() {

        if (x + vx >= 0 && x + vx + 20 <= taulerJoc.getWidth()) {  // 20 és l'amplada de Pacman
            x += vx;
        }
        if (y + vy >= 0 && y + vy + 20 <= taulerJoc.getHeight()) {  // 20 és l'alçada de Pacman
            y += vy;
        }

    }

    public void dibuixaPacman(Graphics g) {

        g.setColor(Color.YELLOW);

        g.fillArc(x, y, 20, 20, 45, 260);    // 260 graus per l'arc de la boca

        int ullX = x + 8;
        int ullY = y + 2;

        g.setColor(Color.BLACK);
        g.fillOval(ullX, ullY, 4, 4);

    }

    public void teclaPitjada(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            vx = -2;
            vy = 0;
        }
        else if (key == KeyEvent.VK_RIGHT) {
            vx = 2;
            vy = 0;
        }
        else if (key == KeyEvent.VK_UP) {
            vy = -2;
            vx = 0;
        }
        else if (key == KeyEvent.VK_DOWN) {
            vy = 2;
            vx = 0;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 20, 20);
    }
}
