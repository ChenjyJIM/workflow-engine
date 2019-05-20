package com.graduate.engine.service.impl;

import com.graduate.engine.exception.BusinessException;
import com.graduate.engine.mapper.ActMilestoneMapper;
import com.graduate.engine.mapper.ActSubMilestoneMapper;
import com.graduate.engine.model.ActMilestone;
import com.graduate.engine.model.ActSubMilestone;
import com.graduate.engine.model.viewobject.MilestoneVo;
import com.graduate.engine.request.MilestoneRequest;
import com.graduate.engine.service.MilestoneService;
import com.graduate.engine.utils.BeanUtils;
import com.graduate.engine.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MilestoneServiceImpl implements MilestoneService {

    @Resource
    private ActMilestoneMapper actMilestoneMapper;

    @Resource
    private ActSubMilestoneMapper actSubMilestoneMapper;

    @Override
    public boolean addMilestone(MilestoneRequest request) {
        switch (request.getType()) {
            case "活动":
                ActMilestone actMilestone = BeanUtils.copyBean(request, ActMilestone.class);
                actMilestone.setActId(request.getId());
                try {
                    actMilestone.setMilestoneFrom(DateUtils.getTimeStampByUTC(request.getMilestoneFrom()));
                    actMilestone.setMilestoneTo(DateUtils.getTimeStampByUTC(request.getMilestoneTo()));
                } catch (ParseException e) {
                    throw new BusinessException("日期转化解析失败！");
                }
                return 1 == actMilestoneMapper.insertSelective(actMilestone);
            case "子活动":
                ActSubMilestone actSubMilestone = new ActSubMilestone();
                actSubMilestone.setActSubId(request.getId());
                try {
                    actSubMilestone.setSubMilestoneFrom(DateUtils.getTimeStampByUTC(request.getMilestoneFrom()));
                    actSubMilestone.setSubMilestoneTo(DateUtils.getTimeStampByUTC(request.getMilestoneTo()));
                } catch (Exception e) {
                    throw new BusinessException("日期转化解析失败！");
                }
                actSubMilestone.setSubMilestoneMemo(request.getMilestoneMemo());
                actSubMilestone.setSubMilestoneName(request.getMilestoneName());
                return 1 == actSubMilestoneMapper.insertSelective(actSubMilestone);
            default: return false;
        }
    }

    @Override
    public boolean modifiyMilestone(MilestoneRequest request) {
        switch (request.getType()) {
            case "活动":
                ActMilestone actMilestone = BeanUtils.copyBean(request, ActMilestone.class);
                actMilestone.setActId(request.getId());
                actMilestone.setMilestoneId(request.getMilestoneId());
                try {
                    actMilestone.setMilestoneFrom(DateUtils.getTimestampByDateStr(request.getMilestoneFrom()));
                    actMilestone.setMilestoneTo(DateUtils.getTimestampByDateStr(request.getMilestoneTo()));
                } catch (Exception e) {
                    throw new BusinessException("日期转化解析失败！");
                }
                return 1 == actMilestoneMapper.updateByPrimaryKeySelective(actMilestone);
            case "子活动":
                ActSubMilestone actSubMilestone = new ActSubMilestone();
                actSubMilestone.setActSubId(request.getId());
                actSubMilestone.setSubMilestoneId(request.getMilestoneId());
                try {
                    actSubMilestone.setSubMilestoneFrom(DateUtils.getTimestampByDateStr(request.getMilestoneFrom()));
                    actSubMilestone.setSubMilestoneTo(DateUtils.getTimestampByDateStr(request.getMilestoneTo()));
                } catch (Exception e) {
                    throw new BusinessException("日期转化解析失败！");
                }
                actSubMilestone.setSubMilestoneMemo(request.getMilestoneMemo());
                actSubMilestone.setSubMilestoneName(request.getMilestoneName());
                actSubMilestone.setSubMilestoneReport(request.getMilestoneReport());
                actSubMilestone.setSubMilestoneMonitor(request.getMilestoneMonitor());
                return 1 == actSubMilestoneMapper.updateByPrimaryKeySelective(actSubMilestone);
            default: return false;
        }
    }

    @Override
    public boolean deleteMilestone(Long id, String type) {
        if ("活动".equals(type)) {
            return 1 == actMilestoneMapper.deleteByPrimaryKey(id);
        } else if ("子活动".equals(type)) {
            return 1 == actSubMilestoneMapper.deleteByPrimaryKey(id);
        }
        return false;
    }

    @Override
    public List<MilestoneVo> getMilestone(Long id, String type) {
        switch (type) {
            case "act":
                return actMilestoneMapper.getById(id).stream().map(e -> BeanUtils.copyBean(e, MilestoneVo.class)).collect(Collectors.toList());
            case "actSub":
                List<ActSubMilestone> byId = actSubMilestoneMapper.getById(id);
                return actSubMilestoneMapper.getById(id).stream().map(ActSubMilestone::packetVo).collect(Collectors.toList());
            default:
                throw new BusinessException("type识别错误！");
        }
    }
}
