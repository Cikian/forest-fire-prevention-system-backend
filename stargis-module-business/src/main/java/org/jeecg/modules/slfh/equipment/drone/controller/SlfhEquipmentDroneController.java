package org.jeecg.modules.slfh.equipment.drone.controller;

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
import org.jeecg.modules.slfh.equipment.drone.entity.SlfhEquipmentDrone;
import org.jeecg.modules.slfh.equipment.drone.service.ISlfhEquipmentDroneService;
import org.jeecg.modules.slfh.equipment.drone.mapper.SlfhEquipmentDroneMapper;
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
 * @Description: 设备管理-无人机
 * @Author: jeecg-boot
 * @Date:   2026-03-25
 * @Version: V1.0
 */
@Api(tags="设备管理-无人机")
@RestController
@RequestMapping("/stargis/slfhEquipmentDrone")
@Slf4j
public class SlfhEquipmentDroneController extends JeecgController<SlfhEquipmentDrone, ISlfhEquipmentDroneService> {
	@Autowired
	private ISlfhEquipmentDroneService slfhEquipmentDroneService;
	@Resource
	private SlfhEquipmentDroneMapper slfhEquipmentDroneMapper;
	
	/**
	 * 分页列表查询
	 *
	 * @param slfhEquipmentDrone
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "设备管理-无人机-分页列表查询")
	@ApiOperation(value="设备管理-无人机-分页列表查询", notes="设备管理-无人机-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<SlfhEquipmentDrone>> queryPageList(SlfhEquipmentDrone slfhEquipmentDrone,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SlfhEquipmentDrone> queryWrapper = QueryGenerator.initQueryWrapper(slfhEquipmentDrone, req.getParameterMap());
		Page<SlfhEquipmentDrone> page = new Page<SlfhEquipmentDrone>(pageNo, pageSize);
		IPage<SlfhEquipmentDrone> pageList = slfhEquipmentDroneService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param slfhEquipmentDrone
	 * @return
	 */
	@AutoLog(value = "设备管理-无人机-添加")
	@ApiOperation(value="设备管理-无人机-添加", notes="设备管理-无人机-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_equipment_drone:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody SlfhEquipmentDrone slfhEquipmentDrone) {
		slfhEquipmentDroneService.save(slfhEquipmentDrone);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param slfhEquipmentDrone
	 * @return
	 */
	@AutoLog(value = "设备管理-无人机-编辑")
	@ApiOperation(value="设备管理-无人机-编辑", notes="设备管理-无人机-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_equipment_drone:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody SlfhEquipmentDrone slfhEquipmentDrone) {
		slfhEquipmentDroneService.updateById(slfhEquipmentDrone);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "设备管理-无人机-通过id删除")
	@ApiOperation(value="设备管理-无人机-通过id删除", notes="设备管理-无人机-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_equipment_drone:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		slfhEquipmentDroneService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "设备管理-无人机-批量删除")
	@ApiOperation(value="设备管理-无人机-批量删除", notes="设备管理-无人机-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_equipment_drone:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.slfhEquipmentDroneService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "设备管理-无人机-通过id查询")
	@ApiOperation(value="设备管理-无人机-通过id查询", notes="设备管理-无人机-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<SlfhEquipmentDrone> queryById(@RequestParam(name="id",required=true) String id) {
		SlfhEquipmentDrone slfhEquipmentDrone = slfhEquipmentDroneService.getById(id);
		if(slfhEquipmentDrone==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(slfhEquipmentDrone);
	}

	 /**
	  * 通过name查询
	  *
	  * @param name
	  * @return
	  */
	 //@AutoLog(value = "设备管理-无人机-通过name查询")
	 @ApiOperation(value="设备管理-无人机-通过name查询", notes="设备管理-无人机-通过name查询")
	 @GetMapping(value = "/queryByName")
	 public Result<List<SlfhEquipmentDrone>> queryByName(@RequestParam(name="name",required=true) String name) {
		 QueryWrapper<SlfhEquipmentDrone> queryWrapper = new QueryWrapper<>();
		 queryWrapper.like("name", name);
		 List<SlfhEquipmentDrone> droneList = slfhEquipmentDroneMapper.selectList(queryWrapper);
		 return Result.OK(droneList);
	 }

    /**
    * 导出excel
    *
    * @param request
    * @param slfhEquipmentDrone
    */
    //@RequiresPermissions("org.jeecg.modules.demo:slfh_equipment_drone:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SlfhEquipmentDrone slfhEquipmentDrone) {
        return super.exportXls(request, slfhEquipmentDrone, SlfhEquipmentDrone.class, "设备管理-无人机");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("slfh_equipment_drone:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, SlfhEquipmentDrone.class);
    }

}
