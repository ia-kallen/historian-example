package com.inductiveautomation.ignition.examples.historian;

import com.inductiveautomation.historian.gateway.api.config.HistorianSettings;
import com.inductiveautomation.ignition.gateway.dataroutes.openapi.annotations.*;
import com.inductiveautomation.ignition.gateway.web.nav.FormFieldType;
import org.apache.commons.lang3.StringUtils;

/**
 * Configuration for a Example Historian provider.
 */
public record Settings(
        @FormCategory("CUSTOM SETTINGS")
        @Label("Webdev Endpoint")
        @FormField(FormFieldType.TEXT)
        @DefaultValue("http://localhost:8088/system/webdev/test/historian")
        @Required
        @Description("The webdev endpoint to use")
        String webdevEndpoint

//        ,@FormCategory("CUSTOM SETTINGS")
//        @Label("Password")
//        @FormField(FormFieldType.SECRET)
//        @DescriptionKey("SecretResource.password.Desc")
//        @Description(".")
//        SecretConfig password
) implements HistorianSettings {

    public static final Settings DEFAULT = new Settings(
            "http://localhost:8088/system/webdev/test/historian"
    );

    /**
     * Canonical constructor that fills in default values for any null or blank parameters.
     *
     * @param webdevEndpoint   The webdev endpoint to use
     */
    public Settings {
        if (StringUtils.isBlank(webdevEndpoint)) {
            webdevEndpoint = DEFAULT.webdevEndpoint();
        }
    }
}
