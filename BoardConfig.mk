#
# Copyright (C) 2018 The LineageOS Project
#
# SPDX-License-Identifier: Apache-2.0
#

DEVICE_PATH := device/xiaomi/clover

# Architecture
TARGET_ARCH := arm64
TARGET_ARCH_VARIANT := armv8-a
TARGET_CPU_ABI := arm64-v8a
TARGET_CPU_ABI2 :=
TARGET_CPU_VARIANT := cortex-a73

TARGET_2ND_ARCH := arm
TARGET_2ND_ARCH_VARIANT := armv8-a
TARGET_2ND_CPU_ABI := armeabi-v7a
TARGET_2ND_CPU_ABI2 := armeabi
TARGET_2ND_CPU_VARIANT := cortex-a73

# Audio
AUDIO_DISABLE_SWAP_CHANNELS := true
AUDIO_FEATURE_ENABLED_COMPRESS_VOIP := true
AUDIO_FEATURE_ENABLED_EXT_AMPLIFIER := false
AUDIO_FEATURE_ENABLED_GEF_SUPPORT := true
AUDIO_FEATURE_ENABLED_SVA_MULTI_STAGE := true
BOARD_USES_ALSA_AUDIO := true
BOARD_SUPPORTS_SOUND_TRIGGER := true
USE_CUSTOM_AUDIO_POLICY := 1

# Bluetooth
BOARD_HAVE_BLUETOOTH := true
BOARD_HAVE_BLUETOOTH_QCOM := true
BOARD_HAS_QCA_BT_SOC := "cherokee"
BLUETOOTH_HCI_USE_MCT := true
QCOM_BT_USE_SMD_TTY := true

# Board
TARGET_BOARD_PLATFORM := sdm660

# Bootloader
TARGET_BOOTLOADER_BOARD_NAME := sdm660
TARGET_NO_BOOTLOADER := true

# Build Rules
BUILD_BROKEN_DUP_RULES := true
BUILD_BROKEN_ELF_PREBUILT_PRODUCT_COPY_FILES := true

# Camera
TARGET_TS_MAKEUP := true
TARGET_USES_QTI_CAMERA_DEVICE := true
BOARD_QTI_CAMERA_32BIT_ONLY := true

# ConfigFS
TARGET_FS_CONFIG_GEN := $(DEVICE_PATH)/configs/config.fs

# Density
TARGET_SCREEN_DENSITY := 320

# Display
BOARD_USES_ADRENO := true
TARGET_USES_QTI_MAPPER_2_0 := true
TARGET_USES_QTI_MAPPER_EXTENSIONS_1_1 := true
TARGET_USES_GRALLOC4 := true
TARGET_USES_HWC2 := true
TARGET_USES_GRALLOC1 := true
TARGET_USES_ION := true
NUM_FRAMEBUFFER_SURFACE_BUFFERS := 3

SOONG_CONFIG_NAMESPACES += XIAOMI_BACKLIGHT
SOONG_CONFIG_XIAOMI_BACKLIGHT := USE_LCD_BACKLIGHT_INTERFACE_ONLY
SOONG_CONFIG_XIAOMI_BACKLIGHT_USE_LCD_BACKLIGHT_INTERFACE_ONLY := true

# DT2W
TARGET_TAP_TO_WAKE_NODE := "/proc/touchscreen/enable_dt2w"

# GPS
BOARD_VENDOR_QCOM_GPS_LOC_API_HARDWARE := default
LOC_HIDL_VERSION := 4.0

# HALS
USE_DEVICE_SPECIFIC_AUDIO := true
DEVICE_SPECIFIC_AUDIO_PATH := $(DEVICE_PATH)/oss-hals/audio
USE_DEVICE_SPECIFIC_DISPLAY := true
DEVICE_SPECIFIC_DISPLAY_PATH := $(DEVICE_PATH)/oss-hals/display
USE_DEVICE_SPECIFIC_MEDIA := true
DEVICE_SPECIFIC_MEDIA_PATH := $(DEVICE_PATH)/oss-hals/media
USE_DEVICE_SPECIFIC_CAMERA := true
DEVICE_SPECIFIC_MEDIA_PATH := $(DEVICE_PATH)/oss-hals/camera

# HIDL
DEVICE_FRAMEWORK_MANIFEST_FILE := $(DEVICE_PATH)/configs/vintf/framework_manifest.xml
DEVICE_MANIFEST_FILE := $(DEVICE_PATH)/configs/vintf/manifest.xml
DEVICE_MATRIX_FILE := $(DEVICE_PATH)/configs/vintf/compatibility_matrix.xml

# HWUI
HWUI_COMPILE_FOR_PERF := true

# Init
TARGET_INIT_VENDOR_LIB := //$(DEVICE_PATH):libinit_sdm660
TARGET_PLATFORM_DEVICE_BASE := /devices/soc/
TARGET_RECOVERY_DEVICE_MODULES := libinit_sdm660

# Kernel
BOARD_KERNEL_CMDLINE := \
    androidboot.hardware=qcom \
    user_debug=31 \
    msm_rtb.filter=0x37 \
    ehci-hcd.park=3 \
    lpm_levels.sleep_disabled=1 \
    service_locator.enable=1 \
    androidboot.configfs=true \
    androidboot.usbcontroller=a800000.dwc3 \
    loop.max_part=7 \
    printk.devkmsg=on \
    usbcore.autosuspend=7 \
    kpti=off \
    androidboot.boot_devices=soc/c0c4000.sdhci

