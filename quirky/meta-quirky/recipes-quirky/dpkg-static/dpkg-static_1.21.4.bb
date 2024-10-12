SUMMARY = "Package maintenance system from Debian"
LICENSE = "GPL-2.0-or-later"
HOMEPAGE = "https://salsa.debian.org/dpkg-team/dpkg"
DESCRIPTION = "The primary interface for the dpkg suite is the dselect program. A more low-level and less user-friendly interface is available in the form of the dpkg command."
SECTION = "base"

#BK
DEPENDS = "zlib bzip2 ncurses-static libmd"

#DEPENDS:class-native = "bzip2-replacement-native zlib-native virtual/update-alternatives-native gettext-native perl-native"
#RDEPENDS:${PN} = "${VIRTUAL-RUNTIME_update-alternatives} perl"
#RDEPENDS:${PN}:class-native = ""

UPSTREAM_CHECK_GITTAGREGEX = "(?P<pver>(\d+(\.\d+)+))"

inherit autotools gettext perlnative pkgconfig perl-version update-alternatives

PERL:class-native = "${STAGING_BINDIR_NATIVE}/perl-native/perl"

export PERL_LIBDIR = "${libdir}/perl5/${@get_perl_version(d)}"
PERL_LIBDIR:class-native = "${libdir}/perl-native/perl/${@get_perl_version(d)}"

EXTRA_OECONF = "\
		--disable-dselect \
		--enable-start-stop-daemon \
		--with-libz \
		--with-libbz2 \
		--with-libmd \
		--without-libselinux \
		TAR=tar \
		"

EXTRA_OECONF:append:class-target = " --disable-update-alternatives DEB_HOST_ARCH=${DPKG_ARCH}"
EXTRA_OECONF:append:class-nativesdk = " --disable-update-alternatives DEB_HOST_ARCH=${DPKG_ARCH}"

PACKAGECONFIG = "liblzma"
PACKAGECONFIG[liblzma] = "--with-liblzma,--without-liblzma, xz"


#autotools.bbclass default AUTOTOOLS_AUXDIR is ${S}, we need to under ${S}/build-aux
AUTOTOOLS_AUXDIR = "${S}/build-aux"

do_compile:append() {
 cd ${B}/src
 ${CC} -static  -m64 -march=nocona -mtune=nocona -mno-sse3 -mfpmath=sse -fstack-protector-strong  -O2 -D_FORTIFY_SOURCE=2 -Wformat -Wformat-security -Werror=format-security --sysroot=/mnt/build/builds/oe/kirkstone/oe-quirky/build-amd64-musl/tmp/work/nocona-64-poky-linux-musl/dpkg-static/1.21.4-r1/recipe-sysroot  -Wall -Wextra -Wcast-align -Wduplicated-branches -Wduplicated-cond -Wformat -Wformat-security -Wformat=2 -Winit-self -Wlogical-not-parentheses -Wlogical-op -Wmissing-declarations -Wmissing-format-attribute -Wno-missing-field-initializers -Wno-nonnull-compare -Wno-unused-parameter -Wnull-dereference -Wpointer-arith -Wredundant-decls -Wrestrict -Wshadow -Wshift-negative-value -Wsizeof-array-argument -Wswitch-bool -Wvla -Wwrite-strings -Wbad-function-cast -Wc99-c11-compat -Wdeclaration-after-statement -Wmissing-prototypes -Wnested-externs -Wold-style-definition -Wstrict-prototypes  -O2 -pipe -g -feliminate-unused-debug-types -fmacro-prefix-map=/mnt/build/builds/oe/kirkstone/oe-quirky/build-amd64-musl/tmp/work/nocona-64-poky-linux-musl/dpkg-static/1.21.4-r1=/usr/src/debug/dpkg-static/1.21.4-r1                      -fdebug-prefix-map=/mnt/build/builds/oe/kirkstone/oe-quirky/build-amd64-musl/tmp/work/nocona-64-poky-linux-musl/dpkg-static/1.21.4-r1=/usr/src/debug/dpkg-static/1.21.4-r1                      -fdebug-prefix-map=/mnt/build/builds/oe/kirkstone/oe-quirky/build-amd64-musl/tmp/work/nocona-64-poky-linux-musl/dpkg-static/1.21.4-r1/recipe-sysroot=                      -fdebug-prefix-map=/mnt/build/builds/oe/kirkstone/oe-quirky/build-amd64-musl/tmp/work/nocona-64-poky-linux-musl/dpkg-static/1.21.4-r1/recipe-sysroot-native=   -Wl,-O1 -Wl,--hash-style=gnu -Wl,--as-needed -fmacro-prefix-map=/mnt/build/builds/oe/kirkstone/oe-quirky/build-amd64-musl/tmp/work/nocona-64-poky-linux-musl/dpkg-static/1.21.4-r1=/usr/src/debug/dpkg-static/1.21.4-r1                      -fdebug-prefix-map=/mnt/build/builds/oe/kirkstone/oe-quirky/build-amd64-musl/tmp/work/nocona-64-poky-linux-musl/dpkg-static/1.21.4-r1=/usr/src/debug/dpkg-static/1.21.4-r1                      -fdebug-prefix-map=/mnt/build/builds/oe/kirkstone/oe-quirky/build-amd64-musl/tmp/work/nocona-64-poky-linux-musl/dpkg-static/1.21.4-r1/recipe-sysroot=                      -fdebug-prefix-map=/mnt/build/builds/oe/kirkstone/oe-quirky/build-amd64-musl/tmp/work/nocona-64-poky-linux-musl/dpkg-static/1.21.4-r1/recipe-sysroot-native=  -Wl,-z,relro,-z,now -Wl,-O1 -o dpkg-static common/force.o common/selinux.o main/archives.o main/cleanup.o main/configure.o main/depcon.o main/enquiry.o main/errors.o main/file-match.o main/filters.o main/help.o main/main.o main/packages.o main/perpkgstate.o main/remove.o main/script.o main/select.o main/trigproc.o main/unpack.o main/update.o main/verify.o ../lib/dpkg/.libs/libdpkg.a  ../lib/compat/.libs/libcompat.a -lmd
}

