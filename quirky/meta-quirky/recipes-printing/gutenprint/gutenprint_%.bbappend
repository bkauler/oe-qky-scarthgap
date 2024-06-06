
#20240527 do_package fail:
#Exception: Exception: KeyError: 'getgrgid(): gid not found: 102'
#Path /mnt/build/builds/oe/scarthgap/oe-quirky/build-amd64/tmp/work/nocona-64-poky-linux/gutenprint/5.3.4/sstate-build-package/package/etc/cups is owned by uid 0, gid 102, which doesn't match any user/group on target. This may be due to host contamination.

do_install:append() {
  chgrp root ${D}${sysconfdir}/cups
}
