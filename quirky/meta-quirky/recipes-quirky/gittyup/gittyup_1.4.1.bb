# Recipe created by recipetool
# recipetool create -o gittyup_1.4.1.bb https://distro.ibiblio.org/easyos/source/alphabetical/g/gittyup-1.4.1.tar.gz

LICENSE = "MIT & GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=3e0b480588945a0173fd585217b49ef4 \
                    file://dep/libgit2/libgit2/COPYING;md5=5b002a195fb7ea2d8d583f07eaff3a8e"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/g/gittyup-${PV}.tar.gz"

SRC_URI[sha256sum] = "4430b5261ffb0ef6f542558f99f460a759f7c68f0e831ac7b10e448167b7d00d"

# NOTE: unable to map the following CMake package dependencies: PCRE2 Sanitizers Lua Libgcrypt mbedTLS Qt5Gui Iconv HTTP_Parser PCRE Doxygen
#leave out:  mbedtls gnome-keyring
#rem: libgit2 
DEPENDS = "zlib hunspell openssl libssh2 cmark cmark-native \
           lua lua-native expat curl openssl-native zlib git git-native \
           qtbase qtx11extras qttools qtsvg qtdeclarative qtimageformats \
           qtxmlpatterns qtscript libpcre libpcre2 libgcrypt libgcrypt-native \
           doxygen-native qtbase-native qttools-native"

inherit cmake_qt5 pkgconfig gettext python3-dir python3native mime mime-xdg

#ref: meta/classes-recipe/cmake.bbclass
OECMAKE_GENERATOR = "Ninja"

#get error:
#| /mnt/build/builds/oe/scarthgap/oe-quirky/build-amd64/tmp/work/nocona-64-poky-linux/gittyup/1.4.1/gittyup-1.4.1/src/git/Buffer.cpp:15:9: error: 'GIT_BUF_INIT_CONST' was not declared in this scope; did you mean 'GIT_BUF_INIT'?
# try change ON to OFF: -DUSE_SYSTEM_LIBGIT2=ON ...yeah that fixed it.
EXTRA_OECMAKE = "-DCMAKE_BUILD_TYPE=Release -DENABLE_UPDATE_OVER_GUI=OFF \
    -DUSE_SYSTEM_OPENSSL=ON -DUSE_SYSTEM_QT=ON -DUSE_SYSTEM_LUA=ON \
    -DUSE_SYSTEM_HUNSPELL=ON -DUSE_SYSTEM_LIBGIT2=OFF -DUSE_SYSTEM_GIT=ON \
    -DUSE_SYSTEM_CMARK=ON -DENABLE_TESTS=OFF -DUSE_SYSTEM_LIBSSH2=ON"

#WARNING: gittyup-1.4.1-r1 do_package_qa: QA Issue: File /usr/bin/indexer in package gittyup contains reference to TMPDIR
#File /usr/bin/gittyup in package gittyup contains reference to TMPDIR [buildpaths]
WARN_QA:remove = "buildpaths"

SUMMARY = "GUI for git"
HOMEPAGE = "https://github.com/Murmele/Gittyup"
