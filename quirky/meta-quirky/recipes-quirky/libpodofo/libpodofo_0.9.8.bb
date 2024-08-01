# Recipe created by recipetool
# recipetool create -o libpodofo_0.9.8.bb http://deb.debian.org/debian/pool/main/libp/libpodofo/libpodofo_0.9.8+dfsg.orig.tar.xz

LICENSE = "GPL-2.0-only & LGPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=393a5ca445f6965873eca0259a17f833 \
                    file://COPYING.LIB;md5=db979804f025cf55aabec7129cb671ed"

#patch from debian
SRC_URI = "http://deb.debian.org/debian/pool/main/libp/libpodofo/libpodofo_${PV}%2Bdfsg.orig.tar.xz \
           file://0001-Fix-declaration-of-operator-for-PoDoFo-PdfString.patch"

SRC_URI[sha256sum] = "1c827b14d52b95a636ca756804a6c1b971cd0c664144f7ab9e56f36c221dabc1"

S = "${WORKDIR}/podofo-${PV}"

# NOTE: unable to map the following CMake package dependencies: LIBCRYPTO LUA CppUnit LIBSTLPORT LIBIDN LIBJPEG UNISTRING
#rem: boost
DEPENDS = "fontconfig openssl libpng freetype zlib tiff \
           lua libidn libjpeg-turbo libunistring"

inherit cmake pkgconfig gettext

#no, boost support not currently recomended...
#EXTRA_OECMAKE = "-DWANT_BOOST:BOOL=1"
EXTRA_OECMAKE = "-DCMAKE_BUILD_TYPE=RELEASE -DPODOFO_BUILD_SHARED:BOOL=TRUE -DPODOFO_BUILD_STATIC:BOOL=FALSE"

SUMMARY = "PDF manipulation library"
HOMEPAGE = "http://deb.debian.org/debian/pool/main/libp/libpodofo/libpodofo_0.9.8+dfsg.orig.tar.xz"

