package com.yizhanshi.place.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yizhanshi.common.core.annotation.Excel;
import com.yizhanshi.place.api.domain.Place;
import com.yizhanshi.place.api.domain.PlaceApply;

import java.util.List;

/**
 * 返回给前端用于场地申请的场地列表
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AllPlace {

    private Place place;
     private List<PlaceApply> placeApplies;


    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public List<PlaceApply> getPlaceApplies() {
        return placeApplies;
    }

    public void setPlaceApplies(List<PlaceApply> placeApplies) {
        this.placeApplies = placeApplies;
    }

    public AllPlace(Place place, List<PlaceApply> placeApplies) {
        this.place = place;
        this.placeApplies = placeApplies;
    }
}
