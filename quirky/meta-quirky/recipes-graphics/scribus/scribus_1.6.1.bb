# Recipe created by recipetool
# recipetool create -o scribus_1.6.1.bb https://sourceforge.net/projects/scribus/files/scribus/1.6.1/scribus-1.6.1.tar.xz

LICENSE = "GPL-2.0-or-later & LGPL-2.0-or-later & BSD-3-Clause & MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=30a94f7602927f2247caa37d0289c6f5 \
                    file://OSX-package/inputs/Resources/English.lproj/License.txt;md5=959ce048b21311f6f71c219bd129e44f \
                    file://doc/cs/COPYING;md5=c5cffbe2fa807a80465e80cbcb6ce106 \
                    file://doc/de/COPYING;md5=c5cffbe2fa807a80465e80cbcb6ce106 \
                    file://doc/en/COPYING;md5=c5cffbe2fa807a80465e80cbcb6ce106 \
                    file://doc/fr/COPYING;md5=c5cffbe2fa807a80465e80cbcb6ce106 \
                    file://doc/it/COPYING;md5=c5cffbe2fa807a80465e80cbcb6ce106 \
                    file://doc/pl/COPYING;md5=c5cffbe2fa807a80465e80cbcb6ce106 \
                    file://resources/iconsets/1_5_1/COPYING;md5=4a2c07248db4fdea8724e5d5369b17f9 \
                    file://resources/profiles/srgb.license;md5=487557e437d9814091b955e166c51a98 \
                    file://resources/swatches/COPYING_RESENE.txt;md5=ad09f7ac23f11813f65b9413274f10ae \
                    file://scribus/plugins/import/ps/LICENSE;md5=60e6eaccf5fcb47560c967babc479246 \
                    file://scribus/plugins/scripter/doc/LICENSE;md5=fd4afef0bbb58a473655fc364d421750 \
                    file://scribus/third_party/hyphen/COPYING;md5=d45e3467790c1cae990cc9ca3293bc97 \
                    file://scribus/third_party/hyphen/COPYING.LGPL;md5=d8045f3b8f929c1cb29a1e3fd737b499 \
                    file://scribus/third_party/hyphen/COPYING.MPL;md5=bfe1f75d606912a4111c90743d6c7325 \
                    file://scribus/third_party/pgf/COPYING;md5=6b078fb41643cfda09c2606b7d38d8ed \
                    file://scribus/ui/resourcemanagerlicense.cpp;md5=7f2b1310f27c60d55e28272620562043 \
                    file://scribus/ui/resourcemanagerlicense.h;md5=8877c84c1720df83443583ed8c725837 \
                    file://scribus/ui/resourcemanagerlicensebase.ui;md5=267d815d6a4c9ea68b707b1dcdd9419c"

SRC_URI = "https://sourceforge.net/projects/scribus/files/scribus/${PV}/scribus-${PV}.tar.xz"
SRC_URI[sha256sum] = "e09dd78e6db61d01b9321108fededbccd6ec0ab352dd5bafdb8b041f0ef79e99"

inherit cmake_qt5 cmake_extra_sanity pkgconfig python3native gtk-icon-cache mime mime-xdg

# 20221201 BK
# 20221205 remove:  libwpd-native libpagemaker-native libfreehand-native
#      librevenge-native libvisio-native libmspub-native
DEPENDS = "icu cairo cairo-native freetype freetype-native tiff boost \
           libpagemaker virtual/libgl jpeg libcdr zlib libxml2 \
           poppler librsvg sqlite3 hunspell libdrm expat libjpeg-turbo \
           lcms pixman libpng harfbuzz ghostscript libxcb libxrender \
           libx11 libxext openssl mesa gnutls glib-2.0 graphite2 xz util-linux \
           libxau libxdmcp libxshmfence libgcrypt libxdamage libxfixes libxxf86vm \
           libidn libunistring nettle gmp libxau glib-2.0-native \
           qtbase qtbase-native qttools qttools-native \
           python3 python3-native libwpd libmspub \
           libvisio libfreehand \
           librevenge libpagemaker cups \
           cups-filters fontconfig fontconfig-native"


EXTRA_OECMAKE = "-DCMAKE_BUILD_TYPE=Release -DWITH_PODOFO=OFF"

FILES:${PN} += " \
    ${datadir}/icons \
    ${datadir}/mime \
    ${datadir}/metainfo \
"

SUMMARY = "Scribus desktop publishing"
HOMEPAGE = "https://www.scribus.net/"
