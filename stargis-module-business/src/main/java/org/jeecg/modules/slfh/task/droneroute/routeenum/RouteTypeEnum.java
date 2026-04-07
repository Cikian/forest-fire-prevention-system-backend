package org.jeecg.modules.slfh.task.droneroute.routeenum;

import lombok.Getter;

@Getter
public enum RouteTypeEnum {

    TAKE_OFF(1, "起飞"),
    WORK(2, "作业"),
    RETURN(3, "返航"),
    CUSTOM(4, "自定义");

    private final int code;
    private final String desc;

    RouteTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static RouteTypeEnum fromCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (RouteTypeEnum type : RouteTypeEnum.values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown RouteTypeEnum code: " + code);
    }
}
