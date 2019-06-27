package yuconz;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;


/**
 *
 * @author rm631 
 */
public class Home extends JFrame {
    
    Yuconz yuconz;
    
    /**
     * Constructor for objects of class Home; calls initialize();
     */
    public Home() {
        yuconz = new Yuconz();
        initialize();
    }
    
    /**
     * Creates the UI; currently just a label and a button to
     * dispose of the JFrame
     */
    public void initialize() {        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the instance
        setLayout(null);

        setSize(465,315);
        
        /** Personal Details **/
        
        JButton createPersonal = new JButton("<html>Create<br>Personal<br>Details</html>");
        createPersonal.setBounds(25, 25, 100, 100);
        
        createPersonal.addActionListener((ActionEvent e) -> {
            try {
                if(yuconz.authoriseCreatePersonal()) {
                    personalDetails(null, false);
                } else {
                    JPanel panel = new JPanel();

                    JOptionPane.showMessageDialog(panel, "You are not authorised to do this!", "Authorisation Failure", JOptionPane.ERROR_MESSAGE);
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });
        
        add(createPersonal);
        
        /**
         * Whether a modify button is necessary or not is definitely a fair question
         * .. since the permissions for read and modify seem to be the same
         * ie. to whom it relates and the HR department. But for now, it seems fine
         */
        JButton modifyPersonal = new JButton("<html>Modify<br>Personal<br>Details</html>");
        modifyPersonal.setBounds(125, 25, 100, 100);
        
        modifyPersonal.addActionListener((ActionEvent e) -> {
            viewExistingPersonalDetails(false);
        });
        
        add(modifyPersonal);
        
        JButton readPersonal = new JButton("<html>Read<br>Personal<br>Details</html>");
        readPersonal.setBounds(225, 25, 100, 100);
        
        readPersonal.addActionListener((ActionEvent e) -> {
            viewExistingPersonalDetails(true);
        });
        
        add(readPersonal);
        
        /** 
         * Reviews 
         * Display buttons as necessary ie. amend probably only need to be visible to reviewers etc.
         * if logged in as HR then display the assign reviews button
         **/
        
        JButton createReview = new JButton("<html>Create<br>Review</html>");
        createReview.setBounds(25, 125, 100, 100);
        
        createReview.addActionListener((ActionEvent e) -> {
            try {
                // check auth, then either allow create or show error
                if(yuconz.authoriseCreateReview()) {
                    review(null, false);
                } else {
                    JPanel panel = new JPanel();

                    JOptionPane.showMessageDialog(panel, "Cannot create review!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });
        
        add(createReview);
        
        JButton modifyReview = new JButton("<html>Modify<br>Review</html>");
        modifyReview.setBounds(125, 125, 100, 100);
        
        modifyReview.addActionListener((ActionEvent e) -> {
            // check auth, then either allow create or show error
            ArrayList<String> auth = yuconz.authoriseModifyReview();
            if(auth.size() > 1) {
                viewExistingReviews(auth, false);
            } else if(auth.size() == 1) {
                System.out.println(auth.get(0));
                Review review = yuconz.getReviewById(auth.get(0));
                try {
                    review(review, false);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            } else {
                JPanel panel = new JPanel();

                JOptionPane.showMessageDialog(panel, "No reviews avaliable to modify at this time!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        add(modifyReview);
        
        JButton readReview = new JButton("<html>Read<br>Past<br>Reviews</html>");
        readReview.setBounds(225, 125, 100, 100);
        
        readReview.addActionListener((ActionEvent e) -> {
            // check auth, then either allow create or show error
            ArrayList<String> auth = yuconz.authoriseReadPastReview();
            if(auth.size() > 1) {
                viewExistingReviews(auth, true);
            } else if(auth.size() == 1){
                Review review = yuconz.getReviewById(auth.get(0));
                try {
                    review(review, false);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            } else {
                JPanel panel = new JPanel();

                JOptionPane.showMessageDialog(panel, "No reviews avaliable to read at this time!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        add(readReview);
        
        JButton assignReviewers = new JButton("<html>Assign<br>Reviewers</html>");
        assignReviewers.setBounds(325, 125, 100, 100);
        
        assignReviewers.addActionListener((ActionEvent e) -> {
            // check auth, then either allow create or show error
            assignReviewers();
        });
        
        add(assignReviewers);
        
        /** Logout Button **/
      
        JButton logoutBtn = new JButton("Log Out");
        logoutBtn.setBounds(325, 225, 100, 25);
        
        logoutBtn.addActionListener((ActionEvent e) -> {
            logout();
        });
        
        add(logoutBtn);
        
        setVisible(true);
    }
    
    /**
     * 
     * @param pd null if creating, an object if trying to view an existing
     * @param readOnly if true, set fields to read only
     * @throws ParseException 
     */
    private void personalDetails(PersonalDetails pd, boolean readOnly) throws ParseException {
        ArrayList<JComponent> fields = new ArrayList<>();
        ArrayList<JComponent> formattedFields = new ArrayList<>();
        JFrame personal = new JFrame(); 

        personal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        personal.setLayout(null);

        personal.setSize(500,500); 

        /** Centred title and staff no **/

        JLabel personalLab = new JLabel("Personal Details");
        personalLab.setBounds(190, 10, 100, 50);
        personal.add(personalLab);
        
        JLabel staffNoLab = new JLabel("Staff No:");
        staffNoLab.setBounds(160, 30, 100, 50);
        personal.add(staffNoLab);
        
        MaskFormatter staffNoMask = new MaskFormatter("LLL###");
        JFormattedTextField staffNoField = new JFormattedTextField(staffNoMask);
        staffNoField.setBounds(220, 50, 105, 22);
        if(pd != null) { staffNoField.setText(pd.getStaffNo()); }
        if(readOnly) { staffNoField.setEditable(false); }
        formattedFields.add(staffNoField);
        personal.add(staffNoField);
        
        /** Left aligned labels and fields **/

        /** surname **/
        JLabel surnameLab = new JLabel("Surname:");
        surnameLab.setBounds(80, 80, 100, 50);
        personal.add(surnameLab);
        
        JTextField surnameField = new JTextField();
        surnameField.setBounds(220, 95, 105, 22);
        if(pd != null) { surnameField.setText(pd.getSurname()); }
        if(readOnly) { surnameField.setEditable(false); }
        fields.add(surnameField);
        personal.add(surnameField);

        /** name **/
        JLabel nameLab = new JLabel("Name:");
        nameLab.setBounds(80, 100, 100, 50);
        personal.add(nameLab);

        JTextField nameField = new JTextField();
        nameField.setBounds(220, 115, 105, 22);
        if(pd != null) { nameField.setText(pd.getName()); }
        if(readOnly) { nameField.setEditable(false); }
        fields.add(nameField);
        personal.add(nameField);

        /** Date of Birth **/
        JLabel doBLab = new JLabel("Date of Birth:");
        doBLab.setBounds(80, 120, 100, 50);
        personal.add(doBLab);

        MaskFormatter dateMask = new MaskFormatter("####/##/##");
        dateMask.setValidCharacters("0123456789");
        JFormattedTextField doBField = new JFormattedTextField(dateMask);
        doBField.setBounds(220, 135, 105, 22);
        if(pd != null) { doBField.setText(pd.getDoB()); }
        if(readOnly) { doBField.setEditable(false); }
        formattedFields.add(doBField);
        personal.add(doBField);


        /** Address label + line 1 & 2 **/
        JLabel addressLab = new JLabel("Address:");
        addressLab.setBounds(80, 140, 100, 50);
        personal.add(addressLab);

        JTextField addressLine1Field = new JTextField();
        addressLine1Field.setBounds(220, 155, 105, 22);
        if(pd != null) { addressLine1Field.setText(pd.getAddressLine1()); }
        if(readOnly) { addressLine1Field.setEditable(false); }
        fields.add(addressLine1Field);
        personal.add(addressLine1Field);

        JTextField addressLine2Field = new JTextField();
        addressLine2Field.setBounds(220, 175, 105, 22);
        if(pd != null) { addressLine2Field.setText(pd.getAddressLine2()); }
        if(readOnly) { addressLine2Field.setEditable(false); }
        fields.add(addressLine2Field);
        personal.add(addressLine2Field);

        /** Town/city **/
        JLabel cityLab = new JLabel("Town/City:");
        cityLab.setBounds(80, 180, 100, 50);
        personal.add(cityLab);

        JTextField cityField = new JTextField();
        cityField.setBounds(220, 195, 105, 22);
        if(pd != null) { cityField.setText(pd.getCity()); }
        if(readOnly) { cityField.setEditable(false); }
        fields.add(cityField);
        personal.add(cityField);

        /** County **/
        JLabel countyLab = new JLabel("County:");
        countyLab.setBounds(80, 200, 100, 50);
        personal.add(countyLab);

        JTextField countyField = new JTextField();
        countyField.setBounds(220, 215, 105, 22);
        if(pd != null) { countyField.setText(pd.getCounty()); }
        if(readOnly) { countyField.setEditable(false); }
        fields.add(countyField);
        personal.add(countyField);


        /** Postcode **/
        JLabel postcodeLab = new JLabel("Postcode:");
        postcodeLab.setBounds(80, 220, 100, 50);
        personal.add(postcodeLab);

        MaskFormatter postcodeMask = new MaskFormatter("*******");
        postcodeMask.setValidCharacters("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ");
        JFormattedTextField postcodeField = new JFormattedTextField(postcodeMask);
        postcodeField.setBounds(220, 235, 105, 22);
        if(pd != null) { postcodeField.setText(pd.getPostcode()); }
        if(readOnly) { postcodeField.setEditable(false); }
        formattedFields.add(postcodeField);
        personal.add(postcodeField);

        /** Telephone No. **/
        JLabel teleNoLab = new JLabel("Tele No.:");
        teleNoLab.setBounds(80, 240, 100, 50);
        personal.add(teleNoLab);

        MaskFormatter phoneNoMask = new MaskFormatter("##### ######"); // the mask for teleNo.s (FIND SOMEWHERE BETTER TO PUT THIS)
        JFormattedTextField teleNoField = new JFormattedTextField(phoneNoMask);
        teleNoField.setBounds(220, 255, 105, 22);
        if(pd != null) { teleNoField.setText(pd.getTeleNo()); }
        if(readOnly) { teleNoField.setEditable(false); }
        formattedFields.add(teleNoField);
        personal.add(teleNoField);

        /** Mob No. **/
        JLabel mobNoLab = new JLabel("Mob No.:");
        mobNoLab.setBounds(80, 260, 100, 50);
        personal.add(mobNoLab);
        
        JFormattedTextField mobNoField = new JFormattedTextField(phoneNoMask);
        mobNoField.setBounds(220, 275, 105, 22);
        if(pd != null) { mobNoField.setText(pd.getMobNo()); }
        if(readOnly) { mobNoField.setEditable(false); }
        formattedFields.add(mobNoField);
        personal.add(mobNoField);

        /** Emergency Contact **/
        JLabel emergencyContactLab = new JLabel("Emergency Contact:");
        emergencyContactLab.setBounds(80, 280, 150, 50);
        personal.add(emergencyContactLab);

        JTextField emergencyContactField = new JTextField();
        emergencyContactField.setBounds(220, 295, 105, 22);
        if(pd != null) { emergencyContactField.setText(pd.getEmergencyContact()); }
        if(readOnly) { emergencyContactField.setEditable(false); }
        fields.add(emergencyContactField);
        personal.add(emergencyContactField);

        /** Emergency contact No. **/
        JLabel emergencyNoLab = new JLabel("Emergency Contact No.:");
        emergencyNoLab.setBounds(80, 300, 150, 50);
        personal.add(emergencyNoLab);

        JFormattedTextField emergencyContactNoField = new JFormattedTextField(phoneNoMask);
        emergencyContactNoField.setBounds(220, 315, 105, 22);
        if(pd != null) { emergencyContactNoField.setText(pd.getEmergencyContactNo()); }
        if(readOnly) { emergencyContactNoField.setEditable(false); }
        formattedFields.add(emergencyContactNoField);
        personal.add(emergencyContactNoField);

        /** Submit/cancel buttons **/
        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(370, 400, 97, 25);
            submitBtn.addActionListener((ActionEvent e) -> {
                int validationErrorCount = 0; // 0 == no errors
                if(!readOnly) {
                    for(JComponent input : fields) {
                        JTextField tmp = (JTextField)input;
                        if(!tmp.getText().isEmpty()) {
                            // if the field validates..

                        } else {
                            validationErrorCount++;
                        }
                    }
                    for(JComponent input : formattedFields) {
                        JFormattedTextField tmp = (JFormattedTextField)input;
                        if(tmp.isEditValid()) {

                        } else {
                            validationErrorCount++;
                        }
                    }
                    if(postcodeField.getText().matches("([A-PR-UWYZ](([0-9](([0-9]|[A-HJKSTUW])?)?)|([A-HK-Y][0-9]([0-9]|[ABEHMNPRVWXY])?)) ?[0-9][ABD-HJLNP-UW-Z]{2})")) {
                        // validates successfully
                    } else {
                        validationErrorCount++;
                    }
                    if(validationErrorCount == 0) {
                        if(pd != null) {
                            yuconz.modifyPersonalDetails(pd, staffNoField.getText(), surnameField.getText(), 
                                    nameField.getText(), doBField.getText(), addressLine1Field.getText(), 
                                    addressLine2Field.getText(), cityField.getText(), countyField.getText(),
                                    postcodeField.getText(), teleNoField.getText(), mobNoField.getText(),
                                    emergencyContactField.getText(), emergencyContactNoField.getText());
                            personal.dispose();
                            JPanel panel = new JPanel();

                            JOptionPane.showMessageDialog(panel, "You have successfully Modified this file!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            yuconz.createPersonalDetails(staffNoField.getText(), surnameField.getText(), 
                                    nameField.getText(), doBField.getText(), addressLine1Field.getText(), 
                                    addressLine2Field.getText(), cityField.getText(), countyField.getText(),
                                    postcodeField.getText(), teleNoField.getText(), mobNoField.getText(),
                                    emergencyContactField.getText(), emergencyContactNoField.getText());
                            personal.dispose();
                            JPanel panel = new JPanel();

                            JOptionPane.showMessageDialog(panel, "You have successfully created a personal details file!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JPanel panel = new JPanel();

                        JOptionPane.showMessageDialog(panel, validationErrorCount + " validation errors! Please try again.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JPanel panel = new JPanel();

                    JOptionPane.showMessageDialog(panel, "You are currently in read only mode!", "Read Only", JOptionPane.INFORMATION_MESSAGE);
                }
            });
        personal.add(submitBtn);

        JButton closeBtn = new JButton("Cancel");
        closeBtn.setBounds(270, 400, 97, 25);
        closeBtn.addActionListener((ActionEvent e) -> {
            personal.dispose();
        });
        personal.add(closeBtn);

        personal.setVisible(true);
    }
    
    private void viewExistingPersonalDetails(boolean readOnly) {
        ArrayList<String> list = yuconz.getPersonalDetailsList();
        
        JFrame existing = new JFrame();
        existing.setSize(200,200);
        
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for(String pd : list) {
            pd = pd.substring(0, 6);
            listModel.addElement(pd);
        }
        
        /**
         * This next bit of code essentially handles if something is double clicked
         */
        JList<String> personalDetails = new JList<>(listModel);
        personalDetails.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        personalDetails.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList jlist = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = jlist.getSelectedIndex();
                    if(readOnly) {
                        if(yuconz.authoriseReadPersonal(listModel.getElementAt(index))) {
                            for(String pd : list) {
                                pd = pd.substring(0, 6);
                                if(pd.equals(listModel.getElementAt(index))) {
                                    try {
                                        PersonalDetails pdObj = yuconz.getPersonalDetailsByID(pd);
                                        personalDetails(pdObj, readOnly);
                                        existing.dispose();
                                    } catch (ParseException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                        } else {
                            JPanel panel = new JPanel();

                            JOptionPane.showMessageDialog(panel, "You are not authorised to read this!", "Authorisation Failure", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        if(yuconz.authoriseModifyPersonal(listModel.getElementAt(index))) {
                            for(String pd : list) {
                                pd = pd.substring(0, 6);
                                if(pd.equals(listModel.getElementAt(index))) {
                                    try {
                                        PersonalDetails pdObj = yuconz.getPersonalDetailsByID(pd);
                                        personalDetails(pdObj, readOnly);
                                        existing.dispose();
                                    } catch (ParseException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                        } else {
                            JPanel panel = new JPanel();

                            JOptionPane.showMessageDialog(panel, "You are not authorised to modify this!", "Authorisation Failure", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        
        JScrollPane scroll = new JScrollPane(personalDetails);
        existing.add(scroll);
        
        existing.setVisible(true);
    }
    
    /**
     * Currently has *possible* handling for existing reviews
     * Needs some work for viewing existing
     * @param reviewList
     * @param readOnly 
     */
    private void viewExistingReviews(ArrayList<String> reviewList, boolean readOnly) {
        JFrame existing = new JFrame();
        existing.setSize(200,200);
        
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for(String reviewee : reviewList) {
            listModel.addElement(reviewee);
        }
        /**
         * This next bit of code essentially handles if something is double clicked
         */
        JList<String> reviews = new JList<>(listModel);
        reviews.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        reviews.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList jlist = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = jlist.getSelectedIndex();
                    for(String reviewee : reviewList) {
                        if(reviewee.equals(listModel.getElementAt(index))) {
                            try {
                                // compare clicked on vs the list to find id
                                // get the current review for that id
                                // call review
                                Review reviewObj = yuconz.getReviewById(reviewee);
                                review(reviewObj, readOnly);
                                existing.dispose();
                            } catch (ParseException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
            
        JScrollPane scroll = new JScrollPane(reviews);
        existing.add(scroll);
        
        existing.setVisible(true);
    }
    
    /**
     * The UI for creating/modifying a review
     */
    private void review(Review reviewFile, boolean readOnly) throws ParseException {
        ArrayList<JComponent> fields = new ArrayList<>();
        ArrayList<JComponent> formattedFields = new ArrayList<>();
        JFrame review = new JFrame(); 

        review.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        review.setLayout(null);

        review.setSize(500,500);
        
        JLabel reviewLab = new JLabel("Performance Review");
        reviewLab.setBounds(190, 10, 150, 50);
        review.add(reviewLab);

        JLabel staffNoLab = new JLabel("Staff No:");
        staffNoLab.setBounds(160, 30, 100, 50);
        review.add(staffNoLab);

        MaskFormatter staffNoMask = new MaskFormatter("LLL###");
        JFormattedTextField staffNoField = new JFormattedTextField(staffNoMask);
        staffNoField.setBounds(220, 45, 105, 22);
        if(reviewFile != null) { staffNoField.setText(reviewFile.getStaffNo()); }
        if(readOnly) { staffNoField.setEditable(false); }
        formattedFields.add(staffNoField);
        review.add(staffNoField);
        
        JLabel nameLab = new JLabel("Name:");
        nameLab.setBounds(160, 50, 100, 50);
        review.add(nameLab);

        JTextField nameField = new JTextField();
        nameField.setBounds(220, 65, 105, 22);
        if(reviewFile != null) { nameField.setText(reviewFile.getName()); }
        if(readOnly) { nameField.setEditable(false); }
        fields.add(nameField);
        review.add(nameField);

        /** manager **/
        JLabel managerLab = new JLabel("Manager/Director:");
        managerLab.setBounds(80, 70, 100, 50);
        review.add(managerLab);

        JTextField managerField = new JTextField();
        managerField.setBounds(220, 85, 105, 22);
        if(reviewFile != null) { managerField.setText(reviewFile.getManagerOrDirector()); }
        if(readOnly) { managerField.setEditable(false); }
        fields.add(managerField);
        review.add(managerField);

        /** 2nd manager/director **/
        JLabel secondManagerLab = new JLabel("2nd Manager/Director:");
        secondManagerLab.setBounds(80, 90, 100, 50);
        review.add(secondManagerLab);

        JTextField secondManagerField = new JTextField();
        secondManagerField.setBounds(220, 105, 105, 22);
        if(reviewFile != null) { secondManagerField.setText(reviewFile.getSecondManagerOrDirector()); }
        if(readOnly) { secondManagerField.setEditable(false); }
        fields.add(secondManagerField);
        review.add(secondManagerField);

        /** Section **/
        JLabel sectionLab = new JLabel("Section:");
        sectionLab.setBounds(80, 110, 100, 50);
        review.add(sectionLab);

        JTextField sectionField = new JTextField();
        sectionField.setBounds(220, 125, 105, 22);
        if(reviewFile != null) { sectionField.setText(reviewFile.getSection()); }
        if(readOnly) { sectionField.setEditable(false); }
        fields.add(sectionField);
        review.add(sectionField);

        /** Job Title **/
        JLabel jobTitleLab = new JLabel("job Title:");
        jobTitleLab.setBounds(80, 130, 100, 50);
        review.add(jobTitleLab);

        JTextField jobTitleField = new JTextField();
        jobTitleField.setBounds(220, 145, 105, 22);
        if(reviewFile != null) { jobTitleField.setText(reviewFile.getJobTitle()); }
        if(readOnly) { jobTitleField.setEditable(false); }
        fields.add(jobTitleField);
        review.add(jobTitleField);


        /** future performance **/
        JLabel futurePerformanceLab = new JLabel("Future performance:");
        futurePerformanceLab.setBounds(80, 150, 100, 50);
        review.add(futurePerformanceLab);

        JTextField futurePerformanceField = new JTextField();
        futurePerformanceField.setBounds(220, 165, 105, 22);
        if(reviewFile != null) { futurePerformanceField.setText(reviewFile.getFuturePerformance()); }
        if(readOnly) { futurePerformanceField.setEditable(false); }
        review.add(futurePerformanceField);

        /** Telephone No. **/
        JLabel performanceSummaryLab = new JLabel("Performance Summary:");
        performanceSummaryLab.setBounds(80, 170, 100, 50);
        review.add(performanceSummaryLab);

        JTextField performanceSummaryField = new JTextField();
        performanceSummaryField.setBounds(220, 185, 105, 22);
        if(reviewFile != null) { performanceSummaryField.setText(reviewFile.getPerformanceSummary()); }
        if(readOnly) { performanceSummaryField.setEditable(false); }
        review.add(performanceSummaryField);

        /** Objs and achievements **/
        JLabel objectiveLab = new JLabel("Objectives/achievement:");
        objectiveLab.setBounds(80, 190, 100, 50);
        review.add(objectiveLab);
        
        JTable tbl = new JTable();
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 0) {
                    return false;
                } else {
                    return !readOnly;
                }
            }
        };
        
        String headers[] = new String[] { "No.", "Objectives", "Achievements" };
        model.setColumnIdentifiers(headers);
        tbl.setModel(model);
        tbl.setBounds(220, 205, 210, 100);
        if(reviewFile != null) {
            // iterate through and add rows as appropriate
            int i = 0;
            String no = "";
            String objective = "";
            String achievement = "";
            String[] split = {};
            for(String s : reviewFile.getAchievements()) {
                split = s.split(":");
            }
            for(String s : split) {
                switch(i) {
                    case 0:
                        no = s;
                        i++;
                        break;
                    case 1:
                        objective = s;
                        i++;
                        break;
                    case 2:
                        achievement = s;
                        i++;
                        break;
                }
                if(i == 3) {
                    Object[] data = { no, objective, achievement };
                    model.addRow(data);
                    i = 0;
                }
                System.out.println(s);
            }
        }
        
        // If the file is read only than adding rows isn't necessary..
        if(!readOnly) {
            JButton addRowsBtn = new JButton("Add row");
            addRowsBtn.setBounds(350, 310, 92, 25);
            addRowsBtn.addActionListener((ActionEvent e) -> {
                // add an empty row
                Object[] empty = { model.getRowCount()+1 };
                model.addRow(empty);
            });
            review.add(addRowsBtn);
        }
        
        JScrollPane scrollTbl = new JScrollPane(tbl);
        scrollTbl.setBounds(220, 205, 210, 100);
        
        review.add(scrollTbl);

        /** Reviewer comments **/
        JLabel reviewerCommentsLab = new JLabel("Reviewer Comments:");
        reviewerCommentsLab.setBounds(80, 290, 150, 50);
        review.add(reviewerCommentsLab);

        JTextField reviewerCommentsField = new JTextField();
        reviewerCommentsField.setBounds(220, 305, 105, 22);
        if(reviewFile != null) { reviewerCommentsField.setText(reviewFile.getReviewerComments()); }
        if(readOnly) { reviewerCommentsField.setEditable(false); }
        review.add(reviewerCommentsField);
        
        /** recommendation **/
        JLabel recommendationLab = new JLabel("Recommendation:");
        recommendationLab.setBounds(80, 310, 150, 50);
        review.add(recommendationLab);
        
        String[] recommendation = { "stay in post", "salary increase", "promotion", "probation", "termination" };

        JComboBox recommendationBox = new JComboBox(recommendation);
        recommendationBox.setBounds(220, 325, 97, 25);
        if(reviewFile != null) {
            for(int i = 0; i < recommendation.length - 1; i++) {
                if(recommendation[i].equals(reviewFile.getRecommendation())) {
                    recommendationBox.setSelectedIndex(i);
                }
            }
        } else {
            recommendationBox.setSelectedIndex(0);
        }
        if(readOnly) { recommendationBox.setEnabled(false); }
        review.add(recommendationBox);
        
        /** Dates for the signatures **/
        
        MaskFormatter dateMask;
        JTextField revieweeDateField;
        JTextField managerDateField;
        JTextField secondManagerDateField;
        
        dateMask = new MaskFormatter("####/##/##");
        dateMask.setValidCharacters("0123456789");
        
        revieweeDateField = new JFormattedTextField(dateMask);
        revieweeDateField.setBounds(325, 345, 105, 22);
        if(reviewFile != null) { revieweeDateField.setText(reviewFile.getRevieweeDate()); }
        if(readOnly) { revieweeDateField.setEditable(false); }

        managerDateField = new JFormattedTextField(dateMask);
        managerDateField.setBounds(325, 365, 105, 22);
        if(reviewFile != null) { managerDateField.setText(reviewFile.getManagerOrDirectorDate()); }
        if(readOnly) { managerDateField.setEditable(false); }
        
        secondManagerDateField = new JFormattedTextField(dateMask);
        secondManagerDateField.setBounds(325, 385, 105, 22);
        if(reviewFile != null) { secondManagerDateField.setText(reviewFile.getSecondReviewerDate()); }
        if(readOnly) { secondManagerDateField.setEditable(false); }
        
        review.add(revieweeDateField);
        review.add(managerDateField);
        review.add(secondManagerDateField);

        /** Signatures **/
        JLabel revieweeSigLab = new JLabel("Reviewee Signature");
        revieweeSigLab.setBounds(80, 330, 150, 50);
        review.add(revieweeSigLab);

        JTextField revieweeSigField = new JTextField();
        revieweeSigField.setBounds(220, 345, 105, 22);
        if(reviewFile != null) { revieweeSigField.setText(reviewFile.getRevieweeSignature()); }
        if(readOnly) { revieweeSigField.setEditable(false); }
        review.add(revieweeSigField);
        
        JLabel managerSigLab = new JLabel("Manager/Director Signature:");
        managerSigLab.setBounds(80, 350, 150, 50);
        review.add(managerSigLab);

        JTextField managerSigField = new JTextField();
        managerSigField.setBounds(220, 365, 105, 22);
        if(reviewFile != null) { managerSigField.setText(reviewFile.getManagerOrDirectorSignature()); }
        if(readOnly) { managerSigField.setEditable(false); }
        review.add(managerSigField);
        
        JLabel secondManagerSigLab = new JLabel("2nd Manager Signature:");
        secondManagerSigLab.setBounds(80, 370, 150, 50);
        review.add(secondManagerSigLab);

        JTextField secondManagerSigField = new JTextField();
        secondManagerSigField.setBounds(220, 385, 105, 22);
        if(reviewFile != null) { secondManagerSigField.setText(reviewFile.getSecondReviewerSignature()); }
        if(readOnly) { secondManagerSigField.setEditable(false); }
        review.add(secondManagerSigField);
        
        /** Submit/cancel buttons **/
        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(370, 425, 97, 25);
        submitBtn.addActionListener((ActionEvent e) -> {
            int validationErrorCount = 0;
            for(JComponent input : fields) {
                JTextField tmp = (JTextField)input;
                if(!tmp.getText().isEmpty()) {
                    // if the field validates..

                } else {
                    validationErrorCount++;
                }
            }
            for(JComponent input : formattedFields) {
                JFormattedTextField tmp = (JFormattedTextField)input;
                if(tmp.isEditValid()) {

                } else {
                    validationErrorCount++;
                }
            }
            if(validationErrorCount == 0) {
                if(!readOnly) {
                    ArrayList<String> objList = new ArrayList<>(); // obj as in objective appose to object
                    for(int i = 0; i < model.getRowCount(); i++) {
                        for(int j = 0; j < model.getColumnCount(); j++) {
                            objList.add(model.getValueAt(i, j).toString());
                        }
                    }

                    Review reviewObj = new Review(staffNoField.getText(), nameField.getText(), managerField.getText(),
                            secondManagerField.getText(), sectionField.getText(), jobTitleField.getText(), 
                            objList, performanceSummaryField.getText(), futurePerformanceField.getText(), 
                            reviewerCommentsField.getText(), recommendationBox.getSelectedItem().toString(),
                            revieweeSigField.getText(), revieweeDateField.getText(), managerSigField.getText(),
                            managerDateField.getText(), secondManagerSigField.getText(), secondManagerDateField.getText());
                    if(yuconz.createReview(reviewObj)) {
                        review.dispose();
                        JPanel panel = new JPanel();

                        JOptionPane.showMessageDialog(panel, "You have successfully created a review!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JPanel panel = new JPanel();

                        JOptionPane.showMessageDialog(panel, "There was an Error! Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JPanel panel = new JPanel();

                    JOptionPane.showMessageDialog(panel, "You are in read only mode!", "Read Only", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JPanel panel = new JPanel();

                JOptionPane.showMessageDialog(panel, validationErrorCount + " validation errors! Please try again.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        review.add(submitBtn);
        review.setVisible(true);
    }
    
    /**
     * UI for assigning a reviewer
     */
    private void assignReviewers(){
        JFrame assignReviewers = new JFrame(); 

        assignReviewers.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        assignReviewers.setLayout(null);

        assignReviewers.setSize(250,250);
        
        JLabel revieweeLab = new JLabel("Reviewee :");
        revieweeLab.setBounds(15, 15, 150, 50);
        assignReviewers.add(revieweeLab);
        
        JLabel reviewer1Lab = new JLabel("Reviewer 1 :");
        reviewer1Lab.setBounds(15, 45, 150, 50);
        assignReviewers.add(reviewer1Lab);
        
        JLabel reviewer2Lab = new JLabel("Reviewer 2 :");
        reviewer2Lab.setBounds(15, 75, 150, 50);
        assignReviewers.add(reviewer2Lab);
        
        // Combobox doesn't take arraylists as an arguement, so we convert..
        String[] employees = yuconz.getEmployeeIdList().toArray(new String[0]);

        JComboBox revieweeBox = new JComboBox(employees);
        revieweeBox.setBounds(130, 30, 97, 25);
        revieweeBox.setSelectedIndex(0);
        assignReviewers.add(revieweeBox);
        
        JComboBox reviewer1Box = new JComboBox(employees);
        reviewer1Box.setBounds(130, 60, 97, 25);
        reviewer1Box.setSelectedIndex(0);
        assignReviewers.add(reviewer1Box);
        
        JComboBox reviewer2Box = new JComboBox(employees);
        reviewer2Box.setBounds(130, 90, 97, 25);
        reviewer2Box.setSelectedIndex(0);
        assignReviewers.add(reviewer2Box);
        
        /** submit btn **/
        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(200, 150, 97, 25);
        
        submitBtn.addActionListener((ActionEvent e) -> {
            // check auth, then either allow create or show error
            if(yuconz.assignReviewers(revieweeBox.getSelectedItem().toString(), reviewer1Box.getSelectedItem().toString(),
                    reviewer2Box.getSelectedItem().toString())) {
                assignReviewers.dispose();
                JPanel panel = new JPanel();

                JOptionPane.showMessageDialog(panel, "You have successfully assigned reviewers to "
                        + revieweeBox.getSelectedItem().toString() + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JPanel panel = new JPanel();
                
                JOptionPane.showMessageDialog(panel, "Could not assign reviewers!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        assignReviewers.add(submitBtn);
        
        assignReviewers.setVisible(true);
    }
    
    /**
     * Disposes of the JFrame; re-opens the authentication window
     */
    private void logout() {
        yuconz.logout();
        Authentication auth = new Authentication();
        this.dispose();
    }
}