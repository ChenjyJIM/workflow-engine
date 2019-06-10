package com.graduate.engine.mapper;

import com.graduate.engine.model.PersonMember;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonMemberMapper {
    int deleteByPrimaryKey(Integer personId);

    int insert(PersonMember record);

    int insertSelective(PersonMember record);

    PersonMember selectByPrimaryKey(Long personId);

    /**
     * 通过会员证号码（非主键）查询出会员信息
     *
     * @param personMemberId
     * @return
     */
    PersonMember selectByPersonMemberID(Long personMemberId);


    int updateByPrimaryKeySelective(PersonMember record);

    int updateByPrimaryKey(PersonMember record);

    /**
     * 根据学会id查询出该学会的成员数量
     *
     * @param instId
     * @return
     */
    Integer countByInstId(Long instId);

    /**
     * 根据学会id查询出该学会会员
     *
     * @param instId
     */
    List<PersonMember> getByInstId(@Param("instId") Long instId);
}
