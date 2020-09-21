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
 * Simple class for representing Rectangle using Double values.
 * @param left left value of the rectangle
 * @param top top value of the rectangle
 * @param right right value of the rectangle
 * @param bottom bottom value of the rectangle
 */
data class RectD(var left: Double = 0.0, var top: Double = 0.0, var right: Double = 0.0, var bottom: Double = 0.0) {

    constructor(rectD: RectD) : this(rectD.left, rectD.top, rectD.right, rectD.bottom)
    constructor(left: Float, top: Float, right: Float, bottom: Float) : this(left.toDouble(), top.toDouble(), right.toDouble(), bottom.toDouble())
    constructor(left: Int, top: Int, right: Int, bottom: Int) : this(left.toDouble(), top.toDouble(), right.toDouble(), bottom.toDouble())

    /**
     * Returns true if the rectangle is empty (left >= right or top >= bottom)
     */
    fun isEmpty(): Boolean {
        return left >= right || top >= bottom
    }

    /**
     * @return the rectangle's width. This does not check for a valid rectangle
     * (i.e. left <= right) so the result may be negative.
     */
    fun width(): Double {
        return right - left
    }

    /**
     * @return the rectangle's height. This does not check for a valid rectangle
     * (i.e. top <= bottom) so the result may be negative.
     */
    fun height(): Double {
        return bottom - top
    }

    /**
     * @return the horizontal center of the rectangle. This does not check for
     * a valid rectangle (i.e. left <= right)
     */
    fun centerX(): Double {
        return (left + right) * 0.5
    }

    /**
     * @return the vertical center of the rectangle. This does not check for
     * a valid rectangle (i.e. top <= bottom)
     */
    fun centerY(): Double {
        return (top + bottom) * 0.5
    }

    fun center(pointD: PointD? = null): PointD {
        val x = (left + right) * 0.5
        val y = (top + bottom) * 0.5
        return if (pointD != null) {
            pointD.x = x
            pointD.y = y
            pointD
        } else {
            PointD(x, y)
        }
    }

    /**
     * Set the rectangle to (0,0,0,0)
     */
    fun setEmpty() {
        bottom = 0.0
        top = bottom
        right = top
        left = right
    }

    /**
     * Set the rectangle's coordinates to the specified values. Note: no range
     * checking is performed, so it is up to the caller to ensure that
     * left <= right and top <= bottom.
     * @param left   The X coordinate of the left side of the rectangle
     * @param top    The Y coordinate of the top of the rectangle
     * @param right  The X coordinate of the right side of the rectangle
     * @param bottom The Y coordinate of the bottom of the rectangle
     */
    operator fun set(left: Double, top: Double, right: Double, bottom: Double) {
        this.left = left
        this.top = top
        this.right = right
        this.bottom = bottom
    }

    /**
     * Copy the coordinates from src into this rectangle.
     * @param src The rectangle whose coordinates are copied into this
     * rectangle.
     */
    fun set(src: RectD) {
        left = src.left
        top = src.top
        right = src.right
        bottom = src.bottom
    }

    /**
     * Offset the rectangle by adding dx to its left and right coordinates, and
     * adding dy to its top and bottom coordinates.
     * @param dx The amount to add to the rectangle's left and right coordinates
     * @param dy The amount to add to the rectangle's top and bottom coordinates
     */
    fun offset(dx: Double, dy: Double) {
        left += dx
        top += dy
        right += dx
        bottom += dy
    }

    /**
     * Offset the rectangle by adding the corresponding value for each of the four
     * values: left, top, right, bottom
     * @param left The amount to add to the rectangle's left coordinate
     * @param top The amount to add to the rectangle's top coordinate
     * @param right The amount to add to the rectangle's right coordinate
     * @param bottom The amount to add to the rectangle's bottom coordinate
     */
    fun offset(left: Double, top: Double, right: Double, bottom: Double) {
        this.left += left
        this.top  += top
        this.right  += right
        this.bottom += bottom
    }

    /**
     * Offset the rectangle to a specific (left, top) position,
     * keeping its width and height the same.
     * @param newLeft The new "left" coordinate for the rectangle
     * @param newTop  The new "top" coordinate for the rectangle
     */
    fun offsetTo(newLeft: Double, newTop: Double) {
        right += newLeft - left
        bottom += newTop - top
        left = newLeft
        top = newTop
    }

