package ru.serdyuk;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Arrays.stream;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * Created by Sergey Serdyuk on 09/12/2018.
 */

public class EmailMatcher {

    public static final String SEPARATOR_USER_ID_ADDRESS = "->";
    private static final Logger log = LoggerFactory.getLogger(EmailMatcher.class.getName());

    public static void main(String[] args) {
        Map<String, String> emailDictionary = new HashMap<>();
        Map<String, Set<String>> usersEmails = new HashMap<>();

        String inputData = "user1 -> xxx@ya.ru, foo@gmail.com, lol@mail.ru\n"
                + "user2 -> foo@gmail.com, ups@pisem.net\n"
                + "user3 -> xyz@pisem.net, vasya@pupkin.com\n"
                + "user4 -> ups@pisem.net, aaa@bbb.ru\n"
                + "user5 -> xyz@pisem.net";

        Scanner scanner = new Scanner(inputData);

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            log.debug("input: {}", input);

            List<String> checkingEmails = stream(parseEmailAddresses(input)
                    .split(","))
                    .map(String::trim)
                    .collect(toList());

            String userId = checkingEmails.stream()
                    .filter(emailDictionary::containsKey)
                    .map(emailDictionary::get)
                    .findFirst()
                    .orElse(parseUserId(input));

            checkingEmails.forEach(email -> emailDictionary.put(email, userId));

            Optional<Set<String>> userEmails = ofNullable(usersEmails.get(userId));
            if (userEmails.isPresent()) {
                userEmails.get().addAll(checkingEmails);
            } else {
                usersEmails.put(userId, new HashSet<>(checkingEmails));
            }
        }
        log.info("result: {}", usersEmails);
    }

    private static String parseEmailAddresses(String input) {
        return input.substring(input.indexOf(SEPARATOR_USER_ID_ADDRESS) + SEPARATOR_USER_ID_ADDRESS.length());
    }

    private static String parseUserId(String input) {
        return input.substring(0, input.indexOf(SEPARATOR_USER_ID_ADDRESS)).trim();
    }
}