SUMMARY = "Library for importing and converting Corel WordPerfect(tm) graphics"
HOMEPAGE = "http://libwpg.sourceforge.net/"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING.MPL;md5=815ca599c9df247a0c7f619bab123dad \
"

SRC_URI = "http://dev-www.libreoffice.org/src/${BPN}-${PV}.tar.xz"

SRC_URI[sha256sum] = "b55fda9440d1e070630eb2487d8b8697cf412c214a27caee9df69cec7c004de3"

inherit autotools pkgconfig

DEPENDS = "librevenge libwpd zlib"
RDEPENDS:${PN} = "perl"
