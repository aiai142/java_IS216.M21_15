package Model;

public class Sys_User {
    private String userID;
    private String userName;
    private String userPassword;
    private String createdDate;

    public Sys_User(String userID, String userName, String userPassword, String createdDate) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.createdDate = createdDate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
