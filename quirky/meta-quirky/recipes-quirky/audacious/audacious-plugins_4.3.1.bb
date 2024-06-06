# Recipe created by recipetool
# recipetool create -o audacious-plugins_4.3.1.bb https://distfiles.audacious-media-player.org/audacious-plugins-4.3.1.tar.bz2

LICENSE = "GPL-2.0-only & MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=93d720bf0fa5e0c402ae680fadb29a13 \
                    file://src/aosd/ghosd-license;md5=9ad6f7e25b05e7789e737e9712c8c118 \
                    file://src/psf/peops/License.txt;md5=e7ddda57ffb56f5b2b82fe582985610a \
                    file://src/psf/peops2/License.txt;md5=2d9813d7528006ff6bd80b8fd42fb290 \
                    file://src/xsf/desmume/COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

SRC_URI = "https://distfiles.audacious-media-player.org/audacious-plugins-${PV}.tar.bz2"
SRC_URI[sha256sum] = "2dea26e3af583a2d684df240b27b2b2932bcd653df4db500a85f4fe5d5fdc8a6"

DEPENDS = "ffmpeg virtual/libgl gtk+3 libsdl audacious glib-2.0 libsdl2 lame \
   libxml2 faad2 dbus dbus-glib dbus-native dbus-glib-native libsndfile1 libnotify \
   alsa-lib neon faac mpg123 libvorbis flac jack pulseaudio wavpack audacious"

inherit pkgconfig gettext autotools meson mime-xdg python3native

EXTRA_OEMESON = "--buildtype=release -Dgtk3=true -Dqt=false -Dqt6=false"

HOMEPAGE = "https://audacious-media-player.org/"
SUMMARY = "plugins for audio player"

