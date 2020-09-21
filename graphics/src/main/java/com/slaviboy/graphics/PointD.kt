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

/**
 * Simple class for representing Point using Double values.
 * @param x x coordinate value of the point
 * @param y y coordinate value of the point
 */
data class PointD(var x: Double = 0.0, var y: Double = 0.0) {

    constructor(pointD: PointD) : this(pointD.x, pointD.y)
    constructor(x: Float, y: Float) : this(x.toDouble(), y.toDouble())
    constructor(x: Int, y: Int) : this(x.toDouble(), y.toDouble())

    /**
     * Set values using double values
     * @param x x value for the point
     * @param y y value for the point
     */
    fun set(x: Double, y: Double) {
        this.x = x
        this.y = y
    }

    /**
     * Set values using PointD object
     * @param pointD PointD object holding the point values
     */
    fun set(pointD: PointD) {
        this.x = pointD.x
        this.y = pointD.y
    }

    /**
     * Set current values to be the opposite of current values
     */
    fun negate() {
        x = -x
        y = -y
    }

    /**
     * Add offset to current values
     * @param dx value to be added to the current x value
     * @param dy value to be added to the current y value
     */
    fun offset(dx: Double, dy: Double) {
        x += dx
        y += dy
    }

    /**
     * Returns true if the point's coordinates equal (x,y)
     * @param x x value of the coordinate to check for equality
     * @param y y value of the coordinate to check for equality
     */
    fun equals(x: Double, y: Double): Boolean {
        return this.x == x && this.y == y
    }


    /**
     * Return the euclidian distance from (0,0) to the point
     */
    fun length(): Double {
        return length(x, y)
    }

    /**
     * Returns the euclidian distance from (0,0) to (x,y)
     */
    fun length(x: Double, y: Double): Double {
        return Math.hypot(x, y)
    }

    override fun toString(): String {
        return "PointD($x, $y)"
    }
}