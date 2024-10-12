SUMMARY = "Ext2 Filesystem Utilities"
DESCRIPTION = "The Ext2 Filesystem Utilities (e2fsprogs) contain all of the standard utilities for creating, \
fixing, configuring , and debugging ext2 filesystems."
HOMEPAGE = "http://e2fsprogs.sourceforge.net/"

LICENSE = "GPL-2.0-only & LGPL-2.0-only & BSD-3-Clause & MIT"
LICENSE:e2fsprogs-dumpe2fs = "GPL-2.0-only"
LICENSE:e2fsprogs-e2fsck = "GPL-2.0-only"
LICENSE:e2fsprogs-mke2fs = "GPL-2.0-only"
LICENSE:e2fsprogs-tune2fs = "GPL-2.0-only"
LICENSE:e2fsprogs-badblocks = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://NOTICE;md5=d50be0580c0b0a7fbc7a4830bbe6c12b \
                      file://lib/ext2fs/ext2fs.h;beginline=1;endline=9;md5=596a8dedcb4e731c6b21c7a46fba6bef \
                      file://lib/e2p/e2p.h;beginline=1;endline=7;md5=8a74ade8f9d65095d70ef2d4bf48e36a \
                      file://lib/uuid/uuid.h.in;beginline=1;endline=32;md5=dbb8079e114a5f841934b99e59c8820a \
                      file://lib/uuid/COPYING;md5=58dcd8452651fc8b07d1f65ce07ca8af \
                      file://lib/et/et_name.c;beginline=1;endline=11;md5=ead236447dac7b980dbc5b4804d8c836 \
                      file://lib/ss/ss.h;beginline=1;endline=20;md5=6e89ad47da6e75fecd2b5e0e81e1d4a6"
SECTION = "base"
DEPENDS = "file util-linux attr autoconf-archive-native"

SRC_URI = "git://git.kernel.org/pub/scm/fs/ext2/e2fsprogs.git;branch=master;protocol=https"
S = "${WORKDIR}/git"
CFLAGS += " -static"
LDFLAGS += " -static"

#inherit autotools gettext texinfo pkgconfig multilib_header update-alternatives ptest
inherit perlnative texinfo autotools pkgconfig

SRC_URI += "file://remove.ldconfig.call.patch \
           file://run-ptest \
           file://ptest.patch \
           file://mkdir_p.patch \
           "

SRCREV = "f4c9cc4bedacde8408edda3520a32d3842290112"
UPSTREAM_CHECK_GITTAGREGEX = "v(?P<pver>\d+\.\d+(\.\d+)*)$"


EXTRA_OECONF = "--disable-fuse2fs --disable-nls --disable-tdb --disable-mmp --disable-uuidd \
   --disable-defrag --disable-imager --enable-libblkid --enable-libuuid --disable-bmap-stats \
   --disable-debugfs --disable-backtrace --with-crond-dir=no --enable-largefile \
   --libdir=${base_libdir} --sbindir=${base_sbindir}"

# make locale rules sometimes fire, sometimes don't as git doesn't preserve
# file mktime. Touch the files introducing non-determinism to the build
do_compile:prepend (){
	find ${S}/po -type f -name "*.po" -exec touch {} +
}

# warning: -s (strip) does not work for aarch64 target...
do_install () {
 install -d ${D}/usr/bin
 install -d ${D}/usr/sbin
 install -m 755 ${B}/e2fsck/e2fsck ${D}/usr/sbin
 install -m 755 ${B}/misc/badblocks ${D}/usr/sbin
 install -m 755 ${B}/misc/blkid ${D}/usr/sbin
 install -m 755 ${B}/misc/chattr ${D}/usr/bin
 install -m 755 ${B}/misc/dumpe2fs ${D}/usr/sbin
 install -m 755 ${B}/misc/e2freefrag ${D}/usr/sbin
 install -m 755 ${B}/misc/e4crypt ${D}/usr/sbin
 install -m 755 ${B}/misc/lsattr ${D}/usr/bin
 install -m 755 ${B}/misc/mke2fs ${D}/usr/sbin
 install -m 755 ${B}/misc/tune2fs ${D}/usr/sbin
 install -m 755 ${B}/resize/resize2fs ${D}/usr/bin
}
