package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddClientView extends JFrame {
    JPanel panel = new JPanel();
    JLabel nume = new JLabel("Nume:");
    JLabel adresa = new JLabel("Adresa:");
    JLabel email = new JLabel("Email:");
    JLabel varsta = new JLabel("Varsta:");
    public JTextField numeTF = new JTextField();
    public JTextField adresaTF = new JTextField();
    public JTextField emailTF = new JTextField();
    public JTextField varstaTF = new JTextField();
    JButton adauga = new JButton("Adauga client");

    /**
     * Constructor fara parametru pentru crearea ferestrei
     */
    public AddClientView() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 400);
        this.setLocation(525, 150);
        this.setTitle("Client nou");

        panel.setBackground(new Color(243, 177, 240, 198));
        panel.setLayout(null);

        nume.setBounds(50, 40, 100, 25);
        adresa.setBounds(50, 100, 100, 25);
        email.setBounds(50, 160, 100, 25);
        varsta.setBounds(50, 220, 100, 25);

        numeTF.setBounds(160, 40, 200, 30);
        adresaTF.setBounds(160, 100, 200, 30);
        emailTF.setBounds(160, 160, 200, 30);
        varstaTF.setBounds(160, 220, 200, 30);

        nume.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 22));
        adresa.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 22));
        email.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 22));
        varsta.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 22));

        adauga.setBounds(95, 280, 200, 50);
        adauga.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 18));
        adauga.setBackground(new Color(255, 155, 155, 219));

        panel.add(nume);
        panel.add(adresa);
        panel.add(email);
        panel.add(varsta);
        panel.add(numeTF);
        panel.add(adresaTF);
        panel.add(emailTF);
        panel.add(varstaTF);
        panel.add(adauga);

        this.add(panel);
        this.setVisible(true);
    }

    /**
     * Metoda pentru adaugarea unui ActionListener la buton
     * @param adaugaListener
     */
    public void addAdaugaListener (ActionListener adaugaListener) { adauga.addActionListener(adaugaListener); }
}
