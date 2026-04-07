package org.jeecg.modules.slfh.common.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.dto.DataLogDTO;
import org.jeecg.common.api.dto.OnlineAuthDTO;
import org.jeecg.common.api.dto.message.*;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.*;
import org.jeecg.modules.slfh.common.utils.BusUtils;
import org.jeecg.modules.system.entity.SysRole;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.entity.SysUserRole;
import org.jeecg.modules.system.mapper.SysDepartMapper;
import org.jeecg.modules.system.mapper.SysRoleMapper;
import org.jeecg.modules.system.mapper.SysUserMapper;
import org.jeecg.modules.system.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Cikian
 * @version 1.0
 * @implNote
 * @see <a href="https://www.cikian.cn">https://www.cikian.cn</a>
 * @since 2025-10-10 17:29
 */
@Slf4j
@Service
public class BusCommonService {

    @Autowired
    private ISysBaseAPI sysBaseAPI;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysDepartMapper departMapper;
    @Value("${jeecg.path.upload}")
    private String baseFilePath;
    @Value(value = "${server.servlet.context-path}")
    private String contextPath;
    @Value(value = "${server.port}")
    private String port;

    public void sendSysAnnouncement(MessageDTO message) {
        sysBaseAPI.sendSysAnnouncement(message);
    }

    public void sendBusAnnouncement(BusMessageDTO message) {
        sysBaseAPI.sendBusAnnouncement(message);
    }

    public void sendTemplateAnnouncement(TemplateMessageDTO message) {
        sysBaseAPI.sendTemplateAnnouncement(message);
    }

    public void sendBusTemplateAnnouncement(BusTemplateMessageDTO message) {
        sysBaseAPI.sendBusTemplateAnnouncement(message);
    }

    public String parseTemplateByCode(TemplateDTO templateDTO) {
        return sysBaseAPI.parseTemplateByCode(templateDTO);
    }


    public void sendTemplateMessage(MessageDTO message) {
        sysBaseAPI.sendTemplateMessage(message);
    }


    public String getTemplateContent(String templateCode) {
        return sysBaseAPI.getTemplateContent(templateCode);
    }


    public LoginUser getUserById(String id) {
        return sysBaseAPI.getUserById(id);
    }


    public List<String> getRolesByUsername(String username) {
        return sysBaseAPI.getRolesByUsername(username);
    }


    public List<String> getDepartIdsByUsername(String username) {
        return sysBaseAPI.getDepartIdsByUsername(username);
    }


    public List<String> getDepartNamesByUsername(String username) {
        return sysBaseAPI.getDepartNamesByUsername(username);
    }


    public List<DictModel> queryAllDict() {
        return sysBaseAPI.queryAllDict();
    }


    public List<SysCategoryModel> queryAllSysCategory() {
        return sysBaseAPI.queryAllSysCategory();
    }


    public List<DictModel> queryAllDepartBackDictModel() {
        return sysBaseAPI.queryAllDepartBackDictModel();
    }


    public void updateSysAnnounReadFlag(String busType, String busId) {
        sysBaseAPI.updateSysAnnounReadFlag(busType, busId);
    }


    public List<DictModel> queryFilterTableDictInfo(String table, String text, String code, String filterSql) {
        return sysBaseAPI.queryFilterTableDictInfo(table, text, code, filterSql);
    }


    public List<String> queryTableDictByKeys(String table, String text, String code, String[] keyArray) {
        return sysBaseAPI.queryTableDictByKeys(table, text, code, keyArray);
    }


    public List<ComboModel> queryAllUserBackCombo() {
        return sysBaseAPI.queryAllUserBackCombo();
    }


    public JSONObject queryAllUser(String userIds, Integer pageNo, Integer pageSize) {
        return sysBaseAPI.queryAllUser(userIds, pageNo, pageSize);
    }

    public Result<List<SysUser>> getUserByRole(String roleCode) {
        SysRole sysRole = roleMapper.selectByRoleCode(roleCode);
        if (sysRole == null) {
            return null;
        }
        LambdaQueryWrapper<SysUserRole> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SysUserRole::getRoleId, sysRole.getId());
        List<SysUserRole> userRoles = sysUserRoleMapper.selectList(lqw);
        List<String> userIds = new ArrayList<>();
        for (SysUserRole userRole : userRoles) {
            userIds.add(userRole.getUserId());
        }

