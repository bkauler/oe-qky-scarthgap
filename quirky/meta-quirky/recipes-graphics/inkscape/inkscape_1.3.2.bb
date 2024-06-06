# Recipe created by recipetool
# recipetool create -o inkscape_1.3.2.bb https://inkscape.org/gallery/item/44615/inkscape-1.3.2.tar.xz

LICENSE = "BSD-3-Clause & GPL-2.0-only & GPL-3.0-only & LGPL-2.0-only & LGPL-2.1-only & LGPL-3.0-only"
LIC_FILES_CHKSUM = "file://CMakeScripts/COPYING-CMAKE-SCRIPTS;md5=54c7042be62e169199200bc6477f04d1 \
                    file://COPYING;md5=46f815712c095f667139ef42f2270d57 \
                    file://LICENSES/GPL-2.0-or-later.txt;md5=cc25c4e718ebe6811a78da4d0fbdddab \
                    file://LICENSES/GPL-2.0.txt;md5=0597d53b8f2dba85c174c771eead1324 \
                    file://LICENSES/GPL-3.0-or-later.txt;md5=495c5dccf51c1d4f4cf723450b1af016 \
                    file://LICENSES/GPL-3.0.txt;md5=d32239bcb673463ab874e80d47fae504 \
                    file://LICENSES/LGPL-2.1-or-later.txt;md5=388d98f2bb3c60a8a3105be2e7f812ee \
                    file://LICENSES/LGPL-2.1.txt;md5=58536dbdbca839fcfc5366fa07bfa758 \
                    file://LICENSES/LGPL-3.0-or-later.txt;md5=6aeb448bc450b9fe4c14e7f1d723e0f4 \
                    file://LICENSES/LGPL-3.0.txt;md5=3000208d539ec061b899bce1d9ce9404 \
                    file://buildtools/check_license_headers.py;md5=b7ae4485385dc5e6df4ee9ae50e87d07 \
                    file://packaging/wix/featuretree_nolicense.wxs;md5=42afdfed182bc01606528cda1f7bb6b6 \
                    file://share/doc/LICENSE;md5=2ddb103ee628b1d6bea487dfcb345f32 \
                    file://share/extensions/LICENSE.txt;md5=75859989545e37968a99b631ef42722e \
                    file://share/extensions/other/clipart/LICENSE.txt;md5=d32239bcb673463ab874e80d47fae504 \
                    file://share/extensions/other/extension-xaml/LICENSE.txt;md5=0597d53b8f2dba85c174c771eead1324 \
                    file://share/extensions/other/gcodetools/LICENSE.txt;md5=0597d53b8f2dba85c174c771eead1324 \
                    file://share/extensions/other/inkman/LICENSE.txt;md5=d32239bcb673463ab874e80d47fae504 \
                    file://share/themes/LICENSE.txt;md5=cc25c4e718ebe6811a78da4d0fbdddab \
                    file://src/3rdparty/2geom/COPYING-LGPL-2.1;md5=fad9b3332be894bab9bc501572864b29 \
                    file://src/3rdparty/2geom/COPYING-MPL-1.1;md5=bfe1f75d606912a4111c90743d6c7325 \
                    file://src/3rdparty/2geom/LICENSE.md;md5=676991d8aaee00f6ab19d996658dab0a \
                    file://src/3rdparty/adaptagrams/libavoid/LICENSE.LGPL;md5=49f14752fbcf469d6889865f91ef8493 \
                    file://src/3rdparty/adaptagrams/libvpsc/COPYING;md5=a6a79b625a6b5fa5d201e59fd0ac98ca \
                    file://src/3rdparty/libcroco/COPYING;md5=55ca817ccb7d5b5b66355690e9abc605 \
                    file://src/3rdparty/libcroco/COPYING.LIB;md5=55ca817ccb7d5b5b66355690e9abc605 \
                    file://src/3rdparty/libcroco/COPYRIGHTS;md5=c0833eb98ecbbc9aa218251d02a6ed2c \
                    file://testfiles/rendering_tests/fonts/LICENSES;md5=17d85695dbdaed9ae2867f2935b48124"

SRC_URI = "https://inkscape.org/gallery/item/44615/inkscape-${PV}.tar.xz \
           file://fix-libxml.patch"

SRC_URI[sha256sum] = "dbd1844dc443fe5e10d3e9a887144e5fb7223852fff191cfb5ef7adeab0e086b"

S = "${WORKDIR}/${BPN}-${PV}_2023-11-25_091e20ef0f"

#those python modules needed at runtime...
DEPENDS = "glib-2.0-native pango gtkmm3 libsoup-2.4 harfbuzz poppler gsl bdwgc lcms \
           gspell libxslt double-conversion libwpg librevenge libcdr libvisio \
           potrace zlib glib-2.0 jpeg readline libpng libepoxy libxml2 \
           libx11 virtual/libintl cairo boost gtksourceview4 imagemagick \
           boost-native libsoup popt \
           python3-cachecontrol python3-lxml python3-numpy python3-pyserial"

inherit cmake gettext pkgconfig python3-dir gtk-icon-cache bash-completion mime-xdg

EXTRA_OECMAKE = ""

FILES:${PN} += "${datadir}/metainfo"

INSANE_SKIP:${PN} += "useless-rpaths"
INSANE_SKIP:${PN} += "dev-so"

SUMMARY = "Vector graphics editor"
HOMEPAGE = "https://inkscape.org/"
