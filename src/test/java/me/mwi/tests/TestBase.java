package me.mwi.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import me.mwi.config.DataConfig;
import me.mwi.config.WebDriverProvider;
import me.mwi.helpers.Attach;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static me.mwi.helpers.CustomApiListener.withCustomTemplates;

public class TestBase {

    public static DataConfig dataConfig = ConfigFactory.create(DataConfig.class);

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("Allure", new AllureSelenide());
        WebDriverProvider.configuration();
        RestAssured.filters(withCustomTemplates());
    }

    @AfterEach
    void addAttach() {
        Attach.browserConsoleLogs();
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
    }

}
