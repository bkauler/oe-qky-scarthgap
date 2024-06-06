# Recipe created by recipetool
# recipetool create -o sane-backends_1.2.1.bb https://gitlab.com/sane-project/backends/uploads/110fc43336d0fb5e514f1fdc7360dd87/sane-backends-1.2.1.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "https://gitlab.com/sane-project/backends/uploads/110fc43336d0fb5e514f1fdc7360dd87/sane-backends-${PV}.tar.gz"
SRC_URI[sha256sum] = "f832395efcb90bb5ea8acd367a820c393dda7e0dd578b16f48928b8f5bdd0524"

#20240105
#| ../sane-backends-1.2.1/configure: line 21749: syntax error near unexpected token `noext,'
#| ../sane-backends-1.2.1/configure: line 21749: `AX_CXX_COMPILE_STDCXX_11(noext, optional)'
#| ../sane-backends-1.2.1/configure: line 22630: syntax error near unexpected token `include/_stdint.h'
#| ../sane-backends-1.2.1/configure: line 22630: `AX_CREATE_STDINT_H(include/_stdint.h)'
# ref: https://github.com/mitlm/mitlm/issues/65
# ref: https://www.mail-archive.com/modemmanager-devel@lists.freedesktop.org/msg06019.html
#DEPENDS = "tiff v4l-utils libpng12 libxml2 poppler libjpeg-turbo libusb1 curl cups gphoto2"
DEPENDS = "tiff v4l-utils libpng12 libxml2 poppler libjpeg-turbo libusb1 curl cups gphoto2 autoconf-archive-native"

inherit gettext pkgconfig autotools

EXTRA_OECONF = "--with-usb --without-systemd --with-gphoto2 --with-group=scanner \
                --without-avahi --with-v4l"

# datadir=/usr/share  libdir=/usr/lib
FILES:${PN} += "${datadir}/sane ${libdir}/sane"

INSANE_SKIP:${PN} += "dev-so"

HOMEPAGE = "http://www.sane-project.org"
SUMMARY = "The Scanner Access Now Easy backends"
