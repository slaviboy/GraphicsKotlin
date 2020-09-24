package com.slaviboy.graphics

import android.graphics.Matrix
import android.graphics.RectF
import android.os.Build
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(RobolectricTestRunner::class)
class MatrixDUnitTest {

    @Test
    fun MainTest() {

        // test post transformation methods
        var matrix = MatrixD().apply {
            postTranslate(12.3, 23.4)
            postRotate(55.2)
            postScale(24.1, 66.6)
            postSkew(32.1, 43.32)
            postRotate(92.1, 2.6, 33.2)
            postScale(84.2, 121.4, 77.4, 23.1)
            postSkew(61.23, 51.24, 66.6, 1.3)
        }
        val values = DoubleArray(9)
        matrix.getValues(values)
        assertThat(values).isEqualTo(
            doubleArrayOf(
                1.2905231E7, 9204737.0, 3.74187872E8,
                -2872687.75, 3491834.75, 4.6197344E7,
                0.0, 0.0, 1.0
            )
        )

        // test pre transformation methods
        matrix = MatrixD().apply {
            preTranslate(12.3, 23.4)
            preRotate(55.2)
            preScale(24.1, 66.6)
            preSkew(32.1, 43.32)
            preRotate(92.1, 2.6, 33.2)
            preScale(84.2, 121.4, 77.4, 23.1)
            preSkew(61.23, 51.24, 66.6, 1.3)
        }
        matrix.getValues(values)
        assertThat(values).isEqualTo(
            doubleArrayOf(
                1.4593362E7, 2721940.25, -9.82059648E8,
                -1.0460662E7, 2948736.0, 6.96852352E8,
                0.0, 0.0, 1.0
            )
        )

        // test set transformation methods
        matrix = MatrixD().apply {
            setTranslate(12.3, 23.4)
            setRotate(55.2)
            setScale(24.1, 66.6)
            setSkew(32.1, 43.32)
            setRotate(92.1, 2.6, 33.2)
            setScale(84.2, 121.4, 77.4, 23.1)
            setSkew(61.23, 51.24, 66.6, 1.3)
        }
        matrix.getValues(values)
        assertThat(values).isEqualTo(
            doubleArrayOf(
                1.0, 61.22999954223633, -79.5989990234375,
                51.2400016784668, 1.0, -3412.583984375,
                0.0, 0.0, 1.0
            )
        )

        // test map points directly to the same array
        var point = doubleArrayOf(7.2, 124.2, 921.2, 32.3, 44.3, 231.2, 66.6, 32.1)
        matrix.mapPoints(point)
        assertThat(point).isEqualTo(doubleArrayOf(7532.36669921875, -2919.4560546875, 2819.330078125, 43822.0078125, 14121.0771484375, -911.451904296875, 1952.48388671875, 32.10009765625))

        // test map points to another array
        point = doubleArrayOf(7.2, 124.2, 921.2, 32.3, 44.3, 231.2, 66.6, 32.1)
        val resultPoints = DoubleArray(8)
        matrix.mapPoints(resultPoints, point)
        assertThat(point).isEqualTo(doubleArrayOf(7.2, 124.2, 921.2, 32.3, 44.3, 231.2, 66.6, 32.1))
        assertThat(resultPoints).isEqualTo(doubleArrayOf(7532.36669921875, -2919.4560546875, 2819.330078125, 43822.0078125, 14121.0771484375, -911.451904296875, 1952.48388671875, 32.10009765625))

        // test map vectors directly to the same array
        var vectors = doubleArrayOf(7.2, 124.2, 921.2, 32.3, 44.3, 231.2, 66.6, 32.1)
        matrix.mapVectors(vectors)
        assertThat(vectors).isEqualTo(doubleArrayOf(7611.9658203125, 493.12799072265625, 2898.928955078125, 47234.58984375, 14200.67578125, 2501.132080078125, 2032.0828857421875, 3444.68408203125))

        // test map vectors to another array
        val resultVectors = DoubleArray(8)
        vectors = doubleArrayOf(7.2, 124.2, 921.2, 32.3, 44.3, 231.2, 66.6, 32.1)
        matrix.mapVectors(resultVectors, vectors)
        assertThat(vectors).isEqualTo(doubleArrayOf(7.2, 124.2, 921.2, 32.3, 44.3, 231.2, 66.6, 32.1))
        assertThat(resultVectors).isEqualTo(doubleArrayOf(7611.9658203125, 493.12799072265625, 2898.928955078125, 47234.58984375, 14200.67578125, 2501.132080078125, 2032.0828857421875, 3444.68408203125))

        // test map rectangle directly to the same rectangle object
        var rect = RectD(32.5, 5.1, 141.92, 92.2)
        matrix.mapRect(rect)
        assertThat(rect).isEqualTo(RectD(265.1739807128906, -1742.1839599609375, 5707.7265625, 3951.59716796875))

        // test map rectangle to another rectangle object
        rect = RectD(32.5, 5.1, 141.92, 92.2)
        val resultRect = RectD()
        matrix.mapRect(resultRect, rect)
        assertThat(rect).isEqualTo(RectD(32.5, 5.1, 141.92, 92.2))
        assertThat(resultRect).isEqualTo(RectD(265.1739807128906, -1742.1839599609375, 5707.7265625, 3951.59716796875))

        // test map radius
        val radius = matrix.mapRadius(43.9)
        assertThat(radius).isEqualTo(2459.356689453125)

        // test values for invert matrix
        val invertMatrix = MatrixD()
        matrix.invert(invertMatrix)
        invertMatrix.getValues(values)
        assertThat(values).isEqualTo(doubleArrayOf(-3.1883432529866695E-4, 0.019522225484251976, 66.59585571289062, 0.016337070614099503, -3.1883432529866695E-4, 0.21236561238765717, -0.0, -0.0, 1.0))

        // test reset to identity matrix
        matrix.reset()
        matrix.getValues(values)
        assertThat(values).isEqualTo(doubleArrayOf(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0))
        assertThat(matrix.isIdentity()).isTrue()

        // unfortunately this method cannot be tested because it is not supported
        // by the Robolectric framework but it work in Emulator environment
        /*matrix.setPolyToPoly(
            doubleArrayOf(7.2, 124.2, 921.2, 32.3, 44.3, 231.2, 66.6, 32.1),
            0,
            doubleArrayOf(7532.36669921875, -2919.4560546875, 2819.330078125, 43822.0078125, 14121.0771484375, -911.451904296875, 1952.48388671875, 32.10009765625),
            0,
            4
        )
        matrix.getValues(values)
        assertThat(values).isEqualTo(
            doubleArrayOf(
                1.0, 61.22999954223633, -79.5989990234375,
                51.2400016784668, 1.0, -3412.583984375,
                0.0, 0.0, 1.0
            )
        )*/

        matrix.setRectToRect(
            RectD(0.0, 0.0, 100.0, 100.0),
            RectD(21.817, 0.0, 49.567, 82.068), Matrix.ScaleToFit.CENTER
        )
        matrix.getValues(values)
        assertThat(values).isEqualTo(
            doubleArrayOf(
                0.2775000333786011, 0.0, 21.816999435424805,
                0.0, 0.2775000333786011, 27.158998489379883,
                0.0, 0.0, 1.0
            )
        )
    }
}