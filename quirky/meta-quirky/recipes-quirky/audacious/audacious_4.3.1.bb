# Recipe created by recipetool
# recipetool create -o audacious_4.3.1.bb https://distfiles.audacious-media-player.org/audacious-4.3.1.tar.bz2

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=39d0acc8bbd58d8843a0f2a1b8ae2d84"

SRC_URI = "https://distfiles.audacious-media-player.org/audacious-${PV}.tar.bz2"
SRC_URI[sha256sum] = "85e9e26841505b51e342ee72a2d05f19bef894f567a029ebb3f3e0c1adb42042"

#DEPENDS = "libarchive gtk+3 glib-2.0 gtk+ qtbase"
DEPENDS = "gtk+ glib-2.0 dbus dbus-glib dbus-native dbus-glib-native alsa-lib \
           alsa-plugins jack pulseaudio python3-packaging-native \
           libarchive gtk+3"

#inherit gettext pkgconfig autotools-brokensep mime-xdg python3native
inherit gettext pkgconfig meson mime-xdg python3native

#EXTRA_OECONF = ""
EXTRA_OEMESON = "--buildtype=release -Dgtk3=true -Dlibarchive=true -Dqt=false \
                 -Dqt6=false -Dbuildstamp=EOS"

HOMEPAGE = "https://audacious-media-player.org/"
SUMMARY = "audio player"
