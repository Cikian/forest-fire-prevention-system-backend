package org.jeecg.modules.slfh.common.utils;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.exception.JeecgBootException;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Cikian
 * @version 1.0
 * @implNote
 * @see <a href="https://www.cikian.cn">https://www.cikian.cn</a>
 * @since 2025-10-13 09:37
 */
@Slf4j
public class BusUtils {

    public static void transformKey (JSONObject json, Map<String, String> replaceFields) {

        for (Map.Entry<String, String> entry : replaceFields.entrySet()) {
            String originKey = entry.getKey();
            String targetKey = entry.getValue();
            if (json.containsKey(originKey)) {
                json.put(targetKey, json.get(originKey));
            }
        }
    }

    /**
     * 计算文件的 MD5 校验码
     *
     * @param file 目标文件
     * @return 文件的 MD5 字符串（32位小写）
     * @throws IllegalArgumentException 文件无效或读取失败
     */
    public static String calculateFileMD5(File file) {
        // 1. 校验文件有效性
        if (file == null || !file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("文件无效或不存在");
        }

        // 2. 初始化 MD5 计算器
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 算法不支持", e);
        }

        // 3. 分块读取文件并计算哈希
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[8192];  // 8KB 缓冲区（平衡内存和IO效率）
            int read;
            while ((read = fis.read(buffer)) != -1) {
                digest.update(buffer, 0, read);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("文件读取失败: " + e.getMessage());
        }

        // 4. 将哈希值转换为十六进制字符串
        byte[] hashBytes = digest.digest();
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    public static byte[] convertBase64ToFile(String base64Str) {
        // 检查参数有效性
        if (!StringUtils.hasText(base64Str)) {
            throw new JeecgBootException("Base64数据不能为空");
        }

        // 处理可能包含的data URI前缀
        String base64Content = base64Str.split(",").length > 1 ?
                base64Str.split(",")[1] :
                base64Str;

        return Base64Utils.decodeFromString(base64Content);
    }

    public static Map<String ,String> saveFile(byte[] bytes, String fullPath) {
        if (bytes == null || bytes.length == 0) {
            throw new JeecgBootException("文件内容不能为空");
        }
        if (fullPath == null || fullPath.trim().isEmpty()) {
            throw new JeecgBootException("输出路径不能为空");
        }

        // 确保输出目录存在
        File file = new File(fullPath);
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            boolean b = parentDir.mkdirs();
        }

        // 生成随机文件名
        File outputFile = new File(fullPath);

        // 写入文件
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            fos.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Map<String, String> result = new HashMap<>();
        result.put("fullPath", outputFile.getAbsolutePath());
        return result;
    }

    // 根据文件类型获取对应后缀
    public static String getFileSuffix(String base64) {
        String[] split = base64.split(";");
        boolean hasData = split.length > 1;
        String condition = null;
        if (hasData) {
            condition = split[0].split(":")[1];
        } else {
            condition = split[0];
        }

        switch (condition) {
            case "image/jpeg":
                return ".jpg";
            case "image/png":
                return ".png";
            case "image/gif":
                return ".gif";
            default:
                return "";
        }
    }

    public static String extractPathFromPattern(final HttpServletRequest request) {
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        System.out.println(path);
        String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        System.out.println(bestMatchPattern);
        System.out.println(new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path));
        return new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);
    }

    public static void deleteFile(String fullPath) {
        if (fullPath == null || fullPath.trim().isEmpty()) {
            log.warn("文件路径为空，取消删除文件");
            return;
        }
        File file = new File(fullPath);
        if (file.exists()) {
            file.delete();
        }
    }

}
