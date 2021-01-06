package com.example.springboot;

import com.example.springboot.dbEntries.Billionair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class RelationalDataAccessApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RelationalDataAccessApplication.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception {

        log.info("Creating tables");

        jdbcTemplate.execute("DROP TABLE comments IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE comments(" +
                "id SERIAL, userName VARCHAR(255), content VARCHAR(255))");
        jdbcTemplate.execute("DROP TABLE billionairs IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE billionairs(" +
                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255), career VARCHAR(255))");

//         Split up the array of whole names into an array of first/last names
        List<Object[]> splitUpNames = Arrays.asList("John ThIsIsOBviOUsLyAThReE", "Jeff FIRST").stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());
        List<Object[]> splitUpNames2 = Arrays.asList("John ThIsIsOBviOUsLyAThReE", "Jeff FIRST", "Josh Bloch", "Josh Long").stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        // Use a Java 8 stream to print out each tuple of the list
        splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));
        splitUpNames2.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));



        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO comments(userName, content) VALUES (?,?)", splitUpNames);
        jdbcTemplate.batchUpdate("INSERT INTO billionairs(first_name, last_name) VALUES (?,?)", splitUpNames2);

        log.info("Database with entry might have been created");

    }

}
