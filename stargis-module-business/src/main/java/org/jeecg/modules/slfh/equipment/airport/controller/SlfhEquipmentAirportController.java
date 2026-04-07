package org.jeecg.modules.slfh.equipment.airport.controller;

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
import org.jeecg.modules.slfh.equipment.airport.entity.SlfhEquipmentAirport;
import org.jeecg.modules.slfh.equipment.airport.service.ISlfhEquipmentAirportService;
import org.jeecg.modules.slfh.equipment.airport.mapper.SlfhEquipmentAirportMapper;

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
 * @Description: 设备管理-机场
 * @Author: jeecg-boot
 * @Date:   2026-03-25
 * @Version: V1.0
 */
@Api(tags="设备管理-机场")
@RestController
@RequestMapping("/stargis/slfhEquipmentAirport")
@Slf4j
public class SlfhEquipmentAirportController extends JeecgController<SlfhEquipmentAirport, ISlfhEquipmentAirportService> {
	@Autowired
	private ISlfhEquipmentAirportService slfhEquipmentAirportService;
	@Resource
	private SlfhEquipmentAirportMapper slfhEquipmentAirportMapper;
	
	/**
	 * 分页列表查询
	 *
	 * @param slfhEquipmentAirport
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "设备管理-机场-分页列表查询")
	@ApiOperation(value="设备管理-机场-分页列表查询", notes="设备管理-机场-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<SlfhEquipmentAirport>> queryPageList(SlfhEquipmentAirport slfhEquipmentAirport,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SlfhEquipmentAirport> queryWrapper = QueryGenerator.initQueryWrapper(slfhEquipmentAirport, req.getParameterMap());
		Page<SlfhEquipmentAirport> page = new Page<SlfhEquipmentAirport>(pageNo, pageSize);
		IPage<SlfhEquipmentAirport> pageList = slfhEquipmentAirportService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param slfhEquipmentAirport
	 * @return
	 */
	@AutoLog(value = "设备管理-机场-添加")
	@ApiOperation(value="设备管理-机场-添加", notes="设备管理-机场-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_equipment_airport:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody SlfhEquipmentAirport slfhEquipmentAirport) {
		slfhEquipmentAirportService.save(slfhEquipmentAirport);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param slfhEquipmentAirport
	 * @return
	 */
	@AutoLog(value = "设备管理-机场-编辑")
	@ApiOperation(value="设备管理-机场-编辑", notes="设备管理-机场-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_equipment_airport:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody SlfhEquipmentAirport slfhEquipmentAirport) {
		slfhEquipmentAirportService.updateById(slfhEquipmentAirport);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "设备管理-机场-通过id删除")
	@ApiOperation(value="设备管理-机场-通过id删除", notes="设备管理-机场-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_equipment_airport:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		slfhEquipmentAirportService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "设备管理-机场-批量删除")
	@ApiOperation(value="设备管理-机场-批量删除", notes="设备管理-机场-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_equipment_airport:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.slfhEquipmentAirportService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "设备管理-机场-通过id查询")
	@ApiOperation(value="设备管理-机场-通过id查询", notes="设备管理-机场-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<SlfhEquipmentAirport> queryById(@RequestParam(name="id",required=true) String id) {
		SlfhEquipmentAirport slfhEquipmentAirport = slfhEquipmentAirportService.getById(id);
		if(slfhEquipmentAirport==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(slfhEquipmentAirport);
	}

	 /**
	  * 通过name查询
	  *
	  * @param name
	  * @return
	  */
	 //@AutoLog(value = "设备管理-机场-通过name查询")
	 @ApiOperation(value="设备管理-机场-通过name查询", notes="设备管理-机场-通过name查询")
	 @GetMapping(value = "/queryByName")
	 public Result<List<SlfhEquipmentAirport>> queryByName(@RequestParam(name="name",required=true) String name) {
		 QueryWrapper<SlfhEquipmentAirport> queryWrapper = new QueryWrapper<>();
		 queryWrapper.like("name", name);
		 List<SlfhEquipmentAirport> airports = slfhEquipmentAirportMapper.selectList(queryWrapper);
		 return Result.OK(airports);
	 }

    /**
    * 导出excel
    *
    * @param request
    * @param slfhEquipmentAirport
    */
    //@RequiresPermissions("org.jeecg.modules.demo:slfh_equipment_airport:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SlfhEquipmentAirport slfhEquipmentAirport) {
        return super.exportXls(request, slfhEquipmentAirport, SlfhEquipmentAirport.class, "设备管理-机场");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("slfh_equipment_airport:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, SlfhEquipmentAirport.class);
    }

}
