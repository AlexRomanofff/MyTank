package CarShop.uis;


import CarShop.Shop;
import com.toedter.calendar.JCalendar;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;


public class SelectSalesByData extends JFrame{

    private JFrame frame;
    private Shop shop;
    TableTransaction tt;

    public SelectSalesByData (Shop shop, TableTransaction tt)  {

        this.shop = shop;
        this.tt = tt;
        frame = new JFrame("Selection Sales");
        frame.setMinimumSize(new Dimension(300, 350));
        frame.setLocation(300,100);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(createPanel());
        frame.pack();
        frame.setVisible(true);

    }

    private JPanel createPanel () {
        JPanel pan = new JPanel();
        pan.setLayout(new GridBagLayout());
        JFormattedTextField tf = new JFormattedTextField(format());
        tf.setColumns(10);

        JButton ok = new JButton("Select");
        JButton cancel = new JButton("Cancel");
        JLabel label = new JLabel("Select the date");
        JCalendar calendar = new JCalendar();


        pan.add(label, new GridBagConstraints(0, 0, 2, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(10,50,0,0), 0,0));
        pan.add(calendar, new GridBagConstraints(1, 1, 2, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(30,40,0,0), 0,0));
        pan.add(ok, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(10,0,0,0), 0,0));
        pan.add(cancel, new GridBagConstraints(2, 2, 2, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(10,25,0,0), 0,0));

//        pan.setBackground(Color.CYAN);


        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Locale loc = new Locale("English");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",loc);
                System.out.println(sdf.format(calendar.getDate()));
                String datas = sdf.format(calendar.getDate()).toString();
                Date date = Date.valueOf(datas);
                System.out.println(date);
                tt.addNew(shop.getSalesByData(date));
        }
        });

        return pan;
    }
    private MaskFormatter format () {
       MaskFormatter form = null;
        try {
            form = new MaskFormatter("2016-##-##");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return form;
    }

}

