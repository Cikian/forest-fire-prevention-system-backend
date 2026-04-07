package org.jeecg.modules.slfh.equipment.alert.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.slfh.equipment.alert.entity.SlfhEquipmentAlert;
import org.jeecg.modules.slfh.equipment.alert.service.ISlfhEquipmentAlertService;
import org.jeecg.modules.slfh.equipment.alert.mapper.SlfhEquipmentAlertMapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 设备管理-告警设备
 * @Author: jeecg-boot
 * @Date:   2026-03-25
 * @Version: V1.0
 */
@Api(tags="设备管理-告警设备")
@RestController
@RequestMapping("/stargis/slfhEquipmentAlert")
@Slf4j
public class SlfhEquipmentAlertController extends JeecgController<SlfhEquipmentAlert, ISlfhEquipmentAlertService> {
	@Autowired
	private ISlfhEquipmentAlertService slfhEquipmentAlertService;
	@Resource
	private SlfhEquipmentAlertMapper slfhEquipmentAlertMapper;
	
	/**
	 * 分页列表查询
	 *
	 * @param slfhEquipmentAlert
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "设备管理-告警设备-分页列表查询")
	@ApiOperation(value="设备管理-告警设备-分页列表查询", notes="设备管理-告警设备-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<SlfhEquipmentAlert>> queryPageList(SlfhEquipmentAlert slfhEquipmentAlert,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SlfhEquipmentAlert> queryWrapper = QueryGenerator.initQueryWrapper(slfhEquipmentAlert, req.getParameterMap());
		Page<SlfhEquipmentAlert> page = new Page<SlfhEquipmentAlert>(pageNo, pageSize);
		IPage<SlfhEquipmentAlert> pageList = slfhEquipmentAlertService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param slfhEquipmentAlert
	 * @return
	 */
	@AutoLog(value = "设备管理-告警设备-添加")
	@ApiOperation(value="设备管理-告警设备-添加", notes="设备管理-告警设备-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_equipment_alert:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody SlfhEquipmentAlert slfhEquipmentAlert) {
		slfhEquipmentAlertService.save(slfhEquipmentAlert);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param slfhEquipmentAlert
	 * @return
	 */
	@AutoLog(value = "设备管理-告警设备-编辑")
	@ApiOperation(value="设备管理-告警设备-编辑", notes="设备管理-告警设备-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_equipment_alert:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody SlfhEquipmentAlert slfhEquipmentAlert) {
		slfhEquipmentAlertService.updateById(slfhEquipmentAlert);
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
		slfhEquipmentAlertService.removeById(id);
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
		this.slfhEquipmentAlertService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<SlfhEquipmentAlert> queryById(@RequestParam(name="id",required=true) String id) {
		SlfhEquipmentAlert slfhEquipmentAlert = slfhEquipmentAlertService.getById(id);
		if(slfhEquipmentAlert==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(slfhEquipmentAlert);
	}

	 //@AutoLog(value = "设备管理-告警设备-通过id查询")
	 @ApiOperation(value="设备管理-告警设备-通过告警查询", notes="设备管理-告警设备-通过告警查询")
	 @GetMapping(value = "/queryByAlert")
	 public Result<List<SlfhEquipmentAlert>> queryByAlert(@RequestParam(name="grade",required=false) String grade,
														  @RequestParam(name="type",required=false) String type) {
		 QueryWrapper<SlfhEquipmentAlert> queryWrapper = new QueryWrapper<>();
		 if (grade != null && !grade.isEmpty()) {
			 queryWrapper.like("alert_grade", grade);
		 }
		 if (type != null && !type.isEmpty()) {
			 queryWrapper.like("alert_type", type);
		 }
		 List<SlfhEquipmentAlert> alerts = slfhEquipmentAlertMapper.selectList(queryWrapper);
		 return Result.OK(alerts);
	 }

    /**
    * 导出excel
    *
    * @param request
    * @param slfhEquipmentAlert
    */
    //@RequiresPermissions("org.jeecg.modules.demo:slfh_equipment_alert:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SlfhEquipmentAlert slfhEquipmentAlert) {
        return super.exportXls(request, slfhEquipmentAlert, SlfhEquipmentAlert.class, "设备管理-告警设备");
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
        return super.importExcel(request, response, SlfhEquipmentAlert.class);
    }

}
