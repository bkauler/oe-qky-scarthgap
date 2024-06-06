# Recipe created by recipetool
# recipetool create -o claws-mail_3.20.0.bb https://distro.ibiblio.org/easyos/source/alphabetical/c/claws-mail-3.20.0.tar.gz

#PR_NUM is defined in local.conf...
#PR = "r${@int(PR_NUM) + 1}"

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=e059bde2972c1790af786f3e86bac22e"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/c/claws-mail-${PV}.tar.gz \
           file://gumbo.pc"

SRC_URI[sha256sum] = "4f46b56e4f4b2a843c965c5b7927ef42b85202816acf2bb966a37dec13c9006e"

#20211209 removed: valgrind
DEPENDS = "libsm gdk-pixbuf cairo libarchive flex-native gnutls bison-native \
           libcanberra libsoup-2.4 startup-notification libnotify libetpan perl \
           networkmanager dbus curl librsvg nettle libical expat zlib gtk+ glib-2.0 \
           poppler fontconfig glib-2.0-native litehtml libunistring libxcb openssl \
           gpgme gnupg openldap enchant2 aspell libgcrypt libpng harfbuzz libidn2 \
           libnsl2 pango fontconfig gmp libpcre fribidi libassuan freetype libffi \
           gdb"

inherit gettext perlnative pkgconfig autotools mime-xdg

EXTRA_OECONF = " --disable-python-plugin --disable-perl-plugin --disable-fancy-plugin \
                 --disable-dillo-plugin --disable-clamd-plugin --enable-libetpan \
                 --enable-litehtml_viewer-plugin --disable-manual --disable-libsm"

#litehtml has libgumbo in it, but missing gumbo.pc
# should fix this in the litehtml recipe, but oh well...
do_configure:prepend() {
 if [ ! -e ${WORKDIR}/recipe-sysroot/usr/lib/pkgconfig/gumbo.pc ];then
  cp -f ${WORKDIR}/gumbo.pc ${WORKDIR}/recipe-sysroot/usr/lib/pkgconfig/
  #hmmm...
  cp -f ${WORKDIR}/recipe-sysroot/usr/include/gumbo/gumbo.h ${S}/src/plugins/litehtml_viewer/litehtml/
 fi
}

HOMEPAGE = "https://www.claws-mail.org/"
SUMMARY = "Email client"
