package com.oranth.applicationmarket.mapper.sqlprovider;

import com.baomidou.mybatisplus.core.toolkit.sql.SqlUtils;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ResponseDataProvider {

    public String selectByTypeId(Map<String,Object> map){
        Object typeId = map.get("typeId");
        Object begin = map.get("begin");
        Object offset = map.get("offset");

        String sql = "SELECT a.is_forbidden as isForbidden,at.type_name as type,a.id as id,a.label,a.pkg_name,a.cls_name,a.file_size,a.des,a.version_code,a.version_name,a.icon,a.file_url,a.news,ac.download_num\n" +
                " FROM apk_type at LEFT JOIN apk a ON at.id = a.type_id LEFT JOIN apk_count ac ON a.id = ac.apk_id ";
        if(typeId != null && Integer.valueOf(typeId.toString()) != 0){
            sql += " where a.type_id = "+ typeId + " and a.is_forbidden = 0";
        }else{
            sql += "where a.is_forbidden = 0";
        }
         sql += "  limit "+begin+","+offset;
        System.out.println(">>>>>>>>>>>>>>>>>>>>>"+sql);
        return sql;
    }
    public String selectByTypeIdAd(Map<String,Object> map){
        Object typeId = map.get("typeId");
        Object begin = map.get("begin");
        Object offset = map.get("offset");

        String sql = "SELECT a.is_forbidden as isForbidden,at.type_name as type,a.id as id,a.label,a.pkg_name,a.cls_name,a.file_size,a.des,a.version_code,a.version_name,a.icon,a.file_url,a.news,ac.download_num\n" +
                " FROM apk_type at inner join apk a ON at.id = a.type_id LEFT JOIN apk_count ac ON a.id = ac.apk_id ";
        if(typeId != null && Integer.valueOf(typeId.toString()) != 0){
            sql += " where a.type_id = "+ typeId;
        }
        sql += "  limit "+begin+","+offset;
        System.out.println(">>>>>>>>>>>>>>>>>>>>>"+sql);
        return sql;
    }



    public String selectByTypeIdCount(Map<String,Object> map){
        Object typeId = map.get("typeId");

        String sql = "SELECT count(a.id) as num FROM apk_type at LEFT JOIN apk a ON at.id = a.type_id";
        if(typeId != null && Integer.valueOf(typeId.toString()) != 0){
            sql += " where a.type_id = "+ typeId + " and a.is_forbidden = 0";
        }else{
            sql += "where a.is_forbidden = 0";
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>"+sql);
        return sql;
    }
    public String selectByTypeIdCountAdmin(Map<String,Object> map){
        Object typeId = map.get("typeId");

        String sql = "SELECT count(a.id) as num FROM apk_type at inner join apk a ON at.id = a.type_id";
        if(typeId != null && Integer.valueOf(typeId.toString()) != 0){
            sql += " where a.type_id = "+ typeId ;
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>"+sql);
        return sql;
    }
}
