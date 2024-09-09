# Recipe created by recipetool
# recipetool create -o libpinyin_2.8.1.bb https://github.com/libpinyin/libpinyin/releases/download/2.8.1/libpinyin-2.8.1.tar.gz

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://github.com/libpinyin/libpinyin/releases/download/${PV}/libpinyin-${PV}.tar.gz"

SRC_URI[sha256sum] = "353154f06d71dd0737b77ddcb27cb0dcaddb00f7ccd695bc0314bb42050e9050"

DEPENDS = "glib-2.0 db libpcre libpinyin-native"

inherit pkgconfig autotools

EXTRA_OECONF = "--with-dbm=BerkeleyDB --enable-libzhuyin"

#| ../utils/storage/gen_binary_files --table-dir ../../libpinyin-2.8.1/data
#| /bin/bash: ../utils/storage/gen_binary_files: No such file or directory
#| make[2]: Leaving directory '/mnt/build/builds/oe/scarthgap/oe-quirky/build-amd64/tmp/work/nocona-64-poky-linux/libpinyin/2.8.1/build/data'
do_compile:prepend() {
 PTN1="s%\.\./utils/storage%${WORKDIR}/recipe-sysroot-native/usr/bin%"
 sed -i -e "${PTN1}" ${B}/data/Makefile
 PTN2="s%\.\./utils/training%${WORKDIR}/recipe-sysroot-native/usr/bin%"
 sed -i -e "${PTN2}" ${B}/data/Makefile
}

SUMMARY = "Chinese pinyin im"
HOMEPAGE = "https://github.com/libpinyin/libpinyin"
