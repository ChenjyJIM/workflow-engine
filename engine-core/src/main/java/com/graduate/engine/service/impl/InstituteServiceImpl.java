package com.graduate.engine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.engine.exception.BusinessException;
import com.graduate.engine.mapper.*;
import com.graduate.engine.model.*;
import com.graduate.engine.model.viewobject.*;
import com.graduate.engine.request.InstituteQuery;
import com.graduate.engine.request.InstituteRequest;
import com.graduate.engine.response.PagedResult;
import com.graduate.engine.service.InstituteService;
import com.graduate.engine.utils.BeanUtils;
import com.graduate.engine.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author jimmy
 */
@Service
public class InstituteServiceImpl extends ServiceImpl<InstituteMapper, Institute> implements InstituteService {

    @Resource
    private InstituteMapper instituteMapper;

    @Resource
    private IndustryMapper industryMapper;

    @Resource
    private InstituteSubMapper instituteSubMapper;

    @Resource
    private InstPeriodMapper instPeriodMapper;

    @Resource
    private PersonMemberMapper personMemberMapper;

    @Resource
    private CompanyMemberMapper companyMemberMapper;

    @Resource
    private InstPeriodPersonMapper instPeriodPersonMapper;

    @Resource
    private InstPeriodCompMapper instPeriodCompMapper;

    @Resource
    private PersonTitleMapper personTitleMapper;

    @Resource
    private InstSubPeriodMapper instSubPeriodMapper;

    @Resource
    private InstSubPeriodPersonMapper instSubPeriodPersonMapper;

    @Override
    public PagedResult<InstituteVo> getAllInstitute(InstituteQuery query) {
        PagedResult<InstituteVo> pagedResult = new PagedResult<>();
        pagedResult.setPage(query.getPage());
        pagedResult.setSize(query.getSize());

        long count = instituteMapper.count(query);
        pagedResult.setTotal(count);
        if (count > 0) {
            List<InstituteVo> instituteVos = new ArrayList<>();
            List<Institute> results = instituteMapper.getInstitutesSelective(query);
            for (Institute result : results) {
                InstituteVo instituteVo = BeanUtils.copyBean(result, InstituteVo.class);
                instituteVo.setIndustryName(industryMapper.selectByPrimaryKey((result.getIndustryId())).getIndusName());
                List<InstPeriodVo> instPeriodVos = getPeriodByInstId(result.getInstId());
                instituteVo.setInstPeriods(instPeriodVos);
                instituteVos.add(instituteVo);
            }
            pagedResult.setItems(instituteVos);
        } else {
            pagedResult.setItems(Collections.<InstituteVo>emptyList());
        }
        return pagedResult;
    }

