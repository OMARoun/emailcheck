package ma.omaroun.snippet.outlook.email;

import ma.omaroun.snippet.email.EmailCheckAbstract;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class OutlookCheck extends EmailCheckAbstract {

    private static final String[] SUFFIXS = {"outlook.com"};
    private static final String OUTLOOK_EMAIL_CHECK_SERVICE = "https://login.live.com/GetCredentialType.srf";
    private static final String DESCRIPTION = "OUTLOOK";

    private enum Singleton {
        INSTANCE;
        OutlookCheck gmailCheck = new OutlookCheck();
    }

    public static OutlookCheck getInstace() {
        return Singleton.INSTANCE.gmailCheck;
    }

    private OutlookCheck() {
    }

    @Override
    public List<String> getSuffixs() {
        return Arrays.asList(SUFFIXS);
    }

    @Override
    public boolean exist(String email) throws IOException {

        String account = email.split("@")[0];

        String response = Jsoup.connect(OUTLOOK_EMAIL_CHECK_SERVICE)
                .userAgent("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
                .ignoreContentType(true)
                .method(Connection.Method.POST)
                .header("content-type", "application/json")
                .requestBody("{\"username\":\"" + email + "\"}")
                .execute()
                .body();

        return !response.contains("\"IfExistsResult\":1");

    }

    @Override
    public String toString() {
        return DESCRIPTION;
    }
}
