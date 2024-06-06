# Recipe created by recipetool
# recipetool create -o liblangtag_0.6.7.bb http://deb.debian.org/debian/pool/main/libl/liblangtag/liblangtag_0.6.7.orig.tar.bz2

LICENSE = "LGPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=e6a600fd5e1d9cbde2d983680233ad02"

SRC_URI = "http://deb.debian.org/debian/pool/main/libl/liblangtag/liblangtag_${PV}.orig.tar.bz2 \
  file://no_encoding.patch \
  file://pthread.patch \
  file://0001-configure.ac-add-missing-HAVE_INTROSPECTION-check.patch \
  file://0002-configure.ac-remove-untranslated-AX_CHECK_ENABLE_DEB.patch"

SRC_URI[sha256sum] = "5ed6bcd4ae3f3c05c912e62f216cd1a44123846147f729a49fb5668da51e030e"

inherit gettext autotools gobject-introspection pkgconfig

DEPENDS = "libxml2 gtk-doc-native"

EXTRA_OECONF = ""

export GIR_EXTRA_LIBS_PATH="${B}/liblangtag/.libs"

HOMEPAGE = "http://tagoh.bitbucket.org/liblangtag/"
SUMMARY = "interface library to access/deal with tags for identifying languages, which is described in RFC 5646"

#getting do_configure errors, use existing configure script...
do_configure() {
 oe_runconf
}

BBCLASSEXTEND = "native"
