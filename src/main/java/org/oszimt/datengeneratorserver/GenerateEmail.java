package org.oszimt.datengeneratorserver;

public class GenerateEmail {

        public static String generateRandomEmail() {
            String emailAddress = "";
            String alphabet = "abcdefghijklmnopqrstuvwxyz";
            String domain = "gmail.com";

            while (emailAddress.length() < 10) {
                int character = (int) (Math.random() * 26);
                emailAddress += alphabet.substring(character, character + 1);
            }

            emailAddress += Integer.valueOf((int) (Math.random() * 99)).toString();
            emailAddress += "@" + domain;

            return emailAddress;
        }
    }