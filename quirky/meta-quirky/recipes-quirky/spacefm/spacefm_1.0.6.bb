# Recipe created by recipetool
# recipetool create -o spacefm_1.0.6.bb https://github.com/IgnorantGuru/spacefm/archive/refs/tags/1.0.6.tar.gz

LICENSE = "GPL-3.0-only & LGPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
                    file://COPYING-LGPL;md5=e6a600fd5e1d9cbde2d983680233ad02"

SRC_URI = "https://github.com/IgnorantGuru/spacefm/archive/refs/tags/${PV}.tar.gz \
    file://update_desktop_files.patch \
    file://drop_spacefm-installer.patch \
    file://fix-spelling-error.patch \
    file://drop-gnome-common-macros.patch \
    file://add_optional_close_last_tab.patch \
    file://0001-glibc-2.28-compatibility.patch \
    file://fix-gcc10-fno-common.patch"

SRC_URI[sha256sum] = "fedea9fcad776e0af4b8d90c5a1c86684a9c96ef1cdd4e959530ce93bdebe7c9"

#20240702 already in another pkg:  eject
DEPENDS = "libx11 eudev intltool-native \
           gdk-pixbuf cairo glib-2.0 glib-2.0-native pango \
           startup-notification shared-mime-info gtk+ \
           dbus dbus-glib lsof sshfs-fuse wget \
           curlftpfs gphotofs bash"

inherit gettext pkgconfig autotools

#note, in easyos sudo is a script...
EXTRA_OECONF = "--with-preferable-sudo=sudo --with-gtk2 --disable-desktop-integration \
    --disable-superuser-checks --disable-video-thumbnails"

RDEPENDS:spacefm = "bash"

HOMEPAGE = "https://ignorantguru.github.io/spacefm/"
SUMMARY = "File manager"