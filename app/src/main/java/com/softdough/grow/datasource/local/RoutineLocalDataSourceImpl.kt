package com.softdough.grow.datasource.local

import com.softdough.grow.data.datasource.RoutineLocalDataSource
import com.softdough.grow.domain.model.Routine
import io.reactivex.rxjava3.core.Single

class RoutineLocalDataSourceImpl(private val sharedPreference: SharedPreference) : RoutineLocalDataSource{
    override fun get(categoryId: Long): Single<List<Routine>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun set(routines: List<Routine>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}