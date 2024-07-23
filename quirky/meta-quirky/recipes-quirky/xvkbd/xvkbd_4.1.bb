# Recipe created by recipetool
# recipetool create -o xvkbd_4.1.bb http://t-sato.in.coocan.jp/xvkbd/xvkbd-4.1.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

#patches from jamesbond and debian...
SRC_URI = "http://t-sato.in.coocan.jp/xvkbd/xvkbd-${PV}.tar.gz \
           file://xvkbd-3.9-polish.patch \
           file://xvkbd-4.1-fonts.patch \
           file://destdir.patch \
           file://no-common.patch \
           file://gcc-14.patch"

SRC_URI[sha256sum] = "952d07df0fe1e45286520b7c98b4fd00fd60dbf3e3e8ff61e12c259f76a3bef4"

DEPENDS = "libx11 libxaw libxmu libxt libsm libice libxpm libxext libxtst"

inherit autotools pkgconfig

do_install () {
    install -d ${D}/usr/bin
    install -m755 ${B}/xvkbd ${D}/usr/bin
    install -d ${D}/etc/X11/app-defaults
    for aFILE in XVkbd XVkbd-belgian XVkbd-common XVkbd-danish XVkbd-fitaly XVkbd-french2 XVkbd-french XVkbd-german XVkbd-greek XVkbd-hebrew XVkbd-icelandic XVkbd-italian XVkbd-jisx6002 XVkbd-jisx6004 XVkbd-korean XVkbd-latin1 XVkbd-norwegian XVkbd-polish XVkbd-portuguese XVkbd-russian XVkbd-slovene XVkbd-small XVkbd-spanish XVkbd-strip XVkbd-swedish XVkbd-swissgerman XVkbd-turkish XVkbd-turkishF XVkbd-uk
    do
      install -m644 ${S}/${aFILE}.ad ${D}/etc/X11/app-defaults/${aFILE}
    done
    install -d ${D}/usr/share/xvkbd
    install -m644 ${S}/words.english ${D}/usr/share/xvkbd
}

HOMEPAGE = "http://homepage3.nifty.com/tsato/xvkbd/"
SUMMARY = "A virtual keyboard for X"
