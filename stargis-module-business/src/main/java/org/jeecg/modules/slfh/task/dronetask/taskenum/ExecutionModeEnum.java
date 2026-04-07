package org.jeecg.modules.slfh.task.dronetask.taskenum;

import lombok.Getter;

@Getter
public enum ExecutionModeEnum {

    IMMEDIATE(1, "立即执行"),
    LOOP(2, "循环执行");

    private final int code;
    private final String desc;

    ExecutionModeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ExecutionModeEnum fromCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (ExecutionModeEnum mode : ExecutionModeEnum.values()) {
            if (mode.code == code) {
                return mode;
            }
        }
        throw new IllegalArgumentException("Unknown ExecutionModeEnum code: " + code);
    }
}
