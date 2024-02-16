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


    public game_cooking() {
        setTitle("Cooking Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        titleLabel = new JLabel("Welcome to Cooking Game!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);
        // setResizable(false);

        startButton = new JButton("<START>");
        startButton.addActionListener(this);
        add(startButton, BorderLayout.SOUTH);

        cookingArea = new JTextArea();
        cookingArea.setFont(new Font("Arial", Font.BOLD, 48)); 
        cookingArea.setAlignmentX(Component.CENTER_ALIGNMENT); 
        // cookingArea.setText("Geeno");
        // cookingArea.setOpaque(true);

        // cookingArea.setLayout(new BorderLayout());
        // cookingArea.add(cookingArea);
        // frame.add(cookingArea);
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

    void startGame() {
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


        JButton thickButton = new JButton("Thick dough");
        JButton thinButton = new JButton("Thin dough");

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

        
        JCheckBox cheeseCheckbox = new JCheckBox("Cheese");
        JCheckBox pineappleCheckbox = new JCheckBox("Pineapple");
        JCheckBox mushroomCheckbox = new JCheckBox("Mushroom");
        JCheckBox hamCheckbox = new JCheckBox("Ham");
        JCheckBox seafoodCheckbox = new JCheckBox("Seafood");
        JCheckBox baconCheckbox = new JCheckBox("Bacon");

        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new GridLayout(5, 1));
        checkboxPanel.add(cheeseCheckbox);
        checkboxPanel.add(pineappleCheckbox);
        checkboxPanel.add(mushroomCheckbox);
        checkboxPanel.add(hamCheckbox);
        checkboxPanel.add(seafoodCheckbox);
        checkboxPanel.add(baconCheckbox);
        add(checkboxPanel, BorderLayout.CENTER);

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {

            ArrayList<String> AListToppings = new ArrayList<>();
            if (cheeseCheckbox.isSelected()) {
                AListToppings.add("Cheese");
            }
            if (pineappleCheckbox.isSelected()) {
                AListToppings.add("Pineapple");
            }
            if (mushroomCheckbox.isSelected()) {
                AListToppings.add("Mushroom");
            }
            if (hamCheckbox.isSelected()) {
                AListToppings.add("Ham");
            }
            if (seafoodCheckbox.isSelected()) {
                AListToppings.add("Seafood");
            }
            if (baconCheckbox.isSelected()) {
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
    
            JTextArea selectedIngredientsTextArea = new JTextArea();
            selectedIngredientsTextArea.append("Dough: " + dough + "\n");
            selectedIngredientsTextArea.append("Toppings: ");
            for (String toppings : topping) {
                selectedIngredientsTextArea.append(toppings + " ");
            }
            selectedIngredientsTextArea.setEditable(false);
            add(selectedIngredientsTextArea, BorderLayout.CENTER);
    
            JButton cookButton = new JButton("Bake Pizza");
            cookButton.addActionListener(e -> {
                PizzaValidator pizzaValidator = new PizzaValidator(dough, topping, Customer.Menu_for_check());
                if (!pizzaValidator.validatePizza()) {
                    JOptionPane.showMessageDialog(this, "Incorrect Pizza! Game Over!");
                    controller.restartGame(); // Restart the game if pizza is incorrect
                } else {
                    NextOrder(); // Generate next order
                    selectDough(); // Call selectDough() to continue the process
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
