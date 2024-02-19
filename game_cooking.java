import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class game_cooking extends JFrame implements ActionListener {
    private JFrame frame ;
    private JButton buttonStart;
    private JLabel backgroundLabel;
    private JLabel backgroundOrder ; 
    private JLabel selectDough;
    private JLabel emptyLabel ;
    private Container container ;
    private JLabel generateMenu;
    public static String save_select = "";
    private int correctAns = 0; 
    private int WIDTH = 800;
    private int HEIGHT = 600 ; 

    public game_cooking() {
        frame = new JFrame();
        frame.setTitle("Cooking Game");
        container = frame.getContentPane();
        //background
        ImageIcon imageIcon = new ImageIcon("img/LASTNAME.JPG"); 
        backgroundLabel = new JLabel();
        backgroundLabel.setIcon(imageIcon);
        backgroundLabel.setBounds(0, 0, WIDTH,HEIGHT);
        backgroundLabel.setLayout(null);
        frame.add(backgroundLabel);
        frame.setBounds(0, 0, 800, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);//let the background in the middle
        frame.setVisible(true);
        Image scaledImage = imageIcon.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        backgroundLabel.setIcon(scaledImageIcon);
        ImageIcon button = new ImageIcon("img/start.PNG");
        Image scaledButton = button.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        button = new ImageIcon(scaledButton);
        buttonStart = new JButton(button);       
        buttonStart.setBorderPainted(false);
        buttonStart.setContentAreaFilled(false);

        buttonStart.setBounds(WIDTH/2-100,HEIGHT/2-75,200,150);
        backgroundLabel.add(buttonStart);
        buttonStart.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonStart) {
            showMenu();
        }
    }

    private void showMenu() {
        HowtoPlay howto = new HowtoPlay();
        howto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        howto.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                startGame(); // start 
            }
        });
        
        howto.setVisible(true);
    }

    public void startGame() {
        // hide "<Start>"
        buttonStart.setVisible(false);
        new Thread(new Runnable() {
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    container.remove(backgroundLabel);
                    //Clear
                    backgroundOrder = new JLabel();
                    ImageIcon backgroundImageOrder = new ImageIcon("img/Background.jpg");
                    Image scaledbackground = backgroundImageOrder.getImage().getScaledInstance(800,600, Image.SCALE_SMOOTH);
                    ImageIcon imgOrder = new ImageIcon(scaledbackground);
                    backgroundOrder = new JLabel(imgOrder);

                    backgroundOrder.setBounds(0, 0, WIDTH, HEIGHT);
                    

                    frame.add(backgroundOrder);
                    JButton nextButton = new JButton();
                    ImageIcon button = new ImageIcon("img/NEXT.PNG");
                    Image scaledButton = button.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
                    button = new ImageIcon(scaledButton);
                    nextButton = new JButton(button);       
                    nextButton.setBorderPainted(false);
                    nextButton.setContentAreaFilled(false);
                    nextButton.setBounds(WIDTH/2-100,HEIGHT-(250),200,150);

                    generateMenu = new JLabel(Customer.generateMenu());
                    generateMenu.setFont(new Font("Arial", Font.PLAIN, 25));
                    generateMenu.setBounds(120,HEIGHT/2-(HEIGHT/8) , WIDTH, HEIGHT/4);
                    backgroundOrder.add(nextButton);
                    backgroundOrder.add(generateMenu);
                    nextButton.addActionListener(e -> {
                        selectDough();
                        System.out.println("select");
                    });
                    frame.repaint();
                });

            }
        }).start();
    }

    private void selectDough() {
        //Clear
        frame.remove(backgroundOrder);
        frame.repaint();
        selectDough = new JLabel(new ImageIcon("img/Background.jpg"));
        selectDough.setBounds(0, 0, WIDTH, HEIGHT);
        frame.add(selectDough);

        JLabel chooseDoughLabel = new JLabel("SELECT DOUGH ");
        chooseDoughLabel.setFont(new Font("Arial", Font.BOLD, 30));
        selectDough.add(chooseDoughLabel);
        chooseDoughLabel.setBounds(WIDTH/2-120,0 ,300,30);
        chooseDoughLabel.setBackground(new Color(0, 100, 0));
            
        ImageIcon thickIcon = new ImageIcon("img/thick.jpg");
        JButton thickButton = new JButton(thickIcon);

        ImageIcon thinIcon = new ImageIcon("img/thin.jpg");
        JButton thinButton = new JButton(thinIcon);
    
        // // ActionListener "thick Dough"
        thickButton.addActionListener(e -> {
            selectTopping("Thick dough");
        });

        // // ActionListener "thin Dough"
        thinButton.addActionListener(e -> {
            selectTopping("Thin dough");
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0,30, WIDTH, HEIGHT-30);
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(thickButton);
        buttonPanel.add(thinButton);
        selectDough.add(buttonPanel);
        frame.revalidate();
        frame.repaint();

    }

    private void selectTopping(String doughType) {
        
        frame.remove(selectDough);
        emptyLabel = new JLabel();
        emptyLabel.setBounds(0, 0, WIDTH, HEIGHT);
        frame.add(emptyLabel);
    
        JLabel chooseToppingLabel = new JLabel("choose Toppings");
        chooseToppingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        chooseToppingLabel.setBounds(WIDTH/2-76, 0, WIDTH, 18);
        emptyLabel.add(chooseToppingLabel);
        
        ImageIcon cheeseIcon = new ImageIcon("img/cheese.png");
        ImageIcon pineappleIcon = new ImageIcon("img/pineapple.png");
        ImageIcon mushroomIcon = new ImageIcon("img/mushroom.png");
        ImageIcon hamIcon = new ImageIcon("img/ham.png");
        ImageIcon seafoodIcon = new ImageIcon("img/seafood.png");
        ImageIcon baconIcon = new ImageIcon("img/bacon.png");

        Image cheeseImage = cheeseIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        Image pineappleImage = pineappleIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        Image mushroomImage = mushroomIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        Image hamImage = hamIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        Image seafoodImage = seafoodIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        Image baconImage = baconIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH); 
        
        JToggleButton cheeseButton = new JToggleButton(new ImageIcon(cheeseImage));
        JToggleButton pineappleButton = new JToggleButton(new ImageIcon(pineappleImage));
        JToggleButton mushroomButton = new JToggleButton(new ImageIcon(mushroomImage));
        JToggleButton hamButton = new JToggleButton(new ImageIcon(hamImage));
        JToggleButton seafoodButton = new JToggleButton(new ImageIcon(seafoodImage));
        JToggleButton baconButton = new JToggleButton(new ImageIcon(baconImage));
        int topRow = cheeseButton.getY();
        int bottomRow = 250;

        cheeseButton.setBounds(0,topRow,250,250);
        pineappleButton.setBounds(cheeseButton.getWidth(),topRow,250,250);
        mushroomButton.setBounds(pineappleButton.getX()+250,topRow,250,250);
        hamButton.setBounds(0,bottomRow,250,250);
        seafoodButton.setBounds(250,bottomRow,250,250);
        baconButton.setBounds(500,bottomRow,250,250);
        
        Color brownColor = new Color(100, 70, 29);
        cheeseButton.setBackground(brownColor);
        pineappleButton.setBackground(brownColor);
        mushroomButton.setBackground(brownColor);
        hamButton.setBackground(brownColor);
        seafoodButton.setBackground(brownColor);
        baconButton.setBackground(brownColor);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 2));
        buttonPanel.setBounds(0,18, 750,500);
        buttonPanel.add(cheeseButton);
        buttonPanel.add(pineappleButton);
        buttonPanel.add(mushroomButton);
        buttonPanel.add(hamButton);
        buttonPanel.add(seafoodButton);
        buttonPanel.add(baconButton);
        emptyLabel.add(buttonPanel);
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {

            ArrayList<String> AListToppings = new ArrayList<>();
            if (cheeseButton.isSelected()) {
                AListToppings.add("Cheese");
            }
            if (pineappleButton.isSelected()) {
                AListToppings.add("Pineapple");
            }
            if (mushroomButton.isSelected()) {
                AListToppings.add("Mushroom");
            }
            if (hamButton.isSelected()) {
                AListToppings.add("Ham");
            }
            if (seafoodButton.isSelected()) {
                AListToppings.add("Seafood");
            }
            if (baconButton.isSelected()) {
                AListToppings.add("Bacon");
            }
            System.out.println("bakepizza");
            bakePizza(doughType, AListToppings);
        });
        nextButton.setBounds(0, 540, WIDTH, 25);
        nextButton.setFont(new Font("Arial", Font.BOLD, 18));
        emptyLabel.add(nextButton);
        frame.repaint();
    }

    private void NextOrder() {
        SwingUtilities.invokeLater(() -> {
            System.out.println("in next order");
            backgroundOrder.setBounds(0, 0, WIDTH, HEIGHT);
            frame.add(backgroundOrder);
            
            generateMenu.setText(Customer.generateMenu());    
            frame.repaint();
        });
    }

    private void bakePizza(String dough, ArrayList<String> topping) {
        if (!topping.isEmpty()) {
            frame.remove(emptyLabel);

            ImageIcon bake = new ImageIcon("img/bakepizza.PNG");
            Image bakeImage = bake.getImage(); 

            JLabel label = new JLabel(bake);
            label.setBounds(0,0, WIDTH, HEIGHT);
            frame.add(label); // add JLabel in BorderLayout,CENTER

            addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent e) {
                    int width = getWidth();
                    int height = getHeight();
                    Image bakePIZZAImage = bakeImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                    ImageIcon bakeIcon = new ImageIcon(bakePIZZAImage);
                    label.setIcon(bakeIcon); // update size JFrame
                }
            });
            
            JButton cookButton = new JButton("Bake Pizza");
            cookButton.setBounds(0, 540, WIDTH, 25);
            cookButton.addActionListener(e -> {
                PizzaValidator pizzaValidator = new PizzaValidator(dough, topping, Customer.Menu_for_check());
                if (!pizzaValidator.validatePizza()) {
                    JOptionPane.showMessageDialog(this, "Incorrect Pizza! Game Over!");
                    System.exit(0); ;// close window
                    correctAns = 0; // reset correctAns = 0
                    
                } else {
                    correctAns++; 
                    if (correctAns == 5) {
                        JOptionPane.showMessageDialog(this, "Congratulations! You got 3 star!");
                    } else {
                        frame.remove(label);
                        NextOrder(); // Generate next order
                        // selectDough(); // Call selectDough() to continue the process
                    }
                }
            });
            label.add(cookButton);
            frame.repaint();            
            } else {
                JOptionPane.showMessageDialog(this, "Please choose at least one topping.");
            }
    }
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            game_cooking game = new game_cooking();
            game.setVisible(true);
        });
    }
}
