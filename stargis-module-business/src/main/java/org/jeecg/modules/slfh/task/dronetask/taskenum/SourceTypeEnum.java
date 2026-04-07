package org.jeecg.modules.slfh.task.dronetask.taskenum;

import lombok.Getter;

@Getter
public enum SourceTypeEnum {

    PLAN(1, "PLAN", "计划"),
    ADHOC(2, "ADHOC", "临时");

    private final int code;      // 入库/传输用（对应 sourceType）
    private final String key;    // 业务标识（PLAN/ADHOC）
    private final String desc;   // 中文展示

    SourceTypeEnum(int code, String key, String desc) {
        this.code = code;
        this.key = key;
        this.desc = desc;
    }

    public static SourceTypeEnum fromCode(Integer code) {
        if (code == null) return null;
        for (SourceTypeEnum e : values()) {
            if (e.code == code) return e;
        }
        throw new IllegalArgumentException("Unknown SourceTypeEnum code: " + code);
    }

    public static SourceTypeEnum fromKey(String key) {
        if (key == null) return null;
        for (SourceTypeEnum e : values()) {
            if (e.key.equalsIgnoreCase(key)) return e;
        }
        throw new IllegalArgumentException("Unknown SourceTypeEnum key: " + key);
    }
}
