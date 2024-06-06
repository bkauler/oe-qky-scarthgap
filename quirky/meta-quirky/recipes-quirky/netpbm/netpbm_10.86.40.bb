# Recipe created by recipetool
# recipetool create -o netpbm_10.86.40.bb https://sourceforge.net/projects/netpbm/files/super_stable/10.86.40/netpbm-10.86.40.tgz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://doc/GPL_LICENSE.txt;md5=079b27cd65c86dbc1b6997ffde902735"

SRC_URI = "https://sourceforge.net/projects/netpbm/files/super_stable/${PV}/netpbm-${PV}.tgz"
SRC_URI[sha256sum] = "7098c0926474303c6fe2d17c0dfb88f24622d1b3a590b6d75495d62ea933b324"

DEPENDS = "flex-native libx11 jpeg libpng tiff libxpm librsvg perl-native libxml2 zlib librsvg"

inherit pkgconfig perlnative

#override OE...
CFLAGS:append = " -Wno-error=format-security "

do_configure () {
 sed -i -e 's%libpng\-config \-\-ldflags%pkg-config --libs libpng16%' buildtools/configure.pl
 sed -i -e 's%libpng\-config \-\-cflags%pkg-config --cflags libpng16%' buildtools/configure.pl
 sed -i -e 's%xml2\-config %pkg-config libxml-2.0 %' buildtools/configure.pl
 sed -i -e 's%libpng$(PNGVER)\-config %pkg-config libpng16 %' converter/other/Makefile
 sed -i -e 's%xml2\-config %pkg-config libxml-2.0 %' converter/other/Makefile
 sed -i -e 's%xml2\-config %pkg-config libxml-2.0 %' GNUmakefile
 
 #ref: https://t2sde.org/packages/netpbm
 #echo -e "\ngnu\nregular\nshared\ny\n\n\n\n\n\n\n\n\n\n`pkgprefix libdir libx11`/libX11.so\n\nnone\n" | ./configure
 echo -e '\ngnu\nregular\nshared\nn\n\n\n\n\n\n\n\n\n\n\n' | ./configure

 #20210103 build these to run on host system...
 #(it does this because config.mk is broken)
 #cp -a -f ./config.mk ./config.mkBAK
 #sed -i -e 's%config\.mk%config.mkBAK%' buildtools/Makefile
 #20240105 this error due to old gcc in dunfell host os:
 # gcc: error: unrecognized command line option ‘-fcanon-prefix-map’; did you mean ‘-fmacro-prefix-map=’?
 #do it this way...
 CURRWD="$(pwd)"
 cd ${S}/buildtools
 gcc -c -o libopt.o -DSHLIBPREFIXLIST="\"lib\"" -DEXPLICIT -O3 -ffast-math  -pedantic -fno-common -Wall -Wno-uninitialized -Wmissing-declarations -Wimplicit -Wwrite-strings -Wmissing-prototypes -Wundef -Wno-unknown-pragmas -Wno-strict-overflow libopt.c
 gcc -o libopt  libopt.o
 gcc -c -o typegen.o  typegen.c
 gcc -o typegen  typegen.o
 gcc -c -o endiangen.o  endiangen.c
 gcc -o endiangen  endiangen.o
 cd ${CURRWD}
 
 #20210103 cross-compiling for rpi4, binaries all x86_64, fix...
 sed -i '/^CC =/d' ./config.mk
 sed -i '/^CFLAGS =/d' ./config.mk
 sed -i '/^CFLAGS_MERGE =/d' ./config.mk
 sed -i -e 's%^LDRELOC =.*%LDRELOC = $(LD) --reloc%' ./config.mk
}

do_compile () {
	oe_runmake
}

do_install () {
    #do it manually...
    install -d ${D}/usr/lib
    install -d ${D}/usr/bin
    install -d ${D}/usr/share/netpbm
    install -m755 lib/libnetpbm.so.11.86 ${D}/usr/lib/
    ln -s libnetpbm.so.11.86 ${D}/usr/lib/libnetpbm.so.11
    ln -s libnetpbm.so.11 ${D}/usr/lib/libnetpbm.so
    #20210117 added converter/other/xwdtopnm needed by screeny... also converter/other/svgtopam
    for aX in converter/other/anytopnm converter/other/giftopnm converter/other/jpegtopnm editor/pamcomp editor/pamcut editor/pamdice analyzer/pamfile editor/pamscale analyzer/pamslice other/pamsplit editor/pamstretch converter/other/pamtopng converter/other/pamtotiff generator/pbmtext converter/pbm/pbmtoxbm generator/pgmramp converter/other/pgmtopbm converter/other/pgmtoppm converter/other/pngtopam editor/pnmalias editor/pnmcat other/pnmcolormap editor/pnmconvol editor/pnmnlfilt editor/pnmquant editor/pnmremap editor/pnmrotate editor/pnmsmooth converter/other/pnmtojpeg converter/other/pnmtopng converter/other/pnmtops editor/ppmdither editor/ppmlabel editor/ppmquant generator/ppmrainbow generator/ppmrough converter/ppm/ppmtobmp converter/ppm/ppmtogif converter/other/ppmtopgm converter/ppm/ppmtoxpm converter/other/pstopnm converter/other/tifftopnm converter/pbm/xbmtopbm converter/ppm/xpmtoppm converter/other/xwdtopnm converter/other/svgtopam
    do
     install -m755 ${aX} ${D}/usr/bin/
    done
    ln -s pamtopng ${D}/usr/bin/pamrgbatopng
    ln -s pamslice ${D}/usr/bin/pgmslice
    ln -s pngtopam ${D}/usr/bin/pngtopnm
    ln -s pamcomp ${D}/usr/bin/pnmcomp
    ln -s pamcut ${D}/usr/bin/pnmcut
    ln -s pamfile ${D}/usr/bin/pnmfile
    ln -s pamstretch ${D}/usr/bin/pnminterp
    ln -s pamscale ${D}/usr/bin/pnmscale
    ln -s pamsplit ${D}/usr/bin/pnmsplit
    ln -s pamtotiff ${D}/usr/bin/pnmtotiff
    ln -s pnmtojpeg ${D}/usr/bin/ppmtojpeg
    
    install -m644 converter/other/pnmtopalm/palmgray1.map ${D}/usr/share/netpbm/
    install -m644 converter/other/pnmtopalm/palmcolor8.map ${D}/usr/share/netpbm/
    install -m644 converter/other/pnmtopalm/palmgray2.map ${D}/usr/share/netpbm/
    install -m644 converter/other/pnmtopalm/palmgray4.map ${D}/usr/share/netpbm/
    install -m644 converter/ppm/pcxstd.ppm ${D}/usr/share/netpbm/
    install -m644 lib/rgb.txt ${D}/usr/share/netpbm/
}

RDEPENDS:netpbm += "perl"

HOMEPAGE = "http://sourceforge.net/projects/netpbm"
SUMMARY = "Collection of primitive graphics tools converters etc"
