# Recipe created by recipetool
# recipetool create -o limine_7.12.0.bb https://github.com/limine-bootloader/limine/releases/download/v7.12.0/limine-7.12.0.tar.gz

LICENSE = "0BSD & BSD-2-Clause & GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b8e39cd4a81a11436885d845033d3fb8 \
                    file://build-aux/freestanding-toolchain/LICENSE;md5=2f2d19e311e9e82cc370eea3b94061f7 \
                    file://common/cc-runtime/LICENSE.TXT;md5=d846d1d65baf322d4c485d6ee54e877a \
                    file://common/flanterm/LICENSE;md5=5c905a090e69725c0c270ed82a442f56 \
                    file://decompressor/cc-runtime/LICENSE.TXT;md5=d846d1d65baf322d4c485d6ee54e877a \
                    file://freestanding-headers/LICENSE;md5=b42726b0ad58fbafb23016db65878d21 \
                    file://limine-efi/COPYING;md5=c27a4b4a954b36c8afddf7587fd749be"

SRC_URI = "https://github.com/limine-bootloader/limine/releases/download/v${PV}/limine-${PV}.tar.gz \
           file://remove-ext4-encrypt-warning.patch"

SRC_URI[sha256sum] = "4375cf42ef6eec06180c23eb2269b7d8ff50a6f09bf92ebf838c130a7214c457"

inherit autotools

DEPENDS = "mtools-native nasm-native coreutils-native"

# ref: https://github.com/limine-bootloader/limine/issues/193
export CROSS_TOOLCHAIN="${TARGET_SYS}"

#20220819 make this work for i686 host...
#EXTRA_OECONF = "--enable-all"
EXTRA_OECONF = "--enable-bios --enable-limine-deploy --enable-bios-cd --enable-bios-pxe"
EXTRA_OECONF:append:x86-64 = " --enable-uefi-ia32 --enable-uefi-x86_64 --enable-cd-efi"

SUMMARY = "x86/x86_64 BIOS/UEFI bootloader"
HOMEPAGE = "https://limine-bootloader.org/"
