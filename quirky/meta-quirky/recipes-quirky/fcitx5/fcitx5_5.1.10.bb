# Recipe created by recipetool
# recipetool create -o fcitx5_5.1.10.bb https://github.com/fcitx/fcitx5/archive/refs/tags/5.1.10.tar.gz

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-or-later.txt;md5=2a4f4fd2128ea2f65047ee63fbca9f68"

SRC_URI = "https://github.com/fcitx/fcitx5/archive/refs/tags/${PV}.tar.gz"
SRC_URI[sha256sum] = "a33f71e60a840b37fed7b04d2dcc7544a89bda78e4f4b2df7946ff358032a903"

DEPENDS = "zlib libxcb expat \
           xcb-imdkit extra-cmake-modules-native xcb-util xcb-util-keysyms \
           expat json-c dbus fmt enchant2 libx11 libuv util-linux-libuuid \
           xcb-util-wm cairo libxkbfile libxkbcommon iso-codes pango \
           gdk-pixbuf ninja-native xkeyboard-config fcitx5-tools-native"

inherit cmake gettext pkgconfig

EXTRA_OECMAKE = "-DENABLE_EMOJI=Off -DENABLE_WAYLAND=Off -DENABLE_TEST=Off \
    -DENABLE_ENCHANT=On -DENABLE_X11=On -DENABLE_DBUS=On -DUSE_SYSTEMD=Off \
    -DENABLE_LIBUUID=On"

SUMMARY = "Input method framework"
HOMEPAGE = "https://fcitx-im.org/wiki/Fcitx_5"

