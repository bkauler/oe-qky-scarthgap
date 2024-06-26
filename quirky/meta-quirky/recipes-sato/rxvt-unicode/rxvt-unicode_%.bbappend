HOMEPAGE = "http://software.schmorp.de/pkg/rxvt-unicode.html"

#PR = "r2"
# 20210121 PR_NUM is defined in local.conf... 20220131 removed...
#PR = "r${@int(PR_NUM) + 2}"

# the official rxvt-unicode is crippled, doesn't build 'urxvt' binary.

# official recipe left off pkgconfig, it is needed...
inherit autotools update-alternatives pkgconfig

#20221207 add libptytty
#20240521 add ncurses-native (need tic)
DEPENDS = "libx11 libxt libxft gdk-pixbuf libxmu \
           freetype fontconfig libxrender expat zlib libxcb libxext \
           libsm libice libxau libxdmcp libptytty ncurses-native"

# this is the official configure options...
# EXTRA_OECONF = "--enable-xim 
# 		--enable-utmp --enable-wtmp --enable-lastlog 
# 		--with-term=rxvt --enable-keepscrolling 
# 		--enable-xft --with-name=rxvt --enable-frills 
# 		--enable-swapscreen --enable-transparency 
# 		--with-codesets=eu --enable-pointer-blank 
# 		--enable-text-blink --enable-rxvt-scroll 
# 		--enable-combining --disable-perl 
# 		--with-x=${STAGING_DIR_HOST}${prefix}"

# BK my preference...
EXTRA_OECONF = "--disable-everything --enable-xft --enable-font-styles \
    --enable-xim --enable-combining --with-res-name=rxvt --with-res-class=Rxvt \
    --enable-rxvt-scroll --enable-frills --enable-keepscrolling \
    --enable-selectionscrolling --enable-mousewheel --enable-slipwheeling \
    --enable-smart-resize --enable-text-blink --enable-pointer-blank \
    --enable-256-color --with-x=${STAGING_DIR_HOST}${prefix}"

