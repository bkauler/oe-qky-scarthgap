# Recipe created by recipetool
# recipetool create -o gtkspellmm3_3.0.bb http://deb.debian.org/debian/pool/main/g/gtkspellmm/gtkspellmm_3.0.5+dfsg.orig.tar.xz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "http://deb.debian.org/debian/pool/main/g/gtkspellmm/gtkspellmm_${PV}.5%2Bdfsg.orig.tar.xz"
SRC_URI[sha256sum] = "3346edf38253cf6d6dbd93fb14097b6c7cca4689cbe190b9a58dec7da8e86939"

S = "${WORKDIR}/gtkspellmm-${PV}.5+dfsg.orig"


inherit pkgconfig autotools

EXTRA_OECONF = ""

PROVIDES = "gtkspellmm3"

DEPENDS = "gtk+3 enchant2 glibmm gtkmm3 libsigc++-2.0 glib-2.0 gtkspell3 \
           gtk-doc-native doxygen-native libxslt-native"

SUMMARY = "gtkmm wrapper for gtkspell3"
HOMEPAGE = "https://gtkspell.sourceforge.net/"


