package com.cvte.customer_service.cuse.dto;

import java.util.List;

public class CustomerServiceConfigDTO {
    private List<String> configValue;

    public List<String> getConfigValue() {
        return configValue;
    }

    public void setConfigValue(List<String> configValue) {
        this.configValue = configValue;
    }

    @Override
    public String toString() {
        return "CustomerServiceConfigDTO{" +
                "configValue=" + configValue +
                '}';
    }
}
