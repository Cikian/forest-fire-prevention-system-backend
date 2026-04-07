package org.jeecg.modules.slfh.task.dronetask.taskenum;

import lombok.Getter;

@Getter
public enum TaskTypeEnum {

    INSPECTION(1, "巡检"),
    AERIAL_PHOTOGRAPHY(2, "航拍"),
    EMERGENCY(3, "应急");
    // 后续可继续扩展：如 4-测绘，5-执法 等

    private final int code;
    private final String desc;

    TaskTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static TaskTypeEnum fromCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (TaskTypeEnum type : TaskTypeEnum.values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown TaskTypeEnum code: " + code);
    }
}
