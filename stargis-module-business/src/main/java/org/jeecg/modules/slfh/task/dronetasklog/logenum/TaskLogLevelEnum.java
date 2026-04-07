package org.jeecg.modules.slfh.task.dronetasklog.logenum;

public enum TaskLogLevelEnum {

    INFO(1, "INFO", "信息"),
    WARN(2, "WARN", "报警"),
    ERROR(3, "ERROR", "错误");

    /** 数值（用于落库 / 数据字典） */
    private final int code;
    /** 英文标识（日志/前端可能用） */
    private final String key;
    /** 中文描述 */
    private final String desc;

    TaskLogLevelEnum(int code, String key, String desc) {
        this.code = code;
        this.key = key;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 根据 code 获取枚举
     */
    public static TaskLogLevelEnum of(int code) {
        for (TaskLogLevelEnum e : values()) {
            if (e.code == code) {
                return e;
            }
        }
        return null;
    }

    /**
     * 根据 key 获取枚举（INFO/WARN/ERROR）
     */
    public static TaskLogLevelEnum ofKey(String key) {
        for (TaskLogLevelEnum e : values()) {
            if (e.key.equalsIgnoreCase(key)) {
                return e;
            }
        }
        return null;
    }

    /**
     * 校验 code 是否合法
     */
    public static boolean isValid(int code) {
        return of(code) != null;
    }
}
