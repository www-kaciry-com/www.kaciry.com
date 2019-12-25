package com.kaciry.utils;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author kaciry
 * @date 2019/11/10 13:04
 * @description 处理图片的属性，文件操作
 */
public class ManageFilesUtil {
    /**
     * @param oldFilePath 源文件
     * @param newFilePath 新文件
     * @param width       宽度,int类型
     * @param height      高度，int类型
     * @author kaciry
     * @description 按尺寸压缩图片大小并将格式转换为jpg, 不输入大小则使用重载方法，默认为450*350
     * @date 2019/11/10 13:34
     **/
    public void compressWithDimension(String oldFilePath, String newFilePath, int width, int height) throws IOException {
        Thumbnails.of(oldFilePath).size(width, height).keepAspectRatio(false).outputFormat("jpg").toFile(newFilePath);
    }

    void compressWithDimension(String oldFilePath, String newFilePath) throws IOException {
        Thumbnails.of(oldFilePath).size(450, 350).keepAspectRatio(false).toFile(newFilePath);
    }

    /**
     * @param oldFilePath 源文件
     * @param newFilePath 新文件
     * @param var         按比例缩放，0~1缩小，1原比例，>1放大
     * @author kaciry
     * @description 缩放图片
     * @date 2019/11/10 13:34
     **/
    public void compressWithScale(String oldFilePath, String newFilePath, double var) throws IOException {
        Thumbnails.of(oldFilePath).scale(var).toFile(newFilePath);
    }

    /**
     * @param oldFilePath 源文件
     * @param newFilePath 新文件
     * @param width       宽度，int类型
     * @param height      高度，int类型
     * @param deg         旋转角度，int类型
     * @author kaciry
     * @description 旋转图片
     * @date 2019/11/10 13:35
     **/
    public void compressWithRotate(String oldFilePath, String newFilePath, int width, int height, int deg) throws IOException {
        Thumbnails.of(oldFilePath).size(width, height).rotate(deg).toFile(newFilePath);
    }

    /**
     * @param oldFilePath 源文件
     * @param newFilePath 新文件
     * @param width       宽度，int类型
     * @param height      高度，int类型
     * @param wishFormat  目标格式，String类型
     * @author kaciry
     * @description 转换图片的格式，支持的文件格式有：[JPG, jpg, bmp, BMP, gif, GIF, WBMP, png, PNG, wbmp, jpeg, JPEG]
     * @date 2019/11/10 13:36
     **/
    public void changeFileFormat(String oldFilePath, String newFilePath, int width, int height, String wishFormat) throws IOException {
        Thumbnails.of(oldFilePath).size(width, height).outputFormat(wishFormat).toFile(newFilePath);
    }

    /**
     * @param path 文件路径
     * @return boolean
     * @author kaciry
     * @description 删除文件
     * @date 2019/11/10 13:46
     **/
    public boolean deleteOriginFile(String path) {
        try {
            FileUtils.forceDelete(new File(path));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
