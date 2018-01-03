package ma.omaroun.snippet.email;

import java.util.regex.Pattern;

abstract public class EmailCheckAbstract implements EmailCheck {

    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private static Pattern pattern;

    static {
        pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public boolean isValide(String email) {
        if (!pattern.matcher(email).matches()) {
            return false;
        }

        return getSuffixs().contains(email.split("@")[1]);
    }

    @Override
    public String info() {
        return toString();
    }
}
