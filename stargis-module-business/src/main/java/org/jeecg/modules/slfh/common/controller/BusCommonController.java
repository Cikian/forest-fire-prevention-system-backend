package org.jeecg.modules.slfh.common.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.constant.SymbolConstant;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.util.CommonUtils;
import org.jeecg.common.util.filter.FileTypeFilter;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.slfh.common.service.BusCommonService;
import org.jeecg.modules.slfh.common.utils.BusUtils;
import org.jeecg.modules.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @implNote
 * @see <a href="https://www.cikian.cn">https://www.cikian.cn</a>
 * @since 2025-10-10 17:29
 */
@Api(tags = "通用API")
@RestController
@RequestMapping("/api")
@Slf4j
public class BusCommonController {
    @Value(value = "${jeecg.path.upload}")
    private String uploadpath;
    @Value(value = "${server.servlet.context-path}")
    private String contextPath;
    @Value(value = "${server.port}")
    private String port;
    /**
     * 本地：local minio：minio 阿里：alioss
     */
    @Value(value = "${jeecg.uploadType}")
    private String uploadType;
    @Autowired
    private BusCommonService commonService;

    @GetMapping("/dict")
    @ApiOperation(value = "根据字典code获取字典item", notes = "根据字典code获取字典item")
    public Result<?> getDict(@RequestParam String dictCode, HttpServletRequest request) {
        BusUtils.extractPathFromPattern(request);
        return Result.ok(commonService.getDictItems(dictCode));
    }

