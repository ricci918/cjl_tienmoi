package com.tienmoi.vayonline.nhanh.model.data

import java.util.Objects

data class TienAreaListData(
    val lCTsCt4: String,
    val f3SSikB: MutableList<TienAreaList>,
    val QCxS882: String,
)

class TienAreaList(
    val children: Objects,
    val name: String,
    val value: String
)
