package com.yizhanshi.place.controller;

import com.yizhanshi.common.core.utils.DateUtils;
import com.yizhanshi.common.core.utils.poi.ExcelUtil;
import com.yizhanshi.common.core.web.controller.BaseController;
import com.yizhanshi.common.core.web.domain.AjaxResult;
import com.yizhanshi.common.core.web.page.TableDataInfo;
import com.yizhanshi.common.log.annotation.Log;
import com.yizhanshi.common.log.enums.BusinessType;
import com.yizhanshi.common.security.annotation.RequiresPermissions;
import com.yizhanshi.common.security.utils.SecurityUtils;
import com.yizhanshi.course.api.domain.Course;
import com.yizhanshi.place.api.domain.Place;
import com.yizhanshi.place.api.domain.PlaceApplyTime;
import com.yizhanshi.place.service.IPlaceApplyService;
import com.yizhanshi.place.service.IPlaceApplyTimeRelatedService;
import com.yizhanshi.place.service.IPlaceApplyTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 场地预约时间信息 业务处理
 *
 * @author hejiale
 */

@RestController
@RequestMapping("/placeApplyTime")
public class PlaceApplyTimeController extends BaseController {
    @Autowired
    private IPlaceApplyTimeService placeApplyTimeService;
    @Autowired
    private IPlaceApplyService placeApplyService;
    @Autowired
    private IPlaceApplyTimeRelatedService placeApplyTimeRelatedService;
    /**
     * 场地预约时间信息——管理员使用
     */
    @RequiresPermissions("business:placeApplyTime:list")
    @GetMapping("/list")
    public TableDataInfo list(@RequestBody PlaceApplyTime placeApplyTime) {
        startPage();
        List<PlaceApplyTime> list = placeApplyTimeService.selectPlaceApplyTimeList(placeApplyTime);
        return getDataTable(list);
    }

    /**
     * 导出场地预约时间信息——管理员使用
     * @param response
     * @param placeAppplyTime
     */
    @Log(title = "场地预约时间管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("business:placeApplyTime:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody PlaceApplyTime placeAppplyTime)
    {
        List<PlaceApplyTime> list = placeApplyTimeService.selectPlaceApplyTimeList(placeAppplyTime);
        ExcelUtil<PlaceApplyTime> util = new ExcelUtil<PlaceApplyTime>(PlaceApplyTime.class);
        util.exportExcel(response, list, "场地预约时间信息数据");
    }
    /**
     * 根据场地预约时间编号获取详细信息——管理员使用
     */
    @RequiresPermissions("business:placeApplyTime:query")
    @GetMapping(value = { "/{applyTimeId}" })
    public AjaxResult getInfo(@PathVariable(value = "applyTimeId") Long applyTimeId){
        return success(placeApplyTimeService.selectPlaceApplyTimeById(applyTimeId));
    }

    /**
     * 新增场地预约时间信息——管理员使用
     * 比如这个活动还想继续新增预约时间
     * 是后期修改用，不是第一次场地预约使用
     * @param placeApplyTime
     * @return
     */
    @RequiresPermissions("business:placeApplyTime:add")
    @Log(title = "场地预约时间管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody PlaceApplyTime placeApplyTime) {
        //先判断冲突
        String str= DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,placeApplyTime.getApplyDay());
        List<PlaceApplyTime> dataBasePlaceTimeApplies = placeApplyTimeService.selectAllPlace(placeApplyTime.getPlaceId(), str);
        //先判断所选择天数的所在场地第一天的情况，然后循环判断
        if(placeApplyService.timeConflict(dataBasePlaceTimeApplies,placeApplyTime)){
            return error("时间冲突!请查看当天场地预约信息后修改时间");
        }
        //再远程调用课程服务，判断时间是否冲突
        Course course=new Course();
        course.setTimeStartId(placeApplyTime.getTimeStartId());
        course.setTimeEndId(placeApplyTime.getTimeEndId());
        course.setCourseStartTime(placeApplyTime.getApplyStartTime());
        course.setCourseEndTime(placeApplyTime.getApplyEndTime());
        course.setPlaceId(placeApplyTime.getPlaceId());
        Map<String,Object> params=new HashMap<>();
        params.put("chooseDay",str);
        course.setParams(params);
        if(placeApplyService.timeConflictByCourse(course)) {
            return error("与课程预约冲突，请检查课程预约时间");
        }
        //不冲突则新增
        placeApplyTime.setCreateBy(SecurityUtils.getUsername());
        placeApplyTimeService.insertPlaceApplyTime(placeApplyTime);
        return success();
    }

    /**
     * 删除场地预约时间信息——管理员使用
     */
    @RequiresPermissions("business:placeTime:remove")
    @Log(title = "场地管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{placeTimeIds}")
    public AjaxResult removePlace(@PathVariable Long[] placeTimeIds)
    {
        placeApplyTimeService.deletePlaceApplyTime(placeTimeIds);
        return success();
    }
}
