# Recipe created by recipetool
# recipetool create -o network-manager-applet_1.8.20.bb https://gitlab.gnome.org/GNOME/network-manager-applet/-/archive/1.8.20/network-manager-applet-1.8.20.tar.bz2
# 20201012 remove modemmanager dep.

#ref: https://bkhome.org/news/201909/network-manager-applet-patched-for-easyos.html

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

SRC_URI = "https://gitlab.gnome.org/GNOME/network-manager-applet/-/archive/${PV}/network-manager-applet-${PV}.tar.bz2 \
  file://00-Allow-creation-of-connections-without-admin-privileges.patch \
  file://01-Force-online-state-with-unmanaged-devices.patch \
  file://02-Fix-double-free-crashing-in-wifi-dialog.patch \
  file://03-BK-external-trigger-menu.patch \
  file://04-BK-replacement-icons-part1.patch \
  file://05-BK-replacement-icons-part2.patch \
  file://gtk-doc.make \
  "

SRC_URI[md5sum] = "8f6b4e5cf856c71e7dfd2a7441b2ab4e"
SRC_URI[sha1sum] = "d681ae0f9814f49135bda83f568690cf0a73afea"
SRC_URI[sha256sum] = "2dd8cdbf2e5db3891751b3ee1e41a62c757c767bf9429469353e8d87bc205932"
SRC_URI[sha384sum] = "54b986b299d47dbc183ccd43ede4ddc72def0d9b28aed388f0a9a5b8bb59f5a8d5728d90c0fe7f451ae4a7d2836a396c"
SRC_URI[sha512sum] = "a4b3ddb456423a5a568daee718cc0a78e4386d76ec45d42560035702be4932eb00df809a0e47959ec70f2be446c188222766459a12786e8a9806c713ff200c24"

#20240103 gcr is now gcr3...
DEPENDS = "gcr3 intltool-native jansson libnotify libsecret networkmanager \
           mobile-broadband-provider-info gtk+3 glib-2.0 libgudev iso-codes \
           glib-2.0-native libtool libtool-native libgcrypt p11-kit \
           gtk-doc-native"

inherit gettext pkgconfig autotools-brokensep

EXTRA_OECONF = "--with-gcr --with-selinux=no --with-appindicator=no --enable-introspection=no --enable-gtk-doc-html=no --without-wwan --enable-more-warnings=no --enable-gtk-doc=no"

#| autoreconf: running: automake --add-missing --copy --force-missing
#| configure.ac:33: installing './compile'
#| configure.ac:52: installing './config.guess'
#| configure.ac:52: installing './config.sub'
#| configure.ac:17: installing './install-sh'
#| configure.ac:17: installing './missing'
#| automake: error: cannot open < gtk-doc.make: No such file or directory

#oh man, this is so awful. it is supposed to be created but isn't, so I am pulling it in...
do_configure:prepend() {
 cp -f ${WORKDIR}/gtk-doc.make ./
}

do_configure() {
 
 #20210104 do_configure error, need to tell it we are cross compiling...
 #./autogen.sh --with-gcr --with-selinux=no --with-appindicator=no --enable-introspection=no --enable-gtk-doc-html=no --without-wwan --enable-more-warnings=no --enable-gtk-doc=no
 ./autogen.sh --with-gcr --with-selinux=no --with-appindicator=no --enable-introspection=no --enable-gtk-doc-html=no --without-wwan --enable-more-warnings=no --enable-gtk-doc=no --host=${HOST_SYS} --build=${BUILD_SYS}
 
 oe_runconf
}

HOMEPAGE = "https://gitlab.gnome.org/GNOME/network-manager-applet"
SUMMARY = "network manager tray applet"

