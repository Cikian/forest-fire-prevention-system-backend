package org.jeecg.modules.slfh.equipment.alert.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.slfh.equipment.alert.entity.DevAlert;
import org.jeecg.modules.slfh.equipment.alert.service.DevAlertService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

/**
 * @author Cikian
 * @version 1.0
 * @implNote
 * @see <a href="https://www.cikian.cn">https://www.cikian.cn</a>
 * @since 2026-03-29 22:20
 */

@Api(tags="设备管理-告警设备")
@RestController
@RequestMapping("/dev/alert")
@Slf4j
public class DevAlertController extends JeecgController<DevAlert, DevAlertService> {
	@Autowired
	private DevAlertService alertService;

	/**
	 * 分页列表查询
	 *
	 * @param devAlert
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "设备管理-告警设备-分页列表查询")
	@ApiOperation(value="设备管理-告警设备-分页列表查询", notes="设备管理-告警设备-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<DevAlert>> queryPageList(DevAlert devAlert,
												 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
												 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
												 HttpServletRequest req) {
		QueryWrapper<DevAlert> queryWrapper = QueryGenerator.initQueryWrapper(devAlert, req.getParameterMap());
		Page<DevAlert> page = new Page<DevAlert>(pageNo, pageSize);
		IPage<DevAlert> pageList = alertService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param devAlert
	 * @return
	 */
	@AutoLog(value = "设备管理-告警设备-添加")
	@ApiOperation(value="设备管理-告警设备-添加", notes="设备管理-告警设备-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_equipment_alert:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody DevAlert devAlert) {
		alertService.save(devAlert);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param devAlert
	 * @return
	 */
	@AutoLog(value = "设备管理-告警设备-编辑")
	@ApiOperation(value="设备管理-告警设备-编辑", notes="设备管理-告警设备-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_equipment_alert:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody DevAlert devAlert) {
		alertService.updateById(devAlert);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "设备管理-告警设备-通过id删除")
	@ApiOperation(value="设备管理-告警设备-通过id删除", notes="设备管理-告警设备-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_equipment_alert:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		alertService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "设备管理-告警设备-批量删除")
	@ApiOperation(value="设备管理-告警设备-批量删除", notes="设备管理-告警设备-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_equipment_alert:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.alertService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "设备管理-告警设备-通过id查询")
	@ApiOperation(value="设备管理-告警设备-通过id查询", notes="设备管理-告警设备-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<DevAlert> queryById(@RequestParam(name="id",required=true) String id) {
		DevAlert devAlert = alertService.getById(id);
		if(devAlert ==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(devAlert);
	}

	 //@AutoLog(value = "设备管理-告警设备-通过id查询")
	 @ApiOperation(value="设备管理-告警设备-通过告警查询", notes="设备管理-告警设备-通过告警查询")
	 @GetMapping(value = "/queryByAlert")
	 public Result<List<DevAlert>> queryByAlert(@RequestParam(name="grade",required=false) String grade,
												@RequestParam(name="type",required=false) String type) {
		 QueryWrapper<DevAlert> queryWrapper = new QueryWrapper<>();
		 if (grade != null && !grade.isEmpty()) {
			 queryWrapper.like("alert_grade", grade);
		 }
		 if (type != null && !type.isEmpty()) {
			 queryWrapper.like("alert_type", type);
		 }
		 List<DevAlert> alerts = alertService.list(queryWrapper);
		 return Result.OK(alerts);
	 }

    /**
    * 导出excel
    *
    * @param request
    * @param devAlert
    */
    //@RequiresPermissions("org.jeecg.modules.demo:slfh_equipment_alert:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, DevAlert devAlert) {
        return super.exportXls(request, devAlert, DevAlert.class, "设备管理-告警设备");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("slfh_equipment_alert:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, DevAlert.class);
    }

}
