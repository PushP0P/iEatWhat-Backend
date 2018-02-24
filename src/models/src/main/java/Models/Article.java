package Models;

import javax.persistence.Column;
import javax.persistence.OneToMany;

public class Article extends Model{
    @Column
    private String id;
    @Column
    private String title;
    @Column
    private String blurb;
    @OneToMany
    private ArticleSection[] sections;

    public Article(){}

    public Article(String id, String title, String blurb, ArticleSection... articleSections) {
        setId(id);
        setTitle(title);
        setBlurb(blurb);
        setSections(articleSections);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBlurb() {
        return blurb;
    }

    public ArticleSection[] getSections() {
        return sections;
    }

    private void setId(String id) {
        this.id = id;
    }

    private void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setSections(ArticleSection[] sections) {
        this.sections = sections;
    }

    public static Article demoData() {
        ArticleSection[] sections;
        sections = new ArticleSection[3];
        String paragraph = "Zombie ipsum reversus ab viral inferno, nam rick grimes malum cerebro. De carne lumbering animata corpora quaeritis. Summus brains sit\\u200B\\u200B, morbo vel maleficia? De apocalypsi gorger omero undead survivor dictum mauris. Hi mindless mortuis soulless creaturas, imo evil stalking monstra adventus resi dentevil vultus comedat cerebella viventium. Qui animated corpse, cricket bat max brucks terribilem incessu zomby. The voodoo sacerdos flesh eater, suscitat mortuos comedere carnem virus. Zonbi tattered for solum oculi eorum defunctis go lum cerebro. Nescio brains an Undead zombies. Sicut malus putrid voodoo horror. Nigh tofth eliv ingdead. Zombie ipsum reversus ab viral inferno, nam rick grimes malum cerebro. De carne lumbering animata corpora quaeritis. Summus brains sit\\u200B\\u200B, morbo vel maleficia? De apocalypsi gorger omero undead survivor dictum mauris. Hi mindless mortuis soulless creaturas, imo evil stalking monstra adventus resi dentevil vultus comedat cerebella viventium. Qui animated corpse, cricket bat max brucks terribilem incessu zomby. The voodoo sacerdos flesh eater, suscitat mortuos comedere carnem virus. Zonbi tattered for solum oculi eorum defunctis go lum cerebro. Nescio brains an Undead zombies. Sicut malus putrid voodoo horror. Nigh tofth eliv ingdead.";
        sections[0] = new ArticleSection("Zombie", paragraph);
        sections[1] = new ArticleSection("Zombies", paragraph);
        sections[2] = new ArticleSection("ZOMBIEZ!!", paragraph);
        return new Article("1235ASD", "Zombie Ipsum", "Brains!!!!", sections);
    }
}