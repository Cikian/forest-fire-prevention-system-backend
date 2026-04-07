package org.jeecg.modules.dji.cloudapi.organization;

import org.jeecg.modules.dji.common.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * @author sean
 * @version 1.1
 * @date 2022/6/13
 */
public class AirportOrganizationGetResponse extends BaseModel {

    @NotNull
    private String organizationName;

    public AirportOrganizationGetResponse() {
    }

    @Override
    public String toString() {
        return "AirportOrganizationGetResponse{" +
                "organizationName='" + organizationName + '\'' +
                '}';
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public AirportOrganizationGetResponse setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
        return this;
    }
}
