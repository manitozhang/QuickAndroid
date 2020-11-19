package com.common.util

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import java.io.*
import kotlin.jvm.Throws

/**
 * @Author: 张
 * @Email: 1271396448@qq.com
 * @Date: 2019/7/22 2:53 PM
 *
 * 文件操作工具类
 */
object FileUtil {
    private const val EOF = -1
    private const val DEFAULT_BUFFER_SIZE = 1024 * 4
    /**
     * 根据文件路径获取文件
     *
     * @param filePath 文件路径
     * @return 文件
     */
    fun getFileByPath(filePath: String?): File? {
        return if (ParamUtil.isBlank(filePath)) null else File(filePath)
    }

    /**
     * 判断文件是否存在
     *
     * @param filePath 文件路径
     * @return `true`: 存在<br></br>`false`: 不存在
     */
    fun isFileExists(filePath: String?): Boolean {
        return isFileExists(getFileByPath(filePath))
    }

    /**
     * 判断文件是否存在
     *
     * @param file 文件
     * @return `true`: 存在<br></br>`false`: 不存在
     */
    fun isFileExists(file: File?): Boolean {
        return file != null && file.exists()
    }

    /**
     * 重命名文件
     *
     * @param filePath 文件路径
     * @param newName  新名称
     * @return `true`: 重命名成功<br></br>`false`: 重命名失败
     */
    fun rename(filePath: String?, newName: String): Boolean {
        return rename(getFileByPath(filePath), newName)
    }

    /**
     * 重命名文件
     *
     * @param file    文件
     * @param newName 新名称
     * @return `true`: 重命名成功<br></br>`false`: 重命名失败
     */
    fun rename(file: File?, newName: String): Boolean { // 文件为空返回false
        if (file == null) return false
        // 文件不存在返回false
        if (!file.exists()) return false
        // 新的文件名为空返回false
        if (ParamUtil.isBlank(newName)) return false
        // 如果文件名没有改变返回true
        if (newName == file.name) return true
        val newFile = File(file.parent + File.separator + newName)
        // 如果重命名的文件已存在返回false
        return (!newFile.exists()
                && file.renameTo(newFile))
    }

    /**
     * 判断是否是目录
     *
     * @param dirPath 目录路径
     * @return `true`: 是<br></br>`false`: 否
     */
    fun isDir(dirPath: String?): Boolean {
        return isDir(getFileByPath(dirPath))
    }

    /**
     * 判断是否是目录
     *
     * @param file 文件
     * @return `true`: 是<br></br>`false`: 否
     */
    fun isDir(file: File?): Boolean {
        return isFileExists(file) && file!!.isDirectory
    }

    /**
     * 判断是否是文件
     *
     * @param filePath 文件路径
     * @return `true`: 是<br></br>`false`: 否
     */
    fun isFile(filePath: String?): Boolean {
        return isFile(getFileByPath(filePath))
    }

    /**
     * 判断是否是文件
     *
     * @param file 文件
     * @return `true`: 是<br></br>`false`: 否
     */
    fun isFile(file: File?): Boolean {
        return isFileExists(file) && file!!.isFile
    }

    /**
     * 重命名文件
     * @param file      文件
     * @param newName   新名字
     * @return          新文件
     */
    fun renameFile(file: File, newName: String?): File {
        val newFile = File(file.parent, newName)
        if (newFile != file) {
            if (newFile.exists()) {
                if (newFile.delete()) {
                    Log.d("FileUtil", "Delete old $newName file")
                }
            }
            if (file.renameTo(newFile)) {
                Log.d("FileUtil", "Rename file to $newName")
            }
        }
        return newFile
    }

    /**
     * 获取临时文件
     * @param context   上下文
     * @param uri       url
     * @return          临时文件
     * @throws IOException
     */
    @Throws(IOException::class)
    fun getTempFile(context: Context, uri: Uri): File {
        val inputStream = context.contentResolver.openInputStream(uri)
        val fileName = getFileName(context, uri)
        val splitName = splitFileName(fileName)
        var tempFile = File.createTempFile(splitName[0], splitName[1])
        tempFile = renameFile(tempFile, fileName)
        tempFile.deleteOnExit()
        var out: FileOutputStream? = null
        try {
            out = FileOutputStream(tempFile)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        if (inputStream != null) {
            copy(inputStream, out)
            inputStream.close()
        }
        out?.close()
        return tempFile
    }

    /**
     * 截取文件名称
     * @param fileName  文件名称
     */
    fun splitFileName(fileName: String?): Array<String?> {
        var name = fileName
        var extension: String? = ""
        val i = fileName!!.lastIndexOf(".")
        if (i != -1) {
            name = fileName.substring(0, i)
            extension = fileName.substring(i)
        }
        return arrayOf(name, extension)
    }

    /**
     * 获取文件名称
     * @param context   上下文
     * @param uri       uri
     * @return          文件名称
     */
    fun getFileName(context: Context, uri: Uri): String? {
        var result: String? = null
        if (uri.scheme == "content") {
            val cursor = context.contentResolver.query(uri, null, null, null, null)
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                cursor?.close()
            }
        }
        if (result == null) {
            result = uri.path
            val cut = result!!.lastIndexOf(File.separator)
            if (cut != -1) {
                result = result.substring(cut + 1)
            }
        }
        return result
    }

    /**
     * 获取真实的路径
     * @param context   上下文
     * @param uri       uri
     * @return          文件路径
     */
    fun getRealPathFromURI(context: Context, uri: Uri): String? {
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        return if (cursor == null) {
            uri.path
        } else {
            cursor.moveToFirst()
            val index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            val realPath = cursor.getString(index)
            cursor.close()
            realPath
        }
    }

    @Throws(IOException::class)
    fun copy(input: InputStream, output: OutputStream?): Int {
        val count = copyLarge(input, output)
        return if (count > Int.MAX_VALUE) {
            -1
        } else count.toInt()
    }

    @JvmOverloads
    @Throws(IOException::class)
    fun copyLarge(input: InputStream, output: OutputStream?, buffer: ByteArray? = ByteArray(DEFAULT_BUFFER_SIZE)): Long {
        var count: Long = 0
        var n: Int
        while (EOF != input.read(buffer).also { n = it }) {
            output!!.write(buffer, 0, n)
            count += n.toLong()
        }
        return count
    }
}