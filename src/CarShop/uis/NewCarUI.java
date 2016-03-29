package CarShop.uis;

import CarShop.*;
import CarShop.model.Car;
import CarShop.model.EngineKind;
import CarShop.model.Manufacturer;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class NewCarUI extends JPanel{
      private JFrame frame;
      Shop shop;

    public NewCarUI (Shop shop) {
        this.shop = shop;
        frame = new JFrame("Add new car to storage");

        frame.setMinimumSize(new Dimension(300, 200));
        frame.setLocation (300, 500);
        frame.getContentPane().add(createPanel());

        frame.setVisible(true);
        frame.pack();


    }
    private JPanel createPanel() {
        JPanel pan = new JPanel();
        pan.setLayout(new BorderLayout());
        JPanel productPanel = new JPanel();
        pan.add(productPanel, BorderLayout.NORTH);
        productPanel.setLayout(new GridBagLayout());

        JLabel producerLabel = new JLabel("Producer");
        JComboBox<Manufacturer> producerBox = new JComboBox<>();
        for(Manufacturer man: Manufacturer.values()) {
            producerBox.addItem(man);
        }
        productPanel.add(producerLabel, new GridBagConstraints(0, 0, 1, 1,0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10,0,10,0), 50,0));
        productPanel.add(producerBox, new GridBagConstraints(1, 0, 2, 1,0, 0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(10,0,10,0), 0,0));

        JLabel modelLabel = new JLabel("Model");
        JTextField modelText = new JTextField();

        productPanel.add(modelLabel, new GridBagConstraints(0, 1, 1, 1,0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0,0,10,0), 50,0) );
        productPanel.add(modelText, new GridBagConstraints(1, 1, 2, 1,0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0,0,10,0), 0,0) );

        JLabel engineLabel = new JLabel("Engine");
        JComboBox<EngineKind> engineBox = new JComboBox<>();
        for(EngineKind eng: EngineKind.values()) {
            engineBox.addItem(eng);
        }
        productPanel.add(engineLabel, new GridBagConstraints(0, 2, 1, 1,0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0,0,10,0), 50,0) );
        productPanel.add(engineBox, new GridBagConstraints(1, 2, 2, 1,0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0,0,10,0), 0,0) );

        JLabel priceLabel = new JLabel("Price");


        JLabel currency = new JLabel("$");
        JFormattedTextField priceText = new JFormattedTextField(setFormatForPrice());

        productPanel.add(priceLabel, new GridBagConstraints(0, 3, 1, 1,0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0,0,10,0), 50,0) );
        productPanel.add(priceText, new GridBagConstraints(1, 3, 2, 1,0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0,0,10,0), 0,0) );
        productPanel.add(currency, new GridBagConstraints(3, 3, 1, 1,0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0,0,10,0), 0,0) );

        JLabel countLabel = new JLabel("Count");

        JFormattedTextField count = new JFormattedTextField(setFormatForCount());
        count.setValue(1);


        productPanel.add(countLabel, new GridBagConstraints(0, 4, 1, 1,0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0,0,10,0), 50,0) );
        productPanel.add(count, new GridBagConstraints(1, 4, 1, 1,0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0,0,10,0), 10,0) );

        JButton cancelButton = new JButton("Cancel");
        JButton addButton = new JButton("Add goods");

        productPanel.add(cancelButton, new GridBagConstraints(0, 5, 1, 1,0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0,-50,10,5), 0,0) );
        productPanel.add(addButton, new GridBagConstraints(1, 5, 3, 1,0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0,0,10,-50), 10,0) );

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Car car = shop.initCar(producerBox.getItemAt(producerBox.getSelectedIndex()), modelText.getText(), Double.parseDouble(priceText.getText()),
                       engineBox.getItemAt(engineBox.getSelectedIndex()), CarShop.Color.BLUE);
                shop.addNewCar(car);
                frame.dispose();
            }
        });

        return pan;

    }

    private MaskFormatter setFormatForPrice() {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter("#####.#");
        } catch (java.text.ParseException exc) {
            exc.printStackTrace();
        }
        return formatter;

    }
    private MaskFormatter setFormatForCount() {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter("#");
        } catch (java.text.ParseException exc) {
            exc.printStackTrace();
        }
        return formatter;

    }
}
