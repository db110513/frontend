import javax.swing.*;

// Crea el marc i mostra TaulerJoc()
public class JocPacman extends JFrame {

    public JocPacman() {
        setTitle("Pacman 2D");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new TaulerJoc());
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JocPacman joc = new JocPacman();
            joc.setVisible(true);
        });
    }
}
