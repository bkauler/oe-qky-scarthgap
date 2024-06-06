
#20221226
# i removed mariadb dep from libreoffice, but then discovered that redland
# recipe has mariadb dep. redland is a dep of libreoffice.

#PR_NUM is defined in local.conf...
#PR = "r${@int(PR_NUM) + 1}"

DEPENDS:remove = "mariadb"
DEPENDS:append = " sqlite3 libxml2 openssl"
inherit pkgconfig
EXTRA_OECONF:append = " --with-sqlite=3"
HOMEPAGE = "https://librdf.org/"

