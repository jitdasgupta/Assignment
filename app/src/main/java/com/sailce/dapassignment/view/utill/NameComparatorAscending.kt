package com.sailce.dapassignment.view.utill

import com.sailce.dapassignment.db.UserEntity

internal class NameComparatorAscending : Comparator<UserEntity?> { //PGListingBean
    override fun compare(m1: UserEntity?, m2: UserEntity?): Int {
        return m1?.name!!.compareTo(m2?.name!!)
    }
}