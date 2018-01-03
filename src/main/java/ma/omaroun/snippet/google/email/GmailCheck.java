package ma.omaroun.snippet.google.email;

import ma.omaroun.snippet.email.EmailCheckAbstract;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GmailCheck extends EmailCheckAbstract {

    private static final String[] SUFFIXS = {"gmail.com"};
    private static final String GMAIL_EMAIL_CHECK_SERVICE = "https://accounts.google.com/InputValidator?resource=SignUp";
    private static final String DESCRIPTION = "GMAIL";

    private enum Singleton {
        INSTANCE;
        GmailCheck gmailCheck = new GmailCheck();
    }

    public static GmailCheck getInstace() {
        return Singleton.INSTANCE.gmailCheck;
    }

    private GmailCheck() {
    }

    @Override
    public List<String> getSuffixs() {
        return Arrays.asList(SUFFIXS);
    }

    @Override
    public boolean isValide(String email) {
        if (!super.isValide(email))
            return false;

        //specific gmail account test
        String account = email.split("@")[0];
        return !(account.contains("_") || account.endsWith(".") || account.startsWith("."));
    }

    @Override
    public boolean exist(String email) throws IOException {

        String account = email.split("@")[0];

        String response = Jsoup.connect(GMAIL_EMAIL_CHECK_SERVICE)
                .userAgent("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
                .ignoreContentType(true)
                .method(Connection.Method.POST)
                .header("content-type", "application/json")
                .requestBody(
                        "{\"input01\":{\"Input\":\"GmailAddress\",\"GmailAddress\":\"" + account + "\",\"FirstName\":\"\",\"LastName\":\"\"},\"Locale\":\"fr\"}")
                .execute()
                .body();

        return !response.contains("\"true\"");

    }

    @Override
    public String toString() {
        return DESCRIPTION;
    }
}
