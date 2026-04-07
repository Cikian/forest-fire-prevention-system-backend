package org.jeecg.modules.slfh.route.controller;

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
import org.jeecg.modules.slfh.route.entity.NoflyZone;
import org.jeecg.modules.slfh.route.service.INoflyZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @author Cikian
 * @version 1.0
 * @implNote
 * @see <a href="https://www.cikian.cn">https://www.cikian.cn</a>
 * @since 2026-04-03 14:50
 */

@Api(tags = "限飞区管理")
@RestController
@RequestMapping("/drone/nofly")
@Slf4j
public class NoflyZoneController extends JeecgController<NoflyZone, INoflyZoneService> {
    @Autowired
    private INoflyZoneService noflyZoneService;

    /**
     * 分页列表查询
     *
     * @param noflyZone
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    //@AutoLog(value = "限飞区管理-分页列表查询")
    @ApiOperation(value = "限飞区管理-分页列表查询", notes = "限飞区管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<NoflyZone>> queryPageList(NoflyZone noflyZone,
                                                  @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                  HttpServletRequest req) {
        QueryWrapper<NoflyZone> queryWrapper = QueryGenerator.initQueryWrapper(noflyZone, req.getParameterMap());
        Page<NoflyZone> page = new Page<NoflyZone>(pageNo, pageSize);
        IPage<NoflyZone> pageList = noflyZoneService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param noflyZone
     * @return
     */
    @AutoLog(value = "限飞区管理-添加")
    @ApiOperation(value = "限飞区管理-添加", notes = "限飞区管理-添加")
    //@RequiresPermissions("org.jeecg.modules.demo:slfh_nofly_zone:add")
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody NoflyZone noflyZone) {
        noflyZoneService.save(noflyZone);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param noflyZone
     * @return
     */
    @AutoLog(value = "限飞区管理-编辑")
    @ApiOperation(value = "限飞区管理-编辑", notes = "限飞区管理-编辑")
    //@RequiresPermissions("org.jeecg.modules.demo:slfh_nofly_zone:edit")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public Result<String> edit(@RequestBody NoflyZone noflyZone) {
        noflyZoneService.updateById(noflyZone);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "限飞区管理-通过id删除")
    @ApiOperation(value = "限飞区管理-通过id删除", notes = "限飞区管理-通过id删除")
    //@RequiresPermissions("org.jeecg.modules.demo:slfh_nofly_zone:delete")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam(name = "id", required = true) String id) {
        noflyZoneService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "限飞区管理-批量删除")
    @ApiOperation(value = "限飞区管理-批量删除", notes = "限飞区管理-批量删除")
    //@RequiresPermissions("org.jeecg.modules.demo:slfh_nofly_zone:deleteBatch")
    @DeleteMapping(value = "/deleteBatch")
    public Result<String> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.noflyZoneService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    //@AutoLog(value = "限飞区管理-通过id查询")
    @ApiOperation(value = "限飞区管理-通过id查询", notes = "限飞区管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<NoflyZone> queryById(@RequestParam(name = "id", required = true) String id) {
        NoflyZone noflyZone = noflyZoneService.getById(id);
        if (noflyZone == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(noflyZone);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param noflyZone
     */
    //@RequiresPermissions("org.jeecg.modules.demo:slfh_nofly_zone:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, NoflyZone noflyZone) {
        return super.exportXls(request, noflyZone, NoflyZone.class, "限飞区管理");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    //@RequiresPermissions("slfh_nofly_zone:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, NoflyZone.class);
    }

}
