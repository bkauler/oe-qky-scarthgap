# Recipe created by recipetool
# recipetool create -o solvespace_3.1-e7c0c16-20231117.bb https://distro.ibiblio.org/easyos/source/alphabetical/s/solvespace-3.1-e7c0c16-20231117.tar.gz
# recipetool create -o solvespace_20250207.bb https://distro.ibiblio.org/easyos/source/alphabetical/s/solvespace-20250207.tar.gz

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=8006d9c814277c1bfc4ca22af94b59ee"

SRC_URI = "http://distro.ibiblio.org/easyos/source/alphabetical/s/solvespace-${PV}.tar.gz"

SRC_URI[sha256sum] = "10ced99ca2c5cf0f27ed6175e9612fde72e02dd4c2b5766a73ca66475e1e1338"

DEPENDS = "cairo json-c zlib fontconfig libpng virtual/libgl freetype gtkmm3 atkmm \
           cairomm glibmm pangomm libglu mesa"

# rem:  npm
inherit cmake pkgconfig gettext mime-xdg

EXTRA_OECMAKE = "-DCMAKE_BUILD_TYPE=Release -DENABLE_OPENMP=ON \
     -DCMAKE_INSTALL_PREFIX=/usr -DENABLE_TESTS=OFF"

HOMEPAGE = "https://github.com/solvespace/solvespace"
SUMMARY = "Parametric 2d/3d CAD"

#do_package_qa errors fix:
ERROR_QA:remove = "file-rdeps"
WARN_QA:remove = "file-rdeps"
