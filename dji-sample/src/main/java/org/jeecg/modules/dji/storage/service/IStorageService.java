package org.jeecg.modules.dji.storage.service;

import org.jeecg.modules.dji.cloudapi.storage.StsCredentialsResponse;

/**
 * @author sean
 * @version 0.3
 * @date 2021/12/29
 */
public interface IStorageService {

    /**
     * Get custom temporary credentials object for uploading the media and wayline.
     * @return temporary credentials object
     */
    StsCredentialsResponse getSTSCredentials();

}
