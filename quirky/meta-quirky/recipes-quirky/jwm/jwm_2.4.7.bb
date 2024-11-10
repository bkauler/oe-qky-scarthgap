# Recipe created by recipetool
# recipetool create -o jwm-2.4.7.bb https://distro.ibiblio.org/easyos/source/alphabetical/j/jwm-2.4.7.tar.gz

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c2938cf0f6887d0bca0cea7cac3e097f"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/j/jwm-2.4.7.tar.gz"

SRC_URI[sha256sum] = "4c5605d33727da17ff4d207dc8a3c76a954c4e156c09e51348acc564717b3992"

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

HOMEPAGE = "https://joewing.net/projects/jwm/"
SUMMARY = "Joes Window Manager"

