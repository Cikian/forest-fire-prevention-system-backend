package org.jeecg.modules.slfh.equipment.airport.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.slfh.equipment.airport.entity.Airport;
import org.jeecg.modules.slfh.equipment.airport.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @implNote
 * @see <a href="https://www.cikian.cn">https://www.cikian.cn</a>
 * @since 2026-03-29 22:20
 */

@Api(tags = "设备管理-机场")
@RestController
@RequestMapping("/dev/airport")
@Slf4j
public class AirportController extends JeecgController<Airport, AirportService> {
    @Autowired
    private AirportService airportService;

    /**
     * 分页列表查询
     *
     * @param airport
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    //@AutoLog(value = "设备管理-机场-分页列表查询")
    @ApiOperation(value = "设备管理-机场-分页列表查询", notes = "设备管理-机场-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<Airport>> queryPageList(Airport airport,
                                                @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                HttpServletRequest req) {
        QueryWrapper<Airport> queryWrapper = QueryGenerator.initQueryWrapper(airport, req.getParameterMap());
        queryWrapper.orderByAsc("status");

        Page<Airport> page = new Page<Airport>(pageNo, pageSize);
        IPage<Airport> pageList = airportService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param airport
     * @return
     */
    @AutoLog(value = "设备管理-机场-添加")
    @ApiOperation(value = "设备管理-机场-添加", notes = "设备管理-机场-添加")
    //@RequiresPermissions("org.jeecg.modules.demo:slfh_equipment_airport:add")
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody Airport airport) {
        airportService.save(airport);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param airport
     * @return
     */
    @AutoLog(value = "设备管理-机场-编辑")
    @ApiOperation(value = "设备管理-机场-编辑", notes = "设备管理-机场-编辑")
    //@RequiresPermissions("org.jeecg.modules.demo:slfh_equipment_airport:edit")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public Result<String> edit(@RequestBody Airport airport) {
        airportService.updateById(airport);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "设备管理-机场-通过id删除")
    @ApiOperation(value = "设备管理-机场-通过id删除", notes = "设备管理-机场-通过id删除")
    //@RequiresPermissions("org.jeecg.modules.demo:slfh_equipment_airport:delete")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam(name = "id", required = true) String id) {
        airportService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "设备管理-机场-批量删除")
    @ApiOperation(value = "设备管理-机场-批量删除", notes = "设备管理-机场-批量删除")
    //@RequiresPermissions("org.jeecg.modules.demo:slfh_equipment_airport:deleteBatch")
    @DeleteMapping(value = "/deleteBatch")
    public Result<String> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.airportService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    //@AutoLog(value = "设备管理-机场-通过id查询")
    @ApiOperation(value = "设备管理-机场-通过id查询", notes = "设备管理-机场-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<Airport> queryById(@RequestParam(name = "id", required = true) String id) {
        Airport airport = airportService.getById(id);
        if (airport == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(airport);
    }

    /**
     * 通过name查询
     *
     * @param name
     * @return
     */
    //@AutoLog(value = "设备管理-机场-通过name查询")
    @ApiOperation(value = "设备管理-机场-通过name查询", notes = "设备管理-机场-通过name查询")
    @GetMapping(value = "/queryByName")
    public Result<List<Airport>> queryByName(@RequestParam(name = "name", required = true) String name) {
        QueryWrapper<Airport> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        List<Airport> airports = airportService.list(queryWrapper);
        return Result.OK(airports);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param airport
     */
    //@RequiresPermissions("org.jeecg.modules.demo:slfh_equipment_airport:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Airport airport) {
        return super.exportXls(request, airport, Airport.class, "设备管理-机场");
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
        return super.importExcel(request, response, Airport.class);
    }

    @GetMapping("/statistics")
    @ApiOperation(value = "首页-机场统计", notes = "首页-机场统计")
    public Result<?> statistics(HttpServletRequest req) {
        JSONObject res = airportService.getHomeStatistics();
        return Result.OK(res);
    }

}
