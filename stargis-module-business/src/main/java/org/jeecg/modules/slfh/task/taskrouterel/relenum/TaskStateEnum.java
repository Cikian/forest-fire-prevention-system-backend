package org.jeecg.modules.slfh.task.taskrouterel.relenum;

import lombok.Getter;

/** 任务状态（未启动 PENDING/运行中 RUNNING/成功 SUCCESS/失败 FAILED/跳过 SKIPPED） */
@Getter
public enum TaskStateEnum {

    PENDING(1, "PENDING", "未启动"),
    RUNNING(2, "RUNNING", "运行中"),
    SUCCESS(3, "SUCCESS", "成功"),
    FAILED(4, "FAILED", "失败"),
    SKIPPED(5, "SKIPPED", "跳过");

    private final int code;     // 入库/传输用（对应 routeState）
    private final String key;   // 业务标识
    private final String desc;  // 中文展示

    TaskStateEnum(int code, String key, String desc) {
        this.code = code;
        this.key = key;
        this.desc = desc;
    }

    public static TaskStateEnum fromCode(Integer code) {
        if (code == null) return null;
        for (TaskStateEnum e : values()) {
            if (e.code == code) return e;
        }
        throw new IllegalArgumentException("Unknown RouteStateEnum code: " + code);
    }

    public static TaskStateEnum fromKey(String key) {
        if (key == null) return null;
        for (TaskStateEnum e : values()) {
            if (e.key.equalsIgnoreCase(key)) return e;
        }
        throw new IllegalArgumentException("Unknown RouteStateEnum key: " + key);
    }
}
