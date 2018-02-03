import com.fasterxml.jackson.databind.ObjectMapper;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import java.util.HashMap;
import java.util.Map;

public class PublicAPI {

    public Map<String, Handler> postRequestHandlers() {
        Map<String, Handler> handlers = new HashMap<>();

        return handlers;
    }

    public Map<String, Handler> getRequestHandlers() {
        Map<String, Handler> handlers = new HashMap<>();
        handlers.put("", (ctx) -> ctx.render("FOO BAT METAL"));
        handlers.put("public", (ctx) -> ctx.render("Public API Dynamic Success!"));
        handlers.put(
            "demo",
            (ctx) -> {
                ObjectMapper om = new ObjectMapper();
//                final JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
//                ObjectNode articleJSON = jsonNodeFactory.objectNode();
                Article article = dummyContent();
                String articleAsString = om.writeValueAsString(article);
                ctx.render(articleAsString);
            });
        return handlers;
    }

    private void eventHandler(Context ctx) {

    }

    public static Article dummyContent() {
        ArticleSection[] sections;
        sections = new ArticleSection[3];
        String paragraph = "Zombie ipsum reversus ab viral inferno, nam rick grimes malum cerebro. De carne lumbering animata corpora quaeritis. Summus brains sit\\u200B\\u200B, morbo vel maleficia? De apocalypsi gorger omero undead survivor dictum mauris. Hi mindless mortuis soulless creaturas, imo evil stalking monstra adventus resi dentevil vultus comedat cerebella viventium. Qui animated corpse, cricket bat max brucks terribilem incessu zomby. The voodoo sacerdos flesh eater, suscitat mortuos comedere carnem virus. Zonbi tattered for solum oculi eorum defunctis go lum cerebro. Nescio brains an Undead zombies. Sicut malus putrid voodoo horror. Nigh tofth eliv ingdead. Zombie ipsum reversus ab viral inferno, nam rick grimes malum cerebro. De carne lumbering animata corpora quaeritis. Summus brains sit\\u200B\\u200B, morbo vel maleficia? De apocalypsi gorger omero undead survivor dictum mauris. Hi mindless mortuis soulless creaturas, imo evil stalking monstra adventus resi dentevil vultus comedat cerebella viventium. Qui animated corpse, cricket bat max brucks terribilem incessu zomby. The voodoo sacerdos flesh eater, suscitat mortuos comedere carnem virus. Zonbi tattered for solum oculi eorum defunctis go lum cerebro. Nescio brains an Undead zombies. Sicut malus putrid voodoo horror. Nigh tofth eliv ingdead.";
        sections[0] = new ArticleSection("Zombie", paragraph);
        sections[1] = new ArticleSection("Zombies", paragraph);
        sections[2] = new ArticleSection("ZOMBIEZ!!", paragraph);
        return new Article("1235ASD", "Zombie Ipsum", "Brains!!!!", sections);
    }
}

