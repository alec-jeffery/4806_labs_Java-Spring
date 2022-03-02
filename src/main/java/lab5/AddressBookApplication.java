package lab5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AddressBookApplication {

    private static final Logger log = LoggerFactory.getLogger(AddressBookApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AddressBookApplication.class);
    }

    @Bean
    public CommandLineRunner demo(AddressBookRepository addressBookRepository, BuddyInfoRepository buddyInfoRepository) {
        return (args) -> {
            // save a few buddies
            BuddyInfo buddyGenerator;
            AddressBook addressBook = new AddressBook();
            List<BuddyInfo> buddyInfoList = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                buddyGenerator = new BuddyInfo();
                buddyGenerator.setName("Greg" + i);
                buddyGenerator.setPhoneNumber("613-325-432" + i);
                buddyInfoList.add(buddyGenerator);
                buddyInfoRepository.save(buddyGenerator);
            }
            addressBook.addBuddyInfoList(buddyInfoList);
            addressBookRepository.save(addressBook);

            // fetch all buddies
            log.info("buddies found with findAll():");
            log.info("-------------------------------");
            for (BuddyInfo buddy : buddyInfoRepository.findAll()) {
                log.info(buddy.toString());
            }
            log.info("");

//            // fetch an individual customer by ID
//            Customer customer = repository.findById(1L);
//            log.info("Customer found with findById(1L):");
//            log.info("--------------------------------");
//            log.info(customer.toString());
//            log.info("");

//            // fetch customers by last name
//            log.info("Customer found with findByLastName('Bauer'):");
//            log.info("--------------------------------------------");
//            repository.findByLastName("Bauer").forEach(bauer -> {
//                log.info(bauer.toString());
//            });
//            // for (Customer bauer : repository.findByLastName("Bauer")) {
//            //  log.info(bauer.toString());
//            // }
//            log.info("");
        };
    }

}