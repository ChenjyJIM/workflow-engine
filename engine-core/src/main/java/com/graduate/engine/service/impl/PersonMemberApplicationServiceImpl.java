package com.graduate.engine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.engine.model.Institute;
import com.graduate.engine.model.PersonMemberApplication;
import com.graduate.engine.mapper.PersonMemberApplicationMapper;
import com.graduate.engine.model.PersonMemberApplicationDetails;
import com.graduate.engine.model.PersonMemberApplicationRecord;
import com.graduate.engine.service.InstituteService;
import com.graduate.engine.service.PersonMemberApplicationDetailsService;
import com.graduate.engine.service.PersonMemberApplicationRecordService;
import com.graduate.engine.service.PersonMemberApplicationService;
import com.graduate.engine.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

/**
 * <p>
 * 个人会员申请表 服务实现类
 * </p>
 *
 * @author silicon
 * @since 2019-05-09
 */
@Service
public class PersonMemberApplicationServiceImpl extends ServiceImpl<PersonMemberApplicationMapper, PersonMemberApplication> implements PersonMemberApplicationService {
    @Resource
    PersonMemberApplicationDetailsService detailsService;
    @Resource
    PersonMemberApplicationRecordService recordService;
    @Resource
    PersonMemberApplicationMapper applicationMapper;
    @Resource
    InstituteService instituteService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void applyPersonMember(PersonMemberApplicationDetails details, Long userId) throws ParseException {
        Long time = DateUtils.getCurrentSecondTimestamp();
        Institute institute = instituteService.getInstById(details.getInstId());
        //申请表信息
        PersonMemberApplication application = new PersonMemberApplication();
        application.setLoginId(userId);
        application.setAddTime(time);
        application.setLastEditTime(time);
        application.setStatus(0L);
        this.save(application);
        //详情表信息
        details.setApplicationId(application.getApplicationId());
        details.setBirthday(DateUtils.getTimeStampByUTC(details.getFormatBirthday()));
        detailsService.save(details);
        //记录表信息
        PersonMemberApplicationRecord record = new PersonMemberApplicationRecord();
        record.setApplicationId(application.getApplicationId());
        record.setEditPerson(userId);
        record.setEditTime(time);
        record.setMessage("发起申请，希望加入" + institute.getInstName());
        recordService.save(record);
    }

    @Override
    public List<PersonMemberApplication> selectByLoginId(Long loginId) {
        return applicationMapper.selectByLoginId(loginId);
    }

    @Override
    public List<PersonMemberApplication> selectReviewApplication(Long initId) {
        return applicationMapper.selectReviewApplication(initId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void agreeApplication(Long applicationId, Long editPersonId) {
        Long time = DateUtils.getCurrentSecondTimestamp();
        //更新申请表
        PersonMemberApplication application = this.getById(applicationId);
        application.setStatus(1L);
        application.setLastEditTime(time);
        this.updateById(application);
        //添加记录到申请记录表
        PersonMemberApplicationRecord record = new PersonMemberApplicationRecord();
        record.setApplicationId(applicationId);
        record.setMessage("通过申请");
        record.setEditPerson(editPersonId);
        record.setEditTime(time);
        recordService.save(record);
    }

    @Override
    public void disagreeApplication(Long applicationId, String message, Long editPersonId) {
        Long time = DateUtils.getCurrentSecondTimestamp();
        //更新申请表
        PersonMemberApplication application = this.getById(applicationId);
        application.setStatus(2L);
        application.setLastEditTime(time);
        this.updateById(application);
        //添加记录到申请记录表
        PersonMemberApplicationRecord record = new PersonMemberApplicationRecord();
        record.setApplicationId(applicationId);
        record.setMessage(message);
        record.setEditPerson(editPersonId);
        record.setEditTime(time);
        recordService.save(record);
    }
}
