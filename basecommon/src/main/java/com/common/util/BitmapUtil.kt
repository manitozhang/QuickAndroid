package com.common.util

import android.content.Context
import android.content.Intent
import android.graphics.*
import android.graphics.Bitmap.CompressFormat
import android.media.ExifInterface
import android.net.Uri
import android.os.Environment
import android.text.TextUtils
import com.blankj.utilcode.util.Utils
import com.common.constants.Constants
import java.io.*

/**
 * @Author: 张
 * @Email: 1271396448@qq.com
 * @Date: 2018/11/6
 *
 *
 * 图片工具类
 * 压缩 bitmap与file的互转 获取图片尺寸
 */
object BitmapUtil {
    fun getScaledBitmap(context: Context?, imageUri: Uri, maxWidth: Float, maxHeight: Float, bitmapConfig: Bitmap.Config?): Bitmap? {
        val filePath = context?.let { FileUtil.getRealPathFromURI(it, imageUri) }
        var scaledBitmap: Bitmap? = null
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        var bmp = BitmapFactory.decodeFile(filePath, options)
        if (bmp == null) {
            var inputStream: InputStream? = null
            try {
                inputStream = FileInputStream(filePath)
                BitmapFactory.decodeStream(inputStream, null, options)
                inputStream.close()
            } catch (exception: FileNotFoundException) {
                exception.printStackTrace()
            } catch (exception: IOException) {
                exception.printStackTrace()
            }
        }
        var actualHeight = options.outHeight
        var actualWidth = options.outWidth
        if (actualHeight == -1 || actualWidth == -1) {
            try {
                val exifInterface = ExifInterface(filePath)
                actualHeight = exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_LENGTH, ExifInterface.ORIENTATION_NORMAL) //获取图片的高度
                actualWidth = exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH, ExifInterface.ORIENTATION_NORMAL) //获取图片的宽度
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        if (actualWidth <= 0 || actualHeight <= 0) {
            val bitmap2 = BitmapFactory.decodeFile(filePath)
            if (bitmap2 != null) {
                actualWidth = bitmap2.width
                actualHeight = bitmap2.height
            } else {
                return null
            }
        }
        var imgRatio = actualWidth.toFloat() / actualHeight
        val maxRatio = maxWidth / maxHeight
        //width and height values are set maintaining the aspect ratio of the image
        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight
                actualWidth = (imgRatio * actualWidth) as Int
                actualHeight = maxHeight.toInt()
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth
                actualHeight = (imgRatio * actualHeight).toInt()
                actualWidth = maxWidth.toInt()
            } else {
                actualHeight = maxHeight.toInt()
                actualWidth = maxWidth.toInt()
            }
        }
        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight)
        options.inJustDecodeBounds = false
        options.inPurgeable = true
        options.inInputShareable = true
        options.inTempStorage = ByteArray(16 * 1024)
        try {
            bmp = BitmapFactory.decodeFile(filePath, options)
            if (bmp == null) {
                var inputStream: InputStream? = null
                try {
                    inputStream = FileInputStream(filePath)
                    BitmapFactory.decodeStream(inputStream, null, options)
                    inputStream.close()
                } catch (exception: IOException) {
                    exception.printStackTrace()
                }
            }
        } catch (exception: OutOfMemoryError) {
            exception.printStackTrace()
        }
        if (actualHeight <= 0 || actualWidth <= 0) {
            return null
        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, bitmapConfig!!)
        } catch (exception: OutOfMemoryError) {
            exception.printStackTrace()
        }
        val ratioX = actualWidth / options.outWidth.toFloat()
        val ratioY = actualHeight / options.outHeight.toFloat()
        val scaleMatrix = Matrix()
        scaleMatrix.setScale(ratioX, ratioY, 0f, 0f)
        val canvas = Canvas(scaledBitmap!!)
        canvas.setMatrix(scaleMatrix)
        canvas.drawBitmap(bmp!!, 0f, 0f, Paint(Paint.FILTER_BITMAP_FLAG))
        // 采用 ExitInterface 设置图片旋转方向
        val exif: ExifInterface
        try {
            exif = ExifInterface(filePath)
            val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0)
            val matrix = Matrix()
            if (orientation == 6) {
                matrix.postRotate(90f)
            } else if (orientation == 3) {
                matrix.postRotate(180f)
            } else if (orientation == 8) {
                matrix.postRotate(270f)
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                    scaledBitmap.width, scaledBitmap.height,
                    matrix, true)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return scaledBitmap
    }

    fun compressImage(context: Context, imageUri: Uri, maxWidth: Float, maxHeight: Float,
                      compressFormat: CompressFormat, bitmapConfig: Bitmap.Config?,
                      quality: Int, parentPath: String, prefix: String, fileName: String): File {
        var out: FileOutputStream? = null
        val filename = generateFilePath(context, parentPath, imageUri, compressFormat.name.toLowerCase(), prefix, fileName)
        try {
            out = FileOutputStream(filename)
            // 通过文件名写入
            val newBmp = getScaledBitmap(context, imageUri, maxWidth, maxHeight, bitmapConfig)
            newBmp?.compress(compressFormat, quality, out)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } finally {
            try {
                out?.close()
            } catch (ignored: IOException) {
            }
        }
        return File(filename)
    }

    private fun generateFilePath(context: Context, parentPath: String, uri: Uri,
                                 extension: String, prefix: String, fileName: String): String {
        var prefix = prefix
        var fileName = fileName
        val file = File(parentPath)
        if (!file.exists()) {
            file.mkdirs()
        }
        /** if prefix is null, set prefix ""  */
        prefix = if (TextUtils.isEmpty(prefix)) "" else prefix
        /** reset fileName by prefix and custom file name  */
        fileName = if (TextUtils.isEmpty(fileName)) prefix + FileUtil.splitFileName(FileUtil.getFileName(context, uri))[0] else fileName
        return file.absolutePath + File.separator + fileName + "." + extension
    }

    /**
     * 计算inSampleSize
     */
    private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {
            val heightRatio = Math.round(height.toFloat() / reqHeight.toFloat())
            val widthRatio = Math.round(width.toFloat() / reqWidth.toFloat())
            inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
        }
        val totalPixels = width * height.toFloat()
        val totalReqPixelsCap = reqWidth * reqHeight * 2.toFloat()
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++
        }
        return inSampleSize
    }// 获取跟目录// 判断sd卡是否存在

    /**
     * 获取sd卡路径
     *
     * @return
     */
    val sDPath: String
        get() {
            var sdDir: File? = null
            val sdCardExist = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED // 判断sd卡是否存在
            sdDir = if (sdCardExist) {
                Environment.getExternalStorageDirectory() // 获取跟目录
            } else {
                Utils.getApp().filesDir
            }
            return sdDir.toString()
        }

    /**
     * 解决图片太大转bitmap对象会出问题
     *
     * @param inSampleSize
     * @return
     */
    fun getBitmapOption(inSampleSize: Int): BitmapFactory.Options {
        System.gc()
        val options = BitmapFactory.Options()
        options.inPurgeable = true
        options.inSampleSize = inSampleSize
        return options
    }

    //删除文件操作
    @JvmStatic
    fun deleteImage(context: Context, file: File?) {
        if (file == null) {
            return
        }
        val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        val uri = Uri.fromFile(file)
        intent.data = uri
        context.sendBroadcast(intent)
        file.delete()
    }

    @JvmStatic
    val fileStoragePath: String
        get() = sDPath + Constants.SD_ROOT_DIR
}