package com.yizhanshi.place.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yizhanshi.common.core.annotation.Excel;
import com.yizhanshi.place.api.domain.Place;
import com.yizhanshi.place.api.domain.PlaceApply;
import com.yizhanshi.place.api.domain.PlaceApplyTime;

import java.util.List;

/**
 * 返回给前端用于场地预约的场地列表
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AllPlace {

    private Place place;
     private List<PlaceApplyTime> placeApplyTimes;


    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public List<PlaceApplyTime> getPlaceApplyTimes() {
        return placeApplyTimes;
    }

    public void setPlaceApplyTimes(List<PlaceApplyTime> placeApplyTimes) {
        this.placeApplyTimes = placeApplyTimes;
    }

    public AllPlace(Place place, List<PlaceApplyTime> placeApplyTimes) {
        this.place = place;
        this.placeApplyTimes = placeApplyTimes;
    }
}
