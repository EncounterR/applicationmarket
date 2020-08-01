package com.oranth.applicationmarket.utils;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
	
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();
    
    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }
 
    @Override
    protected Object determineCurrentLookupKey() {   	
        return getDataSource();
    }
 
    public static void setDataSource(String dataSource) { 
    	removeDataSource(dataSource);
    	CONTEXT_HOLDER.set(dataSource);
    }
 
    public static String getDataSource() {
    	System.out.println(CONTEXT_HOLDER.get());
        return CONTEXT_HOLDER.get();
    }
    public static void removeDataSource(String dataSource) {  	
    	CONTEXT_HOLDER.remove();
    }
}