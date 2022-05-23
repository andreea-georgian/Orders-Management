package presentation.view;

import model.Clients;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;

public class OrderView extends JFrame {
    JPanel panel = new JPanel();
    JButton creeaza, back;
    JLabel creeazaL = new JLabel("Comanda produs");
    public JTable clientsList = new JTable();
    public JTable productsList = new JTable();
    public JTextField textField = new JTextField();
    JLabel cantitate = new JLabel("Cantitate: ");
    JLabel dataL = new JLabel("ID client                      Nume client                      Nume produs              Cantitate                      Pret");

    /**
     * Contructor fara parametrii pentru crearea ferestrei
     */
    public OrderView() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setLocation(450, 100);
        this.setTitle("Order management");

        panel.setLayout(null);

        creeaza = new JButton(resize(new ImageIcon("C:\\Users\\this pc\\Desktop\\Facultate\\TP\\Laborator\\PT2021_30221_Georgian_Andreea_Assignment_3\\src\\main\\java\\presentation\\view\\sent.png"), 40, 40));
        back = new JButton(resize(new ImageIcon("C:\\Users\\this pc\\Desktop\\Facultate\\TP\\Laborator\\PT2021_30221_Georgian_Andreea_Assignment_3\\src\\main\\java\\presentation\\view\\back.png"), 40, 40));

        creeaza.setBounds(50, 40, 40, 40);
        back.setBounds(540, 5, 40, 40);
        cantitate.setBounds(110, 90, 200, 35);
        creeazaL.setBounds(110, 35, 300, 50);
        textField.setBounds(230, 95, 200, 25);
        clientsList.setBounds(10, 170, 250, 400);
        productsList.setBounds(270, 170, 310, 400);
        dataL.setBounds(45, 150, 580, 25);

        creeaza.setBackground(new Color(255, 155, 155, 219));
        back.setBackground(new Color(243, 177, 240, 198));

        cantitate.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 22));
        creeazaL.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 22));
        dataL.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 12));

        panel.add(creeaza);
        panel.add(back);
        panel.add(creeazaL);
        panel.add(cantitate);
        panel.add(textField);
        panel.add(clientsList);
        panel.add(productsList);
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

    public void addAdaugaListener(ActionListener adaugaListener) {creeaza.addActionListener(adaugaListener);}
    public void addBackListener (ActionListener backListener) {back.addActionListener(backListener);}

    /**
     * Metoda populeaza tabelul cu elementele lista de clienti
     * @param clients
     * @param fields
     */
    public void addElementsToClientTable(List<Clients> clients, String[] fields) {
        DefaultTableModel model = new DefaultTableModel();
        clientsList.setModel(model);
        for (String f : fields)
            model.addColumn(f);
        for (Clients c : clients) {
            model.addRow(new Object[]{c.getID(), c.getClientName()});
        }
    }

    /**
     * Metoda populeaza tabelul cu elementele din lista de produse
     * @param product
     * @param fields
     */
    public void addElementsToProductTable(List<Product> product, String[] fields) {
        DefaultTableModel model = new DefaultTableModel();
        productsList.setModel(model);
        for (String f : fields)
            model.addColumn(f);
        for (Product p : product) {
            model.addRow(new Object[]{p.getProductName(), p.getCantity(), p.getPrice()});
        }
    }

}
