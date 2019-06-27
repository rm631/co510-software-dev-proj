package yuconz;

import java.util.ArrayList;

/**
 *
 * @author Ryan
 */
public class Review {
    // in the order they appear ie. they're not grouped by anything
    private String staffNo;
    private String name;
    private String managerOrDirector; // Consider a taking an object instead
    private String secondManagerOrDirector; // again, consider object instead
    private String section;
    private String jobTitle;
    
    private ArrayList<String> achievements; // No, objectives, achievement - see review. Also consider datatype/structure
    private String performanceSummary;
    private String futurePerformance;
    private String reviewerComments;
    private String recommendation; // stay in post / salary increase / promotion / probation / termination.
    
    private String revieweeSignature;
    private String revieweeDate;
    private String managerOrDirectorSignature;
    private String managerOrDirectorDate;
    private String secondReviewerSignature;
    private String secondReviewerDate;

    public Review(String staffNo, String name, String managerOrDirector, String secondManagerOrDirector, 
            String section, String jobTitle, ArrayList<String> achievements, String performanceSummary, 
            String futurePerformance, String reviewerComments, String recommendation, String revieweeSignature, 
            String revieweeDate, String managerOrDirectorSignature, String managerOrDirectorDate, 
            String secondReviewerSignature, String secondReviewerDate) {
        this.staffNo = staffNo;
        this.name = name;
        this.managerOrDirector = managerOrDirector;
        this.secondManagerOrDirector = secondManagerOrDirector;
        this.section = section;
        this.jobTitle = jobTitle;
        this.achievements = achievements;
        this.performanceSummary = performanceSummary;
        this.futurePerformance = futurePerformance;
        this.reviewerComments = reviewerComments;
        this.recommendation = recommendation;
        this.revieweeSignature = revieweeSignature;
        this.revieweeDate = revieweeDate;
        this.managerOrDirectorSignature = managerOrDirectorSignature;
        this.managerOrDirectorDate = managerOrDirectorDate;
        this.secondReviewerSignature = secondReviewerSignature;
        this.secondReviewerDate = secondReviewerDate;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public String getName() {
        return name;
    }

    public String getManagerOrDirector() {
        return managerOrDirector;
    }

    public String getSecondManagerOrDirector() {
        return secondManagerOrDirector;
    }

    public String getSection() {
        return section;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public ArrayList<String> getAchievements() {
        return achievements;
    }

    public String getPerformanceSummary() {
        return performanceSummary;
    }

    public String getFuturePerformance() {
        return futurePerformance;
    }

    public String getReviewerComments() {
        return reviewerComments;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public String getRevieweeSignature() {
        return revieweeSignature;
    }

    public String getRevieweeDate() {
        return revieweeDate;
    }

    public String getManagerOrDirectorSignature() {
        return managerOrDirectorSignature;
    }

    public String getManagerOrDirectorDate() {
        return managerOrDirectorDate;
    }

    public String getSecondReviewerSignature() {
        return secondReviewerSignature;
    }

    public String getSecondReviewerDate() {
        return secondReviewerDate;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManagerOrDirector(String managerOrDirector) {
        this.managerOrDirector = managerOrDirector;
    }

    public void setSecondManagerOrDirector(String secondManagerOrDirector) {
        this.secondManagerOrDirector = secondManagerOrDirector;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setAchievements(ArrayList<String> achievements) {
        this.achievements = achievements;
    }

    public void setPerformanceSummary(String performanceSummary) {
        this.performanceSummary = performanceSummary;
    }

    public void setFuturePerformance(String futurePerformance) {
        this.futurePerformance = futurePerformance;
    }

    public void setReviewerComments(String reviewerComments) {
        this.reviewerComments = reviewerComments;
    }

    public void setRecommentdation(String recommendation) {
        this.recommendation = recommendation;
    }

    public void setRevieweeSignature(String revieweeSignature) {
        this.revieweeSignature = revieweeSignature;
    }

    public void setRevieweeDate(String revieweeDate) {
        this.revieweeDate = revieweeDate;
    }

    public void setManagerOrDirectorSignature(String managerOrDirectorSignature) {
        this.managerOrDirectorSignature = managerOrDirectorSignature;
    }

    public void setManagerOrDirectorDate(String managerOrDirectorDate) {
        this.managerOrDirectorDate = managerOrDirectorDate;
    }

    public void setSecondReviewerSignature(String secondReviewerSignature) {
        this.secondReviewerSignature = secondReviewerSignature;
    }

    public void setSecondReviewerDate(String secondReviewerDate) {
        this.secondReviewerDate = secondReviewerDate;
    }
}
