
#20240802
#do not want dep of tesseract-lang-eng. instead have my own recipes
#ex: tessdata-eng
#was: RRECOMMENDS:${PN} += "tesseract-lang-eng"
RRECOMMENDS:${PN}:remove = "tesseract-lang-eng"
