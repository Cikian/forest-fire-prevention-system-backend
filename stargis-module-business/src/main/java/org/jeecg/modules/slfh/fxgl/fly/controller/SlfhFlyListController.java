package org.jeecg.modules.slfh.fxgl.fly.controller;

import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.slfh.fxgl.fly.entity.SlfhFlyList;
import org.jeecg.modules.slfh.fxgl.fly.service.ISlfhFlyListService;

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
 * @Description: 飞行管理-事件列表
 * @Author: jeecg-boot
 * @Date:   2026-03-09
 * @Version: V1.0
 */
@Api(tags="飞行管理-事件列表")
@RestController
@RequestMapping("/fly/event")
@Slf4j
public class SlfhFlyListController extends JeecgController<SlfhFlyList, ISlfhFlyListService> {
	@Autowired
	private ISlfhFlyListService flyListService;
	
	/**
	 * 分页列表查询
	 *
	 * @param slfhFlyList
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "飞行管理-事件列表-分页列表查询")
	@ApiOperation(value="飞行管理-事件列表-分页列表查询", notes="飞行管理-事件列表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<SlfhFlyList>> queryPageList(SlfhFlyList slfhFlyList,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SlfhFlyList> queryWrapper = QueryGenerator.initQueryWrapper(slfhFlyList, req.getParameterMap());
		Page<SlfhFlyList> page = new Page<SlfhFlyList>(pageNo, pageSize);
		IPage<SlfhFlyList> pageList = flyListService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param slfhFlyList
	 * @return
	 */
	@AutoLog(value = "飞行管理-事件列表-添加")
	@ApiOperation(value="飞行管理-事件列表-添加", notes="飞行管理-事件列表-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_fly_list:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody SlfhFlyList slfhFlyList) {
		flyListService.save(slfhFlyList);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param slfhFlyList
	 * @return
	 */
	@AutoLog(value = "飞行管理-事件列表-编辑")
	@ApiOperation(value="飞行管理-事件列表-编辑", notes="飞行管理-事件列表-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_fly_list:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody SlfhFlyList slfhFlyList) {
		flyListService.updateById(slfhFlyList);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "飞行管理-事件列表-通过id删除")
	@ApiOperation(value="飞行管理-事件列表-通过id删除", notes="飞行管理-事件列表-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_fly_list:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		flyListService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "飞行管理-事件列表-批量删除")
	@ApiOperation(value="飞行管理-事件列表-批量删除", notes="飞行管理-事件列表-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_fly_list:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.flyListService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "飞行管理-事件列表-通过id查询")
	@ApiOperation(value="飞行管理-事件列表-通过id查询", notes="飞行管理-事件列表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<SlfhFlyList> queryById(@RequestParam(name="id",required=true) String id) {
		SlfhFlyList slfhFlyList = flyListService.getById(id);
		if(slfhFlyList==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(slfhFlyList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param slfhFlyList
    */
    //@RequiresPermissions("org.jeecg.modules.demo:slfh_fly_list:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SlfhFlyList slfhFlyList) {
        return super.exportXls(request, slfhFlyList, SlfhFlyList.class, "飞行管理-事件列表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("slfh_fly_list:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, SlfhFlyList.class);
    }

	 @GetMapping("/statistics")
	 @ApiOperation(value = "飞行事件统计", notes = "飞行事件统计")
	 public Result<?> statistics(HttpServletRequest req,
								 @RequestParam(name = "startTime", defaultValue = "-1") String startTime,
								 @RequestParam(name = "endTime", defaultValue = "-1") String endTime
								 ) {
		Date startDate = null;
		Date endDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			if (!"-1".equals(startTime)) {
				startDate = sdf.parse(startTime);
			}
			if (!"-1".equals(endTime)) {
				endDate = sdf.parse(endTime);
			}
		} catch (Exception e) {
			log.error("日期格式转换失败", e);
			return Result.error("日期格式错误，请使用 yyyy-MM-dd HH:mm:ss 格式");
		}

		 JSONObject res = flyListService.getStatistics(startDate, endDate);
		 return Result.OK(res);
	 }

}
