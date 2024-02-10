import javax.swing.*;
import java.awt.*;

public class CustomerOrder extends JPanel {
    private JLabel orderLabel;

    public CustomerOrder(String order) {
        setLayout(new BorderLayout());

        orderLabel = new JLabel(order);
        orderLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        orderLabel.setHorizontalAlignment(JLabel.CENTER);
        add(orderLabel, BorderLayout.CENTER);
    }
}
