package com.inductiveautomation.ignition.test.historian;

import com.inductiveautomation.historian.gateway.api.AbstractHistorian;
import com.inductiveautomation.ignition.gateway.model.GatewayContext;
import com.inductiveautomation.ignition.gateway.model.ProfileStatus;

public class TestHistorian extends AbstractHistorian<TestHistorianSettings> {

  public TestHistorian(
      GatewayContext context, String historianName) {
    super(context, historianName);
  }

  @Override
  public ProfileStatus getStatus() {
    return ProfileStatus.RUNNING;
  }
}
