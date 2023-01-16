#
# This file is the meta-husky recipe.
#
inherit ros_distro_foxy

SUMMARY = "XSENSE library"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD;md5=3775480a712fc46a69647678acb234cb"

ROS_CN = "xsense"
ROS_BPN = "xsense"

ROS_BUILD_DEPENDS = " \
    rclcpp \
    tf2 \
    tf2-ros \
    std-msgs \
    geometry-msgs \
    sensor-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""
#
DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# # Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# # don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"
#
RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"


FILES:${PN}:prepend = " \
    ${datadir}/bluespace_ai_xsens_mti_driver \
"

# ERROR: husky-base-1.0-r0 do_package_qa: QA Issue: -dev package husky-base-dev contains non-symlink .so '/usr/lib/libhusky_hardware.so' [dev-elf]
FILES_SOLIBSDEV = ""
FILES:${PN} += " \
    ${libdir}/bluespace_ai_xsens_mti_driver \
"

ROS_BRANCH ?= "branch=foxy-husky"
SRC_URI = "git://github.com/PPI-PUT/bluespace_ai_xsens_ros_mti_driver;${ROS_BRANCH};protocol=https"
SRCREV = "bafb81836e4de3eada5ed128515d97ab537b3ada"

S = "${WORKDIR}/git"

do_compile:prepend() {
    oe_runmake -C ${S}/lib/xspublic
}

do_install:append() {
    install -Dm 0644 ${WORKDIR}/git/udev/49-xsens.rules ${D}${sysconfdir}/udev/rules.d/49-xsens.rules
}

ROS_BUILD_TYPE = "ament_cmake"


inherit ros_${ROS_BUILD_TYPE}
