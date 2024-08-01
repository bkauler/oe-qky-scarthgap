SUMMARY = "tesseract-ocr language files"
HOMEPAGE = "https://github.com/tesseract-ocr/tessdata"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRCREV = "4767ea922bcc460e70b87b1d303ebdfed0897da8"
SRC_URI = "git://github.com/tesseract-ocr/tessdata.git;branch=main;protocol=https"

S = "${WORKDIR}/git"

inherit allarch

do_install() {
    install -d ${D}${datadir}/tessdata
    install -m644 ${S}/spa.traineddata ${D}${datadir}/tessdata
}

do_install:append() {
 #hack, delete these as so enormous about 1GB...
 rm -f ${S}/*.traineddata
}

RDEPENDS:tessdata-spa = "tesseract"
PROVIDES = "tessdata-spa"
