package com.yizhanshi.place.controller;

import com.yizhanshi.common.core.web.controller.BaseController;
import com.yizhanshi.common.core.web.domain.AjaxResult;
import com.yizhanshi.common.core.web.page.TableDataInfo;
import com.yizhanshi.common.security.annotation.RequiresPermissions;
import com.yizhanshi.place.domain.Time;
import com.yizhanshi.place.service.ITimeService;
import com.yizhanshi.system.api.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @RequiresPermissions("business:time:add")
    @PostMapping
    public AjaxResult add(@RequestBody Time time){
        return toAjax(timeService.insertTime(time));
    }
    @RequiresPermissions("business:time:update")
    @PutMapping
    public AjaxResult edit(@RequestBody Time time){
        return toAjax(timeService.updateTime(time));
    }
    @RequiresPermissions("business:time:delete")
    @DeleteMapping
    public AjaxResult remove(String timeType){
        return toAjax(timeService.deleteTime(timeType));
    }

    /**
     * 修改状态,把某一个类型的status全部修改
     * @param timeType status
     * @return
     */
    @PutMapping
    public AjaxResult editStatusByTimeType(String status,String timeType) {
        return toAjax(timeService.updateStatus(status,timeType));
    }



}
