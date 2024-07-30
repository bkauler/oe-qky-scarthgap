# Recipe created by recipetool
# recipetool create -o gtksourceviewmm3_3.18.0.bb http://deb.debian.org/debian/pool/main/libg/libgtksourceviewmm/libgtksourceviewmm_3.18.0.orig.tar.xz

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=1d98bdbc4e7595f51d38e379da78af6d"

SRC_URI = "http://deb.debian.org/debian/pool/main/libg/libgtksourceviewmm/libgtksourceviewmm_${PV}.orig.tar.xz"
SRC_URI[sha256sum] = "51081ae3d37975dae33d3f6a40621d85cb68f4b36ae3835eec1513482aacfb39"

S = "${WORKDIR}/gtksourceviewmm-${PV}"

inherit pkgconfig autotools

DEPENDS = "gtksourceview3 gtkmm3 glibmm mm-common pangomm cairomm \
           glib-2.0 glib-2.0-native libsigc++-2.0"

EXTRA_OECONF = ""

SUMMARY = "gtkmm wrapper for gtksourceview3"
HOMEPAGE = "https://gitlab.gnome.org/GNOME/gtksourceviewmm/"

PROVIDES = "gtksourceviewmm3"
