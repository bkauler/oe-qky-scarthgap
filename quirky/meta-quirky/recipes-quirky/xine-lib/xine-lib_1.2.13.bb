# Recipe created by recipetool
# recipetool create -o xine-lib_1.2.8.bb http://distro.ibiblio.org/quirky/quirky6/sources/alphabetical/x/xine-lib-1.2.8.tar.bz2
# 20210407 added jack
# 20211027 added pulseaudio
#20240527 recipetool create -o xine-lib_1.2.13.bb https://downloads.sourceforge.net/xine/xine-lib-1.2.13.tar.xz

LICENSE = "LGPL-2.0-only & GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=b29c0845cfd8eb68f25dd58c624c161f \
                    file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

#SRC_URI = "http://sourceforge.net/projects/xine/files/xine-lib/${PV}/xine-lib-${PV}.tar.xz"
SRC_URI = "https://downloads.sourceforge.net/xine/xine-lib-${PV}.tar.xz"

SRC_URI[sha256sum] = "5f10d6d718a4a51c17ed1b32b031d4f9b80b061e8276535b2be31e5ac4b75e6f"

# BK 20180623 added: mpg123 xvidcore  20210407 added jack 20211027 pulseaudio
# just added in case: lame lcms libcdio libraw1394 libavc1394 libdc1394 mpeg2dec schroedinger taglib openssl libsamplerate0
DEPENDS = "zlib libxext fontconfig freetype libx11 librsvg libpng ffmpeg alsa-lib \
           libdvdnav faac faad2 flac gdk-pixbuf mesa libglu liba52 libmad libmng \
           libtheora libva libvdpau libvorbis libogg libvpx libsdl libsdl-image \
           libsdl-mixer libsdl-ttf speex libmodplug vcdimager wavpack x264 libxcb \
           libxvmc lame lcms libcdio libraw1394 libavc1394 libdc1394 mpeg2dec \
           schroedinger taglib openssl libsamplerate0 libbluray libv4l libwebp \
           mpg123 xvidcore jack pulseaudio"

inherit pkgconfig gettext perlnative autotools

SROOT = "${WORKDIR}/recipe-sysroot"
# note, broken finding flac headers, have put in explicit path.

# 20210407 change --without-jack to --with-jack
EXTRA_OECONF = "--disable-dxr3 --disable-gnomevfs --disable-samba --enable-antialiasing \
                --without-imagemagick --with-freetype --with-fontconfig --without-esound \
                --with-jack --with-libflac --enable-faad --with-real-codecs-path=/usr/lib/codecs \
                --with-w32-path=/usr/lib/codecs --without-caca --with-pulseaudio \
                --with-alsa --enable-mad --enable-faad --enable-a52dec --enable-vdpau \
                --enable-dvb --disable-directfb --disable-aalib --with-xcb \
                --with-external-dvdnav --disable-ipv6 --with-sdl --enable-vpx \
                --enable-mad --enable-opengl --enable-glu --enable-xvmc --enable-vaapi \
                --disable-vcd --with-libFLAC-includes=${SROOT}/usr/include/FLAC \
                --enable-bluray --enable-libv4l"

#20240527 seems don't need anymore...
# ref: http://www.linuxfromscratch.org/blfs/view/svn/multimedia/xine-lib.html
XXXdo_configure:prepend() {
    sed -i -e 's|wand/magick_wand.h|MagickWand/MagickWand.h|' ${S}/src/video_dec/image.c
    sed -i -e 's/\(xcb-shape >= 1.0\)/xcb \1/' ${S}/m4/video_out.m4
}

FILES:${PN} += "${libdir}/xine"

SUMMARY = "multimedia player library"
HOMEPAGE = "https://www.xine-project.org/home"
