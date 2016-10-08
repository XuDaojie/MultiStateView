MultiStateView
==============
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](LICENSE) [![JitPack](https://jitpack.io/v/XuDaojie/MultiStateView.svg)](https://jitpack.io/#XuDaojie/MultiStateView) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-MultiStateView-green.svg?style=true)](https://android-arsenal.com/details/1/4386)

参考[Kennyc1012/MultiStateView](https://github.com/Kennyc1012/MultiStateView)这个这个写的,1.0版本只是在这个上面加了默认布局,以及修正了在设计器中无法预览的问题<br>
2.0是自己写的,主要增加了自定义状态,当然,核心原理还是那样

用于切换不同View的各种状态,默认包含`Content`,`Empty`,`Fail`,`Loading` 4个状态,并且支持添加其他状态<br>
并且处理了`Loading`显示时间很短立刻切换到其他状态时看起来好像`Loading`界面闪了一下

![screenshot](https://github.com/XuDaojie/MultiStateView/blob/develop/art/MultiStateView.gif)

## Using MultiStateView
### Layout
``` xml
<me.xdj.view.SimpleMultiStateView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:xdj="http://schemas.android.com/apk/res-auto"
    android:id="@+id/multi_state_view"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    xdj:msv_emptyView="@layout/msv_view_state_empty"
    xdj:msv_failView="@layout/msv_view_state_fail"
    xdj:msv_loadingView="@layout/msv_view_state_loading"
    tools:context="me.xdj.multistateview.MainActivity">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center"
        android:text="Content" />
</me.xdj.view.SimpleMultiStateView>
```

可以通过以下属性自定义可状态下的视图
``` xml
<attr name="msv_loadingView" format="reference" />
<attr name="msv_emptyView" format="reference" />
<attr name="msv_failView" format="reference" />
```

### 主要方法
``` java 
public void setViewState(int state) // 设置视图状态 
public int getViewState()           // 获得当前状态
public View getView(int state)      // 获得指定状态的视图
public void addViewForStatus(int status, int resLayoutID) // 增加状态
public void setOnInflaterListener(OnInflateListener onInflateListener) // 各状态Layout inflate时触发(除了CONTENT)
```

> Tips<br>
> 1. 当从`Loading`状态切换到其他状态时,如果`Loading`持续时间低于**600ms**则会延迟**600ms**切换<br>
> 2. 除了`CONTENT`以外的状态**Layout**都是在首次调用`setViewState`后才会**inflate**,所以设置监听事件的话需要状态修改完成后设置,或者调用`setOnInflaterListener`进行设置

### 常量
``` java
public static final int STATE_CONTENT = 10001;
public static final int STATE_LOADING = 10002;
public static final int STATE_EMPTY = 10003;
public static final int STATE_FAIL = 10004;
```

### 代码
具体代码可以看[MultiStateFragment](https://github.com/XuDaojie/MultiStateView/blob/develop/app/src/main/java/me/xdj/multistateview/MultiStateFragment.java)

## Including in your project
要将**MultiStateView**引入你的项目，需要修改你的**build.gradle**

### Add repository 
``` groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```
### Add dependency
``` groovy
dependencies {
    compile 'com.github.XuDaojie:MultiStateView:v2.2.1'
}
```

## 喝水不忘挖井人
[Kennyc1012/MultiStateView](https://github.com/Kennyc1012/MultiStateView)<br>
[android.support.v4.widget.ContentLoadingProgressBar](https://android.googlesource.com/platform/frameworks/support/+/refs/heads/master/v4/java/android/support/v4/widget/ContentLoadingProgressBar.java)

## License
Copyright XuDaojie

Licensed under the Apache License, Version 2.0 (the "License");<br>
you may not use this file except in compliance with the License.<br>
You may obtain a copy of the License at<br>

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software<br>
distributed under the License is distributed on an "AS IS" BASIS,<br>
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.<br>
See the License for the specific language governing permissions and<br>
limitations under the License.<br>