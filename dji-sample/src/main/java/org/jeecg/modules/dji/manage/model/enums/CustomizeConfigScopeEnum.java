package org.jeecg.modules.dji.manage.model.enums;

import org.jeecg.modules.dji.manage.service.IRequestsConfigService;
import org.jeecg.modules.dji.manage.service.impl.ConfigProductServiceImpl;
import org.jeecg.modules.dji.cloudapi.config.ConfigScopeEnum;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author sean
 * @version 1.3
 * @date 2022/11/10
 */
@Getter
public enum CustomizeConfigScopeEnum {

    PRODUCT(ConfigScopeEnum.PRODUCT, ConfigProductServiceImpl.class);

    ConfigScopeEnum scope;

    Class<? extends IRequestsConfigService> clazz;

    CustomizeConfigScopeEnum(ConfigScopeEnum scope, Class<? extends IRequestsConfigService> clazz) {
        this.scope = scope;
        this.clazz = clazz;
    }

    public static Optional<CustomizeConfigScopeEnum> find(String scope) {
        return Arrays.stream(CustomizeConfigScopeEnum.values()).filter(scopeEnum -> scopeEnum.scope.getScope().equals(scope)).findAny();
    }
}
