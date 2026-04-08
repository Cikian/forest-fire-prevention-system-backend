package org.jeecg.modules.slfh.task.droneroute.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.slfh.task.droneroute.entity.SlfhDroneRoute;
import org.jeecg.modules.slfh.task.droneroute.service.ISlfhDroneRouteService;

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
 * @Description: 航线表
 * @Author: jeecg-boot
 * @Date:   2026-02-05
 * @Version: V1.0
 */
@Api(tags="航线表")
@RestController
@RequestMapping("/slfh/route")
@Slf4j
public class SlfhDroneRouteController extends JeecgController<SlfhDroneRoute, ISlfhDroneRouteService> {
	@Autowired
	private ISlfhDroneRouteService slfhDroneRouteService;
	
	/**
	 * 分页列表查询
	 *
	 * @param slfhDroneRoute
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "航线表-分页列表查询")
	@ApiOperation(value="航线表-分页列表查询", notes="航线表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<SlfhDroneRoute>> queryPageList(SlfhDroneRoute slfhDroneRoute,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SlfhDroneRoute> queryWrapper = QueryGenerator.initQueryWrapper(slfhDroneRoute, req.getParameterMap());
		Page<SlfhDroneRoute> page = new Page<SlfhDroneRoute>(pageNo, pageSize);
		IPage<SlfhDroneRoute> pageList = slfhDroneRouteService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param slfhDroneRoute
	 * @return
	 */
	@AutoLog(value = "航线表-添加")
	@ApiOperation(value="航线表-添加", notes="航线表-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_drone_route:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody SlfhDroneRoute slfhDroneRoute) {
		slfhDroneRouteService.save(slfhDroneRoute);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param slfhDroneRoute
	 * @return
	 */
	@AutoLog(value = "航线表-编辑")
	@ApiOperation(value="航线表-编辑", notes="航线表-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_drone_route:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody SlfhDroneRoute slfhDroneRoute) {
		slfhDroneRouteService.updateById(slfhDroneRoute);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "航线表-通过id删除")
	@ApiOperation(value="航线表-通过id删除", notes="航线表-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_drone_route:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		slfhDroneRouteService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "航线表-批量删除")
	@ApiOperation(value="航线表-批量删除", notes="航线表-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_drone_route:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.slfhDroneRouteService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "航线表-通过id查询")
	@ApiOperation(value="航线表-通过id查询", notes="航线表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<SlfhDroneRoute> queryById(@RequestParam(name="id",required=true) String id) {
		SlfhDroneRoute slfhDroneRoute = slfhDroneRouteService.getById(id);
		if(slfhDroneRoute==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(slfhDroneRoute);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param slfhDroneRoute
    */
    //@RequiresPermissions("org.jeecg.modules.demo:slfh_drone_route:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SlfhDroneRoute slfhDroneRoute) {
        return super.exportXls(request, slfhDroneRoute, SlfhDroneRoute.class, "航线表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("slfh_drone_route:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, SlfhDroneRoute.class);
    }

}
