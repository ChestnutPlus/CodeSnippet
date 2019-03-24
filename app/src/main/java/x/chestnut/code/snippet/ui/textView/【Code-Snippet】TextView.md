## 1. TextView文字过长，显示省略号
【[参考](http://blog.csdn.net/lyy1104/article/details/38869739)】

必须要同时设置XML和JAVA，而且，java中设置文字必须是在最后。

```xml
android:ellipsize="start|end|middle"    //省略号的位置
android:singleLine="true"
android:lines="2"						//行数
```

```java
tv.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
tv.setSingleLine(true);
tv.setEllipsize(null); // 展开
tv.setEllipsize(TextUtils.TruncateAt.END); // 收缩
tv.setText("");
```

## 2. TextView文字中间加横线
【[参考](http://blog.csdn.net/lyy1104/article/details/38869739)】


```
tv_goods_price = (TextView) v.findViewById(R.id.tv_goods_price);
tv_goods_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//底部加横线:
tv_goods_price.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
```

## 3. 设置Drawable，改变大小
Java代码中设置Drawable，而且可以设置大小，通过`drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight())`：

```
Drawable drawable= getResources().getDrawable(R.drawable.gray_circle);
drawable.setBounds(0, 0, drawable.getMinimumWidth(),
    drawable.getMinimumHeight()); //设置大小

textView.setCompoundDrawables(drawable, null, null, null);  //左上右下
textView.setCompoundDrawablePadding(4);//设置图片和text之间的间距
```

## 4. 设置字体大小
xml中直接写单位即可：

```
使用android:textSize属性设置，例如
android:textSize="15sp"
```

代码中：

```
1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15); //单位最好使用SP
2.btnFind.setTextSize(TypedValue.COMPLEX_UNIT_SP,
    getResources().getDimension(R.dimen.home_top_txt_selected));
```

SP和DP的差别，SP可以随系统设置的字体大小而改变。如果不想随系统字体大小的改变而改变，可以用DP去声明。

## 5. 文本显示可点击链接，部分字体变色，下划线

#### 1）普通的网页，邮箱地址等

原生有一个字段，autoLink，
> autoLink 一共有六个值 ：web  phone  map  email  all  none  分别是url链接  电话号码提取拨号  地图地址  电子邮件  all是能够支持超链接的全部都起作用   最后一个就是都不起作用

```
<TextView
    android:id="@+id/tv1"
    android:text="hello, this is http://www.baidu.com, and my email is 974920378@qq.com"
    android:autoLink="web|email"
    android:layout_margin="10dp"
    android:layout_centerHorizontal="true"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
```
效果就如下：

![](https://user-gold-cdn.xitu.io/2019/3/19/16994bbd6b5036cb?w=380&h=127&f=png&s=13290)

原生就是好，直接点击，会默认跳到浏览器，默认的邮件app等等，但是，好像不能改！虽然说方便，但是诸多不便，有人就吐槽过了 [TextView autoLink小技巧 - 从源码的角度理解并解决autoLink的所有大坑](https://www.jianshu.com/p/d3bef8449960):
> 1. 这超链接网址字体颜色和TextView设置的字体颜色根本不一致啊
> 2. 这个自带的下划线好烦人，不想要
> 3. 我想点击网址跳转到我自己应用内的WebView打开而不是用手机的浏览器
> 4. 和TextView长按事件有冲突，每次onLongClick后都会带出一发超链接网址的onClick，在onLongClick里返回什么都没用。

#### 2）SpannableString 实现可点击超链接效果
参考：[TextView SpannableString 使用之实现可点击超链接效果](https://www.cnblogs.com/didikee/p/7851260.html)


```
TextView textView2 = (TextView) findViewById(R.id.tv2);
String clickString = "I Love Android!";
SpannableString spannableString =new SpannableString(clickString);
//设置点击的位置，为 position[2,6)，
// 第二个字符开始到第6个字符，前开后闭
spannableString.setSpan(new ClickableSpan() {
    @Override
    public void onClick(@NonNull View widget) {
        Toast.makeText(TextViewActivity.this,"Love",Toast.LENGTH_SHORT).show();
    }
},2,6,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
textView2.setText(spannableString);
textView2.setMovementMethod(LinkMovementMethod.getInstance());
```

以上基本实现了，还可以使用 spannable是设置某些字的颜色，大小，下划线等等，参考：[使用SpannableString设置部分文字大小、颜色、超链接、点击事件](https://blog.csdn.net/oushangfeng123/article/details/47374931)

```
TextView textView3 = (TextView) findViewById(R.id.tv3);
String content = "This is a test, you can click baidu or youku.";
SpannableString ss = new SpannableString(content);
//设置网络超链接
ss.setSpan(new URLSpan("http://www.baidu.com"),
        content.indexOf("baidu"), content.indexOf(" or"),
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
ss.setSpan(new URLSpan("http://www.youku.com"),
        content.indexOf("youku"), ss.length(),
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//设置字体颜色
ss.setSpan(new ForegroundColorSpan(Color.parseColor("#ff0000")),
        content.indexOf("baidu"), content.indexOf(" or"),
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
ss.setSpan(new ForegroundColorSpan(Color.parseColor("#ff00ff")),
        content.indexOf("youku"), ss.length(),
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
// 设置字体大小
ss.setSpan(new AbsoluteSizeSpan(sp2px(this, 25)),
        content.indexOf("baidu"), content.indexOf(" or"),
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
ss.setSpan(new AbsoluteSizeSpan(sp2px(this, 30)),
        content.indexOf("youku"), ss.length(),
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
// 取消下划线
ss.setSpan(new UnderlineSpan() {
               @Override
               public void updateDrawState(@NonNull TextPaint textPaint) {
                   textPaint.setUnderlineText(false);
               }
           },
        content.indexOf("youku"), ss.length(),
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
textView3.setText(ss);
textView3.setMovementMethod(LinkMovementMethod.getInstance());
```

这里设置的网络链接没有传入一个点击事件，所以默认就是跳转到默认浏览器。

#### 3）SpannableStringBuilder 实现多种效果叠加

```
TextView textView4 = (TextView) findViewById(R.id.tv4);
final String linkWord1 = "Android";
final String linkWord2 = "Are you ok?";
final String linkWord3 = "think you!";
String word = "Hello " + linkWord1 + "," + linkWord2 + " I'm fine," + linkWord3;
SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(word);
int index1 = word.indexOf(linkWord1);
int index2 = word.indexOf(linkWord2);
int index3 = word.indexOf(linkWord3);
spannableStringBuilder.setSpan(new ClickableSpan() {
    @Override
    public void onClick(@NonNull View widget) {
        Toast.makeText(TextViewActivity.this, linkWord1, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(Color.RED);       //设置文件颜色
        ds.setUnderlineText(true);      //设置下划线
    }
}, index1, index1 + linkWord1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
spannableStringBuilder.setSpan(new ClickableSpan() {
    @Override
    public void onClick(@NonNull View widget) {
        Toast.makeText(TextViewActivity.this, linkWord2, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(Color.GREEN);       //设置文件颜色
        ds.setUnderlineText(false);      //设置下划线
    }
}, index2, index2 + linkWord2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
spannableStringBuilder.setSpan(new ClickableSpan() {
    @Override
    public void onClick(@NonNull View widget) {
        Toast.makeText(TextViewActivity.this, linkWord3, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(Color.BLUE);       //设置文件颜色
        ds.setUnderlineText(false);      //设置下划线
    }
}, index3, index3 + linkWord3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
textView4.setTextSize(14);
textView4.setText(spannableStringBuilder);
textView4.setMovementMethod(LinkMovementMethod.getInstance());
```

## 6. 设置字体

#### 1）系统默认字体

```
<TextView
    Android:id="@+id/serif" # sans，monospace #
    Android:text="Hello,World"
    Android:typeface="serif"
    Android:textSize="20sp" />
```

#### 2）自定义字体

在Android中可以引入其他字体，将字体文件保存在 **app/src/main/assets/fonts/** 目录下，然后在Java中引入：

```
//得到TextView控件对象
TextView textView =(TextView)findViewById(R.id.custom);
//将字体文件保存在assets/fonts/目录下，创建Typeface对象
Typeface typeFace =Typeface.createFromAsset(getAssets(),"fonts/HandmadeTypewriter.ttf");
//使用字体
textView.setTypeface(typeFace);
```

或者在XML中设置，字体同样放置一样的目录，

```xml
<style name="TextAppearanceLarg" parent="TextAppearance.AppCompat.Large">
    <!-- Custom Attr-->
    <item name="fontPath">fonts/title.ttf</item>
</style>
```

最后在XML中，TextView中引用：`android:textAppearance="@style/TextAppearanceLarg"`


## 7. 设置字体颜色

xml设置单一颜色：

```
<TextView android:textColor="#fff"/>
<TextView android:textColor="@android:color/white"/>
```

设置点击的时候，颜色变化

```
<TextView
    android:textColor="@drawable/item_track_txt_color_selector"
    android:layout_height="55dp" />

//item_track_txt_color_selector
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android" >
    <item android:state_pressed="true" android:color="#cecbcb"/>
    <item android:color="#FFFFFF"/>
</selector>
```

java中设置颜色：

```
textView.setTextColor(Color.rgb(255,238,2));
textView.setTextColor(getContext().getColor(R.color.arrow_white));//API23及以上可以用
//或者
textView.setTextColor(getContext().getResources().getColor(R.color.voice_collect_0));
//设置点击的时候，颜色变化
textView.setTextColor(getContext().getResources()
    .getColorStateList(R.drawable.item_track_txt_color_selector));
```

## 8. 设置阴影效果


```
android:shadowColor="#dc1818" # 阴影的颜色
android:shadowDx="10" # 水平方向的偏移量,就是水平方向阴影开始的横坐标位置，像素
android:shadowDy="10" # 垂直方向的偏移量,就是竖直方向阴影开始的纵坐标位置，像素
android:shadowRadius="3.0" # 阴影的半径大小
```
对应的java方法是：

```
public void setShadowLayer (float radius, float dx, float dy, int color)
```

而且，xml中要设置了颜色后，其他属性才能生效


dx,dy分别为（0，0），（5，5），（20，20）：

![](https://user-gold-cdn.xitu.io/2019/3/19/169954be7b59c750?w=184&h=124&f=png&s=15102)

shadowRadius分别为 1，5，10，越大越模糊：

![](https://user-gold-cdn.xitu.io/2019/3/19/1699562a370a623e?w=181&h=120&f=png&s=16684)

## 9. 跑马灯效果

文本过长的时候，要求用滚动播放的形式显示，也就是跑马灯的效果。

```
XML：
    android:ellipsize="marquee"
    android:maxLines="1"
    android:marqueeRepeatLimit="marquee_forever"
JAVA：
    TextView textView = (TextView) xHolder.getViewById(R.id.txt_title);
    textView.setText("dfsd");
    textView.setSingleLine();
    textView.setSelected(true);
```
注意：以上必须同时设置！


