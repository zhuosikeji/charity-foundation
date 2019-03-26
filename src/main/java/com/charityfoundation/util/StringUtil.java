package com.charityfoundation.util;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * StringUtil class
 *
 * @author tianjun
 * @date 18-7-25
 */
public class StringUtil {
    /**
     * 从字符串中获取由0~9组成的字符串
     * @param s 字符串
     * @return 0~9的字符串
     */
    public static String getNumber(String s){
        if (StringUtils.isEmpty(s)){
            return null;
        }
        s = s.replaceAll("[^0-9]", "");
        return s;
    }

    /**
     * 查找字符串中m到n之间的字符串,不包含m,n字符
     * @param s 待处理的字符串
     * @param m 开始字符串
     * @param n 结束字符串
     * @return
     */
    public static String GetDataBetweenExclusiveMn(String s, String m,String n){
        if (StringUtils.isEmpty(s) && s.length() < 3){
            return s;
        }
        if (s.contains(m) && s.contains(n)){
            String result = s.substring(s.indexOf(m) + 1, s.lastIndexOf(n));
            return result;
        }
        return s;
    }

    /**
     * 将字符串s以字符c进行分割
     * @param s 待处理的字符串
     * @param c 分割的字符串
     * @return 字符串类型的集合
     */
    public static List<String> SplitString(String s, String c) {
        List<String> list= new ArrayList();
        if (StringUtils.isEmpty(s)) {
            return list;
        }
        if (!s.contains(c)){
            list.add(s);
            return list;
        }
        //+、*、|、\
        if (c.contains("+")||c.contains("*")||c.contains("|")||c.contains("\\")){
            c = "["+c+"]";
        }
        String[] array = s.trim().split(c);
        for (int i = 0; i < array.length; i++) {
            //过滤掉分割后为空的字符串
            if (!StringUtils.isEmpty(array[i])) {
                list.add(array[i].trim());
            }
        }
        return list;
    }



    /**
     * 主要针对前悬/后悬，接近角/离去角这些组合数据，获取第一个值
     * @param data 待处理数据
     * @return
     */
    public static List<String> getFrontData(String data){
        List<String> list = new ArrayList<>();
        if (StringUtils.isEmpty(data)){
            return list;
        }
        //判断该条数据有多少个m/n类型的组合组合,然后将m/悬组合拆成m和n
        List<String> stringList = StringUtil.SplitString(data, ",");
        if (stringList.isEmpty()){
            return list;
        }
        for (String s : stringList) {
            List<String> temp = StringUtil.SplitString(s, "/");
            if (!temp.isEmpty()){
                if (temp.size() == 2) {
                    String s1 = temp.get(0).trim().replace("-","");
                    if (!StringUtils.isEmpty(s1)){
                        list.add(s1);
                    }
                }
            }

        }
        //集合去重
        HashSet hs = new HashSet(list);
        list.clear();
        list.addAll(hs);
        return list;
    }


    /**
     * 主要针对前悬/后悬，接近角/离去角这些组合数据，获取第二个值
     * @param data 待处理的字符串
     * @return String类型的集合
     */
    public static List<String> getRearData(String data){

        List<String> list = new ArrayList<>();
        if (StringUtils.isEmpty(data)){
            return list;
        }
        //判断该条数据有多少个m/n类型的组合组合,然后将m/悬组合拆成m和n
        List<String> stringList = StringUtil.SplitString(data, ",");
        if (stringList.isEmpty()){
            return list;
        }
        for (String s : stringList) {
            List<String> temp = StringUtil.SplitString(s, "/");
            if (!temp.isEmpty()){
                if (temp.size() == 2) {
                    String s2 = temp.get(1).trim().replace("-","");
                    if (!StringUtils.isEmpty(s2)){
                        list.add(s2);
                    }
                }
            }

        }
        //集合去重
        HashSet hs = new HashSet(list);
        list.clear();
        list.addAll(hs);
        return list;
    }

    /**
     * 将轴距转换成集合
     * @param s
     */
    public static List<Integer> getWheelbaseData(String s){
        List<Integer> list = new ArrayList<>();
        if (StringUtils.isEmpty(s)){
            return list;
        }

        //先根据逗号分割
        List<String> list1 = StringUtil.SplitString(s, ",");
        if (!list1.isEmpty()){
            for (String l1 : list1) {
                //再根据,号进行分割
                //先根据加号进行分割
                List<String> list2 = StringUtil.SplitString(l1, "+");
                for (String l2: list2) {
                    String t = l2.trim().replace(",","").replace("+","");
                    if (!StringUtils.isEmpty(t)){
                        Integer it = null;
                        try{
                            it = Integer.parseInt(t);
                            list.add(it);
                        }catch (Exception e){
                            e.getStackTrace();
                        }
                    }
                }
            }
        }
        //集合去重
        HashSet hs = new HashSet(list);
        list.clear();
        list.addAll(hs);
        return list;
    }


    /**
     * 将字符串转成Integer的集合
     * @param s 待处理的数据
     * @return
     */
    public static List<Integer> getIntegerData(String s){
        List<Integer> list = new ArrayList<>();
        if (StringUtils.isEmpty(s)){
            return list;
        }
        //根据逗号分割
        List<String> list1 = StringUtil.SplitString(s, ",");
        if (!list1.isEmpty()){
            for (String l1 : list1) {
                String t = l1.trim().replace(",", "").replace("-", "");
                if (!StringUtils.isEmpty(t)) {
                    Integer it = null;
                    try {
                        it = Integer.parseInt(t);
                        list.add(it);
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                }
            }
        }
        //集合去重
        HashSet hs = new HashSet(list);
        list.clear();
        list.addAll(hs);
        return list;
    }
}
