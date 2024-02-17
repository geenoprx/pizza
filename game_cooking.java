import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class game_cooking extends JFrame implements ActionListener {
    private JLabel titleLabel;
    private JButton startButton;
    private JTextArea cookingArea;
    private GameController controller;
    public static String save_select = "";
    private int correctAns = 0; 

    public game_cooking() {
        setTitle("Cooking Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        titleLabel = new JLabel("Welcome to Pizza Pick Me Game!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);
        // setResizable(false);

        startButton = new JButton("<START>");
        startButton.addActionListener(this);
        add(startButton, BorderLayout.SOUTH);

        cookingArea = new JTextArea();
        cookingArea.setFont(new Font("Arial", Font.BOLD, 48)); 
        cookingArea.setAlignmentX(Component.CENTER_ALIGNMENT); 
        
        cookingArea.setEditable(false); 
        cookingArea.setOpaque(false); 
        cookingArea.setAlignmentX(Component.CENTER_ALIGNMENT); 

        JScrollPane scrollPane = new JScrollPane(cookingArea);
        scrollPane.setAlignmentY(Component.CENTER_ALIGNMENT); 
        add(scrollPane, BorderLayout.CENTER);

        controller = new GameController(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
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
        cookingArea.setText(""); // Clear text
        
        // hide "<Start>"
        startButton.setVisible(false);
    
        String[] countdown = {"3", "2", "1", "Start!"," "};
    
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < countdown.length; i++) {
                    final int index = i;
                    try {
                        Thread.sleep(1000); // - 1 second
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    SwingUtilities.invokeLater(() -> {
                        cookingArea.setText(countdown[index]); // Set text
                    });
                }
                
                SwingUtilities.invokeLater(() -> {

                    //Clear
                    getContentPane().removeAll();
                    revalidate();
                    repaint();

                    // show order
                    // cookingArea.setText(MenuGenerator.generateMenu());
                    JLabel Order = new JLabel();
                    Order.setText(Customer.generateMenu());
                    System.out.println(Customer.Menu_for_check());
                    Order.setFont(new Font("Arial", Font.PLAIN, 42));

                    cookingArea.add(Order);     
                    JScrollPane scrollPane = new JScrollPane(Order);
                    scrollPane.setAlignmentY(Component.CENTER_ALIGNMENT); 
                    add(scrollPane, BorderLayout.CENTER);
                    
                    // "Next" bttn
                    JButton nextButton = new JButton("Next");
                    //ActionListener
                    nextButton.addActionListener(e -> {
                        selectDough();;
                    });
                    add(nextButton, BorderLayout.SOUTH);
                    
                    
                    revalidate();
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
        chooseDoughLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(chooseDoughLabel, BorderLayout.NORTH);


        ImageIcon thickIcon = new ImageIcon("img/Thick.PNG");
        JButton thickButton = new JButton(thickIcon);
        thickButton.setText("Thick dough");

        ImageIcon thinIcon = new ImageIcon("img/Thin.PNG");
        JButton thinButton = new JButton(thinIcon);
        thinButton.setText("Thin dough");

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
    
            JLabel bakePizzaLabel = new JLabel("Bake Pizza");
            bakePizzaLabel.setFont(new Font("Arial", Font.BOLD, 18));
            add(bakePizzaLabel, BorderLayout.NORTH);

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