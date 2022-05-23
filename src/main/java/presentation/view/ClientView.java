package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ClientView extends JFrame {
    JPanel panel = new JPanel();
    JButton adauga, editeaza, sterge, back;
    JLabel adaugaL = new JLabel("Adauga un client nou");
    JLabel editeazaL = new JLabel("Editeaza client");
    JLabel stergeL = new JLabel("Sterge client");
    public JTable clientsList = new JTable();
    public JTextField textField = new JTextField();
    public JComboBox dataCB = new JComboBox(new String[]{"Nume", "Adresa", "Email", "Varsta"});
    JLabel dataL = new JLabel("ID                             Nume                           Adresa                          Email                           Varsta");

    /**
     * Contructor fara parametru pentru crearea ferestrei
     */
    public ClientView() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setLocation(450, 100);
        this.setTitle("Clients management");

        panel.setLayout(null);

        adauga = new JButton(resize(new ImageIcon("C:\\Users\\this pc\\Desktop\\Facultate\\TP\\Laborator\\PT2021_30221_Georgian_Andreea_Assignment_3\\src\\main\\java\\presentation\\view\\add-user.png"), 40, 40));
        editeaza = new JButton(resize(new ImageIcon("C:\\Users\\this pc\\Desktop\\Facultate\\TP\\Laborator\\PT2021_30221_Georgian_Andreea_Assignment_3\\src\\main\\java\\presentation\\view\\user.png"), 40, 40));
        sterge = new JButton(resize(new ImageIcon("C:\\Users\\this pc\\Desktop\\Facultate\\TP\\Laborator\\PT2021_30221_Georgian_Andreea_Assignment_3\\src\\main\\java\\presentation\\view\\social-media.png"), 40, 40));
        back = new JButton(resize(new ImageIcon("C:\\Users\\this pc\\Desktop\\Facultate\\TP\\Laborator\\PT2021_30221_Georgian_Andreea_Assignment_3\\src\\main\\java\\presentation\\view\\back.png"), 40, 40));

        adauga.setBounds(20, 30, 40, 40);
        sterge.setBounds(20, 85, 40, 40);
        editeaza.setBounds(20, 140, 40, 40);
        back.setBounds(540, 5, 40, 40);

        adauga.setBackground(new Color(255, 155, 155, 219));
        editeaza.setBackground(new Color(255, 155, 155, 219));
        sterge.setBackground(new Color(255, 155, 155, 219));
        back.setBackground(new Color(243, 177, 240, 198));

        adaugaL.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 22));
        editeazaL.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 22));
        stergeL.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 22));
        dataCB.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 14));
        dataL.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 12));

        adaugaL.setBounds(80, 25, 300, 50);
        stergeL.setBounds(80, 80, 300, 50);
        editeazaL.setBounds(80, 135, 300, 50);

        clientsList.setBounds(0, 220, 587, 350);
        textField.setBounds(350, 90, 200, 25);
        dataCB.setBounds(350, 120, 200, 30);
        dataL.setBounds(50, 200, 580, 25);

        panel.add(adauga);
        panel.add(editeaza);
        panel.add(sterge);
        panel.add(back);
        panel.add(adaugaL);
        panel.add(editeazaL);
        panel.add(stergeL);
        panel.add(clientsList);
        panel.add(textField);
        panel.add(dataCB);
        panel.add(dataL);

        panel.setBackground(new Color(243, 177, 240, 198));
        this.setContentPane(panel);

        this.setVisible(true);
    }

    /**
     * Metoda returneaza o imagine redimensionata
     * @param im
     * @param w
     * @param h
     * @return
     */
    public ImageIcon resize (ImageIcon im, int w, int h) {
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
        Graphics2D gd = (Graphics2D)bi.createGraphics();
        gd.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        gd.drawImage(im.getImage(), 0, 0, w, h, null);
        gd.dispose();
        return new ImageIcon(bi);
    }

    public void addAdaugaListener(ActionListener adaugaListener) {adauga.addActionListener(adaugaListener);}
    public void addEditeazaListener(ActionListener editeazaListener) {editeaza.addActionListener(editeazaListener);}
    public void addStergeListener(ActionListener stergeListener) {sterge.addActionListener(stergeListener);}
    public void addBackListener (ActionListener backListener) {back.addActionListener(backListener);}
}
