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
        HowtoPlay menuSelectionGUI = new HowtoPlay();
        menuSelectionGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        menuSelectionGUI.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                startGame(); // start countdown
            }
        });
        
        menuSelectionGUI.setVisible(true);
    }

    void startGame() {
        cookingArea.setText(""); // Clear text
        
        // hide "Start"
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
                    getContentPane().removeAll();
                    revalidate();
                    repaint();
                    // show order
                    // cookingArea.setText(MenuGenerator.generateMenu());
                    JLabel customerOrder = new JLabel();
                    customerOrder.setText(MenuGenerator.generateMenu());
                    System.out.println(MenuGenerator.getLastGeneratedMenu());
                    customerOrder.setFont(new Font("Arial", Font.PLAIN, 42));
                    cookingArea.add(customerOrder);     
                    JScrollPane scrollPane = new JScrollPane(customerOrder);
                    scrollPane.setAlignmentY(Component.CENTER_ALIGNMENT); 
                    add(scrollPane, BorderLayout.CENTER);
                    // cookingArea.setFont(new Font("Arial", Font.PLAIN, 42)); 
                    
                    // "Next" bttn
                    JButton nextButton = new JButton("Next");
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

        
        JButton thickDoughButton = new JButton("Thick dough");
        JButton thinDoughButton = new JButton("Thin dough");

        // ActionListener "thick Dough"
        thickDoughButton.addActionListener(e -> {
            
            selectTopping("Thick dough");
        });

        // ActionListener "thin Dough"
        thinDoughButton.addActionListener(e -> {
            
            selectTopping("Thin dough");
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(thickDoughButton);
        buttonPanel.add(thinDoughButton);
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
            ArrayList<String> selectedToppings = new ArrayList<>();
            if (cheeseCheckbox.isSelected()) {
                selectedToppings.add("Cheese");
            }
            if (pineappleCheckbox.isSelected()) {
                selectedToppings.add("Pineapple");
            }
            if (mushroomCheckbox.isSelected()) {
                selectedToppings.add("Mushroom");
            }
            if (hamCheckbox.isSelected()) {
                selectedToppings.add("Ham");
            }
            if (seafoodCheckbox.isSelected()) {
                selectedToppings.add("Seafood");
            }
            if (baconCheckbox.isSelected()) {
                selectedToppings.add("Bacon");
            }
            bakePizza(doughType, selectedToppings);
        });
        add(nextButton, BorderLayout.SOUTH);

        revalidate();
    }

    private void generateNextOrder() {
        SwingUtilities.invokeLater(() -> {
            getContentPane().removeAll();
            revalidate();
            repaint();
     
            // cookingArea.setText(MenuGenerator.generateMenu());
            // cookingArea.setFont((new Font("Arial", Font.PLAIN, 42)));
            JLabel customerOrder = new JLabel();
            customerOrder.setText(MenuGenerator.generateMenu());
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

    private void bakePizza(String doughType, ArrayList<String> toppingTypes) {
        if (!toppingTypes.isEmpty()) {
            getContentPane().removeAll();
            revalidate();
            repaint();
    
            JLabel bakePizzaLabel = new JLabel("Bake Pizza");
            bakePizzaLabel.setFont(new Font("Arial", Font.BOLD, 18));
            add(bakePizzaLabel, BorderLayout.NORTH);

            save_select = doughType + " ";
    
            JTextArea selectedIngredientsTextArea = new JTextArea();
            selectedIngredientsTextArea.append("Dough: " + doughType + "\n");
            selectedIngredientsTextArea.append("Toppings: ");
            for (String topping : toppingTypes) {
                selectedIngredientsTextArea.append(topping + " ");
                save_select += toppingTypes + " ";
            }
            System.out.println(save_select);
            selectedIngredientsTextArea.setEditable(false);
            add(selectedIngredientsTextArea, BorderLayout.CENTER);
    
            JButton cookButton = new JButton("Bake Pizza");
            cookButton.addActionListener(e -> {
                PizzaValidator pizzaValidator = new PizzaValidator(doughType, toppingTypes, MenuGenerator.getLastGeneratedMenu());
                if (!pizzaValidator.validatePizza()) {
                    JOptionPane.showMessageDialog(this, "Incorrect Pizza! Game Over!");
                    controller.restartGame(); // Restart the game if pizza is incorrect
                } else {
                    generateNextOrder(); // Generate next order
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
