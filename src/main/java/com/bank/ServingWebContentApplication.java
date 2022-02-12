/**
 * SpringBootApplication
 * 
 * Starts a server using BankController
 * 
 * @author Erik Rundberg
 * @version 1.0
 * @since 2022-02-11
 */

package com.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServingWebContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServingWebContentApplication.class, args);
    }

}