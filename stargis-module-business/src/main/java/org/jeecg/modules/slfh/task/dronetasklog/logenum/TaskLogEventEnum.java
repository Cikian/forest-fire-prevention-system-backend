package org.jeecg.modules.slfh.task.dronetasklog.logenum;

/**
 * 任务日志事件类型枚举
 */
public enum TaskLogEventEnum {

    // 任务开始
    TASK_START(1,"TASK_START", "任务开始"),
    // 航线起飞
    ROUTE_START(2, "ROUTE_START", "航线起飞"),
    // 航线结束
    ROUTE_END(3, "ROUTE_END", "航线结束"),
    // 异常
    ERROR(4, "ERROR", "异常"),
    // 停止
    STOP(5, "STOP", "停止");

    /** 事件编码（落库值） */
    private int code;
    /** 英文标识（程序内部使用） */
    private final String key;
    /** 事件描述（中文含义） */
    private final String data;

    TaskLogEventEnum(int code, String key, String desc) {
        this.code = code;
        this.key = key;
        this.data = desc;
    }

    public int getCode() {
        return code;
    }

    public String getKey() {return key;}

    public String getData() {
        return data;
    }

    /**
     * 根据 code 获取枚举
     */
    public static TaskLogEventEnum ofCode(Integer  code) {
        if (code == null) {
            return null;
        }

        for (TaskLogEventEnum e : values()) {
            if (e.code == code) {
                return e;
            }
        }
        return null;
    }

    /**
     * 根据 key（TASK_START / ROUTE_START）获取枚举
     */
    public static TaskLogEventEnum ofKey(String key) {
        if (key == null) {
            return null;
        }
        for (TaskLogEventEnum e : values()) {
            if (e.key.equals(key)) {
                return e;
            }
        }
        return null;
    }

    /**
     * 判断 code 是否合法
     */
    public static boolean isValid(Integer code) {
        return ofCode(code) != null;
    }
}
