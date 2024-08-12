# Recipe created by recipetool
# recipetool create -o whisper-cpp_1.6.2.bb https://github.com/ggerganov/whisper.cpp/archive/refs/tags/v1.6.2.tar.gz

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1539dadbedb60aa18519febfeab70632"

SRC_URI = "https://github.com/ggerganov/whisper.cpp/archive/refs/tags/v${PV}.tar.gz"

SRC_URI[sha256sum] = "da7988072022acc3cfa61b370b3c51baad017f1900c3dc4e68cb276499f66894"

S = "${WORKDIR}/whisper.cpp-${PV}"

#inherit pkgconfig

do_configure() {
 #Makefile reads /proc/cpuinfo of host system...
 sed -i -e 's%CPUINFO_CMD := cat /proc/cpuinfo%CPUINFO_CMD := nothing%' ${S}/Makefile 
 #tries to execute main after acompiling...
 sed -i -e 's%\./main -h%%' ${S}/Makefile
}

do_compile() {
 oe_runmake main
}

do_install() {
 install -d ${D}/usr/local/whisper-cpp/model
 install -m755 ${S}/main ${D}/usr/local/whisper-cpp
 install ${S}/models/download-ggml-model.sh ${D}/usr/local/whisper-cpp/model
}

SUMMARY = "Voice to text"
HOMEPAGE = "https://github.com/ggerganov/whisper.cpp"
