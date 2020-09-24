/*
* Copyright (C) 2020 Stanislav Georgiev
* https://github.com/slaviboy
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.slaviboy.graphics

import android.graphics.Matrix
import android.graphics.Matrix.ScaleToFit
import android.graphics.RectF

/**
 * Wrapper class for the android.graphics.Matrix class that holds a 3x3 matrix for transforming coordinates.
 * The class used Double values instead of Float values, for calling the methods, that way its easy to use
 * when you have double values. Initially the Double values are converted to Float values, and back to Double.
 * But the idea of this class is to use Double values instead manually converting them to Float values.
 * @param matrix graphic matrix on which the wrap around is made
 */
data class MatrixD(var matrix: Matrix = Matrix()) {

    constructor(matrix: MatrixD) : this(matrix.matrix)

    // temp values used in the conversion between Float<->Double, that way there is no need to create new object each time
    private val src: RectF = RectF()
    private val dst: RectF = RectF()
    private val values: FloatArray = FloatArray(9)

    fun isIdentity(): Boolean {
        return matrix.isIdentity
    }

    fun isAffine(): Boolean {
        return matrix.isAffine
    }

    fun rectStaysRect(): Boolean {
        return matrix.rectStaysRect()
    }

    fun reset() {
        matrix.reset()
    }

    fun setTranslate(dx: Double, dy: Double) {
        matrix.setTranslate(dx.toFloat(), dy.toFloat())
    }

    fun setScale(sx: Double, sy: Double, px: Double, py: Double) {
        matrix.setScale(sx.toFloat(), sy.toFloat(), px.toFloat(), py.toFloat())
    }

    fun setScale(sx: Double, sy: Double) {
        matrix.setScale(sx.toFloat(), sy.toFloat())
    }

    fun setRotate(degrees: Double, px: Double, py: Double) {
        matrix.setRotate(degrees.toFloat(), px.toFloat(), py.toFloat())
    }

    fun setRotate(degrees: Double) {
        matrix.setRotate(degrees.toFloat())
    }

    fun setSinCos(sinValue: Double, cosValue: Double, px: Double, py: Double) {
        matrix.setSinCos(sinValue.toFloat(), cosValue.toFloat(), px.toFloat(), py.toFloat())
    }

    fun setSinCos(sinValue: Double, cosValue: Double) {
        matrix.setSinCos(sinValue.toFloat(), cosValue.toFloat())
    }

    fun setSkew(kx: Double, ky: Double, px: Double, py: Double) {
        matrix.setSkew(kx.toFloat(), ky.toFloat(), px.toFloat(), py.toFloat())
    }

    fun setSkew(kx: Double, ky: Double) {
        matrix.setSkew(kx.toFloat(), ky.toFloat())
    }

    fun setConcat(a: MatrixD, b: MatrixD): Boolean {
        return matrix.setConcat(a.matrix, b.matrix)
    }

    fun preTranslate(dx: Double, dy: Double): Boolean {
        return matrix.preTranslate(dx.toFloat(), dy.toFloat())
    }

    fun preScale(sx: Double, sy: Double, px: Double, py: Double): Boolean {
        return matrix.preScale(sx.toFloat(), sy.toFloat(), px.toFloat(), py.toFloat())
    }

    fun preScale(sx: Double, sy: Double): Boolean {
        return matrix.preScale(sx.toFloat(), sy.toFloat())
    }

    fun preRotate(degrees: Double, px: Double, py: Double) {
        matrix.preRotate(degrees.toFloat(), px.toFloat(), py.toFloat())
    }

    fun preRotate(degrees: Double) {
        matrix.preRotate(degrees.toFloat())
    }

    fun preSkew(kx: Double, ky: Double, px: Double, py: Double) {
        matrix.preSkew(kx.toFloat(), ky.toFloat(), px.toFloat(), py.toFloat())
    }

    fun preSkew(kx: Double, ky: Double) {
        matrix.preSkew(kx.toFloat(), ky.toFloat())
    }

    fun preConcat(other: MatrixD): Boolean {
        return matrix.preConcat(other.matrix)
    }

    fun postTranslate(dx: Double, dy: Double): Boolean {
        return matrix.postTranslate(dx.toFloat(), dy.toFloat())
    }

    fun postScale(sx: Double, sy: Double, px: Double, py: Double): Boolean {
        return matrix.postScale(sx.toFloat(), sy.toFloat(), px.toFloat(), py.toFloat())
    }

    fun postScale(sx: Double, sy: Double): Boolean {
        return matrix.postScale(sx.toFloat(), sy.toFloat())
    }

    fun postRotate(degrees: Double, px: Double, py: Double) {
        matrix.postRotate(degrees.toFloat(), px.toFloat(), py.toFloat())
    }

    fun postRotate(degrees: Double) {
        matrix.postRotate(degrees.toFloat())
    }

    fun postSkew(kx: Double, ky: Double, px: Double, py: Double) {
        matrix.postSkew(kx.toFloat(), ky.toFloat(), px.toFloat(), py.toFloat())
    }

