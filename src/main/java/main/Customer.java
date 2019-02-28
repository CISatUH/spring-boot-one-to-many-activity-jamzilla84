package main;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Customer {
    private int customerId;
    private String lastName;
    private String firstName;

    public Customer() {
    }

    public Customer(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    private Set<Address> addresses;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "customer",cascade = CascadeType.ALL)
    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        addresses.forEach(address -> address.setCustomer(this));
        this.addresses = addresses;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID", nullable = false)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "LAST_NAME", nullable = true, length = 20)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "FIRST_NAME", nullable = true, length = 20)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(firstName, customer.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, lastName, firstName);
    }
}
