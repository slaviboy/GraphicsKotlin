# GraphicsKotlin
Simple library that wraps the classes from 'android.graphics' package and uses Double instead of Float values written in Kotlin
 
## About
Library with useful classes that use Double instead of Float, with the same use as classes from 'android.graphics' package. Some of the classes are written directly for example PointD and RectF, but some classes like the Matrix classes are wrapped around and existing classes. The reason for this is because some classes uses native methods. Here is the [Native](https://github.com/google/skia/blob/master/include/core/SkMatrix.h) class for the matrix.

Supported classes are:
* **PointD** - represents 2D points that uses Double values for x and y properties
* **RectD** - represents rectangle that uses Double values for top, left, right and bottom properties
* **MatrixD** - wrapper over the Matrix class from the 'android.graphics' package that uses Double values

[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)
[![Download](https://img.shields.io/badge/version-0.2.0-blue)](https://github.com/slaviboy/GraphicsKotlin/releases/tag/v0.2.0)

## Add to your project
Add the jitpack maven repository
```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
``` 
Add the dependency
```
dependencies {
  implementation 'com.github.slaviboy:GraphicsKotlin:v0.2.0'
}
```
 
### How to use
Creating object from the supported classes is the same way as those from 'android.graphics' package. Visit the official documentation on the android site to see supported methods and their use [PointF](https://developer.android.com/reference/android/graphics/PointF), [RectF](https://developer.android.com/reference/android/graphics/RectF), [Matrix](https://developer.android.com/reference/android/graphics/Matrix)
 ```kotlin

// create point
val point = PointD(10.0, 20.0)

// create rectangle
val rect = RectD(32.0, 12.5, 200.2, 51.2)

// create matrix
val matrix = MatrixD()

```
 
