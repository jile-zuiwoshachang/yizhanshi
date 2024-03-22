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
import com.yizhanshi.course.api.domain.CourseTime;
import com.yizhanshi.place.api.domain.Place;
import com.yizhanshi.place.api.domain.PlaceApplyTime;
import com.yizhanshi.place.domain.vo.PlaceApplyExport;
import com.yizhanshi.place.domain.vo.PlaceApplyTimeExport;
import com.yizhanshi.place.service.IPlaceApplyService;
import com.yizhanshi.place.service.IPlaceApplyTimeRelatedService;
import com.yizhanshi.place.service.IPlaceApplyTimeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

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
     * 前端请传递applyId,后端返回这个预约的是时间列表
     */
    @RequiresPermissions("business:placeApplyTime:byApplyId")
    @GetMapping("/byApplyId/{applyId}")
    public TableDataInfo byApplyId(@PathVariable("applyId") Long applyId) {
        startPage();
        List<PlaceApplyTime> list = placeApplyTimeService.selectPlaceApplyTimeByApplyId(applyId);
        return getDataTable(list);
    }

    /**
     * 导出场地预约时间信息——管理员使用
     * 只导出数据库的表记录
     *
     * @param response
     * @param placeAppplyTime
     */
    @Log(title = "场地预约时间管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("business:placeApplyTime:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody PlaceApplyTime placeAppplyTime) {
        List<PlaceApplyTime> list = placeApplyTimeService.selectPlaceApplyTimeList(placeAppplyTime);
        List<PlaceApplyTimeExport> placeApplyTimeExportList=new ArrayList<>();
        list.forEach(placeApplyTimeTemp -> {
            PlaceApplyTimeExport placeApplyTimeExport = new PlaceApplyTimeExport();
            BeanUtils.copyProperties(placeApplyTimeTemp, placeApplyTimeExport); // 先复制
            placeApplyTimeExport.setPlaceName(placeApplyTimeTemp.getPlace().getPlaceName());
            placeApplyTimeExport.setPlaceCampus(placeApplyTimeTemp.getPlace().getPlaceCampus());
            placeApplyTimeExportList.add(placeApplyTimeExport);
        });
        ExcelUtil<PlaceApplyTimeExport> util = new ExcelUtil<PlaceApplyTimeExport>(PlaceApplyTimeExport.class);
        util.exportExcel(response, placeApplyTimeExportList, "场地预约时间信息数据");
    }

    /**
     * 根据场地预约时间编号获取详细信息——管理员使用
     */
    @RequiresPermissions("business:placeApplyTime:query")
    @GetMapping(value = {"/{applyTimeId}"})
    public AjaxResult getInfo(@PathVariable(value = "applyTimeId") Long applyTimeId) {
        return success(placeApplyTimeService.selectPlaceApplyTimeById(applyTimeId));
    }

    /**
     * 新增场地预约时间信息——管理员使用
     * 比如这个活动还想继续新增预约时间
     * 是后期修改用，不是第一次场地预约使用
     *
     * @param placeApplyTime
     * @return
     */
    @RequiresPermissions("business:placeApplyTime:add")
    @Log(title = "场地预约时间管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody PlaceApplyTime placeApplyTime) {
        //先判断冲突
        String str = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, placeApplyTime.getApplyDay());
        List<PlaceApplyTime> dataBasePlaceTimeApplies = placeApplyTimeService.selectAllPlace(placeApplyTime.getPlaceId(), str);
        //先判断所选择天数的所在场地第一天的情况，然后循环判断
        if (placeApplyService.timeConflict(dataBasePlaceTimeApplies, placeApplyTime)) {
            return error("时间冲突!请查看当天场地预约信息后修改时间");
        }
        //再远程调用课程服务，判断时间是否冲突
        //单个对象转化为列表方法
        if (timeConflict(Collections.singletonList(placeApplyTime), str)) {
            return error("时间冲突！请查看当天课程预约信息后修改时间");
        }
        //不冲突则新增
        placeApplyTime.setCreateBy(SecurityUtils.getUsername());
        placeApplyTimeService.insertPlaceApplyTime(placeApplyTime);
        return success();
    }

    private Boolean timeConflict(List<PlaceApplyTime> placeApplyTimes, String str) {
        //再远程调用课程服务，判断时间是否冲突
        List<CourseTime> courseTimes = new ArrayList<>();
        for (PlaceApplyTime placeApplyTime : placeApplyTimes) {
            CourseTime courseTime = new CourseTime();
            courseTime.setTimeStartId(placeApplyTime.getTimeStartId());
            courseTime.setTimeEndId(placeApplyTime.getTimeEndId());
            courseTime.setPlaceId(placeApplyTime.getPlaceId());
            Map<String, Object> params = new HashMap<>();
            params.put("chooseDay", str);
            courseTime.setParams(params);
        }
        return placeApplyService.timeConflictByCourse(courseTimes);
    }

    /**
     * 删除场地预约时间信息——管理员使用
     */
    @RequiresPermissions("business:placeTime:remove")
    @Log(title = "场地预约时间管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{applyTimeIds}")
    public AjaxResult remove(@PathVariable Long[] applyTimeIds) {
        placeApplyTimeService.deletePlaceApplyTime(applyTimeIds);
        return success();
    }
}
