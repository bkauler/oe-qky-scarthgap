# Recipe created by recipetool
# recipetool create -o homebank_5.7.3.bb https://www.gethomebank.org/public/sources/homebank-5.7.3.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "https://www.gethomebank.org/public/sources/homebank-${PV}.tar.gz"

SRC_URI[sha256sum] = "69df172a599acd66629bf98b3669ec152cb93a78bbcafdc7431617dd25f35dc5"

DEPENDS = "glib-2.0 glib-2.0-native gtk+3 intltool-native libsoup libofx"

inherit pkgconfig gettext autotools mime-xdg

EXTRA_OECONF = "--with-ofx"

FILES:${PN} += "${datadir}/mime ${datadir}/application-registry ${datadir}/icons ${datadir}/mime-info ${datadir}/appdata"

HOMEPAGE = "https://www.gethomebank.org/en/"
SUMMARY = "Personal finance management"
