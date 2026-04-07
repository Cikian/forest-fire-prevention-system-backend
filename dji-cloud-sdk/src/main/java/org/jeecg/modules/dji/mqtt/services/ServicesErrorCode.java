package org.jeecg.modules.dji.mqtt.services;

import org.jeecg.modules.dji.cloudapi.control.ControlErrorCodeEnum;
import org.jeecg.modules.dji.cloudapi.debug.DebugErrorCodeEnum;
import org.jeecg.modules.dji.cloudapi.firmware.FirmwareErrorCodeEnum;
import org.jeecg.modules.dji.cloudapi.livestream.LiveErrorCodeEnum;
import org.jeecg.modules.dji.cloudapi.log.LogErrorCodeEnum;
import org.jeecg.modules.dji.cloudapi.wayline.WaylineErrorCodeEnum;
import org.jeecg.modules.dji.common.CommonErrorEnum;
import org.jeecg.modules.dji.common.ErrorCodeSourceEnum;
import org.jeecg.modules.dji.common.IErrorInfo;
import org.jeecg.modules.dji.mqtt.MqttReply;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author sean
 * @version 1.7
 * @date 2023/7/14
 */
public class ServicesErrorCode implements IErrorInfo {

    private static final int MOD = 100_000;

    private ErrorCodeSourceEnum source;

    private IServicesErrorCode errorCode;

    private boolean success;

    private Integer sourceCode;

    @Override
    public String toString() {
        return "{" +
                "errorCode=" + getCode() +
                ", errorMsg=" + getMessage() +
                '}';
    }

    @JsonCreator
    public ServicesErrorCode(int code) {
        this.sourceCode = code;
        if (MqttReply.CODE_SUCCESS == code) {
            this.success = true;
            this.errorCode = CommonErrorEnum.SUCCESS;
            return;
        }
        this.source = ErrorCodeSourceEnum.find(code / MOD);
        this.errorCode = LiveErrorCodeEnum.find(code % MOD);
        if (errorCode.getCode() != -1) {
            return;
        }
        this.errorCode = DebugErrorCodeEnum.find(code);
        if (errorCode.getCode() != -1) {
            return;
        }
        this.errorCode = ControlErrorCodeEnum.find(code);
        if (errorCode.getCode() != -1) {
            return;
        }
        this.errorCode = LogErrorCodeEnum.find(code);
        if (errorCode.getCode() != -1) {
            return;
        }
        this.errorCode = FirmwareErrorCodeEnum.find(code);
        if (errorCode.getCode() != -1) {
            return;
        }
        this.errorCode = WaylineErrorCodeEnum.find(code);
        if (errorCode.getCode() != -1) {
            return;
        }
        this.errorCode = CommonErrorEnum.find(code);
    }

    @Override
    public String getMessage() {
        return errorCode.getMessage();
    }

    @JsonValue
    public Integer getCode() {
        return sourceCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public ErrorCodeSourceEnum getSource() {
        return source;
    }
}
