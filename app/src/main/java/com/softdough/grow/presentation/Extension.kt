package com.softdough.grow.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.softdough.grow.domain.usecase.AuthorizeUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

fun Activity.bindColor(@ColorRes res: Int): Lazy<Int> = lazy {
    ContextCompat.getColor(this, res)
}

fun Activity.loadImage(url: String, imageView: ImageView) = lazy {
    Glide.with(this)
        .load(url)
        .into(imageView)
}

fun Fragment.loadImage(url: String, imageView: ImageView) = lazy {
    Glide.with(this)
        .load(url)
        .into(imageView)
}

inline fun <reified T : Activity> Activity.startActivity(bundle: Bundle? = null) {
    startActivity(Intent(this, T::class.java), bundle)
}


inline fun <reified T : Activity> Activity.startActivityWithFinish(bundle: Bundle? = null) {
    startActivity<T>(bundle)
    finish()
}

inline fun <reified T : Activity> Fragment.startActivity(bundle: Bundle? = null) {
    var intent = Intent(context, T::class.java)
    bundle?.run { intent.putExtras(this) }

    startActivity(intent)
}

fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }

