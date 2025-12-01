package com.inductiveautomation.ignition.test.historian;

import com.inductiveautomation.historian.gateway.api.Historian;
import com.inductiveautomation.historian.gateway.api.HistorianExtensionPoint;
import com.inductiveautomation.historian.gateway.api.HistorianProvider;
import com.inductiveautomation.historian.gateway.api.config.HistorianSettings;
import com.inductiveautomation.ignition.gateway.config.DecodedResource;
import com.inductiveautomation.ignition.gateway.config.ExtensionPointConfig;
import com.inductiveautomation.ignition.gateway.dataroutes.openapi.SchemaUtil;
import com.inductiveautomation.ignition.gateway.model.GatewayContext;
import com.inductiveautomation.ignition.gateway.web.nav.ExtensionPointResourceForm;
import com.inductiveautomation.ignition.gateway.web.nav.WebUiComponent;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class TestHistorianExtensionPoint extends HistorianExtensionPoint<TestHistorianSettings> {

  public TestHistorianExtensionPoint() {
    super(
        "TestHistorian",
        "TestHistorianSettings.HistorianType.Name",
        "TestHistorianSettings.HistorianType.Description");
  }

  @Override
  public Optional<Class<TestHistorianSettings>> settingsType() {
    return Optional.of(TestHistorianSettings.class);
  }

  @Override
  public Optional<WebUiComponent> getWebUiComponent(ComponentType type) {
    return Optional.of(
        new ExtensionPointResourceForm(
            HistorianExtensionPoint.getResourceType(),
            "Historian",
            "TestHistorian",
            SchemaUtil.fromType(HistorianProvider.class),
            SchemaUtil.fromType(TestHistorianSettings.class)));
  }

  @NotNull
  @Override
  public Historian<TestHistorianSettings> createHistorianProvider(
      @NotNull GatewayContext gatewayContext,
      @NotNull DecodedResource<ExtensionPointConfig<HistorianProvider, HistorianSettings>> decodedResource) {
    return new TestHistorian(gatewayContext, decodedResource.name());
  }
}
