package com.d288.idris.bootstrap;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.d288.idris.dao.CartItemRepository;
import com.d288.idris.dao.CountryRepository;
import com.d288.idris.dao.CustomerRepository;
import com.d288.idris.dao.DivisionRepository;
import com.d288.idris.dao.ExcursionRepository;
import com.d288.idris.dao.VacationRepository;
import com.d288.idris.entities.CartItem;
import com.d288.idris.entities.Country;
import com.d288.idris.entities.Customer;
import com.d288.idris.entities.Division;
import com.d288.idris.entities.Excursion;
import com.d288.idris.entities.Vacation;

@Component
public class SampleDataInitializer implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private VacationRepository vacationRepository;

    @Autowired
    private ExcursionRepository excursionRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if any customer data exists
        if (customerRepository.count() < 6) {
            initializeSampleData();
        }
    }

    private void initializeSampleData() {
        // Initialize countries
        Country usCountry = new Country();
        usCountry.setCountry_name("United States");
        countryRepository.save(usCountry);
        
        Country ukCountry = new Country();
        ukCountry.setCountry_name("United Kingdom");
        countryRepository.save(ukCountry);
        
        Country canadaCountry = new Country();
        canadaCountry.setCountry_name("Canada");
        countryRepository.save(canadaCountry);
        // Initialize divisions
        Division arizona = new Division();
        arizona.setDivision_name("Arizona");
        arizona.setCountry(usCountry);

        Division texas = new Division();
        texas.setDivision_name("Texas");
        texas.setCountry(ukCountry);

        Division ontario = new Division();
        ontario.setDivision_name("Ontario");
        ontario.setCountry(canadaCountry);

        divisionRepository.save(arizona);
        divisionRepository.save(texas);
        divisionRepository.save(ontario);

        // Initialize sample customers
        Customer customer1 = new Customer();
        customer1.setFirstName("Adan");
        customer1.setLastName("Alio");
        customer1.setAddress("555 One Street");
        customer1.setDivision(arizona);
        customer1.setPostal_code("77777");
        customer1.setPhone("555-666-7777");

        Customer customer2 = new Customer();
        customer2.setFirstName("Jane");
        customer2.setLastName("Smith");
        customer2.setAddress("456 Oak Avenue");
        customer2.setDivision(texas);
        customer2.setPostal_code("73301");
        customer2.setPhone("234-567-8901");

        Customer customer3 = new Customer();
        customer3.setFirstName("Robert");
        customer3.setLastName("Johnson");
        customer3.setAddress("789 Pine Road");
        customer3.setDivision(ontario);
        customer3.setPostal_code("M4B 1B3");
        customer3.setPhone("345-678-9012");

        Customer customer4 = new Customer();
        customer4.setFirstName("Emily");
        customer4.setLastName("Davis");
        customer4.setAddress("101 Maple Street");
        customer4.setDivision(arizona);
        customer4.setPostal_code("85002");
        customer4.setPhone("456-789-0123");

        Customer customer5 = new Customer();
        customer5.setFirstName("Michael");
        customer5.setLastName("Brown");
        customer5.setAddress("202 Birch Lane");
        customer5.setDivision(texas);
        customer5.setPostal_code("73302");
        customer5.setPhone("567-890-1234");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        customerRepository.save(customer4);
        customerRepository.save(customer5);

        // Initialize vacations
        Vacation vacation1 = new Vacation();
        vacation1.setVacation_title("Tropical Paradise");
        vacation1.setDescription("A relaxing getaway to the beautiful beaches.");
        vacation1.setTravel_price(new BigDecimal("999.99"));
        vacation1.setImage_URL("https://example.com/tropical.jpg");
        // vacation1.setCreate_date(new Date());
        // vacation1.setLast_update(new Date());

        Vacation vacation2 = new Vacation();
        vacation2.setVacation_title("Mountain Adventure");
        vacation2.setDescription("An exciting adventure in the mountains.");
        vacation2.setTravel_price(new BigDecimal("1299.99"));
        vacation2.setImage_URL("https://example.com/mountain.jpg");
        // vacation2.setCreate_date(new Date());
        // vacation2.setLast_update(new Date());

        vacationRepository.save(vacation1);
        vacationRepository.save(vacation2);

        // Initialize excursions
        Excursion excursion1 = new Excursion();
        excursion1.setExcursion_title("Snorkeling");
        excursion1.setExcursion_price(new BigDecimal("199.99"));
        excursion1.setImage_URL("https://example.com/snorkeling.jpg");
        // excursion1.setCreate_date(new Date());
        // excursion1.setLast_update(new Date());
        excursion1.setVacation(vacation1);

        Excursion excursion2 = new Excursion();
        excursion2.setExcursion_title("Hiking");
        excursion2.setExcursion_price(new BigDecimal("149.99"));
        excursion2.setImage_URL("https://example.com/hiking.jpg");
        excursion2.setCreate_date(new Date());
        excursion2.setLast_update(new Date());
        excursion2.setVacation(vacation2);

        excursionRepository.save(excursion1);
        excursionRepository.save(excursion2);

        // Initialize cart items
        CartItem cartItem1 = new CartItem();
        cartItem1.setVacation(vacation1);
        cartItem1.setExcursions(new HashSet<>(Set.of(excursion1)));
        // cartItem1.setCreate_date(new Date());
        // cartItem1.setLast_update(new Date());

        CartItem cartItem2 = new CartItem();
        cartItem2.setVacation(vacation2);
        cartItem2.setExcursions(new HashSet<>(Set.of(excursion2)));
        // cartItem2.setCreate_date(new Date());
        // cartItem2.setLast_update(new Date());

        cartItemRepository.save(cartItem1);
        cartItemRepository.save(cartItem2);
    }
}

