package ma.omaroun.snippet.email;

import ma.omaroun.snippet.google.email.GmailCheck;
import ma.omaroun.snippet.outlook.email.OutlookCheck;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Email {

    private static List<EmailCheck> emailChecks = new ArrayList<EmailCheck>();
    static {
        emailChecks.add(GmailCheck.getInstace());
        emailChecks.add(OutlookCheck.getInstace());
    }

    public static class CheckResult {

        public static final String NOT_VALIDE = "NOT VALIDE";

        private String info;
        private String email;
        private boolean exist;

        CheckResult(String email, String info, boolean exist) {
            this.email = email;
            this.info = info;
            this.exist = exist;
        }

        public String getEmail() {
            return email;
        }

        public String getInfo() {
            return info;
        }

        public boolean isExist() {
            return exist;
        }

        @Override
        public String toString() {
            return email + " : " + info + " [" + (exist ? "EXIST" : "NOT EXIST") + "]";
        }
    }

    public static CheckResult check(String email)throws IOException {
        boolean cheked = false;
        for (EmailCheck emailCheck : emailChecks) {
            if (!cheked && emailCheck.isValide(email)) {
                return new CheckResult(email, emailCheck.info(), emailCheck.exist(email));
            }
        }
        return new CheckResult(email, CheckResult.NOT_VALIDE, false);

    }
    public static List<CheckResult> check(List<String> emails)throws IOException {
        List<CheckResult> checkResults = new ArrayList<CheckResult>(emails.size());
        for (String email : emails) {
            boolean cheked = false;
            for (EmailCheck emailCheck : emailChecks) {
                if (!cheked && emailCheck.isValide(email)) {
                    checkResults.add(new CheckResult(email, emailCheck.info(), emailCheck.exist(email)));
                    cheked = true;
                }
            }
            if (!cheked) {
                checkResults.add(new CheckResult(email, "NOT VALIDE", false));
            }
        }
        return checkResults;
    }


}
