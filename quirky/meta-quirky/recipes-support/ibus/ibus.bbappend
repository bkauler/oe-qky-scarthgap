
PACKAGECONFIG:append = " gtk2"

#20240908 trying to only have gtk2 and gtk3 apps in easyos...
PACKAGECONFIG:remove = "gtk4"
