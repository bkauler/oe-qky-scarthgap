# Recipe created by recipetool
# recipetool create -o ibus-libpinyin_1.15.8.bb https://github.com/libpinyin/ibus-libpinyin/releases/download/1.15.8/ibus-libpinyin-1.15.8.tar.gz

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://github.com/libpinyin/ibus-libpinyin/releases/download/${PV}/ibus-libpinyin-${PV}.tar.gz"

SRC_URI[sha256sum] = "1d32eb82a09bc043da0e2b65849bc61c8820b99f30a8cff10d807a1c44848bfa"

DEPENDS = "sqlite3 libsoup boost lua libpinyin ibus json-glib glib-2.0 glib-2.0-native"

inherit pkgconfig gettext autotools python3-dir python3native

EXTRA_OECONF = "--disable-opencc --enable-boost"

#in running easyos get this error whwn select Preferences in menu:
# # ibus-setup
# /usr/libexec/ibus-setup-libpinyin: line 31: /mnt/build/builds/oe/scarthgap/oe-quirky/build-amd64/tmp/work/nocona-64-poky-linux/ibus-libpinyin/1.15.8/recipe-sysroot-native/usr/bin/python3-native/python3: No such file or directory
do_install:append() {
 sed -i -e 's%^exec .*%exec /usr/bin/python3 main2.py \$\@%' ${D}/usr/libexec/ibus-setup-libpinyin
}

SUMMARY = "pinyin im for ibus"
HOMEPAGE = "https://github.com/libpinyin/ibus-libpinyin"
