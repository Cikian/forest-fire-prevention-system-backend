package org.jeecg.modules.dji.manage.service.impl;

import org.jeecg.modules.dji.manage.model.common.AppLicenseProperties;
import org.jeecg.modules.dji.manage.model.common.NtpServerProperties;
import org.jeecg.modules.dji.manage.model.dto.ProductConfigDTO;
import org.jeecg.modules.dji.manage.service.IRequestsConfigService;
import org.springframework.stereotype.Service;

/**
 * @author sean
 * @version 1.3
 * @date 2022/11/10
 */
@Service
public class ConfigProductServiceImpl implements IRequestsConfigService {

    @Override
    public Object getConfig() {
        return new ProductConfigDTO(NtpServerProperties.host, AppLicenseProperties.id, AppLicenseProperties.key, AppLicenseProperties.license);
    }
}
