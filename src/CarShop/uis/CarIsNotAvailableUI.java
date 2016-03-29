package CarShop.uis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarIsNotAvailableUI extends JPanel {
    JFrame frame;
    public CarIsNotAvailableUI () {
        this.frame = new JFrame("new");
        frame.setMinimumSize(new Dimension(300, 150));
        frame.setLocation(500,400);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        frame.getContentPane().add(this);
        JLabel label = new JLabel("This model is not avaliable");
        label.setFont(new Font("Serial", Font.BOLD, 15));

        frame.setLayout(new GridBagLayout());
        frame.add(label,new GridBagConstraints(0, 0, 1, 1,0, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,22,0), 0,0));

        JButton okButton = new JButton("Ok");
        frame.add(okButton, new GridBagConstraints(0, 1, 1, 1,0, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0), 0,0));

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });


        frame.pack();
        frame.setVisible(true);
    }
}
