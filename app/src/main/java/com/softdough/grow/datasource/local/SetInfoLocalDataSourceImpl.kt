package com.softdough.grow.datasource.local

import com.softdough.grow.data.datasource.SetInfoLocalDataSource
import com.softdough.grow.domain.model.SetInfo
import io.reactivex.Single

class SetInfoLocalDataSourceImpl(private val pref : SharedPreference) : SetInfoLocalDataSource{
    override fun setSetInfo() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSetInfo(routineId: Long, exerciseId: Long): Single<List<SetInfo>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}