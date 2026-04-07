package org.jeecg.modules.dji.manage.model.receiver;

import org.jeecg.modules.dji.cloudapi.device.ObstacleAvoidance;
import org.jeecg.modules.dji.cloudapi.device.OsdDockDrone;
import org.jeecg.modules.dji.cloudapi.device.SwitchActionEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

/**
 * @author sean
 * @version 1.3
 * @date 2022/10/27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ObstacleAvoidanceReceiver extends BasicDeviceProperty {

    private SwitchActionEnum horizon;

    private SwitchActionEnum upside;

    private SwitchActionEnum downside;

    public boolean valid() {
        return Objects.nonNull(this.horizon) || Objects.nonNull(this.upside) || Objects.nonNull(this.downside);
    }

    @Override
    public boolean canPublish(OsdDockDrone osd) {
        ObstacleAvoidance obstacleAvoidance = osd.getObstacleAvoidance();
        return (Objects.nonNull(obstacleAvoidance.getHorizon()) && horizon != obstacleAvoidance.getHorizon())
                || (Objects.nonNull(obstacleAvoidance.getUpside()) && upside != obstacleAvoidance.getUpside())
                || (Objects.nonNull(obstacleAvoidance.getDownside()) && downside != obstacleAvoidance.getDownside());
    }
}
