SUMMARY = "Portable C library for multiline text editing"
HOMEPAGE = "http://projects.gnome.org/gtksourceview/"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

#20240520 gtkdocize missing need gtk-doc-native
DEPENDS = "gtk+ libxml2 intltool-native gnome-common-native glib-2.0-native gtk-doc-native"

PNAME = "gtksourceview"

S = "${WORKDIR}/${PNAME}-${PV}"

#20221203
#distro_features_check.bbclass is deprecated, please use features_check.bbclass instead
#inherit gnomebase lib_package gettext features_check
#20240105
inherit lib_package gettext features_check pkgconfig autotools

REQUIRED_DISTRO_FEATURES = "x11"

SRC_URI = "https://distro.ibiblio.org/easyos/source/oe/dunfell/gtksourceview-2.10.5.tar.bz2 \
           file://gtk-doc.make \
           file://suppress-string-format-literal-warning.patch \
           file://0001-test-widget.c-fix-non-literal-format-string-issues.patch \
           "

SRC_URI[sha256sum] = "c585773743b1df8a04b1be7f7d90eecdf22681490d6810be54c81a7ae152191e"

do_configure:prepend() {
    cp ${WORKDIR}/gtk-doc.make ${S}/
    sed -i -e s:docs::g ${S}/Makefile.am
    echo "EXTRA_DIST = version.xml" > gnome-doc-utils.make
}

FILES:${PN} += " ${datadir}/gtksourceview-2.0"
