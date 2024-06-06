
do_configure:prepend() {
 sed -i -e 's%^check-if-root:%check-if-rootXXX:%' ${S}/Makefile.in
 echo -e '\n\ncheck-if-root:\n\n' >> ${S}/Makefile.in
}

do_compile:prepend() {
 #have saved all the tarballs that libreoffice downloads...
 mkdir -p ${S}/external/tarballs
 #$TMPDIR is in the 'tmp' folder...
 if [ -d ${TMPDIR}/../../../tarballs-libreoffice ];then
  cp -a -u ${TMPDIR}/../../../tarballs-libreoffice/* ${S}/external/tarballs/
 fi
 #disable this check...
 #sed -i -e 's%^check-if-root:%check-if-root:\n\ncheck-if-rootXXX:%' ${B}/Makefile
}

#20221201 1st build was successful. now bring in some options from dunfell...
# don't recall why adding these two...
DEPENDS += " zip-native neon-native"
EXTRA_OECONF += " --with-system-neon --disable-gstreamer-1-0"

#20240103 scarthgap: system libetonyek compile fail.
DEPENDS:remove = "libetonyek"
EXTRA_OECONF:remove = "--with-system-libetonyek"
DEPENDS:remove = "libwps"
EXTRA_OECONF:remove = "--with-system-libwps"
#20240104
DEPENDS:remove = "icu"
EXTRA_OECONF:remove = "--with-system-icu"