do_configure:prepend () {
        mkdir -p ${AUTOTOOLS_AUXDIR}
        # autotools_do_configure updates po/Makefile.in.in, we also need
        # update dselect/po and scripts/po
        cp -f ${STAGING_DATADIR_NATIVE}/gettext/po/Makefile.in.in ${S}/dselect/po/
        cp -f ${STAGING_DATADIR_NATIVE}/gettext/po/Makefile.in.in ${S}/scripts/po/
}

do_install:append () {
	if [ "${PN}" = "dpkg-native" ]; then
		# update-alternatives doesn't have an offline mode
		rm ${D}${bindir}/update-alternatives
		sed -i -e 's|^#!.*${STAGING_BINDIR_NATIVE}/perl-native.*/perl|#!/usr/bin/env nativeperl|' ${D}${bindir}/dpkg-* ${D}${sbindir}/dpkg-*
	else
		sed -i -e 's|^#!.*${STAGING_BINDIR_NATIVE}/perl-native.*/perl|#!/usr/bin/env perl|' ${D}${bindir}/dpkg-* ${D}${sbindir}/dpkg-*
	fi
}

PROV = "virtual/update-alternatives"
PROV:class-native = ""
PROV:class-nativesdk = ""

PROVIDES += "${PROV}"

FILES:${PN} += "${datadir}/zsh"

PACKAGES =+ "update-alternatives-dpkg"
FILES:update-alternatives-dpkg = "${bindir}/update-alternatives ${localstatedir}/lib/dpkg/alternatives ${sysconfdir}/alternatives"
RPROVIDES:update-alternatives-dpkg += "update-alternatives"

PACKAGES += "${PN}-perl"
FILES:${PN}-perl = "${libdir}/perl5/${@get_perl_version(d)}"


LIC_FILES_CHKSUM = "file://${WORKDIR}/git/COPYING;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "git://salsa.debian.org/dpkg-team/dpkg.git;protocol=https;branch=main \
           file://noman.patch \
           file://remove-tar-no-timestamp.patch \
           file://arch_pm.patch \
           file://add_armeb_triplet_entry.patch \
           file://0002-Adapt-to-linux-wrs-kernel-version-which-has-characte.patch \
           file://0003-Our-pre-postinsts-expect-D-to-be-set-when-running-in.patch \
           file://0004-The-lutimes-function-doesn-t-work-properly-for-all-s.patch \
           file://0006-add-musleabi-to-known-target-tripets.patch \
           file://0007-dpkg-deb-build.c-Remove-usage-of-clamp-mtime-in-tar.patch \
           file://0001-dpkg-Support-muslx32-build.patch \
           file://pager.patch \
           file://0001-Add-support-for-riscv32-CPU.patch \
           file://0001-Dpkg-Source-Archive-Prevent-directory-traversal-for-.patch \
           "

SRC_URI:append:class-native = " file://0001-build.c-ignore-return-of-1-from-tar-cf.patch"

SRCREV = "5563bdb608b3413639b69f1c76567cb66ff1a961"

S = "${WORKDIR}/git"

LDCONFIG += " -static"