    /**
     * Inset the rectangle by (dx,dy). If dx is positive, then the sides are
     * moved inwards, making the rectangle narrower. If dx is negative, then the
     * sides are moved outwards, making the rectangle wider. The same holds true
     * for dy and the top and bottom.
     * @param dx The amount to add(subtract) from the rectangle's left(right)
     * @param dy The amount to add(subtract) from the rectangle's top(bottom)
     */
    fun inset(dx: Double, dy: Double) {
        left += dx
        top += dy
        right -= dx
        bottom -= dy
    }

    /**
     * Returns true if (x,y) is inside the rectangle. The left and top are
     * considered to be inside, while the right and bottom are not. This means
     * that for a x,y to be contained: left <= x < right and top <= y < bottom.
     * An empty rectangle never contains any point.
     * @param x The X coordinate of the point being tested for containment
     * @param y The Y coordinate of the point being tested for containment
     * @return true if (x,y) are contained by the rectangle, where containment
     * means left <= x < right and top <= y < bottom
     */
    fun contains(x: Double, y: Double): Boolean {
        return left < right && top < bottom // check for empty first
                && x >= left && x < right && y >= top && y < bottom
    }

    /**
     * Returns true if the 4 specified sides of a rectangle are inside or equal
     * to this rectangle. i.e. is this rectangle a superset of the specified
     * rectangle. An empty rectangle never contains another rectangle.
     * @param left The left side of the rectangle being tested for containment
     * @param top The top of the rectangle being tested for containment
     * @param right The right side of the rectangle being tested for containment
     * @param bottom The bottom of the rectangle being tested for containment
     * @return true if the the 4 specified sides of a rectangle are inside or
     * equal to this rectangle
     */
    fun contains(left: Double, top: Double, right: Double, bottom: Double): Boolean {
        // check for empty first
        return this.left < this.right && this.top < this.bottom // now check for containment
                && this.left <= left && this.top <= top && this.right >= right && this.bottom >= bottom
    }

    /**
     * Returns true if the specified rectangle r is inside or equal to this
     * rectangle. An empty rectangle never contains another rectangle.
     * @param r The rectangle being tested for containment.
     * @return true if the specified rectangle r is inside or equal to this
     * rectangle
     */
    operator fun contains(r: RectD): Boolean {
        return contains(r.left, r.top, r.right, r.bottom)
    }

    /**
     * If the rectangle specified by left,top,right,bottom intersects this
     * rectangle, return true and set this rectangle to that intersection,
     * otherwise return false and do not change this rectangle. No check is
     * performed to see if either rectangle is empty. Note: To just test for
     * intersection, use intersects()
     * @param left The left side of the rectangle being intersected with this
     * rectangle
     * @param top The top of the rectangle being intersected with this rectangle
     * @param right The right side of the rectangle being intersected with this
     * rectangle.
     * @param bottom The bottom of the rectangle being intersected with this
     * rectangle.
     * @return true if the specified rectangle and this rectangle intersect
     * (and this rectangle is then set to that intersection) else
     * return false and do not change this rectangle.
     */
    fun intersect(left: Double, top: Double, right: Double, bottom: Double): Boolean {
        if (this.left < right && left < this.right && this.top < bottom && top < this.bottom) {
            if (this.left < left) {
                this.left = left
            }
            if (this.top < top) {
                this.top = top
            }
            if (this.right > right) {
                this.right = right
            }
            if (this.bottom > bottom) {
                this.bottom = bottom
            }
            return true
        }
        return false
    }

    /**
     * If the specified rectangle intersects this rectangle, return true and set
     * this rectangle to that intersection, otherwise return false and do not
     * change this rectangle. No check is performed to see if either rectangle
     * is empty. To just test for intersection, use intersects()
     * @param r The rectangle being intersected with this rectangle.
     * @return true if the specified rectangle and this rectangle intersect
     * (and this rectangle is then set to that intersection) else
     * return false and do not change this rectangle.
     */
    fun intersect(r: RectD): Boolean {
        return intersect(r.left, r.top, r.right, r.bottom)
    }

