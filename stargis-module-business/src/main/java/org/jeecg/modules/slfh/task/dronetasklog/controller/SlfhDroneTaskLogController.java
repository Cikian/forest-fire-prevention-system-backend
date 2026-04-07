package org.jeecg.modules.slfh.task.dronetasklog.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.slfh.task.dronetasklog.entity.SlfhDroneTaskLog;
import org.jeecg.modules.slfh.task.dronetasklog.service.ISlfhDroneTaskLogService;

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
 * @Description: 任务日志表
 * @Author: jeecg-boot
 * @Date:   2026-02-05
 * @Version: V1.0
 */
@Api(tags="任务日志表")
@RestController
@RequestMapping("/task/log")
@Slf4j
public class SlfhDroneTaskLogController extends JeecgController<SlfhDroneTaskLog, ISlfhDroneTaskLogService> {
	@Autowired
	private ISlfhDroneTaskLogService slfhDroneTaskLogService;
	
	/**
	 * 分页列表查询
	 *
	 * @param slfhDroneTaskLog
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "任务日志表-分页列表查询")
	@ApiOperation(value="任务日志表-分页列表查询", notes="任务日志表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<SlfhDroneTaskLog>> queryPageList(SlfhDroneTaskLog slfhDroneTaskLog,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SlfhDroneTaskLog> queryWrapper = QueryGenerator.initQueryWrapper(slfhDroneTaskLog, req.getParameterMap());
		Page<SlfhDroneTaskLog> page = new Page<SlfhDroneTaskLog>(pageNo, pageSize);
		IPage<SlfhDroneTaskLog> pageList = slfhDroneTaskLogService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param slfhDroneTaskLog
	 * @return
	 */
	@AutoLog(value = "任务日志表-添加")
	@ApiOperation(value="任务日志表-添加", notes="任务日志表-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_drone_task_log:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody SlfhDroneTaskLog slfhDroneTaskLog) {
		slfhDroneTaskLogService.save(slfhDroneTaskLog);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param slfhDroneTaskLog
	 * @return
	 */
	@AutoLog(value = "任务日志表-编辑")
	@ApiOperation(value="任务日志表-编辑", notes="任务日志表-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_drone_task_log:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody SlfhDroneTaskLog slfhDroneTaskLog) {
		slfhDroneTaskLogService.updateById(slfhDroneTaskLog);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "任务日志表-通过id删除")
	@ApiOperation(value="任务日志表-通过id删除", notes="任务日志表-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_drone_task_log:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		slfhDroneTaskLogService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "任务日志表-批量删除")
	@ApiOperation(value="任务日志表-批量删除", notes="任务日志表-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_drone_task_log:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.slfhDroneTaskLogService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "任务日志表-通过id查询")
	@ApiOperation(value="任务日志表-通过id查询", notes="任务日志表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<SlfhDroneTaskLog> queryById(@RequestParam(name="id",required=true) String id) {
		SlfhDroneTaskLog slfhDroneTaskLog = slfhDroneTaskLogService.getById(id);
		if(slfhDroneTaskLog==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(slfhDroneTaskLog);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param slfhDroneTaskLog
    */
    //@RequiresPermissions("org.jeecg.modules.demo:slfh_drone_task_log:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SlfhDroneTaskLog slfhDroneTaskLog) {
        return super.exportXls(request, slfhDroneTaskLog, SlfhDroneTaskLog.class, "任务日志表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("slfh_drone_task_log:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, SlfhDroneTaskLog.class);
    }

}
