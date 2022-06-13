package com.lfs.common.util;

import com.lfs.common.vo.UserVo;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * BeanUtils
 *
 * @author fushenliao
 * @version 1.0.0
 * @project tecwealth
 * @create 2022/6/10
 * @modify 2022/6/10
 */
public class BeanUtils {
    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    private static String[] getNotNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue != null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static void copyIfSrcNotNull(Object src, Object target) {
        org.springframework.beans.BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    public static void copyIfTargetNull(Object src, Object target) {
        org.springframework.beans.BeanUtils.copyProperties(src, target, getNotNullPropertyNames(target));
    }

    public static void main(String[] args) {

        UserVo userVo = new UserVo();
        userVo.setUid(234234L);
        userVo.setUname("lisa");
        System.out.println(JsonUtils.toStr((userVo)));
        System.out.println(JsonUtils.toStr(getNullPropertyNames(userVo)));
        System.out.println(JsonUtils.toStr(getNotNullPropertyNames(userVo)));
    }
}
