
#20240103 scarthgap
#PACKAGECONFIG:append = " drm libarchive jack vaapi vdpau"
#nah, override everything...

# While this item does not require it, it depends on ffmpeg which does
LICENSE_FLAGS = "commercial"

SIMPLE_TARGET_SYS = "${@'${TARGET_SYS}'.replace('${TARGET_VENDOR}', '')}"

DEPENDS = "zlib ffmpeg jpeg virtual/libx11 libxv \
           libxscrnsaver libv4l libxinerama \
           libxext fontconfig freetype libx11 librsvg libpng alsa-lib \
           libdvdnav faac faad2 flac gdk-pixbuf mesa libglu liba52 libmad libmng \
           libtheora libva libvdpau libvorbis libogg libvpx libsdl2 libsdl2-image \
           libsdl2-mixer libsdl2-ttf speex libmodplug vcdimager wavpack x264 libxcb \
           libxvmc lame lcms libcdio libraw1394 libavc1394 libdc1394 mpeg2dec \
           schroedinger taglib openssl libsamplerate0 libbluray mesa libdvdread \
           libdvdcss libcddb libcdio-paranoia libarchive libdrm libwebp \
           mpg123 xvidcore x265 jack pulseaudio libass desktop-file-utils"

DEPENDS:append:x86-64 = " lua luajit lua-native"
DEPENDS:append:aarch64 = " lua luajit lua-native"

EXTRA_OECONF = " \
    --prefix=${prefix} \
    --target=${SIMPLE_TARGET_SYS} \
    --confdir=${sysconfdir}/mpv \
    --datadir=${datadir} \
    --disable-manpage-build \
    --enable-gl \
    --enable-libbluray \
    --enable-dvdnav \
    --enable-cdda \
    --disable-uchardet \
    --disable-rubberband \
    --enable-lcms2 \
    --disable-vapoursynth \
    --disable-debug-build \
    --enable-pulse \
    --enable-libmpv-shared \
    --enable-drm \
    --enable-alsa \
    --enable-libarchive \
    --enable-jack \
    --enable-vaapi \
    --enable-vdpau \
    ${PACKAGECONFIG_CONFARGS} \
"

EXTRA_OECONF:append:i686 = " --disable-lua"
EXTRA_OECONF:append:x86-64 = " --enable-lua"
EXTRA_OECONF:append:aarch64 = " --enable-lua"
