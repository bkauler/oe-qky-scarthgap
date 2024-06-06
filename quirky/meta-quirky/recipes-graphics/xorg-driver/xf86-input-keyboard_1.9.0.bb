
#require xorg-driver-input.inc
SUMMARY = "X driver"
HOMEPAGE = "http://www.x.org"
BUGTRACKER = "https://bugs.freedesktop.org"
SECTION = "x11/drivers"
LICENSE = "MIT"

PE = "2"

DEPENDS = "virtual/xserver xorgproto util-macros"

XORG_DRIVER_COMPRESSOR ?= ".tar.bz2"
SRC_URI = "${XORG_MIRROR}/individual/driver/${BPN}-${PV}${XORG_DRIVER_COMPRESSOR}"

FILES:${PN} += " ${libdir}/xorg/modules/drivers/*.so"

XORGBUILDCLASS ??= "autotools"
inherit ${XORGBUILDCLASS} pkgconfig features_check

# depends on virtual/xserver
REQUIRED_DISTRO_FEATURES = "x11"

# FIXME: We don't want to include the libtool archives (*.la) from modules
# directory, as they serve no useful purpose. Upstream should fix Makefile.am
do_install:append() {
	find ${D}${libdir}/xorg/modules -regex ".*\.la$" | xargs rm -f --
}

# Function to add the relevant ABI dependency to drivers, which should be called
# from a PACKAGEFUNC.
def _add_xorg_abi_depends(d, name):
    # Map of ABI names exposed in the dependencies to pkg-config variables
    abis = {
      "video": "abi_videodrv",
      "input": "abi_xinput"
    }

    output = os.popen("pkg-config xorg-server --variable=%s" % abis[name]).read()
    mlprefix = d.getVar('MLPREFIX') or ''
    abi = "%sxorg-abi-%s-%s" % (mlprefix, name, output.split(".")[0])

    pn = d.getVar("PN")
    d.appendVar('RDEPENDS:' + pn, ' ' + abi)

SECURITY_LDFLAGS = "${SECURITY_X_LDFLAGS}"

#####
DEPENDS += "xorgproto"

python add_xorg_abi_depends() {
    _add_xorg_abi_depends(d, "input")
}
PACKAGEFUNCS =+ "add_xorg_abi_depends"

FILES:${PN} += " ${libdir}/xorg/modules/input/*.so \
                 ${datadir}/X11/xorg.conf.d \
                 "
#####

SUMMARY = "X.Org X server -- keyboard input driver"

DESCRIPTION = "keyboard is an Xorg input driver for keyboards. The \
driver supports the standard OS-provided keyboard interface.  The driver \
functions as a keyboard input device, and may be used as the X server's \
core keyboard."

LIC_FILES_CHKSUM = "file://COPYING;md5=ea2099d24ac9e316a6d4b9f20b3d4e10"

SRC_URI[md5sum] = "a3a3f0dd32361dcdbd406e894dafe090"
SRC_URI[sha256sum] = "f7c900f21752683402992b288d5a2826de7a6c0c0abac2aadd7e8a409e170388"
