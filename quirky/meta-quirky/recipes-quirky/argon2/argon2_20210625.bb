# Recipe created by recipetool
# recipetool create -o argon2_20210625.bb http://distro.ibiblio.org/easyos/source/alphabetical/a/argon2-20210625.tar.gz

#PR = "r${@int(PR_NUM) + 1}"

LICENSE = "CC0-1.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=97ba219b479900241a90026f4d22b403"

SRC_URI = "http://distro.ibiblio.org/easyos/source/alphabetical/a/argon2-${PV}.tar.gz"

SRC_URI[md5sum] = "95ed960fab9cdff508667d42f680fd40"
SRC_URI[sha256sum] = "f1af4d353d490e16c9c7645db90c37a61002528ba5d15f5464c0013ca23373ec"

#BK 20230714 "illegal instruction" in Compaq Presario, try these fixes...
do_configure () {
 #true
 sed -i '/^AR=ar/d' ${S}/Makefile
}

do_compile () {
 #oe_runmake
 #20230714 also...
 if [ "${TARGET_ARCH}" == "x86_64" ];then
  oe_runmake OPTTARGET=nocona
 elif [ "${TARGET_ARCH}" == "aarch64" ];then
  oe_runmake OPTTARGET=aarch64
 else
  oe_runmake
 fi
}

do_install () {
 oe_runmake install 'DESTDIR=${D}'
 #20230621 installs into usr/lib/x86_64-linux-gnu, fix...
 if [ -f ${D}/usr/lib/${TARGET_ARCH}-linux-gnu/libargon2.so.1 ];then
  cp -a -f ${D}/usr/lib/${TARGET_ARCH}-linux-gnu/* ${D}/usr/lib/
  rm -rf ${D}/usr/lib/${TARGET_ARCH}-linux-gnu
  sed -i -e 's%^libdir=.*%libdir=/usr/lib%' ${D}/usr/lib/pkgconfig/libargon2.pc
 fi
}

HOMEPAGE = "https://argon2.online"
SUMMARY = "hash generator"
