package com.oranth.applicationmarket.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Lazy
@Component
@Data
@PropertySource("classpath:recommend.properties")
public class RecommendParam {
    @Value("${recommend}")
    private Integer recommend;
    @Value("${video}")
    private Integer video;

    @Value("${game}")
    private Integer game;
    @Value("${other}")
    private Integer other;
    @Value("${tools}")
    private Integer tools;
}
