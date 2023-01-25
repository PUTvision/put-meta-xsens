sleep 30

export ROS_DOMAIN_ID=21
export AMENT_PREFIX_PATH=/usr
export ROS_OS_OVERRIDE=openembedded
export ROS_DISTRO=foxy
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:/usr/lib/"
export PKG_CONFIG_PATH="/usr/lib/pkgconfig:/usr/share/pkgconfig"

source /usr/bin/ros_setup.bash

ros2 launch bluespace_ai_xsens_mti_driver xsens_mti_node.launch.py
