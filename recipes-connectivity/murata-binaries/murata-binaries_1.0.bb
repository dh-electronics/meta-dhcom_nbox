SUMMARY = "Murata Binaries"
LICENSE = "BSD"

LIC_FILES_CHKSUM = "file://${S}/cyw-bt-patch/LICENCE.cypress;md5=cbc5f665d04f741f1e006d2096236ba7"

SRC_URI = " \
	git://github.com/murata-wireless/cyw-fmac-fw;protocol=http;branch=kong;destsuffix=cyw-fmac-fw;name=cyw-fmac-fw \
	git://github.com/murata-wireless/cyw-fmac-nvram;protocol=http;branch=kong;destsuffix=cyw-fmac-nvram;name=cyw-fmac-nvram \
	git://github.com/murata-wireless/cyw-bt-patch;protocol=http;branch=sumo-kong;destsuffix=cyw-bt-patch;name=cyw-bt-patch \
	git://github.com/murata-wireless/cyw-fmac-utils-imx32;protocol=http;branch=kong;destsuffix=cyw-fmac-utils-imx32;name=cyw-fmac-utils-imx32 \
	git://github.com/murata-wireless/cyw-fmac-utils-imx64;protocol=http;branch=kong;destsuffix=cyw-fmac-utils-imx64;name=cyw-fmac-utils-imx64 \
	file://10-network.rules \
"

SRCREV_cyw-fmac-fw="f837656c958a1fff8e4ef84ff78c29c359e6cdb7"
SRCREV_cyw-fmac-nvram="9abba466839563f0237c5928b95375752596c072"
SRCREV_cyw-bt-patch="558f98ac67bd944afa003c247643fd47cc2dd3ab"
SRCREV_cyw-fmac-utils-imx32="b1cbd9170dd96ac73102aeee5d73b11575e1028a"
SRCREV_cyw-fmac-utils-imx64="6848dc6c805a29ff2a297d99557b8c875342cd57"

SRCREV_default = "${AUTOREV}"

S = "${WORKDIR}"
B = "${WORKDIR}"
DEPENDS = " libnl"

do_compile () {
	echo "Compiling: "
        echo "Testing Make        Display:: ${MAKE}"
        echo "Testing bindir      Display:: ${bindir}"
        echo "Testing base_libdir Display:: ${base_libdir}"
        echo "Testing sysconfdir  Display:: ${sysconfdir}"
        echo "Testing S  Display:: ${S}"
        echo "Testing B  Display:: ${B}"
        echo "Testing D  Display:: ${D}"
        echo "WORK_DIR :: ${WORKDIR}"
        echo "PWD :: "
        pwd
}

PACKAGES_prepend = "murata-binaries-wlarm "
FILES_murata-binaries-wlarm = "${bindir}/wlarm"

DO_INSTALL_64BIT_BINARIES = "no"
DO_INSTALL_64BIT_BINARIES_mx6 = "no"
DO_INSTALL_64BIT_BINARIES_mx7 = "no"
DO_INSTALL_64BIT_BINARIES_mx8 = "yes"

