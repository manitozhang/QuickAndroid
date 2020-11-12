package com.common.http.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import com.common.constants.Constants;
import com.common.util.BitmapUtil;

import java.io.File;

/**
 * @Author: 张
 * @Email: 1271396448@qq.com
 * @Date: 2019/7/22 2:51 PM
 * <p>
 * 图片压缩辅助类
 */

/**
 * 使用
 * File newFile=CompressHelper.getDefault(this).compressToFile(oldFile);
 * <p>
 * File newFile=new CompressHelper.Builder(this)
 * .setMaxWidth(720)  // 默认最大宽度为720
 * .setMaxHeight(960) // 默认最大高度为960
 * .setQuality(80)    // 默认压缩质量为80
 * .setFileName(yourFileName) // 设置你需要修改的文件名
 * .setCompressFormat(CompressFormat.JPEG) // 设置默认压缩为jpg格式
 * .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
 * Environment.DIRECTORY_PICTURES).getAbsolutePath())//存储路径
 * .build()
 * .compressToFile(oldFile);
 */


public class CompressHelper {

    private static volatile CompressHelper INSTANCE;

    private Context context;
    /**
     * 最大宽度，默认为720
     */
    private float maxWidth = 720.0f;
    /**
     * 最大高度,默认为960
     */
    private float maxHeight = 960.0f;
    /**
     * 默认压缩后的方式为JPEG
     */
    private Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.JPEG;

    /**
     * 默认的图片处理方式是ARGB_8888
     */
    private Bitmap.Config bitmapConfig = Bitmap.Config.ARGB_8888;
    /**
     * 默认压缩质量为80
     */
    private int quality = 80;
    /**
     * 存储路径
     */
    private String destinationDirectoryPath;
    /**
     * 文件名前缀
     */
    private String fileNamePrefix;
    /**
     * 文件名
     */
    private String fileName;

    public static CompressHelper getDefault(Context context) {
        if (INSTANCE == null) {
            synchronized (CompressHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CompressHelper(context.getApplicationContext());
                }
            }
        }
        return INSTANCE;
    }

    private CompressHelper(Context context) {
        this.context = context.getApplicationContext();
        destinationDirectoryPath = BitmapUtil.getSDPath() + Constants.SD_ROOT_DIR;
    }

    /**
     * 压缩成文件
     *
     * @param file 原始文件
     * @return 压缩后的文件
     */
    public File compressToFile(File file) {
        return BitmapUtil.compressImage(context, Uri.fromFile(file), maxWidth, maxHeight,
                compressFormat, bitmapConfig, quality, destinationDirectoryPath,
                fileNamePrefix, fileName);
    }

    /**
     * 压缩为Bitmap
     *
     * @param file 原始文件
     * @return 压缩后的Bitmap
     */
    public Bitmap compressToBitmap(File file) {
        return BitmapUtil.getScaledBitmap(context, Uri.fromFile(file), maxWidth, maxHeight, bitmapConfig);
    }


    /**
     * 采用建造者模式，设置Builder
     */
    public static class Builder {
        private CompressHelper mCompressHelper;

        public Builder(Context context) {
            mCompressHelper = new CompressHelper(context.getApplicationContext());
        }

        /**
         * 设置图片最大宽度
         *
         * @param maxWidth 最大宽度
         */
        public Builder setMaxWidth(float maxWidth) {
            mCompressHelper.maxWidth = maxWidth;
            return this;
        }

        /**
         * 设置图片最大高度
         *
         * @param maxHeight 最大高度
         */
        public Builder setMaxHeight(float maxHeight) {
            mCompressHelper.maxHeight = maxHeight;
            return this;
        }

        /**
         * 设置压缩的后缀格式
         */
        public Builder setCompressFormat(Bitmap.CompressFormat compressFormat) {
            mCompressHelper.compressFormat = compressFormat;
            return this;
        }

        /**
         * 设置Bitmap的参数
         */
        public Builder setBitmapConfig(Bitmap.Config bitmapConfig) {
            mCompressHelper.bitmapConfig = bitmapConfig;
            return this;
        }

        /**
         * 设置压缩质量，建议80
         *
         * @param quality 压缩质量，[0,100]
         */
        public Builder setQuality(int quality) {
            mCompressHelper.quality = quality;
            return this;
        }

        /**
         * 设置目的存储路径
         *
         * @param destinationDirectoryPath 目的路径
         */
        public Builder setDestinationDirectoryPath(String destinationDirectoryPath) {
            mCompressHelper.destinationDirectoryPath = destinationDirectoryPath;
            return this;
        }

        /**
         * 设置文件前缀
         *
         * @param prefix 前缀
         */
        public Builder setFileNamePrefix(String prefix) {
            mCompressHelper.fileNamePrefix = prefix;
            return this;
        }

        /**
         * 设置文件名称
         *
         * @param fileName 文件名
         */
        public Builder setFileName(String fileName) {
            mCompressHelper.fileName = fileName;
            return this;
        }

        public CompressHelper build() {
            return mCompressHelper;
        }
    }

}
