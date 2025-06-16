package com.tienmoi.vayonline.nhanh.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap.CompressFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.nanchen.compresshelper.CompressHelper
import com.tienmoi.vayonline.nhanh.R
import com.tienmoi.vayonline.nhanh.base.TienBaseActivity
import com.tienmoi.vayonline.nhanh.databinding.ActivityCertificationThreeBinding
import com.tienmoi.vayonline.nhanh.model.contract.TienCertificationThreeContract
import com.tienmoi.vayonline.nhanh.model.data.TienAcquisitionReq
import com.tienmoi.vayonline.nhanh.model.data.TienAcquisitionReq.TienUserDeviceInfo
import com.tienmoi.vayonline.nhanh.model.data.TienAddBankInfoReq
import com.tienmoi.vayonline.nhanh.model.data.TienBankListData
import com.tienmoi.vayonline.nhanh.model.data.TienInfoData
import com.tienmoi.vayonline.nhanh.model.data.TienLicenseSuccessData
import com.tienmoi.vayonline.nhanh.model.data.TienOrderCreateReq
import com.tienmoi.vayonline.nhanh.model.data.TienResultReq
import com.tienmoi.vayonline.nhanh.model.utils.TienDeviceInfoUtils
import com.tienmoi.vayonline.nhanh.model.utils.TienGsonUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienSharedPreferencesUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienSystemUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienToastUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienUserApplicationsUtils
import com.tienmoi.vayonline.nhanh.presenter.TienCertificationThreePresenter
import com.tienmoi.vayonline.nhanh.ui.dialog.TienKycDialog
import com.trustdecision.mobrisk.TDRisk
import com.trustdecision.mobrisk.TDRiskLivenessCallback
import com.yanzhenjie.permission.AndPermission
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileNotFoundException

