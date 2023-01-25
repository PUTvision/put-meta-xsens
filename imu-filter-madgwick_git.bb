#
# This file is the meta-husky recipe.
#
inherit ros_distro_foxy

SUMMARY = "imu-tools library"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD;md5=3775480a712fc46a69647678acb234cb"

ROS_CN = "imu-tools"
ROS_BPN = "imu-filter-madgwick"

ROS_BUILD_DEPENDS = " \
    geometry-msgs \
    message-filters \
    pluginlib \
    sensor-msgs \
    tf2 \
    tf2-geometry-msgs \
    tf2-ros \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    geometry-msgs \
    message-filters \
    pluginlib \
    sensor-msgs \
    tf2 \
    tf2-geometry-msgs \
    tf2-ros \
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
    ${datadir}/imu_filter_madgwick \
"

# ERROR: husky-base-1.0-r0 do_package_qa: QA Issue: -dev package husky-base-dev contains non-symlink .so '/usr/lib/libhusky_hardware.so' [dev-elf]
FILES_SOLIBSDEV = ""
FILES:${PN} += " \
    ${libdir}/imu_filter_madgwick \
    ${libdir}/libimu_filter_madgwick.so \
"

ROS_BRANCH ?= "branch=foxy"
SRC_URI = "git://github.com/CCNYRoboticsLab/imu_tools;${ROS_BRANCH};protocol=https"
SRCREV = "d28555e487e4c1278c9a2e94143dc79dcc8941bf"

S = "${WORKDIR}/git/imu_filter_madgwick"


ROS_BUILD_TYPE = "ament_cmake"


inherit ros_${ROS_BUILD_TYPE}
