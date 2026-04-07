package org.jeecg.modules.dji.manage.service;

import org.jeecg.modules.dji.cloudapi.tsa.TopologyList;

import java.util.List;
import java.util.Optional;

/**
 * @author sean
 * @version 0.2
 * @date 2021/12/8
 */
public interface ITopologyService {

    /**
     * Get the topology list of all devices in the workspace for pilot display.
     * @param workspaceId
     * @return
     */
    List<TopologyList> getDeviceTopology(String workspaceId);

    /**
     * Query the topology according to the gateway sn.
     * @param gatewaySn
     * @return
     */
    Optional<TopologyList> getDeviceTopologyByGatewaySn(String gatewaySn);
}
