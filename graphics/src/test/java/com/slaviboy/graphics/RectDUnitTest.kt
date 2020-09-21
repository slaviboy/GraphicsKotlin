package com.slaviboy.graphics

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RectDUnitTest {

    @Test
    fun MainTest() {

        // create rectangle using default values
        var rect = RectD()
        assertThat(rect.left).isEqualTo(0.0)
        assertThat(rect.top).isEqualTo(0.0)
        assertThat(rect.right).isEqualTo(0.0)
        assertThat(rect.bottom).isEqualTo(0.0)

        rect = RectD(242.32, 552.2, 331.2, 135.3)
        assertThat(rect.left).isEqualTo(242.32)
        assertThat(rect.top).isEqualTo(552.2)
        assertThat(rect.right).isEqualTo(331.2)
        assertThat(rect.bottom).isEqualTo(135.3)

        // set rectangle values using RectD object
        rect.set(RectD(13.1, 54.6, 22.9, 66.6))
        assertThat(rect).isEqualTo(RectD(13.1, 54.6, 22.9, 66.6))

        // set rect values using separate values
        rect.set(100.0, 100.0, 200.0, 200.0)
        assertThat(rect).isEqualTo(RectD(100.0, 100.0, 200.0, 200.0))

        // get center values of the rectangle
        val centerX = rect.centerX()
        val centerY = rect.centerY()
        val center = rect.center()
        assertThat(centerX).isEqualTo(150.0)
        assertThat(centerY).isEqualTo(150.0)
        assertThat(center).isEqualTo(PointD(150.0, 150.0))

        // check if rectangle with given coordinate is inside current rectangle
        var isRectInside = rect.contains(12.4, 24.1, 551.2, 141.2)
        assertThat(isRectInside).isFalse()
        isRectInside = rect.contains(105.0, 105.0, 195.0, 141.2)
        assertThat(isRectInside).isTrue()

        // check if rectangle with given coordinates as RectD object is inside current rectangle
        isRectInside = rect.contains(RectD(12.4, 24.1, 551.2, 141.2))
        assertThat(isRectInside).isFalse()
        isRectInside = rect.contains(RectD(105.0, 105.0, 195.0, 141.2))
        assertThat(isRectInside).isTrue()

        // set values to empty -0.0
        rect.setEmpty()
        assertThat(rect).isEqualTo(RectD())

        // add offset to current rectangle values
        rect.offset(12.4, 552.2)
        assertThat(rect).isEqualTo(RectD(12.4, 552.2, 12.4, 552.2))

        // add offset to current rectangle values
        rect.offset(10.2, 83.32, 44.3, 31.2)
        assertThat(rect).isEqualTo(RectD(22.6, 635.52, 56.699999999999996, 583.4000000000001))

        // set offset to
        rect.offsetTo(100.0, 100.0)
        assertThat(rect).isEqualTo(RectD(100.0, 100.0, 134.1, 47.88000000000011))

        rect.inset(32.2, 35.1)
        assertThat(rect).isEqualTo(RectD(132.2, 135.1, 101.89999999999999, 12.780000000000108))

        // test scaling the rectangle
        rect.scale(10.0)
        assertThat(rect).isEqualTo(RectD(1322.0, 1351.0, 1018.9999999999999, 127.80000000000108))

        val stringValue = rect.toString()
        assertThat(stringValue).isEqualTo("RectD(1322.0,1351.0,1018.9999999999999,127.80000000000108)")

        // check if rectangle is impossible (left >= right or top >= bottom)
        rect = RectD(109.2, 411.1, 21.4, 221.9)
        val isEmpty = rect.isEmpty()
        assertThat(isEmpty).isTrue()

        // sort by swapping values
        rect.sort()
        assertThat(rect).isEqualTo(RectD(21.4, 221.9, 109.2, 411.1))

        // test union with new x,y coordinates
        rect.union(10.0, 600.0)
        assertThat(rect).isEqualTo(RectD(10.0, 221.9, 109.2, 600.0))
        rect.union(300.0, 100.0)
        assertThat(rect).isEqualTo(RectD(10.0, 100.0, 300.0, 600.0))

        rect = RectD(90.0, 50.0, 230.9, 321.9)
        rect.union(10.0, 20.0, 300.0, 600.0)
        assertThat(rect).isEqualTo(RectD(10.0, 20.0, 300.0, 600.0))

        // get width and height of the rectangle
        val width = rect.width()
        val height = rect.height()
        assertThat(width).isEqualTo(290.0)
        assertThat(height).isEqualTo(580.0)

        rect = RectD(100.0, 100.0, 200.0, 200.0)
        var intersect = rect.intersect(50.0, 50.0, 150.0, 150.0)
        assertThat(intersect).isTrue()
        intersect = rect.intersect(RectD(50.0, 50.0, 99.0, 150.0))
        assertThat(intersect).isFalse()
    }
}