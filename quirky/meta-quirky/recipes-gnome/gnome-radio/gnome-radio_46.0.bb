# Recipe created by recipetool
# recipetool create -o gnome-radio_46.0.bb https://www.gnomeradio.org/src/gnome-radio-46.0.tar.xz

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://www.gnomeradio.org/src/gnome-radio-${PV}.tar.xz"

SRC_URI[sha256sum] = "e31ecd7c27eee3f6e799603f995afabe8b9335e22b0c8df5c82b7065b2bac362"

#ERROR: gnome-radio-46.0-r1 do_fetch: Missing SRC_URI checksum, please add those to the recipe:
SRC_URI[archive.sha256sum] = "e31ecd7c27eee3f6e799603f995afabe8b9335e22b0c8df5c82b7065b2bac362"

DEPENDS = "intltool-native gstreamer1.0-plugins-bad libxml2 glib-2.0 geoclue \
           pango gstreamer1.0 gtk+3 gstreamer1.0-plugins-base libchamplain \
           geocode-glib cogl-1.0 clutter-1.0 clutter-gtk-1.0 libxml2 libxml2-native \
           glib-2.0-native libsoup libsoup-2.4"

#this expects meson:  gnomebase gnome-help
inherit gettext pkgconfig autotools itstool

EXTRA_OECONF = "--with-recording --without-help --disable-static --disable-gtk-doc \
                --disable-gtk-doc-html"

#No package 'geocode-glib-1.0' found
do_configure:prepend() {
 cp -f ${WORKDIR}/recipe-sysroot/usr/lib/pkgconfig/geocode-glib-2.0.pc ${WORKDIR}/recipe-sysroot/usr/lib/pkgconfig/geocode-glib-1.0.pc
}

#| ../gnome-radio-46.0/configure: line 15821: syntax error near unexpected token `1.16,'
#| ../gnome-radio-46.0/configure: line 15821: `GTK_DOC_CHECK(1.16, --flavour no-tmpl)'
#try run existing configure script...
do_configure() {
 oe_runconf
}

HOMEPAGE = "https://gnomeradio.org/"
SUMMARY = "Internet radio player"

