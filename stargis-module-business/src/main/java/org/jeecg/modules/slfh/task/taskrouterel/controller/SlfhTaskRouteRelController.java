package org.jeecg.modules.slfh.task.taskrouterel.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.slfh.task.taskrouterel.entity.SlfhTaskRouteRel;
import org.jeecg.modules.slfh.task.taskrouterel.service.ISlfhTaskRouteRelService;

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
 * @Description: 航线排班表
 * @Author: jeecg-boot
 * @Date:   2026-02-05
 * @Version: V1.0
 */
@Api(tags="航线排班表")
@RestController
@RequestMapping("/task/shifts")
@Slf4j
public class SlfhTaskRouteRelController extends JeecgController<SlfhTaskRouteRel, ISlfhTaskRouteRelService> {
	@Autowired
	private ISlfhTaskRouteRelService slfhTaskRouteRelService;
	
	/**
	 * 分页列表查询
	 *
	 * @param slfhTaskRouteRel
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "航线排班表-分页列表查询")
	@ApiOperation(value="航线排班表-分页列表查询", notes="航线排班表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<SlfhTaskRouteRel>> queryPageList(SlfhTaskRouteRel slfhTaskRouteRel,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SlfhTaskRouteRel> queryWrapper = QueryGenerator.initQueryWrapper(slfhTaskRouteRel, req.getParameterMap());
		Page<SlfhTaskRouteRel> page = new Page<SlfhTaskRouteRel>(pageNo, pageSize);
		IPage<SlfhTaskRouteRel> pageList = slfhTaskRouteRelService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param slfhTaskRouteRel
	 * @return
	 */
	@AutoLog(value = "航线排班表-添加")
	@ApiOperation(value="航线排班表-添加", notes="航线排班表-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_task_route_rel:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody SlfhTaskRouteRel slfhTaskRouteRel) {
		slfhTaskRouteRelService.save(slfhTaskRouteRel);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param slfhTaskRouteRel
	 * @return
	 */
	@AutoLog(value = "航线排班表-编辑")
	@ApiOperation(value="航线排班表-编辑", notes="航线排班表-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_task_route_rel:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody SlfhTaskRouteRel slfhTaskRouteRel) {
		slfhTaskRouteRelService.updateById(slfhTaskRouteRel);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "航线排班表-通过id删除")
	@ApiOperation(value="航线排班表-通过id删除", notes="航线排班表-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_task_route_rel:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		slfhTaskRouteRelService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "航线排班表-批量删除")
	@ApiOperation(value="航线排班表-批量删除", notes="航线排班表-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_task_route_rel:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.slfhTaskRouteRelService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "航线排班表-通过id查询")
	@ApiOperation(value="航线排班表-通过id查询", notes="航线排班表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<SlfhTaskRouteRel> queryById(@RequestParam(name="id",required=true) String id) {
		SlfhTaskRouteRel slfhTaskRouteRel = slfhTaskRouteRelService.getById(id);
		if(slfhTaskRouteRel==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(slfhTaskRouteRel);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param slfhTaskRouteRel
    */
    //@RequiresPermissions("org.jeecg.modules.demo:slfh_task_route_rel:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SlfhTaskRouteRel slfhTaskRouteRel) {
        return super.exportXls(request, slfhTaskRouteRel, SlfhTaskRouteRel.class, "航线排班表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("slfh_task_route_rel:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, SlfhTaskRouteRel.class);
    }

}
