package Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;


public class PostEverythingArray {
    private final String POSTS_CONTAINER = "div.feed-list";
    private final String POST_ITEM = ".feed-w";
    private ElementsCollection groupPosts;

    public PostEverythingArray() {
        this.groupPosts = $$(POSTS_CONTAINER + " " + POST_ITEM);
    }

    public SelenideElement getPostByIndex(int index) {
        return groupPosts.get(index);
    }

    public int getPostCount() {
        return groupPosts.size();
    }

    public String getPostHeading(int index) {
        return groupPosts.get(index).$(".feed_h_heading").getText();
    }

    public String getPostTime(int index) {
        return groupPosts.get(index).$("time").getText();
    }

    public ElementsCollection getAllPosts() {
        this.groupPosts = $$(POSTS_CONTAINER + " " + POST_ITEM);
        return groupPosts;
    }


}