package com.charityfoundation.util;

import java.lang.reflect.Field;
import java.util.List;


/**
 * 判断对象是否为空
 * EmptyUtil class
 *
 * @author tianjun
 * @date 18-7-14
 */

public class EmptyUtil
{
    /**
     * 判断对象为空
     * @param
     * obj   对象名
     * @return 是否为空
     */
    public static boolean isEmpty(Object obj) throws IllegalAccessException {
//        boolean flag = false;
//        for(Field f : obj.getClass().getDeclaredFields()){
//            f.setAccessible(true);
//            if(f.get(obj) == null || f.get(obj).equals("")){
//                flag = true;
//                return flag;
//            }
//        }
//        return flag;

        // 得到类对象
        Class stuCla = (Class) obj.getClass();
        //得到属性集合
        Field[] fs = stuCla.getDeclaredFields();
        boolean flag = true;
        //遍历属性
        for (Field f : fs) {
            // 设置属性是可以访问的(私有的也可以)
            f.setAccessible(true);
            // 得到此属性的值
            Object val = f.get(obj);
            //只要有1个属性不为空,那么就不是所有的属性值都为空
            if(val!=null&& val!="") {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * 判断对象不为空
     * @param obj  对象名
     * @return 是否不为空
     */
    public static boolean isNotEmpty(Object obj) throws IllegalAccessException {
        return !isEmpty(obj);
    }

    public static boolean isListNotNull(List<?> list){
        return !(null == list || list.size() ==0);
    }
}
