package org.jeecg.modules.slfh.task.donetaskstatus.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.slfh.task.donetaskstatus.entity.SlfhDroneTaskStatus;
import org.jeecg.modules.slfh.task.donetaskstatus.service.ISlfhDroneTaskStatusService;

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
 * @Description: 任务运行态表
 * @Author: jeecg-boot
 * @Date:   2026-02-05
 * @Version: V1.0
 */
@Api(tags="任务运行态表")
@RestController
@RequestMapping("/task/work")
@Slf4j
public class SlfhDroneTaskStatusController extends JeecgController<SlfhDroneTaskStatus, ISlfhDroneTaskStatusService> {
	@Autowired
	private ISlfhDroneTaskStatusService slfhDroneTaskStatusService;
	
	/**
	 * 分页列表查询
	 *
	 * @param slfhDroneTaskStatus
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "任务运行态表-分页列表查询")
	@ApiOperation(value="任务运行态表-分页列表查询", notes="任务运行态表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<SlfhDroneTaskStatus>> queryPageList(SlfhDroneTaskStatus slfhDroneTaskStatus,
															@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
															@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
															HttpServletRequest req) {
		QueryWrapper<SlfhDroneTaskStatus> queryWrapper = QueryGenerator.initQueryWrapper(slfhDroneTaskStatus, req.getParameterMap());
		Page<SlfhDroneTaskStatus> page = new Page<SlfhDroneTaskStatus>(pageNo, pageSize);
		IPage<SlfhDroneTaskStatus> pageList = slfhDroneTaskStatusService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param slfhDroneTaskStatus
	 * @return
	 */
	@AutoLog(value = "任务运行态表-添加")
	@ApiOperation(value="任务运行态表-添加", notes="任务运行态表-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_drone_task_status:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody SlfhDroneTaskStatus slfhDroneTaskStatus) {
		slfhDroneTaskStatusService.save(slfhDroneTaskStatus);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param slfhDroneTaskStatus
	 * @return
	 */
	@AutoLog(value = "任务运行态表-编辑")
	@ApiOperation(value="任务运行态表-编辑", notes="任务运行态表-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_drone_task_status:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody SlfhDroneTaskStatus slfhDroneTaskStatus) {
		slfhDroneTaskStatusService.updateById(slfhDroneTaskStatus);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "任务运行态表-通过id删除")
	@ApiOperation(value="任务运行态表-通过id删除", notes="任务运行态表-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_drone_task_status:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		slfhDroneTaskStatusService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "任务运行态表-批量删除")
	@ApiOperation(value="任务运行态表-批量删除", notes="任务运行态表-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_drone_task_status:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.slfhDroneTaskStatusService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "任务运行态表-通过id查询")
	@ApiOperation(value="任务运行态表-通过id查询", notes="任务运行态表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<SlfhDroneTaskStatus> queryById(@RequestParam(name="id",required=true) String id) {
		SlfhDroneTaskStatus slfhDroneTaskStatus = slfhDroneTaskStatusService.getById(id);
		if(slfhDroneTaskStatus==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(slfhDroneTaskStatus);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param slfhDroneTaskStatus
    */
    //@RequiresPermissions("org.jeecg.modules.demo:slfh_drone_task_status:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SlfhDroneTaskStatus slfhDroneTaskStatus) {
        return super.exportXls(request, slfhDroneTaskStatus, SlfhDroneTaskStatus.class, "任务运行态表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("slfh_drone_task_status:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, SlfhDroneTaskStatus.class);
    }

}
