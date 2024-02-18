import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HowtoPlay extends JFrame implements ActionListener {
    private JButton okButton;
    
    public HowtoPlay() {
        setTitle("Menu Selection");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel menuLabel = new JLabel("Menu Pizza");
        menuLabel.setFont(new Font("Arial", Font.BOLD, 16));
        menuLabel.setHorizontalAlignment(JLabel.CENTER);
        add(menuLabel, BorderLayout.NORTH);

        ImageIcon menu = new ImageIcon("img/menu.PNG");
        Image menuImage = menu.getImage(); 

        JLabel label = new JLabel(menu);
        add(label, BorderLayout.CENTER); // add JLabel in BorderLayout,CENTER

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                int width = getWidth();
                int height = getHeight();
                Image newMenuImage = menuImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                ImageIcon newMenuIcon = new ImageIcon(newMenuImage);
                label.setIcon(newMenuIcon); // update size JFrame
            }
        });

        okButton = new JButton("OK");
        okButton.addActionListener(this);
        add(okButton, BorderLayout.SOUTH);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            // click OK for close this window
            this.dispose();
        }
    }
}
