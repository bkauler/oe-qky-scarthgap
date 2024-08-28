# Recipe created by recipetool
# recipetool create -o jwm-2.4.3.99.bb https://distro.ibiblio.org/easyos/source/alphabetical/j/jwm-2.4.3.99.tar.gz

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c2938cf0f6887d0bca0cea7cac3e097f"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/j/jwm-2.4.3.99.tar.gz"

SRC_URI[sha256sum] = "e2817dcd218b9209ec75771748df67c47a80617300fa51f70f5e70586c133441"

DEPENDS = "libjpeg-turbo libxft libx11 libxpm cairo libpng12 libxrender libxext \
           librsvg libxmu libxinerama intltool-native pango"

inherit gettext autotools-brokensep pkgconfig

EXTRA_OECONF = "--disable-confirm"

do_configure:prepend() {
 #this commit has broken autoreconf...
 # autoreconf: running: automake --add-missing --copy --force-missing
 # automake: error: no 'Makefile.am' found for any configure output
 # ref: https://github.com/joewing/jwm/commit/607fc3d4e3127b79f79cfd602e24523dfcf2a29d
 sed -i '/^AM_INIT_AUTOMAKE/d' ${S}/configure.ac
}

HOMEPAGE = "http://www.joewing.net/programs/jwm"
SUMMARY = "Joes Window Manager"

