import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.MyListsPageObject;
import org.junit.Test;

public class Ex6 extends CoreTestCase {

    @Test
    public void testSaveTwoArticlesAndDeleteOne() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);

        searchPageObject.clickSkipButton();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");

        String firstArticleTitle = articlePageObject.getArticleTitle();
        articlePageObject.addArticleToMyList("My List");

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Python");
        searchPageObject.clickByArticleWithSubstring("Python (programming language)");

        String secondArticleTitle = articlePageObject.getArticleTitle();
        articlePageObject.addArticleToMyList("My List");

        myListsPageObject.openMyLists();
        myListsPageObject.vewArticlePage();

        myListsPageObject.swipeArticleToDelete(firstArticleTitle);
        myListsPageObject.waitForArticleToDisappearByTitle(firstArticleTitle);

        myListsPageObject.openArticleByTitle(secondArticleTitle);

        String titleAfterDeletion = articlePageObject.getArticleTitle();
        assertEquals("Wrong article is displayed", secondArticleTitle, titleAfterDeletion);
    }
}