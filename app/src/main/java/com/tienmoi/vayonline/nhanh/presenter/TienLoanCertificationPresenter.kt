package com.tienmoi.vayonline.nhanh.presenter

import com.tienmoi.vayonline.nhanh.base.TienBasePresenter
import com.tienmoi.vayonline.nhanh.model.api.TienApiServiceObject
import com.tienmoi.vayonline.nhanh.model.contract.TienLoanConfirmationContract
import com.tienmoi.vayonline.nhanh.model.data.TienAcquisitionReq
import com.tienmoi.vayonline.nhanh.model.data.TienOrderCreateReq
import com.tienmoi.vayonline.nhanh.model.utils.TienToastUtil
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TienLoanCertificationPresenter :
    TienBasePresenter<TienLoanConfirmationContract.TienLoanConfirmationView>(),
    TienLoanConfirmationContract.TienLoanConfirmationPresenter {

    @OptIn(DelicateCoroutinesApi::class)
    override fun getTienInfo() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tienInfo = TienApiServiceObject.tienInfo()
                withContext(Dispatchers.Main) {
                    mView?.successTienInfo(
                        tienInfo
                    )
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    if (e.message != "" && e.message != null) {
                        TienToastUtil.myToast(e.message!!)
                    }
                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun getTienAddVayHub() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tienAddVayHub = TienApiServiceObject.tienAddVayHub()
                withContext(Dispatchers.Main) {
                    mView?.successTienAddVayHub(
                        tienAddVayHub
                    )
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    if (e.message != "" && e.message != null) {
                        mView?.error()
                    }
                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun getTienAcquisition(data: TienAcquisitionReq) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tienAcquisition = TienApiServiceObject.tienAcquisition(data)
                withContext(Dispatchers.Main) {
                    mView?.successTienAcquisition(
                        tienAcquisition
                    )
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    if (e.message != "" && e.message != null) {
                        mView?.error()
                    }
                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun getTienCreate(data: TienOrderCreateReq) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tienCreate = TienApiServiceObject.tienCreate(data)
                withContext(Dispatchers.Main) {
                    mView?.successTienCreate(
                        tienCreate
                    )
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    if (e.message != "" && e.message != null) {
                        mView?.error()
                    }
                }
            }
        }
    }
}