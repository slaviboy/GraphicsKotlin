package com.slaviboy.graphics

import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * Simple unit test for PointD class
 */
class PointDUnitTest {

    @Test
    fun MainTest() {

        // create point using default values
        var point: PointD = PointD()
        assertThat(point.x).isEqualTo(0.0)
        assertThat(point.y).isEqualTo(0.0)

        point = PointD(13.2, 52.21)
        assertThat(point.x).isEqualTo(13.2)
        assertThat(point.y).isEqualTo(52.21)
        assertThat(point.toString()).isEqualTo("PointD(13.2, 52.21)")

        // opposite sign for values
        point.negate()
        assertThat(point).isEqualTo(PointD(-13.2, -52.21))

        // add offset to current values
        point.offset(92.5, 41.8)
        assertThat(point).isEqualTo(PointD(79.3, -10.410000000000004))

        // set values using PointD object
        point.set(PointD(11.29, 332.45))
        assertThat(point).isEqualTo(PointD(11.29, 332.45))

        // set values using double values
        point.set(32.92, 52.1)
        assertThat(point).isEqualTo(PointD(32.92, 52.1))

        val length = point.length()
        assertThat(length).isEqualTo(61.62902238393856)
    }

}
