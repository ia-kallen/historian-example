package com.inductiveautomation.ignition.examples.historian;

import com.inductiveautomation.historian.gateway.api.Historian;
import com.inductiveautomation.historian.gateway.api.HistorianProvider;
import com.inductiveautomation.historian.gateway.api.config.HistorianSettings;
import com.inductiveautomation.ignition.gateway.config.DecodedResource;
import com.inductiveautomation.ignition.gateway.config.ExtensionPointConfig;
import com.inductiveautomation.ignition.gateway.config.ValidationErrors;
import com.inductiveautomation.ignition.gateway.dataroutes.openapi.SchemaUtil;
import com.inductiveautomation.ignition.gateway.model.GatewayContext;
import com.inductiveautomation.ignition.gateway.web.nav.ExtensionPointResourceForm;
import com.inductiveautomation.ignition.gateway.web.nav.WebUiComponent;

import com.inductiveautomation.historian.gateway.api.HistorianExtensionPoint;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ExtensionPoint
        extends HistorianExtensionPoint<Settings> {

    public static final String EXTENSION_POINT_TYPE = "com.inductiveautomation.example.historian";

    public ExtensionPoint() {
        super(EXTENSION_POINT_TYPE,
                "HistorianExample.HistorianExampleType.Name",
                "HistorianExample.HistorianExampleType.Desc");
    }

    @Override
    public Optional<Settings> defaultSettings() {
        return Optional.of(Settings.DEFAULT);
    }

    @Override
    public Optional<WebUiComponent> getWebUiComponent(ComponentType type) {
        return Optional.of(
                new ExtensionPointResourceForm(
                        HistorianExtensionPoint.getResourceType(),
                        "Historian",
                        EXTENSION_POINT_TYPE,
                        SchemaUtil.fromType(HistorianProvider.class),
                        SchemaUtil.fromType(Settings.class)
                )
        );
    }

    @Override
    protected void validate(Settings settings, ValidationErrors.Builder errors) {
        /*
         Optionally, add validation to an incoming configuration object
         These error messages will be conveyed back to the standard web UI automatically
        */
        // errors.requireNotNull("someField", settings.auditProfileName());
        super.validate(settings, errors);
    }

    @NotNull
    @Override
    public Historian<Settings> createHistorianProvider(@NotNull GatewayContext context, @NotNull DecodedResource<ExtensionPointConfig<HistorianProvider, HistorianSettings>> decodedResource) throws Exception {
        Optional<Settings> osettings = this.getSettings(decodedResource.config());
        Settings settings = osettings.get();
        return new HistoryProvider(context, decodedResource.name(), settings);
    }

    @Override
    public Optional<Class<Settings>> settingsType() {
        return Optional.of(Settings.class);
    }
}
