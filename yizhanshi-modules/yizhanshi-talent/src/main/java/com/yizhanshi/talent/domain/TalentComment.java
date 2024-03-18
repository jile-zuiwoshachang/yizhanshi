package com.yizhanshi.talent.domain;

import com.yizhanshi.common.core.annotation.Excel;
import com.yizhanshi.common.core.web.domain.BaseEntity;
import com.yizhanshi.talent.domain.vo.Talent;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class TalentComment extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @Excel(name = "评价主键", cellType = Excel.ColumnType.NUMERIC)
    private Long commentId;
    @Excel(name = "申请单id")
    private Long applyId;
    @Excel(name = "评价星级",readConverterExp = "1=一星,2=二星,3=三星,4=四星,5=五星")
    private String commentStar;
    @Excel(name = "评价内容")
    private String commentContent;
    @Excel(name = "状态",readConverterExp = "0=正常,1=停用")
    private String status;
    private String delFlag;
    private TalentApply talentApplys;

    public TalentComment() {
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public TalentApply getTalentApplys() {
        return talentApplys;
    }

    public void setTalentApplys(TalentApply talentApplys) {
        this.talentApplys = talentApplys;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("commentId", commentId)
                .append("applyId", applyId)
                .append("commentStar", commentStar)
                .append("commentContent", commentContent)
                .append("status", status)
                .append("delFlag", delFlag)
                .append("talentApplys", talentApplys)
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
