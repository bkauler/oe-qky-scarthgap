# Recipe created by recipetool
# recipetool create -o litebrowser_202404.bb https://distro.ibiblio.org/easyos/source/alphabetical/l/litebrowser-202404.tar.gz

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b118f23daf2793cf0976d8d6aa9c715a"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/l/litebrowser-${PV}.tar.gz"
SRC_URI[sha256sum] = "f631d7d3f5bea7dec2c28c30759a29b89ddccd8245e2d8da3bba336305b72e38"

# vim-native has 'xxd' utility...
DEPENDS = "vim-native ncurses gettext-native gtk+3 gtkmm3 litehtml pango curl \
   fontconfig openssl"

inherit cmake pkgconfig

EXTRA_OECMAKE = ""

