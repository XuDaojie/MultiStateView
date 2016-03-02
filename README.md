MultiStateView
===
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

msv_loadingView is the view to be used for the [LOADING ViewState](https://github.com/Kennyc1012/MultiStateView/blob/master/library/src/main/java/com/kennyc/view/MultiStateView.java#L34)<br>
msv_emptyView is the view to be used for the [EMPTY ViewSate](https://github.com/Kennyc1012/MultiStateView/blob/master/library/src/main/java/com/kennyc/view/MultiStateView.java#L32)<br>
msv_failView is the view to be used for the [ERROR ViewState](https://github.com/Kennyc1012/MultiStateView/blob/master/library/src/main/java/com/kennyc/view/MultiStateView.java#L30)<br>
msv_viewState is the [ViewState](https://github.com/Kennyc1012/MultiStateView/blob/master/library/src/main/java/com/kennyc/view/MultiStateView.java#L38) for the MultiStateView<br>
The [CONTENT ViewState](https://github.com/Kennyc1012/MultiStateView/blob/master/library/src/main/java/com/kennyc/view/MultiStateView.java#L28) is determined by whatever is inside of the tags via XML. <b>NOTE**</b> a Content view must be set for the view to function, this is by design. 

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