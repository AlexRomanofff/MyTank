package CarShop.uis;

import CarShop.model.Sell;
import CarShop.Shop;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class TableTransaction extends JPanel {
    private Shop shop;

    public TableTransaction(Shop shop) {
        this.shop = shop;
        JFrame frame = new JFrame("Transactions");
        frame.setMinimumSize(new Dimension(600, 400));
        frame.setLocation(300,100);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.getContentPane().add(createTransactionPanel());

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("      File      ");
        JMenu menu1 = new JMenu("      Edit      ");



        frame.setJMenuBar(menuBar);
        menuBar.add(menu);
        menuBar.add(menu1);

        JMenuItem addClientItem = new JMenuItem("Add new Customer");
        JMenuItem addNewCar = new JMenuItem("Add new Car");
        menu1.add(addClientItem);
        menu1.add(addNewCar);


        JMenuItem buyMenuItem = new JMenuItem("Buy Car");
        buyMenuItem.setPreferredSize(new Dimension (100,20));
        menu.add(buyMenuItem);


        buyMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               frame.dispose();
               new ShopWindow(shop);

            }
        });

        addClientItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewClientUI(shop);
            }
        });

        addNewCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 new NewCarUI(shop);
            }
        });

        frame.pack();
        frame.setVisible(true);
    }
    private JPanel createTransactionPanel() {
        JPanel pan = new JPanel();
        pan.setLayout(new GridLayout(1,0));

        Object [] dataColumns = {"TID", "Data","Customer", "Car", "Price"};
        Object [][] data = new Object[10][5];

        fillTable(data);
        JTable table = new JTable(data, dataColumns);
        table.setSelectionForeground(Color.RED);
        table.setShowGrid(false);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(false);

        table.getColumnModel().getColumn(0).setPreferredWidth(20);

        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        pan.add(scrollPane);
        return pan;
    }

    private void fillTable(Object[][] data) {
        int i = 0;
        Locale loc = new Locale("English");
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy",loc);

        for (Sell s : shop.getSales()) {

                data[i][0] = i+1;
                data[i][1] = sdf.format(s.getData());
                data[i][2] = s.getClient().getFullName();
                data[i][3] = s.getCar().getManufacturer() + " " + s.getCar().getModel();
                data[i][4] = s.getCar().getPrice()+"$";
                i++;

        }
    }

}
