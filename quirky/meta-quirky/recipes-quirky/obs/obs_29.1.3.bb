# Recipe created by recipetool
# recipetool create -o obs_29.1.3.bb https://distro.ibiblio.org/easyos/source/alphabetical/o/obs-studio-29.1.3.tar.gz
# ref: https://github.com/obsproject/obs-studio/wiki/build-instructions-for-linux

LICENSE = "GPL-2.0-only & LGPL-2.1-only & MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://deps/jansson/LICENSE;md5=8b70213ec164c7bd876ec2120ba52f61 \
                    file://deps/json11/LICENSE.txt;md5=67c566d2a95f89606ed2c9fa60b68467 \
                    file://deps/libcaption/LICENSE.txt;md5=da1082841ec483429abb0e8c8779d6dc \
                    file://deps/uthash/uthash/LICENSE;md5=736712b5904dd42f8678df7172bc5f2b \
                    file://deps/w32-pthreads/COPYING;md5=51b90c76bb95fd648da6d973aac3ac04 \
                    file://deps/w32-pthreads/COPYING.LIB;md5=1f9afd9ce31d5df8ee588082031db79e \
                    file://plugins/enc-amf/AMF/LICENSE.txt;md5=1eb8e358f15af1952df8e39623542473 \
                    file://plugins/enc-amf/LICENSE;md5=f75a81055a77626fa5f72389027b2091 \
                    file://plugins/mac-syphon/data/syphon_license.txt;md5=d4ac777b0a4d5609c55ef554894d298e \
                    file://plugins/mac-syphon/syphon-framework/License.txt;md5=28ed697dab374b6d4e4d0d9b3ae87ef3 \
                    file://plugins/obs-browser/COPYING;md5=94d55d512a9ba36caa9b7df079bae19f \
                    file://plugins/obs-browser/deps/json11/LICENSE.txt;md5=67c566d2a95f89606ed2c9fa60b68467 \
                    file://plugins/obs-filters/rnnoise/COPYING;md5=1890bf89a18f8339491894a0b45428bf \
                    file://plugins/obs-outputs/ftl-sdk/LICENSE;md5=25de6d60ae20e3cf7f81624750c31abf \
                    file://plugins/obs-outputs/ftl-sdk/libcurl/COPYING;md5=88de35e3bb9a2c9bf35f098047a569c0 \
                    file://plugins/obs-outputs/ftl-sdk/libcurl/docs/LICENSE-MIXING.md;md5=076bb6a0ec2981ffa4d82656077c8b18 \
                    file://plugins/obs-outputs/ftl-sdk/libjansson/LICENSE;md5=8b70213ec164c7bd876ec2120ba52f61 \
                    file://plugins/obs-outputs/librtmp/COPYING;md5=e344c8fa836c3a41c4cbd79d7bd3a379 \
                    file://plugins/obs-qsv11/QSV11-License-Clarification-Email.txt;md5=eade0adcb41299a816ac7c2ba5860380 \
                    file://plugins/obs-websocket/LICENSE;md5=a28efa141d7350cbeff5851a06ace6e7 \
                    file://plugins/win-dshow/libdshowcapture/COPYING;md5=4fbd65380cdd255951079008b364516c \
                    file://plugins/win-dshow/libdshowcapture/external/capture-device-support/LICENSE;md5=ec4ac1e559dba658ecfe9e11c18c4f24"


SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/o/obs-studio-${PV}.tar.gz"
SRC_URI[sha256sum] = "8a917bdefffd39980e12206184d51ea6a94b08ffbc2203dbf19730ccabd2de17"

S = "${WORKDIR}/${BPN}-studio-${PV}"

DEPENDS = "fontconfig pulseaudio qtx11extras zlib libx11 qtsvg qtbase libxcb \
           virtual/libgl swig-native freetype dbus alsa-lib \
           imagemagick jack libsndfile1 eudev jansson x264 x265 mbedtls \
           luajit v4l-utils speex speexdsp curl ffmpeg libxcb sysfsutils \
           mesa glib-2.0 glib-2.0-native libxcomposite libxinerama pciutils \
           libdrm libxdamage libpciaccess libice libsm libxtst libcap libxxf86vm \
           expat libxshmfence graphite2 icu libpcre util-linux libxi flac \
           libpng jpeg libogg libtheora libxml2 libxau libgcrypt openssl \
           libxdmcp libbluray bzip2 libvdpau libvdpau-va-gl schroedinger \
           xvidcore wavpack libvorbis libvpx opencore-amr lame lcms xz libwebp \
           libxfixes libxext zlib \
           freetype libxcb swig-native alsa-lib vlc nlohmann-json websocketpp asio \
           srt librist"

inherit cmake pkgconfig cmake_qt5 python3native python3-dir

#override OE...
CFLAGS:append = " -Wno-error=deprecated-declarations"

#20221208 add: -DENABLE_WAYLAND=OFF -DBUILD_VST=OFF
EXTRA_OECMAKE = "-DUNIX_STRUCTURE=1 -DBUILD_BROWSER=OFF -DENABLE_PIPEWIRE=OFF \
                 -DTHREADS_PTHREAD_ARG=-pthread -DENABLE_WAYLAND=OFF -DBUILD_VST=OFF \
                 -DENABLE_AJA=0 -DENABLE_WEBRTC=0 -DCMAKE_BUILD_TYPE=Release"

HOMEPAGE = "https://obsproject.com/"
SUMMARY = "Video recording and live streaming"

#ERROR: obs-26.1.2-r6 do_package_qa: QA Issue: -dev package obs-dev contains non-symlink .so '/usr/lib/libobs-scripting.so' [dev-elf]
ERROR_QA:remove:obs = "dev-elf"

