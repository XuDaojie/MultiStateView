MultiStateView
==============
[![JitPack](https://jitpack.io/v/XuDaojie/MultiStateView.svg)](https://jitpack.io/#XuDaojie/MultiStateView)

用于切换不同View的各种状态,默认包含`Content`,`Empty`,`Fail`,`Loading` 4个状态,并且支持添加其他状态

![screenshot](https://github.com/XuDaojie/MultiStateView/blob/develop/art/MultiStateView.gif)

## Using MultiStateView
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
```
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
    compile 'com.github.XuDaojie:MultiStateView:v2.0.0
}
```

## 喝水不忘挖井人
[Kennyc1012/MultiStateView](https://github.com/Kennyc1012/MultiStateView)