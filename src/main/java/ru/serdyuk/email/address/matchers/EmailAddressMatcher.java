package ru.serdyuk.email.address.matchers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Arrays.stream;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

/**
 * Created by Sergey Serdyuk on 09/12/2018.
 */
public class EmailAddressMatcher implements Matcher {

    public static final String SEPARATOR_USER_ID_ADDRESS = "->";
    public static final String ADDRESS_SEPARATOR = ",";
    private static final Logger log = LoggerFactory.getLogger(EmailAddressMatcher.class.getName());

    private Map<String, String> emailDictionary = new HashMap<>();
    private Map<String, Set<String>> usersEmails = new HashMap<>();

    public void match(String input) {

        log.debug("input data: {}", input);

        List<String> checkingEmails = getCheckingEmails(input);
        log.debug("checking emails: {}", checkingEmails);
        String userId = getUserId(input, checkingEmails);
        log.debug("userId: {}", userId);
        updateDictionary(emailDictionary, checkingEmails, userId);

        Optional<Set<String>> userEmails = ofNullable(usersEmails.get(userId));
        if (userEmails.isPresent()) {
            userEmails.get().addAll(checkingEmails);
        } else {
            usersEmails.put(userId, new HashSet<>(checkingEmails));
        }
    }

    @Override
    public Map<String, Set<String>> getResult() {
        return usersEmails;
    }

    private List<String> getCheckingEmails(String input) {
        return stream(parseEmailAddresses(input)
                .split(ADDRESS_SEPARATOR))
                .map(String::trim)
                .collect(toList());
    }

    private String getUserId(String input, List<String> checkingEmails) {
        return checkingEmails.stream()
                .filter(emailDictionary::containsKey)
                .map(emailDictionary::get)
                .findFirst()
                .orElse(parseUserId(input));
    }

    private void updateDictionary(Map<String, String> emailDictionary, List<String> checkingEmails, String userId) {
        checkingEmails.forEach(email -> emailDictionary.put(email, userId));
    }

    private static String parseEmailAddresses(String input) {
        return input.substring(input.indexOf(SEPARATOR_USER_ID_ADDRESS) + SEPARATOR_USER_ID_ADDRESS.length());
    }

    private static String parseUserId(String input) {
        return input.substring(0, input.indexOf(SEPARATOR_USER_ID_ADDRESS)).trim();
    }
}
