import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HowtoPlay extends JFrame implements ActionListener {
    private JButton okButton;

    public HowtoPlay() {
        setTitle("Menu Selection");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel menuLabel = new JLabel("How to Play");
        menuLabel.setFont(new Font("Arial", Font.BOLD, 16));
        menuLabel.setHorizontalAlignment(JLabel.CENTER);
        add(menuLabel, BorderLayout.NORTH);

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
