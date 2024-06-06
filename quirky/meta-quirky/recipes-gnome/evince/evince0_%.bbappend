
#20221203 gnome-desktop requires gtk4 and a later pango
DEPENDS:remove = "gnome-desktop"

#20240521 needs gtkdocize
DEPENDS:append = " gtk-doc-native"
