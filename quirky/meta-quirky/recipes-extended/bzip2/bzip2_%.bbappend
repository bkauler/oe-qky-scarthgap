
#BK 20240923
# meta-poky//conf/distro/poky.conf has this line:
# require conf/distro/include/no-static-libs.inc (whic is in meta)
# which has variabel DISABLE_STATIC="--disable-static" clear it:
DISABLE_STATIC = ""

#not sure if need this...
SSTATE_ALLOW_OVERLAP_FILES = "/"