        return Result.ok(userMapper.selectBatchIds(userIds));
    }


    public List<ComboModel> queryAllRole() {
        return sysBaseAPI.queryAllRole();
    }


    public List<ComboModel> queryAllRole(String[] roleIds) {
        return sysBaseAPI.queryAllRole(roleIds);
    }


    public List<String> getRoleIdsByUsername(String username) {
        return sysBaseAPI.getRoleIdsByUsername(username);
    }


    public String getDepartIdsByOrgCode(String orgCode) {
        return sysBaseAPI.getDepartIdsByOrgCode(orgCode);
    }


    public List<SysDepartModel> getAllSysDepart() {
        return sysBaseAPI.getAllSysDepart();
    }


    public DictModel getParentDepartId(String departId) {
        return sysBaseAPI.getParentDepartId(departId);
    }


    public List<String> getDeptHeadByDepId(String deptId) {
        return sysBaseAPI.getDeptHeadByDepId(deptId);
    }


    public void sendWebSocketMsg(String[] userIds, String cmd) {
        sysBaseAPI.sendWebSocketMsg(userIds, cmd);
    }


    public List<LoginUser> queryAllUserByIds(String[] userIds) {
        return sysBaseAPI.queryAllUserByIds(userIds);
    }


    public void meetingSignWebsocket(String userId) {
        sysBaseAPI.meetingSignWebsocket(userId);
    }


    public List<LoginUser> queryUserByNames(String[] userNames) {
        return sysBaseAPI.queryUserByNames(userNames);
    }


    public Set<String> getUserRoleSet(String username) {
        return sysBaseAPI.getUserRoleSet(username);
    }


    public Set<String> getUserPermissionSet(String username) {
        return sysBaseAPI.getUserPermissionSet(username);
    }


    public boolean hasOnlineAuth(OnlineAuthDTO onlineAuthDTO) {
        return sysBaseAPI.hasOnlineAuth(onlineAuthDTO);
    }


    public SysDepartModel selectAllById(String id) {
        return sysBaseAPI.selectAllById(id);
    }


    public List<String> queryDeptUsersByUserId(String userId) {
        return sysBaseAPI.queryDeptUsersByUserId(userId);
    }


    public List<JSONObject> queryUsersByUsernames(String usernames) {
        return sysBaseAPI.queryUsersByUsernames(usernames);
    }


    public List<JSONObject> queryUsersByIds(String ids) {
        return sysBaseAPI.queryUsersByIds(ids);
    }


    public List<JSONObject> queryDepartsByOrgcodes(String orgCodes) {
        return sysBaseAPI.queryDepartsByOrgcodes(orgCodes);
    }


    public List<JSONObject> queryDepartsByIds(String ids) {
        return sysBaseAPI.queryDepartsByIds(ids);
    }


    public void sendEmailMsg(String email, String title, String content) {
        sysBaseAPI.sendEmailMsg(email, title, content);
    }


    public List<Map> getDeptUserByOrgCode(String orgCode) {
        return sysBaseAPI.getDeptUserByOrgCode(orgCode);
    }


    public List<String> loadCategoryDictItem(String ids) {
        return sysBaseAPI.loadCategoryDictItem(ids);
    }


    public List<String> loadDictItem(String dictCode, String keys) {
        return sysBaseAPI.loadDictItem(dictCode, keys);
    }


    public List<DictModel> getDictItems(String dictCode) {
        return sysBaseAPI.getDictItems(dictCode);
    }

    public Map<String, String> getDictMap(String dictCode) {
        List<DictModel> dictItems = this.getDictItems(dictCode);
        return dictItems.stream().collect(Collectors.toMap(DictModel::getValue, DictModel::getText));
    }


    public Map<String, List<DictModel>> getManyDictItems(List<String> dictCodeList) {
        return sysBaseAPI.getManyDictItems(dictCodeList);
    }


    public List<DictModel> loadDictItemByKeyword(String dictCode, String keyword, Integer pageSize) {
        return sysBaseAPI.loadDictItemByKeyword(dictCode, keyword, pageSize);
    }


    public void saveDataLog(DataLogDTO dataLogDto) {
        sysBaseAPI.saveDataLog(dataLogDto);
    }


    public void addSysFiles(SysFilesModel sysFilesModel) {
        sysBaseAPI.addSysFiles(sysFilesModel);
    }


    public String getFileUrl(String fileId) {
        return sysBaseAPI.getFileUrl(fileId);
    }


    public void updateAvatar(LoginUser loginUser) {
        sysBaseAPI.updateAvatar(loginUser);
    }


    public void sendAppChatSocket(String userId) {
        sysBaseAPI.sendAppChatSocket(userId);
    }


    public Set<String> queryUserRoles(String username) {
        return sysBaseAPI.queryUserRoles(username);
    }


    public Set<String> queryUserAuths(String username) {
        return sysBaseAPI.queryUserAuths(username);
    }


    public DynamicDataSourceModel getDynamicDbSourceById(String dbSourceId) {
        return sysBaseAPI.getDynamicDbSourceById(dbSourceId);
    }


    public DynamicDataSourceModel getDynamicDbSourceByCode(String dbSourceCode) {
        return sysBaseAPI.getDynamicDbSourceByCode(dbSourceCode);
    }


    public LoginUser getUserByName(String username) {
        return sysBaseAPI.getUserByName(username);
    }


    public String translateDictFromTable(String table, String text, String code, String key) {
        return sysBaseAPI.translateDictFromTable(table, text, code, key);
    }


    public String translateDict(String code, String key) {
        return sysBaseAPI.translateDict(code, key);
    }


    public List<SysPermissionDataRuleModel> queryPermissionDataRule(String component, String requestPath, String username) {
        return sysBaseAPI.queryPermissionDataRule(component, requestPath, username);
    }


    public SysUserCacheInfo getCacheUser(String username) {
        return sysBaseAPI.getCacheUser(username);
    }


    public List<DictModel> queryDictItemsByCode(String code) {
        return sysBaseAPI.queryDictItemsByCode(code);
    }


    public List<DictModel> queryEnableDictItemsByCode(String code) {
        return sysBaseAPI.queryEnableDictItemsByCode(code);
    }


    public List<DictModel> queryTableDictItemsByCode(String table, String text, String code) {
        return sysBaseAPI.queryTableDictItemsByCode(table, text, code);
    }


    public Map<String, List<DictModel>> translateManyDict(String dictCodes, String keys) {
        return sysBaseAPI.translateManyDict(dictCodes, keys);
    }


    public List<DictModel> translateDictFromTableByKeys(String table, String text, String code, String keys) {
        return sysBaseAPI.translateDictFromTableByKeys(table, text, code, keys);
    }

    public <T> JSONArray pojo2JSONArray(List<T> list) {
        return pojo2JSONArray(list, null);
    }

    /**
     * replaceFields: 替换JSONObject中的键名称，实际是增加字段，map的key为源字段，value为新增字段
     *
     * @param list
     * @param replaceFields
     * @param <T>
     * @return
     */
    public <T> JSONArray pojo2JSONArray(List<T> list, Map<String, String> replaceFields) {
        return this.pojo2JSONArray(list, replaceFields, false, null);
    }

    /**
     * replaceFields: 替换JSONObject中的键名称，实际是增加字段，map的key为源字段，value为新增字段
     * correlation: 关联父子节点
     *
     * @param list
     * @param replaceFields
     * @param <T>
     * @return
     */
    public <T> JSONArray pojo2JSONArray(List<T> list, Map<String, String> replaceFields, boolean correlation, String parentId) {
        if (list == null) {
            return new JSONArray();
        }

        boolean r = replaceFields != null && !replaceFields.isEmpty();


        JSONArray arr = new JSONArray();
        for (T i : list) {
            String str = JSON.toJSONString(i);
            JSONObject o = JSONObject.parseObject(str);
            if (correlation && parentId != null && !parentId.isEmpty()) {
                o.put("id", parentId + "-" + o.getString("id"));
            }
            if (r) {
                BusUtils.transformKey(o, replaceFields);
            }
            arr.add(o);
        }
        return arr;
    }

    /**
     * 将图片转换为Base64
     *
     * @param imagePath 图片路径
     * @return Base64字符串
     * @throws IOException IO异常
     */
    private String imageToBase64(String imagePath) throws IOException {
        BufferedImage image = ImageIO.read(new File(imagePath));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.getEncoder().encodeToString(imageBytes);
    }

}
