
#20231231
#chromium 119.0.6045.199
#the default do_configure has this:
# use_cups=false use_custom_libcxx=false ffmpeg_branding="Chromium"
# proprietary_codecs=false use_vaapi=false is_component_build=false
# use_gnome_keyring=false use_kerberos=false use_alsa=false use_pulseaudio=true
# use_system_libjpeg=true  use_system_freetype=false use_qt=false
# enable_js_type_check=false host_pkg_config="pkg-config-native"
# is_debug=false is_official_build=true use_lld=true use_gold=false
# symbol_level=0 enable_remoting=false enable_nacl=false use_sysroot=false
# treat_warnings_as_errors=false is_cfi=false disable_fieldtrial_testing_config=true
# chrome_pgo_phase=0 google_api_key="invalid-api-key" google_default_client_id="invalid-client-id"
# google_default_client_secret="invalid-client-secret"
# custom_toolchain="//build/toolchain/yocto:yocto_target" gold_path=""
# host_toolchain="//build/toolchain/yocto:yocto_native" is_clang=true
# clang_base_path="/mnt/build/builds/oe/scarthgap/oe-quirky/build-amd64/tmp/work/core2-64-poky-linux/chromium-x11/119.0.6045.199/recipe-sysroot-native/usr/bin"
# clang_use_chrome_plugins=false target_cpu="x64"
# v8_snapshot_toolchain="//build/toolchain/yocto:yocto_target"
# max_jobs_per_link="2" enable_rust=false ozone_auto_platforms=false
# ozone_platform_x11=true

PACKAGECONFIG:append:class-target = " cups proprietary-codecs use-vaapi"
