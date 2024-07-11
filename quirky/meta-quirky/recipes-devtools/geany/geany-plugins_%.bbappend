
#20240711
#dep is vte which requires gtk4. replace with vte9 which required gtk+3
DEPENDS:remove = "vte"
DEPENDS:append = " vte9"
