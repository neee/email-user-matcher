package ru.serdyuk.email.address.matchers;

import java.util.Map;
import java.util.Set;

/**
 * Created by Sergey Serdyuk on 09/12/2018.
 */
public interface Matcher {

    void match(String data);

    Map<String, Set<String>> getResult();
}
