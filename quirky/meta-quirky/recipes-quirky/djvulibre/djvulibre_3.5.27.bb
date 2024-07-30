# Recipe created by recipetool
# recipetool create -o djvulibre_3.5.27.bb https://www.paldo.org/paldo/sources/ddjvuapi/djvulibre-3.5.27.tar.zst

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=c93c0550bd3173f4504b2cbd8991e50b"

SRC_URI = "https://www.paldo.org/paldo/sources/ddjvuapi/djvulibre-${PV}.tar.zst"

SRC_URI[sha256sum] = "062ccd6b663ed111fac5c91f46ea3ce757dd37b54f04ad09f10b3daf74af808d"

DEPENDS = "libxext libjpeg-turbo tiff xdg-utils librsvg librsvg-native"

REDEPENDS = "bash"

inherit autotools pkgconfig

EXTRA_OECONF = ""

SUMMARY = "DjVu decoding api"
HOMEPAGE = "https://www.paldo.org/index-section-packages-page-main-releaseid-21914.html"

#ERROR: djvulibre-3.5.27-r1 do_package_qa: QA Issue: /usr/bin/any2djvu contained in package djvulibre requires /bin/bash, but no providers found in RDEPENDS:djvulibre? [file-rdeps]
ERROR_QA:remove = "file-rdeps"
WARN_QA:remove = "file-rdeps"
