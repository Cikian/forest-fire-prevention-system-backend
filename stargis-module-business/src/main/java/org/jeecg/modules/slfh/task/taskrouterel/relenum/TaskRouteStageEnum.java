package org.jeecg.modules.slfh.task.taskrouterel.relenum;

import lombok.Getter;

/** 阶段（起飞 TAKEOFF/作业 WORK/返航 RETURN） */
@Getter
public enum TaskRouteStageEnum {

    TAKEOFF(1, "TAKEOFF", "起飞"),
    WORK(2, "WORK", "作业"),
    RETURN(3, "RETURN", "返航");

    private final int code;     // 入库/传输用（对应 stage）
    private final String key;   // 业务标识（TAKEOFF/WORK/RETURN）
    private final String desc;  // 中文展示

    TaskRouteStageEnum(int code, String key, String desc) {
        this.code = code;
        this.key = key;
        this.desc = desc;
    }

    public static TaskRouteStageEnum fromCode(Integer code) {
        if (code == null) return null;
        for (TaskRouteStageEnum e : values()) {
            if (e.code == code) return e;
        }
        throw new IllegalArgumentException("Unknown TaskRouteStageEnum code: " + code);
    }

    public static TaskRouteStageEnum fromKey(String key) {
        if (key == null) return null;
        for (TaskRouteStageEnum e : values()) {
            if (e.key.equalsIgnoreCase(key)) return e;
        }
        throw new IllegalArgumentException("Unknown TaskRouteStageEnum key: " + key);
    }
}
