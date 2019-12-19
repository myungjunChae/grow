package com.softdough.grow.presentation.ui.Routine.recommend

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.softdough.grow.R
import com.softdough.grow.databinding.ViewRoutineBinding
import com.softdough.grow.presentation.ui.Routine.RoutineCollectionPagerAdapter
import com.softdough.grow.presentation.ui.Routine.RoutineFragment
import com.softdough.grow.util.ScreenUtil
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecommendFragment : RoutineFragment<RecommendViewModel>() {
    override val vm: RecommendViewModel by viewModel()
    private val CATEGROY_TYPE = "RECOMMEND"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //뷰모델에 데이터가 없을 경우
        if (!vm.saveState) {
            vm.saveState = true

            //구독
            vm.liveCategories.observe(this, Observer {
                renderCategories()
                renderAfterLoaded(it.size!! > 0)
            })

            vm.liveCategoryIdRoutineBindings.observe(this, Observer {
                renderRoutines(vm.currentPage.value!!)
            })

            vm.currentPage.observe(this, Observer { changeCurrentPage() })

            //데이터 로드
            vm.getCategory(CATEGROY_TYPE, false)
        }
        //뷰모델에 데이터가 있을 경우
        else {
            renderCategories()
            renderRoutines(vm.currentPage.value!!)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding.floatingButton.visibility = View.GONE
        binding.loadingPanel.visibility = View.VISIBLE

        return binding.root
    }
}