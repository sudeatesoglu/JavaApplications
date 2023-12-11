package clientToserverApp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class BmiCalculatorApp extends JFrame implements ActionListener, KeyListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField heightText, weightText, bmiText, resultText, blankText;
    private JLabel heightLabel, weightLabel, bmiLabel, resultLabel, infoLabel;
    private JButton calculateBmiButton, resetButton;
    private JRadioButton cmButton, metersButton;
    private ButtonGroup btnGroup;
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem calculateItem, resetItem, exitItem;
    private BmiClient client;
    DecimalFormat df = new DecimalFormat("##,###.####");

    public BmiCalculatorApp() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // initialize GUI components
        heightText = new JTextField();
        weightText = new JTextField();
        bmiText = new JTextField();
        bmiText.setEditable(false);
        resultText = new JTextField();
        resultText.setEditable(false);
        blankText = new JTextField();
        blankText.setEditable(false);

        heightLabel = new JLabel("Height");
        weightLabel = new JLabel("Weight");
        bmiLabel = new JLabel("Approximate BMI");
        resultLabel = new JLabel("Result:");
        infoLabel = new JLabel("Height option:");

        calculateBmiButton = new JButton("Calculate");
        resetButton = new JButton("Reset");

        cmButton = new JRadioButton("cm");
        metersButton = new JRadioButton("meters");

        btnGroup = new ButtonGroup();

        menuBar = new JMenuBar();
        menu = new JMenu("File");
        menu.setForeground(Color.RED);

        calculateItem = new JMenuItem("Calculate");
        resetItem = new JMenuItem("Reset");
        exitItem = new JMenuItem("Exit");

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.setSize(280, 230);
        panel.setLocation(300, 250);

        menu.add(calculateItem);
        menu.add(resetItem);
        menu.add(exitItem);
        menuBar.add(menu);

        panel.add(menuBar);
        panel.add(blankText);
        panel.add(heightLabel);
        panel.add(heightText);
        panel.add(weightLabel);
        panel.add(weightText);
        panel.add(bmiLabel);
        panel.add(bmiText);
        panel.add(infoLabel);
        panel.add(cmButton);
        cmButton.setSelected(true);
        panel.add(metersButton);

        btnGroup.add(cmButton);
        btnGroup.add(metersButton);

        frame = new JFrame("BMI Calculator");
        frame.setSize(280, 230);
        frame.setLocation(300, 250);
        frame.setLayout(new BorderLayout());

        frame.add(panel, BorderLayout.NORTH);
        frame.add(resultLabel, BorderLayout.CENTER);
        frame.add(resultText, BorderLayout.WEST);
        frame.add(calculateBmiButton, BorderLayout.SOUTH);
        frame.setVisible(true);

        weightText.addKeyListener(this);

        calculateBmiButton.addActionListener(this);
        resetButton.addActionListener(this);

        calculateItem.addActionListener(this);
        resetItem.addActionListener(this);
        exitItem.addActionListener(this);

        // initialize the bmiclient for server communication
        client = new BmiClient(this);
    }

    // reset the input fields
    private void reset() {
        weightText.setText("");
        heightText.setText("");
        resultText.setText("");
    }

    // exit from the app
    private void exit() {
        int x = JOptionPane.showConfirmDialog(this, "Are You Sure?", "Close the App", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION)
            System.exit(1);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    // handle key events
    @Override
    public void keyPressed(KeyEvent e) {
        double height;
        double weight;

        boolean isMeters = metersButton.isSelected();

        if (e.getSource().equals(weightText)) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    height = Double.parseDouble(heightText.getText());
                    weight = Double.parseDouble(weightText.getText());
                    sendRequestToServer(height, weight, isMeters);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Invalid input. Please enter valid numeric values for height and weight.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                reset();
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                client.close();
                exit();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    // send request to the server
    private void sendRequestToServer(double height, double weight, boolean isMeters) {
        client.sendRequest(height, weight, isMeters);
    }

    // update the result label based on the server response
    public void updateResultLabel(String response) {
        try {
            String[] parts = response.split(",");
            if (parts.length >= 2) {
                String bmiString = parts[0].replaceAll("[^0-9.]", "").trim();
                bmiString = bmiString.replace(',', '.');
                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setDecimalSeparator('.');
                df.setDecimalFormatSymbols(symbols);

                double bmi = Double.parseDouble(bmiString);
                String formattedBmi = df.format(bmi);
                bmiText.setText(formattedBmi);

                String result = parts[1].trim();
                resultText.setText(result);
            } else {
                System.err.println("Invalid format: " + response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    // handle action events
    @Override
    public void actionPerformed(ActionEvent e) {
        double height;
        double weight;

        boolean isMeters = metersButton.isSelected();

        if (e.getSource().equals(calculateBmiButton) || e.getSource().equals(calculateItem)) {
            try {
                // parse height and weight and send request to the serve
                height = Double.parseDouble(heightText.getText());
                weight = Double.parseDouble(weightText.getText());
                sendRequestToServer(height, weight, isMeters);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Invalid input. Please enter valid numeric values for height and weight.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource().equals(resetButton) || e.getSource().equals(resetItem))
            reset();
        else {
            // close the client and exit the app on exit button from red file label or esc
            // key
            client.close();
            exit();
        }
    }

    // start the app
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SwingUtilities.invokeLater(() -> new BmiCalculatorApp());
    }

}