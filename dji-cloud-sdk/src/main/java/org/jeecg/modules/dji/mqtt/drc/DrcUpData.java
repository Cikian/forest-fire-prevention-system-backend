package org.jeecg.modules.dji.mqtt.drc;

import org.jeecg.modules.dji.cloudapi.wayline.WaylineErrorCodeEnum;

/**
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/22
 */
public class DrcUpData<T> {

    private WaylineErrorCodeEnum result;

    private T output;

    public DrcUpData() {
    }

    @Override
    public String toString() {
        return "DrcUpData{" +
                "result=" + result +
                ", output=" + output +
                '}';
    }

    public WaylineErrorCodeEnum getResult() {
        return result;
    }

    public DrcUpData<T> setResult(WaylineErrorCodeEnum result) {
        this.result = result;
        return this;
    }

    public T getOutput() {
        return output;
    }

    public DrcUpData<T> setOutput(T output) {
        this.output = output;
        return this;
    }
}