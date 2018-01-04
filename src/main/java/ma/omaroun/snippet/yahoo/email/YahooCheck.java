package ma.omaroun.snippet.yahoo.email;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import ma.omaroun.snippet.email.EmailCheckAbstract;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class YahooCheck extends EmailCheckAbstract {

    private static final String[] SUFFIXS = {"yahoo.com"};
    private static final String YAHOO_EMAIL_CHECK_SERVICE = "https://edit.yahoo.com/forgot?stage=fe100";
    private static final String DESCRIPTION = "YAHOO";

    private enum Singleton {
        INSTANCE;
        YahooCheck gmailCheck = new YahooCheck();
    }

    public static YahooCheck getInstace() {
        return Singleton.INSTANCE.gmailCheck;
    }

    private YahooCheck() {
    }

    @Override
    public List<String> getSuffixs() {
        return Arrays.asList(SUFFIXS);
    }

    @Override
    public boolean exist(String email) throws IOException {

        final WebClient webClient = new WebClient(BrowserVersion.CHROME);

        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);

        final HtmlPage page = webClient.getPage(YAHOO_EMAIL_CHECK_SERVICE);

        final HtmlForm form = page.querySelector("form.pure-form");

        final HtmlInput txtUsername = form.getInputByName("username");
        final HtmlButton submitButton = form.getButtonByName("verifyYid");

        txtUsername.setValueAttribute(email);

        final HtmlPage resPage = submitButton.click();

        return resPage.querySelectorAll("div.error-msg").size() == 0;

    }

    @Override
    public String toString() {
        return DESCRIPTION;
    }
}
