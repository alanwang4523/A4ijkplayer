# A4ijkplayer
ijkplayer compile with cmake

最近在学习和研究 **[ijkplayer](https://github.com/bilibili/ijkplayer)，其源码是用 Android.mk 方式编译的，为了方便开发、调试，现将其编译方式替换成 CMake，可以直接在 Android Studio 中查看、跳转、调试 ijkplayer 源码。**

只要不是修改 ffmpeg 的源代码，修改其他其它代码包括 ijkplayer 的 C 代码、Java 代码，都可以在 Android Studio  中修改、运行、调试。

**本项目是基于 ijkplayer 最新的 [k0.8.8 版本](https://github.com/bilibili/ijkplayer/releases/tag/k0.8.8)，ffmpeg 4.0 编译（基于 ff4.0--ijk0.8.8--20210426--001 版本），并包含了 openssl（即支持 https 网络视频）、yuv、sdl、soundtouch 等模块**

其中 ffmpeg (libijkffmpeg.so) 需要先编译好，可以参考我上一篇文章编译（[Android ijkplayer 编译踩坑与记录](https://blog.csdn.net/u011520181/article/details/129697501)），或直接使用我编译好的。

**效果截图**
<div align="left">
<img src=./Screenshots/Screenshot_20230323_184501_com.alanwang4523.a4ijkplayerdemo.jpg width=30% />
</div>

**ijkplayer native 代码调试截图**
<div align="left">
<img src=./Screenshots/ijkplayer_native_debug_prepare_async.png width=80% />
</div>


## 编译 A4ijkplayer aar


**执行以下命令分别编译 A4ijkplayer 的 Debug 和 Release aar**
```
./gradlew clean :A4ijkplayer:assembleDebug

./gradlew clean :A4ijkplayer:assembleRelease
```


输出结果在项目目录下的：./A4ijkplayer/build/outputs/aar/ 目录下，如下：

**A4ijkplayer 编译的 aar 截图**
<div align="left">
<img src=./Screenshots/ijkplayer_aar.png width=80% />
</div>



## 相关项目

https://github.com/bilibili/ijkplayer

https://github.com/alanwang4523/ijkplayer_Build4Android
