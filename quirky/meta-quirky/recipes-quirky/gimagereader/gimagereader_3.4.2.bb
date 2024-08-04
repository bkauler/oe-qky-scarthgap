# Recipe created by recipetool
# recipetool create -o gimagereader_3.4.2.bb https://github.com/manisandro/gImageReader/releases/download/v3.4.2/gimagereader-3.4.2.tar.xz

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=8f0e2cd40e05189ec81232da84bd6e1a"

#see note below...
SRC_URI = "https://github.com/manisandro/gImageReader/releases/download/v${PV}/gimagereader-${PV}.tar.xz"

SRC_URI[sha256sum] = "184cafe57d86ffc4115ce76e29a47f0d627655de46b7890dffbb82267091a264"

#ref: https://github.com/manisandro/gImageReader/wiki/Compiling-gImageReader
DEPENDS = "poppler gtkmm3 enchant2 json-glib libjpeg-turbo sane-backends \
           pangomm tesseract leptonica fontconfig cairomm \
           zlib libxml++-5.0 djvulibre intltool intltool-native gtksourceviewmm3 \
           gtkspell3 libpng atkmm glib-2.0 glib-2.0-native json-glib-native json-glib \
           gtkspellmm3 libzip libpodofo util-linux"

inherit cmake gettext pkgconfig

EXTRA_OECMAKE = "-DCMAKE_BUILD_TYPE=RELEASE"

do_configure:prepend() {
 #this for qt5 build...
 ##oe has old quazip 0.x (i tried compile quazip 1.4 fail)...
 #sed -i -e 's%quazipfile.h PATH_SUFFIXES %quazipfile.h PATH_SUFFIXES quazip %' ${S}/CMakeLists.txt
 #sed -i -e 's%QUAZIP_LIBRARIES NAMES %QUAZIP_LIBRARIES NAMES quazip %' ${S}/CMakeLists.txt
 #seems to default to qt5...
 sed -i -e 's%INTERFACE_TYPE "qt5"%INTERFACE_TYPE "gtk"%' ${S}/CMakeLists.txt
 #hmm, more hacks... oe has libxml++-5.0
 sed -i -e 's%libxml\+\+-3\.0%libxml++-5.0%' ${S}/CMakeLists.txt
 ln -s libxml++-5.0.pc ${WORKDIR}/recipe-sysroot/usr/lib/pkgconfig/libxml++-3.0.pc
 #aargh wants gdkwayland.h... fix stupid code...
 sed -i -e 's%G_OS_UNIX%G_OS_UNIXW%' ${S}/gtk/src/SourceManager.cc
}

WARN_QA:remove = "buildpaths"
WARN_QA:remove = "mime-xdg"

do_install:append() {
 mv -f ${D}/usr/bin/gimagereader-gtk ${D}/usr/bin/gimagereader
 mv -f ${D}/usr/share/applications/gimagereader-gtk.desktop ${D}/usr/share/applications/gimagereader.desktop
 sed -i -e 's%^Exec=.*%Exec=gimagereader%'  ${D}/usr/share/applications/gimagereader.desktop
 sed -i -e 's%^Icon=.*%Icon=gimagereader.png%'  ${D}/usr/share/applications/gimagereader.desktop
 sed -i -e 's%^Name=.*%Name=gImageReader OCR%'  ${D}/usr/share/applications/gimagereader.desktop
 sed -i -e 's%^Categories=.*%Categories=OCR;%'  ${D}/usr/share/applications/gimagereader.desktop
 mkdir -p ${D}/usr/share/pixmaps
 ln -s ../icons/hicolor/48x48/apps/gimagereader.png ${D}/usr/share/pixmaps/gimagereader.png
}

SUMMARY = "GUI for tesseract OCR"
HOMEPAGE = "https://github.com/manisandro/gImageReader"

RDEPENDS:${PN} = "tessdata-eng tessdata-fra tessdata-deu"
