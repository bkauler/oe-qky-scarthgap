SUMMARY = "A Cross Platform and Open Source Electronics Design Automation Suite"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = " \
    file://LICENSE_LGPL_21.txt;md5=6ab17b41640564434dda85c06b7124f7 \
    file://OCCT_LGPL_EXCEPTION.txt;md5=509623a9302457aa45a3621bf7ca591c"

#took out:  tbb-2020 vtk
DEPENDS = " \
    doxygen-native \
    tcl-native \
    freetype \
    tk \
    ffmpeg \
"

inherit cmake features_check

REQUIRED_DISTRO_FEATURES = "opengl x11"

#SRC_URI = "git://github.com/Open-Cascade-SAS/OCCT.git 
#    file://0001-Steal-some-fixes.patch"
#SRCREV = "b896c6f4af81b710607255d594378c166a78b1dc"
#PV = "7.8.1"
#S = "${WORKDIR}/git"

SRC_URI = "https://github.com/Open-Cascade-SAS/OCCT/archive/refs/tags/V7_8_1.tar.gz"

SRC_URI[sha256sum] = "7321af48c34dc253bf8aae3f0430e8cb10976961d534d8509e72516978aa82f5"

S = "${WORKDIR}/OCCT-7_8_1"

#VTKVER = "9.0"

#    -D3RDPARTY_VTK_INCLUDE_DIR=${STAGING_INCDIR}/vtk-${VTKVER}
#    -D3RDPARTY_VTK_LIBRARY_DIR=${STAGING_LIBDIR}

EXTRA_OECMAKE = " \
    -DCMAKE_BUILD_TYPE=Release \
    -DUSE_FFMPEG=ON \
    -DUSE_TBB=OFF \
    -DUSE_VTK=OFF \
"

RDEPENDS:${PN} += "bash tk-lib"
