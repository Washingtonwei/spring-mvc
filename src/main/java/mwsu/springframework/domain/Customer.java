package mwsu.springframework.domain;
import javax.persistence.*;

/**
 * Created by bingyang.wei on 5/6/2017.
 */
@Entity
public class Customer extends AbstractDomainObject{

    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    @Embedded
    private Address billingAddress;
    @Embedded
    private Address shippingAddress;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private User user;

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {

        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