BOARD_KERNEL_BASE := 0x00000000
BOARD_KERNEL_PAGESIZE := 4096
BOARD_KERNEL_IMAGE_NAME := Image.gz-dtb
TARGET_KERNEL_VERSION := 4.19
TARGET_KERNEL_SOURCE := kernel/xiaomi/clover
TARGET_KERNEL_CONFIG := clover_defconfig
TARGET_COMPILE_WITH_MSM_KERNEL := true

# Partitions
BOARD_FLASH_BLOCK_SIZE := 262144
BOARD_BOOTIMAGE_PARTITION_SIZE := 67108864
BOARD_CACHEIMAGE_FILE_SYSTEM_TYPE := f2fs
BOARD_CACHEIMAGE_PARTITION_SIZE := 268435456
BOARD_RECOVERYIMAGE_PARTITION_SIZE := 67108864
BOARD_SYSTEMIMAGE_PARTITION_TYPE := erofs
# BOARD_SYSTEMIMAGE_PARTITION_SIZE := 3221225472
# BOARD_VENDORIMAGE_PARTITION_SIZE := 2147483648
BOARD_VENDORIMAGE_FILE_SYSTEM_TYPE := erofs
TARGET_USERIMAGES_USE_EXT4 := true
TARGET_USERIMAGES_USE_F2FS := true

BOARD_SUPER_PARTITION_GROUPS := xiaomi660_dynamic_partitions
BOARD_XIAOMI660_DYNAMIC_PARTITIONS_PARTITION_LIST := system vendor
BOARD_SUPER_PARTITION_SIZE := 5368709120
BOARD_SUPER_PARTITION_METADATA_DEVICE := system
BOARD_SUPER_PARTITION_BLOCK_DEVICES := system vendor
BOARD_SUPER_PARTITION_SYSTEM_DEVICE_SIZE := 3221225472
BOARD_SUPER_PARTITION_VENDOR_DEVICE_SIZE := 2147483648
# 4 MiB metadata size
BOARD_XIAOMI660_DYNAMIC_PARTITIONS_SIZE := 5364514816
BOARD_EROFS_PCLUSTER_SIZE := 262144
BOARD_EROFS_COMPRESSOR := lz4

BOARD_USES_METADATA_PARTITION := true
TARGET_COPY_OUT_VENDOR := vendor
BOARD_ROOT_EXTRA_SYMLINKS := \
    /vendor/dsp:/dsp \
    /vendor/firmware_mnt:/firmware \
    /vendor/bt_firmware:/bt_firmware \
    /mnt/vendor/persist:/persist

# Properties
TARGET_ODM_PROP += $(DEVICE_PATH)/configs/properties/odm.prop
TARGET_SYSTEM_EXT_PROP += $(DEVICE_PATH)/configs/properties/system_ext.prop
TARGET_SYSTEM_PROP += $(DEVICE_PATH)/configs/properties/system.prop
TARGET_VENDOR_PROP += $(DEVICE_PATH)/configs/properties/vendor.prop

# QCOM hardware
BOARD_USES_QCOM_HARDWARE := true
TARGET_USES_QCOM_BSP := false

# Recovery
TARGET_RECOVERY_FSTAB := $(DEVICE_PATH)/rootdir/etc/fstab.qcom

# Releasetools
TARGET_RELEASETOOLS_EXTENSIONS := $(DEVICE_PATH)

# RIL
ENABLE_VENDOR_RIL_SERVICE := true

# SELinux
include device/qcom/sepolicy-legacy-um/SEPolicy.mk
BOARD_VENDOR_SEPOLICY_DIRS += $(DEVICE_PATH)/sepolicy/vendor
SYSTEM_EXT_PUBLIC_SEPOLICY_DIRS += $(DEVICE_PATH)/sepolicy/public
SYSTEM_EXT_PRIVATE_SEPOLICY_DIRS += $(DEVICE_PATH)/sepolicy/private

# Vendor Security patch level
VENDOR_SECURITY_PATCH := $(PLATFORM_SECURITY_PATCH)

# VNDK
BOARD_VNDK_VERSION := current

# Wifi
BOARD_WLAN_DEVICE := qcwcn
BOARD_HOSTAPD_DRIVER := NL80211
BOARD_HOSTAPD_PRIVATE_LIB := lib_driver_cmd_$(BOARD_WLAN_DEVICE)
BOARD_WPA_SUPPLICANT_DRIVER := NL80211
BOARD_WPA_SUPPLICANT_PRIVATE_LIB := lib_driver_cmd_$(BOARD_WLAN_DEVICE)
QC_WIFI_HIDL_FEATURE_DUAL_AP := true
TARGET_HAS_BROKEN_WLAN_SET_INTERFACE := true
WIFI_DRIVER_DEFAULT := qca_cld3
WIFI_DRIVER_STATE_CTRL_PARAM := "/dev/wlan"
WIFI_DRIVER_STATE_OFF := "OFF"
WIFI_DRIVER_STATE_ON := "ON"
WIFI_HIDL_FEATURE_DUAL_INTERFACE := true
WIFI_HIDL_FEATURE_AWARE := true
WIFI_HIDL_UNIFIED_SUPPLICANT_SERVICE_RC_ENTRY := true
WPA_SUPPLICANT_VERSION := VER_0_8_X

# Inherit the proprietary files
-include vendor/xiaomi/clover/BoardConfigVendor.mk
