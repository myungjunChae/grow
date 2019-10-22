package com.softdough.grow.presentation.routine

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.softdough.grow.R
import com.softdough.grow.databinding.FragmentRoutineBinding
import com.softdough.grow.databinding.ViewRoutineBinding
import com.softdough.grow.databinding.ViewRoutineCollectionBinding
import com.softdough.grow.presentation.Base.BaseFragment
import com.softdough.grow.presentation.model.RoutineCollectionItem
import com.softdough.grow.presentation.model.RoutineItem
import com.softdough.grow.util.ScreenUtil


abstract class RoutineFragment<T_VIEW_MODEL : ViewModel> : BaseFragment<FragmentRoutineBinding, T_VIEW_MODEL>() {

    override val resourceId: Int = R.layout.fragment_routine

    private var collectionData: List<RoutineCollectionItem> = listOf(
        RoutineCollectionItem(R.drawable.routine_collection_image_2, "title1"),
        RoutineCollectionItem(R.drawable.routine_collection_image_2, "title2")
    )

    private var routineData: List<RoutineItem> = listOf(
        RoutineItem(R.drawable.routine_collection_image_2, "routine1"),
        RoutineItem(R.drawable.routine_collection_image_2, "routine2"),
        RoutineItem(R.drawable.routine_collection_image_2, "routine3"),
        RoutineItem(R.drawable.routine_collection_image_2, "routine4")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding.routineCollectionViewPager.apply {
            adapter = RoutineCollectionPagerAdapter(collectionData, context)

            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(position: Int,
                                            positionOffset: Float,
                                            positionOffsetPixels: Int
                ) {}

                override fun onPageSelected(position: Int) {}

                override fun onPageScrollStateChanged(state: Int) {
                    //indicator update
                    if (state == ViewPager.SCROLL_STATE_SETTLING) {
                        binding.routineCollectionIndicator.apply {
                            currentPage = currentItem
                            updateDotState()
                        }
                    }
                }
            })
        }

        binding.routineCollectionIndicator.apply {
            pageCount = collectionData.size
            updateDotState()
        }

        binding.routineGridLayout.apply {
            var childWidth = (ScreenUtil.deviceWidth() - (ScreenUtil.DP_16 * 3)) / 2

            routineData.forEachIndexed { index, item ->
                var viewRoutinebinding: ViewRoutineBinding =
                    DataBindingUtil.inflate(layoutInflater, R.layout.view_routine, this, false)

                var params = ConstraintLayout.LayoutParams(viewRoutinebinding.root.layoutParams)

                viewRoutinebinding.root.layoutParams = params.apply {
                    width = childWidth
                    leftMargin = ScreenUtil.DP_16

                    if (index >= 2) {
                        topMargin = ScreenUtil.DP_14 * 3
                    }
                }

                viewRoutinebinding.routineImage.apply {
                    background = context.getDrawable(item.resourceId)
                }
                viewRoutinebinding.routineTitle.apply {
                    text = item.title
                }

                this.addView(viewRoutinebinding.root)
            }
        }

        return binding.root
    }
}

class RoutineCollectionPagerAdapter(
    private val collectionList: List<RoutineCollectionItem>,
    private val context: Context
) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return collectionList.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        var inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var binding: ViewRoutineCollectionBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.view_routine_collection,
                container,
                false
            )

        binding.collectionImage.apply {
            background = context.getDrawable(R.drawable.routine_collection_image_2)
        }

        container.addView(binding.root)

        return binding.root
    }
}