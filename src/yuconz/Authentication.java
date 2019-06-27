package yuconz;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 
 * @author rm631, ol61, mb859, ra466
 */
public class Authentication extends JFrame {

    Yuconz system;
    
    /**
     * Constructor for objects of class Authorisation; calls initialize()
     */
    public Authentication() {
        system = new Yuconz();
        initialize();
    }

    /**
     * TODO: Make the UI look better, possibly with a layout manager
     * Creates the GUI for authenticating the user
     */
    public void initialize() {        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the instance
        setLayout(null);

        setSize(250,250); // Size of the window

        // initialising fields and buttons

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(15, 42, 76, 16);
        add(userLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(90, 39, 105, 22);
        add(usernameField);
        usernameField.setColumns(10);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(15, 77, 66, 16);
        add(passLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(90, 74, 105, 22);
        add(passwordField);

        /* "A particular individual might have different levels of.. 
         * .. authorization available to them when they log in."
         * This option will be avaliable via a combobox
         * Here the user can chose which auth level they wish to
         * use to access the system, this will be verified by login()
         * Stage 4 - "User" removed from roles
         */
        String[] roles = { "Employee", "HR Employee", "Manager", "Director", "Reviewer" };

        JComboBox roleBox = new JComboBox(roles);
        roleBox.setBounds(122, 130, 97, 25);
        roleBox.setSelectedIndex(0); // Set defualt value
        add(roleBox);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(122, 158, 97, 25);
        loginBtn.addActionListener((ActionEvent e) -> {
            if(system.login(usernameField.getText(), passwordField.getPassword(), roleBox.getSelectedItem().toString())) {
                authenticationSuccessful();
            } else {
                authenticationFailed();
            }
        });
        add(loginBtn);
        
        setVisible(true);
    } 
     
    /**
     * Disposes of the Authentication JFrame window, and creates
     * an instance of Home
     */
    public void authenticationSuccessful() {
        this.dispose();
        Home home = new Home();
    }
    
    /**
     * Creates a JPanel to display an error on incorrect login credentials
     */
    public void authenticationFailed() {
        final JPanel panel = new JPanel();

        JOptionPane.showMessageDialog(panel, "Login details are incorrect", "Authentication Failure", JOptionPane.ERROR_MESSAGE);
    }
}