package com.oranth.applicationmarket;

import com.oranth.applicationmarket.entity.*;
import com.oranth.applicationmarket.mapper.*;
import com.oranth.applicationmarket.service.IApkDownloadLogService;
import com.oranth.applicationmarket.service.IStateService;
import com.oranth.applicationmarket.utils.ParsingApkUtil;
import com.oranth.applicationmarket.utils.Util;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ApplicationmarketApplicationTests {
    private final static Logger logger = LoggerFactory.getLogger(ApplicationmarketApplicationTests.class);



    @Autowired
    ApkMapper apkMapper;

    @Autowired
    ApkTypeMapper apkTypeMapper;

    @Autowired
    ApkScreenshotMapper apkScreenshotMapper;

    @Autowired
    ResponseDataMapper responseDataMapper;
    @Autowired
    IStateService stateService;
    @Autowired
    WebUserMapper webUserMapper;
    @Autowired
    IApkDownloadLogService iApkDownloadLogService;

    @Test
    public void showSqlParam(){
//        Apk apk = apkMapper.selectById(1);
//        List<ApkType> apkType = apkTypeMapper.selectAll();
//        System.out.println(apkType);
 //       System.out.println(JSON.toJSONString(responseDataMapper.selectByTypeId(1)));
//        System.out.println(Util.getTypeId("video"));
        List<State> list = getIpState();
        List<State> list1 = new ArrayList<>();
//        for(int i = 0;i < (list.size() - 100); i=+100){
//            for(int b = i;b < i+100;b++){
//                list1.add(list.get(b));
//            }
//
//        }
        stateService.saveBatch(list);
    }

    @Test
    void getWebUser(){
        WebUser webUser=webUserMapper.getWebUserByUserNameAndUserPassword("admin","admin");
        System.out.println("---"+webUser);
    }

    @Test
    void getApkD(){
        List<ActiveApkDownloadLog> apkDownloadLogList=iApkDownloadLogService.getApkDownloadLogByDownloadTime();
        for(ActiveApkDownloadLog activeApkDownloadLog:apkDownloadLogList){
            System.out.println(activeApkDownloadLog);
        }

    }

    @Test
    void parsingApkUtilTest() {
            String demo = "C:\\Users\\Administrato\\Desktop\\appstore.apk";
        Apk apkInfo = null;
        try {
            apkInfo = new ParsingApkUtil().getApkInfo(demo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(apkInfo);
    }
    public List<State> getIpState(){
        File file = new File("C:\\Users\\Administrato\\Desktop\\ipv4_s.txt");
        try (BufferedReader in = new BufferedReader(new FileReader(file))){
            Map<String,String> mapState = getState();
            String str = null;
            str = in.readLine();
            List<State> list = new ArrayList<>();
            while (true){
                State ipORState = new State();
                String [] ss = str.split("\\|");
                ipORState.setIp(ss[2]);
                ipORState.setStateAbbr(ss[0]);
                ipORState.setState(mapState.get(ss[0]));
                list.add(ipORState);
                str = in.readLine();
                if(str == null || str.length() == 0){
                    break;
                }
            }
            System.out.println("=="+list.size());
            return list;
        }catch (IOException e){}
        return null;
    }
    public Map<String,String> getState() {
        File file = new File("C:\\Users\\Administrato\\Desktop\\a.txt");
        try (BufferedReader in = new BufferedReader(new FileReader(file))){

            String str = null;
            str = in.readLine();
            HashMap<String,String> map = new HashMap<>();
            boolean b = true;
            while (true){
                if(b){
                    String [] ss = str.split("\t");
                    map.put(ss[0],ss[1]);
                    b= false;
                }else{
                    b = true;
                }
                str = in.readLine();
                if(str == null || str.length() == 0){
                    break;
                }
            }
            return map;
        }catch (IOException e){}
        return null;
    }
}
