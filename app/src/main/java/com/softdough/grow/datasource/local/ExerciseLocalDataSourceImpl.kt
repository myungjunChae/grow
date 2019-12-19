package com.softdough.grow.datasource.local

import com.softdough.grow.data.datasource.ExerciseLocalDataSource
import com.softdough.grow.datasource.remote.ExerciseApi
import com.softdough.grow.domain.model.Exercise
import io.reactivex.Single

class ExerciseLocalDataSourceImpl(private val pref : SharedPreference) : ExerciseLocalDataSource{
    override fun getExercise(rouitneId: Long): Single<List<Exercise>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getExerciseAll(): Single<List<Exercise>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setExerciseAll(): Single<List<Exercise>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}