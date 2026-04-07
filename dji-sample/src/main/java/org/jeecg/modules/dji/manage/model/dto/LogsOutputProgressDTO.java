package org.jeecg.modules.dji.manage.model.dto;

import org.jeecg.modules.dji.cloudapi.log.FileUploadStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author sean
 * @version 1.2
 * @date 2022/9/9
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogsOutputProgressDTO {

    private String logsId;

    private FileUploadStatusEnum status;

    private List<LogsProgressDTO> files;
}
