package com.graduate.engine.mapper;

import com.graduate.engine.model.CompanyMember;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyMemberMapper {
    int deleteByPrimaryKey(Integer compId);

    int insert(CompanyMember record);

    int insertSelective(CompanyMember record);

    CompanyMember selectByPrimaryKey(Integer compId);

    int updateByPrimaryKeySelective(CompanyMember record);

    int updateByPrimaryKey(CompanyMember record);

    /**
     * 根据instId获取单位会员的数量
     *
     * @param instId
     * @return
     */
    Integer countByInstId(Long instId);

    /**
     * 根据instId获取到单位会员到信息
     */
    List<CompanyMember> getByInstId(Long instId);
}
