#
# Copyright (C) 2018 The Xiaomi-SDM660 Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

#
# This file sets variables that control the way modules are built
# thorughout the system. It should not be used to conditionally
# disable makefiles (the proper mechanism to control what gets
# included in a build is to use PRODUCT_PACKAGES in a product
# definition file).
#

# Inherit device configuration
$(call inherit-product, device/xiaomi/clover/device.mk)

# Inherit some common aosp stuff
$(call inherit-product, vendor/aosp/config/common_full_phone.mk)

# Device identifier. This must come after all inclusions.
PRODUCT_NAME := aosp_clover
PRODUCT_DEVICE := clover
PRODUCT_BRAND := Xiaomi
PRODUCT_MODEL := Mi PAD 4
PRODUCT_MANUFACTURER := Xiaomi
PRODUCT_CHARACTERISTICS := tablet

PRODUCT_SYSTEM_NAME := clover

BUILD_FINGERPRINT := "Xiaomi/clover/clover:8.1.0/OPM1.171019.019/V9.6.4.0.ODJCNFD:user/release-keys"

PRODUCT_BUILD_PROP_OVERRIDES += \
    PRIVATE_BUILD_DESC="clover-user 8.1.0 OPM1.171019.019 V9.6.4.0.ODJCNFD release-keys" \
    TARGET_PRODUCT="clover"

PRODUCT_GMS_CLIENTID_BASE := android-xiaomi

TARGET_SUPPORTS_GOOGLE_RECORDER := true
TARGET_BOOT_ANIMATION_RES := 1440