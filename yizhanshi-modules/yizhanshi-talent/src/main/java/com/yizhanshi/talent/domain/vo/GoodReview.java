package com.yizhanshi.talent.domain.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GoodReview {
    /**
     * 接单数
     */
    private int applyNumbers;
    /**
     * 好评数（根据评价表获得好评数）
     *
     */
    private int goodReviews;
    /**
     * 好评率（根据评价表通过好评数/评论数获取好评率）
     * 36 97这样(不超过100)
     */
    private int goodPercent;

    public GoodReview(int applyNumbers, int goodReviews, int goodPercent) {
        this.applyNumbers = applyNumbers;
        this.goodReviews = goodReviews;
        this.goodPercent = goodPercent;
    }

    public int getApplyNumbers() {
        return applyNumbers;
    }

    public void setApplyNumbers(int applyNumbers) {
        this.applyNumbers = applyNumbers;
    }

    public int getGoodReviews() {
        return goodReviews;
    }

    public void setGoodReviews(int goodReviews) {
        this.goodReviews = goodReviews;
    }

    public int getGoodPercent() {
        return goodPercent;
    }

    public void setGoodPercent(int goodPercent) {
        this.goodPercent = goodPercent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("applyNumbers", applyNumbers)
                .append("goodReviews", goodReviews)
                .append("goodPercent", goodPercent)
                .toString();
    }
}