    /**
     * If rectangles a and b intersect, return true and set this rectangle to
     * that intersection, otherwise return false and do not change this
     * rectangle. No check is performed to see if either rectangle is empty.
     * To just test for intersection, use intersects()
     * @param a The first rectangle being intersected with
     * @param b The second rectangle being intersected with
     * @return true if the two specified rectangles intersect. If they do, set
     * this rectangle to that intersection. If they do not, return
     * false and do not change this rectangle.
     */
    fun setIntersect(a: RectD, b: RectD): Boolean {
        if (a.left < b.right && b.left < a.right && a.top < b.bottom && b.top < a.bottom) {
            left = Math.max(a.left, b.left)
            top = Math.max(a.top, b.top)
            right = Math.min(a.right, b.right)
            bottom = Math.min(a.bottom, b.bottom)
            return true
        }
        return false
    }

    /**
     * Returns true if this rectangle intersects the specified rectangle.
     * In no event is this rectangle modified. No check is performed to see
     * if either rectangle is empty. To record the intersection, use intersect()
     * or setIntersect().
     * @param left The left side of the rectangle being tested for intersection
     * @param top The top of the rectangle being tested for intersection
     * @param right The right side of the rectangle being tested for
     * intersection
     * @param bottom The bottom of the rectangle being tested for intersection
     * @return true if the specified rectangle intersects this rectangle. In
     * no event is this rectangle modified.
     */
    fun intersects(left: Double, top: Double, right: Double, bottom: Double): Boolean {
        return this.left < right && left < this.right && this.top < bottom && top < this.bottom
    }

    /**
     * Returns true if the two specified rectangles intersect. In no event are
     * either of the rectangles modified. To record the intersection,
     * use intersect() or setIntersect().
     * @param a The first rectangle being tested for intersection
     * @param b The second rectangle being tested for intersection
     * @return true if the two specified rectangles intersect. In no event are
     * either of the rectangles modified.
     */
    fun intersects(a: RectD, b: RectD): Boolean {
        return a.left < b.right && b.left < a.right && a.top < b.bottom && b.top < a.bottom
    }

    /**
     * Update this Rect to enclose itself and the specified rectangle. If the
     * specified rectangle is empty, nothing is done. If this rectangle is empty
     * it is set to the specified rectangle.
     * @param left The left edge being unioned with this rectangle
     * @param top The top edge being unioned with this rectangle
     * @param right The right edge being unioned with this rectangle
     * @param bottom The bottom edge being unioned with this rectangle
     */
    fun union(left: Double, top: Double, right: Double, bottom: Double) {
        if (left < right && top < bottom) {
            if (this.left < this.right && this.top < this.bottom) {
                if (this.left > left) this.left = left
                if (this.top > top) this.top = top
                if (this.right < right) this.right = right
                if (this.bottom < bottom) this.bottom = bottom
            } else {
                this.left = left
                this.top = top
                this.right = right
                this.bottom = bottom
            }
        }
    }

    /**
     * Update this Rect to enclose itself and the specified rectangle. If the
     * specified rectangle is empty, nothing is done. If this rectangle is empty
     * it is set to the specified rectangle.
     * @param r The rectangle being unioned with this rectangle
     */
    fun union(r: RectD) {
        union(r.left, r.top, r.right, r.bottom)
    }

    /**
     * Update this Rect to enclose itself and the [x,y] coordinate. There is no
     * check to see that this rectangle is non-empty.
     * @param x The x coordinate of the point to add to the rectangle
     * @param y The y coordinate of the point to add to the rectangle
     */
    fun union(x: Double, y: Double) {

        if (x < left) {
            left = x
        } else if (x > right) {
            right = x
        }

        if (y < top) {
            top = y
        } else if (y > bottom) {
            bottom = y
        }
    }

    /**
     * Swap top/bottom or left/right if there are flipped (i.e. left > right
     * and/or top > bottom). This can be called if
     * the edges are computed separately, and may have crossed over each other.
     * If the edges are already correct (i.e. left <= right and top <= bottom)
     * then nothing is done.
     */
    fun sort() {
        if (left > right) {
            val temp = left
            left = right
            right = temp
        }
        if (top > bottom) {
            val temp = top
            top = bottom
            bottom = temp
        }
    }

    /**
     * Scales up the rect by the given scale.
     * @param scale by how much to multiply the values
     */
    fun scale(scale: Double) {
        if (scale != 1.0) {
            left *= scale
            top *= scale
            right *= scale
            bottom *= scale
        }
    }

    override fun toString(): String {
        return "RectD($left,$top,$right,$bottom)"
    }
}