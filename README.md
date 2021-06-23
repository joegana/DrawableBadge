# Drawable Badge
[![CircleCI](https://circleci.com/gh/joeiot/DrawableBadge.svg?style=shield)](https://circleci.com/gh/joeiot/DrawableBadge)
[![Release](https://jitpack.io/v/joeiot/DrawableBadge.svg)](https://jitpack.io/#joeiot/DrawableBadge/2.0.1)
[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/joeiot/DrawableBadge/master/LICENSE)

Drawable Badge is a android library for adding badges to drawables.

![Drawable Badge Android Library](https://github.com/joeiot/DrawableBadge/blob/master/screenshot.png?raw=true)

## Usage
Generate drawable with badge
``` kotlin
val drawable = DrawableBadge.Builder(context)
    .drawableResId(R.mipmap.ic_launcher_round)
    .badgeColor(R.color.badgeColor)
    .badgeSize(R.dimen.badge_size)
    .badgeGravity(Gravity.CENTER_VERTICAL or Gravity.Start)
    .badgeMargin(R.dimen.badge_margin)
    .textFont(R.font.bold)
    .textColor(R.color.textColor)
    .showBorder(true)
    .badgeBorderColor(R.color.badgeBorderColor)
    .badgeBorderSize(R.dimen.badge_border_size)
    .maximumCounter(99)
    .showCounter(true)
    .build()
    .get(99)
```
Apply to image view.
``` kotlin
imageViewBadge.setImageDrawable(drawable)
```

## Customize
- `drawableResId`: Drawable resource id to added badges.
- `drawable`: Drawable to added badges.
- `bitmap`: Bitmap to added badges.
- `textColor`: Badge text color resource id, default `#FFFFFF`.
- `textFont`: Badge text font resource id, default `null`.
- `badgeColor`: Badge color resource id , default `#FF0000`.
- `badgeSize`: Badge size supported `@DimenRes` or `@Px`, default `16dp`.
- `badgeGravity`: Position of badge by [Android Gravity](https://developer.android.com/reference/android/view/Gravity) which need to added. default `Gravity.TOP or Gravity.END`.
- `badgeMargin`: Badge margin supported `@DimenRes` or `@Px`, default `0`.
- `showBorder`: Set visible of badge border, default `true`.
- `badgeBorderColor`: Badge border color resource id , default `#FFFFFF`.
- `badgeBorderSize`: Badge border size supported `@DimenRes` or `@Px`, default `0.5dp`.
- `maximumCounter`: Maximum counter text will append with `+`, default and not more than `99`.
- `showCounter`: Set visible of badge counter number, default `true`.

#### Deprecated
- `badgePosition`: Position of Badge which need to added.

## Download
``` groovy
repositories {
     mavenCentral()gt
}
```

``` groovy
implementation 'io.github.joeiot:drawablebadge:2.0.4'
```


## License
```
MIT License

Copyright (c) 2017 Teeranai.P

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
