MultiStateView
==============
[![](https://jitpack.io/v/XuDaojie/MultiStateView.svg)](https://jitpack.io/#XuDaojie/MultiStateView)

用于切换不同View的各种状态.<br>
基于[Kennyc1012/MultiStateView](https://github.com/Kennyc1012/MultiStateView)

目前支持:
- Content
- Empty
- Fail
- Loading

![screenshot](https://github.com/XuDaojie/MultiStateView/blob/develop/art/MultiStateView.gif)

# Using MultiStateView
相比[Kennyc1012/MultiStateView](https://github.com/Kennyc1012/MultiStateView)增加`previewState`（类似tools）和各状态的默认**Layout**
```xml
<?xml version="1.0" encoding="utf-8"?>
<me.xdj.view.MultiStateView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/multi_state_view"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    tools:context="me.xdj.multistateview.MainActivity">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center"
        android:text="MultiStateView" />
</me.xdj.view.MultiStateView>
```
可以通过以下属性自定义可状态下的视图
```xml
<attr name="msv_loadingView" format="reference" />
<attr name="msv_emptyView" format="reference" />
<attr name="msv_failView" format="reference" />
<attr name="msv_viewState" format="enum"><!-- default:loading -->
<attr name="msv_previewState" format="enum"><!-- default:content -->
```

主要方法
```java 
public void setViewState(int state) // 设置视图状态
public int getViewState()           // 获得当前状态
public View getView(int state)      // 获得指定状态的视图
```

# Including in your project
要将**MultiStateView**引入你的项目，需要修改你的**build.gradle**

## Add repository 
```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```
## Add dependency
```groovy
dependencies {
    compile 'com.github.XuDaojie:MultiStateView:v1.0.5
}
```