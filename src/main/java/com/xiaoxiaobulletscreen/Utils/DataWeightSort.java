package com.xiaoxiaobulletscreen.Utils;

import com.xiaoxiaobulletscreen.entity.VideoInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataWeightSort {
    //处理返回的数据
    public static List<VideoInfo> dataWeightSort(List<VideoInfo> videoInfo,int length) {
        double Star, Coin, Connection, Share, PlayNum, Barrage;
        double[] result = new double[videoInfo.size()];
        int[] index = new int[videoInfo.size()];
        for (int i = 0; i < videoInfo.size(); i++) {
            //权重
            Star = videoInfo.get(i).getVideoStars() * 0.15;
            Coin = videoInfo.get(i).getVideoCoins() * 0.15;
            Connection = videoInfo.get(i).getVideoConnections() * 0.10;
            Share = videoInfo.get(i).getVideoShares() * 0.05;
            PlayNum = videoInfo.get(i).getVideoPlayNum() * 0.3;
            Barrage = videoInfo.get(i).getVideoBarrages() * 0.25;
            //计算当前i的加权平均数,并保存他的videoIndex,索引对应
            result[i] = Star + Coin + Connection + Share + PlayNum + Barrage;
            index[i] = videoInfo.get(i).getVideoID();
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
//        System.out.println("result : " + Arrays.toString(result));
//        System.out.println("index : " + Arrays.toString(index));
        int[] array = Arrays.copyOfRange(index, index.length - length, index.length);
//        System.out.println("array" + Arrays.toString(array));
        List<VideoInfo> arrayList = new ArrayList<>();
        for (i = 0; i < array.length; i++) {
            arrayList.add(selectInfo(videoInfo, array[i]));
        }
//        System.out.println("arrayList : " + arrayList);
        Collections.reverse(arrayList);
        return arrayList;
    }
    //根据index索引值查找videoInfo对应的数据项并返回
    private static VideoInfo selectInfo(List<VideoInfo> videoInfo, int index) {
        for (VideoInfo info : videoInfo) {
            if (info.getVideoID() == index) {
                return info;
            }
        }
        return null;
    }

}
