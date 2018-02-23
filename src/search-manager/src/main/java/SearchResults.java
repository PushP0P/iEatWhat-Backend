import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchResults {
    List<?> matches;

    public SearchResults(Class<?>[] items) {
        List<Class<?>> list = new ArrayList<>();
        Collections.addAll(list, items);
        setMatches(list);
    }

    public void setMatches(List<?> matches) {
        this.matches = matches;
    }

    public List<?> getMatches() {
        return matches;
    }
}
