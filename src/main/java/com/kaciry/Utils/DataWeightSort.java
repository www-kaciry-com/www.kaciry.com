package com.kaciry.Utils;

import com.kaciry.entity.VideoInfo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.math.BigInteger.valueOf;

/**
 * @author kaciry
 * @date 2019/10/26 13:57
 * @description 权重排行工具类，用于首页视频
 */
public class DataWeightSort {
    /**
     * @param videoInfo VideoInfo实体，包含信息见实体类
     * @param length    需要返回视频的个数
     * @return java.util.List<com.kaciry.entity.VideoInfo>
     * @author kaciry
     * @description 核心算法，处理返回的数据
     * @date 2019/10/26 14:04
     **/
    public static List<VideoInfo> dataWeightSort(List<VideoInfo> videoInfo, int length) {
        double star, coin, connection, share, playNum, barrage;
        double[] result = new double[videoInfo.size()];
        BigInteger[] index = new BigInteger[videoInfo.size()];
        for (int i = 0; i < videoInfo.size(); i++) {
            //权重
            star = videoInfo.get(i).getVideoStars() * 0.15;
            coin = videoInfo.get(i).getVideoCoins() * 0.15;
            connection = videoInfo.get(i).getVideoConnections() * 0.10;
            share = videoInfo.get(i).getVideoShares() * 0.05;
            playNum = videoInfo.get(i).getVideoPlayNum() * 0.3;
            barrage = videoInfo.get(i).getVideoBarrages() * 0.25;
            //计算当前i的加权平均数,并保存他的videoIndex,索引对应
            result[i] = star + coin + connection + share + playNum + barrage;
            index[i] = videoInfo.get(i).getVideoIdentityDocument();
        }
        //希尔排序
        BigInteger n = valueOf(result.length);
        BigInteger i, j, k, gap;
        for (gap = n.subtract(valueOf(2)); gap.compareTo(valueOf(0)) > 0; gap = gap.divide(valueOf(2))) {
            for (i = BigInteger.valueOf(0); i.compareTo(gap) < 0; i = i.add(valueOf(1))) {
                for (j = i.add(gap); j.compareTo(n) < 0; j = j.add(gap)) {
                    double temp = result[Integer.valueOf(j.toString())];
                    BigInteger indexTemp = index[Integer.valueOf(j.toString())];
                    for (k = j.subtract(gap); k.compareTo(valueOf(0)) >= 0 && result[Integer.valueOf(k.toString())] > temp; k = k.subtract(gap)) {
                        result[Integer.valueOf(k.add(gap).toString())] = result[Integer.valueOf(k.toString())];
                        index[Integer.valueOf(k.add(gap).toString())] = index[Integer.valueOf(k.toString())];
                    }
                    result[Integer.valueOf(k.add(gap).toString())] = temp;
                    index[Integer.valueOf(k.add(gap).toString())] = indexTemp;
                }
            }
        }
        //System.out.println("result : " + Arrays.toString(result));
        //System.out.println("index : " + Arrays.toString(index));
        BigInteger[] array = Arrays.copyOfRange(index, index.length - length, index.length);
        //System.out.println("array" + Arrays.toString(array));
        List<VideoInfo> arrayList = new ArrayList<>();
        for (i = BigInteger.valueOf(0); i.compareTo(BigInteger.valueOf(array.length)) < 0; i = i.add(valueOf(1))) {
            arrayList.add(selectInfo(videoInfo, Integer.valueOf(array[Integer.valueOf(i.toString())].toString())));
        }
        //System.out.println("arrayList : " + arrayList);
        Collections.reverse(arrayList);
        return arrayList;
    }

    /**
     * @param videoInfo VideoInfo实体，包含信息见实体类
     * @param index     索引
     * @return com.kaciry.entity.VideoInfo
     * @author kaciry
     * @description 根据index索引值查找videoInfo对应的数据项并返回
     * @date 2019/10/26 14:05
     **/
    private static VideoInfo selectInfo(List<VideoInfo> videoInfo, int index) {
        for (VideoInfo info : videoInfo) {
            if (info.getVideoIdentityDocument().compareTo(BigInteger.valueOf(index)) == 0) {
                return info;
            }
        }
        return null;
    }

}
