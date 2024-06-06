# Recipe created by recipetool
# recipetool create -o gcolorsel_1.0.0.bb http://distro.ibiblio.org/quirky/quirky6/sources/t2/april/gcolorsel-1.0.0.tar.bz2

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

SRC_URI = "http://distro.ibiblio.org/quirky/quirky6/sources/t2/april/gcolorsel-${PV}.tar.bz2"
SRC_URI[md5sum] = "f9259ac6e4194585d70025205fae0576"
SRC_URI[sha256sum] = "6bfcbe1de8bcb0f0e0f50200dedd10d1a891694904a90ca10a3ee01e3f8cb3ef"

DEPENDS = "gtk+ gdk-pixbuf"
inherit pkgconfig gettext

do_configure () {
    sed -i -e 's%/usr/local%/usr%' Makefile
    sed -i -e 's%^CC = %# CC = %' Makefile
    sed -i -e 's%^CFLAGS = %CFLAGS += %' Makefile
    sed -i -e 's%^LFLAGS = %LFLAGS = $(LDFLAGS) %' Makefile
    #20240106 fail (see also gfontsel):
    # | gcc: error: unrecognized command line option ‘-fcanon-prefix-map’; did you mean ‘-fmacro-prefix-map=’?
    # | Makefile:133: depend: No such file or directory
    #compile directly for host os...
    #oe_runmake depend
    gcc -MM -O2 -pipe -g -feliminate-unused-debug-types -fmerge-constants -fomit-frame-pointer `pkg-config --cflags gtk+-2.0` -DGTK_DISABLE_DEPRECATED -DVERSION="1.0.0" -DPACKAGE="gcolorsel" callbacks.c interface.c main.c >depend
}

do_compile () {
    oe_runmake
}

do_install () {
    install -d ${D}/usr/bin
    install gcolorsel ${D}/usr/bin
}


HOMEPAGE = "http://nixbit.com/software/gcolorsel-review/"
SUMMARY = "It is a program to select colors with a GTK+ 2.0 interface"
