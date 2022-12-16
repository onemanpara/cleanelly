package me.mwi.specs;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.URLENC;
import static me.mwi.helpers.CustomApiListener.withCustomTemplates;

public class AddItemToBasketRequestSpec {

    public static RequestSpecification addItemToBasketRequestSpec = with()
            .contentType(URLENC)
            .filter(withCustomTemplates())
            .baseUri("https://www.cleanelly.ru")
            .basePath("/ajax/item.php")
            .log().all();

}
