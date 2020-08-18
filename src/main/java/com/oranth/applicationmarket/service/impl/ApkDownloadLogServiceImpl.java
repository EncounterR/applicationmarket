package com.oranth.applicationmarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oranth.applicationmarket.entity.ActiveApkDownloadLog;
import com.oranth.applicationmarket.entity.ApkCount;
import com.oranth.applicationmarket.entity.ApkDownloadLog;
import com.oranth.applicationmarket.mapper.ApkDownloadLogMapper;
import com.oranth.applicationmarket.service.IApkCountService;
import com.oranth.applicationmarket.service.IApkDownloadLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 下载记录表 服务实现类
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
@Transactional
@Service
public class ApkDownloadLogServiceImpl extends ServiceImpl<ApkDownloadLogMapper, ApkDownloadLog> implements IApkDownloadLogService {

    @Autowired
    IApkCountService apkCountService;
    @Autowired
    ApkDownloadLogMapper apkDownloadLogMapper;



    @Override
    public int downStat(ApkDownloadLog apkDownloadLog) {
        this.save(apkDownloadLog);
        apkCountService.updataBySn(apkDownloadLog.getApkId(),1,0);
        return 1;
    }

    @Override
    public List<ApkDownloadLog> getAll() {
        return apkDownloadLogMapper.getAll();
    }

    @Override
    public List<ActiveApkDownloadLog> getApkDownloadLogByDownloadTime() {
        List<ApkDownloadLog> apkDownloadLogList=apkDownloadLogMapper.getAll();
        List<ApkDownloadLog> apkDownloadLogList1=new ArrayList<>();
        List<ApkDownloadLog> apkDownloadLogList2=new ArrayList<>();
        List<ApkDownloadLog> apkDownloadLogList3=new ArrayList<>();
        List<ApkDownloadLog> apkDownloadLogList4=new ArrayList<>();
        List<ApkDownloadLog> apkDownloadLogList5=new ArrayList<>();
        List<ApkDownloadLog> apkDownloadLogList6=new ArrayList<>();
        List<ApkDownloadLog> apkDownloadLogList7=new ArrayList<>();
        List<ApkDownloadLog> apkDownloadLogList8=new ArrayList<>();
        List<ApkDownloadLog> apkDownloadLogList9=new ArrayList<>();
        List<ApkDownloadLog> apkDownloadLogList10=new ArrayList<>();
        List<ApkDownloadLog> apkDownloadLogList11=new ArrayList<>();
        List<ApkDownloadLog> apkDownloadLogList12=new ArrayList<>();
        for(ApkDownloadLog apkDownloadLog:apkDownloadLogList){
            switch (apkDownloadLog.getDownloadTime().getMonth().getValue()){
                case 1:
                    apkDownloadLogList1.add(apkDownloadLog);
                    break;
                case 2:
                    apkDownloadLogList2.add(apkDownloadLog);
                    break;
                case 3:
                    apkDownloadLogList3.add(apkDownloadLog);
                    break;
                case 4:
                    apkDownloadLogList4.add(apkDownloadLog);
                    break;
                case 5:
                    apkDownloadLogList5.add(apkDownloadLog);
                    break;
                case 6:
                    apkDownloadLogList6.add(apkDownloadLog);
                    break;
                case 7:
                    apkDownloadLogList7.add(apkDownloadLog);
                    break;
                case 8:
                    apkDownloadLogList8.add(apkDownloadLog);
                    break;
                case 9:
                    apkDownloadLogList9.add(apkDownloadLog);
                    break;
                case 10:
                    apkDownloadLogList10.add(apkDownloadLog);
                    break;
                case 11:
                    apkDownloadLogList11.add(apkDownloadLog);
                    break;
                case 12:
                    apkDownloadLogList12.add(apkDownloadLog);
                    break;
            }
        }
        List<ActiveApkDownloadLog> activeApkDownloadLogs=new ArrayList<>();
        activeApkDownloadLogs.add(new ActiveApkDownloadLog("1",apkDownloadLogList1));
        activeApkDownloadLogs.add(new ActiveApkDownloadLog("2",apkDownloadLogList2));
        activeApkDownloadLogs.add(new ActiveApkDownloadLog("3",apkDownloadLogList3));
        activeApkDownloadLogs.add(new ActiveApkDownloadLog("4",apkDownloadLogList4));
        activeApkDownloadLogs.add(new ActiveApkDownloadLog("5",apkDownloadLogList5));
        activeApkDownloadLogs.add(new ActiveApkDownloadLog("6",apkDownloadLogList6));
        activeApkDownloadLogs.add(new ActiveApkDownloadLog("7",apkDownloadLogList7));
        activeApkDownloadLogs.add(new ActiveApkDownloadLog("8",apkDownloadLogList8));
        activeApkDownloadLogs.add(new ActiveApkDownloadLog("9",apkDownloadLogList9));
        activeApkDownloadLogs.add(new ActiveApkDownloadLog("10",apkDownloadLogList10));
        activeApkDownloadLogs.add(new ActiveApkDownloadLog("11",apkDownloadLogList11));
        activeApkDownloadLogs.add(new ActiveApkDownloadLog("12",apkDownloadLogList12));
        return activeApkDownloadLogs;
    }

    @Override
    public Boolean addApkDownloadLog(ApkDownloadLog apkDownloadLog) {
        int row=apkDownloadLogMapper.addApkDownloadLog(apkDownloadLog);
        return (row>0)?true:false;
    }

    @Override
    public Boolean delApkDownloadLog(Integer id) {
        int row=apkDownloadLogMapper.delApkDownloadLog(id);
        return (row>0)?true:false;
    }

    @Override
    public Boolean editApkDownloadLog(ApkDownloadLog apkDownloadLog) {
        int row=apkDownloadLogMapper.editApkDownloadLog(apkDownloadLog);
        return (row>0)?true:false;
    }
}
