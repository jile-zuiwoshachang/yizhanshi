package com.yizhanshi.place.domain;


import javax.validation.constraints.NotNull;

/**
 * 时间信息表
 *
 * @author hejiale
 */
public class Time {
    private static final long serialVersionUID = 1L;
    /** 时间序号（保证从小到大） */
    private Long timeId;
    /** 时间名称（例如07:00）**/
    private String timeName;
    /** 时间类别（例如方案一 方案二）**/
    private String timeType;
    /** 状态（0启用  1弃用）**/
    private String  status;

    public Long getTimeId() {
        return timeId;
    }

    public void setTimeId(Long timeId) {
        this.timeId = timeId;
    }
    @NotNull(message = "时间名称不能为空")
    public String getTimeName() {
        return timeName;
    }

    public void setTimeName(String timeName) {
        this.timeName = timeName;
    }
    @NotNull(message = "时间类型不能为空")
    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
