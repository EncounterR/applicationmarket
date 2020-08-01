package com.oranth.applicationmarket.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oranth.applicationmarket.cache.MybatisRedisCache;
import com.oranth.applicationmarket.entity.Apk;
import com.oranth.applicationmarket.entity.ApkCount;
import com.oranth.applicationmarket.entity.ApkScreenshot;
import com.oranth.applicationmarket.entity.custom.ResultData;
import com.oranth.applicationmarket.mapper.ApkMapper;
import com.oranth.applicationmarket.mapper.ApkVersionOldMapper;
import com.oranth.applicationmarket.mapper.ResponseDataMapper;
import com.oranth.applicationmarket.service.IApkCountService;
import com.oranth.applicationmarket.service.IApkDownloadLogService;
import com.oranth.applicationmarket.service.IApkScreenshotService;
import com.oranth.applicationmarket.service.IApkService;
import com.oranth.applicationmarket.utils.AutoEntityJSONParam;
import com.oranth.applicationmarket.utils.DateUtil;
import com.oranth.applicationmarket.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * <p>
 * apk表 服务实现类
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
@Service
public class ApkServiceImpl implements IApkService {

    private static Logger logger = LoggerFactory.getLogger(ApkServiceImpl.class);

    @Autowired
    ResponseDataMapper rdm;

    @Autowired
    ApkMapper apkMapper;

    @Autowired
    IApkScreenshotService ass;

    @Autowired
    IApkCountService acs;

    @Autowired
    ApkVersionOldMapper avom;

    @Autowired
    IApkDownloadLogService apkDownloadLogService;

    public List<Apk> getAll() {
        return apkMapper.getAll();
    }

    @Override
    public List<Apk> getApkByTypeId(Integer typeId) {
        return apkMapper.getApkByTypeId(typeId);
    }

    public Apk queryApkById(Integer id) {
        return apkMapper.getApkById(id);
    }

    @Override
    public Boolean editApk(Apk apk) {
        int row = apkMapper.editApk(apk);
        return (row == 1) ? true : false;
    }

    @Override
    public @ResponseBody String queryByType(String type, int page, int offset) {
        int begin = (page - 1) * offset;
        logger.debug(">>>>>>>>>>>>>>>" + type);
        int typeId = 0;
        if (!Util.isEmpty(type)) {
            typeId = Util.getTypeId(type);
        }
        List<ResultData> resultDate = rdm.selectByTypeId(typeId, begin, offset);
        return JSON.toJSONString(resultDate);
    }

    @Override
    public List<ResultData> queryRecommend() {

        List<ResultData> resultDate = rdm.rSelectAll();
        for (int i = 0; i < resultDate.size(); i++) {
            resultDate.get(i).setType(Util.getType(Integer.parseInt(resultDate.get(i).getType())));
        }
        return resultDate;
    }

    @Override
    public boolean saveApk(String apkInfo, String screenshot) {
        try {
            Apk apk = (Apk) AutoEntityJSONParam.setParam(Apk.class, apkInfo);
            Date date = new Date();
            apk.setCreateTime(DateUtil.asLocalDateTime(date));
            apk.setUpdateTime(DateUtil.asLocalDateTime(date));
            apk.setEditTime(DateUtil.asLocalDateTime(date));
            int a = apkMapper.addApk(apk);
            //添加apk截图
            JSONArray jsonArray = JSON.parseArray(screenshot);
            List<ApkScreenshot> apkScreenshotList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                ApkScreenshot apkScreenshot = (ApkScreenshot) AutoEntityJSONParam.setParam(ApkScreenshot.class, JSON.toJSONString(jsonArray.get(i)));
                apkScreenshot.setApkId(apk.getId());
                apkScreenshotList.add(apkScreenshot);
            }
            boolean b = ass.saveBatch(apkScreenshotList);
            //添加apk点击，下载，评分的表
            ApkCount apkCount = new ApkCount();
            apkCount.setApkId(apk.getId());
            apkCount.setClickCount((int) Math.random() * 100);
            apkCount.setDownloadNum((int) Math.random() * 100);
            boolean cb = acs.save(apkCount);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateApk(String apkInfo, String screenshot) {
        try {
            Apk apk = (Apk) AutoEntityJSONParam.setParam(Apk.class, apkInfo);
            Date date = new Date();
            apk.setUpdateTime(DateUtil.asLocalDateTime(date));
            apk.setEditTime(DateUtil.asLocalDateTime(date));
            int row = apkMapper.editApk(apk);
            JSONArray jsonArray = JSON.parseArray(screenshot);
            List<ApkScreenshot> apkScreenshotList=ass.getApkScreenshotByApkid(apk.getId());
            boolean flat=false;
            if (apkScreenshotList.size()==0){
                flat=true;
            }else{
                flat=ass.delApkScreenshot(apk.getId());
            }
            int flat2=0;
            if(flat){
                for (int i = 0; i < jsonArray.size(); i++) {
                    ApkScreenshot apkScreenshot = (ApkScreenshot) AutoEntityJSONParam.setParam(ApkScreenshot.class, JSON.toJSONString(jsonArray.get(i)));
                    apkScreenshot.setApkId(apk.getId());
                    apkScreenshot.setSequence(i+1);
                    boolean flat3=ass.addApkScreenshot(apkScreenshot);
                    if (flat3){
                        flat2++;
                    }
                }
            }
            return (flat2==jsonArray.size())?true:false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String queryById(Integer id) {
        ResultData resultData = rdm.selectById(id);
        if (resultData != null) {
            resultData.setType(Util.getType(Integer.parseInt(resultData.getType())));
            return JSON.toJSONString(resultData);
        }
        return "";
    }

    @Override
    public int queryByTypeCount(String type, int offset) {
        int typeId = 0;
        if (!Util.isEmpty(type)) {
            typeId = Util.getTypeId(type);
        }
        int num = rdm.selectByTypeIdCount(typeId);
        logger.debug(">>>>>>>>>>>>>" + num);
        if (num % offset > 0) {
            num = num / offset + 1;
        } else {
            num = num / offset;
        }
        return num;
    }

    @Transactional
    @Override
    public Integer del(Integer id) {
        Map<String, Object> map = new HashMap<>();
        map.put("apk_id", id);
        boolean b = ass.removeByMap(map);
        boolean cb = acs.removeByMap(map);
        apkDownloadLogService.removeByMap(map);
        int a = apkMapper.delApk(id);
/*        if(a > 0)
            new MybatisRedisCache("").clear();*/
        return a;
    }

    @Override
    public String queryByTypeAd(String type, int page, int offset) {
        int begin = (page - 1) * offset;
        logger.debug(">>>>>>>>>>>>>>>" + type);
        int typeId = 0;
        if (!Util.isEmpty(type)) {
            typeId = Util.getTypeId(type);
        }
        List<ResultData> resultDate = rdm.selectByTypeIdAd(typeId, begin, offset);
        return JSON.toJSONString(resultDate);
    }

    @Override
    public Integer queryByTypeCountAd(String type, int offset) {
        int typeId = 0;
        if (!Util.isEmpty(type)) {
            typeId = Util.getTypeId(type);
        }
        int num = rdm.selectByTypeIdCountAd(typeId);
        logger.debug(">>>>>>>>>>>>>" + num);
        if (num % offset > 0) {
            num = num / offset + 1;
        } else {
            num = num / offset;
        }
        return num;
    }
}
