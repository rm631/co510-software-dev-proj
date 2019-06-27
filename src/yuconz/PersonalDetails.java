package yuconz;

/**
 *
 * @author rm631, ol61, mb859, ra466
 */
public class PersonalDetails {
    private String staffNo;
    private String surname;
    private String name;
    private String doB;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String county;
    private String postcode;
    private String teleNo;
    private String mobNo;
    private String emergencyContact;
    private String emergencyContactNo;
    
    public PersonalDetails(String staffNo, String surname, String name, String doB, String addressLine1,
            String addressLine2, String city, String county, String postcode, String teleNo, String mobNo, String emergencyContact,
            String emergencyContactNo) {
        this.staffNo = staffNo;
        this.surname = surname;
        this.name = name;
        this.doB = doB;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.county = county;
        this.postcode = postcode;
        this.teleNo = teleNo;
        this.mobNo = mobNo;
        this.emergencyContact = emergencyContact;
        this.emergencyContactNo = emergencyContactNo;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getDoB() {
        return doB;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getTeleNo() {
        return teleNo;
    }

    public String getMobNo() {
        return mobNo;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public String getEmergencyContactNo() {
        return emergencyContactNo;
    }
	
    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }
	
    public void setSurname(String surname) {
        this.surname = surname;
    }
	
    public void setName(String name) {
        this.name = name;
    }
	
    public void setDoB(String doB) {
        this.doB = doB;
    }
	
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }
	
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }
	
    public void setCity(String city) {
        this.city = city;
    }
	
    public void setCounty(String county) {
        this.county = county;
    }
	
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
	
    public void setTeleNo(String teleNo) {
        this.teleNo = teleNo;
    }
	
    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }
	
    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }
	
    public void setEmergencyContactNo(String emergencyContactNo) {
        this.emergencyContactNo = emergencyContactNo;
    }
}
