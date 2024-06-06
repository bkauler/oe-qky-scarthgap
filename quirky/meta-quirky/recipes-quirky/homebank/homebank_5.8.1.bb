# Recipe created by recipetool
# recipetool create -o homebank_5.8.1.bb https://www.gethomebank.org/public/sources/homebank-5.8.1.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "https://www.gethomebank.org/public/sources/homebank-${PV}.tar.gz"
SRC_URI[sha256sum] = "60c35feafe341aec8fed9de4b0a875dc0f5c1674c5f5804ff7190a6c6c53dc01"

DEPENDS = "glib-2.0 glib-2.0-native gtk+3 intltool-native libsoup libofx"

inherit pkgconfig gettext autotools mime-xdg

EXTRA_OECONF = "--with-ofx"

FILES:${PN} += "${datadir}/mime ${datadir}/application-registry ${datadir}/icons ${datadir}/mime-info ${datadir}/appdata"

HOMEPAGE = "https://www.gethomebank.org/en/"
SUMMARY = "Personal finance management"

