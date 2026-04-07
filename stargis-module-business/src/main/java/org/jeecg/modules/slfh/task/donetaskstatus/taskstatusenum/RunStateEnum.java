package org.jeecg.modules.slfh.task.donetaskstatus.taskstatusenum;

public enum RunStateEnum {

    READY(1, "准备"),
    RUNNING(2, "运行中"),
    SUCCESS(3, "完成"),
    FAILED(4, "失败"),
    STOPPED(5, "停止"),
    PENDING(6, "待执行");

    private final int code;
    private final String desc;

    RunStateEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static RunStateEnum fromCode(int code) {
        for (RunStateEnum status : RunStateEnum.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown TaskStatus code: " + code);
    }

}
