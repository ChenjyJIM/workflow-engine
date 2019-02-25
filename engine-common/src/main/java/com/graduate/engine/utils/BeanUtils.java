package com.graduate.engine.utils;

import com.graduate.engine.exception.BusinessException;

import java.util.ArrayList;
import java.util.List;

public class BeanUtils {
    /**
     * 将包含BeanA的list，转换为包含BeanB的list。应用场景示例：List<DO>转List<TO>
     *
     * @param sourceList
     * @param targetClass
     * @return
     */
    public static <T> List<T> copyListWithBeans(List<?> sourceList, Class<T> targetClass) {
        if (sourceList == null) {
            return null;
        }

        List<T> targetList = new ArrayList<T>();

        for (Object tempSrc : sourceList) {
            try {
                T t = copyBean(tempSrc, targetClass);
                targetList.add(t);
            } catch (Exception e) {
                throw new BusinessException(e.getMessage());
            }
        }

        return targetList;
    }

    public static <T> T copyBean(Object srcBean, Class<T> targetClass) {
        try {
            T t = targetClass.newInstance();
            org.springframework.beans.BeanUtils.copyProperties(srcBean, t);
            return t;
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
