package Pages;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public class GroupPageArray {
    private final String GROUPS = "a.group-detailed-card_name";
    private ElementsCollection  groupCards;


    public GroupPageArray() {
        groupCards = $$(GROUPS);
    }

    public String getGroupName(int index) {
        return groupCards.get(index).text();
    }

    public GroupOnePage openGroupByIndex(int index) {
        groupCards.get(index).click();
        return new GroupOnePage();
    }
}
