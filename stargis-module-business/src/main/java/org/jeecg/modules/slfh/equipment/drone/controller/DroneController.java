package org.jeecg.modules.slfh.equipment.drone.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.slfh.equipment.drone.entity.Drone;
import org.jeecg.modules.slfh.equipment.drone.service.DroneService;
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

@Api(tags="设备管理-无人机")
@RestController
@RequestMapping("/dev/drone")
@Slf4j
public class DroneController extends JeecgController<Drone, DroneService> {
	@Autowired
	private DroneService droneService;

	/**
	 * 分页列表查询
	 *
	 * @param drone
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "设备管理-无人机-分页列表查询")
	@ApiOperation(value="设备管理-无人机-分页列表查询", notes="设备管理-无人机-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Drone>> queryPageList(Drone drone,
											  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
											  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
											  HttpServletRequest req) {
		QueryWrapper<Drone> queryWrapper = QueryGenerator.initQueryWrapper(drone, req.getParameterMap());
		queryWrapper.orderByAsc("status");
		Page<Drone> page = new Page<Drone>(pageNo, pageSize);
		IPage<Drone> pageList = droneService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param drone
	 * @return
	 */
	@AutoLog(value = "设备管理-无人机-添加")
	@ApiOperation(value="设备管理-无人机-添加", notes="设备管理-无人机-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_equipment_drone:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody Drone drone) {
		droneService.save(drone);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param drone
	 * @return
	 */
	@AutoLog(value = "设备管理-无人机-编辑")
	@ApiOperation(value="设备管理-无人机-编辑", notes="设备管理-无人机-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_equipment_drone:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody Drone drone) {
		droneService.updateById(drone);
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
		droneService.removeById(id);
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
		this.droneService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<Drone> queryById(@RequestParam(name="id",required=true) String id) {
		Drone drone = droneService.getById(id);
		if(drone ==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(drone);
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
	 public Result<List<Drone>> queryByName(@RequestParam(name="name",required=true) String name) {
		 QueryWrapper<Drone> queryWrapper = new QueryWrapper<>();
		 queryWrapper.like("name", name);
		 List<Drone> droneList = droneService.list(queryWrapper);
		 return Result.OK(droneList);
	 }

    /**
    * 导出excel
    *
    * @param request
    * @param drone
    */
    //@RequiresPermissions("org.jeecg.modules.demo:slfh_equipment_drone:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Drone drone) {
        return super.exportXls(request, drone, Drone.class, "设备管理-无人机");
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
        return super.importExcel(request, response, Drone.class);
    }

	@GetMapping("/statistics")
	@ApiOperation(value = "首页-无人机统计", notes = "首页-无人机统计")
	public Result<?> statistics(HttpServletRequest req) {
		JSONObject res = droneService.getHomeStatistics();
		return Result.OK(res);
	}

}
