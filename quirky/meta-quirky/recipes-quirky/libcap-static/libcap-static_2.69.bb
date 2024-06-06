SUMMARY = "Library for getting/setting POSIX.1e capabilities"
DESCRIPTION = "A library providing the API to access POSIX capabilities. \
These allow giving various kinds of specific privileges to individual \
users, without giving them full root permissions."
HOMEPAGE = "http://sites.google.com/site/fullycapable/"
# no specific GPL version required
LICENSE = "BSD-3-Clause | GPL-2.0-only"
LIC_FILES_CHKSUM_PAM = "file://pam_cap/License;md5=905326f41d3d1f8df21943f9a4ed6b50"
LIC_FILES_CHKSUM = "file://License;md5=2965a646645b72ecee859b43c592dcaa \
                    ${@bb.utils.contains('PACKAGECONFIG', 'pam', '${LIC_FILES_CHKSUM_PAM}', '', d)} \
                    "

DEPENDS = "hostperl-runtime-native gperf-native"

SRC_URI = "${KERNELORG_MIRROR}/linux/libs/security/linux-privs/libcap2/libcap-${PV}.tar.xz \
           file://0001-ensure-the-XATTR_NAME_CAPS-is-defined-when-it-is-use.patch \
           file://0002-tests-do-not-run-target-executables.patch \
           file://cap_sys_mount.patch \
           "
SRC_URI:append:class-nativesdk = " \
           file://0001-nativesdk-libcap-Raise-the-size-of-arrays-containing.patch \
           "
SRC_URI[sha256sum] = "f311f8f3dad84699d0566d1d6f7ec943a9298b28f714cae3c931dfd57492d7eb"

UPSTREAM_CHECK_URI = "https://www.kernel.org/pub/linux/libs/security/linux-privs/${BPN}2/"

S = "${WORKDIR}/libcap-${PV}"

inherit lib_package

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'pam', d)}"
PACKAGECONFIG:class-native ??= ""

PACKAGECONFIG[pam] = "PAM_CAP=yes,PAM_CAP=no,libpam"

EXTRA_OEMAKE = " \
  INDENT=  \
  lib='${baselib}' \
  RAISE_SETFCAP=no \
  DYNAMIC=no \
  USE_GPERF=yes \
"

EXTRA_OEMAKE:append:class-target = " SYSTEM_HEADERS=${STAGING_INCDIR}"

# these are present in the libcap defaults, so include in our CFLAGS too
CFLAGS += "-D_LARGEFILE64_SOURCE -D_FILE_OFFSET_BITS=64"

do_compile:prepend() {
 sed -i -e 's%^SHARED .*%SHARED := no%' Make.Rules
 sed -i -e 's%^DYNAMIC .*%DYNAMIC := no%' Make.Rules
 sed -i -e 's%^LDFLAGS .*%LDFLAGS := -static $(LDFLAGS)%' Make.Rules
 sed -i -e 's%^BUILD_LDFLAGS .*%BUILD_LDFLAGS := $(LDFLAGS)%' Make.Rules
 sed -i -e 's%^BUILD_LD .*%BUILD_LD := $(CC)%' Make.Rules
}

do_compile() {
	unset CFLAGS BUILD_CFLAGS
	oe_runmake \
		${PACKAGECONFIG_CONFARGS} \
		AR="${AR}" \
		CC="${CC}" \
		RANLIB="${RANLIB}" \
                OBJCOPY="${OBJCOPY}" \
		COPTS="${CFLAGS}" \
		BUILD_COPTS="${BUILD_CFLAGS}"
}

Xdo_compile() {
	oe_runmake ${PACKAGECONFIG_CONFARGS}
}

do_compile:append() {
 cd progs
 ${CC} -Dlinux -D_LARGEFILE64_SOURCE -D_FILE_OFFSET_BITS=64 -I../libcap/include/uapi -I../libcap/include  -Wl,-Bstatic capsh.c capshdoc.c -L../libcap -lcap -Wl,-Bstatic -o capsh.static ${CFLAGS} ${LDFLAGS} -static
 cd ..
}

Xdo_install() {
	oe_runmake install \
		${PACKAGECONFIG_CONFARGS} \
		DESTDIR="${D}" \
		prefix="${prefix}" \
		SBINDIR="${sbindir}"
}

do_install() {
 install -d ${D}${bindir}
 install -m755 progs/capsh.static ${D}${bindir}
}

Xdo_install:append() {
	# Move the library to base_libdir
	install -d ${D}${base_libdir}
	if [ ! ${D}${libdir} -ef ${D}${base_libdir} ]; then
		mv ${D}${libdir}/libcap* ${D}${base_libdir}
                if [ -d ${D}${libdir}/security ]; then
			mv ${D}${libdir}/security ${D}${base_libdir}
		fi
	fi
}

#FILES:${PN}-dev += "${base_libdir}/*.so"

# pam files
#FILES:${PN} += "${base_libdir}/security/*.so"

#BBCLASSEXTEND = "native nativesdk"
