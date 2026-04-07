package org.jeecg.modules.dji.exception;

import org.jeecg.modules.dji.config.version.CloudSDKVersionEnum;

/**
 * @author sean
 * @version 1.7
 * @date 2023/6/7
 */
public class CloudSDKVersionException extends CloudSDKException {

    public CloudSDKVersionException(String thingVersion) {
        super(String.format("The current CloudSDK version(%s) does not support this thing version(%s), " +
                "please replace the corresponding CloudSDK version.)", CloudSDKVersionEnum.DEFAULT.getVersion(), thingVersion));
    }

}
