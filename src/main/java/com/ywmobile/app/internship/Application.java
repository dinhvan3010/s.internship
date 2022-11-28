package com.ywmobile.app.internship;

import com.ywmobile.app.internship.DAO.UserDAO;
import com.ywmobile.app.internship.enums.UserRole;
import com.ywmobile.app.internship.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Application implements CommandLineRunner {
    public static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    UserDAO userDAO;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void run(String... args) throws Exception {

        LOGGER.info("========= ADMIN - Initialization - Begin");

        if (!userDAO.getExistByUsername("admin")) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setRole(UserRole.ADMIN);
            user.setEnabled(true);
            userDAO.save(user);
            LOGGER.info("========= ADMIN - Initialization - SUCCESS");
        }
    }
}
