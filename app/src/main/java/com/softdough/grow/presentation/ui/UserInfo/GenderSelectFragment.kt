package com.softdough.grow.presentation.ui.UserInfo

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.softdough.grow.R
import com.softdough.grow.databinding.FragmentGenderSelectBinding
import com.softdough.grow.databinding.ViewGenderPagerItemBinding
import com.softdough.grow.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class GenderSelectFragment : BaseFragment<FragmentGenderSelectBinding>() {
    override val resourceId: Int = R.layout.fragment_gender_select

    private val vm: UserInfoViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        Log.i("", "onCreateView")


        binding.genderViewPager.apply {
            adapter = GenderViewPager(vm!!.genderResources, context)

            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {}

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {}

                override fun onPageSelected(position: Int) {
                    Log.i("", "position : ${position}")
                    vm!!.genderPage.value = position
                }
            })
        }

        return binding.root
    }
}

class GenderViewPager(private val resourceList: List<Int>, private val context: Context) :
    PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return resourceList.size
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        var inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var binding: ViewGenderPagerItemBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.view_gender_pager_item,
                container,
                false
            )

        binding.imageView.apply {
            background = context.getDrawable(resourceList[position])
        }

        container.addView(binding.root)

        return binding.root
    }
}