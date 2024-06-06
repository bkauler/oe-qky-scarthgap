
#20240103 scarthgap
DEPENDS:append = " dbus-glib dbus-glib-native libgudev libndp jansson \
  dhcpcd gnutls readline libidn zlib libunistring \
  nettle gmp libffi libpcre ncurses glib-2.0 ofono \
  libsoup-2.4 slang popt rp-pppoe ppp wpa-supplicant bluez5"

NETWORKMANAGER_DNS_RC_MANAGER_DEFAULT = "auto"
NETWORKMANAGER_DHCP_DEFAULT = "internal"
NETWORKMANAGER_FIREWALL_DEFAULT = "iptables"

#just in case...
PACKAGECONFIG:remove = "polkit"
PACKAGECONFIG:remove = "consolekit"
PACKAGECONFIG:remove = "modemmanager"
PACKAGECONFIG:remove = "ifupdown"
PACKAGECONFIG:remove = "nss"
PACKAGECONFIG:remove = "dhclient"
PACKAGECONFIG:remove = "dhcpcd"
PACKAGECONFIG:remove = "vala"
PACKAGECONFIG:remove = "ovs"
PACKAGECONFIG:remove = "audit"
PACKAGECONFIG:remove = "selinux"

#this is in the .bb file:
#PACKAGECONFIG[resolvconf] = "-Dresolvconf=${base_sbindir}/resolvconf,-Dresolvconf=no,,resolvconf"
#...hmmm, leave it out.
PACKAGECONFIG:remove = "resolvconf"
#...will be out anyway, as set NETWORKMANAGER_DNS_RC_MANAGER_DEFAULT = "auto"

PACKAGECONFIG:append = " bluez5 wifi iwd gnutls ppp nmtui readline concheck man-resolv-conf"
