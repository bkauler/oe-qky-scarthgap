#require recipes-graphics/xorg-app/xorg-app-common.inc
SUMMARY = "X application"
HOMEPAGE = "http://www.x.org/"
BUGTRACKER = "https://bugs.freedesktop.org"
SECTION = "x11/apps"
LICENSE = "MIT"
DEPENDS = "util-macros-native virtual/libx11"

# depends on virtual/libx11
REQUIRED_DISTRO_FEATURES = "x11"

SRC_URI_EXT = "bz2"
SRC_URI = "${XORG_MIRROR}/individual/app/${BPN}-${PV}.tar.${SRC_URI_EXT}"

inherit autotools pkgconfig features_check

FILES:${PN} += " ${libdir}/X11/${BPN} ${datadir}/X11/app-defaults/"

####

SUMMARY = "X.Org X11 X client utilities"
HOMEPAGE = "http://cgit.freedesktop.org/xorg/app/xsetmode/"
DESCRIPTION = "xsetmode sets the mode of an XInput device to either absolute \
or relative."
SECTION = "x11/app"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=9b37e00e7793b667cbc64f9df7b6d733"

DEPENDS += "libxi"
BBCLASSEXTEND = "native"

SRC_URI[md5sum] = "d074e79d380b031d2f60e4cd56538c93"
SRC_URI[sha256sum] = "988b47cd922991c6e6adbce15dc386ac75690b61744b526c3af5a4eaa9afa0aa"
