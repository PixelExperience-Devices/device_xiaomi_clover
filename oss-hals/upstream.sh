# Get branch
echo "Enter clo branch/tag: "
echo ""
read clobranch
echo ""

# Update tree/subtree
git subtree pull --prefix=oss-hals/audio https://git.codelinaro.org/clo/la/platform/hardware/qcom/audio $clobranch
git subtree pull --prefix=oss-hals/media https://git.codelinaro.org/clo/la/platform/hardware/qcom/media $clobranch
git subtree pull --prefix=oss-hals/camera https://git.codelinaro.org/clo/la/platform/hardware/qcom/camera $clobranch
git subtree pull --prefix=oss-hals/display https://git.codelinaro.org/clo/la/platform/hardware/qcom/display $clobranch
git subtree pull --prefix=oss-hals/display/display-commonsys-intf https://git.codelinaro.org/clo/la/platform/vendor/qcom-opensource/display-commonsys-intf $clobranch
git subtree pull --prefix=oss-hals/display/display-commonsys https://git.codelinaro.org/clo/la/platform/vendor/qcom-opensource/display-commonsys $clobranch
