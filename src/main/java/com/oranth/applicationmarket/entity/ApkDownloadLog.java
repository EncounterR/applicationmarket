package com.oranth.applicationmarket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 下载记录表
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ApkDownloadLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 下载id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer apkId;

    private String ip = " ";

    /**
     * 下载行政区编号
     */
    private String admincode = " ";

    /**
     * 芯片提供商名称
     */
    private String device;

    /**
     * 设备型号
     */
    private String model;

    /**
     * 下载时间
     */
    private LocalDateTime downloadTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApkId() {
        return apkId;
    }

    public void setApkId(Integer apkId) {
        this.apkId = apkId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAdmincode() {
        return admincode;
    }

    public void setAdmincode(String admincode) {
        this.admincode = admincode;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDateTime getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(LocalDateTime downloadTime) {
        this.downloadTime = downloadTime;
    }
}
