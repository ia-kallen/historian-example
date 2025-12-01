package com.inductiveautomation.ignition.test.historian;

import com.inductiveautomation.historian.gateway.api.config.HistorianSettings;
import com.inductiveautomation.ignition.gateway.dataroutes.openapi.annotations.DefaultValue;
import com.inductiveautomation.ignition.gateway.dataroutes.openapi.annotations.DescriptionKey;
import com.inductiveautomation.ignition.gateway.dataroutes.openapi.annotations.FormCategory;
import com.inductiveautomation.ignition.gateway.dataroutes.openapi.annotations.FormField;
import com.inductiveautomation.ignition.gateway.dataroutes.openapi.annotations.Label;
import com.inductiveautomation.ignition.gateway.web.nav.FormFieldType;
import org.apache.commons.lang3.StringUtils;

public record TestHistorianSettings(
    @FormCategory("GENERAL")
    @Label("Test Field")
    @FormField(FormFieldType.TEXT)
    @DefaultValue("")
    @DescriptionKey("TestHistorianSettings.TestField.Description")
    String testField)
    implements HistorianSettings {

  public TestHistorianSettings {
    testField = StringUtils.defaultIfBlank(testField, "Test Value");
  }
}
