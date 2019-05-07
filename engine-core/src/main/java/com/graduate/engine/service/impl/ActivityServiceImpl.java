package com.graduate.engine.service.impl;

import com.graduate.engine.exception.BusinessException;
import com.graduate.engine.mapper.ActSubChargerMapper;
import com.graduate.engine.mapper.ActivityMapper;
import com.graduate.engine.mapper.ActivitySubMapper;
import com.graduate.engine.mapper.PersonMemberMapper;
import com.graduate.engine.model.ActSubCharger;
import com.graduate.engine.model.Activity;
import com.graduate.engine.model.ActivitySub;
import com.graduate.engine.model.viewobject.ActivitySubDto;
import com.graduate.engine.request.ActivityRequest;
import com.graduate.engine.request.ActivitySubRequest;
import com.graduate.engine.service.ActivityService;
import com.graduate.engine.utils.BeanUtils;
import com.graduate.engine.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private ActivitySubMapper activitySubMapper;

    @Resource
    private PersonMemberMapper personMemberMapper;

    @Resource
    private ActSubChargerMapper actSubChargerMapper;

    @Override
    public Long addActivity(ActivityRequest request) {
        Activity activity = BeanUtils.copyBean(request, Activity.class);
        activity.setActDate(DateUtils.getCurrentMillSecondsTimestamp() / 1000);
        activity.setActStatusId(0);
        try {
            activity.setActDateTo(DateUtils.getTimeStampByUTC(request.getActDateTo()));
            activity.setActDateFrom(DateUtils.getTimeStampByUTC(request.getActDateFrom()));
        } catch (Exception e) {
            throw new BusinessException("日期转化解析失败！");
        }
        List<String> ids = request.getIds();
        activity.setInstId(Long.parseLong(ids.get(0)));
        activity.setInstSubId(Long.parseLong(ids.get(1)));
        activityMapper.insertSelective(activity);
        return activity.getActId();
    }

    @Override
    public int modifyActivity(ActivityRequest request) {
        Activity activity = BeanUtils.copyBean(request, Activity.class);
        try {
            activity.setActDateTo(DateUtils.getTimeStampByUTC(request.getActDateTo()));
            activity.setActDateFrom(DateUtils.getTimeStampByUTC(request.getActDateFrom()));
        } catch (Exception e) {
            throw new BusinessException("日期转化解析失败！");
        }
         return activityMapper.updateByPrimaryKeySelective(activity);
    }

    @Override
    public int deleteActivity(Long actId) {
        Activity activity = new Activity(){
            {
                setActId(actId);
                // 逻辑删除
                setStop(true);
            }
        };
       return activityMapper.updateByPrimaryKeySelective(activity);
    }

    @Override
    public Long addSubActivity(ActivitySubRequest request) {
        ActivitySub activitySub = BeanUtils.copyBean(request, ActivitySub.class);
        activitySub.setActSubStatusId(0);
        try {
            activitySub.setActSubDateTo(DateUtils.getTimeStampByUTC(request.getActSubDateTo()));
            activitySub.setActSubDateFrom(DateUtils.getTimeStampByUTC(request.getActSubDateFrom()));
        } catch (Exception e) {
            throw new BusinessException("日期转化解析失败！");
        }
        List<String> ids = request.getIds();
        activitySub.setInstId(Long.parseLong(ids.get(0)));
        activitySub.setInstSubId(Long.parseLong(ids.get(1)));
        if ("子活动".equals(request.getParentType())) {
            activitySub.setActSubFatherId(request.getParentId());
        }
        // 设置总活动根节点id
        activitySub.setActId(request.getActId());
        if (1 == activitySubMapper.insertSelective(activitySub)) {
            // 插入成功才更新负责人
            List<ActivitySubDto> actSubChargerList = request.getPersonChargers();
            actSubChargerList.stream()
                    .filter(e -> e.getStatus())
                    .map(r -> BeanUtils.copyBean(r, ActSubCharger.class))
                    .peek(e -> e.setInstId(personMemberMapper.selectByPrimaryKey(e.getPersonId()).getInstId()))
                    .peek(e -> e.setActSubId(activitySub.getActSubId()))
                    .collect(Collectors.toList()).forEach(actSubCharger -> actSubChargerMapper.insertSelective(actSubCharger));
        } else {
            throw new BusinessException("插入失败！");
        }
        return activitySub.getActSubId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean modifyActivitySub(ActivitySubRequest request){
        ActivitySub activitySub = BeanUtils.copyBean(request, ActivitySub.class);
        try {
            activitySub.setActSubDateTo(DateUtils.getTimeStampByUTC(request.getActSubDateTo()));
            activitySub.setActSubDateFrom(DateUtils.getTimeStampByUTC(request.getActSubDateFrom()));
        } catch (Exception e) {
            throw new BusinessException("日期转化解析失败！");
        }
        ActSubCharger actSubCharger = new ActSubCharger();
        actSubCharger.setActSubId(request.getActSubId());
        actSubCharger.setPersonId(request.getPersonId());
        actSubCharger.setActSubChargerDuty(request.getDuty());
        activitySubMapper.updateByPrimaryKeySelective(activitySub);
        actSubChargerMapper.updateByPrimaryKeySelective(actSubCharger);
        return true ;
    }

    @Override
    public int deleteActivitySub(Long actSubId) {
        ActivitySub activitySub = new ActivitySub(){
            {
                setActSubId(actSubId);
                // 逻辑删除
                setStop(true);
            }
        };
        return activitySubMapper.updateByPrimaryKeySelective(activitySub);
    }
}