    /**
     * 预览图片&下载文件
     * 请求示例：http://IP:port/api/static/img/filename.jpg
     *
     * @param request
     * @param response
     */
    @GetMapping(value = "/static/**")
    @ApiOperation(value = "预览图片&下载文件", notes = "预览图片&下载文件")
    public void view(HttpServletRequest request, HttpServletResponse response) {
        // ISO-8859-1 ==> UTF-8 进行编码转换
        String imgPath = BusUtils.extractPathFromPattern(request);
        if (oConvertUtils.isEmpty(imgPath) || CommonConstant.STRING_NULL.equals(imgPath)) {
            return;
        }
        // 其余处理略
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            imgPath = imgPath.replace("..", "").replace("../", "");
            if (imgPath.endsWith(SymbolConstant.COMMA)) {
                imgPath = imgPath.substring(0, imgPath.length() - 1);
            }
            String filePath = uploadpath + File.separator + imgPath;
            File file = new File(filePath);
            if (!file.exists()) {
                response.setStatus(404);
                throw new RuntimeException("文件[" + imgPath + "]不存在..");
            }
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + new String(file.getName().getBytes("UTF-8"), "iso-8859-1"));
            inputStream = new BufferedInputStream(new FileInputStream(filePath));
            outputStream = response.getOutputStream();
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, len);
            }
            response.flushBuffer();
        } catch (IOException e) {
            log.error("预览文件失败" + e.getMessage());
            response.setStatus(404);
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }

    }

    @GetMapping(value = "/view")
    @ApiOperation(value = "预览图片", notes = "预览图片&下载文件(查询参数)")
    public void viewImg(@RequestParam String path, HttpServletResponse response) {
        // ISO-8859-1 ==> UTF-8 进行编码转换
        String imgPath = path;
        if (oConvertUtils.isEmpty(imgPath) || CommonConstant.STRING_NULL.equals(imgPath)) {
            return;
        }
        // 其余处理略
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            imgPath = imgPath.replace("..", "").replace("../", "");
            if (imgPath.endsWith(SymbolConstant.COMMA)) {
                imgPath = imgPath.substring(0, imgPath.length() - 1);
            }
            String filePath = uploadpath + File.separator + imgPath;
            File file = new File(filePath);
            if (!file.exists()) {
                response.setStatus(404);
                throw new RuntimeException("文件[" + imgPath + "]不存在..");
            }
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + new String(file.getName().getBytes("UTF-8"), "iso-8859-1"));
            inputStream = new BufferedInputStream(new FileInputStream(filePath));
            outputStream = response.getOutputStream();
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, len);
            }
            response.flushBuffer();
        } catch (IOException e) {
            log.error("预览文件失败" + e.getMessage());
            response.setStatus(404);
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    @GetMapping(value = "/pdf")
    @ApiOperation(value = "下载pdf", notes = "v")
    public void getExportPDF(@RequestParam String path, HttpServletResponse response) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=UTF-8");

        try {
            InputStream inputStream = null;
            String fullPath = uploadpath + path;
            File file = new File(fullPath);
            inputStream = Files.newInputStream(file.toPath());
            byte[] data = null;
            if (inputStream != null) {
                data = new byte[inputStream.available()];
            }
            if (inputStream != null) {
                inputStream.read(data);
            }

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");

            OutputStream os = response.getOutputStream();
            if (data != null) {
                os.write(data);
            }
            os.flush();
            os.close();
            if (inputStream != null) {
                inputStream.close();
            }

        } catch (RestClientException | IOException e) {
            e.printStackTrace();
        }

    }

    @GetMapping("/user/byRole")
    @ApiOperation(value = "通过角色code获取用户列表", notes = "通过角色code获取用户列表")
    public Result<List<SysUser>> getUsersByRole(String role) {
        return commonService.getUserByRole(role);
    }

    @PostMapping(value = "/upload")
    public Result<?> upload(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Result<?> result = new Result<>();
        String savePath = "";
        String bizPath = request.getParameter("biz");

        //LOWCOD-2580 sys/common/upload接口存在任意文件上传漏洞
        if (oConvertUtils.isNotEmpty(bizPath)) {
            if (bizPath.contains(SymbolConstant.SPOT_SINGLE_SLASH) || bizPath.contains(SymbolConstant.SPOT_DOUBLE_BACKSLASH)) {
                throw new JeecgBootException("上传目录bizPath，格式非法！");
            }
        }

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获取上传文件对象
        MultipartFile file = multipartRequest.getFile("file");
        if (oConvertUtils.isEmpty(bizPath)) {
            if (CommonConstant.UPLOAD_TYPE_OSS.equals(uploadType)) {
                //未指定目录，则用阿里云默认目录 upload
                bizPath = "upload";
                //result.setMessage("使用阿里云文件上传时，必须添加目录！");
                //result.setSuccess(false);
                //return result;
            } else {
                bizPath = "";
            }
        }
        if (CommonConstant.UPLOAD_TYPE_LOCAL.equals(uploadType)) {
            //update-begin-author:liusq date:20221102 for: 过滤上传文件类型
            FileTypeFilter.fileTypeFilter(file);
            //update-end-author:liusq date:20221102 for: 过滤上传文件类型
            //update-begin-author:lvdandan date:20200928 for:修改JEditor编辑器本地上传
            savePath = this.uploadLocal(file, bizPath);
            //update-begin-author:lvdandan date:20200928 for:修改JEditor编辑器本地上传
            /**  富文本编辑器及markdown本地上传时，采用返回链接方式
             //针对jeditor编辑器如何使 lcaol模式，采用 base64格式存储
             String jeditor = request.getParameter("jeditor");
             if(oConvertUtils.isNotEmpty(jeditor)){
             result.setMessage(CommonConstant.UPLOAD_TYPE_LOCAL);
             result.setSuccess(true);
             return result;
             }else{
             savePath = this.uploadLocal(file,bizPath);
             }
             */
        } else {
            //update-begin-author:taoyan date:20200814 for:文件上传改造
            savePath = CommonUtils.upload(file, bizPath, uploadType);
            //update-end-author:taoyan date:20200814 for:文件上传改造
        }
        if (oConvertUtils.isNotEmpty(savePath)) {
            result.setMessage(savePath);
            result.setSuccess(true);
        } else {
            result.setMessage("上传失败！");
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 本地文件上传
     *
     * @param mf      文件
     * @param bizPath 自定义路径
     * @return
     */
    private String uploadLocal(MultipartFile mf, String bizPath) {
        try {
            String ctxPath = uploadpath;
            String fileName = null;
            File file = new File(ctxPath + File.separator + bizPath + File.separator);
            if (!file.exists()) {
                // 创建文件根目录
                file.mkdirs();
            }
            // 获取文件名
            String orgName = mf.getOriginalFilename();
            orgName = CommonUtils.getFileName(orgName);
            if (orgName.indexOf(SymbolConstant.SPOT) != -1) {
                fileName = orgName.substring(0, orgName.lastIndexOf(".")) + "_" + System.currentTimeMillis() + orgName.substring(orgName.lastIndexOf("."));
            } else {
                fileName = orgName + "_" + System.currentTimeMillis();
            }
            String savePath = file.getPath() + File.separator + fileName;
            File savefile = new File(savePath);
            FileCopyUtils.copy(mf.getBytes(), savefile);
            String dbpath = null;
            if (oConvertUtils.isNotEmpty(bizPath)) {
                dbpath = bizPath + File.separator + fileName;
            } else {
                dbpath = fileName;
            }
            if (dbpath.contains(SymbolConstant.DOUBLE_BACKSLASH)) {
                dbpath = dbpath.replace(SymbolConstant.DOUBLE_BACKSLASH, SymbolConstant.SINGLE_SLASH);
            }
            return dbpath;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }

    @GetMapping("/getCategory")
    public Result<?> getCategory(@RequestParam String id) {
        return Result.ok(commonService.loadCategoryDictItem(id));
    }

}
