package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class FirstView extends JFrame {
    JPanel panel = new JPanel();
    JButton client, produs, comanda;
    JLabel clientL = new JLabel("Clienti");
    JLabel produsL = new JLabel("Produse");
    JLabel comandaL = new JLabel("Comenzi");

    /**
     * Contructor fara parametrii pentru crearea ferestrei
     */
    public FirstView() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 300);
        this.setLocation(500, 250);
        this.setTitle("Orders management");

        panel.setLayout(null);

        client = new JButton(resize(new ImageIcon("C:\\Users\\this pc\\Desktop\\Facultate\\TP\\Laborator\\PT2021_30221_Georgian_Andreea_Assignment_3\\src\\main\\java\\presentation\\view\\customer.png"), 100, 100));
        produs = new JButton(resize(new ImageIcon("C:\\Users\\this pc\\Desktop\\Facultate\\TP\\Laborator\\PT2021_30221_Georgian_Andreea_Assignment_3\\src\\main\\java\\presentation\\view\\products.png"), 100,100));
        comanda = new JButton(resize(new ImageIcon("C:\\Users\\this pc\\Desktop\\Facultate\\TP\\Laborator\\PT2021_30221_Georgian_Andreea_Assignment_3\\src\\main\\java\\presentation\\view\\checkout.png"), 100, 100));

        client.setBackground(new Color(255, 155, 155, 219));
        produs.setBackground(new Color(255, 155, 155, 219));
        comanda.setBackground(new Color(255, 155, 155, 219));

        client.setBounds(20, 70, 120, 120);
        produs.setBounds(185, 70, 120, 120);
        comanda.setBounds(350, 70, 120, 120);

        clientL.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 23));
        produsL.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 23));
        comandaL.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 23));

        clientL.setBounds(40, 185, 100, 50);
        produsL.setBounds(205, 185, 100, 50);
        comandaL.setBounds(368, 185, 100, 50);

        panel.add(client);
        panel.add(produs);
        panel.add(comanda);
        panel.add(clientL);
        panel.add(produsL);
        panel.add(comandaL);

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

    public void addClientListener (ActionListener clientListener) {client.addActionListener(clientListener);}
    public void addProdusListener (ActionListener produsListener) {produs.addActionListener(produsListener);}
    public void addComandaListener (ActionListener comandaListener) {comanda.addActionListener(comandaListener);}
}
