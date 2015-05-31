package pl.thotael.RandomAww.logic;

import java.util.Random;

/**
 * Created by SeBa on 2015-05-31.
 */
public class AnimalPageGenerator {

    private static final String DOG_URL = "http://www.randomdoggiegenerator.com/randomdoggie.php";
    private static final String CAT_URL = "http://www.randomkittengenerator.com/images/cats/rotator.php";

    private static String getHtml(String url) {
        return "<html><body style='background-color: black'><img style='-webkit-user-select: none; width: 100%;' src='" + url + "'></body></html>";
    }

    private String getDogOrCatUrl() {
        String url;
        if (new Random().nextBoolean()) {
            url = DOG_URL;
        } else {
            url = CAT_URL;
        }
        return url;
    }

    public String getDogOrCatHtml() {
        return getHtml(getDogOrCatUrl());
    }

    public String getDogHtml() {
        return getHtml(DOG_URL);
    }

    public String getCatHtml() {
        return getHtml(CAT_URL);
    }
}