do_install () {
	echo "Installing: "
	install -d ${D}/lib/firmware/brcm
	install -d ${D}/lib/firmware/brcm/murata-master
	install -d ${D}/etc/firmware
	install -d ${D}/etc/firmware/murata-master
	install -d ${D}/usr/sbin
	install -d ${D}/etc/udev/rules.d

#       Copying *.HCD files to etc/firmware and etc/firmware/murata-master
        install -m 444 ${S}/cyw-bt-patch/CYW4335C0.ZP.hcd ${D}${sysconfdir}/firmware/BCM4335C0.ZP.hcd
        install -m 444 ${S}/cyw-bt-patch/BCM4345C0_003.001.025.0144.0266.1MW.hcd ${D}${sysconfdir}/firmware/BCM4345C0_003.001.025.0144.0266.1MW.hcd
        install -m 444 ${S}/cyw-bt-patch/BCM43012C0_003.001.015.0102.0141.1LV.hcd ${D}${sysconfdir}/firmware/BCM43012C0_003.001.015.0102.0141.1LV.hcd
        install -m 444 ${S}/cyw-bt-patch/CYW43341B0.1BW.hcd ${D}${sysconfdir}/firmware/BCM43341B0.1BW.hcd
        install -m 444 ${S}/cyw-bt-patch/BCM4343A1_001.002.009.0093.0395.1DX.hcd ${D}${sysconfdir}/firmware/BCM43430A1_001.002.009.0093.0395.1DX.hcd
        install -m 444 ${S}/cyw-bt-patch/CYW4350C0.1BB.hcd ${D}${sysconfdir}/firmware/BCM4350C0.1BB.hcd
        install -m 444 ${S}/cyw-bt-patch/BCM4356A2_001.003.015.0106.0403.1CX.hcd ${D}${sysconfdir}/firmware/BCM4354A2_001.003.015.0106.0403.1CX.hcd
	install -m 444 ${S}/cyw-bt-patch/README_BT_PATCHFILE ${D}${sysconfdir}/firmware

#	install -m 444 ${D}${sysconfdir}/firmware/*.hcd       ${D}${sysconfdir}/firmware/murata-master
        install -m 444 ${S}/cyw-bt-patch/CYW4335C0.ZP.hcd    ${D}${sysconfdir}/firmware/murata-master/_BCM4335C0.ZP.hcd
        install -m 444 ${S}/cyw-bt-patch/BCM4345C0_003.001.025.0144.0266.1MW.hcd   ${D}${sysconfdir}/firmware/murata-master/_BCM4345C0_003.001.025.0144.0266.1MW.hcd
        install -m 444 ${S}/cyw-bt-patch/BCM43012C0_003.001.015.0102.0141.1LV.hcd  ${D}${sysconfdir}/firmware/murata-master/_BCM43012C0_003.001.015.0102.0141.1LV.hcd
        install -m 444 ${S}/cyw-bt-patch/CYW43341B0.1BW.hcd  ${D}${sysconfdir}/firmware/murata-master/_BCM43341B0.1BW.hcd
        install -m 444 ${S}/cyw-bt-patch/BCM4343A1_001.002.009.0093.0395.1DX.hcd  ${D}${sysconfdir}/firmware/murata-master/_BCM4343A1_001.002.009.0093.0395.1DX.hcd
        install -m 444 ${S}/cyw-bt-patch/CYW4350C0.1BB.hcd   ${D}${sysconfdir}/firmware/murata-master/_BCM4350C0.1BB.hcd
        install -m 444 ${S}/cyw-bt-patch/BCM4356A2_001.003.015.0106.0403.1CX.hcd   ${D}${sysconfdir}/firmware/murata-master/_BCM4356A2_001.003.015.0106.0403.1CX.hcd
	install -m 444 ${S}/cyw-bt-patch/README_BT_PATCHFILE ${D}${sysconfdir}/firmware/murata-master

#       Copying FW and CLM BLOB files (*.bin, *.clm_blob) to lib/firmware/brcm folder
	install -m 444 ${S}/cyw-fmac-fw/*.bin ${D}/lib/firmware/brcm
##	install -m 444 ${S}/cyw-fmac-fw/*.clm_blob ${D}/lib/firmware/brcm


#       Rename clm blob files accordingly
	install -m 444 ${S}/cyw-fmac-fw/brcmfmac4354-sdio.clm_blob ${D}/lib/firmware/brcm/brcmfmac4354-sdio.clm_blob
	install -m 444 ${S}/cyw-fmac-fw/brcmfmac4356-pcie.1CX.clm_blob ${D}/lib/firmware/brcm/brcmfmac4356-pcie.clm_blob
	install -m 444 ${S}/cyw-fmac-fw/brcmfmac43012-sdio.1LV.clm_blob ${D}/lib/firmware/brcm/brcmfmac43012-sdio.clm_blob
	install -m 444 ${S}/cyw-fmac-fw/brcmfmac43430-sdio.1DX.clm_blob ${D}/lib/firmware/brcm/brcmfmac43430-sdio.clm_blob
	install -m 444 ${S}/cyw-fmac-fw/brcmfmac43455-sdio.1MW.clm_blob ${D}/lib/firmware/brcm/brcmfmac43455-sdio.clm_blob
	
#       Copying NVRAM files (*.txt) to lib/firmware/brcm and lib/firmware/brcm/murata-master
	install -m 444 ${S}/cyw-fmac-nvram/*.txt ${D}/lib/firmware/brcm/murata-master
	install -m 444 ${S}/cyw-fmac-nvram/brcmfmac4339-sdio.ZP.txt ${D}/lib/firmware/brcm/brcmfmac4339-sdio.txt
	install -m 444 ${S}/cyw-fmac-nvram/brcmfmac4354-sdio.1BB.txt ${D}/lib/firmware/brcm/brcmfmac4354-sdio.txt
	install -m 444 ${S}/cyw-fmac-nvram/brcmfmac4356-pcie.1CX.txt ${D}/lib/firmware/brcm/brcmfmac4356-pcie.txt
	install -m 444 ${S}/cyw-fmac-nvram/brcmfmac43012-sdio.1LV.txt ${D}/lib/firmware/brcm/brcmfmac43012-sdio.txt
	install -m 444 ${S}/cyw-fmac-nvram/brcmfmac43340-sdio.1BW.txt ${D}/lib/firmware/brcm/brcmfmac43340-sdio.txt
	install -m 444 ${S}/cyw-fmac-nvram/brcmfmac43362-sdio.SN8000.txt ${D}/lib/firmware/brcm/brcmfmac43362-sdio.txt
	install -m 444 ${S}/cyw-fmac-nvram/brcmfmac43430-sdio.1DX.txt ${D}/lib/firmware/brcm/brcmfmac43430-sdio.txt
	install -m 444 ${S}/cyw-fmac-nvram/brcmfmac43455-sdio.1MW.txt ${D}/lib/firmware/brcm/brcmfmac43455-sdio.txt

	install -m 444 ${S}/cyw-fmac-nvram/README_NVRAM.txt ${D}/lib/firmware/brcm
	install -m 444 ${S}/cyw-fmac-fw/README_CLM_BLOB.txt ${D}/lib/firmware/brcm/README_CLM_BLOB.txt
	install -m 444 ${S}/cyw-fmac-fw/README_FW.txt ${D}/lib/firmware/brcm/README_FW.txt


	install -m 444 ${S}/10-network.rules                  ${D}${sysconfdir}/udev/rules.d/10-network.rules

#       Copying wl tool binary to /usr/sbin
	if [ ${DO_INSTALL_64BIT_BINARIES} = "yes" ]; then
		install -m 755 ${S}/cyw-fmac-utils-imx64/wl ${D}/usr/sbin/wl
	else
		install -m 755 ${S}/cyw-fmac-utils-imx32/wl ${D}/usr/sbin/wl
	fi
}

PACKAGES =+ "${PN}-mfgtest"

FILES_${PN} += "/lib/firmware"
FILES_${PN} += "/lib/firmware/*"
FILES_${PN} += "${bindir}"
FILES_${PN} += "${sbindir}"
FILES_${PN} += "{sysconfdir}/firmware"
FILES_${PN} += "/lib"


FILES_${PN}-mfgtest = " \
	/usr/bin/wl \
"

INSANE_SKIP_${PN} += "build-deps"
INSANE_SKIP_${PN} += "file-rdeps"
