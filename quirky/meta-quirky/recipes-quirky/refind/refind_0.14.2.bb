# Recipe created by recipetool
# recipetool create -o refind_0.13.3.1.bb https://downloads.sourceforge.net/project/refind/0.13.3.1/refind-src-0.13.3.1.tar.gz
# recipetool create -o refind_0.14.2.bb https://sourceforge.net/projects/refind/files/0.14.2/refind-src-0.14.2.tar.gz

SUMMARY = "EFI boot manager software"
HOMEPAGE = "https://sourceforge.net/projects/refind/"

LICENSE = "GPL-3.0-only & GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=d32239bcb673463ab874e80d47fae504 \
                    file://LICENSE.txt;md5=9396e07c1572dd19fb709d14874e00ec"

SRC_URI = "https://downloads.sourceforge.net/project/refind/${PV}/refind-src-${PV}.tar.gz"

SRC_URI[sha256sum] = "f7d93ce80da76b86c567281ea225b6a87907ce86ff77233c9357a522c115c8f0"

DEPENDS = "gnu-efi"

do_configure () {
 true
}

do_compile () {
 #oe_runmake all_gnuefi
 oe_runmake gnuefi
 cd filesystems
 oe_runmake ext2_gnuefi
 oe_runmake ext4_gnuefi
 oe_runmake iso9660_gnuefi
 oe_runmake btrfs_gnuefi
 oe_runmake ntfs_gnuefi
 cd ..
}

do_install () {
 install -d ${D}/usr/share/refind/drivers_x64
 install -m644 filesystems/ext2_x64.efi ${D}/usr/share/refind/drivers_x64
 install -m644 filesystems/ext4_x64.efi ${D}/usr/share/refind/drivers_x64
 install -m644 filesystems/iso9660_x64.efi ${D}/usr/share/refind/drivers_x64
 install -m644 filesystems/btrfs_x64.efi ${D}/usr/share/refind/drivers_x64
 install -m644 filesystems/ntfs_x64.efi ${D}/usr/share/refind/drivers_x64
 install -m644 refind/refind_x64.efi ${D}/usr/share/refind
 install -m644 gptsync/gptsync_x64.efi ${D}/usr/share/refind
}