    @Override
    public List<InstSubsVo> getSimpleSubsByInstId(Long instId) {
        List<InstSubsVo> results = new ArrayList<>();
        List<InstituteSub> instituteSubs = instituteSubMapper.getInstSubsByInstId(instId);
        instituteSubs.forEach( instituteSub -> {
            InstSubsVo instSubsVo = BeanUtils.copyBean(instituteSub, InstSubsVo.class);
            InstSubPeriod currentPeriod = instSubPeriodMapper.getCurrentPeriod(instituteSub.getInstSubId());
            instSubsVo.setInstSubPeriodFrom(DateUtils.getDateStrByTimestamp(currentPeriod.getInstSubPeriodFrom()).substring(0, 10));
            instSubsVo.setInstSubPeriodTo(DateUtils.getDateStrByTimestamp(currentPeriod.getInstSubPeriodTo()).substring(0, 10));
            instSubsVo.setIndustryName(industryMapper.selectByPrimaryKey(instSubsVo.getIndustryId()).getIndusName());
            instSubsVo.setInstSubPeriodNo(currentPeriod.getInstSubPeriodNo());
            InstSubPeriodPerson instSubPeriodPerson = instSubPeriodPersonMapper.getMainPersonByPeriodId(currentPeriod.getInstSubPeriodId());
            instSubsVo.setChargerName(personMemberMapper.selectByPrimaryKey(instSubPeriodPerson.getPersonId()).getName());
            instSubsVo.setPersonTitleName(instSubPeriodPerson.getPersonTitleId() != null ?
                    personTitleMapper.getNameById(instSubPeriodPerson.getPersonTitleId()) : "无职称");
            results.add(instSubsVo);
        });
        return results;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void stopByPrimaryKey(String instId) {
        Institute institute = this.getById(instId);
        institute.setStop(true);
        this.updateById(institute);
    }

    @Override
    public Institute getInstById(Long instId) {
        Institute institute = this.getById(instId);
        int industryId = institute.getIndustryId();
        Industry industry = industryMapper.selectById(industryId);
        //todo 如果分类没有怎么做？插入时判断/这里判断
        institute.setIndusName(industry.getIndusName());
        institute.setIndusShort(industry.getIndusShort());
        return institute;
    }

    @Override
    public List<Institute> getInstitutes() {
        //todo 考虑加入学会行业分类信息
        return instituteMapper.queryAllInst();
    }

    @Override
    public List<InstPeriodVo> getPeriodByInstId(Long instId) {
        List<InstPeriod> periods = instPeriodMapper.getByInstId(instId);
        List<InstPeriodVo> instPeriodVos = new ArrayList<>();
        //遍历历届学会拿到历届学会的负责人和企业理事
        periods.forEach(period -> {
            InstPeriodVo periodVo = BeanUtils.copyBean(period, InstPeriodVo.class);
            periodVo.setInstPeriodFrom(DateUtils.getDateStrByTimestamp(period.getInstPeriodFrom()).substring(0, 10));
            periodVo.setInstPeriodTo(DateUtils.getDateStrByTimestamp(period.getInstPeriodTo()).substring(0, 10));

            //个人理事
            List<InstPeriodPerson> instPeriodPersons = instPeriodPersonMapper.getByInstPeriodId(period.getInstPeriodId());
            List<PersonCharger> personChargers = new ArrayList<>();
            instPeriodPersons.forEach(person -> {
                PersonCharger personCharger = new PersonCharger();
                personCharger.setMemo(person.getInstPeriodPersonMemo());
                personCharger.setPeriodPersonId(person.getInstPeriodPersonId());
                personCharger.setPeriodPersonOrder(person.getInstPeriodPersonOrder());
                personCharger.setPersonTitleName(person.getPersonTitleId() != null ?
                        personTitleMapper.getNameById(person.getPersonTitleId()) : "无职称");
                //设置具体用户信息
                PersonMember personMember = personMemberMapper.selectByPrimaryKey(person.getPersonId());
                personCharger.setPersonMember(BeanUtils.copyBean(personMember, com.graduate.engine.model.dataobject.PersonMember.class));
                personChargers.add(personCharger);
            });
            periodVo.setPersonChargers(personChargers);
            //TODO 单位理事
            List<InstPeriodComp> instPeriodComps = instPeriodCompMapper.getByInstPeriodId(period.getInstPeriodId());
            List<CompCharger> compChargers = new ArrayList<>();
            instPeriodComps.forEach(instPeriodComp -> {
                CompCharger compCharger = new CompCharger();

            });
            instPeriodVos.add(periodVo);
        });
        return instPeriodVos;
    }

    @Override
    public InstituteVo getInstituteByInstId(Long instId) {
        Institute institute = instituteMapper.selectByPrimaryKey(instId);
        InstituteVo result = BeanUtils.copyBean(institute, InstituteVo.class);
        result.setCurrentPeriod("第"+instPeriodMapper.getCurrentPeriod(instId).getInstPeriodNo()+"届");
        result.setIndustryName(industryMapper.selectByPrimaryKey(result.getIndustryId()).getIndusName());
        result.setMemberCount(personMemberMapper.countByInstId(instId));
        result.setCompCount(companyMemberMapper.countByInstId(instId));
        return result;
    }

    @Override
    public InfoListVo getInfoList(Long instId) {
        InfoListVo results = new InfoListVo();
        List<SelectList> subList = new ArrayList<>();
        List<InstSubsVo> subsVos = getSimpleSubsByInstId(instId);
        subsVos.forEach( sub -> {
            SelectList selectList = new SelectList();
            selectList.setId(sub.getInstSubId());
            selectList.setValue(sub.getInstSubName());
            selectList.setLabel(sub.getInstSubName());
            selectList.setContent(sub.getInstSubIntroduction());
            subList.add(selectList);
        });
        results.setSubList(subList);

        List<SelectList> memberList = new ArrayList<>();
        List<PersonMember> members = personMemberMapper.getByInstId(instId);
        members.forEach( member -> {
            SelectList selectList = new SelectList();
            selectList.setId(member.getPersonId());
            selectList.setLabel(member.getName());
            selectList.setValue(member.getName());
            selectList.setContent("联系方式：" + member.getPhone1() + "性别：" + member.getSex());
            memberList.add(selectList);
        });
        results.setMemberList(memberList);


        List<SelectList> compList = new ArrayList<>();
        List<CompanyMember> comps = companyMemberMapper.getByInstId(instId);
        comps.forEach( comp -> {
            SelectList selectList = new SelectList();
            selectList.setId(comp.getCompId());
            selectList.setLabel(comp.getCompName());
            selectList.setValue(comp.getCompName());
            selectList.setContent("企业法人：" + comp.getCompLegalName() + "联系方式：" + comp.getCompLegalInform());
            compList.add(selectList);
        });
        return results;
    }

    @Override
    public List<Institute> getInstListBuInstIds(Long[] instIds) {
        return instituteMapper.queryInstByInstIds(instIds);
    }

    @Override
    public boolean instModify(InstituteRequest request) {
        Institute institute = BeanUtils.copyBean(request, Institute.class);
        return 1 == instituteMapper.updateByPrimaryKeySelective(institute);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean instAdd(InstituteRequest request) {
        Institute institute = BeanUtils.copyBean(request, Institute.class);
        institute.setInstRegisterDate(DateUtils.getCurrentSecondTimestamp());
        if (1 == instituteMapper.insertSelective(institute)) {
            try {
                InstPeriod instPeriod = new InstPeriod();
                instPeriod.setInstMemo(request.getInstPeriodMemo());
                instPeriod.setInstPeriodTo(DateUtils.getTimeStampByUTC(request.getInstPeriodTo()));
                instPeriod.setInstPeriodNo(1);
                instPeriod.setInstPeriodFrom(DateUtils.getCurrentSecondTimestamp());
                instPeriod.setInstId(institute.getInstId());
                if (1 == instPeriodMapper.insertSelective(instPeriod)) {
                    InstPeriodPerson instPeriodPerson = new InstPeriodPerson();
                    instPeriodPerson.setInstId(institute.getInstId());
                    instPeriodPerson.setPersonId(request.getPersonId());
                    instPeriodPerson.setInstPeriodId(instPeriod.getInstPeriodId());
                    // todo 时间关系，目前先写死
                    instPeriodPerson.setPersonTitleId(1);
                    instPeriodPerson.setInstPeriodPersonOrder(1);
                    instPeriodPerson.setInstPeriodPersonMemo("");
                    if (1 == instPeriodPersonMapper.insertSelective(instPeriodPerson)) {
                        return true;
                    }
                }
            } catch (Exception e) {
                throw new BusinessException("日期转化失败");
            }
        }
        return false;
    }
}
