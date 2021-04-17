package x.chestnut.code.snippet.utils

import android.graphics.Bitmap
import android.graphics.Matrix

/**
 * <pre>
 * author: Chestnut
 * blog  : http://www.jianshu.com/u/a0206b5f4526
 * time  : 2018/6/29 18:08
 * desc  : 封装一些常用的UI套路函数
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
object ViewUtils {

    /**
     * 将旋转应用到矩阵
     * 此旋转是基于Bitmap的中心旋转
     *
     * @param bitmap 位图对象
     * @param rotation 旋转度数
     * @param posX canvas绘制Bitmap的起点
     * @param posY canvas绘制Bitmap的起点
     * @param matrix matrix
     */
    fun applySelfRotationToMatrix(bitmap: Bitmap, rotation: Float, posX: Float, posY: Float, matrix: Matrix) {
        val offsetX = (bitmap.width / 2).toFloat()
        val offsetY = (bitmap.height / 2).toFloat()
        matrix.postTranslate(-offsetX, -offsetY)
        matrix.postRotate(rotation)
        matrix.postTranslate(posX + offsetX, posY + offsetY)
    }

    /**
     * 将缩放应用到矩阵
     * 此缩放是基于Bitmap的中心旋转
     *
     * @param bitmap 位图对象
     * @param scaleX scaleX
     * @param scaleY scaleY
     * @param posX canvas绘制Bitmap的起点
     * @param posY canvas绘制Bitmap的起点
     * @param matrix matrix
     */
    fun applySelfScaleToMatrix(bitmap: Bitmap, scaleX: Float, scaleY: Float, posX: Float, posY: Float, matrix: Matrix) {
        val offsetX = (bitmap.width / 2).toFloat()
        val offsetY = (bitmap.height / 2).toFloat()
        matrix.postTranslate(-offsetX, -offsetY)
        matrix.postScale(scaleX, scaleY)
        matrix.postTranslate(posX + offsetX, posY + offsetY)
    }

    /**
     * 因子映射
     * @param currentFactor 原因子的值
     * @param origStartFactor 原因子的上限
     * @param origEndFactor 原因子的下限
     * @param interpolatorFactors 新因子的插值，从数组0位置为新因子的初始值。
     * @return 新因子的值
     */
    fun factorMapping(currentFactor: Float, origStartFactor: Float, origEndFactor: Float,
                      interpolatorFactors: FloatArray): Float {
        //现在的百分比
        val temp = (currentFactor - origStartFactor) / (origEndFactor - origStartFactor)
        //新的总大小
        var newFactorMax = 0f
        for (i in 1 until interpolatorFactors.size) {
            newFactorMax += Math.abs(interpolatorFactors[i] - interpolatorFactors[i - 1])
        }

        //是否在第一个区间内
        var interpolatorTemp = 0f
        //比较每一个区间
        for (i in 1 until interpolatorFactors.size) {
            val localTemp = Math.abs(interpolatorFactors[i] - interpolatorFactors[i - 1]) / newFactorMax
            interpolatorTemp += if (interpolatorTemp + localTemp >= temp) {
                //所占当前区间百分比
                val tempX = (temp - interpolatorTemp) * newFactorMax / Math.abs(interpolatorFactors[i] - interpolatorFactors[i - 1])
                return tempX * (interpolatorFactors[i] - interpolatorFactors[i - 1]) + interpolatorFactors[i - 1]
            } else {
                localTemp
            }
        }
        return 0F
    }

    /**
     * 因子映射
     * @param currentFactor 原因子的值
     * @param origStartFactor 原因子的上限
     * @param origEndFactor 原因子的下限
     * @param startFactor 新因子的上限
     * @param interpolatorFactor 新因子的插值
     * @param endFactor 新因子的下限
     * @return 新因子的值
     */
    fun factorMapping(currentFactor: Float, origStartFactor: Float, origEndFactor: Float,
                      startFactor: Float, interpolatorFactor: Float, endFactor: Float): Float {
        //现在的百分比
        val temp = (currentFactor - origStartFactor) / (origEndFactor - origStartFactor)
        //新的总大小
        val newFactorMax = Math.abs(startFactor - interpolatorFactor) + Math.abs(endFactor - interpolatorFactor)
        //上限所占的百分比
        val startTemp = Math.abs(startFactor - interpolatorFactor) / newFactorMax
        return if (startTemp < temp) {
            //当前限所占百分比
            val tempX = Math.abs((temp - startTemp) * newFactorMax / Math.abs(endFactor - interpolatorFactor))
            tempX * (endFactor - interpolatorFactor) + interpolatorFactor
        } else {
            //当前限所占百分比
            val tempX = Math.abs(temp * newFactorMax / Math.abs(startFactor - interpolatorFactor))
            tempX * (interpolatorFactor - startFactor) + startFactor
        }
    }

    fun factorMapping(currentFactor: Float, origStartFactor: Float, origEndFactor: Float, startFactor: Float, endFactor: Float): Float {
        return (currentFactor - origStartFactor) * (endFactor - startFactor) / (origEndFactor - origStartFactor) + startFactor
    }

    /**
     * 在平面中，一个点绕任意点旋转 radian 弧度
     * 后的点的坐标
     *
     * @param rX0 旋转基准点x
     * @param rY0 旋转基准点y
     * @param radian 旋转radian
     * @param x 旋转点x
     * @param y 旋转点y
     * @return 旋转的结果，x，y
     */
    fun pointRotateGetX(rX0: Float, rY0: Float, radian: Float, x: Float, y: Float): Float {
        return ((x - rX0) * Math.cos(radian.toDouble()) - (y - rY0) * Math.sin(radian.toDouble()) + rX0).toFloat()
    }

    fun pointRotateGetY(rX0: Float, rY0: Float, radian: Float, x: Float, y: Float): Float {
        return ((x - rX0) * Math.sin(radian.toDouble()) + (y - rY0) * Math.cos(radian.toDouble()) + rY0).toFloat()
    }

    /**
     * 释放Bitmap
     * @param bitmap bitmap
     */
    fun releaseBitmap(bitmap: Bitmap?) {
        var bitmap = bitmap
        if (bitmap != null && !bitmap.isRecycled) bitmap.recycle()
        bitmap = null
    }

    /**
     * 多种Matrix变化组合的时候使用
     */
    class MultipleMatrixSelfChangeBuilder private constructor(private val matrix: Matrix) {
        private var posX = 0f
        private var posY = 0f
        private var offsetX = 0f
        private var offsetY = 0f
        fun start(bitmap: Bitmap, posX: Float, posY: Float): MultipleMatrixSelfChangeBuilder {
            matrix.reset()
            offsetX = (bitmap.width / 2).toFloat()
            offsetY = (bitmap.height / 2).toFloat()
            matrix.postTranslate(-offsetX, -offsetY)
            this.posX = posX
            this.posY = posY
            return this
        }

        fun applySelfRotation(rotation: Float): MultipleMatrixSelfChangeBuilder {
            matrix.postRotate(rotation)
            return this
        }

        fun applySelfScale(scaleX: Float, scaleY: Float): MultipleMatrixSelfChangeBuilder {
            matrix.postScale(scaleX, scaleY)
            return this
        }

        fun end() {
            matrix.postTranslate(posX + offsetX, posY + offsetY)
        }

        companion object {
            operator fun get(matrix: Matrix): MultipleMatrixSelfChangeBuilder {
                return MultipleMatrixSelfChangeBuilder(matrix)
            }
        }
    }
}