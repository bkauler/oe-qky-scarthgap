#20230609 testing qemu in easy 5.3.2, discovered only alsa support, no pa...
#20230617 federico: usb-host (libusb) and virtio-9p-pci (virtfs) missing.

#PR_NUM is defined in local.conf...
#PR = "r${@int(PR_NUM) + 2}"

PACKAGECONFIG:append:class-target = " pulseaudio"

#20230617 add lots more...
#20240520 libxml2 not there anymore
PACKAGECONFIG:append:class-target = " virtfs libusb libcap-ng ssh gcrypt \
                                    gnutls xkbcommon libudev attr"

#20230617 now it wants this...
DEPENDS:append:class-target = " libtasn1"