    fun postSkew(kx: Double, ky: Double) {
        matrix.postSkew(kx.toFloat(), ky.toFloat())
    }

    fun postConcat(other: MatrixD): Boolean {
        return matrix.postConcat(other.matrix)
    }

    fun setRectToRect(src: RectD, dst: RectD, stf: ScaleToFit): Boolean {
        transferRectDtoRectF(src, this.src)
        transferRectDtoRectF(dst, this.dst)
        return matrix.setRectToRect(this.src, this.dst, stf)
    }

    fun setPolyToPoly(src: DoubleArray, srcIndex: Int, dst: DoubleArray, dstIndex: Int, pointCount: Int): Boolean {
        return matrix.setPolyToPoly(
            FloatArray(src.size) {
                src[it].toFloat()
            },
            srcIndex,
            FloatArray(dst.size) {
                dst[it].toFloat()
            },
            dstIndex,
            pointCount
        )
    }

    fun invert(inverse: MatrixD): Boolean {
        return matrix.invert(inverse.matrix)
    }

    fun mapPoints(dst: DoubleArray, dstIndex: Int, src: DoubleArray, srcIndex: Int, pointCount: Int) {

        val dstFloat = FloatArray(dst.size)
        matrix.mapPoints(
            dstFloat,
            dstIndex,
            FloatArray(src.size) {
                src[it].toFloat()
            },
            srcIndex,
            pointCount
        )

        // transfer values
        dstFloat.forEachIndexed { index, fl ->
            dst[index] = fl.toDouble()
        }
    }

    fun mapVectors(dst: DoubleArray, dstIndex: Int, src: DoubleArray, srcIndex: Int, vectorCount: Int) {

        val dstFloat = FloatArray(dst.size)
        matrix.mapVectors(
            dstFloat,
            dstIndex,
            FloatArray(src.size) {
                src[it].toFloat()
            },
            srcIndex,
            vectorCount
        )

        // transfer values
        dstFloat.forEachIndexed { index, fl ->
            dst[index] = fl.toDouble()
        }
    }

    fun mapPoints(dst: DoubleArray, src: DoubleArray) {
        mapPoints(dst, 0, src, 0, dst.size / 2)
    }

    fun mapVectors(dst: DoubleArray, src: DoubleArray) {
        mapVectors(dst, 0, src, 0, dst.size / 2)
    }

    fun mapPoints(pts: DoubleArray) {
        mapPoints(pts, 0, pts, 0, pts.size / 2)
    }

    fun mapVectors(vecs: DoubleArray) {
        mapVectors(vecs, 0, vecs, 0, vecs.size / 2)
    }

    fun mapRect(dst: RectD, src: RectD): Boolean {

        transferRectDtoRectF(dst, this.dst)
        transferRectDtoRectF(src, this.src)
        val returnValue = matrix.mapRect(
            this.dst,
            this.src
        )

        transferRectFtoRectD(this.dst, dst)
        return returnValue
    }

    fun mapRect(rect: RectD): Boolean {
        return mapRect(rect, rect)
    }

    fun mapRadius(radius: Double): Double {
        return matrix.mapRadius(radius.toFloat()).toDouble()
    }

    fun getValues(values: DoubleArray) {
        matrix.getValues(this.values)

        this.values.forEachIndexed { index, fl ->
            values[index] = fl.toDouble()
        }
    }

    fun setValues(values: DoubleArray) {
        values.forEachIndexed { index, fl ->
            this.values[index] = fl.toFloat()
        }
        matrix.setValues(this.values)
    }

    override fun toString(): String {
        return matrix.toString()
    }

    fun toShortString(): String {
        return matrix.toShortString()
    }

    /**
     * Transfer values from RectD to RectF
     */
    internal fun transferRectDtoRectF(rectD: RectD, rectF: RectF) {
        rectF.top = rectD.top.toFloat()
        rectF.left = rectD.left.toFloat()
        rectF.bottom = rectD.bottom.toFloat()
        rectF.right = rectD.right.toFloat()
    }

    /**
     * Transfer values from RectD to RectF
     */
    internal fun transferRectFtoRectD(rectF: RectF, rectD: RectD) {
        rectD.top = rectF.top.toDouble()
        rectD.left = rectF.left.toDouble()
        rectD.bottom = rectF.bottom.toDouble()
        rectD.right = rectF.right.toDouble()
    }

    companion object {
        const val MSCALE_X = 0  // use with getValues/setValues
        const val MSKEW_X = 1   // use with getValues/setValues
        const val MTRANS_X = 2  // use with getValues/setValues
        const val MSKEW_Y = 3   // use with getValues/setValues
        const val MSCALE_Y = 4  // use with getValues/setValues
        const val MTRANS_Y = 5  // use with getValues/setValues
        const val MPERSP_0 = 6  // use with getValues/setValues
        const val MPERSP_1 = 7  // use with getValues/setValues
        const val MPERSP_2 = 8  // use with getValues/setValues
    }
}