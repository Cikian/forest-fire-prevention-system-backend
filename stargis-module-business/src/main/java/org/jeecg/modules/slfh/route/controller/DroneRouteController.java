package org.jeecg.modules.slfh.route.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.slfh.route.entity.DronePoint;
import org.jeecg.modules.slfh.route.entity.DroneRoute;
import org.jeecg.modules.slfh.route.service.IDronePointService;
import org.jeecg.modules.slfh.route.service.IDroneRouteService;
import org.jeecg.modules.slfh.route.vo.DroneRoutePage;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Cikian
 * @version 1.0
 * @implNote
 * @see <a href="https://www.cikian.cn">https://www.cikian.cn</a>
 * @since 2026-03-29 22:16
 */

@Api(tags = "航线表")
@RestController
@RequestMapping("/drone/route")
@Slf4j
public class DroneRouteController {
    @Autowired
    private IDroneRouteService routeService;
    @Autowired
    private IDronePointService pointService;

    /**
     * 分页列表查询
     *
     * @param droneRoute
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    //@AutoLog(value = "航线表-分页列表查询")
    @ApiOperation(value = "航线表-分页列表查询", notes = "航线表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<DroneRoute>> queryPageList(DroneRoute droneRoute,
                                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                   HttpServletRequest req) {
        QueryWrapper<DroneRoute> queryWrapper = QueryGenerator.initQueryWrapper(droneRoute, req.getParameterMap());
        queryWrapper.orderByDesc("create_time");
        Page<DroneRoute> page = new Page<DroneRoute>(pageNo, pageSize);
        IPage<DroneRoute> pageList = routeService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param droneRoutePage
     * @return
     */
    @AutoLog(value = "航线表-添加")
    @ApiOperation(value = "航线表-添加", notes = "航线表-添加")
    //@RequiresPermissions("org.jeecg.modules.demo:slfh_drone_route:add")
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody DroneRoutePage droneRoutePage) {
        DroneRoute droneRoute = new DroneRoute();
        BeanUtils.copyProperties(droneRoutePage, droneRoute);
        routeService.saveMain(droneRoute, droneRoutePage.getDronePointList());
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param droneRoutePage
     * @return
     */
    @AutoLog(value = "航线表-编辑")
    @ApiOperation(value = "航线表-编辑", notes = "航线表-编辑")
    //@RequiresPermissions("org.jeecg.modules.demo:slfh_drone_route:edit")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public Result<String> edit(@RequestBody DroneRoutePage droneRoutePage) {
        DroneRoute droneRoute = new DroneRoute();
        BeanUtils.copyProperties(droneRoutePage, droneRoute);
        DroneRoute droneRouteEntity = routeService.getById(droneRoute.getId());
        if (droneRouteEntity == null) {
            return Result.error("未找到对应数据");
        }
        routeService.updateMain(droneRoute, droneRoutePage.getDronePointList());
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "航线表-通过id删除")
    @ApiOperation(value = "航线表-通过id删除", notes = "航线表-通过id删除")
    //@RequiresPermissions("org.jeecg.modules.demo:slfh_drone_route:delete")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam(name = "id", required = true) String id) {
        routeService.delMain(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "航线表-批量删除")
    @ApiOperation(value = "航线表-批量删除", notes = "航线表-批量删除")
    //@RequiresPermissions("org.jeecg.modules.demo:slfh_drone_route:deleteBatch")
    @DeleteMapping(value = "/deleteBatch")
    public Result<String> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.routeService.delBatchMain(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    //@AutoLog(value = "航线表-通过id查询")
    @ApiOperation(value = "航线表-通过id查询", notes = "航线表-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<DroneRoute> queryById(@RequestParam(name = "id", required = true) String id) {
        DroneRoute droneRoute = routeService.getById(id);
        if (droneRoute == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(droneRoute);

    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    //@AutoLog(value = "航点表通过主表ID查询")
    @ApiOperation(value = "航点表主表ID查询", notes = "航点表-通主表ID查询")
    @GetMapping(value = "/queryDronePointByMainId")
    public Result<List<DronePoint>> queryDronePointListByMainId(@RequestParam(name = "id", required = true) String id) {
        List<DronePoint> dronePointList = pointService.selectByMainId(id);
        return Result.OK(dronePointList);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param droneRoute
     */
    //@RequiresPermissions("org.jeecg.modules.demo:slfh_drone_route:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, DroneRoute droneRoute) {
        // Step.1 组装查询条件查询数据
        QueryWrapper<DroneRoute> queryWrapper = QueryGenerator.initQueryWrapper(droneRoute, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        //配置选中数据查询条件
        String selections = request.getParameter("selections");
        if (oConvertUtils.isNotEmpty(selections)) {
            List<String> selectionList = Arrays.asList(selections.split(","));
            queryWrapper.in("id", selectionList);
        }
        //Step.2 获取导出数据
        List<DroneRoute> droneRouteList = routeService.list(queryWrapper);

        // Step.3 组装pageList
        List<DroneRoutePage> pageList = new ArrayList<DroneRoutePage>();
        for (DroneRoute main : droneRouteList) {
            DroneRoutePage vo = new DroneRoutePage();
            BeanUtils.copyProperties(main, vo);
            List<DronePoint> dronePointList = pointService.selectByMainId(main.getId());
            vo.setDronePointList(dronePointList);
            pageList.add(vo);
        }

        // Step.4 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "航线表列表");
        mv.addObject(NormalExcelConstants.CLASS, DroneRoutePage.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("航线表数据", "导出人:" + sysUser.getRealname(), "航线表"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    //@RequiresPermissions("org.jeecg.modules.demo:slfh_drone_route:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            // 获取上传文件对象
            MultipartFile file = entity.getValue();
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<DroneRoutePage> list = ExcelImportUtil.importExcel(file.getInputStream(), DroneRoutePage.class, params);
                for (DroneRoutePage page : list) {
                    DroneRoute po = new DroneRoute();
                    BeanUtils.copyProperties(page, po);
                    routeService.saveMain(po, page.getDronePointList());
                }
                return Result.OK("文件导入成功！数据行数:" + list.size());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败:" + e.getMessage());
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.OK("文件导入失败！");
    }

}
