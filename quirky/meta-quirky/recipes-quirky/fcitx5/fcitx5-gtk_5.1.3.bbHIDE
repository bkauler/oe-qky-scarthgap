# Recipe created by recipetool
# recipetool create -o fcitx5-gtk_5.1.3.bb https://github.com/fcitx/fcitx5-gtk/archive/refs/tags/5.1.3.tar.gz
#ref: https://github.com/ashie/meta-inputmethod/blob/master/recipes-support/fcitx5-gtk/fcitx5-gtk_5.0.7.bb

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-or-later.txt;md5=2a4f4fd2128ea2f65047ee63fbca9f68"

SRC_URI = "https://github.com/fcitx/fcitx5-gtk/archive/refs/tags/${PV}.tar.gz \
           file://fcitx-gtk-fix-old-glib.patch"

SRC_URI[sha256sum] = "1892fcaeed0e710cb992a87982a8af78f9a9922805a84da13f7e3d416e2a28d1"

#rem:  gobject-introspection gobject-introspection-native
DEPENDS = "libx11 gtk+ gtk+3 libxkbcommon fcitx5 extra-cmake-modules-native \
    glib-2.0 glib-2.0-native"

inherit cmake pkgconfig python3-dir python3native gtk-immodules-cache

GTKIMMODULES_PACKAGES = "fcitx5-gtk"

#ENABLE_GIR=ON compile fail.
EXTRA_OECMAKE = "-DENABLE_GIR=OFF -DENABLE_GTK2_IM_MODULE=ON \
   -DENABLE_GTK3_IM_MODULE=ON -DENABLE_GTK4_IM_MODULE=OFF -DENABLE_SNOOPER=ON"

#FILES:${PN}2 += "${libdir}/gtk-2.0/2.10.0/immodules/im-fcitx5.so"
#FILES_${PN}3 += "${libdir}/gtk-3.0/3.0.0/immodules/im-fcitx5.so"

SUMMARY = "gtk im module for fcitx5"
HOMEPAGE = "https://github.com/fcitx/fcitx5-gtk"
