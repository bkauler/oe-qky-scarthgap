DESCRIPTION = "GTK+ based simple text editor"
HOMEPAGE = "http://tarot.freeshell.org/leafpad"
AUTHOR = "Tarot Osuji <tarot@sdf.lonestar.org>"
SECTION = "x11/applications"
SRC_URI = "http://savannah.nongnu.org/download/${PN}/${PN}-${PV}.tar.gz"

#PR = "r0"
# 20210121 PR_NUM is defined in local.conf... 20220131 removed...
#PR = "r${@int(PR_NUM) + 1}"

DEPENDS = "gtk+ intltool-native glib-2.0-native"
# error: "glib-gettextize: command not found"
# but it is there, installed by 'glib-2.0' ...fix set dep 'glib-2.0-native'
# error: intltoolize: command not found ...fix, set dep 'intltool-native'

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

inherit autotools pkgconfig mime-xdg

#20200921 override OE...
CFLAGS:append = " -Wno-error=format-security "

# Printing feature requires libgnomeprintui-2.2
EXTRA_OECONF = " --enable-chooser --disable-print"

SRC_URI[sha256sum] = "07d3f712f4dbd0a33251fd1dee14e21afdc9f92090fc768c11ab0ac556adbe97"

SUMMARY = "A GTK+ based simple text editor"
