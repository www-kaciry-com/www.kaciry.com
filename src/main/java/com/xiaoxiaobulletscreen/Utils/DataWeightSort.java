package com.xiaoxiaobulletscreen.Utils;

import com.xiaoxiaobulletscreen.entity.VideoInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author kaciry
 * @date 2019/10/26 13:57
 * @description 权重排行工具类，用于首页视频
 */
public class DataWeightSort {
    /**
     * @param videoInfo VideoInfo实体，包含信息见实体类
     * @param length    需要返回视频的个数
     * @return java.util.List<com.xiaoxiaobulletscreen.entity.VideoInfo>
     * @author kaciry
     * @description 核心算法，处理返回的数据
     * @date 2019/10/26 14:04
     **/
    public static List<VideoInfo> dataWeightSort(List<VideoInfo> videoInfo, int length) {
        double star, coin, connection, share, playNum, barrage;
        double[] result = new double[videoInfo.size()];
        int[] index = new int[videoInfo.size()];
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
        int n = result.length;
        int i, j, k, gap;
        for (gap = n / 2; gap > 0; gap /= 2) {
            for (i = 0; i < gap; i++) {
                for (j = i + gap; j < n; j += gap) {
                    double temp = result[j];
                    int indexTemp = index[j];
                    for (k = j - gap; k >= 0 && result[k] > temp; k -= gap) {
                        result[k + gap] = result[k];
                        index[k + gap] = index[k];
                    }
                    result[k + gap] = temp;
                    index[k + gap] = indexTemp;
                }
            }
        }
        //System.out.println("result : " + Arrays.toString(result));
        //System.out.println("index : " + Arrays.toString(index));
        int[] array = Arrays.copyOfRange(index, index.length - length, index.length);
        //System.out.println("array" + Arrays.toString(array));
        List<VideoInfo> arrayList = new ArrayList<>();
        for (i = 0; i < array.length; i++) {
            arrayList.add(selectInfo(videoInfo, array[i]));
        }
        //System.out.println("arrayList : " + arrayList);
        Collections.reverse(arrayList);
        return arrayList;
    }

    /**
     * @param videoInfo VideoInfo实体，包含信息见实体类
     * @param index     索引
     * @return com.xiaoxiaobulletscreen.entity.VideoInfo
     * @author kaciry
     * @description 根据index索引值查找videoInfo对应的数据项并返回
     * @date 2019/10/26 14:05
     **/
    private static VideoInfo selectInfo(List<VideoInfo> videoInfo, int index) {
        for (VideoInfo info : videoInfo) {
            if (info.getVideoIdentityDocument() == index) {
                return info;
            }
        }
        return null;
    }

}
