package yuconz;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author rm631, ol61, mb859, ra466
 */
public class Yuconz {
    
    private static final Logger logger = Logger.getLogger(System.class.getName());
    
    private static User user;
    
   /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
           public void run() {
                try {
                    Authentication window = new Authentication();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
           }
       });
   }
    
    /**
     *
     * @return
     */
    public ArrayList<String> getEmployeeIdList() {
        ArrayList<String> employeeList = new ArrayList<>();
        Scanner read = null;
        try {
            File file = new File("files/login.txt");
            read = new Scanner(file);
            read.useDelimiter("\\n|:"); // ending on a new line (\n) in the txt file causes strings to not match for some reason (sometimes??) - if this happens add a : to the end
            while(read.hasNext()) {
                String userId = read.next();
                employeeList.add(userId);
                read.nextLine();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            if(read != null) {read.close();}
        }
        return employeeList;
    }
    
    /**
     * @return arraylist of staff numbers with PD files (for use in home.showExisting())
     */
    public ArrayList<String> getPersonalDetailsList() {
        ArrayList<String> pDList = new ArrayList<>();
        
        File[] files = new File("files/Personal Details").listFiles();
        
        for(File file : files) {
            if(file.isFile()) {
                pDList.add(file.getName());
            }
        }
        return pDList;
        //return personalDetailsList;
    }
    
    /**
     * Read from the id's PD file and drop the details into an arraylist
     * @param id
     * @return 
     */
    public PersonalDetails getPersonalDetailsByID(String id) {
        
        String staffNo = "";
        String surname = "";
        String name = ""; 
        String doB = "";
        String addressLine1 = "";
        String addressLine2 = "";
        String city = "";
        String county = "";
        String postcode = "";
        String teleNo = "";
        String mobNo = "";
        String emergencyContact = "";
        String emergencyContactNo = "";
        
        Scanner read = null;
        try {
            File file = new File("files/Personal Details/" + id + ".txt");
            read = new Scanner(file);
            read.useDelimiter("\\n");
            while(read.hasNext()) {
                staffNo = read.next().trim();
                surname = read.next().trim();
                name = read.next().trim();
                doB = read.next().trim();
                addressLine1 = read.next().trim();
                addressLine2 = read.next().trim();
                city = read.next().trim();
                county = read.next().trim();
                postcode = read.next().trim();
                teleNo = read.next().trim();
                mobNo = read.next().trim();
                emergencyContact = read.next().trim();
                emergencyContactNo = read.next().trim();
            }
            PersonalDetails pd = new PersonalDetails(staffNo, surname, name, doB, addressLine1,
            addressLine2, city,  county,  postcode, teleNo, mobNo, emergencyContact, emergencyContactNo);
            return pd;
        } catch (FileNotFoundException ex) { 
            ex.printStackTrace();
        } finally {
            if(read != null) {read.close();}
        }
        return null;
    }
    
    /**
     * When a review is selected this will retrieve it
     * @param revieweeId to whom the reviews belong
     * @return the current years review
     */
    public Review getReviewById(String revieweeId) {
        // find reviews for id
        // ^ check through directory (removing the ends of the files ie. year or ***use year to find current***)
        // ^^ IT IS AN ANNUAL REVIEW, USE THE YEAR IN THE FILE NAME TO FILE THE CURRENT YEAR NOT WHATS UNDERNEATH THIS LINE
        // go through, check for 'signatures'+dates
        // if empty space then return that one as its the active one
        Review review = null;
        ArrayList<String> reviewList = new ArrayList<>();
        File path = new File("files/reviews");
        File[] files = path.listFiles();
        
        /**
         * To avoid a method of mostly duplicate code, this code decides if we search for
         * a current or past review by checking if the parameter is jjust an ID or ID+Year
         */
        String year;
        if(revieweeId.length() > 6) {
            String[] reviewDetails = revieweeId.split("\\.| - "); 
            revieweeId = reviewDetails[0];
            year = reviewDetails[1];
        } else {
            year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        }

        for(File fileName : files) {
            String[] fileArr = fileName.getName().split("\\.|\\-");
            if(fileName.getName().equals(revieweeId + " - " + year + ".txt")) {
                Scanner read = null;
                try {
                    File file = new File(fileName.getAbsolutePath()); // .getName only passes the *filename* 
                    read = new Scanner(file);
                    read.useDelimiter("\\n");

                    String staffNo = "";
                    String name = ""; 
                    String managerOrDirector = ""; 
                    String secondManagerOrDirector = ""; 
                    String section = "";
                    String jobTitle = "";
                    ArrayList<String> achievements = new ArrayList<>();
                    String performanceSummary = ""; 
                    String futurePerformance = "";
                    String reviewerComments = ""; 
                    String recommendation = "";
                    String revieweeSignature = "";
                    String revieweeDate = "";
                    String managerOrDirectorSignature = "";
                    String managerOrDirectorDate = ""; 
                    String secondReviewerSignature = "";
                    String secondReviewerDate = "";

                    while(read.hasNext()) {
                        staffNo = read.next();
                        name = read.next();
                        managerOrDirector = read.next();
                        secondManagerOrDirector = read.next();
                        section = read.next();
                        jobTitle =  read.next();
                        achievements.add(read.next()); // this will need to be changed when we can add multiple
                        performanceSummary = read.next();
                        futurePerformance = read.next();
                        reviewerComments = read.next();
                        recommendation = read.next();
                        revieweeSignature = read.next();
                        revieweeDate = read.next();
                        managerOrDirectorSignature = read.next();
                        managerOrDirectorDate = read.next();
                        secondReviewerSignature = read.next();
                        secondReviewerDate = read.next();
                    }
                    review = new Review(staffNo, name, managerOrDirector, secondManagerOrDirector, section, jobTitle,
                        achievements, performanceSummary, futurePerformance, reviewerComments, recommendation,
                        revieweeSignature, revieweeDate, managerOrDirectorSignature, managerOrDirectorDate,
                        secondReviewerSignature, secondReviewerDate);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } finally {
                    if(read != null) {read.close();}
                }

            }
        }
        return review;
    }
    
    /**
     * Sets the a variable to store which employee is logged in
     * @param newUser the user that has been authenticated
     */
    public void setUser(User newUser) {
        user = newUser;
    }
    
    /**
     * Password is handled as a char[] due to .getText() being a deprecated 
     * method of JPasswordField
     * @param username entered by the user
     * @param password of the password entered by the user
     * @param role selected by the user in the combo box
     * @return true if correct username/password combo entered
     */
    public boolean login(String username, char[] password, String role) {
        /** username/password from file **/
        Scanner read = null;
        String[] authLevels = null;
        try {
            File file = new File("files/login.txt");
            read = new Scanner(file);
            read.useDelimiter("\\n|:"); // ending on a new line (\n) in the txt file causes strings to not match for some reason (sometimes??) - if this happens add a : to the end
            while(read.hasNext()) {
                String user = read.next();
                String pw = read.next();
                String auth = read.next();
                if(auth.contains("-")) {
                    authLevels = auth.split("-");
                }
                String department = read.next();
                String reviewer = read.next();
                reviewer = reviewer.trim(); // last item on line has an invisible \n character
                char[] employeePass = pw.toCharArray();
                if((username.equals(user)) && (Arrays.equals(password, employeePass))) { //&& ((role.equals(auth)) 
                        //|| (role.equals("Reviewer") && reviewer.equals("true"))) {
                    //department = department.trim(); // Turns out that java was adding the /n character and breaking half the code
                    switch(role) { 
                        case "Employee" : 
                            Employee employee = new Employee(username, pw, department, role);
                            Yuconz.user = employee;
                            return true;
                        case "HR Employee" : 
                            if(department.equals("Human Resources")) {
                                HrEmployee hrEmployee = new HrEmployee(username, pw, department, role);
                                Yuconz.user = hrEmployee;
                                return true;
                            }
                            break;
                        case "Manager" : 
                            if(authLevels != null) {
                                for(String authLevel : authLevels) {
                                    if(authLevel.equals("Manager")) {
                                        Manager manager = new Manager(username, pw, department, role);
                                        Yuconz.user = manager;
                                        return true;
                                    }
                                }
                            } else if(role.equals(auth)){ 
                                Manager manager = new Manager(username, pw, department, role);
                                Yuconz.user = manager;
                                return true;
                            }
                            
                            break;
                        case "Director" :
                            if(authLevels != null) {
                                for (String authLevel : authLevels) {
                                    if(authLevel.equals("Director")) {
                                        Director director = new Director(username, pw, department, role);
                                        Yuconz.user = director;
                                        return true;
                                    }
                                }
                            } else if(role.equals(auth)) {
                                Director director = new Director(username, pw, department, role);
                                Yuconz.user = director;
                                return true;
                            }
                            break;
                        case "Reviewer" :
                            if(role.equals("Reviewer") && reviewer.equals("true")) {
                                Reviewer reviewerObj = new Reviewer(username, pw, department, role);
                                Yuconz.user = reviewerObj;
                                return true;
                            }
                            break;
                        default: break;
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if(read != null) {read.close();}
        }
        return false;
    }
    
    /**
     *
     */
    public void logout() {
        User logout = new User("","","",""); 
        user = logout; // setting user to null was causing issues
    }
    
    /**
     *
     * @return true if the user is authorised to create a PD file
     */
    public boolean authoriseCreatePersonal() {
        FileHandler fh = null;
        try {
            fh = new FileHandler("authorisation.log", true); // true is for appending
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            
            // if the currently logged in user is a member of the hr department and not authenticated as a standard employee
            if((user.getDepartment().equals("Human Resources")) && (!user.getAuth().equals("Employee"))) {
                // LOG SUCCESSFUL ATTEMPT
                logger.info("Authorisation Successful - Create Personal Detail File.");
                return true;
            } else {
                // LOG UNSUCCESSFUL ATTEMPT
                logger.info("Authorisation Failed - Create Personal Detail File.");
                return false;
            }
        } catch (IOException ex) {  
            ex.printStackTrace();  
            return false;
        } finally {
            if(fh != null) {fh.close();}
        }
    }

    public void createPersonalDetails(String staffNo, String surname, String name, String doB, String addressLine1,
            String addressLine2, String city, String county, String postcode, String teleNo, String mobNo, 
            String emergencyContact, String emergencyContactNo) {
        BufferedWriter writer = null;
        try {
            File file = new File("files/Personal Details/" + staffNo + ".txt");
            writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file)));
            writer.write(staffNo);
            writer.newLine();
            writer.write(surname);
            writer.newLine();
            writer.write(name);
            writer.newLine();
            writer.write(doB);
            writer.newLine();
            writer.write(addressLine1);
            writer.newLine();
            writer.write(addressLine2);
            writer.newLine();
            writer.write(city);
            writer.newLine();
            writer.write(county);
            writer.newLine();
            writer.write(postcode);
            writer.newLine();
            writer.write(teleNo);
            writer.newLine();
            writer.write(mobNo);
            writer.newLine();
            writer.write(emergencyContact);
            writer.newLine();
            writer.write(emergencyContactNo);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if(writer != null) {writer.close();}
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    /**
     * 
     * @param pd is the staff no. of the personal details file to be modified
     * @return true of the authenticated user can modify the selected PD file
     */
    public boolean authoriseModifyPersonal(String pd) {
        FileHandler fh = null;
        try {
            fh = new FileHandler("authorisation.log", true); // true is for appending
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            
            // if a user is trying to view their own record, we needn't check anything else
            if(pd.equals(user.getUsername())) {
                logger.log(Level.INFO, "Authorisation Successful - Modify {0}''s Personal Detail File, attempt by: {1}, {2}", new Object[]{pd, user.getUsername(), user.getAuth()});
                return true;
            } else if(user.getDepartment().equals("Human Resources") && (!user.getAuth().equals("Employee"))) {
                logger.log(Level.INFO, "Authorisation Successful - Modify {0}''s Personal Detail File, attempt by: {1}, {2}", new Object[]{pd, user.getUsername(), user.getAuth()});
                return true;
            } else {
                logger.log(Level.INFO, "Authorisation Failed - Modify {0}''s Personal Detail File, attempt by: {1}, {2}", new Object[]{pd, user.getUsername(), user.getAuth()});
                return false;
            }
        } catch (IOException ex) {  
            ex.printStackTrace();  
            return false;
        } finally {
            if(fh != null) {fh.close();}
        }
    }
    
    public void modifyPersonalDetails(PersonalDetails pd, String staffNo, String surname, String name, String doB, String addressLine1,
            String addressLine2, String city, String county, String postcode, String teleNo, String mobNo, 
            String emergencyContact, String emergencyContactNo) {
        /**
         * Modify this to do more than just overwrite the file with the create code
         * ****OR**** turn this into a single method with the purpose of writing
         * PD files to avoid the duplicate code
         */
        BufferedWriter writer = null;
        FileWriter fw = null;
        try {
            File file = new File("files/Personal Details/" + staffNo + ".txt");
            fw = new FileWriter(file, false);
            writer = new BufferedWriter(fw);
            writer.write(staffNo);
            writer.newLine();
            writer.write(surname);
            writer.newLine();
            writer.write(name);
            writer.newLine();
            writer.write(doB);
            writer.newLine();
            writer.write(addressLine1);
            writer.newLine();
            writer.write(addressLine2);
            writer.newLine();
            writer.write(city);
            writer.newLine();
            writer.write(county);
            writer.newLine();
            writer.write(postcode);
            writer.newLine();
            writer.write(teleNo);
            writer.newLine();
            writer.write(mobNo);
            writer.newLine();
            writer.write(emergencyContact);
            writer.newLine();
            writer.write(emergencyContactNo);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if(writer != null) {writer.close();}
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    /**
     * 
     * @param pd where pd is the user to who the personal detail to read belongs
     * @return true if the logged in user has authorisation to read the personal details file
     */
    public boolean authoriseReadPersonal(String pd) {
        FileHandler fh = null;
        try {
            fh = new FileHandler("authorisation.log", true); // true is for appending
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            
            // if a user is trying to view their own record, we needn't check anything else
            if(pd.equals(user.getUsername())) {
                logger.log(Level.INFO, "Authorisation Successful - Read {0}''s Personal Detail File, attempt by: {1}, {2}", new Object[]{pd, user.getUsername(), user.getAuth()});
                return true;
            } else if(user.getDepartment().equals("Human Resources") && (!user.getAuth().equals("Employee"))) {
                logger.log(Level.INFO, "Authorisation Successful - Read {0}''s Personal Detail File, attempt by: {1}, {2}", new Object[]{pd, user.getUsername(), user.getAuth()});
                return true;
            } else {
                logger.log(Level.INFO, "Authorisation Failed - Read {0}''s Personal Detail File, attempt by: {1}, {2}", new Object[]{pd, user.getUsername(), user.getAuth()});
                return false;
            }
        } catch (IOException ex) {  
            ex.printStackTrace();  
            return false;
        } finally {
            if(fh != null) {fh.close();}
        }
    }
    
    /**
     * 
     * @param employee attempted to authorise creation
     * @return true if the employee has been assigned two reviewers
     */
    public boolean authoriseCreateReview() {
        Scanner read = null;
        try {
            // read from files/reviews/reviews.txt find employee
            // if employee is found; do things, if not; don't
            File file = new File("files/reviews/reviewers.txt");
            read = new Scanner(file);
            read.useDelimiter("\\n|:"); 
            while(read.hasNextLine()) {
                String line = read.nextLine();
                String[] lineSplit = line.split(":");
                String employee = null; //lineSplit[0];
                String reviewer1 = null;
                String reviewer2 = null;
                // for+Switch case is to prevent out of bounds/null pointers
                for(int i = 0; i <= lineSplit.length-1; i++) {
                    switch(i) {
                        case 0: 
                            employee = lineSplit[i];
                            break;
                        case 1:
                            reviewer1 = lineSplit[i];
                            break;
                        case 2:
                            reviewer2 = lineSplit[i];
                            break;
                    }
                }
                // As the file contains all the employees and an employee must exist to login
                // we ASSUME that employee will NEVER be null
                if(user.getUsername().equals(employee) && reviewer1 != null && reviewer2 != null) {
                    return true;
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if(read != null) {read.close();}
        }
        return false;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<String> authoriseModifyReview() {
        ArrayList<String> reviews = null;
        Scanner read = null;
        try {
            reviews = new ArrayList<>();
            File file = new File("files/reviews/reviewers.txt");
            read = new Scanner(file);
            read.useDelimiter("\\n|:"); 
            while(read.hasNextLine()) {
                String line = read.nextLine();
                String[] lineSplit = line.split(":");
                String employee = null;
                String reviewer1 = null;
                String reviewer2 = null;
                
                // for+Switch case is to prevent out of bounds/null pointers
                for(int i = 0; i <= lineSplit.length-1; i++) {
                    switch(i) {
                        case 0: 
                            employee = lineSplit[i];
                            break;
                        case 1:
                            reviewer1 = lineSplit[i];
                            break;
                        case 2:
                            reviewer2 = lineSplit[i];
                            break;
                    }
                }
                
                if(employee != null && reviewer1 != null && reviewer2 != null) {
                    // if the user is the reviewee or reviewer of a review document then that employees staff number is added to the arraylist
                    if(user.getUsername().equals(employee) || user.getUsername().equals(reviewer1) || user.getUsername().equals(reviewer2)) {
                        // User can amend employees review record
                        reviews.add(employee);
                    }
                }
            }
            return reviews;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return reviews;
        } finally {
            if(read != null) {read.close();}
        }
    }
    
    /**
     * Find people who user can review, search directory for their reviews and return that
     * then double click will use getById 
     * @return an ArrayList of past reviews the logged in user can read
     */
    public ArrayList<String> authoriseReadPastReview() {
        ArrayList<String> reviews = null;
        String year = "";
        Scanner read = null;
        try {
            reviews = new ArrayList<>();
            year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
            File file = new File("files/reviews/reviewers.txt");
            read = new Scanner(file);
            read.useDelimiter("\\n|:"); 
            while(read.hasNextLine()) {
                String line = read.nextLine();
                String[] lineSplit = line.split(":");
                String employee = null;
                String reviewer1 = null;
                String reviewer2 = null;
                
                // for+Switch case is to prevent out of bounds/null pointers
                for(int i = 0; i <= lineSplit.length-1; i++) {
                    switch(i) {
                        case 0: 
                            employee = lineSplit[i];
                            break;
                        case 1:
                            reviewer1 = lineSplit[i];
                            break;
                        case 2:
                            reviewer2 = lineSplit[i];
                            break;
                    }
                }
                
                if(!user.getDepartment().equals("Human Resouces")) {
                    if(employee != null && reviewer1 != null && reviewer2 != null) { // if user is reviewer
                        if(user.getUsername().equals(reviewer1) || user.getUsername().equals(reviewer2)) {
                            File directory = new File("files/reviews");
                            File[] files = directory.listFiles();
                            for(File reviewFile : files) {
                                String fileName = reviewFile.getName();
                                String[] fileNameSplit = fileName.split("\\.| - ");
                                if(fileNameSplit[0] != null && fileNameSplit[1] != null) {
                                    if((fileNameSplit[0].equals(employee)) && (!fileNameSplit[1].equals(year))) {
                                        reviews.add(fileName);
                                    }
                                }
                            }
                        }
                    }
                } else if((user.getDepartment().equals("Human Resouces")) && (!user.getAuth().equals("Employee"))){ // if reviewer is HR department
                    // if the user is a member of HR with a valid level of authentication
                    // .. then show all (as other departments can only see past reviews if they're reviewing someone
                    // hence will never see the current year as readable
                    File directory = new File("files/reviews");
                    File[] files = directory.listFiles();
                    for(File reviewFile : files) {
                        String fileName = reviewFile.getName();
                        String[] fileNameSplit = fileName.split("\\.| - ");
                        if(fileNameSplit[0] != null && fileNameSplit[1] != null) {
                            if((fileNameSplit[0].equals(employee)) && (!fileNameSplit[1].equals(year))) {
                                reviews.add(fileName);
                            }
                        }
                    }
                }
            }
            
            for(String r : reviews) {
                System.out.println(r);
            }
            
            return reviews;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return reviews;
        } finally {
            if(read != null) {read.close();}
        }
    }
    
    /**
     * Modify will use the same method for the time being, going through and editing specific lines
     * ie. proper editing would be added with more time, similar to how it works in
     * assignReviewers (copying, editing, deleting and renaming)
     * @param review
     * @return true if review was created
     */
    public boolean createReview(Review review) {
        BufferedWriter writer = null;
        ArrayList<String> list = new ArrayList<>();
        try {
            File file = new File("files/Reviews/" + review.getStaffNo() + " - " + Calendar.getInstance().get(Calendar.YEAR) + ".txt");
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            writer.write(review.getStaffNo());
            writer.newLine();
            writer.write(review.getName());
            writer.newLine();
            writer.write(review.getManagerOrDirector());
            writer.newLine();
            writer.write(review.getSecondManagerOrDirector());
            writer.newLine();
            writer.write(review.getSection());
            writer.newLine();
            writer.write(review.getJobTitle());
            writer.newLine();
            
            list = review.getAchievements();
            int counter = 0;
            for(String s : list) {
                if(counter != 0 && counter % 3 == 0) {
                    writer.newLine();
                    writer.write(s);
                    writer.write(":");
                } else {
                    writer.write(s);
                    writer.write(":");
                }
                counter++;
            }
            writer.newLine();
            writer.write(review.getPerformanceSummary());
            writer.newLine();
            writer.write(review.getFuturePerformance());
            writer.newLine();
            writer.write(review.getReviewerComments());
            writer.newLine();
            writer.write(review.getRecommendation());
            writer.newLine();
            writer.write(review.getRevieweeSignature());
            writer.newLine();
            writer.write(review.getRevieweeDate());
            writer.newLine();
            writer.write(review.getManagerOrDirectorSignature());
            writer.newLine();
            writer.write(review.getManagerOrDirectorDate());
            writer.newLine();
            writer.write(review.getSecondReviewerSignature());
            writer.newLine();
            writer.write(review.getSecondReviewerDate());
            
            if(!review.getRevieweeSignature().equals("") && !review.getManagerOrDirectorSignature().equals("") && !review.getSecondReviewerSignature().equals("")) {
                // if the review has been signed off by all members...
                assignReviewers(review.getStaffNo(), "", "");
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if(writer != null) {
                    writer.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }
    
    /**
     * if user is authenticated as HR, they can assign a reviewer to the employee
     * @param reviewee
     * @param reviewer1
     * @param reviewer2
     * @return true if the reviewers are successfully assigned  
     */
    public boolean assignReviewers(String reviewee, String reviewer1, String reviewer2) {
        if(user.getDepartment().equals("Human Resources") && (!user.getAuth().equals("Employee"))) { // only HR can do this
            BufferedReader file = null;
            PrintWriter writer = null;
            BufferedWriter bw = null;
            BufferedReader loginFile = null;
            PrintWriter pw = null;
            String fileName = "files/reviews/reviewers.txt";
            String tmpFile = "files/reviews/reviewersTmp.txt";
            try {
                file = new BufferedReader(new FileReader(fileName));
                writer = new PrintWriter(new File(tmpFile));
                String line;
                String newLine;
                StringBuilder input = new StringBuilder();
                
                while((line = file.readLine()) != null) {
                    String[] lineArr = line.split(":");
                    if(lineArr[0].equals(reviewee)) {
                        newLine = reviewee + ":" + reviewer1 + ":" + reviewer2;
                        writer.println(newLine);
                        
                        // If someone is assigned as a reviewer, they need to be able to authenticate as 
                        // a reviewer
                        loginFile = new BufferedReader(new FileReader("files/login.txt"));
                        pw = new PrintWriter("files/loginTmp.txt");
                        String loginLine;
                        while((loginLine = loginFile.readLine()) != null) {
                            String[] loginLineArr = loginLine.split(":");
                            if(loginLineArr[0].equals(reviewer1) | loginLineArr[0].equals(reviewer2)) {
                                loginLine = loginLine.replace("false", "true");
                                pw.println(loginLine);
                            } else {
                                pw.println(loginLine);
                            }
                        }
                        loginFile.close();
                        pw.close();
                        File oldFile = new File("files/login.txt");
                        oldFile.delete();
                        
                        File newFile = new File("files/loginTmp.txt");
                        newFile.renameTo(oldFile);
                    } else {
                        writer.println(line);
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if(writer != null) {writer.close();}
                try {
                    if(bw != null) {bw.close();}
                    if(file != null) {file.close();}
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            
            File oldFile = new File(fileName);
            oldFile.delete();
            
            File newFile = new File(tmpFile);
            newFile.renameTo(oldFile);
            
            return true;
        } else {
            return false;
        }
    }
}