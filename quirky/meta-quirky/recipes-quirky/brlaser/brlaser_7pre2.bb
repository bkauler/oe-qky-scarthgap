# Recipe created by recipetool
# recipetool create -o brlaser_7pre2.bb https://distro.ibiblio.org/easyos/source/alphabetical/b/brlaser-7pre2.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/b/brlaser-${PV}.tar.gz"

SRC_URI[sha256sum] = "dd117883d0ea2b08ac0ba25e39060acffbae58514d4b01c888066df7d2632487"

inherit cmake

DEPENDS = "cups ghostscript cups-filters"

EXTRA_OECMAKE = ""

HOMEPAGE = "https://github.com/pdewacht/brlaser"
SUMMARY = "cups driver for many brother printers"

