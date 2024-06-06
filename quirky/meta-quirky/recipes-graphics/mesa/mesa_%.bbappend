#20211018
#before having this .bbappend, do_configure had this:
# NOTE: Executing meson -Dshared-glapi=true -Dgallium-opencl=disabled -Dglx-read-only-text=true -Dplatforms=x11,drm,surfaceless -Ddri=true -Ddri-drivers=,r100,r200,nouveau,i965,i915 -Ddri3=true -Degl=true -Delf-tls=true -Dgallium-drivers=swrast,virgl -Dllvm=false -Dgbm=true -Dgles1=true -Dgles2=true -Dopengl=true -Dosmesa=none -Dlibunwind=false -Dvulkan-drivers= -Dgallium-xa=false -Dgallium-xvmc=false -Dasm=false...
#after having this .bbappend, with "r600", do_configure has this:
# NOTE: Executing meson -Dshared-glapi=true -Dgallium-opencl=disabled -Dglx-read-only-text=true -Dplatforms=x11,drm,surfaceless -Ddri=true -Ddri-drivers=,r100,r200,nouveau,i965,i915 -Ddri3=true -Degl=true -Delf-tls=true -Dgallium-drivers=swrast,r300,svga,nouveau,,radeonsi,r600,virgl -Dllvm=true -Dshared-llvm=true -Dgbm=true -Dgles1=true -Dgles2=true -Dopengl=true -Dosmesa=none -Dlibunwind=false -Dvulkan-drivers= -Dgallium-xa=false -Dgallium-xvmc=false -Dasm=false...
#note, if leave out the "r600", do_configure has this:
# NOTE: Executing meson -Dshared-glapi=true -Dgallium-opencl=disabled -Dglx-read-only-text=true -Dplatforms=x11,drm,surfaceless -Ddri=true -Ddri-drivers=,r100,r200,nouveau,i965,i915 -Ddri3=true -Degl=true -Delf-tls=true -Dgallium-drivers=swrast,r300,svga,nouveau,virgl -Dllvm=true -Dshared-llvm=true -Dgbm=true -Dgles1=true -Dgles2=true -Dopengl=true -Dosmesa=none -Dlibunwind=false -Dvulkan-drivers= -Dgallium-xa=false -Dgallium-xvmc=false -Dasm=false...

#20211119 want r600...

#want these extra drivers nouveau_dri.so, r300, etc...
#PACKAGECONFIG_append_x86-64_class-target = "gallium gallium-llvm"

#this adds r600 and radeonsi...
PACKAGECONFIG:append:x86-64:class-target = " gallium gallium-llvm r600"

#20220609 compiling for rpi
PACKAGECONFIG:append:aarch64:class-target = " v3d vc4"

#20221202 add...
PACKAGECONFIG:append:x86:class-target = " gallium gallium-llvm r600"

#20230602 to support xf86-video-vmwgfx
PACKAGECONFIG:append:x86:class-target = " xa"
PACKAGECONFIG:append:x86-64:class-target = " xa"

