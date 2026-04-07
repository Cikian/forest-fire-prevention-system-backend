package org.jeecg.modules.dji.cloudapi.control;

import org.jeecg.modules.dji.cloudapi.device.VideoId;

/**
 * @author sean
 * @version 1.7
 * @date 2023/6/29
 */
public class LiveviewDelay {

    private Integer liveviewDelayTime;

    private VideoId videoId;

    public LiveviewDelay() {
    }

    @Override
    public String toString() {
        return "LiveviewDelay{" +
                "liveviewDelayTime=" + liveviewDelayTime +
                ", videoId=" + videoId +
                '}';
    }

    public Integer getLiveviewDelayTime() {
        return liveviewDelayTime;
    }

    public LiveviewDelay setLiveviewDelayTime(Integer liveviewDelayTime) {
        this.liveviewDelayTime = liveviewDelayTime;
        return this;
    }
}
