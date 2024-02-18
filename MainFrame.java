import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainFrame extends JFrame implements ActionListener{

    JFrame frame;
    private JLabel backgroundLabel;
    private ImageIcon button;
    JButton butt;
    

    public MainFrame() {
        //super("Background Image Example");

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        ImageIcon imageIcon = new ImageIcon("img/LASTNAME.JPG"); 
        backgroundLabel = new JLabel();
        backgroundLabel.setIcon(imageIcon);

        Image scaledImage = imageIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        backgroundLabel.setIcon(scaledImageIcon);

        setContentPane(backgroundLabel);
        //setContentPane(loco);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Image scaledImage = imageIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
                backgroundLabel.setIcon(scaledImageIcon);
            }
        });
        
        button = new ImageIcon("img/start.PNG");

        Image scaledButton = button.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        button = new ImageIcon(scaledButton);
        butt = new JButton(button);
        
        butt.setBorderPainted(false);
        butt.setContentAreaFilled(false);
        butt.setFocusPainted(false);
        butt.addActionListener(this);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(butt, BorderLayout.CENTER);
        
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                int size = Math.min(getWidth(), getHeight());
                butt.setPreferredSize(new Dimension(size, size));
                revalidate();
            }
        });

        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==butt) {
            
        }
    }

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            }
        });
    }
}