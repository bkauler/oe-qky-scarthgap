#ref: https://github.com/ashie/meta-inputmethod/blob/master/recipes-support/fcitx5-tools-native/fcitx5-tools-native_5.0.8.bb

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-or-later.txt;md5=2a4f4fd2128ea2f65047ee63fbca9f68"

SRC_URI = "https://github.com/fcitx/fcitx5/archive/refs/tags/${PV}.tar.gz"
SRC_URI[sha256sum] = "a33f71e60a840b37fed7b04d2dcc7544a89bda78e4f4b2df7946ff358032a903"

S = "${WORKDIR}/fcitx5-${PV}"

FILESEXTRAPATHS =. "${FILE_DIRNAME}/fcitx5-tools:"

DEPENDS = "extra-cmake-modules-native fmt-native util-linux-libuuid-native \
           libuv-native gettext-native enchant2-native"

inherit cmake pkgconfig gettext native

OECMAKE_GENERATOR = "Unix Makefiles"

EXTRA_OECMAKE = " \
    -DENABLE_TEST=OFF \
    -DENABLE_COVERAGE=OFF \
    -DENABLE_X11=OFF \
    -DENABLE_WAYLAND=OFF \
    -DENABLE_DBUS=OFF \
    -DENABLE_KEYBOARD=OFF \
    -DUSE_SYSTEMD=OFF \
    -DENABLE_XDGAUTOSTART=OFF \
    -DENABLE_EMOJI=OFF \
    -DENABLE_LIBUUID=OFF \
    -DENABLE_METAINFO=OFF \
    -DDL_INCLUDE_DIR=/usr/include \
    -DLibIntl_INCLUDE_DIR=/usr/include \
    -DPTHREAD_INCLUDE_DIR=/usr/include \
    -DPTHREAD_LIB_FOUND=/usr/lib \
    -DENABLE_ENCHANT=ON \
"

LDFLAGS:append = " -ldl "

do_compile() {
    cmake --build ${B} --target Fcitx5Utils
    cmake --build ${B} --target Fcitx5Config
    cmake --build ${B} --target comp-spell-dict
}

do_install() {
    DESTDIR=${D} make install -C src/lib/fcitx-utils
    DESTDIR=${D} make install -C src/lib/fcitx-config
    install -d ${D}/${bindir}
    install -d ${D}/${libdir}/cmake/Fcitx5Utils
    install -m 755 src/modules/spell/comp-spell-dict ${D}/${bindir}/Fcitx5::comp-spell-dict
    install -m 644 src/lib/fcitx-utils/libFcitx5Utils.so* ${D}/${libdir}
    install -m 644 src/lib/fcitx-config/libFcitx5Config.so* ${D}/${libdir}
    install -m 644 ${S}/cmake/Fcitx5CompilerSettings.cmake ${D}/${libdir}/cmake/Fcitx5Utils
}

