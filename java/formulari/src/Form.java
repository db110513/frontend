import javax.swing.*;
import java.awt.*;

public class Form {

    public static void main(String[] args) {

        // Crear la finestra (JFrame)
        JFrame frame = new JFrame("Formulari amb Swing");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un panell principal amb GridBagLayout
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Establir la font
        Font font = new Font("Arial", Font.PLAIN, 14);

        // Components del formulari
        JLabel nom = new JLabel("Nom:");
        nom.setFont(font);

        JTextField nomField = new JTextField(15);
        nomField.setFont(font);

        JLabel cognoms = new JLabel("Cognoms:");
        cognoms.setFont(font);

        JTextField cognomsField = new JTextField(15);
        cognomsField.setFont(font);

        JLabel edat = new JLabel("Edat:");
        edat.setFont(font);

        JTextField edatField = new JTextField(15);
        edatField.setFont(font);

        JLabel nacionalitatLabel = new JLabel("Nacionalitat:");
        nacionalitatLabel.setFont(font);

        String[] nacionalitats = {"", "Espanya", "França", "Itàlia", "Alemanya", "Portugal"};
        JComboBox<String> nacionalitatsCombo = new JComboBox<>(nacionalitats);
        nacionalitatsCombo.setFont(font);

        JLabel residenciaLabel = new JLabel("Residència:");
        residenciaLabel.setFont(font);

        JComboBox<String> residenceCombo = new JComboBox<>(nacionalitats);
        residenceCombo.setFont(font);

        JLabel genere = new JLabel("Gènere:");
        genere.setFont(font);

        JRadioButton home = new JRadioButton("Home");
        home.setFont(font);

        JRadioButton dona = new JRadioButton("Dona");
        dona.setFont(font);

        JRadioButton noBinari = new JRadioButton("No binari");
        noBinari.setFont(font);

        ButtonGroup grupGenere = new ButtonGroup();
        grupGenere.add(home);
        grupGenere.add(dona);
        grupGenere.add(noBinari);

        JLabel subscriure = new JLabel("Subscriure's al butlletí:");
        subscriure.setFont(font);

        JCheckBox subscriureCheck = new JCheckBox("Sí, m'agradaria rebre notícies");
        subscriureCheck.setFont(font);

        JButton botoEnviar = new JButton("Enviar");
        botoEnviar.setFont(font);

        // Afegir components al panell
        gbc.anchor = GridBagConstraints.LINE_END;

        // Nom
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nom, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(nomField, gbc);

        // Cognoms
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(cognoms, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(cognomsField, gbc);

        // Edat
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(edat, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(edatField, gbc);

        // Nacionalitat
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(nacionalitatLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(nacionalitatsCombo, gbc);

        // Residència
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(residenciaLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(residenceCombo, gbc);

        // Gènere
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(genere, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.LINE_START;
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        genderPanel.add(home);
        genderPanel.add(dona);
        genderPanel.add(noBinari);
        panel.add(genderPanel, gbc);

        // Subscriure's al butlletí
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(subscriure, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(subscriureCheck, gbc);

        // Botó Enviar
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(botoEnviar, gbc);

        // Afegir panell al frame
        frame.add(panel);

        // Afegir funció al botó d'enviar
        botoEnviar.addActionListener(e -> {
            String name = nomField.getText();
            String surname = cognomsField.getText();
            String age = edatField.getText();
            String nationality = (String) nacionalitatsCombo.getSelectedItem();
            String residence = (String) residenceCombo.getSelectedItem();
            String gender = home.isSelected() ? "Home" : dona.isSelected() ? "Dona" : "No binari";
            boolean subscribed = subscriureCheck.isSelected();

            // Missatge amb la informació del formulari
            String message = String.format("Nom: %s\nCognoms: %s\nEdat: %s\nNacionalitat: %s\nResidència: %s\nGènere: %s\nSubscriure's al butlletí: %s",
                    name, surname, age, nationality, residence, gender, subscribed ? "Sí" : "No");

            JOptionPane.showMessageDialog(frame, message, "Informació del Formulari", JOptionPane.INFORMATION_MESSAGE);
        });

        // Mostrar la finestra
        frame.setVisible(true);
    }
}
