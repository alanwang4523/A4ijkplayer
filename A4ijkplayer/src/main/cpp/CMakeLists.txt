#
# Copyright (c) 2019-present AlanWang4523 <alanwang4523@gmail.com>
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
cmake_minimum_required(VERSION 3.4.1)

set(PATH_TO_A4_PRJ_CPP ${CMAKE_CURRENT_SOURCE_DIR})
set(PATH_TO_A4_IJK_MEDIA ${PATH_TO_A4_PRJ_CPP}/ijkmedia)
set(PATH_TO_A4_IJK_PROF ${PATH_TO_A4_PRJ_CPP}/ijkprof)
set(PATH_TO_OTHER_LIBS ${PATH_TO_A4_PRJ_CPP}/otherlibs)

message(${ANDROID_ABI})
message(${PATH_TO_A4_PRJ_CPP})
message(${PATH_TO_A4_IJK_MEDIA})

add_definitions(-DNDEBUG)

# <------- ijkmedia/ijkj4a 目录下的文件
file(GLOB_RECURSE SOURCE_C_FILES_OF_IJKJ4A "${PATH_TO_A4_IJK_MEDIA}/ijkj4a/*.c")

# <------- ijkmedia/ijksdl 目录下的文件
file(GLOB_RECURSE SOURCE_C_FILES_OF_IJKSDL "${PATH_TO_A4_IJK_MEDIA}/ijksdl/*.c")

# <------- ijkmedia/ijksoundtouch 目录下的文件
file(GLOB_RECURSE SOURCE_CPP_FILES_OF_IJKSOUNDTOUCH "${PATH_TO_A4_IJK_MEDIA}/ijksoundtouch/*.cpp")

# <------- ijkmedia/ijkplayer 目录下的文件
file(GLOB_RECURSE SOURCE_C_FILES_OF_IJKPLAYER "${PATH_TO_A4_IJK_MEDIA}/ijkplayer/*.c")
file(GLOB_RECURSE SOURCE_CPP_FILES_OF_IJKPLAYER "${PATH_TO_A4_IJK_MEDIA}/ijkplayer/*.cpp")

# <------- ijkmedia/ijkyuv 目录下的文件
#file(GLOB_RECURSE SOURCE_CC_FILES_OF_IJKYUV "${PATH_TO_A4_IJK_MEDIA}/ijkyuv/*.cc")

set(SOURCE_COMMON_CC_FILES_OF_IJKYUV
        # ijkmedia/ijkyuv
        ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/compare.cc
        ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/compare_common.cc
        ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/compare_posix.cc
        ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/convert.cc
        ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/convert_argb.cc
        ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/convert_from.cc
        ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/convert_from_argb.cc
        ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/convert_to_argb.cc
        ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/convert_to_i420.cc
        ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/cpu_id.cc
        ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/format_conversion.cc
        ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/planar_functions.cc
        ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/rotate.cc
        ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/rotate_argb.cc
        ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/rotate_mips.cc
        ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/row_any.cc
        ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/row_common.cc
        ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/row_mips.cc
        ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/row_posix.cc
        ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/scale.cc
        ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/scale_argb.cc
        ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/scale_common.cc
        ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/scale_mips.cc
        ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/scale_posix.cc
        ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/video_common.cc
)
# ijkyuv 目录下与 abi 架构相关的代码
if(${ANDROID_ABI} STREQUAL "armeabi-v7a")
    set(SOURCE_ABI_CC_FILES_OF_IJKYUV
            ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/compare_neon.cc
            ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/rotate_neon.cc
            ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/row_neon.cc
            ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/scale_neon.cc
            )
elseif(${ANDROID_ABI} STREQUAL "arm64-v8a")
    set(SOURCE_ABI_CC_FILES_OF_IJKYUV
            ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/compare_neon64.cc
            ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/rotate_neon64.cc
            ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/row_neon64.cc
            ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/source/scale_neon64.cc
            )
endif()

# <------- cpp/ijkprof 目录下的文件
file(GLOB_RECURSE SOURCE_C_FILES_OF_IJKPROF "${PATH_TO_A4_IJK_PROF}/*.c")

include_directories(
        ${PATH_TO_OTHER_LIBS}/ffmpeg/include
        ${PATH_TO_OTHER_LIBS}/ffmpeg/include/${ANDROID_ABI}
        ${PATH_TO_A4_IJK_MEDIA}
        ${PATH_TO_A4_IJK_PROF}
        ${PATH_TO_A4_IJK_MEDIA}/ijkj4a
        ${PATH_TO_A4_IJK_MEDIA}/ijksdl
        ${PATH_TO_A4_IJK_MEDIA}/ijksoundtouch
        ${PATH_TO_A4_IJK_MEDIA}/ijksoundtouch/include
        ${PATH_TO_A4_IJK_MEDIA}/ijkyuv
        ${PATH_TO_A4_IJK_MEDIA}/ijkyuv/include
        ${PATH_TO_A4_IJK_MEDIA}/ijkplayer
)

add_library(
        a4ijkplayer
        SHARED

        ${SOURCE_C_FILES_OF_IJKJ4A}
        ${SOURCE_C_FILES_OF_IJKSDL}
        ${SOURCE_CPP_FILES_OF_IJKSOUNDTOUCH}
        ${SOURCE_COMMON_CC_FILES_OF_IJKYUV}
        ${SOURCE_ABI_CC_FILES_OF_IJKYUV}
        ${SOURCE_C_FILES_OF_IJKPLAYER}
        ${SOURCE_CPP_FILES_OF_IJKPLAYER}
        ${SOURCE_C_FILES_OF_IJKPROF}
)

target_link_libraries( # Specifies the target library.
        a4ijkplayer
        ${PATH_TO_OTHER_LIBS}/ffmpeg/libs/${ANDROID_ABI}/libijkffmpeg.so
        log
        android
        EGL
        GLESv2
        jnigraphics
        OpenSLES
        m z
        )