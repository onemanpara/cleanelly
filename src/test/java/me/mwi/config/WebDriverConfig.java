package me.mwi.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:local.properties"
})
public interface WebDriverConfig extends Config {

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String browserSize();


}