class TienCertificationThreeActivity :
    TienBaseActivity<TienCertificationThreeContract.TienCertificationThreeView, TienCertificationThreePresenter>(),
    TienCertificationThreeContract.TienCertificationThreeView {
    private lateinit var mBinding: ActivityCertificationThreeBinding
    private var tienBankListData: MutableList<TienBankListData>? = null
    private var bankList: TienBankListData? = null
    private var imageUri: Uri? = null
    private var needReq = false
    private var imageOneName = "imageOne.jpeg"
    private var imageTwoName = "imageTwo.jpeg"
    private var imageOneNumber: Double = 0.0
    private var imageTwoNumber: Double = 0.0
    private var imageThreeNumber: Double = 0.0
    private var tester: Boolean? = null
    private var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityCertificationThreeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun initLayout(): View = mBinding.root

    override fun initView() {
        mBinding.apply {
            isPermission()
            ll3Id.setOnClickListener {
                tienBankListData?.let { it1 ->
                    TienKycDialog.showTienBankDialog(
                        this@TienCertificationThreeActivity, tv3Id.text.toString(),
                        it1
                    ) {
                        bankList = it
                        et2Id.text = it.g3jMqJO
                    }
                }
            }

            iv2Id.setOnClickListener {
                if (TienSystemUtil.isFastClick(800)) {
                    return@setOnClickListener
                }
                if (!needReq) {
                    TienKycDialog.showTienCameraDialog(this@TienCertificationThreeActivity) {
                        AndPermission.with(this@TienCertificationThreeActivity)
                            .permission(Manifest.permission.CAMERA)
                            .onGranted {
                                pictureIntent(1, imageOneName)
                                needReq = true
                            }
                            .onDenied {

                            }
                            .start()
                    }
                } else {
                    pictureIntent(1, imageOneName)
                }

            }

            iv4Id.setOnClickListener {
                if (TienSystemUtil.isFastClick(800)) {
                    return@setOnClickListener
                }
                if (!needReq) {
                    TienKycDialog.showTienCameraDialog(this@TienCertificationThreeActivity) {
                        AndPermission.with(this@TienCertificationThreeActivity)
                            .permission(Manifest.permission.CAMERA)
                            .onGranted {
                                pictureIntent(2, imageTwoName)
                                needReq = true
                            }
                            .onDenied {

                            }
                            .start()
                    }
                } else {
                    pictureIntent(2, imageTwoName)
                }

            }

            iv6Id.setOnClickListener {
                if (TienSystemUtil.isFastClick(800)) {
                    return@setOnClickListener
                }
                if (!needReq) {
                    TienKycDialog.showTienCameraDialog(this@TienCertificationThreeActivity) {
                        AndPermission.with(this@TienCertificationThreeActivity)
                            .permission(Manifest.permission.CAMERA)
                            .onGranted {
                                licence()
                                needReq = true
                            }
                            .onDenied {

                            }
                            .start()
                    }
                } else {
                    licence()
                }
            }
            tvNextStep.setOnClickListener {
                if (TienSystemUtil.isFastClick(800)) {
                    return@setOnClickListener
                }
                if (et3Id.text.toString() != "" && et2Id.text.toString() != "" && imageOneNumber != 0.0 && imageTwoNumber != 0.0 && imageThreeNumber != 0.0) {
                    if (TienSharedPreferencesUtil.getSystemInfoData()?.XKruWxQ == true) {
                        if (TienSharedPreferencesUtil.getSystemInfoData()?.hGkzoMf == true) {
                            showLoadingOne()
                        } else {
                            showLoading()
                        }
                    } else {
                        showLoading()
                    }
                    presenter?.getTienAddBankInfo(
                        TienAddBankInfoReq(
                            et3Id.text.toString(),
                            et2Id.text.toString(),
                            null,
                            null,
                            imageOneNumber.toInt().toString(),
                            imageTwoNumber.toInt().toString(),
                            imageThreeNumber.toInt().toString(),
                            false
                        )
                    )
                } else {
                    TienToastUtil.myToast(getString(R.string.certificationOne17))
                }

            }
            tvReturn.setOnClickListener {
                finish()
            }

        }
    }

    private fun isPermission() {
        needReq = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun pictureIntent(action: Int, child: String) {
        try {
            val imageTemp = File(externalCacheDir, child)
            if (imageTemp.exists()) {
                imageTemp.delete()
            }
            imageTemp.createNewFile()
            imageUri = Uri.fromFile(imageTemp)
            if (Build.VERSION.SDK_INT > 24) {
                imageUri = FileProvider.getUriForFile(
                    this,
                    "com.tienmoi.vayonline.nhanh.FileProvider",
                    imageTemp
                )
            }
            val intent = Intent()
            intent.setAction("android.media.action.IMAGE_CAPTURE")
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            startActivityForResult(intent, action)
        } catch (_: Exception) {
        }
    }

    override fun initData() {
        presenter?.getTienInfo()
        presenter?.getTienBankList()
    }

    override fun initPresenter(): TienCertificationThreePresenter =
        TienCertificationThreePresenter()

    override fun successTienBankList(data: MutableList<TienBankListData>) {
        tienBankListData = data
    }

    override fun successTienAddBankInfo(data: Any) {
        if (TienSharedPreferencesUtil.getSystemInfoData()?.XKruWxQ == true) {
            if (TienSharedPreferencesUtil.getSystemInfoData()?.hGkzoMf == true) {
                presenter?.getTienAddVayHub()
            } else {
                dismissLoading()
                startActivity(TienLoanConfirmationActivity::class.java)
                finishAffinity()
            }
        } else {
            dismissLoading()
            val intent = Intent(this, TienOperatorSelectActivity::class.java)
            intent.putExtra(TienSystemUtil.IS_AUTH, true)
            startActivity(intent)
            finishAffinity()
        }

    }

    override fun successTienInfo(data: TienInfoData) {
        mBinding.et1Id.text = data.QaA0CmZ
        mBinding.et3Id.setText(data.JtMhKBI.bR76Dxu)
        mBinding.et2Id.text = data.JtMhKBI.GS4VVmR
        tester = data.WcrIiHm
    }

    override fun successTienResult(data: Any) {
        if (data != "") {
            imageThreeNumber = data as Double
            mBinding.iv6Id.setImageResource(R.mipmap.certification10)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun successTienAddVayHub(data: Any) {
        if (tester == false) {
            GlobalScope.launch {
                presenter?.getTienAcquisition(
                    TienAcquisitionReq(
                        TienUserDeviceInfo(
                            TienDeviceInfoUtils.getTienDeviceInfo(this@TienCertificationThreeActivity)
                        ),
                        TienUserApplicationsUtils.getTienUserApplications(this@TienCertificationThreeActivity)
                    )
                )
            }
        }
    }

    override fun successTienAcquisition(data: Any) {
        presenter?.getTienCreate(TienOrderCreateReq())
    }

    override fun successTienCreate(data: Any) {
        dismissLoading()
        val intent = Intent(this, TienMainActivity::class.java)
        intent.putExtra(TienSystemUtil.GOOD, TienSystemUtil.GOOD)
        startActivity(intent)
        finishAffinity()
    }

    override fun error() {
        if (TienSharedPreferencesUtil.getSystemInfoData()?.XKruWxQ == true) {
            if (TienSharedPreferencesUtil.getSystemInfoData()?.hGkzoMf == true) {
                dismissLoadingOne()
            } else {
                dismissLoading()
            }
        } else {
            dismissLoading()
        }
    }

    override fun successTienIdentity(data: Any) {
        if (data != "") {
            dismissLoading()
            when (i) {
                1 -> {
                    imageOneNumber = data as Double
                    mBinding.iv2Id.setImageResource(R.mipmap.certification10)
                }

                2 -> {
                    imageTwoNumber = data as Double
                    mBinding.iv4Id.setImageResource(R.mipmap.certification10)
                }
            }
        }
    }

    private fun licence() {
        val licence: String = TienSharedPreferencesUtil.getTienLicence()
        TDRisk.showLiveness(licence, object : TDRiskLivenessCallback {
            override fun onSuccess(result: String) {
                val licenseSuccessData =
                    TienGsonUtil.fromJson(result, TienLicenseSuccessData::class.java)
                presenter?.getTienResult(TienResultReq(licenseSuccessData?.liveness_id ?: ""))
            }

            override fun onError(errorCode: String, errorMsg: String, sequenceId: String) {
            }
        })
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            try {
                val convertPathToFile =
                    isPathToFile("$externalCacheDir/$imageOneName")
                val newFile = CompressHelper.Builder(this)
                    .setMaxWidth(1920f)
                    .setMaxHeight(1080f)
                    .setQuality(95)
                    .setCompressFormat(CompressFormat.JPEG)
                    .build()
                    .compressToFile(convertPathToFile)
                i = 1
                showLoading()
                presenter?.getTienIdentity(newFile)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }

        } else if (
            requestCode == 2 && resultCode == RESULT_OK
        ) {
            try {
                val convertPathToFile =
                    isPathToFile("$externalCacheDir/$imageTwoName")
                val newFile = CompressHelper.Builder(this)
                    .setMaxWidth(1920f)
                    .setMaxHeight(1080f)
                    .setQuality(95)
                    .setCompressFormat(CompressFormat.JPEG)
                    .build()
                    .compressToFile(convertPathToFile)
                i = 2
                showLoading()
                presenter?.getTienIdentity(newFile)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
    }

    private fun isPathToFile(path: String): File? {
        val file = File(path)
        // 可选：检查文件是否存在
        if (file.exists()) {
            return file
        } else {
            // 处理文件不存在的情况，例如记录日志或抛出异常
            println("File does not exist: $path")
            return null
        }
    }

}