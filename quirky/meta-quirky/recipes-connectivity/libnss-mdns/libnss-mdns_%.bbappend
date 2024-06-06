# remove avahi dep.

DEPENDS = ""

RDEPENDS:${PN} = ""

EXTRA_OECONF = "--libdir=${base_libdir} --disable-lynx --disable-avahi"
