package com.yizhanshi.place.controller;

import com.yizhanshi.common.core.utils.DateUtils;
import com.yizhanshi.common.core.web.controller.BaseController;
import com.yizhanshi.common.core.web.domain.AjaxResult;
import com.yizhanshi.common.core.web.page.TableDataInfo;
import com.yizhanshi.common.security.annotation.RequiresPermissions;
import com.yizhanshi.place.api.domain.PlaceApply;
import com.yizhanshi.place.domain.Time;
import com.yizhanshi.place.service.IPlaceApplyService;
import com.yizhanshi.place.service.ITimeService;
import com.yizhanshi.system.api.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 时间信息 业务参数获取
 *
 * @author hejiale
 */
@RestController
@RequestMapping("/time")
public class TimeController extends BaseController {

    @Autowired
    private ITimeService timeService;
    @Autowired
    private IPlaceApplyService placeApplyService;

    /**
     * 查看时间列表——管理员使用
     * @param time
     * @return
     */
    @RequiresPermissions("business:time:list")
    @GetMapping("/list")
    public TableDataInfo list(@RequestBody Time time){
        startPage();
        List<Time> list = timeService.selectTimeList(time);
        return getDataTable(list);
    }

    /**
     * 获取启用的时间列表(status=0)
     * @param
     * @return
     */
    @GetMapping("/getTrueTime")
    public AjaxResult getTrueTimeList(){
        return success(timeService.selectTrueTime());
    }

    /**
     * 新增时间列表——管理员使用
     * @param time
     * @return
     */
    @RequiresPermissions("business:time:add")
    @PostMapping
    public AjaxResult add(@RequestBody Time time){
        return toAjax(timeService.insertTime(time));
    }
    /**
     * 根据时间id查询具体信息——管理员使用
     * @param timeId
     * @return
     */
    @RequiresPermissions("business:time:query")
    @GetMapping("/{timeId}")
    public AjaxResult query(@PathVariable("timeId") Long timeId){
        return success(timeService.selectTimeById(timeId));
    }

    /**
     * 更新时间列表——管理员使用
     * @param time
     * @return
     */
    @RequiresPermissions("business:time:edit")
    @PutMapping
    public AjaxResult edit(@RequestBody Time time){
        return toAjax(timeService.updateTime(time));
    }

    /**
     * 根据类别删除某一方案所有的时间列表
     * @param timeType
     * @return
     */
    @RequiresPermissions("business:time:remove")
    @DeleteMapping("/ByTimeType")
    public AjaxResult remove( String timeType){
        return toAjax(timeService.deleteTime(timeType));
    }

    /**
     * 修改状态,把某一个类型的status全部修改
     * @param timeType status
     * @return
     */
    @RequiresPermissions("business:time:edit")
    @PutMapping("/updateStatus")
    public AjaxResult editStatusByTimeType(String status,String timeType) {
        //确保修改状态后不影响今后的场地预约记录
        PlaceApply placeApply=new PlaceApply();
        Map<String,Object> map=new HashMap<>();
        map.put("beginTime", DateUtils.getTime());
        placeApply.setParams(map);
        if(CollectionUtils.isEmpty(placeApplyService.selectPlaceApplyList(placeApply))){
            return toAjax(timeService.updateStatus(status,timeType));
        }else{
            return  error("存在现在和未来的场地预约记录，目前不可修改时间");
        }

    }



}
