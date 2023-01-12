package org.oszimt.datengeneratorserver;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped

public class GenerateEmail {

        public String[] generateRandomEmail(int n) {
            String[] emails = new String[n];

            for(int i = 0; i< n ; i++) {
                String emailAddress = "";
                String alphabet = "abcdefghijklmnopqrstuvwxyz";
                String domain = "gmail.com";

                while (emailAddress.length() < 10) {
                    int character = (int) (Math.random() * 26);
                    emailAddress += alphabet.substring(character, character + 1);
                }

                emailAddress += Integer.valueOf((int) (Math.random() * 99)).toString();
                emailAddress += "@" + domain;
                emails[i] = emailAddress;
            }
            
            return emails;
        }
    }