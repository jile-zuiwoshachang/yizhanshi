package com.yizhanshi.talent.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yizhanshi.common.core.annotation.Excel;
import com.yizhanshi.talent.domain.TalentApply;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class TalentCommentExport {
    private static final long serialVersionUID = 1L;
    @Excel(name = "评价主键", cellType = Excel.ColumnType.NUMERIC)
    private Long commentId;
    @Excel(name = "人才学号")
    private String talentStudentid;
    @Excel(name = "人才姓名")
    private String talentName;
    @Excel(name = "评价星级",readConverterExp = "1=一星,2=二星,3=三星,4=四星,5=五星")
    private String commentStar;
    @Excel(name = "评价内容")
    private String commentContent;
    @Excel(name = "状态",readConverterExp = "0=正常,1=停用")
    private String status;
    @Excel(name = "创建时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Excel(name = "创建人")
    private String createBy;

    public TalentCommentExport() {
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getTalentStudentid() {
        return talentStudentid;
    }

    public void setTalentStudentid(String talentStudentid) {
        this.talentStudentid = talentStudentid;
    }

    public String getTalentName() {
        return talentName;
    }

    public void setTalentName(String talentName) {
        this.talentName = talentName;
    }

    public String getCommentStar() {
        return commentStar;
    }

    public void setCommentStar(String commentStar) {
        this.commentStar = commentStar;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("commentId", commentId)
                .append("talentStudentid", talentStudentid)
                .append("talentName", talentName)
                .append("commentStar", commentStar)
                .append("commentContent", commentContent)
                .append("status", status)
                .append("createTime", createTime)
                .append("createBy", createBy)
                .toString();
    }
}
