# Recipe created by recipetool
# recipetool create -o obs_28.0.1.bb https://github.com/obsproject/obs-studio/archive/refs/tags/28.0.1.tar.gz

LICENSE = "GPL-2.0-only & LGPL-2.1-only & MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://deps/jansson/LICENSE;md5=8b70213ec164c7bd876ec2120ba52f61 \
                    file://deps/json11/LICENSE.txt;md5=67c566d2a95f89606ed2c9fa60b68467 \
                    file://deps/libcaption/LICENSE.txt;md5=da1082841ec483429abb0e8c8779d6dc \
                    file://deps/w32-pthreads/COPYING;md5=51b90c76bb95fd648da6d973aac3ac04 \
                    file://deps/w32-pthreads/COPYING.LIB;md5=1f9afd9ce31d5df8ee588082031db79e \
                    file://plugins/mac-syphon/data/syphon_license.txt;md5=d4ac777b0a4d5609c55ef554894d298e \
                    file://plugins/obs-filters/rnnoise/COPYING;md5=1890bf89a18f8339491894a0b45428bf \
                    file://plugins/obs-outputs/librtmp/COPYING;md5=e344c8fa836c3a41c4cbd79d7bd3a379 \
                    file://plugins/obs-qsv11/QSV11-License-Clarification-Email.txt;md5=eade0adcb41299a816ac7c2ba5860380"

#20250203 patch for ffmpeg6
SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/o/obs-studio-${PV}.tar.gz \
           file://obs28p0p1-ffmpeg6-fix.patch"

SRC_URI[sha256sum] = "a27c5f1f3583729a0472cab2d3386c9bf7b37a45349e95387b3d299c4b14e518"

S = "${WORKDIR}/${BPN}-studio-${PV}"

#export THREADS_PTHREAD_ARG = "0"

DEPENDS = "fontconfig pulseaudio qtx11extras zlib libx11 qtsvg qtbase libxcb \
           virtual/libgl swig-native freetype dbus alsa-lib \
           imagemagick jack libsndfile1 swig eudev jansson x264 x265 mbedtls \
           luajit v4l-utils speex speexdsp curl ffmpeg libxcb sysfsutils \
           mesa glib-2.0 glib-2.0-native libxcomposite libxinerama pciutils \
           libdrm libxdamage libpciaccess libice libsm libxtst libcap libxxf86vm \
           expat libxshmfence graphite2 icu libpcre util-linux libxi flac \
           libpng jpeg libogg libtheora libxml2 libxau libgcrypt openssl \
           libxdmcp libbluray bzip2 libvdpau libvdpau-va-gl schroedinger \
           xvidcore wavpack libvorbis libvpx opencore-amr lame lcms xz libwebp \
           libxfixes libxext zlib \
           srt librist"

inherit cmake cmake_qt5 python3native python3-dir pkgconfig

#CMake Error: TRY_RUN() invoked in cross-compiling mode, please set the following cache variables appropriately:
#   THREADS_PTHREAD_ARG (advanced)

#20221208 add: -DENABLE_WAYLAND=OFF -DBUILD_VST=OFF
EXTRA_OECMAKE = "-DUNIX_STRUCTURE=1 -DBUILD_BROWSER=OFF -DENABLE_PIPEWIRE=OFF \
                 -DTHREADS_PTHREAD_ARG=-pthread -DENABLE_WAYLAND=OFF -DBUILD_VST=OFF \
                 -DENABLE_VLC=OFF"

HOMEPAGE = "https://obsproject.com/"
SUMMARY = "Video recording and live streaming"

#ERROR: obs-26.1.2-r6 do_package_qa: QA Issue: -dev package obs-dev contains non-symlink .so '/usr/lib/libobs-scripting.so' [dev-elf]
ERROR_QA:remove = "dev-elf"
