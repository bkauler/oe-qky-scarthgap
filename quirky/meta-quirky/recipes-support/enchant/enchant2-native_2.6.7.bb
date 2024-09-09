SUMMARY = "Enchant Spell checker API Library"
SECTION = "libs"
HOMEPAGE = "https://abiword.github.io/enchant/"
BUGTRACKER = "https://github.com/AbiWord/enchant/issues/"
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = "glib-2.0-native groff-native"

inherit autotools pkgconfig github-releases native

SRC_URI = "${GITHUB_BASE_URI}/download/v${PV}/enchant-${PV}.tar.gz"
SRC_URI[sha256sum] = "a1c2e5b59acca000bbfb24810af4a1165733d407f2154786588e076c8cd57bfc"

GITHUB_BASE_URI = "https://github.com/AbiWord/enchant/releases"

S = "${WORKDIR}/enchant-${PV}"

PACKAGECONFIG = ""
PACKAGECONFIG[aspell]  = "--with-aspell,--without-aspell,aspell,aspell"
PACKAGECONFIG[hunspell]  = "--with-hunspell,--without-hunspell,hunspell,hunspell"

FILES:${PN} += " \
    ${datadir}/enchant-2 \
    ${libdir}/enchant-2 \
"
FILES:${PN}-staticdev += "${libdir}/enchant-2/*.a"
