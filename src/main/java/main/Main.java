package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @Override
    public void run(String... strings) throws Exception {

        //clear the database at startup
        customerRepository.deleteAll();
        addressRepository.deleteAll();

        //run method for inserting data
        addData();
    }

    private void addData() {
        //create first address
        Address address1 = new Address();
        address1.setStreetName("Mcdade St");
        address1.setStreetNumber("8647");
        address1.setState("TX");
        address1.setZipcode(77080);

        //create second address
        Address address2 = new Address();
        address2.setStreetName("Mcdade St");
        address2.setStreetNumber("8643");
        address2.setState("TX");
        address2.setZipcode(77080);

        //add addresses to a set
        Set<Address> addresses = new HashSet<>();
        addresses.add(address1);
        addresses.add(address2);

        //create a customer and assign address set
        Customer customer= new Customer("Martinez","Joey");
        customer.setAddresses(addresses);
        customerRepository.save(customer);

    }
}
