import driverSetup.BaseClass;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pageobject.LandingPage;
import pageobject.LoginPage;
import pageobject.SearchPage;

public class HepsiburadaWithLogin {

    // Kullanıcı hepsiburada.com sitesini ziyaret eder
    @Test
    public void test1_IsHepsiburadaPageCorrect(){
        BaseClass.SetUpBrowser();
        Assert.assertEquals(LandingPage.GetPageLogoLabel().getAttribute("title"),"Hepsiburada");
    }

    // Kullanıcı giriş işlemi yapılır ve Yönlendirmeden sonra anasayfada kullanıcı giriş işleminin yapıldığı doğrulanır
    @Test
    public void test2_HepsiburadaLoginValidation() throws InterruptedException {
        Assert.assertTrue(LoginPage.LoginHepsiburada());
    }

    //Kullanıcı, burada satın almak istediği ürün için arama yapacaktır.-- iphone aranır
    @Test
    public void test3_IsSearchWordSame() throws InterruptedException{
        String searchWord = "iphone" ;
        SearchPage.MakeProductSearch(searchWord);
        String wordIsAfterSearch = SearchPage.GetSearchResult();
        Assert.assertEquals(searchWord, wordIsAfterSearch);
    }

    // Kullanıcı, Arama sonucunda ekrana gelen ürün listesinden (veya tek bir sonuç da dönmüş olabilir) ürün seçer.
    @Test
    public void test4_IsMyProductNameTrue() throws InterruptedException {
        String firstSelectedProductName = SearchPage.GetSearchResultList().get(0).getText();
        SearchPage.ClickFirstProduct();
        String productNameAfterClicked = SearchPage.GetClickedItemProductName();
        Assert.assertEquals(firstSelectedProductName, productNameAfterClicked);
    }

    // Seçilen ürün için 2 tane farklı satıcıdan ürün seçilip sepete eklenir.Seçilen ürünün doğru olarak eklendiği ‘Sepetim’ sayfasında doğrulanmalıdır.
    @Test
    public void test5_IsVerifiedToAddedProductCart() throws InterruptedException {
        SearchPage.ClickProductsAddToCart();
    }
}
