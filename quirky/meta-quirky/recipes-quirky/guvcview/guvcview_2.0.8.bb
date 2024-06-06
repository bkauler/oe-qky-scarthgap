# Recipe created by recipetool
# recipetool create -o guvcview_2.0.8.bb https://sourceforge.net/projects/guvcview/files/source/guvcview-src-2.0.8.tar.bz2

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://sourceforge.net/projects/guvcview/files/source/guvcview-src-${PV}.tar.bz2"
SRC_URI[sha256sum] = "a21f4e448286666cf27bafef5290cc953a0a1796b752e5bbe521266dc1230c81"

S = "${WORKDIR}/${BPN}-src-${PV}"

DEPENDS = "v4l-utils glib-2.0 portaudio-v19 pulseaudio ffmpeg libsdl2 libusb1 \
   gtk+3 libpng intltool-native gsl eudev alsa-lib jack libjpeg-turbo libxcb \
   libx11 libice libsm libxtst libsndfile1 libcap libcap-ng libvpx libwebp \
   opencore-amr lame speex libtheora libvorbis x264 x265 xvidcore libxrandr \
   libxcursor libxext libxi libxcomposite libxdamage libxfixes libepoxy \
   harfbuzz pango fontconfig freetype libxrender libffi libxau libxdmcp \
   libogg libdrm graphite2 expat glib-2.0-native"

inherit pkgconfig gettext autotools

EXTRA_OECONF = "--with-gnu-ld"

HOMEPAGE = "https://sourceforge.net/projects/guvcview/"
SUMMARY = "Capture and view video from V4L2 devices"
