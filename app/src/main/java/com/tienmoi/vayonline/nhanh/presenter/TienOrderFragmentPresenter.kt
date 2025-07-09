package com.tienmoi.vayonline.nhanh.presenter

import com.tienmoi.vayonline.nhanh.base.TienBasePresenter
import com.tienmoi.vayonline.nhanh.model.api.TienApiServiceObject
import com.tienmoi.vayonline.nhanh.model.contract.TienOrderFragmentContract
import com.tienmoi.vayonline.nhanh.model.data.TienAcquisitionReq
import com.tienmoi.vayonline.nhanh.model.data.TienOrderCreateReq
import com.tienmoi.vayonline.nhanh.model.data.TienRenewReq
import com.tienmoi.vayonline.nhanh.model.data.TienWithdrawReq
import com.tienmoi.vayonline.nhanh.model.utils.TienToastUtil
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TienOrderFragmentPresenter :
    TienBasePresenter<TienOrderFragmentContract.TienOrderFragmentView>(),
    TienOrderFragmentContract.TienOrderFragmentPresenter {
    @OptIn(DelicateCoroutinesApi::class)
    override fun getTienOrder() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tienOrder = TienApiServiceObject.tienOrder()
                withContext(Dispatchers.Main) {
                    mView?.successTienOrder(
                        tienOrder
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
    override fun getTienRepayment() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tienRepayment = TienApiServiceObject.tienRepayment()
                withContext(Dispatchers.Main) {
                    mView?.successTienRepayment(
                        tienRepayment
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
    override fun getTienRepaymentAccomplish() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tienRepaymentAccomplish = TienApiServiceObject.tienRepaymentAccomplish()
                withContext(Dispatchers.Main) {
                    mView?.successTienRepaymentAccomplish(
                        tienRepaymentAccomplish
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
    override fun getTienCheck() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tienCheck = TienApiServiceObject.tienCheck()
                withContext(Dispatchers.Main) {
                    mView?.successTienCheck(
                        tienCheck
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
    override fun getTienWithdraw(data: TienWithdrawReq) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tienWithdraw = TienApiServiceObject.tienWithdraw(data)
                withContext(Dispatchers.Main) {
                    mView?.successTienWithdraw(
                        tienWithdraw
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
    override fun getTienRenew(data: TienRenewReq) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tienRenew = TienApiServiceObject.tienRenew(data)
                withContext(Dispatchers.Main) {
                    mView?.successTienRenew(
                        tienRenew
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