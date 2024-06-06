# Recipe created by recipetool
# recipetool create -o librist_0.2.10.bb https://code.videolan.org/rist/librist/-/archive/v0.2.10/librist-v0.2.10.tar.gz

LICENSE = "Apache-2.0 & BSD-2-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=09a3f4fef2fbe291e90675fa6d707692 \
                    file://contrib/mbedtls/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "https://code.videolan.org/rist/librist/-/archive/v${PV}/librist-v${PV}.tar.gz"
SRC_URI[sha256sum] = "797e486961cd09bc220c5f6561ca5a08e7747b313ec84029704d39cbd73c598c"

S = "${WORKDIR}/${BPN}-v${PV}"

inherit meson pkgconfig

EXTRA_OEMESON = "--buildtype=release"

HOMEPAGE = "https://code.videolan.org/rist/librist"
SUMMARY = "RIST protocol library"

