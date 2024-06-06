require vte9.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=3bf50002aefd002f49e7bb854063f7e7"

SRC_URI = "https://distro.ibiblio.org/easyos/source/oe/dunfell/vte-0.28.2.tar.bz2"

SRC_URI += "file://obsolete_automake_macros.patch \
            file://cve-2012-2738.patch \
           "

SRC_URI[sha256sum] = "8d04e202b617373dfb47689e5e628febe2c58840b34cccc4af4feb88c48df903"

CFLAGS += "-D_GNU_SOURCE"
