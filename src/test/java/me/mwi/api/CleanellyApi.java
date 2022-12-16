package me.mwi.api;

import me.mwi.tests.TestBase;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.URLENC;
import static me.mwi.helpers.CustomApiListener.withCustomTemplates;
import static me.mwi.specs.AddItemToBasketRequestSpec.addItemToBasketRequestSpec;
import static me.mwi.specs.AddItemToBasketResponseSpec.addItemToBasketResponseSpec;

public class CleanellyApi extends TestBase {

    private String phpSessID;

    public CleanellyApi setCookie() {
        String requestBody = "AUTH_FORM=Y&TYPE=AUTH&USER_LOGIN=" + dataConfig.userName()
                + "&USER_PASSWORD=" + dataConfig.userPassword();
        phpSessID = given()
                .contentType(URLENC)
                .filter(withCustomTemplates())
                .log().all()
                .body(requestBody)
                .when()
                .post("https://www.cleanelly.ru/auth/?login=yes")
                .then()
                .extract().response().getCookie("PHPSESSID");
        return this;
    }

    public CleanellyApi logIn() {
        open("local/templates/aspro_next_2019/images/scroll.png");
        getWebDriver().manage().deleteAllCookies();
        getWebDriver().manage().addCookie(new Cookie(
                "PHPSESSID", phpSessID
        ));
        return this;
    }

    public void addItemToBasket() {
        given()
                .spec(addItemToBasketRequestSpec)
                .cookie("PHPSESSID", phpSessID)
                .body("add_item=Y&item=" + dataConfig.itemID())
                .when()
                .post()
                .then()
                .spec(addItemToBasketResponseSpec);
    }
}

