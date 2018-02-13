import java.io.IOException;

public class GAuthManager {
    private SignIn signIn;

    public GAuthManager() {
        this.signIn = new SignIn();
    }

    public String getGeneratedAuthURL$() {
        String url = null;
        try {
            url = this.signIn.generateAuthURL();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("GAuth Generated URL \n" + url);
        return url;
    }
}