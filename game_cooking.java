import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class game_cooking extends JFrame implements ActionListener {
    private JFrame frame ;
    private JLabel titleLabel;
    private JButton buttonStart;
    private JLabel backgroundLabel;
    private JLabel backgroundOrder ; 
    private JLabel cookingArea;
    private Container container ;
    private GameController controller;
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
    //     setContentPane(backgroundLabel);
    //     //setContentPane(loco);
    //     addComponentListener(new ComponentAdapter() {
    //         @Override
    //         public void componentResized(ComponentEvent e) {
    //             Image scaledImage = imageIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
    //             ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
    //             backgroundLabel.setIcon(scaledImageIcon);
    //         }
    //     });

    //     //start button
    //     ImageIcon button = new ImageIcon("img/start.PNG");

    //     Image scaledButton = button.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
    //      button = new ImageIcon(scaledButton);
    //     butt = new JButton(button);
        
    //     butt.setBorderPainted(false);
    //     butt.setContentAreaFilled(false);
    //     butt.setFocusPainted(false);
           
    //     getContentPane().setLayout(new BorderLayout());
    //     getContentPane().add(butt, BorderLayout.SOUTH);
        
    //     addComponentListener(new ComponentAdapter() {
    //         public void componentResized(ComponentEvent e) {
    //             int size = Math.min(getWidth(), getHeight());
    //             butt.setPreferredSize(new Dimension(size, size));
    //             revalidate();
    //         }
    //     });

    //     JScrollPane scrollPane = new JScrollPane(cookingArea);
    //     scrollPane.setAlignmentY(Component.CENTER_ALIGNMENT); 
    //     add(scrollPane, BorderLayout.CENTER);

    //     controller = new GameController(this);
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
                startGame(); // start countdown
            }
        });
        
        howto.setVisible(true);
    }

    public void startGame() {
        // hide "<Start>"
        // System.out.println("start");
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
                    //ActionListener
                    JLabel generateMenu = new JLabel(Customer.generateMenu());
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
        getContentPane().removeAll();
        revalidate();
        repaint();

        
        JLabel chooseDoughLabel = new JLabel("select dough ");
        chooseDoughLabel.setFont(new Font("Arial", Font.BOLD, 30));
        add(chooseDoughLabel, BorderLayout.NORTH);


        ImageIcon thickIcon = new ImageIcon("img/Thick.PNG");
        JButton thickButton = new JButton(thickIcon);
        thickButton.setText("Thick dough");
        thickButton.setForeground(Color.white);

        ImageIcon thinIcon = new ImageIcon("img/Thin.PNG");
        JButton thinButton = new JButton(thinIcon);
        thinButton.setText("Thin dough");
        thinButton.setForeground(Color.white);

        Color brownColor = new Color(100, 70, 29);
        thickButton.setBackground(brownColor);
        thinButton.setBackground(brownColor);

        // ActionListener "thick Dough"
        thickButton.addActionListener(e -> {
            
            selectTopping("Thick dough");
        });

        // ActionListener "thin Dough"
        thinButton.addActionListener(e -> {
            
            selectTopping("Thin dough");
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(thickButton);
        buttonPanel.add(thinButton);
        add(buttonPanel, BorderLayout.CENTER);

        revalidate();
    }


    private void selectTopping(String doughType) {
        
        getContentPane().removeAll();
        revalidate();
        repaint();

        
        JLabel chooseToppingLabel = new JLabel("choose Toppings");
        chooseToppingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(chooseToppingLabel, BorderLayout.NORTH);

        ImageIcon cheeseIcon = new ImageIcon("img/cheese.png");
        ImageIcon pineappleIcon = new ImageIcon("img/pineapple.png");
        ImageIcon mushroomIcon = new ImageIcon("img/mushroom.png");
        ImageIcon hamIcon = new ImageIcon("img/ham.png");
        ImageIcon seafoodIcon = new ImageIcon("img/seafood.png");
        ImageIcon baconIcon = new ImageIcon("img/bacon.png");

        Image cheeseImage = cheeseIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        Image pineappleImage = pineappleIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        Image mushroomImage = mushroomIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        Image hamImage = hamIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        Image seafoodImage = seafoodIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        Image baconImage = baconIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH); 
        
        JToggleButton cheeseButton = new JToggleButton(new ImageIcon(cheeseImage));
        JToggleButton pineappleButton = new JToggleButton(new ImageIcon(pineappleImage));
        JToggleButton mushroomButton = new JToggleButton(new ImageIcon(mushroomImage));
        JToggleButton hamButton = new JToggleButton(new ImageIcon(hamImage));
        JToggleButton seafoodButton = new JToggleButton(new ImageIcon(seafoodImage));
        JToggleButton baconButton = new JToggleButton(new ImageIcon(baconImage));

        Color brownColor = new Color(100, 70, 29);
        cheeseButton.setBackground(brownColor);
        pineappleButton.setBackground(brownColor);
        mushroomButton.setBackground(brownColor);
        hamButton.setBackground(brownColor);
        seafoodButton.setBackground(brownColor);
        baconButton.setBackground(brownColor);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3));
        buttonPanel.add(cheeseButton);
        buttonPanel.add(pineappleButton);
        buttonPanel.add(mushroomButton);
        buttonPanel.add(hamButton);
        buttonPanel.add(seafoodButton);
        buttonPanel.add(baconButton);
        add(buttonPanel, BorderLayout.CENTER);


        // ImageIcon button = new ImageIcon("img/NEXT.PNG");

        //             Image scaledButton = button.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        //             button = new ImageIcon(scaledButton);
        //             JButton nextButton = new JButton(button);
                    
        //             nextButton.setBorderPainted(false);
        //             nextButton.setContentAreaFilled(false);
        //             nextButton.setFocusPainted(false);
        //             nextButton.addActionListener(e -> {
        //                 ArrayList<String> AListToppings = new ArrayList<>();
        //     if (cheeseButton.isSelected()) {
        //         AListToppings.add("Cheese");
        //     }
        //     if (pineappleButton.isSelected()) {
        //         AListToppings.add("Pineapple");
        //     }
        //     if (mushroomButton.isSelected()) {
        //         AListToppings.add("Mushroom");
        //     }
        //     if (hamButton.isSelected()) {
        //         AListToppings.add("Ham");
        //     }
        //     if (seafoodButton.isSelected()) {
        //         AListToppings.add("Seafood");
        //     }
        //     if (baconButton.isSelected()) {
        //         AListToppings.add("Bacon");
        //     }
        //     bakePizza(doughType, AListToppings);
        //                 });
                
        //             add(nextButton, BorderLayout.SOUTH);

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
            bakePizza(doughType, AListToppings);
        });
        add(nextButton, BorderLayout.SOUTH);

        revalidate();
    }

    private void NextOrder() {
        SwingUtilities.invokeLater(() -> {
            getContentPane().removeAll();
            revalidate();
            repaint();
     
            JLabel customerOrder = new JLabel();
            customerOrder.setText(Customer.generateMenu());
            customerOrder.setFont(new Font("Arial", Font.PLAIN, 42));
            add(customerOrder,BorderLayout.NORTH);       
            JScrollPane scrollPane = new JScrollPane(customerOrder);
            scrollPane.setAlignmentY(Component.CENTER_ALIGNMENT); 
            add(scrollPane, BorderLayout.CENTER);
            // "Next" bttn
            JButton nextButton = new JButton("Next");
            nextButton.addActionListener(e -> {
                selectDough();
            });
            add(nextButton, BorderLayout.SOUTH);
            revalidate();
        });
    }

    private void bakePizza(String dough, ArrayList<String> topping) {
        if (!topping.isEmpty()) {
            getContentPane().removeAll();
            revalidate();
            repaint();
    
            // JLabel bakePizzaLabel = new JLabel("Bake Pizza");
            // bakePizzaLabel.setFont(new Font("Arial", Font.BOLD, 18));
            // add(bakePizzaLabel, BorderLayout.NORTH);

            ImageIcon bake = new ImageIcon("img/bakepizza.PNG");
            Image bakeImage = bake.getImage(); 

            JLabel label = new JLabel(bake);
            add(label, BorderLayout.CENTER); // add JLabel in BorderLayout,CENTER

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
            cookButton.addActionListener(e -> {
                PizzaValidator pizzaValidator = new PizzaValidator(dough, topping, Customer.Menu_for_check());
                if (!pizzaValidator.validatePizza()) {
                    JOptionPane.showMessageDialog(this, "Incorrect Pizza! Game Over!");
                    controller.restartGame(); // Restart the game if pizza is incorrect
                    correctAns = 0; // reset correctAns = 0
                    
                } else {
                    correctAns++; 
                    if (correctAns == 5) {
                        JOptionPane.showMessageDialog(this, "Congratulations! You got 3 star!");
                    } else {
                        NextOrder(); // Generate next order
                        selectDough(); // Call selectDough() to continue the process
                    }
                }
            });
            add(cookButton, BorderLayout.SOUTH);

            revalidate();
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
