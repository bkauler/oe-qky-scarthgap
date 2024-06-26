# Recipe created by recipetool
# recipetool create -o nim_1.6.18.bb https://nim-lang.org/download/nim-1.6.18.tar.xz

HOMEPAGE = "https://nim-lang.org/"
DESCRIPTION = "nim compiler"
SECTION = "languages"
SUMMARY = "nim language compiler"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit native

SRC_URI = "https://nim-lang.org/download/nim-${PV}.tar.xz"

SRC_URI[sha256sum] = "50241ac722291ba963753f045aaa3587b73c1aa28ae29c573c15a5b8a602a2cb"

# ERROR: nim-native-1.6.6-r9 do_populate_sysroot: The recipe nim-native is trying to install files into a shared area when those files already exist. Those files and their manifest location are:
#  /mnt/build/oe-builds/oe-quirky/build-amd64/tmp/sysroots-components/x86_64/nim-native/usr/bin/nim
# It could be the overlapping files detected are harmless in which case adding them to SSTATE_DUPWHITELIST may be the correct solution.
#SSTATE_DUPWHITELIST = "/"
# 20221204 kirkstone new name...
SSTATE_ALLOW_OVERLAP_FILES = "/"

do_configure () {
 true
}

do_compile () {
  COMP_FLAGS="${CFLAGS}" LINK_FLAGS="${LDFLAGS}" ./build.sh --os linux --cpu ${BUILD_ARCH}
  bin/nim c -d:release koch
}

do_install () {
 ./install.sh pkg
 install -d ${D}${bindir}
 install -d ${D}${includedir}
 install -d ${D}${libdir}/nim
 install -m 755 pkg/nim/bin/nim ${D}${bindir}/
 install -m 644 pkg/nim/lib/cycle.h ${D}${includedir}/
 install -m 644 pkg/nim/lib/nimbase.h ${D}${includedir}/
 install -m 755 koch ${D}${bindir}/
 (
  cd pkg/nim/lib
  for dir in $(find . -mindepth 1 -type d);do
   install -d ${D}${libdir}/nim/${dir}
  done
  for file in $(find . -mindepth 1 -type f);do
   install -m 755 "${file}" ${D}${libdir}/nim/${file}
  done
 )
 
 echo 'path="$lib/deprecated/core"
path="$lib/deprecated/pure"
path="$lib/pure/collections"
path="$lib/pure/concurrency"
path="$lib/impure"
path="$lib/wrappers"
path="$lib/wrappers/linenoise"
path="$lib/windows"
path="$lib/posix"
path="$lib/js"
path="$lib/pure/unidecode"
path="$lib/arch"
path="$lib/core"
path="$lib/pure"' > nim.cfg
 install -d ${D}${sysconfdir}/nim
 install -m644 nim.cfg ${D}${sysconfdir}/nim/
}

