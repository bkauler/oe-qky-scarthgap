# Recipe created by recipetool
# recipetool create -o mtpaint_3.50.10.bb https://distro.ibiblio.org/easyos/source/alphabetical/m/mtpaint-3.50.10.tar.gz

# note, Mark Tyler is the original author: http://mtpaint.sourceforge.net/
# now maintained by 'wjaguar'.

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/m/mtpaint-${PV}.tar.gz"

SRC_URI[sha256sum] = "cf77cd41d80e34d8f3ca6f175f1dfb17770f8c42c4ffc56d18925ccb040791df"

#20241008 add: libwebp
DEPENDS = "libx11 gtk+ gifsicle giflib libxpm jpeg tiff jasper lcms fontconfig \
           libpng zlib freetype freetype-native fontconfig-native libwebp"

inherit pkgconfig gettext

do_configure () {
    #note, most of these options not really needed, as will autodetect...
    #20240106 fix it was installing to /usr/local...
    #20241008 add: webp
    ./configure --cpu=${TARGET_ARCH} --prefix=/usr intl man webp jpeg tiff jasper lcms2 gtk2 cflags
}

do_compile () {
	oe_runmake
}

do_install () {
    oe_runmake install DESTDIR=${D}
}

SUMMARY = "A simple GTK+1/2/3 painting program"
HOMEPAGE = "https://github.com/wjaguar/mtPaint"
