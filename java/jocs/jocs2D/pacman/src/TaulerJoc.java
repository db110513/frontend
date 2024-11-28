import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TaulerJoc extends JPanel implements ActionListener {

    // Timer > necessari pel moviment de pacman i fantasmes
    private Timer timer;
    private Pacman pacman;
    private List<Fantasma> fantasmes;
    private List<Menjar> menjars;
    private boolean tocat;
    private JButton reinicia;
    private int menjarRestant;

    public TaulerJoc() {
        pacman = new Pacman(this);
        fantasmes = new ArrayList<>();

        // Creo 8 fantasmes
        for (int i = 0; i < 8; i++) {
            fantasmes.add(new Fantasma(new Random().nextInt(400), new Random().nextInt(400)));
        }

        menjars = new ArrayList<>();
        menjarRestant = 20;

        // Crear 20 menjars amb posicions aleatòries
        for (int i = 0; i < menjarRestant; i++) {
            int x = (int) (Math.random() * (getWidth() - 20));
            int y = (int) (Math.random() * (getHeight() - 20));
            menjars.add(new Menjar(x, y));
        }

        tocat = false;

        timer = new Timer(40, this);
        timer.start();

        // Botó que apareix al final del joc
        reinicia = new JButton("Reiniciar");
        reinicia.setFont(new Font("Arial", Font.PLAIN, 16));
        reinicia.setBounds(150, 300, 100, 40);
        reinicia.setVisible(false);
        reinicia.addActionListener(e -> reiniciarJoc());

        this.setLayout(null);

        // Afegeix botó al tauler
        this.add(reinicia);

        setFocusable(true);

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                pacman.teclaPitjada(e);
            }
        });
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);

        if (tocat) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            FontMetrics metrics = g.getFontMetrics();
            String message = "Joc Finalitzat";
            int x = (getWidth() - metrics.stringWidth(message)) / 2;
            int y = getHeight() / 2 - 30;
            g.drawString(message, x, y);

            reinicia.setLocation((getWidth() - reinicia.getWidth()) / 2, y + 50);
            reinicia.setVisible(true);
            timer.stop();
        }
        else if (menjarRestant == 0) {
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            FontMetrics metrics = g.getFontMetrics();
            String message = "Has guanyat!";
            int x = (getWidth() - metrics.stringWidth(message)) / 2;
            int y = getHeight() / 2 - 30;
            g.drawString(message, x, y);

            reinicia.setLocation((getWidth() - reinicia.getWidth()) / 2, y + 50);
            reinicia.setVisible(true);
            timer.stop();
        }
        else {
            pacman.dibuixaPacman(g);

            for (Fantasma fantasma : fantasmes) {
                fantasma.dibuixaFantasma(g);
            }

            for (Menjar menjar : menjars) {
                menjar.dibuixaMenjar(g);
            }

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            String menjarText = "menjar: " + menjarRestant;
            int menjarTextX = getWidth() - g.getFontMetrics().stringWidth(menjarText) - 10;
            g.drawString(menjarText, menjarTextX, 20);

        }

    }

    public void actionPerformed(ActionEvent e) {

        if (!tocat && menjarRestant > 0) {
            pacman.mou();

            for (Fantasma fantasma : fantasmes) {
                fantasma.moureFantasma();
            }

            checkCollisions();
            repaint();
        }
    }

    private void checkCollisions() {
        Rectangle pacmanBounds = pacman.getBounds();

        // Comprovar col·lisions amb el menjar
        for (Menjar menjar : menjars) {
            if (!menjar.isMenjat() && pacmanBounds.intersects(menjar.getBounds())) {
                menjar.setMenjat(true);
                menjarRestant--;
            }
        }

        // Comprovar col·lisions amb els fantasmes
        for (Fantasma fantasma : fantasmes) {
            if (pacmanBounds.intersects(fantasma.getBounds())) {
                tocat = true;
                break;
            }
        }
    }

    private void reiniciarJoc() {
        pacman = new Pacman(this);
        fantasmes.clear();

        for (int i = 0; i < 8; i++) {
            fantasmes.add(new Fantasma(new Random().nextInt(400), new Random().nextInt(400)));
        }

        menjars.clear();
        menjarRestant = 20;

        for (int i = 0; i < menjarRestant; i++) {
            int x = (int) (Math.random() * (getWidth() - 20));
            int y = (int) (Math.random() * (getHeight() - 20));
            menjars.add(new Menjar(x, y));
        }

        tocat = false;
        reinicia.setVisible(false);
        timer.start();
        repaint();
    }
}
