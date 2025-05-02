import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import org.junit.Test;

public class Ex5 extends CoreTestCase {

    @Test
    public void testSaveAndDeleteArticleFromList() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        NavigationUI navigationUI = new NavigationUI(driver);

        searchPageObject.clickSkipButton();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");

        String articleTitle = articlePageObject.getArticleTitle();
        articlePageObject.addArticleToMyList("Learning");

        articlePageObject.closeArticle();

        navigationUI.openSavedLists();
        myListsPageObject.vewArticlePage();

        myListsPageObject.waitForArticleToAppearByTitle(articleTitle);
        myListsPageObject.openArticleByTitle(articleTitle);

        articlePageObject.removeArticleFromSaved();
        myListsPageObject.swipeArticleToDelete(articleTitle);

        myListsPageObject.waitForArticleToDisappearByTitle(articleTitle);
    }
}