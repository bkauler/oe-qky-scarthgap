# Recipe created by recipetool
# recipetool create -o extra-cmake-modules_5.103.0.bb http://deb.debian.org/debian/pool/main/e/extra-cmake-modules/extra-cmake-modules_5.103.0.orig.tar.xz

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING-CMAKE-SCRIPTS;md5=54c7042be62e169199200bc6477f04d1"

SRC_URI = "http://deb.debian.org/debian/pool/main/e/extra-cmake-modules/extra-cmake-modules_${PV}.orig.tar.xz \
           file://force_relative_paths.diff \
           file://demote_unsupported_platform_error_to_a_warning.diff"

SRC_URI[sha256sum] = "92ca2e55cb38956fbdeaf254231f074647173ccfd12dc9664989c6fa9e9c4346"

# NOTE: unable to map the following CMake package dependencies: Sphinx Qt PythonModuleGeneration QCollectionGenerator
#DEPENDS = "qtbase"

inherit cmake gettext pkgconfig python3native python3-dir

EXTRA_OECMAKE = "-DBUILD_TESTING=OFF"

SUMMARY = "Extra modules and scripts for cmake"
HOMEPAGE = "https://invent.kde.org/frameworks/extra-cmake-modules"

BBCLASSEXTEND = "native"
