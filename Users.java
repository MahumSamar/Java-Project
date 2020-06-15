package flexTransport;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.SQLException;

public abstract class Users implements UserInterface
{
    // Variables
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private String emailAddress;
    private String title;




    // Abstract Methods
    abstract boolean login(String username, String password) throws SQLException;
    abstract void signOut(ActionEvent event) throws IOException;


    // Getters And Setters
    public String getFirstName()
    {
        return firstName;
    }
    public String getLastName()
    {
        return lastName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getGender()
    {
        return gender;
    }
    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

