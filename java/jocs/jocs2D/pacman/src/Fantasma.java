import java.awt.*;
import java.util.Random;

public class Fantasma {
    private int x, y;   // Posició
    private int vx, vy;   // Velocitat
    private Color color;

    public Fantasma(int x, int y) {
        this.x = x;
        this.y = y;
        this.vx = 3;
        this.vy = 3;
        this.color = Color.GREEN;
    }

    public void moureFantasma() {
        // Moviment aleatori dels fantasmes per a simulació
        Random rand = new Random();
        int direccio = rand.nextInt(4); // 4 direccions possibles (0: esquerra, 1: dreta, 2: amunt, 3: avall)

        if (direccio == 0) {
            x -= vx;
        }
        else if (direccio == 1) {
            x += vx;
        }
        else if (direccio == 2) {
            y -= vy;
        }
        else if (direccio == 3) {
            y += vy;
        }


        // El fantasma no surt de la pantalla
        if (x < 0) x = 0;
        if (x > 380) x = 380;
        if (y < 0) y = 0;
        if (y > 380) y = 380;

    }

    public void dibuixaFantasma(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, 20, 20);  // Dibuixa un quadrat
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 20, 20);  // Retorna els límits del fantasma per a col·lisions
    }

}
