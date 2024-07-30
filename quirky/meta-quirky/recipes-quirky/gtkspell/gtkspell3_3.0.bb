# Recipe created by recipetool
# recipetool create -o gtkspell3_3.0.bb http://deb.debian.org/debian/pool/main/g/gtkspell3/gtkspell3_3.0.10.orig.tar.xz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "http://deb.debian.org/debian/pool/main/g/gtkspell3/gtkspell3_${PV}.10.orig.tar.xz"
SRC_URI[sha256sum] = "b040f63836b347eb344f5542443dc254621805072f7141d49c067ecb5a375732"

S = "${WORKDIR}/${BPN}-${PV}.10"

inherit gettext pkgconfig autotools python3-dir python3native

EXTRA_OECONF = "--enable-introspection=yes --enable-gtk3"

DEPENDS = "enchant2 gtk+3 glib-2.0 glib-2.0-native intltool-native vala-native \
           gtk-doc-native qemu-native qemu-system-native qemu-helper-native"

PROVIDES = "gtkspell3"

SUMMARY = "spell check"
HOMEPAGE = "https://gtkspell.sourceforge.net/"
