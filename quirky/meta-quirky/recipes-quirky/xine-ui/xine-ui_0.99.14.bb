# Recipe created by recipetool
# recipetool create -o xine-ui_0.99.14.bb https://sourceforge.net/projects/xine/files/xine-ui/0.99.14/xine-ui-0.99.14.tar.xz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "https://sourceforge.net/projects/xine/files/xine-ui/${PV}/xine-ui-${PV}.tar.xz"

SRC_URI[sha256sum] = "d4d490d5cece70e2bb9849c9e482f2cf87af0302d451b614476fdcc3642cd9c3"

DEPENDS = "libjpeg-turbo curl xine-lib libxv libxext libxinerama libxtst libxscrnsaver \
           libxxf86vm libpng libx11 libpng12 libxft openssl libxt"

inherit gettext pkgconfig autotools

EXTRA_OECONF = "--enable-xft --disable-lirc --without-aalib --without-caca"


do_compile:prepend() {
 #20240527 compile error...
 echo -e '\n\nall:\n\ninstall:\n\nclean:\n\n' > ${B}/misc/desktops/Makefile
 
 #20240527 hack...
 find ${B} -type f -name Makefile | xargs -I XXX sed -i -e 's% -Werror-[^ ]*% %g' XXX
 
}

do_install:append() {
 mkdir -p ${D}/usr/share/applications
 echo '[Desktop Entry]
Encoding=UTF-8
Name=Xine multimedia player
Comment=Video Player
Exec=xine
Icon=xine.png
Terminal=false
Type=Application
Categories=X-Multimedia-mediaplayer
MimeType=x-content/video-dvd;x-content/audio-cdda;x-content/video-vcd;x-content/video-svcd;application/annodex;application/x-annodex;audio/annodex;audio/x-annodex;video/annodex;video/x-annodex;video/x-ms-asf;video/x-ms-wmv;audio/x-ms-wma;application/vnd.ms-asf;application/x-mplayer2;video/x-ms-asf-plugin;video/x-ms-wvx;video/x-ms-wax;video/mkv;video/x-matroska;video/webm;audio/mpegurl;audio/x-mpegurl;audio/x-scpls;audio/x-ms-wax;audio/x-ms-wvx;application/smil;application/x-quicktimeplayer;application/xspf+xml;video/mp2t;image/png;image/x-png;video/mng;video/x-mng;video/quicktime;video/x-quicktime;audio/x-m4a;video/mp4;audio/mp4;video/x-flv;video/flv;application/x-flash-video;video/msvideo;video/x-msvideo;video/mp2p;audio/x-aiff;audio/aiff;audio/x-pn-aiff;audio/x-flac;audio/flac;audio/x-realaudio;audio/basic;audio/x-basic;audio/x-pn-au;audio/x-pn-realaudio;audio/x-pn-realaudio-plugin;audio/x-real-audio;application/vnd.rn-realmedia;audio/x-8svx;audio/8svx;audio/x-16sv;audio/168sv;image/x-ilbm;image/ilbm;video/x-anim;video/anim;video/x-flic;application/ogg;application/x-ogm;application/x-ogm-audio;application/x-ogm-video;application/x-ogg;audio/ogg;audio/x-ogg;video/ogg;video/x-ogg;video/mpeg;video/x-mpeg;audio/ac3;audio/x-wav;audio/wav;audio/x-pn-wav;audio/x-pn-windows-acm;audio/musepack;audio/x-musepack;audio/mpeg2;audio/x-mpeg2;audio/mpeg3;audio/x-mpeg3;audio/mpeg;audio/x-mpeg;audio/x-mpegurl;audio/mpegurl;audio/mp3;audio/x-mp3;application/x-flac;x-content/video-dvd;x-content/video-svcd;x-content/video-vcd;
' > ${D}/usr/share/applications/xine.desktop
 mkdir -p ${D}/usr/share/icons/hicolor/48x48/apps
 cp -f ${S}/misc/desktops/icons/48/xine.png ${D}/usr/share/icons/hicolor/48x48/apps/
 mkdir -p ${D}/usr/share/pixmaps
 cp -f ${S}/misc/desktops/icons/48/xine.png ${D}/usr/share/pixmaps/
 mkdir -p ${D}/usr/share/xine/skins
 cp -f ${S}/misc/splash-default/xine_splash.png ${D}/usr/share/xine/skins/
 cp -f ${S}/misc/splash-default/xine-ui_logo.png ${D}/usr/share/xine/skins/
 cp -f ${S}/misc/splash-default/xine-ui_logo.mpg ${D}/usr/share/xine/skins/
}

HOMEPAGE = "https://www.xine-project.org/"
SUMMARY = "Media player, xlib based gui for xine-lib"
