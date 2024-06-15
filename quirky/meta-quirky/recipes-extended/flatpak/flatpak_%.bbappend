
#20240615
#want to get rid of dependence on polkit...

REQUIRED_DISTRO_FEATURES = ""

#removed: polkit
#these were deps in 1.12.8, add them here just in case...
#added: libsoup-2.4 libxau xmlto libxslt libyaml gcab glib-networking
#       openssl gnutls bzip2 xz zip zlib glib-2.0-native libxml2-native
#       libassuan gtk-doc-native
DEPENDS = " \
    appstream \
    bison-native \
    dconf \
    fuse3 \
    gdk-pixbuf \
    glib-2.0 \
    gpgme \
    json-glib \
    libarchive \
    libcap \
    libxml2 \
    ostree \
    python3-pyparsing-native \
    zstd \
    libsoup-2.4 libxau xmlto libxslt libyaml gcab glib-networking \
    openssl gnutls bzip2 xz zip zlib glib-2.0-native libxml2-native \
    libassuan gtk-doc-native \
"

PACKAGECONFIG:append = " soup"

#system_helper is what brings in need for polkit...
EXTRA_OEMESON:append = " -Dsystem_helper=disabled \
     -Dsystem_install_dir=/mnt/wkg/flatpak \
     -Drun_media_dir=/mnt"

#NOTICE#
#oh crap, cannot remove "useradd" from inherit in original .bb
#have to copy the whole lot here and edit flatpak_1.15.6.bb

SUMMARY = "Linux application sandboxing and distribution framework"
HOMEPAGE = "https://flatpak.org/"
