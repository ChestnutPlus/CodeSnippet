package x.chestnut.code.snippet.anim.shreddedPaper

/**
 * <pre>
 * author: Chestnut
 * blog  : http://www.jianshu.com/u/a0206b5f4526
 * time  : 2018/8/12 18:16
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class CircleDataBean(var x: Float, var y: Float, //在大于哪个因子后出现
                     var showInFactor: Float, //内外圆环到圆心的距离
                     var radius: Float, var ringWidth: Float) {

    var linearPoints = arrayOfNulls<CircleLinearPoint>(5)

    inner class CircleLinearPoint(var degree: Float, var linearLength: Float, //在大于哪个因子后出现
                                  var showInFactor: Float)

    init {
        linearPoints[0] = CircleLinearPoint(-36F + 180F, 2F, showInFactor)
        linearPoints[1] = CircleLinearPoint(0F + 180F, 3F, showInFactor + 5)
        linearPoints[2] = CircleLinearPoint(36F + 180F, 2F, showInFactor + 10)
        linearPoints[3] = CircleLinearPoint(72F + 180F, 3F, showInFactor + 15)
        linearPoints[4] = CircleLinearPoint(108F + 180F, 2F, showInFactor + 20)
    }
}