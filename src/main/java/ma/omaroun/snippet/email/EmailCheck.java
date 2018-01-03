package ma.omaroun.snippet.email;

import java.io.IOException;
import java.util.List;

public interface EmailCheck {

    boolean isValide(String email);

    List<String> getSuffixs();

    boolean exist(String email) throws IOException;

    String info();

}
