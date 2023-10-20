import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class BmiCalculatorApp extends JFrame implements ActionListener, KeyListener {

    private JTextField heightText, weightText, bmiText, resultText, blankText;
    private JLabel heightLabel, weightLabel, bmiLabel, resultLabel, infoLabel;
    private JButton calculateBmiButton, resetButton;
    private JRadioButton cmButton, metersButton;
    private ButtonGroup btnGroup;
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem calculateItem, resetItem, exitItem;

    public BmiCalculatorApp() {
        // BmiCalculator bmiCalculator = new BmiCalculator();
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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
        bmiLabel = new JLabel("Calculated BMI");
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
    }

    private void reset() {
        weightText.setText("");
        heightText.setText("");
        resultText.setText("");
    }

    private void exit() {
        int x = JOptionPane.showConfirmDialog(this, "Are You Sure?", "Close the App", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION)
            System.exit(1);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new BmiCalculatorApp();

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(KeyEvent e) {
        double height;
        double weight;
        double bmiVal;
        String result;

        boolean isMeters = metersButton.isSelected();

        if (e.getSource().equals(weightText)) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                height = Double.parseDouble(heightText.getText());
                weight = Double.parseDouble(weightText.getText());
                bmiVal = BmiCalculator.calculate(height, weight, isMeters);
                bmiLabel.setText("BMI: " + bmiVal);
                result = BmiCalculator.BMItoCategory(bmiVal);
                resultLabel.setText("Result: " + result);
            } else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                reset();
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                exit();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        double height;
        double weight;
        double bmiVal;
        String result;

        boolean isMeters = metersButton.isSelected();

        if (e.getSource().equals(calculateBmiButton) || e.getSource().equals(calculateItem)) {
            height = Double.parseDouble(heightText.getText());
            weight = Double.parseDouble(weightText.getText());
            bmiVal = BmiCalculator.calculate(height, weight, isMeters);
            bmiLabel.setText("BMI: " + bmiVal);
            result = BmiCalculator.BMItoCategory(bmiVal);
            resultLabel.setText("Result: " + result);
        } else if (e.getSource().equals(resetButton) || e.getSource().equals(resetItem))
            reset();
        else
            exit();

    }

}
