package org.jeecg.modules.dji.control.controller;

import org.jeecg.modules.dji.common.model.CustomClaim;
import org.jeecg.modules.dji.control.model.dto.JwtAclDTO;
import org.jeecg.modules.dji.control.model.param.DrcConnectParam;
import org.jeecg.modules.dji.control.model.param.DrcModeParam;
import org.jeecg.modules.dji.control.service.IDrcService;
import org.jeecg.modules.dji.cloudapi.control.DrcModeMqttBroker;
import org.jeecg.modules.dji.common.HttpResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.jeecg.modules.dji.component.AuthInterceptor.TOKEN_CLAIM;

/**
 * @author sean
 * @version 1.3
 * @date 2023/1/11
 */
@RestController
@Slf4j
@RequestMapping("${url.control.prefix}${url.control.version}")
public class DrcController {

    @Autowired
    private IDrcService drcService;

    @PostMapping("/workspaces/{workspace_id}/drc/connect")
    public HttpResultResponse drcConnect(@PathVariable("workspace_id") String workspaceId, HttpServletRequest request, @Valid @RequestBody DrcConnectParam param) {
        CustomClaim claims = (CustomClaim) request.getAttribute(TOKEN_CLAIM);

        DrcModeMqttBroker brokerDTO = drcService.userDrcAuth(workspaceId, claims.getId(), claims.getUsername(), param);
        return HttpResultResponse.success(brokerDTO);
    }

    @PostMapping("/workspaces/{workspace_id}/drc/enter")
    public HttpResultResponse drcEnter(@PathVariable("workspace_id") String workspaceId, @Valid @RequestBody DrcModeParam param) {
        JwtAclDTO acl = drcService.deviceDrcEnter(workspaceId, param);

        return HttpResultResponse.success(acl);
    }

    @PostMapping("/workspaces/{workspace_id}/drc/exit")
    public HttpResultResponse drcExit(@PathVariable("workspace_id") String workspaceId, @Valid @RequestBody DrcModeParam param) {
        drcService.deviceDrcExit(workspaceId, param);

        return HttpResultResponse.success();
    }


}
