package ma.omaroun.snippet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ma.omaroun.snippet.email.Email;
import ma.omaroun.snippet.google.email.GmailCheck;

/**
 * @author aboufaris
 */
public class App {

    public static void main(String[] args) throws IOException {

        //Check one email
        System.out.println(Email.check("lesgenies@gmail.com"));

        //Check a list of emails
        System.out.println(
                Email.check(Arrays.asList(
                        "lesanges@gmail.com",
                        "lesanges.popolili@gmail.com",
                        "lesanges@outlook.com"
                )));


    }

}
