package ch.makery.model;

import ch.makery.model.Interface.IPerson;
import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ch.makery.util.LocalDateAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;



/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Person implements IPerson {

    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty street;
    private final IntegerProperty postalCode;
    private final StringProperty city;
    private final ObjectProperty<LocalDate> birthday;
    
    private static int dummyMonth;
    private static int dummyDay;
    private static int dummyYear;
    
    /**
     * Default constructor.
     */
    public Person() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     * 
     * @param firstName
     * @param lastName
     */
    public Person(String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);

        // Some initial dummy data, just for convenient testing.
        this.street = new SimpleStringProperty("Street");
        this.postalCode = new SimpleIntegerProperty(30640640);
        this.city = new SimpleStringProperty("City");
        this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));      
    }
    
    @Override
    public String getFirstName() {
        return firstName.get();
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    @Override
    public StringProperty firstNameProperty() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName.get();
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    @Override
    public StringProperty lastNameProperty() {
        return lastName;
    }

    @Override
    public String getStreet() {
        return street.get();
    }

    @Override
    public void setStreet(String street) {
        this.street.set(street);
    }

    @Override
    public StringProperty streetProperty() {
        return street;
    }

    @Override
    public int getPostalCode() {
        return postalCode.get();
    }

    @Override
    public void setPostalCode(int postalCode) {
        this.postalCode.set(postalCode);
    }

    @Override
    public IntegerProperty postalCodeProperty() {
        return postalCode;
    }

    @Override
    public String getCity() {
        return city.get();
    }

    @Override
    public void setCity(String city) {
        this.city.set(city);
    }

    @Override
    public StringProperty cityProperty() {
        return city;
    }

    @Override
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getBirthday() {
        return birthday.get();
    }

    @Override
    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    @Override
    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }
}