package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddProductView extends JFrame {
    JPanel panel = new JPanel();
    JLabel nume = new JLabel("Nume:");
    JLabel cantitate = new JLabel("Cantitate:");
    JLabel pret = new JLabel("Pret:");
    public JTextField numeTF = new JTextField();
    public JTextField cantitateTF = new JTextField();
    public JTextField pretTF = new JTextField();
    JButton adauga = new JButton("Adauga produs");

    /**
     * Constructor fara parametru pentru crearea ferestrei
     */
    public AddProductView() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 350);
        this.setLocation(525, 200);
        this.setTitle("Produs nou");

        panel.setBackground(new Color(243, 177, 240, 198));
        panel.setLayout(null);

        nume.setBounds(50, 40, 100, 25);
        cantitate.setBounds(50, 100, 100, 25);
        pret.setBounds(50, 160, 100, 25);

        numeTF.setBounds(160, 40, 200, 30);
        cantitateTF.setBounds(160, 100, 200, 30);
        pretTF.setBounds(160, 160, 200, 30);

        nume.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 22));
        cantitate.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 22));
        pret.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 22));

        adauga.setBounds(95, 220, 200, 50);
        adauga.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 18));
        adauga.setBackground(new Color(255, 155, 155, 219));

        panel.add(nume);
        panel.add(cantitate);
        panel.add(pret);
        panel.add(numeTF);
        panel.add(cantitateTF);
        panel.add(pretTF);
        panel.add(adauga);

        this.add(panel);
        this.setVisible(true);
    }

    /**
     * Metoda pentru adaugarea unui ActionListener la buton
     * @param adaugaListener
     */
    public void addAdaugaListener(ActionListener adaugaListener) { adauga.addActionListener(adaugaListener); }
}

