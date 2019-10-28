package com.kaciry.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author kaciry
 * @date 2019/10/26 13:57
 * @description 初始化敏感词库，将敏感词加入到HashMap中，构建DFA算法模型
 */
class SensitiveWordInit {
    @SuppressWarnings("rawtypes")
    private HashMap sensitiveWordMap;

    SensitiveWordInit() {
        super();
    }

    @SuppressWarnings("rawtypes")
    Map initKeyWord() {
        try {
            //读取敏感词库
            Set<String> keyWordSet = readSensitiveWordFile();
            //将敏感词库加入到HashMap中
            addSensitiveWordToHashMap(keyWordSet);
            //spring获取application，然后application.setAttribute("sensitiveWordMap",sensitiveWordMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sensitiveWordMap;
    }

    /**
     * @param keyWordSet
     * @return void
     * @author kaciry
     * @description 将得到的敏感词库用一个DFA算法模型放到map中
     * @date 2019/10/26 14:11
     **/
    @SuppressWarnings({"rawtypes", "unchecked"})
    private void addSensitiveWordToHashMap(Set<String> keyWordSet) {
        //初始化敏感词容器，减少扩容操作
        sensitiveWordMap = new HashMap(keyWordSet.size());
        String key = null;
        Map nowMap = null;
        Map<String, String> newWorMap = null;
        //迭代keyWordSet
        for (String s : keyWordSet) {
            //关键字
            key = s;
            nowMap = sensitiveWordMap;
            for (int i = 0; i < key.length(); i++) {
                //转换成char型
                char keyChar = key.charAt(i);
                //获取
                Object wordMap = nowMap.get(keyChar);
                //如果存在该key，直接赋值
                if (wordMap != null) {
                    nowMap = (Map) wordMap;
                } else {     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                    newWorMap = new HashMap<>();
                    //不是最后一个
                    newWorMap.put("isEnd", "0");
                    nowMap.put(keyChar, newWorMap);
                    nowMap = newWorMap;
                }
                if (i == key.length() - 1) {
                    //最后一个
                    nowMap.put("isEnd", "1");
                }
            }
        }
    }

    /**
     * @param
     * @return java.util.Set<java.lang.String>
     * @author kaciry
     * @description 读取敏感词文件 加到set集合中
     * @date 2019/10/26 14:11
     **/
    @SuppressWarnings("resource")
    private Set<String> readSensitiveWordFile() throws Exception {
        Set<String> set;
        //读取文件
        File file = new File("G:\\SensitiveWord.txt");
        //字符编码
        String ENCODING = "UTF-8";
        try (InputStreamReader read = new InputStreamReader(new FileInputStream(file), ENCODING)) {
            //文件流是否存在
            if (file.isFile() && file.exists()) {
                set = new HashSet<>();
                BufferedReader bufferedReader = new BufferedReader(read);
                String txt;
                //读取文件，将文件内容放入到set中
                while ((txt = bufferedReader.readLine()) != null) {
                    set.add(txt);
                }
            } else {         //不存在抛出异常信息
                throw new Exception("敏感词库文件不存在");
            }
        }
        //关闭文件流
        return set;
    }
}
