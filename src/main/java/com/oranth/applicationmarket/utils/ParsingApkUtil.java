package com.oranth.applicationmarket.utils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.juli.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oranth.applicationmarket.entity.Apk;

public class ParsingApkUtil {
	public static final String VERSION_CODE = "versionCode";
    public static final String VERSION_NAME = "versionName";
    public static final String SDK_VERSION = "sdkVersion";
    public static final String TARGET_SDK_VERSION = "targetSdkVersion";
    public static final String USES_PERMISSION = "uses-permission";
    public static final String APPLICATION_LABEL = "application-label";
    public static final String APPLICATION_ICON = "application-icon";
    public static final String USES_FEATURE = "uses-feature";
    public static final String USES_IMPLIED_FEATURE = "uses-implied-feature";
    public static final String SUPPORTS_SCREENS = "supports-screens";
    public static final String SUPPORTS_ANY_DENSITY = "supports-any-density";
    public static final String DENSITIES = "densities";
    public static final String PACKAGE = "package";
    public static final String APPLICATION = "application:";
    public static final String LAUNCHABLE_ACTIVITY = "launchable-activity";
    private String mAaptPath ="D:\\javaDev\\tomcat\\apache-tomcat-8.5.45\\webapps\\ApplicationMarket\\aapt.exe";
    //private String mAaptPath = "E:\\p2p\\oranth_application_market\\tool_windows\\aapt.exe";// windows 下路径
    private static final String SPLIT_REGEX = "(: )|(=')|(' )|'";
    private static final String FEATURE_SPLIT_REGEX = "(:')|(',')|'";
    
    static Logger logger = LogManager.getLogger(ParsingApkUtil.class.getName());
    
    private ProcessBuilder mBuilder;
    public ParsingApkUtil() {
        mBuilder = new ProcessBuilder();
        mBuilder.redirectErrorStream(true);
    }
    public Apk getApkInfo(String apkPath) throws Exception {
    	logger.debug("---------开始解析apk------------");

        //通过命令调用aapt工具解析apk文件
        Process process = mBuilder.command(mAaptPath, "d", "badging", apkPath)
                .start();        
        InputStream is = null;
        is = process.getInputStream();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(is, "utf8"));
        String tmp = br.readLine();
        try {
            if (tmp == null || !tmp.startsWith("package")) {
                logger.debug("参数不正确，无法正常解析APK包。输出结果为:\n" + tmp + "...");
                throw new Exception("参数不正确，无法正常解析APK包。输出结果为:\n" + tmp + "...");
            }
            Apk apkInfo = new Apk();
            do {
                setApkInfoProperty(apkInfo, tmp);
            } while ((tmp = br.readLine()) != null);
            logger.debug("解析完成！！！");
            return apkInfo;
        } catch (Exception e) {
            throw e;
        } finally {
            process.destroy();
            closeIO(is);
            closeIO(br);
        }
 //       return null;
    }
    private void setApkInfoProperty(Apk apkInfo, String source) {
        if (source.startsWith(PACKAGE)) {
            splitPackageInfo(apkInfo, source);
        } else if (source.startsWith(LAUNCHABLE_ACTIVITY)) {
  //          apkInfo.setLaunchableActivity(getPropertyInQuote(source));
        } else if (source.startsWith(SDK_VERSION)) {
            apkInfo.setMinSdkVersion(getPropertyInQuote(source));
        } else if (source.startsWith(TARGET_SDK_VERSION)) {
            apkInfo.setTargetSdkVersion(getPropertyInQuote(source));
        } else if (source.startsWith(USES_PERMISSION)) {
   //         apkInfo.addToUsesPermissions(getPropertyInQuote(source));
        } else if (source.startsWith(APPLICATION_LABEL)) {
            //windows下获取应用名称
            apkInfo.setLabel(getPropertyInQuote(source));
        } else if (source.startsWith(APPLICATION_ICON)) {
//            apkInfo.addToApplicationIcons(getKeyBeforeColon(source),
 //                   getPropertyInQuote(source));
        } else if (source.startsWith(APPLICATION)) {
            String[] rs = source.split("( icon=')|'");
//            apkInfo.setApplicationIcon(rs[rs.length - 1]);
            //linux下获取应用名称
//            apkInfo.setApplicationLable(rs[1]);
        } else if (source.startsWith(USES_FEATURE)) {
  //          apkInfo.addToFeatures(getPropertyInQuote(source));
        } else if (source.startsWith(USES_IMPLIED_FEATURE)) {
  //          apkInfo.addToImpliedFeatures(getFeature(source));
        } else {
//       System.out.println(source);
        }
    }
//    private ImpliedFeature getFeature(String source) {
//        String[] result = source.split(FEATURE_SPLIT_REGEX);
//        ImpliedFeature impliedFeature = new ImpliedFeature(result[1], result[2]);
//        return impliedFeature;
//    }

    /**
     * 返回出格式为name: 'value'中的value内容。
     *
     * @param source
     * @return
     */
    private String getPropertyInQuote(String source) {
        int index = source.indexOf("'") + 1;
        return source.substring(index, source.indexOf('\'', index));
    }

    /**
     * 返回冒号前的属性名称
     *
     * @param source
     * @return
     */
    private String getKeyBeforeColon(String source) {
        return source.substring(0, source.indexOf(':'));
    }

    /**
     * 分离出包名、版本等信息。
     *
     * @param apkInfo
     * @param packageSource
     */
    private void splitPackageInfo(Apk apkInfo, String packageSource) {
        String[] packageInfo = packageSource.split(SPLIT_REGEX);
        apkInfo.setPkgName(packageInfo[2]);
        apkInfo.setVersionCode(Integer.parseInt(packageInfo[4]));
        apkInfo.setVersionName(packageInfo[6]);
    }

    /**
     * 释放资源。
     *
     * @param c 将关闭的资源
     */
    private final void closeIO(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            String demo = "D:\\Java\\idea_app\\ReadApkAndIpa1\\src\\main\\java\\150211100441.apk";
            Apk apkInfo = new ParsingApkUtil().getApkInfo(demo);
            System.out.println(apkInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getmAaptPath() {
        return mAaptPath;
    }

    public void setmAaptPath(String mAaptPath) {
        this.mAaptPath = mAaptPath;
    }
}
