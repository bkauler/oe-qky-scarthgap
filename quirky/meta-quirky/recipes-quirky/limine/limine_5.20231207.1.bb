# Recipe created by recipetool
# recipetool create -o limine_4.20231006.0.bb https://github.com/limine-bootloader/limine/releases/download/v4.20231006.0/limine-4.20231006.0.tar.gz

LICENSE = "0BSD & BSD-2-Clause & GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=a5d0899bc6d3be3524dc598e516a0f9d \
                    file://common/flanterm/LICENSE;md5=c8e191db1b44779cdae7f55957960772 \
                    file://freestanding-headers/LICENSE;md5=9f8f538c28963f413355725eefaf403b \
                    file://libgcc-binaries/COPYING.RUNTIME;md5=fe60d87048567d4fe8c8a0ed2448bcc8 \
                    file://libgcc-binaries/COPYING3;md5=d32239bcb673463ab874e80d47fae504 \
                    file://limine-efi/COPYING;md5=c27a4b4a954b36c8afddf7587fd749be"

SRC_URI = "https://github.com/limine-bootloader/limine/releases/download/v${PV}/limine-${PV}.tar.gz \
           file://remove-ext4-encrypt-warning.patch"

SRC_URI[sha256sum] = "10134e79e5185439e74411c9dbf727d334b94678a5d2b5e6b750200f25f3c738"

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
