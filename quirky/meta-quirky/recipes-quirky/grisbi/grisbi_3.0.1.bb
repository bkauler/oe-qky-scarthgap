# Recipe created by recipetool
# recipetool create -o grisbi_3.0.1.bb https://sourceforge.net/projects/grisbi/files/grisbi%20stable/3.0.x/3.0.1/grisbi-3.0.1.tar.bz2

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

SRC_URI = "https://sourceforge.net/projects/grisbi/files/grisbi%20stable/3.0.x/${PV}/grisbi-${PV}.tar.bz2"

SRC_URI[sha256sum] = "57f54eafae5cd05c432eb31d7d9d1119c438580dc7159f3b8999c2135a74c208"

#20240519 change libxml++
DEPENDS = "openssl gtk+3 intltool-native glib-2.0 libgsf libxml2 zlib libofx \
           libical libxml++-5.0 glib-2.0-native"

inherit gettext pkgconfig autotools mime-xdg

EXTRA_OECONF = "--without-goffice --with-ofx --with-openssl --with-libxml2 --disable-frenchdoc --disable-schemas-compile"

FILES:${PN} += "${datadir}/mime ${datadir}/icons ${datadir}/mime-info ${datadir}/glib-2.0"

HOMEPAGE = "http://www.grisbi.org/"
SUMMARY = "A personal account management package"
