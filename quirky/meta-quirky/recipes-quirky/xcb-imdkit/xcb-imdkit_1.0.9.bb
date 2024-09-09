# Recipe created by recipetool
# recipetool create -o xcb-imdkit_1.0.9.bb https://github.com/fcitx/xcb-imdkit/archive/refs/tags/1.0.9.tar.gz

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-only.txt;md5=fabba2a3bfeb22a6483d44e9ae824d3f"

SRC_URI = "https://github.com/fcitx/xcb-imdkit/archive/refs/tags/${PV}.tar.gz"

SRC_URI[sha256sum] = "c2f0bbad8a335a64cdc7c19ac7b6ea1f0887dd6300ca9a4fa2e2fec6b9d3f695"

DEPENDS = "libxcb libxcb-native extra-cmake-modules-native xcb-util xcb-util-keysyms"

inherit cmake

EXTRA_OECMAKE = ""

SUMMARY = "Input method development support for xcb"
HOMEPAGE = "https://github.com/fcitx/xcb-imdkit"

