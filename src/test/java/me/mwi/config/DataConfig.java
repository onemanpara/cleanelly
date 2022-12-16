package me.mwi.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:data.properties")
public interface DataConfig extends Config {


    String userName();

    String userPassword();

    String itemID();


}
