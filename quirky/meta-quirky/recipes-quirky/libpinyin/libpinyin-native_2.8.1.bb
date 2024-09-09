# Recipe created by recipetool
# recipetool create -o libpinyin_2.8.1.bb https://github.com/libpinyin/libpinyin/releases/download/2.8.1/libpinyin-2.8.1.tar.gz

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://github.com/libpinyin/libpinyin/releases/download/${PV}/libpinyin-${PV}.tar.gz"

SRC_URI[sha256sum] = "353154f06d71dd0737b77ddcb27cb0dcaddb00f7ccd695bc0314bb42050e9050"

DEPENDS = "glib-2.0-native db-native libpcre-native"

inherit pkgconfig autotools native

EXTRA_OECONF = "--with-dbm=BerkeleyDB --enable-libzhuyin"

SUMMARY = "Chinese pinyin im"
HOMEPAGE = "https://github.com/libpinyin/libpinyin"
