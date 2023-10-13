import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Course extends JFrame implements ActionListener {
    private JLabel departmentCode, numericCode;
    private JTextField departmentCodeText, numericCodeText, blankText;
    private JComboBox credits;
    private JButton submitButton, exitButton;

    public Course() {
        setLayout(new GridLayout(4, 2));

        departmentCode = new JLabel("   DEPARTMENT CODE");
        numericCode = new JLabel("   NUMERICAL CODE");

        departmentCodeText = new JTextField();
        numericCodeText = new JTextField();
        blankText = new JTextField();

        blankText.setEditable(false);

        String[] creditsOptions = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        credits = new JComboBox(creditsOptions);

        submitButton = new JButton("submit");
        exitButton = new JButton("exit");

        add(departmentCode);
        add(departmentCodeText);
        add(numericCode);
        add(numericCodeText);
        add(credits);
        add(blankText);
        add(submitButton);
        add(exitButton);

        credits.addActionListener(this);
        submitButton.addActionListener(this);
        exitButton.addActionListener(this);

        setSize(500, 600);
        setVisible(true);
        setLocation(250, 150);
        setTitle("Course Information");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Course();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource().equals(submitButton)) {
            String deptCode = departmentCodeText.getText();
            String numCode = numericCodeText.getText();
            String creds = credits.getSelectedItem().toString();

            if (deptCode.length() > 5) {
                JOptionPane.showMessageDialog(this, "Invalid Department Code Length!", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }

            try {
                int numCode_ = Integer.parseInt(numCode);
                if (numCode_ < 100 || numCode_ > 400) {
                    JOptionPane.showMessageDialog(this, "Invalid Numerical Code!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                }
            }

            catch (NumberFormatException e_) {
                JOptionPane.showMessageDialog(this, "Invalid!", "ERROR", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }

            JOptionPane.showMessageDialog(this, "Course Info\nDepartment Code: " + deptCode + "\nNumerical Code: "
                    + numCode + "\nCredits: " + creds);
        }

        else if (e.getSource().equals(exitButton)) {
            System.exit(1);
        }
    }
}
