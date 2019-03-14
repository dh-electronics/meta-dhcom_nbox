# Copyright (C) 2013, 2014 Freescale Semiconductor
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Linux Kernel provided and supported by Freescale"
DESCRIPTION = "Linux Kernel provided and supported by Freescale with focus on \
i.MX Family Reference Boards. It includes support for many IPs such as GPU, VPU and IPU."

require recipes-kernel/linux/linux-imx6-dhcom.inc

DEPENDS += "lzop-native bc-native"

# select branch 
SRCBRANCH = "release/nBox/4.19.25_dhsom/20190306"

# use latest source
SRCREV = "${AUTOREV}"

LOCALVERSION = "-release20190306"

#SRC_URI += "file://0001-.....patch"

COMPATIBLE_MACHINE = "(mx6)"
BB_STRICT_CHECKSUM = "0"


