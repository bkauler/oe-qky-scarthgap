# Recipe created by recipetool
# recipetool create -o libblockdev2_2.29.bb https://github.com/storaged-project/libblockdev/releases/download/2.29-1/libblockdev-2.29.tar.gz

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c07cb499d259452f324bb90c3067d85c"

SRC_URI = "https://github.com/storaged-project/libblockdev/releases/download/${PV}-1/libblockdev-${PV}.tar.gz"
SRC_URI[sha256sum] = "9abffc827f30ca3496d1ad63b21668228d175be9c41fbb0f9e83d14572c71d8b"

S = "${WORKDIR}/libblockdev-${PV}"

DEPENDS = "parted libbytesize kmod nss util-linux util-linux-libuuid cryptsetup \
           glib-2.0 eudev libyaml libdevmapper2 python3 volume-key eudev ndctl"

inherit python3native pkgconfig autotools

EXTRA_OECONF = "--with-python3 --with-part --with-crypto --disable-tests \
                --without-dmraid"

HOMEPAGE = "http://rhinstaller.github.io/libblockdev/"
SECTION = "devel/lib"
SUMMARY = "library supporting GObject introspection for manipulation of block devices"

FILES:${PN} += "${libdir}/python2.7/dist-packages ${libdir}/python3.*/site-packages"
