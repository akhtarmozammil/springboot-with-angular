package mozammil.com.fakebook.operation;

import java.util.Base64;

public class Authentication {

    private Base64.Encoder encoder = Base64.getEncoder();
    private Base64.Decoder decoder = Base64.getDecoder();

    public String encodePassword(String password){
        String encodePassword = encoder.encodeToString(password.getBytes());
        return encodePassword;
    }

    private String decodePassword(String encodedPassword){
        String password = new String(decoder.decode(encodedPassword));
        return password;
    }

    public boolean isValidCredential(String password, String encodedPassword){
        return password.matches(decodePassword(encodedPassword));
    }
}
