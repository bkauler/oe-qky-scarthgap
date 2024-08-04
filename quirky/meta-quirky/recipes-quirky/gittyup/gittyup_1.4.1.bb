# Recipe created by recipetool
# recipetool create -o gittyup_1.4.1.bb https://distro.ibiblio.org/easyos/source/alphabetical/g/gittyup-1.4.1.tar.gz

LICENSE = "Apache-2.0 & BSD-3-Clause & BSL-1.0 & GPL-2.0-only & LGPL-2.1-only & MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=3e0b480588945a0173fd585217b49ef4 \
                    file://LICENSE.md.in;md5=8c40b023d13042ac0965a2404c300736 \
                    file://dep/cmark/cmark/COPYING;md5=81f9cae6293cc0345a9144b78152ab62 \
                    file://dep/openssl/openssl/LICENSE;md5=d343e62fc9c833710bbbed25f27364c8 \
                    file://dep/openssl/openssl/external/perl/Text-Template-1.46/COPYING;md5=361b6b837cad26c6900a926b62aada5f \
                    file://dep/openssl/openssl/util/perl/OpenSSL/copyright.pm;md5=dd423710d2592f2c789ab50002e9dd06 \
                    file://dep/scintilla/lexilla/License.txt;md5=778e351f84dbd507f4dd33fb535690e7 \
                    file://dep/scintilla/lexilla/test/unit/LICENSE_1_0.txt;md5=e4224ccaecb14d942c71d31bef20d78c \
                    file://dep/scintilla/scintillua/LICENSE;md5=4a4be7fec6c015e1ef0ca43ea9fb1ffe"

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
