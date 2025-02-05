package com.heyudev.base;

import org.apache.commons.math3.distribution.BetaDistribution;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ThompsonSampling {
    public static ContentDTO choose(List<ContentDTO> contentDTOS) {
        if (Objects.isNull(contentDTOS) || contentDTOS.isEmpty()) {
            return null;
        }
        double max = 0;
        ContentDTO result = null;
        for (ContentDTO contentDTO : contentDTOS) {
            if (Objects.isNull(contentDTO) || Objects.isNull(contentDTO.getReturnNum())
                    || Objects.isNull(contentDTO.getViewNum())) {
                continue;
            }
            double alpha = contentDTO.getReturnNum() + 1;
            double beta = contentDTO.getViewNum() + 1;
            double probability = betaSampler(alpha, beta);
            System.out.println("videoId = " + contentDTO.getVideoId() + ",id = " + contentDTO.getId() + ",alpha = " + alpha + ",beat = " + beta + ",probability = " + probability);
            if (probability > max) {
                result = contentDTO;
                max = probability;
            }
        }
        return result;
    }

    public static double betaSampler(double alpha, double beta) {
        BetaDistribution betaSample = new BetaDistribution(alpha, beta);
        return betaSample.sample();
    }

    public static void main(String[] args) {
        List<ContentDTO> list = new ArrayList<>();
        ContentDTO contentDTO1 = new ContentDTO(0L,1L,"",1,1L,2L);
        ContentDTO contentDTO2 = new ContentDTO(1L,1L,"",1,1L,2L);
        ContentDTO contentDTO3 = new ContentDTO(2L,1L,"",1,1L,2L);
        ContentDTO contentDTO4 = new ContentDTO(3L,1L,"",1,1L,2L);
        list.add(contentDTO1);
        list.add(contentDTO2);
        list.add(contentDTO3);
        list.add(contentDTO4);
        for (int i = 0; i < 100; i++) {
            ContentDTO contentDTO = choose(list);
            System.out.println(i+"---"+"videoId = " + contentDTO.getVideoId() + ",id = " + contentDTO.getId() + ",viewNum = " + contentDTO.getViewNum() + ",returnNum = " + contentDTO.getReturnNum() + ",probability = " + contentDTO.getProbability());
        }
    }
}

class ContentDTO {
    public ContentDTO(Long id, Long videoId, String content, Integer type, Long viewNum, Long returnNum) {
        this.id = id;
        this.videoId = videoId;
        this.content = content;
        this.type = type;
        this.viewNum = viewNum;
        this.returnNum = returnNum;
    }

    /**
     * ID
     * 标题ID or 封面ID
     */
    private Long id;
    /**
     * 视频ID
     */
    private Long videoId;
    /**
     * 标题 or 封面
     */
    private String content;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 曝光数
     */
    private Long viewNum;
    /**
     * 回流数
     */
    private Long returnNum;
    /**
     * 概率
     * Thompson
     */
    private Double probability;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getViewNum() {
        return viewNum;
    }

    public void setViewNum(Long viewNum) {
        this.viewNum = viewNum;
    }

    public Long getReturnNum() {
        return returnNum;
    }

    public void setReturnNum(Long returnNum) {
        this.returnNum = returnNum;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    @Override
    public String toString() {
        return "ContentDTO{" +
                "id=" + id +
                ", videoId=" + videoId +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", viewNum=" + viewNum +
                ", returnNum=" + returnNum +
                ", probability=" + probability +
                '}';
    }
}
