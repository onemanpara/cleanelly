package me.mwi.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class OrderPage {

    private final SelenideElement
            geoInput = $("#bx-soa-region input" +
            ":not([disabled=disabled]):not([style=\"display: none;\"]):not([type=hidden])"),
            geoTip = $(".dropdown-item"),
            deliveryContainer = $("#bx-soa-delivery:not(sourceInfo)"),
            closeSaleBoxButton = $(".fancybox-close"),
            paymentSystemContainer = $("#bx-soa-paysystem"),
            buyerInfoContainer = $("#bx-soa-properties"),
            orderDescription = $("#orderDescription");

    public OrderPage setGeoInput(String city) {
        geoInput.setValue(city);
        geoTip.shouldBe(visible).click();
        return this;
    }

    public OrderPage setDelivery(String deliveryName) {
        deliveryContainer.$(byText(deliveryName)).parent().click();
        return this;
    }

    public OrderPage setPaymentSystem(String paymentSystem) {
        paymentSystemContainer.$(byText(paymentSystem)).parent().click();
        return this;
    }

    public OrderPage setFIO(String FIO) {
        $("#loading_screen").shouldNotBe(visible);
        buyerInfoContainer.$(byText("Ф.И.О.")).parent().$("input").clear();
        buyerInfoContainer.$(byText("Ф.И.О.")).parent().$("input").setValue(FIO);
        return this;
    }

    public OrderPage setEmail(String email) {
        $("#loading_screen").shouldNotBe(visible);
        buyerInfoContainer.$(byText("E-Mail")).parent().$("input").clear();
        buyerInfoContainer.$(byText("E-Mail")).parent().$("input").setValue(email);
        return this;
    }

    public OrderPage setPhone(String phone) {
        $("#loading_screen").shouldNotBe(visible);
        buyerInfoContainer.$(byText("Телефон")).parent().$("input").clear();
        buyerInfoContainer.$(byText("Телефон")).parent().$("input").setValue(phone);
        return this;
    }

    public OrderPage setAddress(String street, String house, String flat) {
        $("#loading_screen").shouldNotBe(visible);
        buyerInfoContainer.$(byText("Улица")).parent().$("input").clear();
        buyerInfoContainer.$(byText("Улица")).parent().$("input").setValue(street);
        buyerInfoContainer.$(byText("Дом, корпус, строение")).parent().$("input").clear();
        buyerInfoContainer.$(byText("Дом, корпус, строение")).parent().$("input").setValue(house);
        buyerInfoContainer.$(byText("Квартира/Офис")).parent().$("input").clear();
        buyerInfoContainer.$(byText("Квартира/Офис")).parent().$("input").setValue(flat);
        return this;
    }

    public OrderPage setDeliveryAddress(String deliveryAddress) {
        $("#loading_screen").shouldNotBe(visible);
        buyerInfoContainer.$(byText("Адрес доставки (выберите из списка, если найден)"))
                .parent().$("input").clear();
        buyerInfoContainer.$(byText("Адрес доставки (выберите из списка, если найден)"))
                .parent().$("input").setValue(deliveryAddress).pressEnter();
        return this;
    }

    public OrderPage alreadyHaveDiscountCardCheckBoxClick() {
        $("#loading_screen").shouldNotBe(visible);
        buyerInfoContainer.$(byText("У меня уже есть дисконтная карта – использовать для получения скидки"))
                .parent()
                .$("input[type=checkbox]").click();
        return this;
    }

    public OrderPage setDiscountCardNumber(String discountCardNumber) {
        $("#loading_screen").shouldNotBe(visible);
        buyerInfoContainer.$(byText("Номер дисконтной карты")).shouldBe(visible)
                .parent()
                .$("input").setValue(discountCardNumber);
        return this;
    }


    public OrderPage closeSaleBox() {
        closeSaleBoxButton.click();
        return this;
    }

    public OrderPage wantDiscountCard(String birthday) {
        $("#loading_screen").shouldNotBe(visible);
        buyerInfoContainer.$(byText("Хочу получить дисконтную карту!"))
                .parent()
                .$("input[type=checkbox]").click();
        buyerInfoContainer.$(byText("Дата рождения")).shouldBe(visible)
                .parent()
                .$("input").setValue(birthday);
        return this;
    }

    public OrderPage CallBackInfo(String inputValue) {
        buyerInfoContainer.$(byText("Перезвонить"))
                .parent()
                .$("input[value=" + inputValue + "]").click();
        return this;
    }

    public OrderPage setOrderDescription(String description) {
        orderDescription.setValue(description);
        return this;
    }
}
