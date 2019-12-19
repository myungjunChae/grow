package com.softdough.grow.presentation.ui.UserInfo

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.viewpager.widget.ViewPager
import com.softdough.grow.R
import com.softdough.grow.databinding.ActivityUserInfoBinding
import com.softdough.grow.domain.model.Account
import com.softdough.grow.presentation.MainActivity
import com.softdough.grow.presentation.base.BaseActivity
import com.softdough.grow.presentation.bindColor
import com.softdough.grow.presentation.startActivityWithFinish
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class UserInfoActivity : BaseActivity<ActivityUserInfoBinding>() {
    private val vm: UserInfoViewModel by viewModel()

    override val resourceId: Int = R.layout.activity_user_info
    override val statusBarColor: Int by bindColor(R.color.colorWhite)

    private val fragments = listOf(
        GenderSelectFragment(), InfoFormFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.prevButton.setOnClickListener { vm.currentPage.value = 0 }
        binding.nextButton.setOnClickListener {
            when(vm.currentPage.value){
                0->{ vm.currentPage.value = 1 }
                1->{
                    //TODO editText 내용 검사
                    startActivityWithFinish<MainActivity>()
                }
            }
        }

        binding.userInfoViewPager.apply {
            adapter = UserInfoAdapter(fragments, supportFragmentManager)

            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {}

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                }

                override fun onPageSelected(position: Int) {

                }
            })
        }

        binding.genderIndicator.apply {
            Log.i("test","${vm}")
            custom = true
            pageCount = vm.genderResources.size
            updateDotState()
        }

        vm.currentPage.observe(this, Observer { renderViewByFlip() })
        vm.genderPage.observe(this, Observer { renderIndicator() })

    }

    private fun renderIndicator() {
        binding.genderIndicator.apply {
            currentPage = vm.genderPage.value!!
            updateDotState()
        }
    }

    private fun renderViewByFlip() {
        vm.currentPage.value.also {
            binding.userInfoViewPager.currentItem = it!!

            when (it) {
                0 -> {
                    binding.genderIndicator.visibility = View.VISIBLE
                    binding.prevButton.visibility = View.GONE
                    binding.nextButton.text = "선택하기"
                    binding.title.text = "성별 선택"
                }
                1 -> {
                    binding.genderIndicator.visibility = View.INVISIBLE
                    binding.prevButton.visibility = View.VISIBLE
                    binding.nextButton.text = "시작하기"
                    binding.title.text = "유저 정보 입력"
                }
            }
        }
    }

    private fun setUserInfo() {

        val account = Account(null, "test", 10f, 10f, "MAEL", 10, null)
    }
}

class UserInfoAdapter(private val fragments: List<Fragment>, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size
}