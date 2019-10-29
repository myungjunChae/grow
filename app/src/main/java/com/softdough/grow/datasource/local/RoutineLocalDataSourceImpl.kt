package com.softdough.grow.datasource.local

import com.google.gson.reflect.TypeToken
import com.softdough.grow.data.datasource.RoutineLocalDataSource
import com.softdough.grow.domain.model.Category
import com.softdough.grow.domain.model.Routine
import io.reactivex.Single

class RoutineLocalDataSourceImpl(private val pref: SharedPreference) : RoutineLocalDataSource{
    //TODO pref key 생각해보기
    override fun get(categoryId: Long): Single<List<Routine>> {
        val classType = object : TypeToken<List<Routine>>() {}.type
        return Single.just(pref.getValue(categoryId, classType) as List<Routine>)
    }

    override fun set(categoryId: Long, routines: List<Routine>) : Single<List<Routine>> {
        return Single.just(pref.setValue(categoryId, routines))
    }

}