
#20240629 remove warning running as root...
do_compile:prepend() {
 sed -i -e 's%geteuid () == 0)%geteuid () == 99999)%' ${S}/thunar/thunar-window.c
}
