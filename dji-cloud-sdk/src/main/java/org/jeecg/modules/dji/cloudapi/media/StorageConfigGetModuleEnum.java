package org.jeecg.modules.dji.cloudapi.media;

import org.jeecg.modules.dji.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * @author sean
 * @version 1.7
 * @date 2023/6/27
 */
public enum StorageConfigGetModuleEnum {

    MEDIA(0);

    private final int module;

    StorageConfigGetModuleEnum(int module) {
        this.module = module;
    }

    @JsonValue
    public int getModule() {
        return module;
    }

    @JsonCreator
    public StorageConfigGetModuleEnum find(int module) {
        return Arrays.stream(values()).filter(moduleEnum -> moduleEnum.module == module).findAny()
                .orElseThrow(() -> new CloudSDKException(StorageConfigGetModuleEnum.class, module));
    }
}
